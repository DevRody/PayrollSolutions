package com.project.payrollSolutions.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "address")
@Entity(name = "address")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String streetAddress;

    private String city;

    private String postalCode;

    private String neighborhood;

    private String houseNumber;

    private String state;

    public Address(String streetAddress, String city, String postalCode, String neighborhood, String houseNumber, String state) {
        this.streetAddress = streetAddress;
        this.city = city;
        this.postalCode = postalCode;
        this.neighborhood = neighborhood;
        this.houseNumber = houseNumber;
        this.state = state;
    }
}
