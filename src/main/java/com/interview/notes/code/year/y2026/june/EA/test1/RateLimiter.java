package com.interview.notes.code.year.y2026.june.EA.test1;

import java.util.*; // Imports necessary utility classes like Map, HashMap, Queue, ArrayDeque, and List.

class RateLimiter { // Defines the blueprint for our rate limiting logic.
    private final int limit; // Stores the maximum allowed requests per window.
    private final long windowMs; // Stores the sliding window size in milliseconds for exact time calculation.
    private final Map<String, Queue<Long>> userLogs; // Maps a user ID string to a queue of their request timestamps.

    public RateLimiter(int maxRequests, int windowSeconds) { // Constructor to set up the limits when creating an instance.
        this.limit = maxRequests; // Assigns the passed maximum requests to our instance variable.
        this.windowMs = windowSeconds * 1000L; // Converts the seconds to milliseconds so it matches System.currentTimeMillis().
        this.userLogs = new HashMap<>(); // Initializes the HashMap to store our in-memory data.
    } // Ends the constructor.

    public boolean isAllowed(String userId) { // Method called every time a user attempts an action.
        long now = System.currentTimeMillis(); // Grabs the exact current time in milliseconds.
        
        // Uses Java's computeIfAbsent to gracefully get the user's history, or create an ArrayDeque if they are new.
        var times = userLogs.computeIfAbsent(userId, k -> new ArrayDeque<>()); 

        // Loop checks if the queue is not empty AND if the oldest request (at the front) is outside our sliding time window.
        while (!times.isEmpty() && (now - times.peek()) >= windowMs) { 
            times.poll(); // Removes the outdated timestamp from the queue because it no longer counts against the limit.
        } // Ends the cleanup loop.

        if (times.size() < limit) { // Checks if the user's current valid requests are strictly less than the allowed limit.
            times.add(now); // Appends the current timestamp to the end of the queue since the request is approved.
            return true; // Returns true to inform the caller that the action is allowed.
        } // Ends the condition for an allowed request.

        return false; // Returns false because the queue size equals the limit, meaning the user is acting too fast.
    } // Ends the isAllowed method.
} // Ends the RateLimiter class.

// =========================================================================
// TEST HARNESS
// =========================================================================

class Solution { // The main class used to execute our testing logic.
    
    public static void main(String[] args) throws InterruptedException { // Main entry point, declares InterruptedException because we use Thread.sleep to simulate time passing.
        var results = new ArrayList<TestResult>(); // Initializes a list to collect the outcome of every test case.

        // We use a 1-second window here so the tests run quickly without waiting 10 seconds.
        RateLimiter limiter = new RateLimiter(3, 1); // Creates a limiter that allows 3 requests every 1 second.

        System.out.println("Running tests...\n"); // Prints a startup message to the console.

        // --- Standard Flow Tests (Similar to example) ---
        boolean r1 = limiter.isAllowed("alice"); // Alice's 1st request.
        results.add(new TestResult("Alice Req 1 (Expect True)", r1)); // Should be true, records result.

        boolean r2 = limiter.isAllowed("alice"); // Alice's 2nd request.
        results.add(new TestResult("Alice Req 2 (Expect True)", r2)); // Should be true, records result.

        boolean r3 = limiter.isAllowed("alice"); // Alice's 3rd request.
        results.add(new TestResult("Alice Req 3 (Expect True)", r3)); // Should be true, records result.

        boolean r4 = limiter.isAllowed("alice"); // Alice's 4th request.
        results.add(new TestResult("Alice Req 4 (Expect False)", !r4)); // Should be false (limit exceeded), records result.

        // --- Multi-User Edge Case ---
        boolean bob1 = limiter.isAllowed("bob"); // Bob's 1st request. Alice's limit should not affect Bob.
        results.add(new TestResult("Bob Req 1 (Expect True)", bob1)); // Should be true, records result.

        // --- Time Expiration Test ---
        Thread.sleep(1100); // Pauses execution for 1.1 seconds to force the sliding window to reset.
        boolean r5 = limiter.isAllowed("alice"); // Alice's 5th request, after the 1-second window has passed.
        results.add(new TestResult("Alice Req 5 After Delay (Expect True)", r5)); // Should be true because old requests expired.

        // --- Large Data Input Case ---
        RateLimiter heavyLimiter = new RateLimiter(5000, 10); // Creates a new limiter allowing 5000 requests per 10 seconds.
        int successfulRequests = 0; // Counter to track how many requests are allowed in our high-volume loop.
        for (int i = 0; i < 6000; i++) { // Loops 6000 times instantly to simulate a burst or DDoS attempt.
            if (heavyLimiter.isAllowed("charlie")) { // Attempts a request for user 'charlie'.
                successfulRequests++; // Increments if allowed.
            } // Ends the condition.
        } // Ends the high-volume loop.
        // Verifies that out of 6000 instantaneous requests, exactly 5000 were allowed.
        results.add(new TestResult("Large Data Burst (Expect 5000 allowed)", successfulRequests == 5000));

        // --- Java 8 Stream API to process results ---
        System.out.println("--- TEST RESULTS ---"); // Prints header for results.

        // Uses Stream API to iterate through results and print each test's name and Pass/Fail status.
        results.stream().forEach(res -> System.out.println(res.testName() + " : " + (res.passed() ? "PASS" : "FAIL")));

        // Uses Stream API to filter only passing tests and counts them.
        long passCount = results.stream().filter(TestResult::passed).count();

        System.out.println("--------------------"); // Prints a separator line.
        System.out.println("Total Passed: " + passCount + " / " + results.size()); // Prints the final summary fraction.
    } // Ends the main method.

    // Java 14+ Record feature: creates a simple, immutable data class to hold test results, minimizing boilerplate.
    record TestResult(String testName, boolean passed) {}
} // Ends the Solution class.