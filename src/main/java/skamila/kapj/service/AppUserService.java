package skamila.kapj.service;

import org.springframework.security.access.annotation.Secured;
import skamila.kapj.domain.AppUser;

import java.util.List;

public interface AppUserService {

    @Secured("ROLE_ADMIN")
    void addAppUser(AppUser user);

    @Secured("hasRole(ROLE_ADMIN) OR ({#appUser.login == principal.username})")
    void editAppUser(AppUser user);

    List<AppUser> listAppUser();

    @Secured("ROLE_ADMIN")
    void removeAppUser(long id);

    AppUser getAppUser(long id);

    AppUser findByLogin(String login);

}
