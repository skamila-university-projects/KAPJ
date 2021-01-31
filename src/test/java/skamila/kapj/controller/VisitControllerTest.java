package skamila.kapj.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.ui.Model;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.support.BindingAwareModelMap;
import org.springframework.web.servlet.view.RedirectView;
import skamila.kapj.controller.utils.AppUserBuilder;
import skamila.kapj.controller.utils.AuthenticationUtils;
import skamila.kapj.controller.utils.VisitBuilder;
import skamila.kapj.dao.AppUserRepository;
import skamila.kapj.dao.AppUserRoleRepository;
import skamila.kapj.dao.VisitRepository;
import skamila.kapj.domain.AppUser;
import skamila.kapj.domain.Visit;
import skamila.kapj.service.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class VisitControllerTest {

    private final AppUserRepository appUserRepositoryMock = mock(AppUserRepository.class);
    private final AppUserRoleRepository appUserRoleRepositoryMock = mock(AppUserRoleRepository.class);
    private final VisitRepository visitRepositoryMock = mock(VisitRepository.class);
    private final AppUserService appUserService = new AppUserServiceImpl(appUserRepositoryMock, appUserRoleRepositoryMock);
    private final AppUserRoleService appUserRoleService = new AppUserRoleServiceImpl(appUserRoleRepositoryMock);
    private final VisitService visitService = new VisitServiceImpl(visitRepositoryMock);
    private final PdfService pdfService = new PdfServiceImpl();
    private final VisitController visitController = new VisitController(appUserService, appUserRoleService, visitService, pdfService);

    @Test
    void addVisit() {
        // given
        AppUser currentUser = new AppUserBuilder().withLogin("user").build();
        initCurrentUser(currentUser);
        AppUser selectedDoctor = new AppUserBuilder().withFirstName("Mr.").withLastName("Doctor").build();
        Visit visit = new VisitBuilder().withDoctor(selectedDoctor).build();
        BindingResult bindingResult = new BeanPropertyBindingResult(visit, "visit");
        // when
        RedirectView redirectView = visitController.addVisit(visit, bindingResult, mock(Model.class), mock(HttpServletRequest.class));
        // then
        assertEquals(currentUser, visit.getPatient());
        assertEquals(selectedDoctor, visit.getDoctor());
        assertEquals(40, visit.getPrice());
        verify(visitRepositoryMock).save(visit);
        assertEquals(redirectView.getUrl(), "/visit/my");
    }

    @Test
    void getAllVisits() {
        // given
        Model model = new BindingAwareModelMap();
        List<Visit> visits = new ArrayList<>();
        visits.add(new Visit());
        visits.add(new Visit());
        when(visitRepositoryMock.findAll()).thenReturn(visits);
        // when
        String redirect = visitController.getAllVisits(model);
        // then
        assertEquals(redirect, "visits");
        assertEquals(visits, model.asMap().get("visits"));
        verify(visitRepositoryMock).findAll();
    }

    @Test
    void getDoctorVisits() {
        // given
        AppUser doctor = new AppUserBuilder().withFirstName("Mr.").withLastName("Doctor").build();
        initCurrentUser(doctor);
        List<Visit> visits = new ArrayList<>();
        visits.add(new Visit());
        visits.add(new Visit());
        Model model = new BindingAwareModelMap();
        when(visitRepositoryMock.findByDoctor(doctor)).thenReturn(visits);
        // when
        String redirect = visitController.getDoctorVisits(model);
        // then
        assertEquals(redirect, "visits");
        assertEquals(visits, model.asMap().get("visits"));
        verify(visitRepositoryMock).findByDoctor(doctor);
    }

    @Test
    void getVisits() {
        // given
        AppUser patient = new AppUserBuilder().withFirstName("Jan").withLastName("Kowalski").build();
        initCurrentUser(patient);
        List<Visit> visits = new ArrayList<>();
        visits.add(new Visit());
        visits.add(new Visit());
        Model model = new BindingAwareModelMap();
        when(visitRepositoryMock.findByPatient(patient)).thenReturn(visits);
        // when
        String redirect = visitController.getVisits(model);
        // then
        assertEquals(redirect, "visits");
        assertEquals(visits, model.asMap().get("visits"));
        verify(visitRepositoryMock).findByPatient(patient);
    }

    @Test
    void cancelVisitByAdminWhenVisitDoesNotExist() {
        // given
        AppUser admin = new AppUserBuilder().withLogin("admin").withAppUserRole("ROLE_ADMIN").build();
        initCurrentUser(admin);
        // when
        RedirectView redirectView = visitController.cancelVisit((long) 1);
        // then
        assertEquals(redirectView.getUrl(), "/visit/admin");
    }

    @Test
    void cancelVisitByAdminWhenVisitIsCancel() {
        // given
        AppUser admin = new AppUserBuilder().withLogin("Jan").withFirstName("Jan").withLastName("Kowalski")
                .withAppUserRole("ROLE_ADMIN").build();
        initCurrentUser(admin);
        Visit visit = new VisitBuilder().withId(1).withCanceled(true).withBillAvailable(false).build();
        when(visitRepositoryMock.findById(visit.getId())).thenReturn(visit);
        // when
        RedirectView redirectView = visitController.cancelVisit(visit.getId());
        // then
        assertTrue(visit.isCanceled());
        assertFalse(visit.isBillAvailable());
        assertEquals(redirectView.getUrl(), "/visit/admin");
        verify(visitRepositoryMock).save(visit);
    }

    @Test
    void cancelVisitByDoctorWhenBillIsNotAvailable() {
        // given
        AppUser doctor = new AppUserBuilder().withLogin("Jan").withFirstName("Jan").withLastName("Kowalski")
                .withAppUserRole("ROLE_DOCTOR").build();
        initCurrentUser(doctor);
        Visit visit = new VisitBuilder().withId(1).withCanceled(false).withBillAvailable(false).build();
        when(visitRepositoryMock.findById(visit.getId())).thenReturn(visit);
        // when
        RedirectView redirectView = visitController.cancelVisit(visit.getId());
        // then
        assertTrue(visit.isCanceled());
        assertFalse(visit.isBillAvailable());
        assertEquals(redirectView.getUrl(), "/visit/doctor");
        verify(visitRepositoryMock).save(visit);
    }

    @Test
    void cancelVisitByPatientWhenBillIsAvailable() {
        // given
        AppUser patient = new AppUserBuilder().withLogin("Jan").withFirstName("Jan").withLastName("Kowalski")
                .withAppUserRole("ROLE_PATIENT").build();
        initCurrentUser(patient);
        Visit visit = new VisitBuilder().withId(1).withCanceled(false).withBillAvailable(true).build();
        when(visitRepositoryMock.findById(visit.getId())).thenReturn(visit);
        // when
        RedirectView redirectView = visitController.cancelVisit(visit.getId());
        // then
        assertTrue(visit.isCanceled());
        assertFalse(visit.isBillAvailable());
        assertEquals(redirectView.getUrl(), "/visit/my");
        verify(visitRepositoryMock).save(visit);
    }

    @Test
    void confirmVisit() {
        // given
        AppUser doctor = new AppUserBuilder().withLogin("Jan").withFirstName("Jan").withLastName("Kowalski")
                .withAppUserRole("ROLE_DOCTOR").build();
        initCurrentUser(doctor);
        Visit visit = new VisitBuilder().withId(1).withConfirmed(false).withCanceled(false).withBillAvailable(false).build();
        when(visitRepositoryMock.findById(visit.getId())).thenReturn(visit);
        // when
        RedirectView redirectView = visitController.confirmVisit(visit.getId());
        // then
        assertEquals(redirectView.getUrl(), "/visit/doctor");
        assertTrue(visit.isConfirmed());
        assertTrue(visit.isBillAvailable());
        assertFalse(visit.isCanceled());
        verify(visitRepositoryMock).save(visit);
    }

    @Test
    void confirmVisitWhenVisitIsCanceled() {
        // given
        AppUser doctor = new AppUserBuilder().withLogin("Jan").withFirstName("Jan").withLastName("Kowalski")
                .withAppUserRole("ROLE_DOCTOR").build();
        initCurrentUser(doctor);
        Visit visit = new VisitBuilder().withId(1).withConfirmed(false).withCanceled(true).withBillAvailable(false).build();
        when(visitRepositoryMock.findById(visit.getId())).thenReturn(visit);
        // when
        RedirectView redirectView = visitController.confirmVisit(visit.getId());
        // then
        assertEquals(redirectView.getUrl(), "/visit/doctor");
        assertFalse(visit.isConfirmed());
        assertFalse(visit.isBillAvailable());
        assertTrue(visit.isCanceled());
        verify(visitRepositoryMock, Mockito.never()).save(visit);
    }

    private void initCurrentUser(AppUser appUser) {
        AuthenticationUtils authenticationUtils = new AuthenticationUtils(appUser);
        authenticationUtils.initSecurityContextMock();
        when(appUserRepositoryMock.findByLogin(appUser.getLogin())).thenReturn(appUser);
    }

}