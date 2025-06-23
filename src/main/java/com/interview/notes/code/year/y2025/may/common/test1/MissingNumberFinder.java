package com.interview.notes.code.year.y2025.may.common.test1;

import java.util.Arrays;

public class MissingNumberFinder {

    /**
     * Finds the missing number in an array containing numbers from 1 to n
     * Uses XOR operation for optimal space complexity
     *
     * @param arr Input array with one missing number
     * @return The missing number
     */
    public static int findMissingNumber(int[] arr) {
        // Handle null or empty array
        if (arr == null || arr.length == 0) {
            return 1;  // Assuming 1 is missing in empty array
        }

        // Length of input array
        int n = arr.length + 1;  // Total numbers should be from 1 to n

        // XOR all numbers from 1 to n
        int xorSum = 1;
        for (int i = 2; i <= n; i++) {
            xorSum ^= i;
        }

        // XOR with all array elements
        for (int num : arr) {
            xorSum ^= num;
        }

        // Final XOR result is the missing number
        return xorSum;
    }

    /**
     * Alternative method using sum formula
     * May have integer overflow for large numbers
     */
    public static int findMissingNumberUsingSum(int[] arr) {
        // Handle null or empty array
        if (arr == null || arr.length == 0) {
            return 1;
        }

        int n = arr.length + 1;
        // Calculate expected sum using formula n*(n+1)/2
        int expectedSum = (n * (n + 1)) / 2;

        // Calculate actual sum
        int actualSum = 0;
        for (int num : arr) {
            actualSum += num;
        }

        // Difference is the missing number
        return expectedSum - actualSum;
    }

    public static void main(String[] args) {
        // Test cases
        TestCase[] testCases = {
                new TestCase(new int[]{1, 2, 4, 5}, 3),
                new TestCase(new int[]{1, 2, 3, 5}, 4),
                new TestCase(new int[]{2, 3, 4, 5}, 1),
                new TestCase(new int[]{1, 3, 4, 5}, 2),
                new TestCase(new int[]{}, 1),
                new TestCase(null, 1),
                // Large array test
                new TestCase(createLargeArray(1000000, 500000), 500000)
        };

        // Run all test cases
        for (TestCase test : testCases) {
            // Test XOR method
            long startTime = System.nanoTime();
            int result = findMissingNumber(test.input);
            long endTime = System.nanoTime();

            System.out.println("Input: " + Arrays.toString(test.input));
            System.out.printf("Expected: %d, Got: %d, Test: %s%n",
                    test.expected, result, result == test.expected ? "PASS" : "FAIL");
            System.out.printf("Time taken: %.3f ms%n", (endTime - startTime) / 1_000_000.0);
            System.out.println("-------------------");
        }
    }

    // Helper method to create large test array
    private static int[] createLargeArray(int size, int missing) {
        int[] arr = new int[size - 1];
        int index = 0;
        for (int i = 1; i <= size; i++) {
            if (i != missing) {
                arr[index++] = i;
            }
        }
        return arr;
    }

    // Test case class
    static class TestCase {
        int[] input;
        int expected;

        TestCase(int[] input, int expected) {
            this.input = input;
            this.expected = expected;
        }
    }
}
