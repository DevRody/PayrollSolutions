package com.project.payrollSolutions.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeFilterDTO {
    private String search;
    @NotNull
    private Integer perPage;
    @NotNull
    private Integer page;
}
