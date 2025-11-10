package com.interview.notes.code.year.y2025.november.common.test2;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CharFrequencyAsync {

    // This method returns a CompletableFuture that computes character frequency asynchronously
    public static CompletableFuture<Map<Character, Long>> getCharFrequencyAsync(String input, Executor executor) {
        return CompletableFuture.supplyAsync(() -> {
            // Convert string to character stream, group by character, and count occurrences
            return input.chars()
                    .mapToObj(c -> (char) c)
                    .collect(Collectors.groupingBy(
                            Function.identity(),
                            Collectors.counting()
                    ));
        }, executor);
    }

    public static void main(String[] args) throws Exception {
        // Create a thread pool for async execution
        ExecutorService executor = Executors.newFixedThreadPool(2);

        // Input string
        String input = "hello";

        // Call the async method
        CompletableFuture<Map<Character, Long>> future = getCharFrequencyAsync(input, executor);

        // Block and get the result synchronously
        Map<Character, Long> result = future.get();

        // Print the result
        System.out.println("Character Frequencies: " + result);

        // Shutdown executor
        executor.shutdown();
    }
}
