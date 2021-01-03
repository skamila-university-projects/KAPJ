package skamila.kapj.service;

import skamila.kapj.domain.AppUserRole;

import java.util.List;

public interface AppUserRoleService {

    void addAppUserRole(AppUserRole appUserRole);

    List<AppUserRole> listAppUserRole();

    AppUserRole getAppUserRole(long id);
}