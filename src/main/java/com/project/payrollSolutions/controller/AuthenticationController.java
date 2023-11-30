package com.project.payrollSolutions.controller;

import com.project.payrollSolutions.dto.AuthenticationRequestDTO;
import com.project.payrollSolutions.dto.TokenDTO;
import com.project.payrollSolutions.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    @Operation(summary = "Faz o login na aplicação")
    public ResponseEntity<TokenDTO> login(@RequestBody @Valid AuthenticationRequestDTO data) {
        TokenDTO credentials = authenticationService.generateCredentials(data);

        return ResponseEntity.ok(credentials);
    }

    @PostMapping("/refresh")
    @Operation(summary = "Faz a renovaçao do token expirado")
    public ResponseEntity<TokenDTO> login(@RequestBody @Valid TokenDTO data) {
        TokenDTO credentials = authenticationService.verifyAndGenerateCredentials(data);

        return ResponseEntity.ok(credentials);
    }
}
