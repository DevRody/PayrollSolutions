package com.project.payrollSolutions.service;

import com.project.payrollSolutions.dto.AddressRequestDTO;
import com.project.payrollSolutions.dto.EmployeeRequestDTO;
import com.project.payrollSolutions.model.Address;
import com.project.payrollSolutions.model.Employee;
import com.project.payrollSolutions.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @InjectMocks
    EmployeeService employeeService;

    @Mock
    EmployeeRepository employeeRepository;

    @Mock
    AddressService addressService;

    private Employee employee;
    private EmployeeRequestDTO employeeRequest;
    private List<Employee> employees;
    private Address address;

    @BeforeEach
    void setup() {
        address = new Address(1L, "Rua teste", "Sao Paulo", "13235678", "Zona Sul", "138", "Sao Paulo");
        employee = new Employee(1L, "Arthur", "teste@teste.com", "45464677798", "Developer", 2500D, "992785578", LocalDate.parse("1999-02-28"), LocalDate.parse("2023-02-28"), address);
        AddressRequestDTO addressRequestDTO = new AddressRequestDTO(1L, "Rua teste", "Sao Paulo", "13235678", "Zona Sul", "138", "Sao Paulo");
        employeeRequest = new EmployeeRequestDTO(1L, "Arthur", "teste@teste.com", "45464677798", "Developer", 2500D, "992785578", "1999-02-28", "2023-02-28", addressRequestDTO);
        employees = new ArrayList<Employee>();

        for (int i = 0; i < 10; i++) {
            employees.add(new Employee((long) i + 1, "Arthur", "teste@teste.com", "45464677798", "Developer", 2500D, "992785578", LocalDate.parse("2023-02-28"), LocalDate.parse("1999-02-28"), address));
        }
    }

    @Test
    @DisplayName("Should return employee by id")
    void findEmployeeById() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

        var employeeTest = employeeService.findEmployeeById(1L);

        assertEquals(employeeTest, employee);
        verify(employeeRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Should return employee list by filter")
    void search() {
        when(employeeRepository.search("art", PageRequest.of(0, 10, Sort.Direction.ASC, "name"))).thenReturn(pageOfEmployees());

        var result = employeeService.search("art");

        assertNotNull(result);
        assertEquals(1, result.getData().size());
    }


    @Test
    @DisplayName("Should return all employees")
    void findAllEmployees() {
        when(employeeRepository.findAll()).thenReturn(employees);

        var result = employeeService.findAllEmployees();

        assertEquals(result.size(), employees.size());
        verify(employeeRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Should create a new employee")
    void createEmployee() {
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);
        when(addressService.createAddress(any(Address.class))).thenReturn(address);

        var result = employeeService.createEmployee(employeeRequest);

        assertNotNull(result);
        assertEquals("Arthur", result.getName());
    }

    @Test
    @DisplayName("Should update an existing employee")
    void updateEmployee() {
        var employeeTest = employeeRequest.transformToEmployee();

        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employeeTest));
        when(employeeRepository.save(any())).thenReturn(employeeTest);

        employeeService.updateEmployee(employeeRequest);

        verify(employeeRepository, times(1)).save(any());
        verify(addressService, times(1)).updateAddress(any());
    }

    @Test
    @DisplayName("Should delete an existing employee")
    void deleteEmployee() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

        employeeService.deleteEmployee(1L);

        verify(employeeRepository, times(1)).delete(any(Employee.class));
        verify(addressService, times(1)).deleteAddress(any(Address.class));
    }

    private Page<Employee> pageOfEmployees() {
        return new PageImpl<>(List.of(employee));
    }
}