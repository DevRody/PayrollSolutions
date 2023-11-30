package com.project.payrollSolutions.repository;

import com.project.payrollSolutions.model.UserLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UserLoginRepository extends JpaRepository<UserLogin, Long> {
    UserDetails findByDocument(String document);

    UserLogin findUserByEmployeeId(Long id);
}
