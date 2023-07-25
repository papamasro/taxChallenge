package com.example.demo.model;


import com.example.demo.model.database.redis.RedisTaxes;

public class CalculateTaxResponse {

    private Double result;
    private Iterable<RedisTaxes> history;

    public CalculateTaxResponse() {
    }
    public CalculateTaxResponse(Double result, Iterable<RedisTaxes> history) {
        this.result = result;
        this.history = history;
    }

    public Double getResult() {
        return result;
    }

    public void setResult(Double result) {
        this.result = result;
    }

    public Iterable<RedisTaxes> getHistory() {
        return history;
    }

    public void setHistory(Iterable<RedisTaxes> history) {
        this.history = history;
    }
}
