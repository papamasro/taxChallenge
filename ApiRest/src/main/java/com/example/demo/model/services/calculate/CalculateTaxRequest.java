package com.example.demo.model.services.calculate;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;


import java.math.BigDecimal;


public class CalculateTaxRequest {

    @JsonProperty
    @Valid
    @NotNull(message = "first number must not be null")
    @DecimalMin("0.0")
    private BigDecimal first;

    @JsonProperty
    @Valid
    @NotNull(message = "second number must not be null")
    @DecimalMin("0.0")
    private BigDecimal second;

    public CalculateTaxRequest(BigDecimal first, BigDecimal second) {
        this.first = first;
        this.second = second;
    }

    public CalculateTaxRequest() {
    }

    public BigDecimal getFirst() {
        return first;
    }

    public void setFirst(BigDecimal first) {
        this.first = first;
    }

    public BigDecimal getSecond() {
        return second;
    }

    public void setSecond(BigDecimal second) {
        this.second = second;
    }
}
