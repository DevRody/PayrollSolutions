package com.project.payrollSolutions.service;

import com.project.payrollSolutions.exceptionhandler.NotFoundException;
import com.project.payrollSolutions.model.Address;
import com.project.payrollSolutions.repository.AddressRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AddressServiceTest {

    @InjectMocks
    AddressService service;

    @Mock
    AddressRepository repository;

    Address address;

    @BeforeEach
    void setup() {
        address = new Address(1L, "Rua teste", "Sao Paulo", "13235678", "Zona Sul", "138", "Sao Paulo");
    }

    @Test
    @DisplayName("Should return address")
    void findAddressById() {
        when(repository.findById(address.getId())).thenReturn(Optional.of(address));

        service.findAddressById(address.getId());
    }

    @Test
    @DisplayName("Should return NotFoundException when no address is found")
    void findExceptionAddressById() {
        final NotFoundException e = Assertions.assertThrows(NotFoundException.class, () -> {
            service.findAddressById(2L);
        });

        assertEquals(e.getMessage(), "Address 2 was not found");
    }

    @Test
    @DisplayName("Should create a address")
    void createAddress() {
        when(repository.save(address)).thenReturn(address);

        Address result = service.createAddress(address);

        verify(repository, times(1)).save(any());

        assertEquals(address, result);
    }

    @Test
    @DisplayName("Should update an existing address")
    void updateAddress() {
        when(repository.findById(address.getId())).thenReturn(Optional.of(address));
        when(repository.save(address)).thenReturn(address);

        service.updateAddress(address);

        verify(repository, times(1)).save(address);
        verify(repository, times(1)).findById(address.getId());
        verifyNoMoreInteractions(repository);
    }

    @Test
    @DisplayName("Should delete an existing address")
    void deleteAddress() {
        when(repository.findById(address.getId())).thenReturn(Optional.of(address));

        service.deleteAddress(address);

        verify(repository, times(1)).delete(address);
        verify(repository, times(1)).findById(address.getId());
        verifyNoMoreInteractions(repository);
    }
}