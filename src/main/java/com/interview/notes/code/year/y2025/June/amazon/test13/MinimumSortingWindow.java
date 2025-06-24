package com.interview.notes.code.year.y2025.June.amazon.test13;

import java.util.Arrays;

public class MinimumSortingWindow {

    public static int[] findMinSortingWindows(int[] nums, int k) {
        // Edge case check for invalid inputs
        if (nums == null || nums.length < k || k <= 0) {
            return new int[0];
        }

        // Calculate result array size based on sliding window
        int resultSize = nums.length - k + 1;
        int[] result = new int[resultSize];

        // Process each window of size k
        for (int i = 0; i <= nums.length - k; i++) {
            // Create a copy of current window for sorting comparison
            int[] window = Arrays.copyOfRange(nums, i, i + k);
            int[] sortedWindow = Arrays.copyOf(window, window.length);
            Arrays.sort(sortedWindow);

            // Find minimum sorting window size
            result[i] = findMinSortLength(window, sortedWindow);
        }

        return result;
    }

    private static int findMinSortLength(int[] original, int[] sorted) {
        // If arrays are equal, no sorting needed
        if (Arrays.equals(original, sorted)) {
            return 0;
        }

        // Find leftmost mismatch
        int left = 0;
        while (left < original.length && original[left] == sorted[left]) {
            left++;
        }

        // Find rightmost mismatch
        int right = original.length - 1;
        while (right >= 0 && original[right] == sorted[right]) {
            right--;
        }

        // Return length of segment that needs sorting
        return right - left + 1;
    }

    // Main method for testing
    public static void main(String[] args) {
        // Test cases
        runTest(new int[]{1, 3, 2, 4, 5}, 3, new int[]{2, 2, 0}, "Test Case 1");
        runTest(new int[]{5, 4, 3, 2, 1}, 4, new int[]{4, 4}, "Test Case 2");

        // Edge cases
        runTest(new int[]{1, 2, 3}, 3, new int[]{0}, "Already Sorted");
        runTest(new int[]{3, 2, 1}, 3, new int[]{3}, "Reverse Sorted");

        // Large input test
        int[] largeInput = generateLargeInput(10000);
        long startTime = System.currentTimeMillis();
        findMinSortingWindows(largeInput, 1000);
        long endTime = System.currentTimeMillis();
        System.out.println("Large input (10000 elements) processing time: " +
                (endTime - startTime) + "ms");
    }

    private static void runTest(int[] nums, int k, int[] expected, String testName) {
        int[] result = findMinSortingWindows(nums, k);
        boolean passed = Arrays.equals(result, expected);
        System.out.println(testName + ": " + (passed ? "PASS" : "FAIL"));
        if (!passed) {
            System.out.println("Expected: " + Arrays.toString(expected));
            System.out.println("Got: " + Arrays.toString(result));
        }
    }

    private static int[] generateLargeInput(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = (int) (Math.random() * size);
        }
        return array;
    }
}
