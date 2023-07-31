package com.example.demo.service;

import com.example.demo.exception.NoTaxValueException;
import com.example.demo.model.jpa.TaxesCache;
import com.example.demo.model.services.calculate.CalculateTaxRequest;
import com.example.demo.model.services.calculate.CalculateTaxResponse;
import com.example.demo.model.services.calculate.TaxValueRequest;
import com.example.demo.model.external.TaxesServiceResponse;
import com.example.demo.service.api.LoggingEventService;
import com.example.demo.service.api.TaxesCacheService;
import com.example.demo.util.DateFormatter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CalculatorServiceTest {

    @Mock
    private TaxService taxRepository;

    @Mock
    private TaxesCacheService taxesCacheService;

    @InjectMocks
    private CalculatorService calculatorService;

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
    public void testCalculateTax() {


        TaxValueRequest taxValueRequest = new TaxValueRequest(taxName, firstNumber, secondNumber);
        when(taxRepository.getTaxes(any())).thenReturn(new TaxesServiceResponse(timestamp, taxName, externalTaxes));

        CalculateTaxResponse result = calculatorService.calculateTax(taxValueRequest);

        assertEquals(externalTaxes, result.getTax());
        assertEquals(expectedResult, result.getResult());

        verify(taxRepository, times(1)).getTaxes(any());
    }

    @Test
    public void testCalculateTaxWithCache() {

        TaxValueRequest taxValueRequest = new TaxValueRequest(taxName, firstNumber, secondNumber);
        when(taxRepository.getTaxes(any())).thenReturn(null);

        TaxesCache taxesCache = new TaxesCache(taxName, DateFormatter.getStringDate(), externalTaxes);
        when(taxesCacheService.getLastTaxesFromCache(taxName)).thenReturn(Optional.of(taxesCache));

        CalculateTaxResponse result = calculatorService.calculateTax(taxValueRequest);

        assertEquals(externalTaxes, result.getTax());
        assertEquals(expectedResult, result.getResult());

        verify(taxRepository, times(1)).getTaxes(any());
        verify(taxesCacheService, times(1)).getLastTaxesFromCache(taxName);
    }

    @Test
    public void testCalculateTaxWithCacheNotFound() {

        TaxValueRequest taxValueRequest = new TaxValueRequest(taxName, firstNumber, secondNumber);
        when(taxRepository.getTaxes(any())).thenThrow(new RuntimeException("Error: External service not available"));

        when(taxesCacheService.getLastTaxesFromCache(taxName)).thenReturn(Optional.empty());

        assertThrows(NoTaxValueException.class, () -> calculatorService.calculateTax(taxValueRequest));

        verify(taxRepository, times(1)).getTaxes(any());
        verify(taxesCacheService, times(1)).getLastTaxesFromCache(taxName);
    }
}