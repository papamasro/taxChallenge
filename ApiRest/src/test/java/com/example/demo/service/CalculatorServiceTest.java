package com.example.demo.service;

import com.example.demo.exception.NoTaxValueException;
import com.example.demo.model.CalculateTaxRequest;
import com.example.demo.model.CalculateTaxResponse;
import com.example.demo.model.external.TaxValueRequest;
import com.example.demo.model.external.TaxValueResponse;
import com.example.demo.service.api.LoggingEventService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CalculatorServiceTest {

    @Mock
    private LoggingEventService loggingEventService;

    @Mock
    private TaxService taxRepository;

    @Mock
    private PercentageCacheService percentageCacheService;

    @InjectMocks
    private CalculatorService calculatorService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetTaxes_SuccessfulResponse() {
        // Arrange
        TaxValueRequest request = new TaxValueRequest("taxesName");
        TaxValueResponse response = new TaxValueResponse("123456","iigg",0.1);
        when(taxRepository.getTaxes(request)).thenReturn(response);

        // Act
        Double result = calculatorService.getExternalTaxes();

        // Assert
        assertNotNull(result);
        // Add more assertions based on expected behavior of the method
    }

    @Test
    public void testGetTaxes_ExceptionThrownFromRepository() {
        // Arrange
        TaxValueRequest request = new TaxValueRequest("taxesName");
        when(taxRepository.getTaxes(request)).thenThrow(new RuntimeException("Test exception"));

        // Act and Assert
        assertThrows(NoTaxValueException.class, () -> calculatorService.getExternalTaxes());
        // Add more assertions based on expected behavior of the method when an exception is thrown
    }

    @Test
    public void testCalculateTax() {
        // Arrange
        double taxValue = 0.1; // Example tax value for testing
        CalculateTaxRequest chargesRequest = new CalculateTaxRequest(10.0, 20.0);
        when(calculatorService.getExternalTaxes()).thenReturn(taxValue);

        // Act
        CalculateTaxResponse response = calculatorService.calculateTax(chargesRequest);

        // Assert
        assertNotNull(response);
        // Add more assertions based on expected behavior of the method
    }

    @Test
    public void testGetPercentageFromCache() {
        // Arrange
        double percentage = 0.15; // Example percentage value for testing
        when(percentageCacheService.getPercentage()).thenReturn(Optional.of(percentage));

        // Act
        Optional<Double> result = calculatorService.getPercentageFromCache();

        // Assert
        assertTrue(result.isPresent());
        assertEquals(percentage, result.get());
    }

    @Test
    public void testSavePercentageFromCache() {
        // Arrange
        double percentage = 0.2; // Example percentage value for testing

        // Act
        calculatorService.savePercentageFromCache(percentage);

        // Assert
        // Add assertions based on the behavior of the method, e.g., verify that the cache service was called with the correct value
        verify(percentageCacheService).savePercentage(percentage);
    }
}