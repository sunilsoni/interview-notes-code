package com.interview.notes.code.year.y2026.july.assessments.test1;

public class VirtualThreadDemo {
    public static void main(String[] args) {
        // Create a virtual thread directly
        Thread.startVirtualThread(() -> {
            System.out.println("Hello from a virtual thread!");
        });

        // Using an executor with virtual threads
        try (var executor = java.util.concurrent.Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 1; i <= 5; i++) {
                int taskId = i;
                executor.submit(() -> {
                    System.out.println("Task " + taskId + " running on " + Thread.currentThread());
                });
            }
        }
    }
}
