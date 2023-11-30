package com.project.payrollSolutions.controller;

import com.project.payrollSolutions.dto.EmployeeFilterDTO;
import com.project.payrollSolutions.dto.EmployeeRequestDTO;
import com.project.payrollSolutions.dto.SearchFilterDTO;
import com.project.payrollSolutions.model.Employee;
import com.project.payrollSolutions.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
@Tag(name = "Employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retorna o funcionário pelo Id")
    public ResponseEntity<Employee> findEmployeeById(@PathVariable Long id) {
        var employee = employeeService.findEmployeeById(id);
        return ResponseEntity.ok(employee);
    }

    @PostMapping("/filter")
    @Operation(summary = "Retorna lista de funcionários filtrados")
    public ResponseEntity<SearchFilterDTO<Employee>> findEmployeeByFilter(@RequestBody @Valid EmployeeFilterDTO employeeFilterDTO) {
        var data = employeeService.search(employeeFilterDTO.getSearch(), employeeFilterDTO.getPage(), employeeFilterDTO.getPerPage());
        return ResponseEntity.ok(data);
    }

    @GetMapping
    @Operation(summary = "Retorna lista de todos os funcionários")
    public ResponseEntity<List<Employee>> findAllEmployees() {
        var employees = employeeService.findAllEmployees();
        return ResponseEntity.ok(employees);
    }

    @PostMapping
    @Operation(summary = "Cria um novo funcionário")
    public ResponseEntity<Employee> createEmployee(@RequestBody @Valid EmployeeRequestDTO employeeRequestDTO) {
        var employee = employeeService.createEmployee(employeeRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(employee);
    }

    @PutMapping
    @Operation(summary = "Edita um funcionário existente")
    public ResponseEntity<Void> updateEmployee(@RequestBody @Valid EmployeeRequestDTO employee) {
        employeeService.updateEmployee(employee);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Exclui um funcionário pelo Id")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }
}
