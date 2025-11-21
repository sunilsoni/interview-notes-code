package com.interview.notes.code.year.y2025.july.google.test2;

import java.util.*;

public class CustomerMessageRateLimiter {
    public static List<Boolean> processMessages(List<Triple<Integer, String, Integer>> messages, int timeLimit) {
        List<Boolean> results = new ArrayList<>();

        // Map to track last successful delivery time for each customer-message combination
        Map<String, Integer> lastDeliveryTime = new HashMap<>();

        for (Triple<Integer, String, Integer> message : messages) {
            int timestamp = message.first();
            String content = message.second();
            int customerId = message.third();

            // Create unique key for customer-message combination
            String key = customerId + ":" + content;

            boolean canDeliver = false;

            if (!lastDeliveryTime.containsKey(key)) {
                // First time this customer is sending this message
                canDeliver = true;
            } else {
                // Check if enough time has passed since last delivery
                int lastTime = lastDeliveryTime.get(key);
                canDeliver = (timestamp - lastTime > timeLimit);
            }

            if (canDeliver) {
                lastDeliveryTime.put(key, timestamp);
                results.add(true);
            } else {
                results.add(false);
            }
        }

        return results;
    }

    public static void main(String[] args) {
        // Test Case 1: Let's analyze each message
        List<Triple<Integer, String, Integer>> test1 = Arrays.asList(
                new Triple<>(1, "Hello", 5),  // true - first message from customer 5
                new Triple<>(2, "Hello", 5),  // false - within time limit for customer 5
                new Triple<>(2, "Hello", 4),  // true - first message from customer 4
                new Triple<>(5, "Hello", 3),  // true - first message from customer 3
                new Triple<>(6, "Hello", 5),  // false - still within time limit for customer 5
                new Triple<>(7, "Dog", 4),    // true - different message for customer 4
                new Triple<>(8, "hELLO", 5)   // true - case sensitive, different message
        );

        List<Boolean> expected1 = Arrays.asList(true, false, true, true, false, true, true);
        List<Boolean> result1 = processMessages(test1, 3);
        System.out.println("Test 1: " + (result1.equals(expected1) ? "PASS" : "FAIL"));
        System.out.println("Result: " + result1);

        // Additional test cases remain the same...
    }

    // Triple class remains the same
        record Triple<T, U, V>(T first, U second, V third) {
    }
}
