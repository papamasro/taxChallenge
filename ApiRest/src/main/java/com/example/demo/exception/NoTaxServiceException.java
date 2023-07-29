package com.example.demo.exception;


public class NoTaxServiceException extends RuntimeException{
    public NoTaxServiceException(String msg) {
        super(msg);
    }
}
