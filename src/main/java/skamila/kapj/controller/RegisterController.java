package skamila.kapj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;
import skamila.kapj.domain.AppUser;
import skamila.kapj.service.AppUserRoleService;
import skamila.kapj.service.AppUserService;
import skamila.kapj.service.MailService;
import skamila.kapj.service.ReCaptchaService;
import skamila.kapj.utils.AppUtils;
import skamila.kapj.validator.AppUserValidator;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
public class RegisterController {

    private AppUserService appUserService;
    private AppUserRoleService appUserRoleService;
    private ReCaptchaService reCaptchaService;
    private MailService mailService;

    private AppUserValidator appUserValidator = new AppUserValidator();

    @Autowired
    public RegisterController(AppUserService appUserService, AppUserRoleService appUserRoleService, MailService mailService, ReCaptchaService reCaptchaService) {
        this.appUserService = appUserService;
        this.appUserRoleService = appUserRoleService;
        this.mailService = mailService;
        this.reCaptchaService = reCaptchaService;
    }

    @RequestMapping(value = "/")
    public RedirectView emptyPage() {
        return new RedirectView(choiceView());
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
                && reCaptchaService.verify(request.getParameter("g-recaptcha-response"))) {
            appUser.getAppUserRole().add(appUserRoleService.getAppUserRole("ROLE_PATIENT"));
            appUser.setToken(UUID.randomUUID().toString());
            mailService.sendMail(appUser);
            appUserService.addAppUser(appUser);
            return "redirect:register.html";
        }
        return "register";
    }

    @RequestMapping(value = "/activateAccount", method = RequestMethod.GET)
    public RedirectView activateAccount(@RequestParam String token) {
        appUserService.activateAccount(token);
        return new RedirectView("/login");
    }

    private String choiceView() {
        if (AppUtils.isAnonymousUser()) {
            return "/login";
        }
        AppUser currentUser = appUserService.findByLogin(AppUtils.getUserLogin());
        List<String> roles = currentUser.getAppUserRole().stream().map(role -> role.getRole()).collect(Collectors.toList());
        if (roles.contains("ROLE_ADMIN")) {
            return "/visit/admin";
        } else if (roles.contains("ROLE_DOCTOR")) {
            return "/visit/doctor";
        } else if (roles.contains("ROLE_PATIENT")) {
            return "/newVisit";
        } else {
            return "/login";
        }
    }
}
