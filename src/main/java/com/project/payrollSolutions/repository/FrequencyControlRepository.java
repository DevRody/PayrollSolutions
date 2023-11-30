package com.project.payrollSolutions.repository;

import com.project.payrollSolutions.model.FrequencyControl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FrequencyControlRepository extends JpaRepository<FrequencyControl, Long> {
}
