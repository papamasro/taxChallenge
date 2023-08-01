package com.example.demo.model.services.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class UserRequest {

    @JsonProperty
    @Valid
    @NotNull(message = "user must not be null")
    private String user;

    @JsonProperty
    @Valid
    @NotNull(message = "password must not be null")
    private String password;

    public UserRequest(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
