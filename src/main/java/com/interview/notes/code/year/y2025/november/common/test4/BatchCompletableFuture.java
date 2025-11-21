package com.interview.notes.code.year.y2025.november.common.test4;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BatchCompletableFuture {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        List<String> documents = IntStream.rangeClosed(1, 10)
                .mapToObj(i -> "Document_" + i)
                .collect(Collectors.toList());

        int batchSize = 5;
        ExecutorService executor = Executors.newFixedThreadPool(5);

        for (int i = 0; i < documents.size(); i += batchSize) {
            int end = Math.min(i + batchSize, documents.size());
            List<String> batch = documents.subList(i, end);

            System.out.println("Processing batch: " + batch);

            List<CompletableFuture<Void>> futures = batch.stream()
                    .map(doc -> CompletableFuture.runAsync(() -> {
                        process(doc);
                        delete(doc);
                    }, executor))
                    .collect(Collectors.toList());

            // Wait for all in batch to complete
            CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();

            System.out.println("Batch completed.\n");
        }

        executor.shutdown();
    }

    private static void process(String doc) {
        System.out.println("Processed: " + doc + " by " + Thread.currentThread().getName());
        sleep(500); // Simulate delay
    }

    private static void delete(String doc) {
        System.out.println("Deleted: " + doc + " by " + Thread.currentThread().getName());
    }

    private static void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ignored) {
        }
    }
}
