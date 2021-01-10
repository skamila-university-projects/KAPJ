package skamila.kapj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;
import skamila.kapj.domain.AppUser;
import skamila.kapj.domain.AppUserRole;
import skamila.kapj.domain.Visit;
import skamila.kapj.service.AppUserRoleService;
import skamila.kapj.service.AppUserService;
import skamila.kapj.service.PdfService;
import skamila.kapj.service.VisitService;
import skamila.kapj.utils.AppUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/visit")
public class VisitController {

    private AppUserService appUserService;
    private AppUserRoleService appUserRoleService;
    private VisitService visitService;
    private PdfService pdfService;

    @Autowired
    public VisitController(AppUserService appUserService, AppUserRoleService appUserRoleService, VisitService visitService, PdfService pdfService) {
        this.appUserService = appUserService;
        this.appUserRoleService = appUserRoleService;
        this.visitService = visitService;
        this.pdfService = pdfService;
    }

    @Secured("ROLE_PATIENT")
    @RequestMapping(value = "/new")
    public String newVisit(@Valid @ModelAttribute("appUser") Visit visit, BindingResult result, Model model, HttpServletRequest request) {
        model.addAttribute("visit", new Visit());
        AppUserRole doctorRole = appUserRoleService.getAppUserRole("ROLE_DOCTOR");
        model.addAttribute("doctorsList", appUserService.findByRole(doctorRole));
        return "newVisit";
    }

    @Secured("ROLE_PATIENT")
    @RequestMapping(value = "/addVisit", method = RequestMethod.POST)
    public RedirectView addVisit(@Valid @ModelAttribute("visit") Visit visit, BindingResult result, Model model, HttpServletRequest request) {
        AppUser currentUser = appUserService.findByLogin(AppUtils.getUserLogin());
        visit.setPatient(currentUser);
        visit.setPrice(40);
        visitService.addVisit(visit);
        return new RedirectView("/visit/my");
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/admin")
    public String getAllVisits(Model model) {
        model.addAttribute("visits", visitService.listVisits());
        return "visits";
    }

    @Secured("ROLE_DOCTOR")
    @RequestMapping(value = "/doctor")
    public String getDoctorVisits(Model model) {
        AppUser currentUser = appUserService.findByLogin(AppUtils.getUserLogin());
        model.addAttribute("visits", visitService.findByDoctor(currentUser));
        return "visits";
    }

    @Secured("ROLE_PATIENT")
    @RequestMapping(value = "/my")
    public String getVisits(Model model) {
        AppUser currentUser = appUserService.findByLogin(AppUtils.getUserLogin());
        model.addAttribute("visits", visitService.findByPatient(currentUser));
        return "visits";
    }

    @RequestMapping(value = "/cancel", method = RequestMethod.GET)
    public String cancelVisit(@RequestParam("visitId") Long visitId) {
        visitService.cancelVisit(visitId);
        return "visits";
    }

    @RequestMapping(value = "/confirm", method = RequestMethod.GET)
    public String confirmVisit(@RequestParam("visitId") Long visitId) {
        visitService.confirmVisit(visitId);
        return "visits";
    }

    @RequestMapping(value = "/pdf", method = RequestMethod.GET)
    public void getPdf(@RequestParam("visitId") Long visitId, HttpServletResponse response) {
        String userLogin = AppUtils.getUserLogin();
        AppUser user = appUserService.findByLogin(userLogin);
        Visit visit = visitService.findById(visitId);
        if ((visit.getPatient().getId() == user.getId() || visit.getDoctor().getId() == user.getId() || isUserAdmin()) && visit.isConfirmed()) {
            pdfService.generatePdf(visit, response);
        }
    }

    private boolean isUserAdmin() {
        AppUser currentUser = appUserService.findByLogin(AppUtils.getUserLogin());
        List<String> roles = currentUser.getAppUserRole().stream().map(role -> role.getRole()).collect(Collectors.toList());
        if (roles.contains("ROLE_ADMIN")) {
            return true;
        } else {
            return false;
        }
    }

    private String choiceView() {
        AppUser currentUser = appUserService.findByLogin(AppUtils.getUserLogin());
        List<String> roles = currentUser.getAppUserRole().stream().map(role -> role.getRole()).collect(Collectors.toList());
        if (roles.contains("ROLE_ADMIN")) {
            return "/visit/admin";
        } else if (roles.contains("ROLE_DOCTOR")) {
            return "/visit/doctor";
        } else if (roles.contains("ROLE_PATIENT")) {
            return "/visit/my";
        } else {
            return "/";
        }
    }
}
