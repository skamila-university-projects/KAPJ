package skamila.kapj.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import skamila.kapj.dao.AppUserRoleRepository;
import skamila.kapj.domain.AppUserRole;

import java.util.List;

@Service("appUserRoleService")
public class AppUserRoleServiceImpl implements AppUserRoleService {

    private AppUserRoleRepository appUserRoleRepository;

    @Autowired
    public AppUserRoleServiceImpl(AppUserRoleRepository appUserRoleRepository) {
        this.appUserRoleRepository = appUserRoleRepository;
    }

    @Transactional
    @Override
    public void addAppUserRole(AppUserRole appUserRole) {
        appUserRoleRepository.save(appUserRole);
    }

    @Transactional
    @Override
    public List<AppUserRole> listAppUserRole() {
        return appUserRoleRepository.findAll();
    }

    @Transactional
    @Override
    public AppUserRole getAppUserRole(long id) {
        return appUserRoleRepository.getOne(id);
    }

}
