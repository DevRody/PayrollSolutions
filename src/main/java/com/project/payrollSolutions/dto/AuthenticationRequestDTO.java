package com.project.payrollSolutions.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class AuthenticationRequestDTO {
    @NotNull
    @NotBlank
    private String document;

    @NotNull
    @NotBlank
    private String password;
}
