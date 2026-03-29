package com.example;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class ApiService {

    private final WebClient webClient;

    public ApiService(WebClient webClient) {
        this.webClient = webClient;
    }

    public String callApi() {
        return webClient.get()
                .uri("https://localhost:8443/hello")
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}