package skamila.kapj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import skamila.kapj.domain.AppUser;

@Controller
public class AppUserController {

    @RequestMapping(value = "/appUsers")
    public ModelAndView showAppUsers() {
        return new ModelAndView("appUser", "appUser", new AppUser());
    }

    @RequestMapping(value = "/addAppUser", method = RequestMethod.POST)
    public String addAppUser(@ModelAttribute("appUser") AppUser appUser) {
        System.out.println("First Name: " + appUser.getFirstName() + "\nLast name: " + appUser.getLastName()
                + "\nTel.: " + appUser.getTelephone() + "\nEmail: " + appUser.getEmail());
        return "redirect:appUsers.html";
    }
}
