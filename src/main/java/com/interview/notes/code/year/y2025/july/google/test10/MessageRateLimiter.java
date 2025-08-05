package com.interview.notes.code.year.y2025.july.google.test10;

import java.util.*;

public class MessageRateLimiter {
    // Method to process messages and determine if they can be delivered based on rate limiting
    public static List<Boolean> processMessages(List<Pair<Integer, String>> messages, int timeLimit) {
        // Store results for each message (delivered or blocked)
        List<Boolean> results = new ArrayList<>();
        
        // Map to track last successful delivery time for each message content
        Map<String, Integer> lastDeliveryTime = new HashMap<>();
        
        // Process each message in the input list
        for (Pair<Integer, String> message : messages) {
            int currentTime = message.getFirst();
            String content = message.getSecond();
            
            // Check if this is first occurrence or enough time has passed since last delivery
            if (!lastDeliveryTime.containsKey(content) || 
                (currentTime - lastDeliveryTime.get(content) > timeLimit)) {
                // Message can be delivered - update last delivery time and add true to results
                lastDeliveryTime.put(content, currentTime);
                results.add(true);
            } else {
                // Message is blocked - add false to results
                results.add(false);
            }
        }
        
        return results;
    }

    // Simple pair class to hold timestamp and message content
    static class Pair<T, U> {
        private final T first;
        private final U second;

        public Pair(T first, U second) {
            this.first = first;
            this.second = second;
        }

        public T getFirst() { return first; }
        public U getSecond() { return second; }
    }

    // Main method for testing
    public static void main(String[] args) {
        // Test Case 1: Basic test
        List<Pair<Integer, String>> test1 = Arrays.asList(
            new Pair<>(1, "Hello"),
            new Pair<>(2, "Goodbye"),
            new Pair<>(4, "Hello"),
            new Pair<>(5, "Hello"),
            new Pair<>(6, "Hello")
        );
        List<Boolean> expected1 = Arrays.asList(true, true, false, true, false);
        List<Boolean> result1 = processMessages(test1, 3);
        System.out.println("Test 1: " + (result1.equals(expected1) ? "PASS" : "FAIL"));

        // Test Case 2: Large gap between messages
        List<Pair<Integer, String>> test2 = Arrays.asList(
            new Pair<>(1, "Test"),
            new Pair<>(10, "Test"),
            new Pair<>(20, "Test")
        );
        List<Boolean> expected2 = Arrays.asList(true, true, true);
        List<Boolean> result2 = processMessages(test2, 5);
        System.out.println("Test 2: " + (result2.equals(expected2) ? "PASS" : "FAIL"));

        // Test Case 3: Case sensitivity test
        List<Pair<Integer, String>> test3 = Arrays.asList(
            new Pair<>(1, "Hello"),
            new Pair<>(2, "hello"),
            new Pair<>(3, "HELLO")
        );
        List<Boolean> expected3 = Arrays.asList(true, true, true);
        List<Boolean> result3 = processMessages(test3, 1);
        System.out.println("Test 3: " + (result3.equals(expected3) ? "PASS" : "FAIL"));

        // Test Case 4: Large dataset simulation
        List<Pair<Integer, String>> test4 = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            test4.add(new Pair<>(i, "Message" + (i % 100)));
        }
        List<Boolean> result4 = processMessages(test4, 5);
        System.out.println("Test 4 (Large Dataset): Processed " + result4.size() + " messages");
    }
}
