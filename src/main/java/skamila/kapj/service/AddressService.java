package skamila.kapj.service;

import skamila.kapj.domain.Address;

import java.util.List;

public interface AddressService {

    void addAddress(Address user);

    void editAddress(Address user);

    List<Address> listAddress();

    void removeAddress(long id);

    Address getAddress(long id);

}
