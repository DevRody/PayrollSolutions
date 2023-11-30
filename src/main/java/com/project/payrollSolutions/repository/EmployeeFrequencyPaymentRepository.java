package com.project.payrollSolutions.repository;

import com.project.payrollSolutions.model.EmployeeFrequencyPayment;
import com.project.payrollSolutions.model.id.EmployeeFrequencyPaymentId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeFrequencyPaymentRepository extends JpaRepository<EmployeeFrequencyPayment, EmployeeFrequencyPaymentId> {
}
