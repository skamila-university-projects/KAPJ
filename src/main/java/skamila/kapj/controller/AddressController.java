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
import skamila.kapj.domain.Address;
import skamila.kapj.service.AddressService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class AddressController {

    private AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @RequestMapping(value = "/addresses")
    public String showAddresses(Model model, HttpServletRequest request) {
        int addressId = ServletRequestUtils.getIntParameter(request, "addressId", -1);
        if (addressId > 0) {
            model.addAttribute("address", addressService.getAddress(addressId));
        } else {
            model.addAttribute("address", new Address());
        }
        model.addAttribute("addressList", addressService.listAddress());
        return "address";
    }

    @RequestMapping("/deleteAddress/{addressId}")
    public String deleteAddress(@PathVariable("addressId") Long address) {
        addressService.removeAddress(address);
        return "redirect:/addresses.html";
    }

    @RequestMapping(value = "/addAddress", method = RequestMethod.POST)
    public String addAddress(@Valid @ModelAttribute("address") Address address, BindingResult result) {
        if (result.getErrorCount() == 0) {
            if (address.getId() == 0) {
                addressService.addAddress(address);
            } else {
                addressService.editAddress(address);
            }
            return "redirect:addresses.html";
        }
        return "address";
    }
}
