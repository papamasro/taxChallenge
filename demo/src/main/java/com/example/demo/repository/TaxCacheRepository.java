package com.example.demo.repository;

import com.example.demo.model.database.redis.RedisTaxes;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface TaxCacheRepository extends CrudRepository<RedisTaxes, String> {

}