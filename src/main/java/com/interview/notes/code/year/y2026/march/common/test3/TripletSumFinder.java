package com.interview.notes.code.year.y2026.march.common.test3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

// Use Java 21 'record' to create an immutable Triplet class without writing getters or constructors
record Triplet(int a, int b, int c) {
    @Override // Override the default object string representation
    public String toString() { // Define how the triplet should be printed
        return "(" + a + "," + b + "," + c + ")"; // Format to match your requested output: (a,b,c)
    } // Close toString method
} // Close record definition

public class TripletSumFinder { // Main class that holds our logic and test runner

    // Core method to find triplets, taking an array and returning a List of Triplet records
    public static List<Triplet> findTriplets(int[] arr) { // Method signature
        var result = new ArrayList<Triplet>(); // Use Java 'var' for cleaner initialization
        if (arr == null || arr.length < 3) return result; // Return empty immediately if input is invalid
        
        Arrays.sort(arr); // Sort the array so we can safely use the two-pointer technique
        
        // Loop backward: target element 'c' will be at index 'i', ensuring it is the largest of the trio
        for (int i = arr.length - 1; i >= 2; i--) { // Start from the end, go down to index 2
            int left = 0; // Initialize left pointer at the very start (smallest numbers)
            int right = i - 1; // Initialize right pointer just below our target element
            
            while (left < right) { // Loop as long as our two pointers haven't collided
                int sum = arr[left] + arr[right]; // Calculate the sum of our two pointer elements
                
                if (sum == arr[i]) { // Check if we have found a perfectly matching triplet
                    result.add(new Triplet(arr[left], arr[right], arr[i])); // Save the successful triplet
                    left++; // Move the left pointer rightwards to find other potential pairs
                    right--; // Move the right pointer leftwards to find other potential pairs
                } else if (sum < arr[i]) { // If our calculated sum is too small
                    left++; // Move the left pointer to a larger number to increase the sum
                } else { // If our calculated sum is too large
                    right--; // Move the right pointer to a smaller number to decrease the sum
                } // Close if-else-if blocks
            } // Close the while loop for the two pointers
        } // Close the for loop iterating through target elements
        
        return result; // Return our completely populated list to the caller
    } // Close findTriplets method

    // Simple main method replacing JUnit, acting as our application entry point
    public static void main(String[] args) { // Standard Java main signature
        runTests(); // Execute our custom testing suite
    } // Close main method

    // Custom test runner to handle standard, edge, and large data cases
    static void runTests() { // Package-private test execution method
        // TEST CASE 1: Your provided example
        int[] test1 = {7, 34, 3, 9, 12, 52, 21, 23, 4}; // Setup array from prompt
        var expected1 = List.of(new Triplet(3, 4, 7), new Triplet(3, 9, 12), new Triplet(9, 12, 21)); // Include the mathematically missing (9,12,21)
        verifyTest("Test 1 (Standard Example)", test1, expected1); // Call verification helper
        
        // TEST CASE 2: No valid triplets exist
        int[] test2 = {1, 2, 4, 8, 16}; // Exponential arrays never have a+b=c
        var expected2 = List.<Triplet>of(); // We expect a completely empty list here
        verifyTest("Test 2 (No Triplets Case)", test2, expected2); // Call verification helper
        
        // TEST CASE 3: Large Data / Performance check
        int[] largeData = IntStream.rangeClosed(1, 3000).toArray(); // Stream API creates an array of 3000 items
        long startTime = System.currentTimeMillis(); // Track exactly when we start processing
        var largeResult = findTriplets(largeData); // Execute algorithm against large dataset
        long endTime = System.currentTimeMillis(); // Track exactly when we finish processing
        boolean isFast = (endTime - startTime) < 1500; // Define acceptable performance as under 1.5 seconds
        boolean hasResults = !largeResult.isEmpty(); // Confirm it actually found the thousands of triplets
        System.out.println("Test 3 (Large Data 3000 items): " + (isFast && hasResults ? "PASS" : "FAIL") + " (Took " + (endTime - startTime) + "ms)"); // Print performance results
    } // Close test runner method

    // Helper method to compare actual output against our expectations and print PASS/FAIL
    static void verifyTest(String testName, int[] input, List<Triplet> expected) { // Verification logic
        var actual = findTriplets(input); // Call the core logic to get actual data
        // Java records automatically generate 'equals()', so containsAll works flawlessly
        boolean passed = actual.size() == expected.size() && actual.containsAll(expected); // Check full match
        System.out.println(testName + ": " + (passed ? "PASS" : "FAIL")); // Print immediate result
        if (!passed) { // If there was a discrepancy, provide debugging info
            System.out.println("   Expected: " + expected); // Show what was wanted
            System.out.println("   Actual:   " + actual); // Show what was calculated
        } // Close if block
    } // Close verification helper method
} // Close main class