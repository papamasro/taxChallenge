package com.example.demo.service.api;

import com.example.demo.repository.PercentageCacheRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Optional;

@Service
public class PercentageCacheService {
    private final String percentageKey = "PERCENTAGE_KEY";

    @Value("${redis.ttl.taxes}")
    private Long percentageTtl;

    PercentageCacheRepository percentageCacheRepository;
    public PercentageCacheService(PercentageCacheRepository percentageCacheRepository) {
        this.percentageCacheRepository = percentageCacheRepository;
    }

    public Optional<Double> getPercentage() {
        return Optional.ofNullable(this.percentageCacheRepository.getKey(this.percentageKey));
    }

    public void savePercentage(Double percentage) {
        this.percentageCacheRepository.saveIfAbsent(this.percentageKey, percentage, Duration.ofMinutes(this.percentageTtl));
    }

}
