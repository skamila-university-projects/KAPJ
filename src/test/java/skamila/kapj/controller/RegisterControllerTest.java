package skamila.kapj.controller;

import org.junit.jupiter.api.Test;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.ui.Model;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import skamila.kapj.dao.AppUserRepository;
import skamila.kapj.dao.AppUserRoleRepository;
import skamila.kapj.domain.AppUser;
import skamila.kapj.service.*;

import javax.servlet.http.HttpServletRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
        AppUser appUser = createUserWithNotActiveAccount(token);
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
        AppUser appUser = createUserWithActiveAccount(token);
        registerController.activateAccount(token);
        when(appUserRepositoryMock.findByToken(token)).thenReturn(appUser);
        // when
        registerController.activateAccount(token);
        // then
        assertTrue(appUser.isEnabled());
    }

    private AppUser createUserWithNotActiveAccount(String token) {
        AppUser appUser = new AppUser();
        appUser.setToken(token);
        return appUser;
    }

    private AppUser createUserWithActiveAccount(String token) {
        AppUser appUser = new AppUser();
        appUser.setToken(token);
        appUser.setEnabled(true);
        return appUser;
    }

}