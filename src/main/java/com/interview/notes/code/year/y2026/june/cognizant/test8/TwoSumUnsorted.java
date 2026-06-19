package com.interview.notes.code.year.y2026.june.cognizant.test8;

import java.util.Arrays; // Import Arrays utility class to help format array outputs for our test results
import java.util.HashMap; // Import HashMap class to store numbers and their indices efficiently
import java.util.Map; // Import Map interface to use as the reference type for our HashMap

public class TwoSumUnsorted { // Declare the main public class for our solution

    public static int[] findTwoSum(int[] numbers, int target) { // Define method taking the integer array and target sum as parameters
        Map<Integer, Integer> map = new HashMap<>(); // Initialize a HashMap to map seen numbers to their 1-based indices

        for (int i = 0; i < numbers.length; i++) { // Start a loop to iterate through the array, using a standard 0-based index 'i'
            int complement = target - numbers[i]; // Calculate the exact number needed to reach the target sum

            if (map.containsKey(complement)) { // Check if our map already contains this needed complement number
                return new int[] { map.get(complement), i + 1 }; // If found, return an array with the stored index and the current index + 1 (for 1-based indexing)
            } // Close the if statement block

            map.put(numbers[i], i + 1); // If complement is not found, save the current number and its 1-based index into the map for future lookups
        } // Close the for loop block

        return new int[0]; // Return an empty array as a fallback if no valid pair is ever found 
    } // Close the findTwoSum method

    public static void main(String[] args) { // Standard main method serving as our entry point for testing
        
        // --- Standard Test Cases ---
        test(new int[]{2, 7, 11, 15}, 9, new int[]{1, 2}, "Standard Case"); // Test normal sequential pair
        test(new int[]{3, 2, 4}, 6, new int[]{2, 3}, "Unsorted Case"); // Test unsorted array pair
        test(new int[]{3, 3}, 6, new int[]{1, 2}, "Duplicate Numbers"); // Test array where the same number is used twice

        // --- Large Data Test Case ---
        int[] largeArray = new int[10000]; // Create a large array of 10,000 elements to test performance and memory
        largeArray[9998] = 50; // Manually set the second-to-last element to 50
        largeArray[9999] = 50; // Manually set the last element to 50
        test(largeArray, 100, new int[]{9999, 10000}, "Large Data Case"); // Run the test expecting the target 100 from the very end of the array
    } // Close the main method

    private static void test(int[] input, int target, int[] expected, String testName) { // Helper method to execute a test case and print PASS/FAIL status
        int[] result = findTwoSum(input, target); // Call our core logic method and store the returned array
        boolean passed = Arrays.equals(result, expected); // Check if the actual result perfectly matches the expected result
        System.out.println(testName + ": " + (passed ? "PASS" : "FAIL") + " | Expected: " + Arrays.toString(expected) + ", Got: " + Arrays.toString(result)); // Print formatted test output to the console
    } // Close the test helper method
} // Close the TwoSumUnsorted class