package com.interview.notes.code.year.y2025.may.common.test10;

import java.util.concurrent.CompletableFuture;

public class ThreadCommunicationExample {
    public static void main(String[] args) {
        CompletableFuture<Integer> additionFuture = CompletableFuture.supplyAsync(() -> add(10, 20));

        CompletableFuture<Integer> multiplicationFuture = additionFuture.thenApply(result -> multiply(result, 50));

        multiplicationFuture.thenAccept(finalResult -> System.out.println("Final Result: " + finalResult));

        // Wait for completion
        multiplicationFuture.join();
    }

    public static int add(int a, int b) {
        System.out.println("Adding " + a + " + " + b);
        return a + b;
    }

    public static int multiply(int num, int factor) {
        System.out.println("Multiplying " + num + " * " + factor);
        return num * factor;
    }
}
