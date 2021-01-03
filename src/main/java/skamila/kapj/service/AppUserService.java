package skamila.kapj.service;

import skamila.kapj.domain.AppUser;

import java.util.List;

public interface AppUserService {

    void addAppUser(AppUser user);

    void editAppUser(AppUser user);

    List<AppUser> listAppUser();

    void removeAppUser(long id);

    AppUser getAppUser(long id);

}
