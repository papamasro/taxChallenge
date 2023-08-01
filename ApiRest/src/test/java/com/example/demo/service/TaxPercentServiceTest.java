package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.demo.model.external.TaxesServiceRequest;
import com.example.demo.model.external.TaxesServiceResponse;
import com.example.demo.service.impl.TaxPercentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;
import java.time.Duration;

@ExtendWith(MockitoExtension.class)
class TaxPercentServiceTest {
/*
    @Mock
    private WebClient.Builder webClientBuilder;

    @InjectMocks
    private TaxPercentService taxPercentService;


    String taxName = "IIGG";
    String timestamp = "12345";
    BigDecimal firstNumber = new BigDecimal("10.0");
    BigDecimal secondNumber = new BigDecimal("20.0");
    BigDecimal externalTaxes = new BigDecimal("0.10");
    BigDecimal expectedResult = new BigDecimal("33");
    @Test
    void testGetTaxes() {
        // Arrange
        TaxesServiceRequest request = new TaxesServiceRequest(taxName);
        TaxesServiceResponse response = new TaxesServiceResponse(timestamp,taxName,externalTaxes);
        when(webClientBuilder.build()).thenReturn(mock(WebClient.class));
        when(webClientBuilder.build().get()).thenReturn(mock(WebClient.RequestHeadersUriSpec.class));
  //      when(webClientBuilder.build().get().uri("/getTax", request)).thenReturn(mock(WebClient.RequestHeadersUriSpec.class));
        when(webClientBuilder.build().get().uri("/getTax", request).retrieve()).thenReturn(mock(WebClient.ResponseSpec.class));
        when(webClientBuilder.build().get().uri("/getTax", request).retrieve().bodyToMono(TaxesServiceResponse.class)).thenReturn(mock(reactor.core.publisher.Mono.class));
  //      when(webClientBuilder.build().get().uri("/getTax", request).retrieve().bodyToMono(TaxesServiceResponse.class).retryWhen(any())).thenReturn(response);

        // Act
        TaxesServiceResponse resultResponse = taxPercentService.getTaxes(request);

        // Assert
        assertNotNull(resultResponse);
        assertEquals(response, resultResponse);
        // You can add more assertions based on your implementation and expected output
    }

 */
}