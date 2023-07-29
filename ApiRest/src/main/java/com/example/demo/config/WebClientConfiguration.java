package com.example.demo.config;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.TimeoutException;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import reactor.netty.tcp.TcpClient;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Configuration
public class WebClientConfiguration {

    @Value("${webclient.timeout}")
    private int timeout;
    private static HttpClient getHttpClientWithTimeout(int seconds) {
        return  HttpClient.create().responseTimeout(Duration.ofSeconds(seconds));

    }

    @Bean//("service-a-web-client")
    public WebClient webClientBuilder() {
        return WebClient.builder()

                .clientConnector(new ReactorClientHttpConnector(getHttpClientWithTimeout(timeout)))
                .filter(buildRetryExchangeFilterFunction())
                .build();
    }

    private ExchangeFilterFunction buildRetryExchangeFilterFunction() {
        return (request, next) -> next.exchange(request)
                .flatMap(clientResponse -> Mono.just(clientResponse)
                .filter(response -> clientResponse.statusCode().isError())
                .flatMap(response -> clientResponse.createException())
                .flatMap(Mono::error)
                .thenReturn(clientResponse))
                .retryWhen( Retry.backoff(3, Duration.ofSeconds(2)).jitter(0.75)
                        .filter(throwable -> throwable instanceof TimeoutException));
    }
}
