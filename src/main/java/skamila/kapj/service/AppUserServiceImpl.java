package skamila.kapj.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import skamila.kapj.dao.AppUserRepository;
import skamila.kapj.dao.AppUserRoleRepository;
import skamila.kapj.domain.AppUser;

import java.util.List;

@Service
public class AppUserServiceImpl implements AppUserService {

    private AppUserRepository appUserRepository;
    private AppUserRoleRepository appUserRoleRepository;

    @Autowired
    public AppUserServiceImpl(AppUserRepository appUserRepository, AppUserRoleRepository appUserRoleRepository) {
        this.appUserRepository = appUserRepository;
        this.appUserRoleRepository = appUserRoleRepository;
    }

    @Transactional
    @Override
    public void addAppUser(AppUser user) {
        user.getAppUserRole().add(appUserRoleRepository.findByRole("ROLE_USER"));
        user.setPassword(hashPassword(user.getPassword()));
        appUserRepository.save(user);
    }

    @Transactional
    @Override
    public void editAppUser(AppUser user) {
        user.getAppUserRole().add(appUserRoleRepository.findByRole("ROLE_USER"));
        user.setPassword(hashPassword(user.getPassword()));
        appUserRepository.save(user);
    }

    private String hashPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

    @Transactional
    @Override
    public List<AppUser> listAppUser() {
        return appUserRepository.findAll();
    }

    @Transactional
    @Override
    public void removeAppUser(long id) {
        appUserRepository.delete(id);
    }

    @Transactional
    @Override
    public AppUser getAppUser(long id) {
        return appUserRepository.findById(id);
    }

    @Transactional
    public AppUser findByLogin(String login) {
        return appUserRepository.findByLogin(login);
    }


}
