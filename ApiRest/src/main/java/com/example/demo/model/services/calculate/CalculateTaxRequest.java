package com.example.demo.model.services.calculate;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.NotEmpty;


public class CalculateTaxRequest {

    @JsonProperty
    @Nonnull
    @NotEmpty
    private Double first;

    @JsonProperty
    @Nonnull
    @NotEmpty
    private Double second;

    public CalculateTaxRequest(Double first, Double second) {
        this.first = first;
        this.second = second;
    }

    public CalculateTaxRequest() {
    }

    public Double getFirst() {
        return first;
    }

    public void setFirst(Double first) {
        this.first = first;
    }

    public Double getSecond() {
        return second;
    }

    public void setSecond(Double second) {
        this.second = second;
    }
}
