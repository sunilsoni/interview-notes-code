package com.interview.notes.code.year.y2025.may.meta.test2;

import java.util.Arrays;

public class RollingWindowAverageBruteForce {

    // Brute force method to calculate rolling window averages
    public static double[] calculateAveragesBruteForce(int[] arr, int windowSize) {
        // Step 1: Input validation
        if (arr == null || arr.length == 0 || windowSize <= 0 || windowSize > arr.length) {
            return new double[0];
        }

        // Step 2: Calculate size of result array
        // For array of size n and window size w, we'll have (n-w+1) windows
        int resultSize = arr.length - windowSize + 1;
        double[] result = new double[resultSize];

        // Step 3: Calculate average for each window position
        // Outer loop: moves the window start position
        for (int i = 0; i < resultSize; i++) {
            double sum = 0;

            // Inner loop: calculates sum for current window
            for (int j = 0; j < windowSize; j++) {
                sum += arr[i + j];
            }

            // Calculate average for current window
            result[i] = sum / windowSize;
        }

        return result;
    }

    // Main method with test cases
    public static void main(String[] args) {
        // Test Case 1: Basic example
        testCase(
                new int[]{1, 2, 3, 4, 5},
                3,
                "Basic Test",
                new double[]{2.0, 3.0, 4.0}
        );

        // Test Case 2: Single window
        testCase(
                new int[]{1, 2, 3},
                3,
                "Single Window Test",
                new double[]{2.0}
        );

        // Test Case 3: Empty array
        testCase(
                new int[]{},
                3,
                "Empty Array Test",
                new double[]{}
        );

        // Test Case 4: Window size equals 1
        testCase(
                new int[]{1, 2, 3},
                1,
                "Window Size 1 Test",
                new double[]{1.0, 2.0, 3.0}
        );

        // Test Case 5: Large array test
        int[] largeArray = new int[1000];
        for (int i = 0; i < largeArray.length; i++) {
            largeArray[i] = i;
        }
        testCase(
                largeArray,
                100,
                "Large Array Test",
                null  // Not checking exact values for large array
        );
    }

    // Helper method to test and print results
    private static void testCase(int[] input, int windowSize, String testName, double[] expected) {
        System.out.println("Running: " + testName);
        System.out.println("Input Array: " + Arrays.toString(input));
        System.out.println("Window Size: " + windowSize);

        double[] result = calculateAveragesBruteForce(input, windowSize);
        System.out.println("Output: " + Arrays.toString(result));

        boolean passed = true;
        if (expected != null) {
            if (result.length != expected.length) {
                passed = false;
            } else {
                for (int i = 0; i < expected.length; i++) {
                    if (Math.abs(result[i] - expected[i]) > 0.0001) {
                        passed = false;
                        break;
                    }
                }
            }
        }

        System.out.println("Test Result: " + (passed ? "PASSED" : "FAILED"));
        System.out.println();
    }
}
