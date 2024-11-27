package com.interview.notes.code.months.nov24.test15;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
/*

find the number of pairs of integers in the array whose sum is equal to output in a single loop.
arr[] = {1, 5, 7, -1, 5}, output = 6
 */
public class PairSumFinder {
    
    // Method to find count of pairs with given sum
    public static int findPairCount(int[] arr, int targetSum) {
        // Handle edge cases
        if (arr == null || arr.length < 2) {
            return 0;
        }
        
        // Use HashMap to store frequency of numbers
        Map<Integer, Integer> numFrequency = new HashMap<>();
        int pairCount = 0;
        
        // Single loop solution
        for (int num : arr) {
            int complement = targetSum - num;
            
            // If complement exists, add its frequency to pair count
            if (numFrequency.containsKey(complement)) {
                pairCount += numFrequency.get(complement);
            }
            
            // Update frequency of current number
            numFrequency.put(num, numFrequency.getOrDefault(num, 0) + 1);
        }
        
        return pairCount;
    }
    
    // Test method
    public static void runTest(int[] arr, int targetSum, int expectedResult, String testName) {
        int result = findPairCount(arr, targetSum);
        System.out.printf("Test %s: %s (Expected: %d, Got: %d)%n",
                testName,
                result == expectedResult ? "PASS" : "FAIL",
                expectedResult,
                result);
    }
    
    public static void main(String[] args) {
        // Test Case 1: Given example
        runTest(new int[]{1, 5, 7, -1, 5}, 6, 3, "Basic Case");
        
        // Test Case 2: Empty array
        runTest(new int[]{}, 6, 0, "Empty Array");
        
        // Test Case 3: Single element
        runTest(new int[]{1}, 6, 0, "Single Element");
        
        // Test Case 4: All pairs sum to target
        runTest(new int[]{3, 3, 3, 3}, 6, 6, "Multiple Same Pairs");
        
        // Test Case 5: No pairs sum to target
        runTest(new int[]{1, 2, 3, 4}, 10, 0, "No Valid Pairs");
        
        // Test Case 6: Negative numbers
        runTest(new int[]{-1, -5, 6, 7, -1}, -2, 1, "Negative Numbers");
        
        // Test Case 7: Large array (stress test)
        int[] largeArray = new int[100000];
        Arrays.fill(largeArray, 3);
        runTest(largeArray, 6, (largeArray.length * (largeArray.length - 1)) / 2, "Large Array");
    }
}
