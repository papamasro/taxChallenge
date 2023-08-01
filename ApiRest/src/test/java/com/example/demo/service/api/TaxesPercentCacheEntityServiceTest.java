package com.example.demo.service.api;

import com.example.demo.model.entity.TaxesPercentCacheEntity;
import com.example.demo.repository.TaxesRedisRepository;
import com.example.demo.service.impl.api.TaxesCacheService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TaxesPercentCacheEntityServiceTest {

    @Mock
    private TaxesRedisRepository taxesRedisRepository;

    @InjectMocks
    private TaxesCacheService taxesCacheService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    String taxName = "IIGG";
    String timestamp = "12345";
    BigDecimal firstNumber = new BigDecimal(10.0);
    BigDecimal secondNumber = new BigDecimal(20.0);
    BigDecimal externalTaxes = new BigDecimal(0.10);
    BigDecimal expectedResult = new BigDecimal(33.0);
    @Test
    public void testGetLastTaxesFromCache() {
        TaxesPercentCacheEntity expectedTaxesPercentCacheEntity = new TaxesPercentCacheEntity(taxName,timestamp,externalTaxes);

        when(taxesRedisRepository.findByName(taxName)).thenReturn(expectedTaxesPercentCacheEntity);

        Optional<TaxesPercentCacheEntity> result = taxesCacheService.getLastTaxesFromCache(taxName);

        assertEquals(expectedTaxesPercentCacheEntity, result.orElse(null));

        verify(taxesRedisRepository, times(1)).findByName(taxName);
    }

    @Test
    public void testGetLastTaxesFromCacheNotFound() {

        when(taxesRedisRepository.findByName(taxName)).thenReturn(null);

        Optional<TaxesPercentCacheEntity> result = taxesCacheService.getLastTaxesFromCache(taxName);

        assertEquals(null, result.orElse(null));

        verify(taxesRedisRepository, times(1)).findByName(taxName);
    }

    @Test
    public void testSaveTaxesCache() {
        TaxesPercentCacheEntity taxesPercentCacheEntity = new TaxesPercentCacheEntity(taxName,timestamp,externalTaxes);

        taxesCacheService.saveTaxesCache(taxesPercentCacheEntity);

        verify(taxesRedisRepository, times(1)).save(taxesPercentCacheEntity);
    }
}