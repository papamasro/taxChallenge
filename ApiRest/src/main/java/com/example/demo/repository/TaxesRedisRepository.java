package com.example.demo.repository;

import com.example.demo.model.jpa.TaxesCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Repository
public interface TaxesRedisRepository extends CrudRepository<TaxesCache, String> {
    TaxesCache findByDate(String date);

    TaxesCache findByName(String name);

}