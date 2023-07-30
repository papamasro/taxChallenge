package com.example.demo.service.api;

import com.example.demo.model.jpa.TaxesCache;
import com.example.demo.repository.TaxesRedisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TaxesCacheService {

    @Autowired
    private TaxesRedisRepository taxesRedisRepository; //TODO ESTA BIEN ESTE DOBLE REPO?

    public Optional<TaxesCache> getLastTaxesFromCache(String name) {
        return Optional.ofNullable(taxesRedisRepository.findByName(name));
    }

    public void saveTaxesCache(TaxesCache tax) {
        taxesRedisRepository.save(tax);
    }


}
