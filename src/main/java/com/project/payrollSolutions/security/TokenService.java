package com.project.payrollSolutions.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.project.payrollSolutions.model.UserLogin;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class TokenService {
    @Value("${api.security.token.secret}")
    public String secret;

    @Value("${api.security.token.expiration}")
    private long tokenExpiration;

    @Value("${api.security.refreshToken.expiration}")
    private long refreshTokenExpiration;

    public String generateToken(UserLogin user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            var employeeId = user.getEmployeeId() != null ? user.getEmployeeId().toString() : "";
            return JWT.create().withIssuer("payroll").withSubject(user.getDocument()).withClaim("role", user.getRole().toString()).withClaim("employeeId", employeeId).withExpiresAt(Instant.ofEpochMilli(generateExpirationDate(tokenExpiration))).sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new JWTCreationException("Error while generating token", exception);
        }
    }

    public String generateRefreshToken(UserLogin user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            return JWT.create().withIssuer("payroll").withSubject(user.getDocument()).withExpiresAt(Instant.ofEpochMilli(generateExpirationDate(refreshTokenExpiration))).sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new JWTCreationException("Error while generating refresh token", exception);
        }
    }

    private Long generateExpirationDate(Long expiration) {
        return System.currentTimeMillis() + expiration;
    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            return JWT.require(algorithm).withIssuer("payroll").build().verify(token).getSubject();
        } catch (JWTVerificationException exception) {
            throw new JWTVerificationException("Error while validating token", exception);
        }
    }

    public String validateRefreshToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            return JWT.require(algorithm).withIssuer("payroll").build().verify(token).getSubject();
        } catch (JWTVerificationException exception) {
            throw new JWTVerificationException("Error while validating token", exception);
        }
    }
}
