package com.project.payrollSolutions.model;

import com.project.payrollSolutions.model.id.EmployeeFrequencyPaymentId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "employee_frequency_payment")
@Entity(name = "employee_frequency_payment")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeFrequencyPayment {

    @EmbeddedId
    private EmployeeFrequencyPaymentId id;

    @ManyToOne
    @JoinColumn(name = "frequency_id")
    private FrequencyControl frequencyControl;

    @ManyToOne
    @JoinColumn(name = "payment_id")
    private PaymentInformation paymentInformation;
}
