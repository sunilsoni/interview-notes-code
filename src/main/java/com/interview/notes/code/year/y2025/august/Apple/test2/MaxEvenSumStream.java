package com.interview.notes.code.year.y2025.august.Apple.test2;

import java.util.Arrays;
import java.util.Optional;

public class MaxEvenSumStream {

    // Method to find maximum even sum using Streams
    public static int findMaxEvenSum(int[] arr) {
        // Check if array is null or has less than 2 elements
        if (arr == null || arr.length < 2) {
            return -1;
        }

        // Convert array to stream and store in list for multiple operations
        return Arrays.stream(arr)
                // Convert to boxed stream for more operations
                .boxed()
                // Get all possible pairs using flatMap
                .flatMap(i -> Arrays.stream(arr)
                        .boxed()
                        // Filter to ensure we only get pairs of different elements
                        .filter(j -> !i.equals(j))
                        // Map to sum of pairs
                        .map(j -> i + j))
                // Filter for even sums only
                .filter(sum -> sum % 2 == 0)
                // Find maximum value
                .max(Integer::compareTo)
                // Return -1 if no even sum found
                .orElse(-1);
    }

    // Alternative implementation using more efficient approach
    public static int findMaxEvenSumOptimized(int[] arr) {
        if (arr == null || arr.length < 2) {
            return -1;
        }

        // Find largest even and odd numbers
        Optional<Integer> maxEven = Arrays.stream(arr)
                .boxed()
                .filter(n -> n % 2 == 0)
                .max(Integer::compareTo);

        Optional<Integer> secondMaxEven = Arrays.stream(arr)
                .boxed()
                .filter(n -> n % 2 == 0)
                .sorted((a, b) -> b - a)
                .skip(1)
                .findFirst();

        Optional<Integer> maxOdd = Arrays.stream(arr)
                .boxed()
                .filter(n -> n % 2 != 0)
                .max(Integer::compareTo);

        Optional<Integer> secondMaxOdd = Arrays.stream(arr)
                .boxed()
                .filter(n -> n % 2 != 0)
                .sorted((a, b) -> b - a)
                .skip(1)
                .findFirst();

        // Calculate possible even sums
        int evenSum = maxEven.isPresent() && secondMaxEven.isPresent() ?
                maxEven.get() + secondMaxEven.get() : Integer.MIN_VALUE;

        int oddSum = maxOdd.isPresent() && secondMaxOdd.isPresent() ?
                maxOdd.get() + secondMaxOdd.get() : Integer.MIN_VALUE;

        // Return maximum even sum or -1 if none found
        return Math.max(evenSum, oddSum) == Integer.MIN_VALUE ? -1 :
                Math.max(evenSum, oddSum);
    }

    // Test method
    public static void main(String[] args) {
        // Test cases with descriptive names and expected results
        testCase(new int[]{5, 1, 3, 4, 2}, 8, "Basic positive numbers");
        testCase(new int[]{0, 1}, -1, "No even sum possible");
        testCase(new int[]{}, -1, "Empty array");
        testCase(new int[]{1}, -1, "Single element");
        testCase(new int[]{2, 4, 6, 8}, 14, "All even numbers");
        testCase(new int[]{1, 3, 5, 7}, 12, "All odd numbers");
        testCase(new int[]{1000000, 999999, 999998, 999997}, 1999998,
                "Large numbers");
        testCase(new int[]{-1, -2, -3, -4}, -3, "Negative numbers");
    }

    // Helper method to run test cases
    private static void testCase(int[] input, int expected, String testName) {
        // Test both implementations
        int result1 = findMaxEvenSum(input);
        int result2 = findMaxEvenSumOptimized(input);

        // Verify results
        System.out.println("Test: " + testName);
        System.out.println("Stream Version: " +
                (result1 == expected ? "PASSED" : "FAILED") +
                " (Expected: " + expected + ", Got: " + result1 + ")");
        System.out.println("Optimized Version: " +
                (result2 == expected ? "PASSED" : "FAILED") +
                " (Expected: " + expected + ", Got: " + result2 + ")");
        System.out.println("------------------------");
    }
}
