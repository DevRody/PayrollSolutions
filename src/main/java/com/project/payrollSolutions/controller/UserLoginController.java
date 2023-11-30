package com.project.payrollSolutions.controller;

import com.project.payrollSolutions.dto.UserLoginRequestDTO;
import com.project.payrollSolutions.model.UserLogin;
import com.project.payrollSolutions.service.UserLoginService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
@Tag(name = "User")
public class UserLoginController {
    private final UserLoginService userLoginService;

    @Autowired
    public UserLoginController(UserLoginService userLoginService) {
        this.userLoginService = userLoginService;
    }

    @GetMapping
    @Operation(summary = "Retorna lista de usuários cadastrados")
    public ResponseEntity<List<UserLogin>> findAllUsers() {
        var users = userLoginService.findAllUsers();
        return ResponseEntity.ok(users);
    }

    @PostMapping
    @Operation(summary = "Cria um novo usuário e faz o disparo de e-mail automático para ele")
    public ResponseEntity createUserLogin(@RequestBody UserLoginRequestDTO user) {
        this.userLoginService.createUserLogin(user);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/sendEmail")
    @Operation(summary = "Faz o disparo de e-mail para o funcionário pelo Id")
    public ResponseEntity<Void> sendEmailToEmployee(Long employeeId) {
        userLoginService.sendEmailToEmployee(employeeId);

        return ResponseEntity.noContent().build();
    }

}
