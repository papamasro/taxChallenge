package com.example.demo.service;

import com.example.demo.model.external.TaxesServiceRequest;
import com.example.demo.model.services.calculate.TaxValueRequest;
import com.example.demo.model.external.TaxesServiceResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.concurrent.TimeoutException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;


public class TaxServiceTest {

    /*
    @Mock
    private WebClient.Builder webClientBuilder;

    @Mock
    private WebClient.RequestHeadersUriSpec<?> requestHeadersUriSpec;

    @Mock
    private WebClient.RequestHeadersSpec<?> requestHeadersSpec;

    @Mock
    private WebClient.ResponseSpec responseSpec;

    @InjectMocks
    private TaxService taxService;

    String taxName = "IIGG";
    String timestamp = "12345";
    double firstNumber = 10.0;
    double secondNumber = 20.0;
    double externalTaxes = 0.1; // 10% tax rate for testing purposes
    double expectedResult = 33.0; // (10 + 20) + (10 + 20) * 0.1 = 33.0

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // Mock the behavior of WebClient.Builder
        when(webClientBuilder.baseUrl(anyString())).thenReturn(webClientBuilder);
        when(webClientBuilder.build()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.get()).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.uri(anyString(), any())).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
    }
    @Test
    public void testGetTaxesSuccess() {
        // Arrange
        TaxesServiceRequest request = new TaxesServiceRequest(taxName);
        TaxesServiceResponse expectedResponse = new TaxesServiceResponse(timestamp,taxName,externalTaxes);

        // Mock the behavior of WebClient.ResponseSpec
        when(responseSpec.bodyToMono(TaxesServiceResponse.class)).thenReturn(Mono.just(expectedResponse));

        // Act
        TaxesServiceResponse result = taxService.getTaxes(request);

        // Assert
        assertEquals(expectedResponse, result);

        // Verify that the WebClient methods were called with the correct arguments
        verify(webClientBuilder, times(1)).build();
        verify(requestHeadersSpec, times(1)).retrieve();
        verify(responseSpec, times(1)).bodyToMono(TaxesServiceResponse.class);
    }

    @Test
    public void testGetTaxesRetryOnTimeoutException() {
        // Arrange
        TaxesServiceRequest request = new TaxesServiceRequest(taxName);
        TaxesServiceResponse expectedResponse = new TaxesServiceResponse(timestamp,taxName,externalTaxes);

        // Mock the behavior of WebClient.ResponseSpec
        when(responseSpec.bodyToMono(TaxesServiceResponse.class))
                .thenReturn(Mono.error(new TimeoutException()))
                .thenReturn(Mono.just(expectedResponse));

        // Act
        TaxesServiceResponse result = taxService.getTaxes(request);

        // Assert
        assertEquals(expectedResponse, result);

        // Verify that the WebClient methods were called with the correct arguments
        verify(webClientBuilder, times(1)).build();
        verify(requestHeadersSpec, times(2)).retrieve();
        verify(responseSpec, times(2)).bodyToMono(TaxesServiceResponse.class);
    }

     */
}