package skamila.kapj.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import skamila.kapj.dao.AddressRepository;
import skamila.kapj.domain.Address;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    private AddressRepository addressRepository;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Transactional
    @Override
    public void addAddress(Address addressService) {
        addressRepository.save(addressService);
    }

    @Transactional
    @Override
    public void editAddress(Address addressService) {
        addressRepository.save(addressService);
    }

    @Transactional
    @Override
    public List<Address> listAddress() {
        return addressRepository.findAll();
    }

    @Transactional
    @Override
    public void removeAddress(long id) {
        addressRepository.delete(id);
    }

    @Transactional
    @Override
    public Address getAddress(long id) {
        return addressRepository.findById(id);
    }
}
