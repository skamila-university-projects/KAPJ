package skamila.kapj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import skamila.kapj.dao.AppUserRoleRepository;
import skamila.kapj.domain.AppUser;
import skamila.kapj.service.AppUserRoleService;
import skamila.kapj.service.AppUserService;
import skamila.kapj.validator.AppUserValidator;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping(value = "/admin")
@Secured("ROLE_ADMIN")
public class AdminController {

    private AppUserService appUserService;
    private AppUserRoleService appUserRoleService;

    private AppUserValidator appUserValidator = new AppUserValidator();

    @Autowired
    public AdminController(AppUserService appUserService, AppUserRoleService appUserRoleService, AppUserRoleRepository appUserRoleRepository) {
        this.appUserService = appUserService;
        this.appUserRoleService = appUserRoleService;
    }

    @RequestMapping(value = "/register")
    public String register(@Valid @ModelAttribute("appUser") AppUser appUser, BindingResult result, Model model, HttpServletRequest request) {
        model.addAttribute("appUser", new AppUser());
        model.addAttribute("appUserRoleList",  appUserRoleService.listAppUserRole());
        return "addUser";
    }

    @RequestMapping(value = "/addPatient", method = RequestMethod.POST)
    public String addPatient(@Valid @ModelAttribute("appUser") AppUser appUser, BindingResult result, Model model, HttpServletRequest request) {
        appUserValidator.validate(appUser, result);
        if (result.getErrorCount() == 0) {
            appUserService.addAppUser(appUser);
            return "redirect:register.html";
        }
        return "register";
    }

}