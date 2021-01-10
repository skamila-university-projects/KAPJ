package skamila.kapj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import skamila.kapj.domain.AppUser;
import skamila.kapj.domain.AppUserRole;
import skamila.kapj.domain.Visit;
import skamila.kapj.service.AppUserRoleService;
import skamila.kapj.service.AppUserService;
import skamila.kapj.service.VisitService;
import skamila.kapj.utils.AppUtils;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class VisitController {

    private AppUserService appUserService;
    private AppUserRoleService appUserRoleService;
    private VisitService visitService;

    @Autowired
    public VisitController(AppUserService appUserService, AppUserRoleService appUserRoleService, VisitService visitService) {
        this.appUserService = appUserService;
        this.appUserRoleService = appUserRoleService;
        this.visitService = visitService;
    }

    @RequestMapping(value = "/newVisit")
    public String newVisit(@Valid @ModelAttribute("appUser") Visit visit, BindingResult result, Model model, HttpServletRequest request) {
        model.addAttribute("visit", new Visit());
        AppUserRole doctorRole = appUserRoleService.getAppUserRole("ROLE_DOCTOR");
        model.addAttribute("doctorsList",  appUserService.findByRole(doctorRole));
        return "newVisit";
    }

    @RequestMapping(value = "/addVisit", method = RequestMethod.POST)
    public String addVisit(@Valid @ModelAttribute("visit") Visit visit, BindingResult result, Model model, HttpServletRequest request) {
        AppUser currentUser = appUserService.findByLogin(AppUtils.getUserLogin());
        visit.setPatient(currentUser);
        visit.setPrice(40);
        visitService.addVisit(visit);
        return "newVisit";
    }

}
