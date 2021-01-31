package skamila.kapj.controller.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import skamila.kapj.domain.AppUser;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AuthenticationUtils {

    private final AppUser appUser;

    public AuthenticationUtils(AppUser appUser) {
        this.appUser = appUser;
    }


    public AppUser getCurrentUser() {
        return appUser;
    }

    public void initSecurityContextMock() {
        Authentication authentication = mock(Authentication.class);
        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        when(authentication.getPrincipal()).thenReturn(getCurrentUser());
    }

}
