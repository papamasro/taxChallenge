package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;



public class CalculateTaxRequest {

    @JsonProperty
    private Double first;

    @JsonProperty
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
