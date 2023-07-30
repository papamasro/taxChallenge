package com.example.demo.model.services.calculate;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TaxValueRequest {

    @JsonProperty
    private String taxesName;

    @JsonProperty
    private Double firstNumber;

    @JsonProperty
    private Double secondNumber;


    public TaxValueRequest(String taxesName, Double firstNumber, Double secondNumber) {
        this.taxesName = taxesName;
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
    }

    public TaxValueRequest() {
    }

    public String getTaxesName() {
        return taxesName;
    }

    public void setTaxesName(String taxesName) {
        this.taxesName = taxesName;
    }

    public Double getFirstNumber() {
        return firstNumber;
    }

    public void setFirstNumber(Double firstNumber) {
        this.firstNumber = firstNumber;
    }

    public Double getSecondNumber() {
        return secondNumber;
    }

    public void setSecondNumber(Double secondNumber) {
        this.secondNumber = secondNumber;
    }
}
