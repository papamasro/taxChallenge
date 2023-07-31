package com.example.demo.repository;

import com.example.demo.model.jpa.TaxesPercentCache;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaxesRedisRepository extends CrudRepository<TaxesPercentCache, String> {
    TaxesPercentCache findByDate(String date);

    TaxesPercentCache findByName(String name);

}