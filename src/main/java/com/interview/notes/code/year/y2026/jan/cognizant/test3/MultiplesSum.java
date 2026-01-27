package com.interview.notes.code.year.y2026.jan.cognizant.test3;

import java.util.Arrays;
import java.util.stream.IntStream;

public class MultiplesSum {

    // Core logic method: Calculates sum of multiples for a specific divisor
    // Returns long to prevent overflow on large datasets
    static long getSum(int[] data, int divisor) {
        return Arrays.stream(data)             // Create a stream source from the input array
                     .filter(n -> n % divisor == 0) // Keep only numbers exactly divisible by divisor
                     .asLongStream()           // Convert to LongStream to handle large sums safely
                     .sum();                   // Aggregate all values into a single total
    }

    // Helper method to run test cases and print PASS/FAIL
    static void runTest(String testName, int[] input, int divisor, long expected) {
        long actual = getSum(input, divisor);   // Execute logic
        String status = (actual == expected) ? "PASS" : "FAIL"; // Determine status
        // Print result in a single line for easy scanning
        System.out.printf("[%s] Divisor: %d | Expected: %d | Actual: %d -> %s%n",
            testName, divisor, expected, actual, status);
    }

    public static void main(String[] args) {
        System.out.println("--- Starting Test Execution ---");

        // --- Test Case 1: The Example Case (1 to 20) ---
        // Generating array 1..20 using Stream
        int[] inputSmall = IntStream.rangeClosed(1, 20).toArray(); 
        
        // Manual verification for verification:
        // Multiples of 2 (1..20): 2,4,6,8,10,12,14,16,18,20 = 110
        // Multiples of 5 (1..20): 5,10,15,20 = 50
        
        runTest("Small Data", inputSmall, 2, 110); // Check sum of multiples of 2
        runTest("Small Data", inputSmall, 3, 63);  // Check sum of multiples of 3 (3+6+9+12+15+18)
        runTest("Small Data", inputSmall, 4, 60);  // Check sum of multiples of 4 (4+8+12+16+20)
        runTest("Small Data", inputSmall, 5, 50);  // Check sum of multiples of 5

        // --- Test Case 2: Edge Case (Empty Array) ---
        runTest("Empty Input", new int[]{}, 2, 0); // Sum should be 0

        // --- Test Case 3: Edge Case (No Multiples found) ---
        int[] noMultiples = {1, 7, 11, 13}; // Primes not divisible by 2,3,4,5
        runTest("No Match", noMultiples, 2, 0); // Should be 0

        // --- Test Case 4: Large Data Input ---
        // Creating a large array: 1,000,000 elements, all equal to 5
        // Sum should be 1,000,000 * 5 = 5,000,000
        int size = 1_000_000; // Define large size
        int[] largeData = new int[size]; // Allocate memory
        Arrays.fill(largeData, 5); // Fill array with 5s
        
        // Test multiples of 5 (All should match)
        runTest("Large Data", largeData, 5, 5_000_000L); 
        // Test multiples of 2 (None should match)
        runTest("Large Data", largeData, 2, 0L); 
    }
}