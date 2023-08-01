package com.example.demo.exception;

public class RequestInvalidException extends RuntimeException{
    public RequestInvalidException(String msg) {
        super(msg);
    }
}
