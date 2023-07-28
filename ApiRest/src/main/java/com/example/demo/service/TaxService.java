package com.example.demo.service;

import com.example.demo.model.external.ChargeRequest;
import com.example.demo.model.external.ChargeResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Collections;

@Service
public class TaxService {

   // private WebClient webClientBuilder;

    public ChargeResponse getTaxes(ChargeRequest request) {

        WebClient client = WebClient.builder()
                .baseUrl("https://www.mockachino.com/428acb5c-9f6a-45")
                .defaultCookie("cookieKey", "cookieValue")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
               //    .defaultUriVariables(Collections.singletonMap("url", "http://localhost:8080"))
                .build();

        return client.get()
                .uri("/getTax")
                .retrieve()
                .bodyToMono(ChargeResponse.class).block();



    }
}
