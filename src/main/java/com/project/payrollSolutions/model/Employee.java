package com.project.payrollSolutions.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Table(name = "employee")
@Entity(name = "employee")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String document;

    private String jobTitle;

    private Double baseSalary;

    private String phone;

    private LocalDate birthDate;

    private LocalDate admissionDate;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;


}
