package com.example.demo.model.entity;

import jakarta.persistence.*;


@Entity
@Table(name = "call_history")
public class CallHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "endpoint")
    private String endpoint;

    @Column(name = "timestamp")
    private String timestamp;

    @Column(name = "status_code")
    private Integer statusCode;

    @Column(name = "response")
    private String response;

    public CallHistoryEntity() {
    }

    public CallHistoryEntity(Long id, String endpoint, String timestamp, Integer statusCode, String response) {
        this.id = id;
        this.endpoint = endpoint;
        this.timestamp = timestamp;
        this.statusCode = statusCode;
        this.response = response;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public Long getId() {
        return id;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String date) {
        this.timestamp = date;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}