package com.interview.notes.code.year.y2026.july.oracle.test3;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class AiCopilotRateLimiter { // Declares the main public class that contains our rate limiter logic.
    
    private final Map<String, UserData> userMap = new HashMap<>(); // Maps unique user IDs (Strings) to their corresponding usage tracking data.
    
    public static void main(String[] args) { // Simple main method used as a test runner instead of using JUnit.
        AiCopilotRateLimiter limiter = new AiCopilotRateLimiter(); // Instantiates a new rate limiter object for our tests.

        // Kept only one existing example for testing as requested.
        boolean t1 = limiter.allowRequest(1, "alice", 30000); // Test 1: Alice requests 30k at time 1. Expected: Accept (true).
        System.out.println("Test 1 (Initial Accept): " + (t1 ? "PASS" : "FAIL")); // Prints PASS if t1 is true, otherwise FAIL.
    } // Ends the main method.

    public boolean allowRequest(long timestamp, String userId, long tokens) { // Core method to determine if an incoming request is accepted or rejected.
        if (tokens > 100_000) return false; // Immediate optimization: reject the request if the single request itself exceeds the total maximum window limit.

        UserData data = userMap.computeIfAbsent(userId, k -> new UserData()); // Java 8 Map API: Gets the user's data, or creates a fresh UserData object if they are new.

        while (!data.requests.isEmpty() && data.requests.peek().timestamp() <= timestamp - 60) { // Loops to clean up the queue: checks if the oldest request is 60 or more seconds older than current time.
            data.totalTokens -= data.requests.poll().tokens(); // Removes the expired request from the queue and deducts its tokens from the user's active total.
        } // Ends the eviction while-loop.

        if (data.totalTokens + tokens <= 100_000) { // Evaluates if the user's current token total plus the requested tokens stays within the 100,000 limit.
            data.requests.offer(new Request(timestamp, tokens)); // If valid, adds this new request to the back of the queue for future tracking.
            data.totalTokens += tokens; // Updates the user's total token count to include this accepted request.
            return true; // Returns true indicating the request complies with the limits and is approved.
        } // Ends the acceptance block.

        return false; // If the limit check fails, returns false to reject the request.
    } // Ends the allowRequest method.

    private record Request(long timestamp, long tokens) {} // Uses Java 21 Record to create a highly concise, immutable object for storing request data.

    private static class UserData { // Helper class to encapsulate the state of a specific user's window.
        long totalTokens = 0; // Tracks the current active sum of tokens this user has consumed in the last 60 seconds.
        Queue<Request> requests = new ArrayDeque<>(); // Queue to store accepted requests sequentially, enabling fast eviction of old requests.
    } // Ends the UserData class block.
} // Ends the entire class definition.