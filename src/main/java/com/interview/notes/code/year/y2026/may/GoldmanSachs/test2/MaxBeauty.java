package com.interview.notes.code.year.y2026.may.GoldmanSachs.test2;

import java.util.Arrays; // Import Arrays utility for sorting and filling

public class MaxBeauty { // Define the main class for our solution

    public static int maximumBeauty(int[] nums, int k) { // Method accepts the array and k value
        Arrays.sort(nums); // Sort the array so numbers closest in value are adjacent
        var left = 0; // Initialize left pointer to track the start of our valid window
        
        for (var right = 0; right < nums.length; right++) { // Iterate right pointer to expand the window
            if (nums[right] - nums[left] > 2 * k) { // Check if the window max difference exceeds the 2k limit
                left++; // Move left pointer forward to maintain the maximum valid window size found
            } // Close the if condition
        } // Close the for loop
        
        return nums.length - left; // The max valid size is the total length minus the left pointer's offset
    } // Close the maximumBeauty method

    public static void main(String[] args) { // Standard main method for standalone testing
        runTest(new int[]{4, 6, 1, 2}, 2, 3, "Basic Example Case"); // Test a standard mixed array
        runTest(new int[]{1, 1, 1, 1}, 10, 4, "All Identical Elements"); // Test where everything already matches
        runTest(new int[]{1, 10, 20}, 2, 1, "Elements Too Far Apart"); // Test where no overlaps are possible
        
        var largeArray = new int[100000]; // Instantiate a large array for stress testing
        Arrays.fill(largeArray, 5); // Fill the massive array with a constant value
        runTest(largeArray, 0, 100000, "Large Data Input"); // Ensure the algorithm easily handles 100k elements
    } // Close the main method

    private static void runTest(int[] nums, int k, int expected, String testName) { // Helper to format test execution
        var result = maximumBeauty(nums, k); // Execute the algorithm and store the result
        var status = (result == expected) ? "PASS" : "FAIL"; // Determine if the result matches expectations
        System.out.println(testName + " -> " + status + " (Expected: " + expected + ", Got: " + result + ")"); // Print logs
    } // Close the runTest helper method
} // Close the class