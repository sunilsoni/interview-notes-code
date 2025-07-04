package com.interview.notes.code.year.y2025.June.common.test9;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ConcurrentHashMapExample {

    private static final ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        // Initialize the map with some values
        map.put("A", 1);
        map.put("B", 2);
        map.put("C", 3);

        // Create a thread pool
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        // Writer thread
        Runnable writer = () -> {
            for (int i = 0; i < 10; i++) {
                String key = "Key" + i;
                map.put(key, i);
                System.out.println(Thread.currentThread().getName() + " Writing: " + key + " -> " + i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        };

        // Reader thread
        Runnable reader = () -> {
            for (int i = 0; i < 10; i++) {
                map.forEach((key, value) -> {
                    System.out.println(Thread.currentThread().getName() + " Reading: " + key + " -> " + value);
                });
                try {
                    Thread.sleep(150);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        };

        // Updater thread
        Runnable updater = () -> {
            for (int i = 0; i < 10; i++) {
                map.computeIfPresent("A", (key, value) -> value + 1);
                System.out.println(Thread.currentThread().getName() + " Updating A: " + map.get("A"));
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        };

        // Submit tasks to executor
        executorService.submit(writer);
        executorService.submit(reader);
        executorService.submit(updater);

        // Shutdown executor
        executorService.shutdown();
        try {
            executorService.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Print final map contents
        System.out.println("\nFinal Map Contents:");
        map.forEach((key, value) -> System.out.println(key + " -> " + value));
    }
}
