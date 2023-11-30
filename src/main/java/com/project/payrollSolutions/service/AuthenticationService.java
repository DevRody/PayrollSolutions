package com.project.payrollSolutions.service;

import com.project.payrollSolutions.dto.AuthenticationRequestDTO;
import com.project.payrollSolutions.dto.TokenDTO;
import com.project.payrollSolutions.model.UserLogin;
import com.project.payrollSolutions.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;
    private final UserLoginService userLoginService;

    @Autowired
    public AuthenticationService(TokenService tokenService, AuthenticationManager authenticationManager, UserLoginService userLoginService) {
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
        this.userLoginService = userLoginService;
    }

    public TokenDTO generateCredentials(AuthenticationRequestDTO data) {
        var userDocumentPassword = new UsernamePasswordAuthenticationToken(data.getDocument(), data.getPassword());

        try {
            var auth = this.authenticationManager.authenticate(userDocumentPassword);
            var token = this.tokenService.generateToken((UserLogin) auth.getPrincipal());
            var refreshToken = this.tokenService.generateRefreshToken((UserLogin) auth.getPrincipal());

            return new TokenDTO(token, refreshToken);

        } catch (AuthenticationException exception) {
            throw new com.project.payrollSolutions.exceptionhandler.AuthenticationException("Authentication error", exception);
        }
    }

    public TokenDTO verifyAndGenerateCredentials(TokenDTO tokenDTO) {
        try {
            var document = this.tokenService.validateRefreshToken(tokenDTO.getRefreshToken());

            var userDetails = this.userLoginService.findUserDetails(document);

            var userDocumentPassword = new UsernamePasswordAuthenticationToken(document, userDetails.getPassword());

            var auth = this.authenticationManager.authenticate(userDocumentPassword);

            var token = this.tokenService.generateToken((UserLogin) auth.getPrincipal());

            tokenDTO.setToken(token);

            return tokenDTO;

        } catch (AuthenticationException exception) {
            throw new com.project.payrollSolutions.exceptionhandler.AuthenticationException("Authentication error", exception);
        }
    }
}
