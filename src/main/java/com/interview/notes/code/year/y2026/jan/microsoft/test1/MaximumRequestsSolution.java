package com.interview.notes.code.year.y2026.jan.microsoft.test1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MaximumRequestsSolution {

    /**
     * Finds the maximum requests in a given time window.
     * Uses a sliding window approach for O(N) efficiency.
     */
    public static int maximumRequests(int window, List<Integer> timestamps) {
        int maxRequests = 0; // Tracks the highest number of requests found so far
        int left = 0;        // Left boundary of our sliding window

        // Iterate through the timestamps using 'right' as the expanding boundary
        for (int right = 0; right < timestamps.size(); right++) {

            // Current time at the right pointer
            int currentTime = timestamps.get(right);

            // While the time gap is too large (current time - start time >= window)
            // We shrink the window from the left side
            while (currentTime - timestamps.get(left) >= window) {
                left++; // Move left pointer forward to reduce the time range
            }

            // Calculate current window count: (right index - left index + 1)
            int currentCount = right - left + 1;

            // Update the global maximum if the current window is larger
            maxRequests = Math.max(maxRequests, currentCount);
        }

        return maxRequests; // Return the final maximum count
    }

    // ==========================================
    // TESTING SUITE (Simple Main Method)
    // ==========================================
    public static void main(String[] args) {
        System.out.println("--- Starting Tests ---\n");

        // Test Case 1: Provided Example
        // Window: 5, Data: [1, 2, 3, 8, 10]
        // Expected: 3 (requests 1, 2, 3 fit in window of size 5)
        runTest("Example Case", 5, Arrays.asList(1, 2, 3, 8, 10), 3);

        // Test Case 2: Provided Sample 0
        // Window: 4, Data: [2, 3, 4, 5, 10, 12]
        // Expected: 4 (requests 2, 3, 4, 5 fit in range [2, 5], size is 4)
        runTest("Sample Case 0", 4, Arrays.asList(2, 3, 4, 5, 10, 12), 4);

        // Test Case 3: Single Element
        runTest("Single Element", 10, List.of(5), 1);

        // Test Case 4: All fit in one window
        // Window 100 covers 10 to 50 easily.
        runTest("All Fit", 100, Arrays.asList(10, 20, 30, 40, 50), 5);

        // Test Case 5: Window size 1 (Only 1 request max possible unless duplicates exist)
        runTest("Minimum Window", 1, Arrays.asList(1, 5, 9), 1);

        // Test Case 6: Large Data (Performance Check)
        testLargeData();

        System.out.println("\n--- All Tests Finished ---");
    }

    // Helper method to run small tests and print PASS/FAIL
    private static void runTest(String testName, int window, List<Integer> data, int expected) {
        long startTime = System.nanoTime(); // Start timer
        int result = maximumRequests(window, data); // Run logic
        long endTime = System.nanoTime();   // End timer

        boolean pass = result == expected;
        String status = pass ? "PASS" : "FAIL (Got " + result + ", Expected " + expected + ")";

        System.out.printf("[%s] %s | Time: %d ns%n", status, testName, (endTime - startTime));
    }

    // Helper method to generate and test large dataset
    private static void testLargeData() {
        System.out.println("\nRunning Large Data Test...");

        int n = 200_000; // Max constraint for N
        List<Integer> largeList = new ArrayList<>(n);

        // Generate sorted timestamps: 0, 10, 20, 30...
        // This makes gaps uniform. Window of size 100 should fit ~10 items.
        for (int i = 0; i < n; i++) {
            largeList.add(i * 10);
        }

        int window = 100; // Window covers range 0 to 99
        // Logic: range [0, 90] includes 0, 10...90 (10 items). 
        // 100 is excluded (100 - 0 >= 100). So expected is 10.
        int expected = 10;

        long startTime = System.currentTimeMillis();
        int result = maximumRequests(window, largeList);
        long endTime = System.currentTimeMillis();

        if (result == expected) {
            System.out.println("[PASS] Large Data (200k items) | Time: " + (endTime - startTime) + " ms");
        } else {
            System.out.println("[FAIL] Large Data. Got: " + result + " Expected: " + expected);
        }
    }
}