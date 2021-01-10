package skamila.kapj.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
import skamila.kapj.service.ReCaptchaService;
import skamila.kapj.validator.AppUserValidator;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Locale;

@Controller
public class RegisterController {

    private AppUserService appUserService;
    private AppUserRoleService appUserRoleService;
    private ReCaptchaService reCaptchaService;

    private AppUserRoleRepository appUserRoleRepository;

    private AppUserValidator appUserValidator = new AppUserValidator();

    @Autowired
    public RegisterController(AppUserService appUserService, AppUserRoleService appUserRoleService, AppUserRoleRepository appUserRoleRepository) {
        this.appUserService = appUserService;
        this.appUserRoleService = appUserRoleService;
        this.appUserRoleRepository = appUserRoleRepository;
        this.reCaptchaService = reCaptchaService;
    }

    @RequestMapping(value = "/")
    public String emptyPage() {
        return "empty";
    }

    @RequestMapping(value = "/register")
    public String register(Locale locale, Model model) {
        model.addAttribute("appUser", new AppUser());
        return "register";
    }

    @RequestMapping(value = "/addPatient", method = RequestMethod.POST)
    public String addPatient(@Valid @ModelAttribute("appUser") AppUser appUser, BindingResult result, Model model, HttpServletRequest request) {
        appUserValidator.validate(appUser, result);
        if (result.getErrorCount() == 0
//                && reCaptchaService.verify(request.getParameter("g-recaptcha-response"))
        ) {
            appUser.getAppUserRole().add(appUserRoleRepository.findByRole("ROLE_PATIENT"));
            appUserService.addAppUser(appUser);
            return "redirect:register.html";
        }
        return "register";
    }
}
