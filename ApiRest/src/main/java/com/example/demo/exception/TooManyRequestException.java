package com.example.demo.exception;

public class TooManyRequestException extends RuntimeException{

    public TooManyRequestException(String msg) {
        super(msg);
    }

}
