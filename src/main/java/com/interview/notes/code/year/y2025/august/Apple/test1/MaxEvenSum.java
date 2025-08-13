package com.interview.notes.code.year.y2025.august.Apple.test1;

import java.util.Arrays;

public class MaxEvenSum {
    
    // Main method to find maximum even sum of two different elements
    public static int findMaxEvenSum(int[] arr) {
        // Check if array has less than 2 elements
        if (arr == null || arr.length < 2) {
            return -1;
        }

        // Sort array in descending order using streams
        int[] sortedArr = Arrays.stream(arr)
                               .boxed()
                               .sorted((a, b) -> b - a)
                               .mapToInt(Integer::intValue)
                               .toArray();

        // Iterate through sorted array to find maximum even sum
        for (int i = 0; i < sortedArr.length - 1; i++) {
            for (int j = i + 1; j < sortedArr.length; j++) {
                // Calculate sum of current pair
                int sum = sortedArr[i] + sortedArr[j];
                // If sum is even, return it (it will be maximum due to sorted array)
                if (sum % 2 == 0) {
                    return sum;
                }
            }
        }
        
        // Return -1 if no even sum found
        return -1;
    }

    // Test method to verify solution
    public static void main(String[] args) {
        // Test case 1: Normal case
        test(new int[]{5, 1, 3, 4, 2}, 8, "Test 1: Normal case");
        
        // Test case 2: No even sum possible
        test(new int[]{0, 1}, -1, "Test 2: No even sum possible");
        
        // Test case 3: Empty array
        test(new int[]{}, -1, "Test 3: Empty array");
        
        // Test case 4: Single element
        test(new int[]{1}, -1, "Test 4: Single element");
        
        // Test case 5: All even numbers
        test(new int[]{2, 4, 6, 8}, 14, "Test 5: All even numbers");
        
        // Test case 6: All odd numbers
        test(new int[]{1, 3, 5, 7}, 12, "Test 6: All odd numbers");
        
        // Test case 7: Large numbers
        test(new int[]{1000000, 999999, 999998, 999997}, 1999998, 
             "Test 7: Large numbers");
        
        // Test case 8: Negative numbers
        test(new int[]{-1, -2, -3, -4}, -3, "Test 8: Negative numbers");
    }

    // Helper method to run test cases
    private static void test(int[] input, int expected, String testName) {
        int result = findMaxEvenSum(input);
        boolean passed = result == expected;
        System.out.println(testName + ": " + 
            (passed ? "PASSED" : "FAILED") + 
            " (Expected: " + expected + ", Got: " + result + ")");
    }
}
