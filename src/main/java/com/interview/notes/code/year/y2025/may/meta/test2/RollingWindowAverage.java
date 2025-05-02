package com.interview.notes.code.year.y2025.may.meta.test2;

import java.util.Arrays;

public class RollingWindowAverage {
    
    // Main method to calculate rolling window averages
    public static double[] calculateAverages(int[] arr, int windowSize) {
        // Handle invalid inputs
        if (arr == null || arr.length == 0 || windowSize <= 0 || windowSize > arr.length) {
            return new double[0];
        }

        // Calculate result array size
        int resultSize = arr.length - windowSize + 1;
        double[] result = new double[resultSize];

        // Calculate first window sum
        double windowSum = 0;
        for (int i = 0; i < windowSize; i++) {
            windowSum += arr[i];
        }
        
        // Store first window average
        result[0] = windowSum / windowSize;

        // Calculate remaining windows using sliding window
        for (int i = 1; i < resultSize; i++) {
            // Subtract first element of previous window
            windowSum = windowSum - arr[i - 1];
            // Add last element of current window
            windowSum = windowSum + arr[i + windowSize - 1];
            // Calculate and store average
            result[i] = windowSum / windowSize;
        }

        return result;
    }

    // Test method
    public static void main(String[] args) {
        // Test Case 1: Basic case
        testCase(
            new int[]{1, 2, 3, 4, 5}, 
            3, 
            new double[]{2.0, 3.0, 4.0},
            "Basic case"
        );

        // Test Case 2: Window size equals array length
        testCase(
            new int[]{1, 2, 3}, 
            3, 
            new double[]{2.0},
            "Window equals array length"
        );

        // Test Case 3: Empty array
        testCase(
            new int[]{}, 
            3, 
            new double[]{},
            "Empty array"
        );

        // Test Case 4: Window size larger than array
        testCase(
            new int[]{1, 2}, 
            3, 
            new double[]{},
            "Window larger than array"
        );

        // Test Case 5: Large array test
        int[] largeArray = new int[1000];
        for (int i = 0; i < largeArray.length; i++) {
            largeArray[i] = i;
        }
        testCase(
            largeArray, 
            100, 
            null,  // Not checking exact values for large array
            "Large array test"
        );
    }

    // Helper method to test and print results
    private static void testCase(int[] input, int windowSize, double[] expected, String testName) {
        System.out.println("Running test: " + testName);
        System.out.println("Input: " + Arrays.toString(input));
        System.out.println("Window size: " + windowSize);

        double[] result = calculateAverages(input, windowSize);
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

        System.out.println("Test " + (passed ? "PASSED" : "FAILED"));
        System.out.println();
    }
}
