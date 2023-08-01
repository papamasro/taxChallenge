package com.example.demo.service;

import com.example.demo.model.entity.TaxesPercentCacheEntity;

import java.util.Optional;

public interface TaxesCache {
    Optional<TaxesPercentCacheEntity> getLastTaxesFromCache(String name);
    void saveTaxesCache(TaxesPercentCacheEntity tax);
}
