package com.example.demo.service.impl.api;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.BucketConfiguration;
import io.github.bucket4j.Refill;
import io.github.bucket4j.distributed.proxy.ProxyManager;
import io.github.bucket4j.local.LocalBucketBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.util.function.Supplier;

@Configuration
public class RateLimiterServices
{
    //autowiring dependencies

    @Autowired
    public ProxyManager buckets;

    public Bucket resolveBucket(String key) {
        Supplier<BucketConfiguration> configSupplier = getConfigSupplierForUser(key);

        // Does not always create a new bucket, but instead returns the existing one if it exists.
        return buckets.builder().build(key, configSupplier);
    }

    private Supplier<BucketConfiguration> getConfigSupplierForUser(String key) {
        //User user = userRepository.findById(userId);
        Refill refill = Refill.intervally(3, Duration.ofMinutes(1));
        //create 10 token bandwidth
        Bandwidth limit = Bandwidth.classic(3, refill);

        // Bandwidth limit = Bandwidth.

        return () -> (BucketConfiguration.builder()
                .addLimit(limit)
                .build());
    }
}