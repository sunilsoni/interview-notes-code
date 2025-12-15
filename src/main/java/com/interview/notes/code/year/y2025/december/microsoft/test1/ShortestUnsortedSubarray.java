package com.interview.notes.code.year.y2025.december.microsoft.test1;

import java.util.Arrays;
import java.util.stream.IntStream;

public class ShortestUnsortedSubarray {
    
    /**
     * Main method to find shortest unsorted subarray length
     * Uses comparison between original and sorted array
     */
    public static int findUnsortedSubarray(int[] nums) {
        // Store the length of input array for reuse
        int n = nums.length;
        
        // Create a sorted copy of the original array
        // This helps us compare what the array should look like when fully sorted
        int[] sorted = Arrays.stream(nums) // Convert array to IntStream
                             .sorted()     // Sort all elements in ascending order
                             .toArray();   // Convert back to int array
        
        // Variable to track the starting position of unsorted subarray
        // Initialize to -1 meaning no mismatch found yet
        int start = -1;
        
        // Variable to track the ending position of unsorted subarray
        // Initialize to -1 meaning no mismatch found yet
        int end = -1;
        
        // Loop through each index to find first mismatch from left side
        // This gives us the left boundary of unsorted portion
        for (int i = 0; i < n; i++) {
            // Compare original value with what it should be in sorted array
            if (nums[i] != sorted[i]) {
                // Found first mismatch, this is where sorting needs to start
                start = i;
                // Break as we only need first mismatch from left
                break;
            }
        }
        
        // If start is still -1, array is already sorted, return 0
        // No subarray needs to be sorted
        if (start == -1) {
            return 0;
        }
        
        // Loop from right side to find last mismatch
        // This gives us the right boundary of unsorted portion
        for (int i = n - 1; i >= 0; i--) {
            // Compare original value with sorted value from right
            if (nums[i] != sorted[i]) {
                // Found last mismatch, this is where sorting needs to end
                end = i;
                // Break as we only need last mismatch from right
                break;
            }
        }
        
        // Calculate and return the length of unsorted subarray
        // Add 1 because both start and end are inclusive
        return end - start + 1;
    }
    
    /**
     * Test method to verify if actual output matches expected output
     * Prints PASS or FAIL for each test case
     */
    public static void runTest(int testNumber, int[] nums, int expected) {
        // Call the main logic method to get actual result
        int actual = findUnsortedSubarray(nums);
        
        // Compare actual result with expected result
        boolean passed = (actual == expected);
        
        // Create a readable string of input array for display
        // Using Stream to convert int array to string representation
        String arrayStr = Arrays.toString(nums);
        
        // Print test result in readable format
        // Shows test number, input, expected, actual and PASS/FAIL status
        System.out.println("Test " + testNumber + ": " + 
                          (passed ? "PASS" : "FAIL") + 
                          " | Input: " + arrayStr + 
                          " | Expected: " + expected + 
                          " | Actual: " + actual);
    }
    
    /**
     * Main method - Entry point of the program
     * Runs all test cases including edge cases and large data
     */
    public static void main(String[] args) {
        // Print header for test results
        System.out.println("=== Testing Shortest Unsorted Subarray ===\n");
        
        // Test Case 1: Standard case from problem
        // Subarray [6,4,8,10,9] at indices 1-5 needs sorting
        runTest(1, new int[]{2, 6, 4, 8, 10, 9, 15}, 5);
        
        // Test Case 2: Already sorted array
        // No subarray needs sorting, return 0
        runTest(2, new int[]{1, 2, 3, 4}, 0);
        
        // Test Case 3: Single element array
        // Already sorted by definition, return 0
        runTest(3, new int[]{1}, 0);
        
        // Test Case 4: Longer unsorted portion
        // Most of the array is unsorted
        runTest(4, new int[]{2, 6, 4, 1, 8, 10, 9, 15}, 7);
        
        // Test Case 5: Short unsorted subarray
        // Elements 3,2,2 at indices 1-3 need sorting
        runTest(5, new int[]{1, 3, 2, 2}, 3);
        
        // Test Case 6: Completely reverse sorted array
        // Entire array needs sorting
        runTest(6, new int[]{5, 4, 3, 2, 1}, 5);
        
        // Test Case 7: Two elements swapped
        // Both elements need to be sorted
        runTest(7, new int[]{2, 1}, 2);
        
        // Test Case 8: All same elements
        // Already sorted, return 0
        runTest(8, new int[]{3, 3, 3, 3}, 0);
        
        // Test Case 9: Negative numbers included
        // Tests handling of negative values
        runTest(9, new int[]{-5, -3, -2, -10, 0, 5}, 3);
        
        // Test Case 10: Mixed positive and negative
        // Entire middle section is unsorted
        runTest(10, new int[]{-2, 5, 3, 1, 8, 10}, 4);
        
        // Test Case 11: Unsorted at the beginning
        // First few elements need sorting
        runTest(11, new int[]{3, 2, 1, 4, 5, 6}, 3);
        
        // Test Case 12: Unsorted at the end
        // Last few elements need sorting
        runTest(12, new int[]{1, 2, 3, 6, 5, 4}, 3);
        
        System.out.println("\n=== Large Data Test Cases ===\n");
        
        // Test Case 13: Large sorted array (10000 elements)
        // Tests performance with maximum size sorted array
        int[] largeSorted = IntStream.range(1, 10001) // Generate 1 to 10000
                                     .toArray();       // Convert to array
        runTest(13, largeSorted, 0);
        System.out.println("   (Large sorted array with 10000 elements)");
        
        // Test Case 14: Large reverse sorted array
        // Tests performance when entire array needs sorting
        int[] largeReverse = IntStream.range(1, 10001)        // Generate 1 to 10000
                                      .map(i -> 10001 - i)    // Reverse the values
                                      .toArray();             // Convert to array
        runTest(14, largeReverse, 10000);
        System.out.println("   (Large reverse sorted array with 10000 elements)");
        
        // Test Case 15: Large array with small unsorted portion in middle
        // Tests finding small unsorted section in large sorted array
        int[] largeWithMiddle = IntStream.range(1, 10001).toArray();
        // Swap two elements in the middle to create small unsorted portion
        largeWithMiddle[5000] = 5002; // Make position 5000 have value 5002
        largeWithMiddle[5001] = 5001; // Keep 5001 at position 5001
        runTest(15, largeWithMiddle, 2);
        System.out.println("   (Large array with 2 elements swapped in middle)");
        
        // Test Case 16: Large array with extreme values
        // Tests handling of maximum allowed values per constraints
        int[] extremeValues = new int[1000];
        // Fill with alternating extreme values
        for (int i = 0; i < 1000; i++) {
            // Alternate between max positive and min negative
            extremeValues[i] = (i % 2 == 0) ? 100000 : -100000;
        }
        runTest(16, extremeValues, 1000);
        System.out.println("   (Array with alternating extreme values)");
        
        // Print summary
        System.out.println("\n=== All Test Cases Completed ===");
    }
}