package com.project.payrollSolutions.controller;

import com.project.payrollSolutions.dto.EmployeeFrequencyPaymentRequestDTO;
import com.project.payrollSolutions.dto.EmployeeFrequencyPaymentResponseDTO;
import com.project.payrollSolutions.model.EmployeeFrequencyPayment;
import com.project.payrollSolutions.model.id.EmployeeFrequencyPaymentId;
import com.project.payrollSolutions.service.EmployeeFrequencyPaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employeeFrequencyPayment")
@Tag(name = "EmployeeFrequencyPayment")
public class EmployeeFrequencyPaymentController {
    private final EmployeeFrequencyPaymentService employeeFrequencyPaymentService;

    @Autowired
    public EmployeeFrequencyPaymentController(EmployeeFrequencyPaymentService employeeFrequencyPaymentService) {
        this.employeeFrequencyPaymentService = employeeFrequencyPaymentService;
    }

    @GetMapping
    @Operation(summary = "Retorna a folha de pagamento e a frequencia mensal de um funcionário de acordo com o Id e o mês desejado")
    public ResponseEntity<EmployeeFrequencyPaymentResponseDTO> findEmployeeFrequencyPaymentById(@RequestParam @Valid Long employeeId, @RequestParam @Valid String monthYear) {
        EmployeeFrequencyPaymentId employeeFrequencyPaymentId = new EmployeeFrequencyPaymentId(employeeId, monthYear);
        var employeeFrequencyPayment = employeeFrequencyPaymentService.findEmployeeFrequencyPaymentById(employeeFrequencyPaymentId);
        return ResponseEntity.ok(employeeFrequencyPayment);
    }

    @PostMapping
    @Operation(summary = "Cria uma nova folha de pagamento e uma nova frequencia mensal para o funcionário")
    public ResponseEntity<EmployeeFrequencyPayment> createEmployeeFrequencyPayment(@RequestBody @Valid EmployeeFrequencyPaymentRequestDTO employeeFrequencyPaymentRequestDTO) {
        var employeeFrequencyPayment = employeeFrequencyPaymentService.createEmployeeFrequencyPayment(employeeFrequencyPaymentRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeFrequencyPayment);
    }


    @PutMapping
    @Operation(summary = "Edita a folha de pagamento e a frequencia mensal do funcionário existente")
    public ResponseEntity<Void> updateEmployeeFrequencyPayment(@RequestBody @Valid EmployeeFrequencyPaymentRequestDTO employeeFrequencyPaymentRequestDTO) {
        employeeFrequencyPaymentService.updateEmployeeFrequencyPayment(employeeFrequencyPaymentRequestDTO);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    @Operation(summary = "Exclui a folha de pagamento e a frequencia mensal de um funcionário de acordo com o Id e o mês desejado")
    public ResponseEntity<Void> deleteEmployeeFrequencyPayment(@RequestParam @Valid Long employeeId, @RequestParam @Valid String monthYear) {
        EmployeeFrequencyPaymentId employeeFrequencyPaymentId = new EmployeeFrequencyPaymentId(employeeId, monthYear);
        employeeFrequencyPaymentService.deleteEmployeeFrequencyPayment(employeeFrequencyPaymentId);
        return ResponseEntity.noContent().build();
    }
}
