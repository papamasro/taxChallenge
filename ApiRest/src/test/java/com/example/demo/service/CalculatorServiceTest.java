package com.example.demo.service;

import com.example.demo.exception.NoTaxValueException;
import com.example.demo.model.entity.TaxesPercentCacheEntity;
import com.example.demo.model.services.calculate.CalculateTaxResponse;
import com.example.demo.model.services.calculate.TaxValueRequest;
import com.example.demo.model.external.TaxesServiceResponse;
import com.example.demo.service.impl.CalculatorService;
import com.example.demo.service.impl.TaxPercentService;
import com.example.demo.service.impl.api.TaxesCacheService;
import com.example.demo.util.DateFormatter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CalculatorServiceTest {

    @Mock
    private TaxPercentService taxRepository;

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
    BigDecimal firstNumber = new BigDecimal("10.0");
    BigDecimal secondNumber = new BigDecimal("20.0");
    BigDecimal externalTaxes = new BigDecimal("0.10");
    BigDecimal expectedResult = new BigDecimal("33");

    @Test
    public void testCalculateTax() {


        TaxValueRequest taxValueRequest = new TaxValueRequest(taxName, firstNumber, secondNumber);
        when(taxRepository.getTaxes(any())).thenReturn(new TaxesServiceResponse(timestamp, taxName, externalTaxes));

        CalculateTaxResponse result = calculatorService.calculateTax(taxValueRequest);

        assertEquals(externalTaxes, result.getTax());
        assertEquals(expectedResult, result.getResult().setScale(0, BigDecimal.ROUND_UP));

        verify(taxRepository, times(1)).getTaxes(any());
    }

    @Test
    public void testCalculateTaxWithCache() {

        TaxValueRequest taxValueRequest = new TaxValueRequest(taxName, firstNumber, secondNumber);
        when(taxRepository.getTaxes(any())).thenReturn(null);

        TaxesPercentCacheEntity taxesPercentCacheEntity = new TaxesPercentCacheEntity(taxName, DateFormatter.getStringDate(), externalTaxes);
        when(taxesCacheService.getLastTaxesFromCache(taxName)).thenReturn(Optional.of(taxesPercentCacheEntity));

        CalculateTaxResponse result = calculatorService.calculateTax(taxValueRequest);

        assertEquals(externalTaxes, result.getTax());
        assertEquals(expectedResult, result.getResult().setScale(0, BigDecimal.ROUND_UP));

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