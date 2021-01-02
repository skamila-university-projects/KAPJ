package skamila.kapj.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import skamila.kapj.domain.AppUserRole;
import skamila.kapj.service.AppUserRoleService;

import java.util.HashSet;
import java.util.Set;

public class AppUserRoleListConverter implements Converter<String[], Set<AppUserRole>> {

    private AppUserRoleService appUserRoleService;

    @Autowired
    public AppUserRoleListConverter(AppUserRoleService appUserRoleService) {
        this.appUserRoleService = appUserRoleService;
    }

    @Override
    public Set<AppUserRole> convert(String[] roleIds) {
        Set<AppUserRole> userRoleList = new HashSet<>();
        for (String roleId : roleIds) {
            userRoleList.add(appUserRoleService.getAppUserRole(Integer.parseInt(roleId)));
        }
        return userRoleList;
    }
}