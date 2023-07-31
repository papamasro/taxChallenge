package com.example.demo.service;

import com.example.demo.model.external.TaxesServiceRequest;
import com.example.demo.model.external.TaxesServiceResponse;
import io.netty.handler.timeout.TimeoutException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.util.retry.Retry;

import java.time.Duration;

@Service
public class TaxService {
    private static final Logger logger = LoggerFactory.getLogger(TaxService.class);
    private final WebClient.Builder webClientBuilder;
    @Autowired
    public TaxService(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    public TaxesServiceResponse getTaxes(TaxesServiceRequest request) {
        logger.info("Calling getTax external service");
        return webClientBuilder
                .build()
                .get()
                .uri("/getTax", request)
                .retrieve()
                .bodyToMono(TaxesServiceResponse.class)
                .retryWhen(Retry.backoff(3, Duration.ofSeconds(2)).jitter(0.75)
                        .filter(TimeoutException.class::isInstance))
                .block();
    }
}
