package com.project.payrollSolutions.dto;

import com.project.payrollSolutions.model.PaymentInformation;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PaymentInformationRequestDTO {
    private Long id;

    @NotNull
    private Double grossSalary;

    @NotNull
    private Double additional;

    @NotNull
    private Double discounts;

    @NotNull
    private Double inssSalary;

    @NotNull
    private Double irrfSalary;

    @NotNull
    private Double netSalary;

    @NotNull
    @NotBlank
    private LocalDate paymentDate;

    public PaymentInformation transformToPaymentInformation() {
        return new PaymentInformation(this.id, this.grossSalary, this.additional, this.discounts, this.inssSalary, this.irrfSalary, this.netSalary, this.paymentDate);
    }
}
