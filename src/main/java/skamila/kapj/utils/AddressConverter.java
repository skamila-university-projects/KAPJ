package skamila.kapj.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import skamila.kapj.domain.Address;
import skamila.kapj.service.AddressService;

public class AddressConverter implements Converter<String, Address> {

    private AddressService addressService;

    @Autowired
    public AddressConverter(AddressService addressService) {
        this.addressService = addressService;
    }

    @Override
    public Address convert(String addressId) {
        return addressService.getAddress(Integer.parseInt(addressId));
    }

}
