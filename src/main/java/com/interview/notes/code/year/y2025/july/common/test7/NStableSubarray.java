package com.interview.notes.code.year.y2025.july.common.test7;

import java.util.stream.IntStream;

public class NStableSubarray {

    // Main method to find longest N-stable subarray length
    public static int findLongestNStableSubarray(int[] arr, int N) {
        // Handle edge case where array is empty or has only one element
        if (arr == null || arr.length == 0) return 0;
        if (arr.length == 1) return 1;

        // Initialize variables to track maximum length and current window boundaries
        int maxLength = 1;          // Stores the longest valid subarray length found
        int start = 0;              // Start index of current window
        int currentLength = 1;      // Length of current window being examined

        // Iterate through array using sliding window approach
        for (int end = 1; end < arr.length; end++) {
            // Check if current window is N-stable by comparing with all elements in window
            boolean isStable = true;
            for (int i = start; i < end; i++) {
                // If difference between any two elements exceeds N, window is not stable
                if (Math.abs(arr[end] - arr[i]) > N) {
                    isStable = false;
                    break;
                }
            }

            if (isStable) {
                // If window is stable, increase current length and update max if needed
                currentLength++;
                maxLength = Math.max(maxLength, currentLength);
            } else {
                // If window becomes unstable, move start pointer and reset current length
                start = end;
                currentLength = 1;
            }
        }

        return maxLength;
    }

    // Main method for testing the solution
    public static void main(String[] args) {
        // Test Case 1: Basic case
        test(new int[]{4, 2, 3, 6, 2, 2, 3, 2, 7}, 1, 4);

        // Test Case 2: Small array
        test(new int[]{8, 2, 4, 7}, 4, 2);

        // Test Case 3: Larger array with larger N
        test(new int[]{1, 3, 4, 5, 6, 7, 8, 9, 9, 9}, 7, 9);

        // Test Case 4: Edge case - single element
        test(new int[]{1}, 5, 1);

        // Test Case 5: Edge case - all same elements
        test(new int[]{2, 2, 2, 2}, 1, 4);

        // Test Case 6: Large array test (generated dynamically)
        int[] largeArray = IntStream.range(0, 100000)
                .map(i -> i % 10)
                .toArray();
        test(largeArray, 2, 10);
    }

    // Helper method to run test cases and verify results
    private static void test(int[] arr, int N, int expectedResult) {
        int result = findLongestNStableSubarray(arr, N);
        String status = result == expectedResult ? "PASS" : "FAIL";
        System.out.printf("Test Case: Array length=%d, N=%d, Expected=%d, Got=%d -> %s%n",
                arr.length, N, expectedResult, result, status);
    }
}
