package com.interview.notes.code.year.y2025.april.common.test2;

import java.util.Arrays;

public class MaxNonAdjacentSum {
    
    public static int findMaxSum(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        if (arr.length == 1) return arr[0];
        
        int[] dp = new int[arr.length];
        dp[0] = arr[0];
        dp[1] = Math.max(arr[0], arr[1]);
        
        for (int i = 2; i < arr.length; i++) {
            dp[i] = Math.max(dp[i-1], dp[i-2] + arr[i]);
        }
        
        return dp[arr.length - 1];
    }
    
    public static void main(String[] args) {
        // Test cases from the image
        runTest(new int[]{1, 3, 5, 1}, 6, "Basic test case 1");
        runTest(new int[]{1, 3, 5, 1, 1}, 7, "Test case 2");
        runTest(new int[]{3, 1, 1, 5, 1, 1}, 9, "Test case 3");
        
        // Additional edge cases
        runTest(new int[]{1}, 1, "Single element");
        runTest(new int[]{}, 0, "Empty array");
        runTest(new int[]{2, 4}, 4, "Two elements");
        runTest(new int[]{5, 5, 10, 100, 10, 5}, 110, "Longer array");
        runTest(new int[]{1, 2, 3, 4, 5}, 9, "Sequential numbers");
        
        // Large array performance test
        int[] largeArray = new int[1000000];
        for (int i = 0; i < largeArray.length; i++) {
            largeArray[i] = i + 1;
        }
        runTest(largeArray, 250000500000L, "Large array test");
        
        // Print explanation for first test case
        explainSolution(new int[]{1, 3, 5, 1});
    }
    
    private static void runTest(int[] input, long expected, String testName) {
        long result = findMaxSum(input);
        boolean passed = result == expected;
        System.out.println(testName + ": " + (passed ? "PASS" : "FAIL"));
        if (!passed) {
            System.out.println("Expected: " + expected + ", Got: " + result);
        }
    }
    
    private static void explainSolution(int[] arr) {
        System.out.println("\nDetailed explanation for array: " + Arrays.toString(arr));
        System.out.println("Maximum sum: " + findMaxSum(arr));
        System.out.println("To achieve this sum:");
        System.out.println("- Can select 1 (index 0)");
        System.out.println("- Cannot select 3 (index 1) as it's adjacent to 1");
        System.out.println("- Can select 5 (index 2)");
        System.out.println("- Cannot select 1 (index 3) as it's adjacent to 5");
        System.out.println("Final sum: 1 + 5 = 6");
    }
}
