package com.example.demo.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.demo.controller.CalculatorController;
import com.example.demo.exception.TooManyRequestException;
import com.example.demo.model.services.calculate.CalculateTaxRequest;
import com.example.demo.model.services.calculate.CalculateTaxResponse;
import com.example.demo.model.services.calculate.TaxValueRequest;
import com.example.demo.service.impl.CalculatorService;
import com.example.demo.service.impl.api.RateLimiterServices;
import io.github.bucket4j.Bucket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.Duration;

@ExtendWith(MockitoExtension.class)
class CalculatorControllerTest {

    @Mock
    private RateLimiterServices rateLimiter;

    @Mock
    private CalculatorService calculatorService;

    @InjectMocks
    private CalculatorController calculatorController;


    String taxName = "IIGG";
    String timestamp = "12345";

    Integer userId = 123;
    BigDecimal firstNumber = new BigDecimal(10.0);
    BigDecimal secondNumber = new BigDecimal(20.0);
    BigDecimal externalTaxes = new BigDecimal(0.10);
    BigDecimal expectedResult = new BigDecimal(33.0);

    @Test
    void testCalculateTaxSuccess() {
        // Arrange
        CalculateTaxRequest calculateTaxRequest = new CalculateTaxRequest(firstNumber, secondNumber);
        TaxValueRequest taxValueRequest = new TaxValueRequest("IIGG", calculateTaxRequest.getFirst(), calculateTaxRequest.getSecond());
        CalculateTaxResponse calculatedResponse = new CalculateTaxResponse(timestamp, externalTaxes, expectedResult);

        Bucket bucketMock = mock(Bucket.class);
        when(rateLimiter.resolveBucket(userId.toString())).thenReturn(bucketMock);
        when(bucketMock.tryConsume(1)).thenReturn(true);

        when(calculatorService.calculateTax(any(TaxValueRequest.class))).thenReturn(calculatedResponse);

        // Act
        ResponseEntity<CalculateTaxResponse> responseEntity = calculatorController.calculateTax(userId, calculateTaxRequest);

        // Assert
        assertEquals(200, responseEntity.getStatusCodeValue());
        CalculateTaxResponse responseBody = responseEntity.getBody();
        assertNotNull(responseBody);
        assertEquals(calculatedResponse.getTax(), responseBody.getTax());
    }

    @Test
    void testCalculateTaxTooManyRequests() {
        // Arrange
        Integer userId = 1;
        CalculateTaxRequest calculateTaxRequest = new CalculateTaxRequest(firstNumber, secondNumber);

        Bucket bucketMock = mock(Bucket.class);
        when(rateLimiter.resolveBucket(userId.toString())).thenReturn(bucketMock);
        when(bucketMock.tryConsume(1)).thenReturn(false);

        // Act & Assert
        assertThrows(TooManyRequestException.class, () -> calculatorController.calculateTax(userId, calculateTaxRequest));
    }
}