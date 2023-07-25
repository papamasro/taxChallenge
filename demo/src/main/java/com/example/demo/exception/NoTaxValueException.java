package com.example.demo.exception;

public class NoTaxValueException extends RuntimeException{
    public NoTaxValueException(String msg) {
        super(msg);
    }
}
