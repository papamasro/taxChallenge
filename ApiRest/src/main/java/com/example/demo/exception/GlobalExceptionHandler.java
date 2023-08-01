package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<String>
    handleValidationExceptions(MethodArgumentNotValidException ex) {
        String error = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage(); //TODO: IMPROVE TO SHOW ALL ERRORS, AND RETURN OBJECT WITH MORE INFORMATION
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingRequestHeaderException.class)
    @ResponseBody
    public ResponseEntity<String>
    handleValidationExceptions(MissingRequestHeaderException ex) {
        String error = ex.getMessage(); //TODO: IMPROVE TO SHOW ALL ERRORS, AND RETURN OBJECT WITH MORE INFORMATION
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseBody
    public ResponseEntity<String>
    handleValidationExceptions(UsernameNotFoundException ex) {
        String error = ex.getMessage(); //TODO: IMPROVE TO SHOW ALL ERRORS, AND RETURN OBJECT WITH MORE INFORMATION
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UsernameAlreadyExistException.class)
    @ResponseBody
    public ResponseEntity<String>
    handleValidationExceptions(UsernameAlreadyExistException ex) {
        String error = ex.getMessage(); //TODO: IMPROVE TO SHOW ALL ERRORS, AND RETURN OBJECT WITH MORE INFORMATION
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(TooManyRequestException.class)
    @ResponseBody
    public ResponseEntity<String>
    handleValidationExceptions(TooManyRequestException ex) {
        String error = ex.getMessage(); //TODO: IMPROVE TO SHOW ALL ERRORS, AND RETURN OBJECT WITH MORE INFORMATION
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}