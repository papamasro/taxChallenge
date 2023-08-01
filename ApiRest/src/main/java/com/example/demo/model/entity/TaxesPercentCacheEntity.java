package com.example.demo.model.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;
import java.math.BigDecimal;

@RedisHash(value = "TAXES", timeToLive = 1800) //TODO MOVE TTL TO APLICATION PROPERTY
public class TaxesPercentCacheEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Indexed
    private String name;
    private String timestamp;
    private BigDecimal value;

    public TaxesPercentCacheEntity(String name, String timestamp, BigDecimal value) {
        this.name = name;
        this.timestamp = timestamp;
        this.value = value;
    }

    public TaxesPercentCacheEntity() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }
}