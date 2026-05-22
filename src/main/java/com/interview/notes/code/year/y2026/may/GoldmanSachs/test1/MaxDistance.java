package com.interview.notes.code.year.y2026.may.GoldmanSachs.test1;

import java.util.Arrays; // Imports Arrays utility to easily convert our array into a Stream

public class MaxDistance { // Main class container

    public static void main(String[] args) { // Standard main method to run our tests without JUnit

        var tests = new TestCase[] { // Using 'var' (modern Java) to declare our test array concisely
            new TestCase(new int[]{1, 9, 2, 4, 3, 2, 1}, 8), // User's test: Max is 9, Min is 1, 9 - 1 = 8
            new TestCase(new int[]{5, 5, 5}, 0), // Edge case: All same numbers, difference is 0
            new TestCase(new int[]{-5, 10, 2}, 15), // Edge case: Negative numbers, 10 - (-5) = 15
            new TestCase(new int[]{10}, 0) // Edge case: Only one number, distance is 0 safely
        }; // End of test cases
        
        for (var test : tests) { // Loop through each test case sequentially
            int result = findMaxDistance(test.arr()); // Calculate actual result using our method
            System.out.println((result == test.expected() ? "PASS" : "FAIL") + " | Expected: " + test.expected() + ", Got: " + result); // Print validation
        } // End of loop

        // Large Data Test Case
        int[] largeArr = new int[100000]; // Create an array with 100,000 elements for performance testing
        Arrays.fill(largeArr, 50); // Fill the whole array with dummy data (50s)
        largeArr[0] = 1; // Set the absolute lowest value
        largeArr[99999] = 100000; // Set the absolute highest value
        System.out.println((findMaxDistance(largeArr) == 99999 ? "PASS" : "FAIL") + " | Large Data Test"); // Expected diff: 100000 - 1 = 99999
    } // End of main method

    static int findMaxDistance(int[] arr) { // Method to find max mathematical distance
        if (arr == null || arr.length < 2) return 0; // Safety check: need at least 2 numbers to find a meaningful distance

        var stats = Arrays.stream(arr).summaryStatistics(); // Stream API calculates min, max, sum, etc. all in one single highly-optimized pass (O(n) time)
        return stats.getMax() - stats.getMin(); // Subtract the smallest number from the largest number to get the maximum distance
    } // End of findMaxDistance method

    record TestCase(int[] arr, int expected) {} // Java Record to cleanly store test inputs and expected outputs in one line
} // End of class