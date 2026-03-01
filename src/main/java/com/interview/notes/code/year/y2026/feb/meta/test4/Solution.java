package com.interview.notes.code.year.y2026.feb.meta.test4;

import java.util.stream.IntStream;

public class Solution {

    // Optimal Binary Search approach for large data (O(log N) time)
    public static int findFirstGreater(int[] arr, int k) {
        // Initialize answer to -1, which acts as our default return if no element > K is found.
        var ans = -1;
        // Set the starting index 'low' to the very beginning of the array.
        var low = 0;
        // Set the ending index 'high' to the very last position in the array.
        var high = arr.length - 1;

        // Loop continuously as long as the search space is valid (low hasn't crossed high).
        while (low <= high) {
            // Calculate the middle index safely to prevent integer overflow on massive arrays.
            var mid = low + (high - low) / 2;

            // Check if the current middle element is strictly greater than our target K.
            if (arr[mid] > k) {
                // If it is greater, save this index as it is a valid candidate for the answer.
                ans = mid;
                // Narrow the search to the left half to find if there's an even earlier valid element.
                high = mid - 1;
            } else {
                // If it is less than or equal to K, the answer must exist in the right half.
                low = mid + 1;
            }
        }
        // Return the recorded index, or -1 if the condition was never met.
        return ans;
    }

    // Java 8 Stream API approach (O(N) time - included for completeness, but avoid for large data)
    public static int findFirstGreaterStream(int[] arr, int k) {
        // Create an integer stream representing all valid index numbers from 0 to array length.
        return IntStream.range(0, arr.length)
                // Keep only the indices where the value in the array is strictly greater than K.
                .filter(i -> arr[i] > k)
                // Grab the very first index that survived the filter.
                .findFirst()
                // If the stream is empty (no elements matched), default to returning -1.
                .orElse(-1);
    }

    // Main method to act as our custom testing framework without using JUnit.
    public static void main(String[] args) {
        // Run standard test case: target is in the middle of the array.
        runTest("Standard Case", new int[]{1, 2, 4, 5, 8}, 3, 2);
        
        // Run test case where the target K is larger than everything in the array.
        runTest("Not Found", new int[]{1, 2, 3}, 5, -1);
        
        // Run test case where the target K is smaller than everything in the array.
        runTest("All Greater", new int[]{5, 6, 7}, 2, 0);
        
        // Run test case to ensure duplicates are handled correctly (target 2).
        runTest("Duplicates", new int[]{2, 2, 2, 4, 4}, 2, 3);
        
        // Run test case on an entirely empty array to ensure no out-of-bounds errors.
        runTest("Empty Array", new int[]{}, 1, -1);

        // Generate a massive array of 10 million elements to test the large data requirement.
        var largeArray = new int[10_000_000];
        // Populate the massive array using a loop.
        for (var i = 0; i < largeArray.length; i++) {
            // Assign each index a value equal to double its index to keep it sorted.
            largeArray[i] = i * 2; 
        }
        // Run the large data test case looking for the first element greater than 9,999,999.
        runTest("Large Data", largeArray, 9_999_999, 5_000_000);
    }

    // Helper method to execute a test, compare results, and print PASS or FAIL.
    private static void runTest(String testName, int[] arr, int k, int expected) {
        // Call our optimal binary search method to get the actual calculated result.
        var actual = findFirstGreater(arr, k);
        // Determine if the actual result matches the expected result.
        var passed = (actual == expected);
        // Print the test name, expected output, actual output, and the final PASS/FAIL status.
        System.out.printf("%-15s | Expected: %2d | Actual: %2d | Result: %s%n", testName, expected, actual, passed ? "PASS" : "FAIL");
    }
}