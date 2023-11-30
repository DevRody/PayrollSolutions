package com.project.payrollSolutions.dto;

import com.project.payrollSolutions.model.UserLogin;
import com.project.payrollSolutions.security.UserRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginRequestDTO {
    @NotNull
    @NotBlank
    private String document;

    @NotNull
    @NotBlank
    private UserRole role;

    @Null
    private String password;

    @Null
    private Long employeeId;

    public UserLogin transformToUserLogin() {
        return new UserLogin(this.document, this.role, this.employeeId);
    }
}
