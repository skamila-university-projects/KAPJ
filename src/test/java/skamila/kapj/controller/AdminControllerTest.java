package skamila.kapj.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.ui.Model;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import skamila.kapj.dao.AppUserRepository;
import skamila.kapj.dao.AppUserRoleRepository;
import skamila.kapj.domain.AppUser;
import skamila.kapj.service.AppUserRoleService;
import skamila.kapj.service.AppUserRoleServiceImpl;
import skamila.kapj.service.AppUserService;
import skamila.kapj.service.AppUserServiceImpl;

import javax.servlet.http.HttpServletRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class AdminControllerTest {

    private final AppUserRepository appUserRepositoryMock = mock(AppUserRepository.class);
    private final AppUserRoleRepository appUserRoleRepositoryMock = mock(AppUserRoleRepository.class);
    private final AppUserService appUserService = new AppUserServiceImpl(appUserRepositoryMock, appUserRoleRepositoryMock);
    private final AppUserRoleService appUserRoleService = new AppUserRoleServiceImpl(appUserRoleRepositoryMock);
    private final AdminController adminController = new AdminController(appUserService, appUserRoleService);

    @Test
    void successfulAddAppUser() {
        // given
        AppUser appUser = new AppUserBuilder().withLogin("login").withPassword("password").withEnabled(true)
                .withFirstName("Jan").withLastName("Kowalski").withPhoneNumber("+48-000-000-000")
                .withAppUserRole("ROLE_ADMIN").withPesel("000000000").withEmail("mail@mail.com").build();
        BindingResult bindingResult = new BeanPropertyBindingResult(appUser, "appUser");
        // when
        String redirect = adminController.addAppUser(appUser, bindingResult, mock(Model.class), mock(HttpServletRequest.class));
        // then
        verify(appUserRepositoryMock).save(appUser);
        assertEquals("redirect:register.html", redirect);
    }

    @Test
    void unsuccessfulAddAppUserWithoutLogin() {
        // given
        AppUser appUser = new AppUserBuilder().withPassword("password").withEnabled(true)
                .withFirstName("Jan").withLastName("Kowalski").withPhoneNumber("+48-000-000-000")
                .withAppUserRole("ROLE_ADMIN").withPesel("000000000").withEmail("mail@mail.com").build();
        BindingResult bindingResult = new BeanPropertyBindingResult(appUser, "appUser");
        // when
        String redirect = adminController.addAppUser(appUser, bindingResult, mock(Model.class), mock(HttpServletRequest.class));
        // then
        verify(appUserRepositoryMock, Mockito.never()).save(appUser);
        assertEquals(1, bindingResult.getErrorCount());
        assertNotEquals(null, bindingResult.getFieldError("login"));
        assertEquals("addUser", redirect);
    }

    @Test
    void unsuccessfulAddAppUserWithoutPassword() {
        // given
        AppUser appUser = new AppUserBuilder().withLogin("login").withEnabled(true)
                .withFirstName("Jan").withLastName("Kowalski").withPhoneNumber("+48-000-000-000")
                .withAppUserRole("ROLE_ADMIN").withPesel("000000000").withEmail("mail@mail.com").build();
        BindingResult bindingResult = new BeanPropertyBindingResult(appUser, "appUser");
        // when
        String redirect = adminController.addAppUser(appUser, bindingResult, mock(Model.class), mock(HttpServletRequest.class));
        // then
        verify(appUserRepositoryMock, Mockito.never()).save(appUser);
        assertEquals(1, bindingResult.getErrorCount());
        assertNotEquals(null, bindingResult.getFieldError("password"));
        assertEquals("addUser", redirect);
    }

    @Test
    void unsuccessfulAddAppUserWithoutFistName() {
        // given
        AppUser appUser = new AppUserBuilder().withLogin("login").withPassword("password").withEnabled(true)
                .withLastName("Kowalski").withPhoneNumber("+48-000-000-000")
                .withAppUserRole("ROLE_ADMIN").withPesel("000000000").withEmail("mail@mail.com").build();
        BindingResult bindingResult = new BeanPropertyBindingResult(appUser, "appUser");
        // when
        String redirect = adminController.addAppUser(appUser, bindingResult, mock(Model.class), mock(HttpServletRequest.class));
        // then
        verify(appUserRepositoryMock, Mockito.never()).save(appUser);
        assertEquals(1, bindingResult.getErrorCount());
        assertNotEquals(null, bindingResult.getFieldError("firstName"));
        assertEquals("addUser", redirect);
    }

    @Test
    void unsuccessfulAddAppUserWithoutLastName() {
        // given
        AppUser appUser = new AppUserBuilder().withLogin("login").withPassword("password").withEnabled(true)
                .withFirstName("Jan").withPhoneNumber("+48-000-000-000")
                .withAppUserRole("ROLE_ADMIN").withPesel("000000000").withEmail("mail@mail.com").build();
        BindingResult bindingResult = new BeanPropertyBindingResult(appUser, "appUser");
        // when
        String redirect = adminController.addAppUser(appUser, bindingResult, mock(Model.class), mock(HttpServletRequest.class));
        // then
        verify(appUserRepositoryMock, Mockito.never()).save(appUser);
        assertEquals(1, bindingResult.getErrorCount());
        assertNotEquals(null, bindingResult.getFieldError("lastName"));
        assertEquals("addUser", redirect);
    }

    @Test
    void unsuccessfulAddAppUserWrongMail() {
        // given
        AppUser appUser = new AppUserBuilder().withLogin("login").withPassword("password").withEnabled(true)
                .withFirstName("Jan").withLastName("Kowalski").withPhoneNumber("+48-000-000-000")
                .withAppUserRole("ROLE_ADMIN").withPesel("000000000").withEmail("mail").build();
        BindingResult bindingResult = new BeanPropertyBindingResult(appUser, "appUser");
        // when
        String redirect = adminController.addAppUser(appUser, bindingResult, mock(Model.class), mock(HttpServletRequest.class));
        // then
        verify(appUserRepositoryMock, Mockito.never()).save(appUser);
        assertEquals(1, bindingResult.getErrorCount());
        assertNotEquals(null, bindingResult.getFieldError("email"));
        assertEquals("addUser", redirect);
    }

    @Test
    void unsuccessfulAddAppUserWithoutMail() {
        // given
        AppUser appUser = new AppUserBuilder().withLogin("login").withPassword("password").withEnabled(true)
                .withFirstName("Jan").withLastName("Kowalski").withPhoneNumber("+48-000-000-000")
                .withAppUserRole("ROLE_ADMIN").withPesel("000000000").build();
        BindingResult bindingResult = new BeanPropertyBindingResult(appUser, "appUser");
        // when
        String redirect = adminController.addAppUser(appUser, bindingResult, mock(Model.class), mock(HttpServletRequest.class));
        // then
        verify(appUserRepositoryMock, Mockito.never()).save(appUser);
        assertEquals(1, bindingResult.getErrorCount());
        assertNotEquals(null, bindingResult.getFieldError("email"));
        assertEquals("addUser", redirect);
    }


    @Test
    void unsuccessfulAddAppUserWithoutPhoneNumber() {
        // given
        AppUser appUser = new AppUserBuilder().withLogin("login").withPassword("password").withEnabled(true)
                .withFirstName("Jan").withLastName("Kowalski")
                .withAppUserRole("ROLE_ADMIN").withPesel("000000000").withEmail("mail@mail.com").build();
        BindingResult bindingResult = new BeanPropertyBindingResult(appUser, "appUser");
        // when
        String redirect = adminController.addAppUser(appUser, bindingResult, mock(Model.class), mock(HttpServletRequest.class));
        // then
        verify(appUserRepositoryMock, Mockito.never()).save(appUser);
        assertEquals(1, bindingResult.getErrorCount());
        assertNotEquals(null, bindingResult.getFieldError("phoneNumber"));
        assertEquals("addUser", redirect);
    }

    @Test
    void unsuccessfulAddAppUserWithoutPesel() {
        // given
        AppUser appUser = new AppUserBuilder().withLogin("login").withPassword("password").withEnabled(true)
                .withFirstName("Jan").withLastName("Kowalski").withPhoneNumber("+48-000-000-000")
                .withEmail("mail@mail.com").build();
        BindingResult bindingResult = new BeanPropertyBindingResult(appUser, "appUser");
        // when
        String redirect = adminController.addAppUser(appUser, bindingResult, mock(Model.class), mock(HttpServletRequest.class));
        // then
        verify(appUserRepositoryMock, Mockito.never()).save(appUser);
        assertEquals(1, bindingResult.getErrorCount());
        assertNotEquals(null, bindingResult.getFieldError("pesel.PESEL"));
        assertEquals("addUser", redirect);
    }

}