package com.example.demo.repository;

import com.example.demo.model.jpa.TaxesCache;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaxesRedisRepository extends CrudRepository<TaxesCache, String> {
    TaxesCache findByDate(String date);

    TaxesCache findByName(String name);

}