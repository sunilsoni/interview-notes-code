package com.interview.notes.code.year.y2024.nov24.test3;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class FastestJavaURLFinder {

    private static final HttpClient client = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(10))
            .build();

    public static Optional<String> findFirstURLWithJava(List<String> urls) {
        return urls.stream()
                .map(url -> CompletableFuture.supplyAsync(() -> checkURL(url)))
                .map(CompletableFuture::join)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .findFirst();
    }

    private static Optional<String> checkURL(String url) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .timeout(Duration.ofSeconds(5))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());

            return response.body().toLowerCase().contains("java") ?
                    Optional.of(url) : Optional.empty();

        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public static void main(String[] args) {
        // Test Case 1: Basic test with sample URLs
        List<String> testUrls1 = Arrays.asList(
                "https://example.com",
                "https://example.org",
                "https://example.net"
        );

        System.out.println("Test Case 1: Basic URLs");
        testURLs(testUrls1);

        // Test Case 2: Empty list
        List<String> testUrls2 = Arrays.asList();
        System.out.println("\nTest Case 2: Empty list");
        testURLs(testUrls2);

        // Test Case 3: Invalid URLs
        List<String> testUrls3 = Arrays.asList(
                "invalid-url",
                "http://nonexistent.domain",
                "https://example.com"
        );
        System.out.println("\nTest Case 3: Invalid URLs");
        testURLs(testUrls3);

        // Test Case 4: Large dataset
        List<String> testUrls4 = generateLargeURLList(1000);
        System.out.println("\nTest Case 4: Large dataset (1000 URLs)");
        testURLs(testUrls4);
    }

    private static void testURLs(List<String> urls) {
        try {
            long startTime = System.currentTimeMillis();
            Optional<String> result = findFirstURLWithJava(urls);
            long endTime = System.currentTimeMillis();

            System.out.println("Result: " +
                    (result.isPresent() ? result.get() : "No URL found"));
            System.out.println("Execution time: " + (endTime - startTime) + "ms");
            System.out.println("Test: " + (result != null ? "PASS" : "FAIL"));
        } catch (Exception e) {
            System.out.println("Test: FAIL");
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static List<String> generateLargeURLList(int size) {
        return Arrays.stream(new String[size])
                .map(s -> "https://example.com")
                .collect(Collectors.toList());
    }
}