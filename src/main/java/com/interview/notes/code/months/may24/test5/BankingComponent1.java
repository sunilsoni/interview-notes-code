package com.interview.notes.code.months.may24.test5;

import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;

public class BankingComponent1 {
    private WebClient webClient;

    public BankingComponent1() {
        this.webClient = WebClient.create();
    }

    public static void main(String[] args) {
        BankingComponent bankingComponent = new BankingComponent();
        CompletableFuture<String> resultFuture = bankingComponent.validateData("someData");

        resultFuture.thenAccept(result -> System.out.println("Result: " + result))
                .exceptionally(ex -> {
                    System.err.println("Exception occurred: " + ex.getMessage());
                    return null;
                });
        // Wait for the result
        try {
            resultFuture.get(); // Blocking call to wait for the result
        } catch (Exception e) {
            System.err.println("Error occurred while waiting for result: " + e.getMessage());
        }
    }

    public CompletableFuture<String> validateData(String data) {
        CompletableFuture<String> future1 = webClient.get()
                .uri("API_ENDPOINT_1")
                .retrieve()
                .bodyToMono(String.class)
                .timeout(Duration.ofMillis(500))
                .toFuture();

        CompletableFuture<String> future2 = webClient.get()
                .uri("API_ENDPOINT_2")
                .retrieve()
                .bodyToMono(String.class)
                .timeout(Duration.ofMillis(500))
                .toFuture();

        return CompletableFuture.anyOf(future1, future2)
                .thenApply(response -> (String) response);
    }
}
