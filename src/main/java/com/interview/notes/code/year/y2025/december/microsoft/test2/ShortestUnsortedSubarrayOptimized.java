package com.interview.notes.code.year.y2025.december.microsoft.test2;

import java.util.Arrays;
import java.util.stream.IntStream;

public class ShortestUnsortedSubarrayOptimized {
    
    /**
     * Optimized method to find shortest unsorted subarray
     * Time Complexity: O(n) - Single pass in each direction
     * Space Complexity: O(1) - Only using few variables
     */
    public static int findUnsortedSubarray(int[] nums) {
        
        // Get the length of input array
        // We need this to loop through the array
        int n = nums.length;
        
        // If array has 0 or 1 element, it is already sorted
        // No subarray needs to be sorted, return 0
        if (n <= 1) {
            return 0;
        }
        
        // Variable to track the END position of unsorted subarray
        // Initialize to -1 meaning no unsorted element found yet
        int end = -1;
        
        // Variable to track the START position of unsorted subarray
        // Initialize to -1 meaning no unsorted element found yet
        int start = -1;
        
        // Variable to track MAXIMUM value seen so far from left side
        // Initialize with first element as starting point
        int max = nums[0];
        
        // Variable to track MINIMUM value seen so far from right side
        // Initialize with last element as starting point
        int min = nums[n - 1];
        
        // ============================================================
        // SINGLE LOOP: Scan both directions at the same time!
        // i goes left to right (0 to n-1)
        // j goes right to left (n-1 to 0)
        // ============================================================
        
        for (int i = 0; i < n; i++) {
            
            // ----------------------------------------------------------
            // LEFT TO RIGHT: Find END boundary
            // ----------------------------------------------------------
            
            // Update max if current element is larger
            // max represents the largest value we have seen from index 0 to i
            if (nums[i] >= max) {
                // Current element is >= max, so it might be in correct position
                // Update max to include this element
                max = nums[i];
            } else {
                // Current element is SMALLER than max seen before
                // This means current element is OUT OF PLACE!
                // It should have appeared before the larger element
                // So this position needs to be included in sorting
                end = i;
            }
            
            // ----------------------------------------------------------
            // RIGHT TO LEFT: Find START boundary
            // ----------------------------------------------------------
            
            // Calculate the mirror index from right side
            // When i=0, j=n-1 (last index)
            // When i=1, j=n-2 (second last index)
            // And so on...
            int j = n - 1 - i;
            
            // Update min if current element (from right) is smaller
            // min represents the smallest value we have seen from index n-1 to j
            if (nums[j] <= min) {
                // Current element is <= min, so it might be in correct position
                // Update min to include this element
                min = nums[j];
            } else {
                // Current element is LARGER than min seen after it
                // This means current element is OUT OF PLACE!
                // It should have appeared after the smaller element
                // So this position needs to be included in sorting
                start = j;
            }
        }
        
        // If end is still -1, no out-of-place element was found
        // Array is already sorted, return 0
        if (end == -1) {
            return 0;
        }
        
        // Calculate and return length of unsorted subarray
        // Add 1 because both start and end positions are inclusive
        return end - start + 1;
    }
    
    /**
     * Test method to verify if actual output matches expected output
     * Prints PASS or FAIL with details for each test case
     */
    public static void runTest(int testNumber, int[] nums, int expected) {
        
        // Record start time to measure performance
        long startTime = System.nanoTime();
        
        // Call the optimized method to get actual result
        int actual = findUnsortedSubarray(nums);
        
        // Record end time after method execution
        long endTime = System.nanoTime();
        
        // Calculate time taken in milliseconds
        double timeTaken = (endTime - startTime) / 1_000_000.0;
        
        // Check if actual result matches expected result
        boolean passed = (actual == expected);
        
        // Create readable string for small arrays (limit display size)
        String arrayStr;
        if (nums.length <= 10) {
            // Show full array if it has 10 or fewer elements
            arrayStr = Arrays.toString(nums);
        } else {
            // Show abbreviated form for large arrays
            arrayStr = "[" + nums.length + " elements]";
        }
        
        // Print formatted test result
        System.out.println("Test " + testNumber + ": " + 
                          (passed ? "PASS ✓" : "FAIL ✗") + 
                          " | Input: " + arrayStr + 
                          " | Expected: " + expected + 
                          " | Actual: " + actual +
                          " | Time: " + String.format("%.3f", timeTaken) + " ms");
    }
    
    /**
     * Main method - Entry point of the program
     * Contains all test cases including edge cases and large data tests
     */
    public static void main(String[] args) {
        
        // Print header for test section
        System.out.println("╔═══════════════════════════════════════════════════════════════╗");
        System.out.println("║   OPTIMIZED O(n) SOLUTION - Shortest Unsorted Subarray       ║");
        System.out.println("╠═══════════════════════════════════════════════════════════════╣");
        System.out.println("║   Time Complexity: O(n)    |    Space Complexity: O(1)       ║");
        System.out.println("╚═══════════════════════════════════════════════════════════════╝");
        System.out.println();
        
        System.out.println("=== Basic Test Cases ===\n");
        
        // Test Case 1: Standard case from problem statement
        // Elements [6,4,8,10,9] at indices 1-5 are unsorted
        runTest(1, new int[]{2, 6, 4, 8, 10, 9, 15}, 5);
        
        // Test Case 2: Already sorted array
        // All elements in correct position, no sorting needed
        runTest(2, new int[]{1, 2, 3, 4}, 0);
        
        // Test Case 3: Single element array
        // One element is always sorted
        runTest(3, new int[]{1}, 0);
        
        // Test Case 4: Longer unsorted portion from problem
        // Most of the array needs sorting
        runTest(4, new int[]{2, 6, 4, 1, 8, 10, 9, 15}, 7);
        
        // Test Case 5: Short unsorted portion from problem
        // [3,2,2] needs sorting to become [2,2,3]
        runTest(5, new int[]{1, 3, 2, 2}, 3);
        
        System.out.println("\n=== Edge Cases ===\n");
        
        // Test Case 6: Completely reverse sorted
        // Entire array needs sorting
        runTest(6, new int[]{5, 4, 3, 2, 1}, 5);
        
        // Test Case 7: Two elements swapped
        // Minimum unsorted case with 2 elements
        runTest(7, new int[]{2, 1}, 2);
        
        // Test Case 8: All same elements
        // Equal elements are considered sorted
        runTest(8, new int[]{3, 3, 3, 3}, 0);
        
        // Test Case 9: Negative numbers
        // Testing with negative values
        runTest(9, new int[]{-5, -3, -2, -10, 0, 5}, 3);
        
        // Test Case 10: Mixed positive and negative
        // Boundary between positive and negative numbers
        runTest(10, new int[]{-2, 5, 3, 1, 8, 10}, 4);
        
        // Test Case 11: Unsorted only at beginning
        // First few elements need rearranging
        runTest(11, new int[]{3, 2, 1, 4, 5, 6}, 3);
        
        // Test Case 12: Unsorted only at end
        // Last few elements need rearranging
        runTest(12, new int[]{1, 2, 3, 6, 5, 4}, 3);
        
        // Test Case 13: Empty-like case with two same elements
        // Two equal elements are sorted
        runTest(13, new int[]{1, 1}, 0);
        
        // Test Case 14: Descending duplicates
        // All duplicates in wrong order
        runTest(14, new int[]{3, 3, 2, 2, 1, 1}, 6);
        
        System.out.println("\n=== Large Data Test Cases (Performance Test) ===\n");
        
        // Test Case 15: Large sorted array (10,000 elements)
        // Tests performance with maximum constraint size
        int[] largeSorted = IntStream.range(1, 10001)  // Generate 1 to 10000
                                     .toArray();        // Convert to array
        runTest(15, largeSorted, 0);
        System.out.println("   → Large sorted array: 10,000 elements");
        
        // Test Case 16: Large reverse sorted array
        // Worst case - entire array needs sorting
        int[] largeReverse = IntStream.range(1, 10001)    // Generate 1 to 10000
                                      .map(i -> 10001 - i) // Reverse values
                                      .toArray();
        runTest(16, largeReverse, 10000);
        System.out.println("   → Large reverse sorted array: 10,000 elements");
        
        // Test Case 17: Large array with small unsorted middle
        // Most elements sorted, only 2 swapped in middle
        int[] largeMiddleSwap = IntStream.range(1, 10001).toArray();
        // Swap elements at position 5000 and 5001
        largeMiddleSwap[5000] = 5002;  // Put larger value first
        largeMiddleSwap[5001] = 5001;  // Put smaller value second
        runTest(17, largeMiddleSwap, 2);
        System.out.println("   → Large array with 2 swapped elements in middle");
        
        // Test Case 18: Very large array (100,000 elements)
        // Stress test beyond given constraints
        int[] veryLargeSorted = IntStream.range(1, 100001).toArray();
        runTest(18, veryLargeSorted, 0);
        System.out.println("   → Very large sorted array: 100,000 elements");
        
        // Test Case 19: Very large reverse array (100,000 elements)
        // Stress test with worst case
        int[] veryLargeReverse = IntStream.range(1, 100001)
                                          .map(i -> 100001 - i)
                                          .toArray();
        runTest(19, veryLargeReverse, 100000);
        System.out.println("   → Very large reverse array: 100,000 elements");
        
        // Test Case 20: Extreme values test
        // Testing with maximum and minimum allowed values
        int[] extremeValues = new int[1000];
        for (int i = 0; i < 1000; i++) {
            // Alternate between +100000 and -100000
            extremeValues[i] = (i % 2 == 0) ? 100000 : -100000;
        }
        runTest(20, extremeValues, 1000);
        System.out.println("   → Array with alternating extreme values (±100000)");
        
        // Print completion message
        System.out.println("\n╔═══════════════════════════════════════════════════════════════╗");
        System.out.println("║              ALL TEST CASES COMPLETED!                        ║");
        System.out.println("╚═══════════════════════════════════════════════════════════════╝");
    }
}