package com.example.demo.model.external;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TaxValueRequest {

        @JsonProperty
        private String taxesName;

        public TaxValueRequest(String taxesName) {
                this.taxesName = taxesName;
        }

        public String getTaxesName() {
                return taxesName;
        }

        public void setTaxesName(String taxesName) {
                this.taxesName = taxesName;
        }
}
