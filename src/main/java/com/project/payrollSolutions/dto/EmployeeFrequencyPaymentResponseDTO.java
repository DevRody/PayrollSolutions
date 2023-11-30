package com.project.payrollSolutions.dto;

import com.project.payrollSolutions.model.FrequencyControl;
import com.project.payrollSolutions.model.PaymentInformation;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeFrequencyPaymentResponseDTO {
    private Long employeeId;

    private String monthYear;

    private FrequencyControl frequencyControl;

    private PaymentInformation paymentInformation;

    public EmployeeFrequencyPaymentResponseDTO(Long employeeId, String monthYear, FrequencyControl frequencyControl, PaymentInformation paymentInformation) {
        this.employeeId = employeeId;
        this.monthYear = monthYear;
        this.frequencyControl = frequencyControl;
        this.paymentInformation = paymentInformation;
    }
}
