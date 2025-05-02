package com.interview.notes.code.year.y2025.may.meta.test1;

import java.util.Arrays;

public class RollingWindowAverage {
    
    // Method to calculate rolling window averages
    public static double[] calculateWindowAverages(int[] numbers, int windowSize) {
        // Check for invalid inputs
        if (numbers == null || numbers.length == 0 || windowSize <= 0) {
            return new double[0];
        }
        
        // If window size is larger than array, return empty result
        if (windowSize > numbers.length) {
            return new double[0];
        }
        
        // Calculate how many windows we'll have
        int resultSize = numbers.length - windowSize + 1;
        double[] result = new double[resultSize];
        
        // For each window position
        for (int i = 0; i < resultSize; i++) {
            double sum = 0;
            // Calculate sum for current window
            for (int j = 0; j < windowSize; j++) {
                sum += numbers[i + j];
            }
            // Calculate average and store in result
            result[i] = sum / windowSize;
        }
        
        return result;
    }

    // Main method for testing
    public static void main(String[] args) {
        // Test Case 1: Normal case
        test(new int[]{1, 2, 3, 4, 5}, 3, new double[]{2.0, 3.0, 4.0});
        
        // Test Case 2: Window size equals array length
        test(new int[]{1, 2, 3}, 3, new double[]{2.0});
        
        // Test Case 3: Empty array
        test(new int[]{}, 3, new double[]{});
        
        // Test Case 4: Window size larger than array
        test(new int[]{1, 2}, 3, new double[]{});
        
        // Test Case 5: Large data
        int[] largeArray = new int[10000];
        for (int i = 0; i < largeArray.length; i++) {
            largeArray[i] = i;
        }
        test(largeArray, 100, null); // We don't verify the exact values here
    }
    
    // Helper method to test and print results
    private static void test(int[] input, int windowSize, double[] expected) {
        double[] result = calculateWindowAverages(input, windowSize);
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
        
        System.out.println("Test Case: " + (passed ? "PASSED" : "FAILED"));
        System.out.println("Input: " + Arrays.toString(input));
        System.out.println("Window Size: " + windowSize);
        System.out.println("Result: " + Arrays.toString(result));
        System.out.println();
    }
}
