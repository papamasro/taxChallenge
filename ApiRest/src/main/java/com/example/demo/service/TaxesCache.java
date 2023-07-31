package com.example.demo.service;

import com.example.demo.model.jpa.TaxesPercentCache;

import java.util.Optional;

public interface TaxesCache {
    Optional<TaxesPercentCache> getLastTaxesFromCache(String name);
    void saveTaxesCache(TaxesPercentCache tax);
}
