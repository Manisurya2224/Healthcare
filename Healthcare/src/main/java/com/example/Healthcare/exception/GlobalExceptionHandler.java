package com.example.Healthcare.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ApiError> handleEmailExists(EmailAlreadyExistsException ex, HttpServletRequest request){
        ApiError error=new ApiError(LocalDateTime.now(), HttpStatus.CONFLICT.value(),"Conflict","USER_EMAIL_ALREADY_EXISTS",ex.getMessage(),request.getRequestURI());
        return new ResponseEntity<>(error,HttpStatus.CONFLICT);
}
}
