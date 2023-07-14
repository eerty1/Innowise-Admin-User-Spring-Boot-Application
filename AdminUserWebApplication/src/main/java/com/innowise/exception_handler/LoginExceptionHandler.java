package com.innowise.exception_handler;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@RestControllerAdvice
public class LoginExceptionHandler {
    @Value("${response-messages.failure.invalid-credentials}")
    private String invalidUserCredentialsMessage;

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<String> handleBadCredentialsException() {
        return new ResponseEntity<>(invalidUserCredentialsMessage, UNAUTHORIZED);
    }
}
