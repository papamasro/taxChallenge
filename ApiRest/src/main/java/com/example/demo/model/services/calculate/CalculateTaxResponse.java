package com.example.demo.model.services.calculate;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class CalculateTaxResponse {

    @JsonProperty
    private String date;
    @JsonProperty
    private BigDecimal tax;
    @JsonProperty
    private BigDecimal result;

    public CalculateTaxResponse() {
    }

    public CalculateTaxResponse(String date, BigDecimal tax, BigDecimal result) {
        this.date = date;
        this.tax = tax;
        this.result = result;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public BigDecimal getResult() {
        return result;
    }

    public void setResult(BigDecimal result) {
        this.result = result;
    }

}
