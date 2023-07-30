package com.example.demo.model.external;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TaxesServiceRequest {

    @JsonProperty
    private String taxesName;


    public TaxesServiceRequest(String taxesName) {
        this.taxesName = taxesName;
    }

    public String getTaxesName() {
        return taxesName;
    }

    public void setTaxesName(String taxesName) {
        this.taxesName = taxesName;
    }
}
