package com.project.payrollSolutions.repository;

import com.project.payrollSolutions.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query("SELECT e FROM employee e WHERE LOWER(e.name) LIKE %:search% OR e.document LIKE %:search%")
    public Page<Employee> search(@Param("search") String search, Pageable pageable);
}
