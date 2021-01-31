package skamila.kapj.controller;

import org.junit.jupiter.api.Test;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
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
        AuthenticationUtils authenticationUtils = new AuthenticationUtils();
        authenticationUtils.initSecurityContextMock();
        AppUser currentUser = authenticationUtils.getCurrentUser();
        AppUser selectedDoctor = new AppUserBuilder().withFirstName("Mr.").withLastName("Doctor").build();
        Visit visit = new VisitBuilder().withDoctor(selectedDoctor).build();
        BindingResult bindingResult = new BeanPropertyBindingResult(visit, "visit");
        when(appUserRepositoryMock.findByLogin(any())).thenReturn(currentUser);
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
        AuthenticationUtils authenticationUtils = new AuthenticationUtils(doctor);
        authenticationUtils.initSecurityContextMock();
        List<Visit> visits = new ArrayList<>();
        visits.add(new Visit());
        visits.add(new Visit());
        when(appUserRepositoryMock.findByLogin(any())).thenReturn(doctor);
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
        AppUser patient = new AppUserBuilder().withFirstName("Jan").withLastName("Kowalski").build();
        AuthenticationUtils authenticationUtils = new AuthenticationUtils(patient);
        authenticationUtils.initSecurityContextMock();
        List<Visit> visits = new ArrayList<>();
        visits.add(new Visit());
        visits.add(new Visit());
        when(appUserRepositoryMock.findByLogin(any())).thenReturn(patient);
        Model model = new BindingAwareModelMap();
        when(visitRepositoryMock.findByPatient(patient)).thenReturn(visits);
        // when
        String redirect = visitController.getVisits(model);
        // then
        assertEquals(redirect, "visits");
        assertEquals(visits, model.asMap().get("visits"));
        verify(visitRepositoryMock).findByPatient(patient);
    }

}