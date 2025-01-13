package com.interview.notes.code.year.y2025.jan24.test1;

import java.time.Duration;
import java.time.Instant;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RateLimiter {

    // To store requests per user
    private final Map<String, Deque<Instant>> userRequests = new ConcurrentHashMap<>();
    private final int MAX_REQUESTS = 5;
    private final Duration TIME_WINDOW = Duration.ofMinutes(1);

    // Method to process requests
    public boolean isRequestAllowed(String userId) {
        Instant now = Instant.now();
        userRequests.putIfAbsent(userId, new LinkedList<>());
        Deque<Instant> requests = userRequests.get(userId);

        // Remove requests that are older than the time window (1 minute)
        while (!requests.isEmpty() && Duration.between(requests.peek(), now).compareTo(TIME_WINDOW) > 0) {
            requests.poll();
        }

        // Check if the user has exceeded the request limit
        if (requests.size() >= MAX_REQUESTS) {
            return false; // Reject request
        }

        // Add the current request timestamp
        requests.offer(now);
        return true; // Allow request
    }

    // Example usage method (could be part of a service class)
    public void handleRequest(String userId) {
        if (isRequestAllowed(userId)) {
            System.out.println("Request allowed for user: " + userId);
            // Proceed with request processing...
        } else {
            System.out.println("Request rejected for user: " + userId);
        }
    }
}
