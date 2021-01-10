package skamila.kapj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import skamila.kapj.dao.AppUserRepository;
import skamila.kapj.dao.VisitRepository;
import skamila.kapj.domain.AppUser;
import skamila.kapj.domain.Visit;

@RestController
@RequestMapping("/rest")
public class MyRestController {

    private AppUserRepository appUserRepository;
    private VisitRepository visitRepository;

    @Autowired
    public MyRestController(AppUserRepository appUserRepository, VisitRepository visitRepository) {
        this.appUserRepository = appUserRepository;
        this.visitRepository = visitRepository;
    }

    @RequestMapping(value = "/user/{login}", method = RequestMethod.GET, produces = "application/json")
    public AppUser getAppUserInJSON(@PathVariable String login) {
        return appUserRepository.findByLogin(login);
    }

    @RequestMapping(value = "/user/{login}.xml", method = RequestMethod.GET, produces = "application/xml")
    public AppUser getAppUserInXML(@PathVariable String login) {
        return appUserRepository.findByLogin(login);
    }

    @RequestMapping(value = "/visit/{visitId}", method = RequestMethod.GET, produces = "application/json")
    public Visit getVisitInJSON(@PathVariable String visitId) {
        return visitRepository.findById(Long.parseLong(visitId));
    }

    @RequestMapping(value = "/visit/{visitId}.xml", method = RequestMethod.GET, produces = "application/xml")
    public Visit getVisitInXML(@PathVariable String visitId) {
        return visitRepository.findById(Long.parseLong(visitId));
    }

}
