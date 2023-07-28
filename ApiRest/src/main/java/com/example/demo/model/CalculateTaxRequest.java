package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;


public class CalculateTaxRequest {

    @JsonProperty @Nonnull
    private Double first;

    @JsonProperty @Nonnull
    private Double second;

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
