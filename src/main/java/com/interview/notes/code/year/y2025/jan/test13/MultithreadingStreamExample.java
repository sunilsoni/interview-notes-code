package com.interview.notes.code.year.y2025.jan.test13;

import java.util.Arrays;
import java.util.List;

public class MultithreadingStreamExample {

    // Method to process strings in parallel
    public static void processStrings(List<String> strings) {
        strings.parallelStream().forEach(str -> {
            String threadName = Thread.currentThread().getName();
            System.out.println("Processing " + str + " in thread " + threadName);
        });
    }

    public static void main(String[] args) {
        List<String> data = Arrays.asList("Alpha", "Bravo", "Charlie", "Delta", "Echo", "Foxtrot", "Golf", "Hotel", "India");

        // Splitting the list into 3 parts to simulate 3 threads
        List<String> part1 = data.subList(0, 3);
        List<String> part2 = data.subList(3, 6);
        List<String> part3 = data.subList(6, 9);

        Thread thread1 = new Thread(() -> processStrings(part1));
        Thread thread2 = new Thread(() -> processStrings(part2));
        Thread thread3 = new Thread(() -> processStrings(part3));

        thread1.start();
        thread2.start();
        thread3.start();

        try {
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {
            System.err.println("Thread interrupted: " + e.getMessage());
        }

        System.out.println("All threads have finished processing.");
    }
}

/*
Problem Analysis:
- This program processes a list of strings using 3 separate threads with Java 8 parallel streams.
- It demonstrates multi-threading with efficient division of tasks.

Solution Design:
- Splits the list into 3 parts and processes each with a dedicated thread.
- Uses parallel streams within each thread for additional concurrency.

Edge Cases Considered:
- Processing an empty list.
- Handling small datasets with fewer than 3 items.

Testing:
- The main method creates and runs threads to process parts of the list.
- Output shows which thread processes each string.
*/
