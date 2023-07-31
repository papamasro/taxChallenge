package com.example.demo.config;

import com.example.demo.exception.NoTaxService400Exception;
import com.example.demo.exception.NoTaxService500Exception;
import io.netty.handler.timeout.TimeoutException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import reactor.util.retry.Retry;

import java.time.Duration;

@Configuration
public class WebClientConfiguration {

    @Value("${webclient.timeout}")
    private int timeout;

    @Value("${mock.url}")
    private String url;

    private static ExchangeFilterFunction errorHandler() {
        return ExchangeFilterFunction.ofResponseProcessor(clientResponse -> {
            if (clientResponse.statusCode().is5xxServerError()) {
                return clientResponse.bodyToMono(String.class)
                        .flatMap(errorBody -> Mono.error(new NoTaxService500Exception(errorBody)));
            } else if (clientResponse.statusCode().is4xxClientError()) {
                return clientResponse.bodyToMono(String.class)
                        .flatMap(errorBody -> Mono.error(new NoTaxService400Exception(errorBody)));
            } else {
                return Mono.just(clientResponse);
            }
        });
    }

    @Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder()
                .baseUrl(url)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .filter(errorHandler())
                .clientConnector(new ReactorClientHttpConnector(getHttpClientWithTimeout(timeout)));
    }

    private static HttpClient getHttpClientWithTimeout(int seconds) {
        return HttpClient.create().responseTimeout(Duration.ofSeconds(seconds));
    }
}
