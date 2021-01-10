package skamila.kapj.service;

import skamila.kapj.domain.AppUser;
import skamila.kapj.domain.AppUserRole;

import java.util.List;

public interface AppUserService {

    void addAppUser(AppUser user);

    void editAppUser(AppUser user);

    List<AppUser> listAppUser();

    void removeAppUser(long id);

    AppUser getAppUser(long id);

    AppUser findByLogin(String login);

    List<AppUser> findByRole(AppUserRole appUserRole);

}
