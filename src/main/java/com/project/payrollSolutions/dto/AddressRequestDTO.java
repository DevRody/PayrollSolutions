package com.project.payrollSolutions.dto;

import com.project.payrollSolutions.model.Address;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressRequestDTO {
    private Long id;

    @NotNull
    @NotBlank
    private String streetAddress;

    @NotNull
    @NotBlank
    private String city;

    @NotNull
    @NotBlank
    private String postalCode;

    @NotNull
    @NotBlank
    private String neighborhood;

    @NotNull
    @NotBlank
    private String houseNumber;

    @NotNull
    @NotBlank
    private String state;

    public Address transformToAddress() {
        return new Address(this.id, this.streetAddress, this.city, this.postalCode, this.neighborhood, this.houseNumber, this.state);
    }
}