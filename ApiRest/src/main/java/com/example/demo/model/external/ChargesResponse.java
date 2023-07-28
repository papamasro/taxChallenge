package com.example.demo.model.external;
import com.fasterxml.jackson.annotation.JsonProperty;


public class ChargesResponse {

    @JsonProperty
    private String timestamp;
    @JsonProperty
    private Double tax;


    public ChargesResponse() {
    }

    public ChargesResponse(String timestamp, Double tax) {
        this.timestamp = timestamp;
        this.tax = tax;
    }

    public String getDate() {
        return timestamp;
    }

    public void setDate(String date) {
        this.timestamp = date;
    }

    public Double getTax() {
        return tax;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }
}
