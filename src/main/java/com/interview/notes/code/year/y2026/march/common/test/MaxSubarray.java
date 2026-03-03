package com.interview.notes.code.year.y2026.march.common.test;

import java.util.stream.Stream;

// Java 14+ Record feature creates a simple class for test cases with zero boilerplate code
record TestCase(int[] nums, int expectedSum, String name) {}

// Main class containing our logic and custom testing framework
public class MaxSubarray {

    // Main method acts as our custom test runner instead of using JUnit
    public static void main(String[] args) {
        
        // Create a large array to test performance limits
        var largeArray = new int[100_000]; // Using 'var' (Java 10+) for clean type inference
        
        // Populate the large array manually to avoid using inbuilt Arrays.fill()
        for (var i = 0; i < largeArray.length; i++) {
            // Fill with 1s so the expected sum is exactly 100,000
            largeArray[i] = 1;
        } // End of large array population loop

        // Use Stream API to process all test cases cleanly
        Stream.of(
            // Example 1 from the provided problem image
            new TestCase(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}, 6, "Example 1"),
            // Example 2 from the provided problem image (single element)
            new TestCase(new int[]{1}, 1, "Example 2"),
            // Example 3 from the provided problem image
            new TestCase(new int[]{5, 4, -1, 7, 8}, 23, "Example 3"),
            // Edge case: array containing absolutely no positive numbers
            new TestCase(new int[]{-2, -5, -1, -8}, -1, "All Negatives Edge Case"),
            // Performance case: massive dataset to ensure O(N) efficiency
            new TestCase(largeArray, 100_000, "Large Data Performance Test")
            
        // Iterate through the stream of our defined test cases
        ).forEach(test -> {
            
            // Execute the core algorithm against the test case input
            var actual = findMaxSubarraySum(test.nums());
            
            // Determine if the actual result matches our expected result
            var isPass = actual == test.expectedSum();
            
            // Format the output string using a ternary operator for brevity
            var status = isPass ? "PASS" : "FAIL";
            
            // Print the final test result clearly to the console
            System.out.println(test.name() + ": " + status + " (Expected: " + test.expectedSum() + ", Got: " + actual + ")");
            
        }); // End of stream processing
    }

    // Core algorithm method that strictly avoids inbuilt functions
    static int findMaxSubarraySum(int[] arr) {
        
        // Track the absolute maximum sum found across the whole array
        var bestSum = arr[0];
        
        // Track the maximum sum ending at our current loop position
        var currentSum = arr[0];

        // Loop through the array sequentially, starting at the second element (index 1)
        for (var i = 1; i < arr.length; i++) {
            
            // Calculate what the sum would be if we extend the current subarray
            var extendedSum = currentSum + arr[i];
            
            // Use ternary operator to manually pick the larger value, avoiding Math.max()
            currentSum = (extendedSum > arr[i]) ? extendedSum : arr[i];
            
            // Use ternary operator to manually update bestSum if currentSum is higher
            bestSum = (currentSum > bestSum) ? currentSum : bestSum;
            
        } // End of core logic loop

        // Return the highest historical sum recorded during the loop
        return bestSum;
    }
}