package com.example.demo.model.external;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ChargeRequest {

        @JsonProperty
        private String taxesName;

        public ChargeRequest(String taxesName) {
                this.taxesName = taxesName;
        }

        public String getTaxesName() {
                return taxesName;
        }

        public void setTaxesName(String taxesName) {
                this.taxesName = taxesName;
        }
}
