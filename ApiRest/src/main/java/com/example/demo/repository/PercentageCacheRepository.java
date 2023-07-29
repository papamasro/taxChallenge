package com.example.demo.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class PercentageCacheRepository {

    @Autowired
    private RedisTemplate<String, Double> template;

    public void saveIfAbsent(String key, Double value, Duration duration) {
        this.template.opsForValue().setIfAbsent(key, value, duration);
    }

    public Double getKey(String key) {
        return this.template.opsForValue().get(key);
    }

}
