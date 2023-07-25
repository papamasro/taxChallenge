package com.example.demo.model.database.redis;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;


@RedisHash(value = "taxes")
public class RedisTaxes implements Serializable {

    @Id
    @Nonnull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="endpoint")
    private String endpoint;

    @Column(name="taxPercent")
    private String taxPercent;

    @Column(name="timestamp")
    private String timestamp;

    @Column(name="statusCode")
    private Integer statusCode;

    @Column(name="response")
    private String response;

    public RedisTaxes() {
    }

    public RedisTaxes(Long id, String endpoint, String taxPercent, String timestamp, Integer statusCode, String response) {
        this.id = id;
        this.endpoint = endpoint;
        this.taxPercent = taxPercent;
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

    public String getTaxPercent() {
        return taxPercent;
    }

    public void setTaxPercent(String taxPercent) {
        this.taxPercent = taxPercent;
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