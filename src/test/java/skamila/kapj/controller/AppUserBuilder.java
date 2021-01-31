package skamila.kapj.controller;

import skamila.kapj.domain.AppUser;
import skamila.kapj.domain.AppUserRole;
import skamila.kapj.domain.Pesel;

import java.util.HashSet;
import java.util.Set;

public class AppUserBuilder {

    private AppUser appUser = new AppUser();

    public AppUserBuilder() {

    }

    public AppUser build() {
        return appUser;
    }

    public AppUserBuilder withLogin(String login) {
        appUser.setLogin(login);
        return this;
    }

    public AppUserBuilder withPassword(String password) {
        appUser.setPassword(password);
        return this;
    }

    public AppUserBuilder withEnabled(boolean enabled) {
        appUser.setEnabled(enabled);
        return this;
    }

    public AppUserBuilder withFirstName(String firstName) {
        appUser.setFirstName(firstName);
        return this;
    }

    public AppUserBuilder withLastName(String lastName) {
        appUser.setLastName(lastName);
        return this;
    }

    public AppUserBuilder withEmail(String email) {
        appUser.setEmail(email);
        return this;
    }

    public AppUserBuilder withPhoneNumber(String phoneNumber) {
        appUser.setPhoneNumber(phoneNumber);
        return this;
    }

    public AppUserBuilder withAppUserRole(String roleName) {
        AppUserRole role = new AppUserRole();
        role.setRole(roleName);
        Set<AppUserRole> roles = new HashSet<>();
        roles.add(role);
        appUser.setAppUserRole(roles);
        return this;
    }

    public AppUserBuilder withPesel(String pesel) {
        appUser.setPesel(new Pesel(pesel));
        return this;
    }

    public AppUserBuilder withToken(String token) {
        appUser.setToken(token);
        return this;
    }

}
