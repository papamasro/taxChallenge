package com.example.demo.model.external;

import com.fasterxml.jackson.annotation.JsonProperty;


public class TaxValueResponse {

    @JsonProperty
    private String timestamp;
    @JsonProperty
    private String name;
    @JsonProperty
    private Double tax;


    public TaxValueResponse() {
    }

    public TaxValueResponse(String timestamp, String name, Double tax) {
        this.name = name;
        this.timestamp = timestamp;
        this.tax = tax;
    }

    public Double getTax() {
        return tax;
    }

    public void setTax(Double tax) {
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
