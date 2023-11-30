package com.project.payrollSolutions.exceptionhandler;

import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.Instant;
import java.util.*;

@ControllerAdvice
public class ControllerExceptionHandler {
    @ResponseBody
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionHandlerMessage> entityNotFound(NotFoundException e, HttpServletRequest request) {
        ExceptionHandlerMessage err = new ExceptionHandlerMessage();
        err.setTimestamp(Instant.now());
        err.setStatus(HttpStatus.NOT_FOUND.value());
        err.setError("Entity not found");
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

    @ResponseBody
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ExceptionHandlerMessage> jsonParseError(HttpMessageNotReadableException e, HttpServletRequest request) {
        ExceptionHandlerMessage err = new ExceptionHandlerMessage();
        err.setTimestamp(Instant.now());
        err.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
        err.setError("JSON parse error");
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(err);
    }

    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionHandlerMessage> argumentsNotValid(MethodArgumentNotValidException e, HttpServletRequest request) {
        BindingResult result = e.getBindingResult();

        Set<String> fieldErrorSet = new HashSet<>();
        List<FieldError> fieldErrorList = result.getFieldErrors();

        StringJoiner stringJoiner = new StringJoiner(", ");
        for (FieldError fieldError : fieldErrorList) {
            if (fieldErrorSet.add(fieldError.getField())) {
                stringJoiner.add(fieldError.getField());
            }
        }

        ExceptionHandlerMessage err = new ExceptionHandlerMessage();
        err.setTimestamp(Instant.now());
        err.setStatus(HttpStatus.BAD_REQUEST.value());
        err.setError("Arguments not valid");
        err.setMessage("The field(s) cannot be null: [" + stringJoiner + "]");
        err.setPath(request.getRequestURI());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ResponseBody
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ExceptionHandlerMessage> conflictConstraintValidation(DataIntegrityViolationException e, HttpServletRequest request) {
        ExceptionHandlerMessage err = new ExceptionHandlerMessage();
        err.setTimestamp(Instant.now());
        err.setStatus(HttpStatus.CONFLICT.value());
        err.setError("conflict");
        err.setMessage(e.getMostSpecificCause().getMessage());
        err.setPath(request.getRequestURI());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(err);
    }

    @ResponseBody
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ExceptionHandlerMessage> methodNotAllowed(HttpRequestMethodNotSupportedException e, HttpServletRequest request) {
        ExceptionHandlerMessage err = new ExceptionHandlerMessage();
        err.setTimestamp(Instant.now());
        err.setStatus(HttpStatus.METHOD_NOT_ALLOWED.value());
        err.setError("Method not allowed");
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());

        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(err);
    }

    @ResponseBody
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ExceptionHandlerMessage> unsupportedMediaType(MethodArgumentTypeMismatchException e, HttpServletRequest request) {
        ExceptionHandlerMessage err = new ExceptionHandlerMessage();
        err.setTimestamp(Instant.now());
        err.setStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value());
        err.setError("Unsupported media type");
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());

        return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body(err);
    }

    @ResponseBody
    @ExceptionHandler(JWTCreationException.class)
    public ResponseEntity<ExceptionHandlerMessage> jwtCreation(JWTCreationException e, HttpServletRequest request) {
        ExceptionHandlerMessage err = new ExceptionHandlerMessage();
        err.setTimestamp(Instant.now());
        err.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        err.setError("JWT error");
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err);
    }

    @ResponseBody
    @ExceptionHandler(JWTVerificationException.class)
    public ResponseEntity<ExceptionHandlerMessage> jwtValidation(JWTVerificationException e, HttpServletRequest request) {
        ExceptionHandlerMessage err = new ExceptionHandlerMessage();
        err.setTimestamp(Instant.now());
        err.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        err.setError("JWT error");
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err);
    }

    @ResponseBody
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ExceptionHandlerMessage> authenticationException(AuthenticationException e, HttpServletRequest request) {
        ExceptionHandlerMessage err = new ExceptionHandlerMessage();
        err.setTimestamp(Instant.now());
        err.setStatus(HttpStatus.UNAUTHORIZED.value());
        err.setError(e.getMessage());
        err.setMessage("Document or password are incorrect");
        err.setPath(request.getRequestURI());

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(err);
    }

    @ResponseBody
    @ExceptionHandler(SendEmailException.class)
    public ResponseEntity<ExceptionHandlerMessage> authenticationException(SendEmailException e, HttpServletRequest request) {
        ExceptionHandlerMessage err = new ExceptionHandlerMessage();
        err.setTimestamp(Instant.now());
        err.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        err.setError("An error occurred while sending the email to the user");
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err);
    }

    @ResponseBody
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ExceptionHandlerMessage> nullPointerException(NullPointerException e, HttpServletRequest request) {
        ExceptionHandlerMessage err = new ExceptionHandlerMessage();
        err.setTimestamp(Instant.now());
        err.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        err.setError("An error occurred ");
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err);
    }
}
