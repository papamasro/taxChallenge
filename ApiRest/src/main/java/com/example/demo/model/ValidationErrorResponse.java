package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

public class ValidationErrorResponse {

      String message;


    public ValidationErrorResponse(String message) {
        this.message = message;
    }


    public String getMessage() {
        return message;
    }


    public void setMessage(String message) {
        this.message = message;
    }
}