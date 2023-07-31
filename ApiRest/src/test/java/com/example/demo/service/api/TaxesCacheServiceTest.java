package com.example.demo.service.api;

import com.example.demo.model.jpa.TaxesCache;
import com.example.demo.repository.TaxesRedisRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TaxesCacheServiceTest {

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
    double firstNumber = 10.0;
    double secondNumber = 20.0;
    double externalTaxes = 0.1; // 10% tax rate for testing purposes
    double expectedResult = 33.0; // (10 + 20) + (10 + 20) * 0.1 = 33.0
    @Test
    public void testGetLastTaxesFromCache() {
        TaxesCache expectedTaxesCache = new TaxesCache(taxName,timestamp,externalTaxes);

        when(taxesRedisRepository.findByName(taxName)).thenReturn(expectedTaxesCache);

        Optional<TaxesCache> result = taxesCacheService.getLastTaxesFromCache(taxName);

        assertEquals(expectedTaxesCache, result.orElse(null));

        verify(taxesRedisRepository, times(1)).findByName(taxName);
    }

    @Test
    public void testGetLastTaxesFromCacheNotFound() {

        when(taxesRedisRepository.findByName(taxName)).thenReturn(null);

        Optional<TaxesCache> result = taxesCacheService.getLastTaxesFromCache(taxName);

        assertEquals(null, result.orElse(null));

        verify(taxesRedisRepository, times(1)).findByName(taxName);
    }

    @Test
    public void testSaveTaxesCache() {
        TaxesCache taxesCache = new TaxesCache(taxName,timestamp,externalTaxes);

        taxesCacheService.saveTaxesCache(taxesCache);

        verify(taxesRedisRepository, times(1)).save(taxesCache);
    }
}