package skamila.kapj.utils;

import org.springframework.core.convert.converter.Converter;
import skamila.kapj.domain.AppUser;
import skamila.kapj.service.AppUserService;

public class AppUserConverter implements Converter<String, AppUser> {

    private AppUserService appUserRoleService;

    public AppUserConverter(AppUserService appUserRoleService) {
        this.appUserRoleService = appUserRoleService;
    }

    @Override
    public AppUser convert(String userId) {
        return appUserRoleService.getAppUser(Integer.parseInt(userId));
    }

}
