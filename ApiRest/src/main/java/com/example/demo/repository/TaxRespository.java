package com.example.demo.repository;

import com.example.demo.model.external.ChargeRequest;
import com.example.demo.model.external.ChargeResponse;
import org.springframework.web.reactive.function.client.WebClient;


public class TaxRespository {

    private WebClient webClientBuilder;

    public ChargeResponse getTaxes(ChargeRequest request) {
        WebClient.create("https://www.mockachino.com/428acb5c-9f6a-45/");
        return webClientBuilder.get()
                .uri("/getTax/{name}", request.getTaxesName())
                .retrieve()
                .bodyToMono(ChargeResponse.class).block();

    }
}
