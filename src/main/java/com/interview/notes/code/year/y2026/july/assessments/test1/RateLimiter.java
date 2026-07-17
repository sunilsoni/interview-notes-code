package com.interview.notes.code.year.y2026.july.assessments.test1;

import java.util.Map; // Needed to define our key-value storage interface
import java.util.concurrent.ConcurrentHashMap; // Thread-safe map to handle concurrent requests safely without locks
import java.util.stream.IntStream; // Stream API to generate large datasets for testing

public class RateLimiter { // Main class that encapsulates our rate limiting logic

    private final int limit; // Stores the maximum allowed requests per time window
    private final long windowSizeMs; // Stores how long each time window lasts in milliseconds
    private final Map<String, WindowData> store; // In-memory database mapping client IDs to their window data
    public RateLimiter(int limit, long windowSizeMs) { // Constructor to initialize our rate limiter rules
        this.limit = limit; // Assign the provided limit to the instance variable
        this.windowSizeMs = windowSizeMs; // Assign the window duration to the instance variable
        this.store = new ConcurrentHashMap<>(); // Initialize the thread-safe map so it's ready to store data
    } // End of constructor

    // 5. Testing Phase
    public static void main(String[] args) throws InterruptedException { // Simple main method for running tests without JUnit
        System.out.println("Starting Rate Limiter Tests...\n"); // Print a console message to indicate tests are starting

        RateLimiter basicLimiter = new RateLimiter(3, 1000); // Create a limiter allowing 3 requests every 1000ms (1 second)

        // Test 1: Basic functionality
        boolean t1_req1 = basicLimiter.allowRequest("user_1"); // Request 1 - Should be true
        boolean t1_req2 = basicLimiter.allowRequest("user_1"); // Request 2 - Should be true
        boolean t1_req3 = basicLimiter.allowRequest("user_1"); // Request 3 - Should be true
        boolean t1_req4 = basicLimiter.allowRequest("user_1"); // Request 4 - Should be false (exceeds limit of 3)
        printResult("Test 1 (Basic Limit Enforcement)", t1_req1 && t1_req2 && t1_req3 && !t1_req4); // Evaluate if logic worked and print

        // Test 2: Window reset behavior
        Thread.sleep(1001); // Pause the thread for just over 1 second to force the time window to expire
        boolean t2_req1 = basicLimiter.allowRequest("user_1"); // Request 1 in the new time window - Should be true again
        printResult("Test 2 (Window Expiration Reset)", t2_req1); // Evaluate and print the result

        // Test 3: Large Data & High Concurrency (Stream API)
        RateLimiter heavyLimiter = new RateLimiter(5000, 10000); // Create a new limiter for 5000 requests per 10 seconds

        long passedRequests = IntStream.range(0, 10000) // Generate 10,000 sequential numbers to simulate large data input
            .parallel() // Java 8 Stream API: Convert to a parallel stream to simulate highly concurrent, multi-threaded traffic
            .mapToObj(i -> heavyLimiter.allowRequest("viral_user")) // Map each number to a rate limit check for the same user
            .filter(isAllowed -> isAllowed) // Keep only the requests that were successfully allowed (returned true)
            .count(); // Count the total number of allowed requests

        printResult("Test 3 (Concurrency & Large Data)", passedRequests == 5000); // Verify exactly 5000 passed out of 10,000
    } // End of main method

    private static void printResult(String testName, boolean isPass) { // Helper method to keep test output formatting clean
        System.out.println(testName + " -> " + (isPass ? "PASS" : "FAIL")); // Uses ternary operator to print PASS or FAIL based on condition
    } // End of helper method

    public boolean allowRequest(String clientId) { // Method called for every incoming request to check if it's allowed
        long currentWindow = System.currentTimeMillis() / windowSizeMs; // Calculates the current time bucket ID using integer division

        // Java 8 API: Atomic compute function updates the map safely in concurrent environments
        WindowData updatedData = store.compute(clientId, (key, oldData) -> { // Starts atomic calculation for the given client
            if (oldData == null || oldData.windowId() != currentWindow) { // Checks if client is new OR if their previous window expired
                return new WindowData(currentWindow, 1); // If expired or new, return a fresh record starting at count 1
            } // End of reset condition
            return new WindowData(currentWindow, oldData.count() + 1); // Otherwise, return a new record with the incremented count
        }); // End of atomic compute operation

        return updatedData.count() <= limit; // Returns true (PASS) if the updated count hasn't exceeded our limit
    } // End of allowRequest method

    // Java 21 feature: Record creates an immutable data carrier for window state in a single line
    private record WindowData(long windowId, int count) {} // Holds the timestamp window and the number of requests made
} // End of RateLimiter class