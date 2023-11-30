package com.project.payrollSolutions.service;

import com.project.payrollSolutions.dto.EmployeeFrequencyPaymentRequestDTO;
import com.project.payrollSolutions.dto.EmployeeFrequencyPaymentResponseDTO;
import com.project.payrollSolutions.model.EmployeeFrequencyPayment;
import com.project.payrollSolutions.model.FrequencyControl;
import com.project.payrollSolutions.model.PaymentInformation;
import com.project.payrollSolutions.model.id.EmployeeFrequencyPaymentId;
import com.project.payrollSolutions.repository.EmployeeFrequencyPaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class EmployeeFrequencyPaymentService {

    private final EmployeeFrequencyPaymentRepository employeeFrequencyPaymentRepository;
    private final FrequencyControlService frequencyControlService;
    private final PaymentInformationService paymentInformationService;

    @Autowired
    public EmployeeFrequencyPaymentService(EmployeeFrequencyPaymentRepository employeeFrequencyPaymentRepository, FrequencyControlService frequencyControlService, PaymentInformationService paymentInformationService) {
        this.employeeFrequencyPaymentRepository = employeeFrequencyPaymentRepository;
        this.frequencyControlService = frequencyControlService;
        this.paymentInformationService = paymentInformationService;
    }

    public EmployeeFrequencyPaymentResponseDTO findEmployeeFrequencyPaymentById(EmployeeFrequencyPaymentId employeeFrequencyPaymentId) {
        var employeeFrequencyPayment = employeeFrequencyPaymentRepository.findById(employeeFrequencyPaymentId).orElseThrow(() -> new RuntimeException("EmployeeFrequencyPayment by employee id " + employeeFrequencyPaymentId.getEmployeeId() + "and monthYear" + employeeFrequencyPaymentId.getMonthYear() + " was not found"));
        return new EmployeeFrequencyPaymentResponseDTO(employeeFrequencyPayment.getId().getEmployeeId(), employeeFrequencyPayment.getId().getMonthYear(), employeeFrequencyPayment.getFrequencyControl(), employeeFrequencyPayment.getPaymentInformation());
    }

    @Transactional
    public EmployeeFrequencyPayment createEmployeeFrequencyPayment(EmployeeFrequencyPaymentRequestDTO employeeFrequencyPaymentRequestDTO) {
        EmployeeFrequencyPayment employeeFrequencyPayment = employeeFrequencyPaymentRequestDTO.transformToEmployeeFrequencyPayment();

        FrequencyControl frequencyControl = employeeFrequencyPayment.getFrequencyControl();
        frequencyControl = frequencyControlService.createFrequencyControl(frequencyControl);
        employeeFrequencyPayment.setFrequencyControl(frequencyControl);

        PaymentInformation paymentInformation = employeeFrequencyPayment.getPaymentInformation();
        paymentInformation = paymentInformationService.createPaymentInformation(paymentInformation);
        employeeFrequencyPayment.setPaymentInformation(paymentInformation);

        employeeFrequencyPayment = employeeFrequencyPaymentRepository.save(employeeFrequencyPayment);
        return employeeFrequencyPayment;
    }

    @Transactional
    public void updateEmployeeFrequencyPayment(EmployeeFrequencyPaymentRequestDTO employeeFrequencyPaymentRequestDTO) {
        EmployeeFrequencyPayment employeeFrequencyPayment = employeeFrequencyPaymentRequestDTO.transformToEmployeeFrequencyPayment();

        Optional<EmployeeFrequencyPayment> optionalEmployeeFrequencyPayment = employeeFrequencyPaymentRepository.findById(employeeFrequencyPayment.getId());

        if (optionalEmployeeFrequencyPayment.isPresent()) {
            frequencyControlService.updateFrequencyControl(employeeFrequencyPaymentRequestDTO.getFrequencyControl());

            paymentInformationService.updatePaymentInformation(employeeFrequencyPaymentRequestDTO.getPaymentInformation());
        } else {
            throw new RuntimeException("EmployeeFrequencyPayment by employee id " + employeeFrequencyPayment.getId().getEmployeeId() + "and monthYear" + employeeFrequencyPayment.getId().getMonthYear() + " was not found");
        }
    }

    @Transactional
    public void deleteEmployeeFrequencyPayment(EmployeeFrequencyPaymentId employeeFrequencyPaymentId) {
        Optional<EmployeeFrequencyPayment> optionalEmployeeFrequencyPayment = employeeFrequencyPaymentRepository.findById(employeeFrequencyPaymentId);

        if (optionalEmployeeFrequencyPayment.isPresent()) {
            EmployeeFrequencyPayment employeeFrequencyPayment = optionalEmployeeFrequencyPayment.get();

            FrequencyControl frequencyControl = employeeFrequencyPayment.getFrequencyControl();
            frequencyControlService.deleteFrequencyControl(frequencyControl.getId());

            PaymentInformation paymentInformation = employeeFrequencyPayment.getPaymentInformation();
            paymentInformationService.deletePaymentInformation(paymentInformation.getId());

            employeeFrequencyPaymentRepository.delete(employeeFrequencyPayment);
        } else {
            throw new RuntimeException("EmployeeFrequencyPayment by employee id " + employeeFrequencyPaymentId.getEmployeeId() + "and monthYear" + employeeFrequencyPaymentId.getMonthYear() + " was not found");
        }
    }
}
