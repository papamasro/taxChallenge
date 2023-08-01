package com.example.demo.service.api;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.demo.service.impl.api.RateLimiterServices;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.BucketConfiguration;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Refill;
import io.github.bucket4j.distributed.proxy.ProxyManager;
import io.github.bucket4j.distributed.proxy.RemoteBucketBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Duration;
import java.util.Optional;
import java.util.function.Supplier;

@ExtendWith(MockitoExtension.class)
class RateLimiterServiceTest {

    @Mock
    private ProxyManager buckets;

    @InjectMocks
    private RateLimiterServices rateLimiter;
/*
    @Test
    void testResolveBucket() {
        // Arrange
        String key = "userKey";
        BucketConfiguration bucketConfig = BucketConfiguration.builder()
                .addLimit(Bandwidth.classic(3, Refill.intervally(3, Duration.ofMinutes(1))))
                .build();
        Supplier<BucketConfiguration> configSupplier = () -> bucketConfig;
        Bucket bucketMock = mock(Bucket.class);

        when(buckets.builder()).thenReturn((RemoteBucketBuilder) mock(Bucket.builder()));
        when(buckets.builder().build(key, configSupplier)).thenReturn(bucketMock);

        // Act
        Bucket resultBucket = rateLimiter.resolveBucket(key);

        // Assert
        assertEquals(bucketMock, resultBucket);
    }

    @Test
    void testGetConfigSupplierForUser() {
        // Arrange
        String key = "userKey";

        // Act
        Optional configSupplier = rateLimiter.buckets.getProxyConfiguration(key);

        // Assert
        assertNotNull(configSupplier);
        BucketConfiguration bucketConfig = (BucketConfiguration) configSupplier.get();
        assertNotNull(bucketConfig);
        assertEquals(1, bucketConfig.getBandwidths().size());
        Bandwidth bandwidth = bucketConfig.getBandwidths().get(0);
        assertEquals(3, bandwidth.getCapacity());
        assertEquals(3, bandwidth.getInitialTokens());
        assertEquals(Duration.ofMinutes(1), bandwidth.getRefillPeriod());
    }

 */
}