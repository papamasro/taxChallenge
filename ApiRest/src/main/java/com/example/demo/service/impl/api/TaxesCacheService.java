package com.example.demo.service.impl.api;

import com.example.demo.model.jpa.TaxesPercentCache;
import com.example.demo.repository.TaxesRedisRepository;
import com.example.demo.service.TaxesCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TaxesCacheService implements TaxesCache {

    @Autowired
    private TaxesRedisRepository taxesRedisRepository;

    public Optional<TaxesPercentCache> getLastTaxesFromCache(String name) {
        return Optional.ofNullable(taxesRedisRepository.findByName(name));
    }

    public void saveTaxesCache(TaxesPercentCache tax) {
        taxesRedisRepository.save(tax);
    }


}
