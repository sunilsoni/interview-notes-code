package com.interview.notes.code.year.y2025.july.common.test1;

import java.util.concurrent.CompletableFuture;

public class HelloWorldFlow {
    public static void main(String[] args) {
        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> "Hello");
        CompletableFuture<String> world = CompletableFuture.supplyAsync(() -> "World");

        // Combine both and trigger final logic
        hello.thenCombine(world, (h, w) -> h + " " + w)
                .thenAccept(result -> {
                    System.out.println(result);       // Prints: Hello World
                    System.out.println("Completed");  // Prints after both are done
                });
    }
}


