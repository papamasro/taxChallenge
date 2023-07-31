package com.example.demo.model.services.calculate;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class TaxValueRequest {

    @JsonProperty
    private String taxesName;

    @JsonProperty
    private BigDecimal firstNumber;

    @JsonProperty
    private BigDecimal secondNumber;


    public TaxValueRequest(String taxesName, BigDecimal firstNumber, BigDecimal secondNumber) {
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

    public BigDecimal getFirstNumber() {
        return firstNumber;
    }

    public void setFirstNumber(BigDecimal firstNumber) {
        this.firstNumber = firstNumber;
    }

    public BigDecimal getSecondNumber() {
        return secondNumber;
    }

    public void setSecondNumber(BigDecimal secondNumber) {
        this.secondNumber = secondNumber;
    }
}
