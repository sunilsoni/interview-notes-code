package com.interview.notes.code.year.y2026.april.amazon.test4;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DiskSpaceAnalysis {

    // Main solution method
    public static int segment(int x, List<Integer> space) { // Method signature required by the problem
        var n = space.size(); // Store the total number of computers to avoid calling size() repeatedly
        var dq = new ArrayDeque<Integer>(); // Use a double-ended queue to store indices of potential minimums
        var maxOfMins = Integer.MIN_VALUE; // Initialize our final answer to the smallest possible integer

        for (var i = 0; i < n; i++) { // Loop through every computer's space exactly once to maintain O(N) time complexity
            
            if (!dq.isEmpty() && dq.peekFirst() <= i - x) { // Check if the oldest index in our queue is now outside our sliding window of size 'x'
                dq.pollFirst(); // If it is outside the window bounds, remove it from the front of the queue
            } // End of window bounds check
            
            while (!dq.isEmpty() && space.get(dq.peekLast()) >= space.get(i)) { // While the current space is smaller than the ones at the back of the queue
                dq.pollLast(); // Remove larger elements from the back because they can never be the minimum for this or any future windows
            } // End of removing useless larger elements
            
            dq.offerLast(i); // Add the current element's index to the back of the queue as a new candidate for minimum
            
            if (i >= x - 1) { // Check if our loop has processed at least 'x' elements to form our first complete valid segment
                maxOfMins = Math.max(maxOfMins, space.get(dq.peekFirst())); // The front of the queue is always the minimum of the current segment; compare and update our global maximum
            } // End of maximum update check
            
        } // End of main processing loop
        
        return maxOfMins; // Return the maximum value found among all segment minimums
    } // End of segment method


    // ---------------------------------------------------------
    // TESTING FRAMEWORK (No JUnit, simple Main method execution)
    // ---------------------------------------------------------
    
    public static void main(String[] args) { // Main entry point for our custom test runner
        var testCases = new ArrayList<TestCase>(); // List to hold all our test scenarios

        // 1. Provided Example 1
        testCases.add(new TestCase("Example 1", 2, List.of(8, 2, 4, 6), 4)); // Adding the first example from the problem description

        // 2. Provided Example 2
        testCases.add(new TestCase("Example 2", 1, List.of(1, 2, 3, 1, 2), 3)); // Adding the second example

        // 3. Edge Case: Window size equals array size
        testCases.add(new TestCase("Full Window", 4, List.of(10, 20, 5, 30), 5)); // Minimum of the whole array is 5, max of that is 5

        // 4. Large Data Test Case Generation
        var largeSpace = new ArrayList<Integer>(); // Create a list to simulate 1 million computers
        var random = new Random(42); // Seeded random for reproducible tests
        for (int i = 0; i < 1_000_000; i++) { // Loop 1 million times
            largeSpace.add(random.nextInt(1_000_000_000) + 1); // Add random disk space up to 10^9
        } // End of large data generation
        // For the large test, we just want to ensure it completes within a second and doesn't crash (expected value check is skipped for dynamic random data, we test performance)
        testCases.add(new TestCase("Large Data (1 Million)", 50000, largeSpace, -1)); // Add the large test case

        // Run all tests
        System.out.println("Running test cases...\n"); // Print starting message
        for (var tc : testCases) { // Iterate over every configured test case
            long startTime = System.currentTimeMillis(); // Record start time for performance tracking

            var result = segment(tc.x(), tc.space()); // Execute our core logic

            long duration = System.currentTimeMillis() - startTime; // Calculate how long the execution took

            if (tc.expected() == -1) { // If expected is -1, it's our performance test
                System.out.printf("[PASS] %s - Completed in %d ms (Result: %d)%n", tc.name(), duration, result); // Print performance success
            } else if (result == tc.expected()) { // If the actual result matches expected
                System.out.printf("[PASS] %s%n", tc.name()); // Print standard success message
            } else { // If the actual result doesn't match expected
                System.out.printf("[FAIL] %s - Expected %d but got %d%n", tc.name(), tc.expected(), result); // Print failure details
            } // End of result verification
        } // End of test runner loop
    } // End of main method

    // Using Java records to define test cases cleanly and in very few words
    record TestCase(String name, int x, List<Integer> space, int expected) {}
}