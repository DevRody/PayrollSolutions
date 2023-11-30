package com.project.payrollSolutions.dto;

import com.project.payrollSolutions.model.EmployeeFrequencyPayment;
import com.project.payrollSolutions.model.FrequencyControl;
import com.project.payrollSolutions.model.PaymentInformation;
import com.project.payrollSolutions.model.id.EmployeeFrequencyPaymentId;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class EmployeeFrequencyPaymentRequestDTO {
    @NotNull
    private Long employeeId;

    @NotNull
    @NotBlank
    private String monthYear;

    @NotNull
    private FrequencyControlRequestDTO frequencyControl;

    @NotNull
    private PaymentInformationRequestDTO paymentInformation;

    public EmployeeFrequencyPayment transformToEmployeeFrequencyPayment() {
        FrequencyControl newFrequencyControl = this.frequencyControl.transformToFrequencyControl();
        PaymentInformation newPaymentInformation = this.paymentInformation.transformToPaymentInformation();
        EmployeeFrequencyPaymentId newEmployeeFrequencyPaymentId = new EmployeeFrequencyPaymentId(this.employeeId, this.monthYear);

        return new EmployeeFrequencyPayment(newEmployeeFrequencyPaymentId, newFrequencyControl, newPaymentInformation);
    }
}
