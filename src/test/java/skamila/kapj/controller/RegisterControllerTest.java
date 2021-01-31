package skamila.kapj.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.ui.Model;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import skamila.kapj.controller.utils.AppUserBuilder;
import skamila.kapj.dao.AppUserRepository;
import skamila.kapj.dao.AppUserRoleRepository;
import skamila.kapj.domain.AppUser;
import skamila.kapj.domain.AppUserRole;
import skamila.kapj.service.*;

import javax.servlet.http.HttpServletRequest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RegisterControllerTest {

    private final AppUserRepository appUserRepositoryMock = mock(AppUserRepository.class);
    private final AppUserRoleRepository appUserRoleRepositoryMock = mock(AppUserRoleRepository.class);
    private final AppUserService appUserService = new AppUserServiceImpl(appUserRepositoryMock, appUserRoleRepositoryMock);
    private final AppUserRoleService appUserRoleService = new AppUserRoleServiceImpl(appUserRoleRepositoryMock);
    private final JavaMailSender javaMailSenderMock = mock(JavaMailSender.class);
    private final MailService mailService = new MailService(javaMailSenderMock);
    private final ReCaptchaService reCaptchaServiceMock = mock(ReCaptchaService.class);
    private final RegisterController registerController = new RegisterController(appUserService, appUserRoleService, mailService, reCaptchaServiceMock);

    @Test
    void activateAccountThatIsNotActive() {
        // given
        String token = "token";
        AppUser appUser = new AppUserBuilder().withToken(token).withEnabled(false).build();
        registerController.activateAccount(token);
        when(appUserRepositoryMock.findByToken(token)).thenReturn(appUser);
        // when
        registerController.activateAccount(token);
        // then
        assertTrue(appUser.isEnabled());
    }

    @Test
    void activateAccountThatIsActive() {
        // given
        String token = "token";
        AppUser appUser = new AppUserBuilder().withToken(token).withEnabled(true).build();
        registerController.activateAccount(token);
        when(appUserRepositoryMock.findByToken(token)).thenReturn(appUser);
        // when
        registerController.activateAccount(token);
        // then
        assertTrue(appUser.isEnabled());
    }

    @Test
    void successfulAddPatient() {
        // given
        String role = "ROLE_PATIENT";
        AppUser appUser = new AppUserBuilder().withLogin("login").withPassword("password")
                .withFirstName("Jan").withLastName("Kowalski").withPhoneNumber("+48-000-000-000")
                .withPesel("000000000").withEmail("mail@mail.com").build();
        BindingResult bindingResult = new BeanPropertyBindingResult(appUser, "appUser");
        AppUserRole appUserRole = new AppUserRole();
        appUserRole.setRole(role);
        when(appUserRoleRepositoryMock.findByRole(role)).thenReturn(appUserRole);
        when(reCaptchaServiceMock.verify(any())).thenReturn(true);
        // when
        String redirect = registerController.addPatient(appUser, bindingResult, mock(Model.class), mock(HttpServletRequest.class));
        // then
        boolean isPatientRole = appUser.getAppUserRole().stream().anyMatch(userRole -> userRole.equals(appUserRole));
        assertTrue(isPatientRole);
        assertFalse(appUser.isEnabled());
        verify(appUserRepositoryMock).save(appUser);
        assertEquals("redirect:register.html", redirect);
    }

    @Test
    void unsuccessfulAddPatientReCaptchaFailed() {
        // given
        String role = "ROLE_PATIENT";
        AppUser appUser = new AppUserBuilder().withLogin("login").withPassword("password")
                .withFirstName("Jan").withLastName("Kowalski").withPhoneNumber("+48-000-000-000")
                .withPesel("000000000").withEmail("mail@mail.com").build();
        BindingResult bindingResult = new BeanPropertyBindingResult(appUser, "appUser");
        AppUserRole appUserRole = new AppUserRole();
        appUserRole.setRole(role);
        when(appUserRoleRepositoryMock.findByRole(role)).thenReturn(appUserRole);
        when(reCaptchaServiceMock.verify(any())).thenReturn(false);
        // when
        String redirect = registerController.addPatient(appUser, bindingResult, mock(Model.class), mock(HttpServletRequest.class));
        // then
        verify(appUserRepositoryMock, Mockito.never()).save(appUser);
        assertEquals("register", redirect);
    }

}