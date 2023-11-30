package com.project.payrollSolutions.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.payrollSolutions.model.Address;
import com.project.payrollSolutions.model.Employee;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRequestDTO {
    private Long id;

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    private String email;

    @NotNull
    @NotBlank
    private String document;

    @NotNull
    @NotBlank
    private String jobTitle;

    @NotNull
    private Double baseSalary;

    @NotNull
    @NotBlank
    private String phone;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonProperty("birthDate")
    private String birthDate;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonProperty("admissionDate")
    private String admissionDate;

    @NotNull
    private AddressRequestDTO address;

    public Employee transformToEmployee() {
        Address address = this.address.transformToAddress();
        return new Employee(this.id, this.name, this.email, this.document, this.jobTitle, this.baseSalary, this.phone, LocalDate.parse(this.birthDate), LocalDate.parse(this.admissionDate), address);
    }
}