package com.example.demo.model.external;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ChargesRequest {

        @JsonProperty
        private Double first;

        @JsonProperty
        private Double second;

        public ChargesRequest(Double first, Double second) {
                this.first = first;
                this.second = second;
        }

        public Double getFirst() {
                return first;
        }

        public void setFirst(Double first) {
                this.first = first;
        }

        public Double getSecond() {
                return second;
        }

        public void setSecond(Double second) {
                this.second = second;
        }
}
