package com.example.demo.repository;

import com.example.demo.model.entity.TaxesPercentCacheEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaxesRedisRepository extends CrudRepository<TaxesPercentCacheEntity, String> {
    TaxesPercentCacheEntity findByDate(String date);

    TaxesPercentCacheEntity findByName(String name);

}