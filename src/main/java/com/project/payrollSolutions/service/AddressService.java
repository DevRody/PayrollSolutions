package com.project.payrollSolutions.service;

import com.project.payrollSolutions.exceptionhandler.NotFoundException;
import com.project.payrollSolutions.model.Address;
import com.project.payrollSolutions.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {
    private final AddressRepository addressRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public void findAddressById(Long id) {
        addressRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Address " + id + " was not found"));
    }

    public Address createAddress(Address address) {
        return addressRepository.save(address);
    }

    public void updateAddress(Address address) {
        findAddressById(address.getId());

        addressRepository.save(address);
    }

    public void deleteAddress(Address address) {
        findAddressById(address.getId());

        addressRepository.delete(address);
    }
}
