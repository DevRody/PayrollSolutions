package com.project.payrollSolutions.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Table(name = "payment_information")
@Entity(name = "payment_information")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double grossSalary;

    private Double additional;

    private Double discounts;

    private Double inssSalary;

    private Double irrfSalary;

    private Double netSalary;

    private LocalDate paymentDate;

    public PaymentInformation(Double grossSalary, Double additional, Double discounts, Double inssSalary, Double irrfSalary, Double netSalary, LocalDate paymentDate) {
        this.grossSalary = grossSalary;
        this.additional = additional;
        this.discounts = discounts;
        this.inssSalary = inssSalary;
        this.irrfSalary = irrfSalary;
        this.netSalary = netSalary;
        this.paymentDate = paymentDate;
    }
}
