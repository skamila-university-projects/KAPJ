package skamila.kapj.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import skamila.kapj.domain.AppUserRole;
import skamila.kapj.service.AppUserRoleService;

import java.util.HashSet;
import java.util.Set;

public class AppUserRoleConverter implements Converter<String, Set<AppUserRole>> {

    private AppUserRoleService appUserRoleService;

    @Autowired
    public AppUserRoleConverter(AppUserRoleService appUserRoleService) {
        this.appUserRoleService = appUserRoleService;
    }

    @Override
    public Set<AppUserRole> convert(String roleId) {
        Set<AppUserRole> userRoleList = new HashSet<>();
        userRoleList.add(appUserRoleService.getAppUserRole(Integer.parseInt(roleId)));
        return userRoleList;
    }
}




