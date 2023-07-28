package com.example.demo.dto;

public class HistoryDTO {
        private String timestamp;
        private String endpoint;
        private Integer statusCode;
        private String response;

    public HistoryDTO() {
    }

    public HistoryDTO(String timestamp, String endpoint, Integer statusCode, String response) {
        this.timestamp = timestamp;
        this.endpoint = endpoint;
        this.statusCode = statusCode;
        this.response = response;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
