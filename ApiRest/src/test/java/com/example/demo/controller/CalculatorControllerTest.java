package com.example.demo.controller;

import com.example.demo.model.services.calculate.CalculateTaxRequest;
import com.example.demo.model.services.calculate.CalculateTaxResponse;
import com.example.demo.model.services.calculate.TaxValueRequest;
import com.example.demo.service.CalculatorService;
import com.example.demo.util.DateFormatter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CalculatorControllerTest {

    @Mock
    private CalculatorService calculatorService;

    @InjectMocks
    private CalculatorController calculatorController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCalculateTax() {
        // Arrange
        double first = 10.0;
        double second = 20.0;
        double tax = 0.1;

        double expectedTaxValue = 30.0;

        CalculateTaxRequest calculateTaxRequest = new CalculateTaxRequest(first, second);
        TaxValueRequest taxValueRequest = new TaxValueRequest("IIGG", first, second);
        CalculateTaxResponse calculateTaxResponse = new CalculateTaxResponse(DateFormatter.getStringDate(),tax,expectedTaxValue);

        when(calculatorService.calculateTax(taxValueRequest)).thenReturn(calculateTaxResponse);

        ResponseEntity<CalculateTaxResponse> response = calculatorController.calculateTax(calculateTaxRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        // Verify that the calculatorService.calculateTax method was called with the correct argument
     //   verify(calculatorService, times(1)).calculateTax(taxValueRequest);
    }
}