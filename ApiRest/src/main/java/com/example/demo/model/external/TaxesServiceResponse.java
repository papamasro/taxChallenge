package com.example.demo.model.external;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;


public class TaxesServiceResponse {

    @JsonProperty
    private String timestamp;
    @JsonProperty
    private String name;
    @JsonProperty
    private BigDecimal tax;


    public TaxesServiceResponse() {
    }

    public TaxesServiceResponse(String timestamp, String name, BigDecimal tax) {
        this.name = name;
        this.timestamp = timestamp;
        this.tax = tax;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
