package com.example.demo.controller;

import com.example.demo.model.CalculateTaxRequest;
import com.example.demo.model.CalculateTaxResponse;
import com.example.demo.service.CalculatorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class CalculatorControllerTest {

    @Mock
    private CalculatorService calculatorService;

    @InjectMocks
    private CalculatorController calculatorController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCalculateTax_SuccessfulResponse() {
        // Arrange
        CalculateTaxRequest calculateTaxRequest = new CalculateTaxRequest(1.0,1.0);
        CalculateTaxResponse expectedResponse = new CalculateTaxResponse(/* fill expected response with necessary data */);
        when(calculatorService.calculateTax(calculateTaxRequest)).thenReturn(expectedResponse);

        // Act
        ResponseEntity<CalculateTaxResponse> responseEntity = calculatorController.calculateTax(calculateTaxRequest);

        // Assert
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(expectedResponse, responseEntity.getBody());
    }

    @Test
    public void testCalculateTax_ExceptionFromService() {
        // Arrange
        CalculateTaxRequest calculateTaxRequest = new CalculateTaxRequest(1.0, 1.0);
        when(calculatorService.calculateTax(calculateTaxRequest)).thenThrow(new RuntimeException("Test exception"));

        // Act
        ResponseEntity<CalculateTaxResponse> responseEntity = calculatorController.calculateTax(calculateTaxRequest);

        // Assert
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertNull(responseEntity.getBody());
    }
}