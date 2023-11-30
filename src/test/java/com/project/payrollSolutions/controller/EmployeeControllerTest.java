package com.project.payrollSolutions.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.payrollSolutions.dto.AddressRequestDTO;
import com.project.payrollSolutions.dto.EmployeeFilterDTO;
import com.project.payrollSolutions.dto.EmployeeRequestDTO;
import com.project.payrollSolutions.dto.SearchFilterDTO;
import com.project.payrollSolutions.model.Address;
import com.project.payrollSolutions.model.Employee;
import com.project.payrollSolutions.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@ExtendWith(MockitoExtension.class)
class EmployeeControllerTest {

    @InjectMocks
    private EmployeeController employeeController;

    @Mock
    private EmployeeService employeeService;

    @Autowired
    public MockMvc mockMvc;

    private Employee employee;

    private EmployeeRequestDTO employeeRequest;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(employeeController)
                .alwaysDo(print())
                .build();

        Address address = new Address(1L, "Rua teste", "Sao Paulo", "13235678", "Zona Sul", "138", "Sao Paulo");
        employee = new Employee(1L, "Arthur", "teste@teste.com", "45464677798", "Developer", 2500D, "992785578", LocalDate.parse("2023-02-28"), LocalDate.parse("1999-02-28"), address);

        AddressRequestDTO addressRequestDTO = new AddressRequestDTO(null, "Rua teste", "Sao Paulo", "13235678", "Zona Sul", "138", "Sao Paulo");
        employeeRequest = new EmployeeRequestDTO(null, "Arthur", "teste@teste.com", "45464677798", "Developer", 2500D, "992785578", "1999-02-28", "2023-02-28", addressRequestDTO);
    }

    @Test
    @DisplayName("Should return employee by id")
    void findEmployeeById() throws Exception {
        when(employeeService.findEmployeeById(1L)).thenReturn(employee);

        mockMvc.perform(MockMvcRequestBuilders.get("/employee/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("id", employee.getId().toString()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        verify(employeeService, times(1)).findEmployeeById(1L);
    }

    @Test
    @DisplayName("Should return employee list by filter")
    void findEmployeeByFilter() throws Exception {
        var objectMapper = new ObjectMapper();

        var searchFilter = new SearchFilterDTO<Employee>(Collections.singletonList(employee), 1L, 1);
        var employeeFilter = new EmployeeFilterDTO("Art", 10, 0);

        when(employeeService.search("Art", 0, 10)).thenReturn(searchFilter);

        mockMvc.perform(MockMvcRequestBuilders.post("/employee/filter")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employeeFilter))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        verify(employeeService, times(1)).search("Art", 0, 10);
    }

    @Test
    @DisplayName("Should return all employees")
    void findAllEmployee() throws Exception {
        when(employeeService.findAllEmployees()).thenReturn(Collections.singletonList(employee));

        mockMvc.perform(MockMvcRequestBuilders.get("/employee")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        verify(employeeService, times(1)).findAllEmployees();
    }

    @Test
    @DisplayName("Should create a new employee")
    void createEmployee() throws Exception {
        var objectMapper = new ObjectMapper();

        when(employeeService.createEmployee(any())).thenReturn(employeeRequest.transformToEmployee());

        mockMvc.perform(MockMvcRequestBuilders.post("/employee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employeeRequest))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Arthur"))
                .andReturn();

        verify(employeeService, times(1)).createEmployee(any());
    }

    @Test
    @DisplayName("Should update an existing employee")
    void updateEmployee() throws Exception {
        var objectMapper = new ObjectMapper();

        employeeRequest.setId(1L);
        employeeRequest.getAddress().setId(1L);

        mockMvc.perform(MockMvcRequestBuilders.put("/employee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employeeRequest))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andReturn();

        verify(employeeService, times(1)).updateEmployee(any());
    }

    @Test
    @DisplayName("Should delete an existing employee")
    void deleteEmployee() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/employee/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("id", employee.getId().toString()))
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andReturn();

        verify(employeeService, times(1)).deleteEmployee(1L);
    }
}