package com.example.demo.service.impl.api;

import com.example.demo.model.entity.TaxesPercentCacheEntity;
import com.example.demo.repository.TaxesRedisRepository;
import com.example.demo.service.TaxesCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TaxesCacheService implements TaxesCache {

    @Autowired
    private TaxesRedisRepository taxesRedisRepository;

    public Optional<TaxesPercentCacheEntity> getLastTaxesFromCache(String name) {
        return Optional.ofNullable(taxesRedisRepository.findByName(name));
    }

    public void saveTaxesCache(TaxesPercentCacheEntity tax) {
        taxesRedisRepository.save(tax);
    }


}
