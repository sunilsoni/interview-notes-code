package com.interview.notes.code.year.y2024.sept24.test14;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class ParallelApiService {

    private final WebClient webClient;

    public ParallelApiService(WebClient webClient) {
        this.webClient = webClient;
    }

    // Method to call API1
    public Mono<String> callApi1() {
        return webClient.get()
                .uri("http://localhost:8081/api1")
                .retrieve()
                .bodyToMono(String.class);
    }

    // Method to call API2
    public Mono<String> callApi2() {
        return webClient.get()
                .uri("http://localhost:8082/api2")
                .retrieve()
                .bodyToMono(String.class);
    }

    // Method to call both APIs in parallel and combine their results
    public Mono<String> callApisInParallelAndCombine() {
        Mono<String> api1Response = callApi1();
        Mono<String> api2Response = callApi2();

        // Combine both responses using Mono.zip
        return Mono.zip(api1Response, api2Response)
                .map(tuple -> {
                    String response1 = tuple.getT1();
                    String response2 = tuple.getT2();
                    return "Combined Response: " + response1 + " & " + response2;
                });
    }
}
