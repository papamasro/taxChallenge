package com.example.demo.model;


import com.fasterxml.jackson.annotation.JsonProperty;

public class CalculateTaxResponse {

    @JsonProperty
    private String date;
    @JsonProperty
    private Double tax;
    @JsonProperty
    private Double result;

    public CalculateTaxResponse() {
    }

    public CalculateTaxResponse(String date, Double tax, Double result) {
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

    public Double getTax() {
        return tax;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }

    public Double getResult() {
        return result;
    }

    public void setResult(Double result) {
        this.result = result;
    }

}
