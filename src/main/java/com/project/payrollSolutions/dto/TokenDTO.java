package com.project.payrollSolutions.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TokenDTO {
    @NotNull
    @NotBlank
    private String token;

    @NotNull
    @NotBlank
    private String refreshToken;
}
