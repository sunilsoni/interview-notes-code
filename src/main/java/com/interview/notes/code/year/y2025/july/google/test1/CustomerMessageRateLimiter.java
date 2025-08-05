package com.interview.notes.code.year.y2025.july.google.test1;

import java.util.*;

public class CustomerMessageRateLimiter {
    // Modified to include customer ID in the message tuple
    public static List<Boolean> processMessages(List<Triple<Integer, String, Integer>> messages, int timeLimit) {
        List<Boolean> results = new ArrayList<>();
        
        // Modified map to track last delivery time for each customer-message combination
        Map<String, Integer> lastDeliveryTime = new HashMap<>();
        
        for (Triple<Integer, String, Integer> message : messages) {
            int currentTime = message.getFirst();      // timestamp
            String content = message.getSecond();      // message content
            int customerId = message.getThird();       // customer ID
            
            // Create unique key combining customer ID and message content
            String key = customerId + ":" + content;
            
            // Check if this is first message for this customer-content combination
            // or enough time has passed since last delivery
            if (!lastDeliveryTime.containsKey(key) || 
                (currentTime - lastDeliveryTime.get(key) > timeLimit)) {
                lastDeliveryTime.put(key, currentTime);
                results.add(true);
            } else {
                results.add(false);
            }
        }
        
        return results;
    }

    // Triple class to hold timestamp, message content, and customer ID
    static class Triple<T, U, V> {
        private final T first;
        private final U second;
        private final V third;

        public Triple(T first, U second, V third) {
            this.first = first;
            this.second = second;
            this.third = third;
        }

        public T getFirst() { return first; }
        public U getSecond() { return second; }
        public V getThird() { return third; }
    }

    public static void main(String[] args) {
        // Test Case 1: Messages with same timestamp, different customers
        List<Triple<Integer, String, Integer>> test1 = Arrays.asList(
            new Triple<>(1, "Hello", 5),
            new Triple<>(2, "Hello", 5),
            new Triple<>(2, "Hello", 4),
            new Triple<>(5, "Hello", 3),
            new Triple<>(6, "Hello", 5),
            new Triple<>(7, "Dog", 4),
            new Triple<>(8, "hELLO", 5)
        );
        
        List<Boolean> expected1 = Arrays.asList(true, false, true, true, false, true, true);
        List<Boolean> result1 = processMessages(test1, 3);
        System.out.println("Test 1: " + (result1.equals(expected1) ? "PASS" : "FAIL"));
        System.out.println("Result: " + result1);
        
        // Test Case 2: Multiple customers with same messages at same time
        List<Triple<Integer, String, Integer>> test2 = Arrays.asList(
            new Triple<>(1, "Test", 1),
            new Triple<>(1, "Test", 2),
            new Triple<>(1, "Test", 3),
            new Triple<>(2, "Test", 1)
        );
        List<Boolean> result2 = processMessages(test2, 2);
        System.out.println("Test 2 Result: " + result2);  // Should be [true, true, true, false]

        // Test Case 3: Large dataset with multiple customers
        List<Triple<Integer, String, Integer>> test3 = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            test3.add(new Triple<>(i, "Message" + (i % 100), i % 10));  // 10 different customers
        }
        List<Boolean> result3 = processMessages(test3, 5);
        System.out.println("Test 3 (Large Dataset): Processed " + result3.size() + " messages");
    }
}
