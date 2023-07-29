package com.example.demo.service;

import com.example.demo.exception.NoTaxService400Exception;
import com.example.demo.exception.NoTaxServiceException;
import com.example.demo.model.external.TaxValueRequest;
import com.example.demo.model.external.TaxValueResponse;
import io.netty.handler.timeout.TimeoutException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import reactor.util.retry.Retry;

import java.time.Duration;


@Service
public class TaxService { //TODO MOVE WEBCLIENT LOGIC TO WEBCLIENT CONFIGURATION CLASS IF WILL REUSED
    private static final Logger logger = LoggerFactory.getLogger(TaxService.class);

    @Value("${webclient.timeout}")
    private int timeout;

    private static HttpClient getHttpClientWithTimeout(int seconds) {
        return HttpClient.create().responseTimeout(Duration.ofSeconds(seconds));

    }

    public TaxValueResponse getTaxes(TaxValueRequest request) {
        logger.info("Creating webclient for call taxes service");
        WebClient client = WebClient.builder()
                .baseUrl("https://www.mockachino.com/428acb5c-9f6a-45")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .filter(errorHandler())
                .clientConnector(new ReactorClientHttpConnector(getHttpClientWithTimeout(timeout)))
                //    .defaultUriVariables(Collections.singletonMap("url", "http://localhost:8080"))
                .build();

        return client.get()
                .uri("/getTax")
                .retrieve()
                .bodyToMono(TaxValueResponse.class)
                .retryWhen(Retry.backoff(3, Duration.ofSeconds(2)).jitter(0.75)
                        .filter(throwable -> throwable instanceof TimeoutException)).block();

    }

    public static ExchangeFilterFunction errorHandler() {
        return ExchangeFilterFunction.ofResponseProcessor(clientResponse -> {
            if (clientResponse.statusCode().is5xxServerError()) {
                logger.error("error 5xx getting taxes from service");
                return clientResponse.bodyToMono(String.class)
                        .flatMap(errorBody -> Mono.error(new NoTaxServiceException(errorBody)));
            } else if (clientResponse.statusCode().is4xxClientError()) {
                logger.error("error 4xx getting taxes from service");
                return clientResponse.bodyToMono(String.class)
                        .flatMap(errorBody -> Mono.error(new NoTaxService400Exception(errorBody)));
            } else {
                logger.info("success getting taxes service");
                return Mono.just(clientResponse);
            }
        });
    }
}
