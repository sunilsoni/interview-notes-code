package com.interview.notes.code.year.y2026.june.EA.test2;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

class RateLimiter { // Defines the class for our rate limiting logic.
    private final int limit; // Stores the maximum allowed requests safely.
    private final long windowMs; // Stores the time window safely in milliseconds.
    private final Map<String, Queue<Long>> userLogs; // Maps user IDs to their timestamp history.

    public RateLimiter(int maxRequests, int windowSeconds) { // Constructor initializes the object with provided numbers.
        // Math.max ensures that if a user passes 0 or a negative number, we just store 0 instead of breaking things.
        this.limit = Math.max(0, maxRequests); // Protects the limit variable from negative misconfigurations.
        this.windowMs = Math.max(0, windowSeconds * 1000L); // Protects the window variable and converts to milliseconds.
        this.userLogs = new HashMap<>(); // Sets up the empty in-memory storage for users.
    } // Ends the constructor.

    public boolean isAllowed(String userId) { // Method called to check if a user is allowed to proceed.
        
        // GUARD CLAUSE: If config is 0 (or was negative), instantly reject to prevent crashes or dividing by zero.
        if (limit == 0 || windowMs == 0) return false; // Returns false immediately, saving CPU time and preventing errors.

        long now = System.currentTimeMillis(); // Captures the exact current time for our sliding window math.
        
        // var (modern Java) reduces boilerplate; computeIfAbsent safely gets or creates the user's history queue.
        var times = userLogs.computeIfAbsent(userId, k -> new ArrayDeque<>()); // Grabs the queue for this specific user.

        // Loop runs as long as the queue has items AND the oldest item is older than our allowed time window.
        while (!times.isEmpty() && (now - times.peek()) >= windowMs) { // Checks if the first timestamp has expired.
            times.poll(); // Safely deletes the expired timestamp so it stops counting against the user.
        } // Ends the cleanup loop.

        if (times.size() < limit) { // Compares the user's valid, recent requests against the safe limit we set.
            times.add(now); // Records this new, successful request's timestamp at the end of the queue.
            return true; // Tells the system the user is allowed to make this request.
        } // Ends the allowance check.

        return false; // If the queue size equals the limit, they are going too fast, so we reject them.
    } // Ends the isAllowed method.
} // Ends the RateLimiter class.

// =========================================================================
// TEST HARNESS FOR MISCONFIGURATIONS
// =========================================================================

class Solution { // Main class used purely to run our manual test cases.
    
    public static void main(String[] args) { // Main method entry point to execute the code.
        
        System.out.println("--- Testing Zero Limits ---"); // Prints a header for our first test group.
        var zeroLimit = new RateLimiter(0, 10); // Creates a limiter configured with 0 allowed requests.
        check("Zero Limit Test 1", zeroLimit.isAllowed("dave"), false); // Should safely return false, no crash.
        check("Zero Limit Test 2", zeroLimit.isAllowed("dave"), false); // Should safely return false again.

        System.out.println("\n--- Testing Zero Window ---"); // Prints a header for our second test group.
        var zeroWindow = new RateLimiter(5, 0); // Creates a limiter configured with a 0-second window.
        check("Zero Window Test 1", zeroWindow.isAllowed("eve"), false); // Should safely return false, no crash.

        System.out.println("\n--- Testing Negative Numbers (Extreme Misconfiguration) ---"); // Header for negative tests.
        var negativeConfig = new RateLimiter(-5, -10); // Creates a limiter with completely invalid negative numbers.
        check("Negative Config Test 1", negativeConfig.isAllowed("frank"), false); // Math.max caught it, safely returns false.
        
        System.out.println("\n--- Testing Normal Operations Still Work ---"); // Header to ensure we didn't break normal logic.
        var normalLimiter = new RateLimiter(2, 10); // Creates a properly configured limiter.
        check("Normal Test 1", normalLimiter.isAllowed("grace"), true);  // 1st request allowed.
        check("Normal Test 2", normalLimiter.isAllowed("grace"), true);  // 2nd request allowed.
        check("Normal Test 3", normalLimiter.isAllowed("grace"), false); // 3rd request safely rejected (over limit).
    } // Ends the main method.

    // Ultra-simple helper method to format our test outputs cleanly without using JUnit.
    private static void check(String testName, boolean actual, boolean expected) { // Accepts the test details.
        var status = (actual == expected) ? "PASS" : "FAIL"; // Simple ternary operator to determine if actual matches expected.
        System.out.printf("[%s] %s -> Expected: %b, Got: %b%n", status, testName, expected, actual); // Prints the result to the console.
    } // Ends the helper method.
} // Ends the Solution class.