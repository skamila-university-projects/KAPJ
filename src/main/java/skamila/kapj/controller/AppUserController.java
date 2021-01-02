package skamila.kapj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import skamila.kapj.domain.AppUser;
import skamila.kapj.service.AddressService;
import skamila.kapj.service.AppUserRoleService;
import skamila.kapj.service.AppUserService;
import skamila.kapj.service.ReCaptchaService;
import skamila.kapj.validator.AppUserValidator;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class AppUserController {

    private AppUserService appUserService;
    private AddressService addressService;
    private AppUserRoleService appUserRoleService;
    private ReCaptchaService reCaptchaService;
    private AppUserValidator appUserValidator = new AppUserValidator();

    @Autowired
    public AppUserController(AppUserService appUserService, AddressService addressService, AppUserRoleService appUserRoleService, ReCaptchaService reCaptchaService) {
        this.appUserService = appUserService;
        this.addressService = addressService;
        this.appUserRoleService = appUserRoleService;
        this.reCaptchaService = reCaptchaService;
    }

    @RequestMapping(value = "/appUsers")
    public String showAppUsers(Model model, HttpServletRequest request) {
        int appUserId = ServletRequestUtils.getIntParameter(request, "appUserId", -1);
        if (appUserId > 0) {
            AppUser appUser = appUserService.getAppUser(appUserId);
            appUser.setPassword("");
            appUser.setAddress(addressService.getAddress(appUserService.getAppUser(appUserId).getAddress().getId()));
            model.addAttribute("selectedAddress", appUserService.getAppUser(appUserId).getAddress().getId());
            model.addAttribute("appUser", appUser);
        } else {
            model.addAttribute("appUser", new AppUser());
        }
        model.addAttribute("appUserList", appUserService.listAppUser());
        model.addAttribute("addressesList", addressService.listAddress());
        model.addAttribute("appUserRoleList",  appUserRoleService.listAppUserRole());
        return "appUser";
    }

    @RequestMapping("/deleteUser/{appUserId}")
    public String deleteUser(@PathVariable("appUserId") Long appUserId) {
        appUserService.removeAppUser(appUserId);
        return "redirect:/appUsers.html";
    }

    @RequestMapping(value = "/addAppUser", method = RequestMethod.POST)
    public String addAppUser(@Valid @ModelAttribute("appUser") AppUser appUser, BindingResult result, Model model, HttpServletRequest request) {
        appUserValidator.validate(appUser, result);
        if (result.getErrorCount() == 0 && reCaptchaService.verify(request.getParameter("g-recaptcha-response"))) {
            if (appUser.getId() == 0) {
                appUserService.addAppUser(appUser);
            } else {
                appUserService.editAppUser(appUser);
            }
            return "redirect:appUsers.html";
        }
        model.addAttribute("appUserList", appUserService.listAppUser());
        return "appUser";
    }
}
