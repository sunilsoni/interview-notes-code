package com.interview.notes.code.months.may24.test5;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class BankingComponent {

    // Simulated API endpoints
    private static final String API_ENDPOINT_1 = "https://api.endpoint1.com";
    private static final String API_ENDPOINT_2 = "https://api.endpoint2.com";
    // Executor for running CompletableFuture tasks
    private final Executor executor = Executors.newFixedThreadPool(2); // Using a fixed thread pool with 2 threads

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
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> fetchDataFromAPI(API_ENDPOINT_1), executor);
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> fetchDataFromAPI(API_ENDPOINT_2), executor);

        return CompletableFuture.anyOf(future1, future2)
                .thenApply(response -> (String) response);
    }

    private String fetchDataFromAPI(String endpoint) {
        // Simulating API call, replace this with actual API call using HttpClient or any other HTTP client
        try {
            Thread.sleep(200); // Simulating API call delay
            return "Response from " + endpoint;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return "Error: Interrupted while fetching data from " + endpoint;
        }
    }
}
