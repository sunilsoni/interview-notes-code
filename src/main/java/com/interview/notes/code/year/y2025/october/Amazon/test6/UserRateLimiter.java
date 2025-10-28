package com.interview.notes.code.year.y2025.october.Amazon.test6;

import java.time.Instant;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class UserRateLimiter {
    // Store user requests with timestamps using ConcurrentHashMap for thread safety
    private final Map<String, Queue<Long>> userRequestTimestamps;

    // Maximum allowed requests per window
    private final int maxRequests;

    // Time window in seconds
    private final int timeWindowSeconds;

    // Constructor to initialize rate limiter with configuration
    public UserRateLimiter(int maxRequests, int timeWindowSeconds) {
        this.userRequestTimestamps = new ConcurrentHashMap<>();
        this.maxRequests = maxRequests;
        this.timeWindowSeconds = timeWindowSeconds;
    }

    public static void main(String[] args) {
        // Test Case 1: Basic functionality
        UserRateLimiter limiter = new UserRateLimiter(3, 60);
        String userId = "user1";

        System.out.println("Test Case 1: Basic functionality");
        System.out.println("Request 1: " + limiter.isAllowed(userId)); // Should be true
        System.out.println("Request 2: " + limiter.isAllowed(userId)); // Should be true
        System.out.println("Request 3: " + limiter.isAllowed(userId)); // Should be true
        System.out.println("Request 4: " + limiter.isAllowed(userId)); // Should be false

        // Test Case 2: Multiple users
        System.out.println("\nTest Case 2: Multiple users");
        String user2 = "user2";
        System.out.println("User2 Request 1: " + limiter.isAllowed(user2)); // Should be true
        System.out.println("User1 Request 5: " + limiter.isAllowed(userId)); // Should be false

        // Test Case 3: Time window test (need to wait)
        System.out.println("\nTest Case 3: Time window test");
        try {
            System.out.println("Waiting for 61 seconds...");
            Thread.sleep(61000); // Wait for window to expire
            System.out.println("After wait, User1 Request: " +
                    limiter.isAllowed(userId)); // Should be true
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Test Case 4: Large number of requests
        System.out.println("\nTest Case 4: Large number of requests");
        UserRateLimiter largeLimiter = new UserRateLimiter(1000, 1);
        String bulkUser = "bulkUser";
        boolean allPassed = true;

        // Test 1000 requests
        for (int i = 0; i < 1000; i++) {
            if (!largeLimiter.isAllowed(bulkUser)) {
                allPassed = false;
                break;
            }
        }
        System.out.println("1000 requests test passed: " + allPassed);
        System.out.println("1001st request (should be false): " +
                largeLimiter.isAllowed(bulkUser));
    }

    // Method to check if request is allowed for a user
    public boolean isAllowed(String userId) {
        long currentTime = Instant.now().getEpochSecond();

        // Get or create queue for user
        Queue<Long> timestamps = userRequestTimestamps.computeIfAbsent(userId,
                k -> new LinkedList<>());

        // Remove timestamps outside the window using Java 8 streams
        timestamps = timestamps.stream()
                .filter(timestamp -> timestamp > currentTime - timeWindowSeconds)
                .collect(Collectors.toCollection(LinkedList::new));

        // Update the queue
        userRequestTimestamps.put(userId, timestamps);

        // Check if request can be allowed
        if (timestamps.size() < maxRequests) {
            timestamps.add(currentTime);
            return true;
        }

        return false;
    }
}
