package com.interview.notes.code.year.y2026.july.oracle.test2;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class AiCopilotRateLimiter { // Declares the main public class that contains our rate limiter logic.
    
    private final Map<String, UserData> userMap = new HashMap<>(); // Maps unique user IDs (Strings) to their corresponding usage tracking data.
    
    public static void main(String[] args) { // Simple main method used as a test runner instead of using JUnit.
        AiCopilotRateLimiter limiter = new AiCopilotRateLimiter(); // Instantiates a new rate limiter object for our tests.

        // Standard test cases provided in the prompt images
        boolean t1 = limiter.allowRequest(1, "alice", 30000); // Test 1: Alice requests 30k at time 1. Expected: Accept (true).
        System.out.println("Test 1 (Initial Accept): " + (t1 ? "PASS" : "FAIL")); // Prints PASS if t1 is true, otherwise FAIL.

        boolean t2 = limiter.allowRequest(10, "alice", 40000); // Test 2: Alice requests 40k at time 10 (Total: 70k). Expected: Accept (true).
        System.out.println("Test 2 (Second Accept): " + (t2 ? "PASS" : "FAIL")); // Prints PASS if t2 is true, otherwise FAIL.

        boolean t3 = limiter.allowRequest(30, "alice", 40000); // Test 3: Alice requests 40k at time 30. (Total would be 110k). Expected: Reject (false).
        System.out.println("Test 3 (Reject Over Limit): " + (!t3 ? "PASS" : "FAIL")); // Prints PASS if t3 is false (correctly rejected).

        boolean t4 = limiter.allowRequest(62, "alice", 40000); // Test 4: Alice requests 40k at time 62. Time 1 expires (-30k). Total goes to 40k. 40k+40k=80k. Expected: Accept (true).
        System.out.println("Test 4 (Accept After Expiry): " + (t4 ? "PASS" : "FAIL")); // Prints PASS if t4 is true, showing the window shifted correctly.

        // Large Data / Edge Case Handling
        boolean largePass = true; // Flag to monitor if our large volume test remains stable and accurate.
        for (long i = 100; i <= 10000; i++) { // Loops 9,900 times representing rapid-fire requests over time.
            if (!limiter.allowRequest(i, "bob", 10)) largePass = false; // Bob asks for 10 tokens per second. Max in 60s is 600, well below 100k limit. All should pass.
        } // Ends the high-volume data injection loop.
        System.out.println("Test 5 (High Volume Sequential): " + (largePass ? "PASS" : "FAIL")); // Prints PASS if all 9,900 micro-requests were accepted.

        boolean t6 = limiter.allowRequest(10001, "bob", 99500); // Test 6: Bob requests a massive spike at the end. Active 60s window holds 600 tokens. 600 + 99500 = 100,100. Expected: Reject (false).
        System.out.println("Test 6 (High Volume Edge Reject): " + (!t6 ? "PASS" : "FAIL")); // Prints PASS if this massive edge-case request is safely rejected.
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