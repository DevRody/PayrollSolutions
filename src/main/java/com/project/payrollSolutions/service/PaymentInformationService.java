package com.project.payrollSolutions.service;

import com.project.payrollSolutions.dto.PaymentInformationRequestDTO;
import com.project.payrollSolutions.model.PaymentInformation;
import com.project.payrollSolutions.repository.PaymentInformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentInformationService {
    private final PaymentInformationRepository paymentInformationRepository;

    @Autowired
    public PaymentInformationService(PaymentInformationRepository paymentInformationRepository) {
        this.paymentInformationRepository = paymentInformationRepository;
    }

    public PaymentInformation createPaymentInformation(PaymentInformation paymentInformation) {
        paymentInformation = paymentInformationRepository.save(paymentInformation);

        return paymentInformation;
    }

    public void updatePaymentInformation(PaymentInformationRequestDTO paymentInformationRequestDTO) {
        Optional<PaymentInformation> paymentInformationOptional = paymentInformationRepository.findById(paymentInformationRequestDTO.getId());

        if (paymentInformationOptional.isPresent()) {
            PaymentInformation paymentInformation = paymentInformationRequestDTO.transformToPaymentInformation();

            paymentInformationRepository.save(paymentInformation);
        } else {
            throw new RuntimeException("PaymentInformation by id " + paymentInformationRequestDTO.getId() + " was not found");
        }
    }

    public void deletePaymentInformation(Long id) {
        Optional<PaymentInformation> paymentInformationOptional = paymentInformationRepository.findById(id);

        if (paymentInformationOptional.isPresent()) {
            PaymentInformation paymentInformation = paymentInformationOptional.get();

            paymentInformationRepository.delete(paymentInformation);
        } else {
            throw new RuntimeException("PaymentInformation by id " + id + " was not found");
        }
    }
}
