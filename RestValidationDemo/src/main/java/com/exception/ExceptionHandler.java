package com.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<GlobalExceptionResponse> handleClassContrainsException(MethodArgumentNotValidException ex){
        GlobalExceptionResponse response = new GlobalExceptionResponse();
        response.setErrorMessage(ex.getBindingResult().getFieldError().getDefaultMessage());
        response.setErrorCode(HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<GlobalExceptionResponse> handleParamContrainsException(ConstraintViolationException ex){
        GlobalExceptionResponse response = new GlobalExceptionResponse();
        response.setErrorMessage(ex.getMessage());
        response.setErrorCode(HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }
}
