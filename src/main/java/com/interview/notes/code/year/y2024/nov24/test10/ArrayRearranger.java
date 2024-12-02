package com.interview.notes.code.year.y2024.nov24.test10;

import java.util.Arrays;

public class ArrayRearranger {

    public static int[] rearrangeArray(int[] arr) {
        if (arr == null || arr.length <= 2) {
            return arr;
        }

        int n = arr.length;
        int[] result = new int[n];
        int index = 0;

        for (int i = 0; i < n; i++) {
            if (i < 2 || arr[i] != result[index - 1] || arr[i] != result[index - 2]) {
                result[index++] = arr[i];
            } else {
                // Find the next different number
                int j = i + 1;
                while (j < n && arr[j] == arr[i]) {
                    j++;
                }
                if (j < n) {
                    result[index++] = arr[j];
                    arr[j] = arr[i]; // Swap to maintain sorting
                }
            }
        }

        // Fill remaining positions with original elements
        while (index < n) {
            result[index] = arr[index];
            index++;
        }

        return result;
    }

    public static void main(String[] args) {
        // Test cases
        int[][] testCases = {
                {1, 1, 2, 2, 2, 3, 4, 4, 4, 4, 5},
                {1, 1, 1, 2, 2, 2, 3, 3, 3},
                {1, 2, 3, 4, 5},
                {1, 1, 1, 1, 1},
                {},
                {1},
                {1, 1},
                {1, 1, 1},
                {1, 2, 2, 2, 3, 3, 3, 4, 4, 4, 5, 5, 5}
        };

        for (int i = 0; i < testCases.length; i++) {
            int[] input = testCases[i];
            int[] output = rearrangeArray(input);
            boolean passed = validateOutput(output);

            System.out.println("Test Case " + (i + 1) + ":");
            System.out.println("Input:  " + Arrays.toString(input));
            System.out.println("Output: " + Arrays.toString(output));
            System.out.println("Result: " + (passed ? "PASS" : "FAIL"));
            System.out.println();
        }

        // Large data input test
        int[] largeInput = new int[1000000];
        for (int i = 0; i < largeInput.length; i++) {
            largeInput[i] = i / 1000; // Creates groups of 1000 repeated numbers
        }
        long startTime = System.currentTimeMillis();
        int[] largeOutput = rearrangeArray(largeInput);
        long endTime = System.currentTimeMillis();
        boolean largePassed = validateOutput(largeOutput);

        System.out.println("Large Data Test:");
        System.out.println("Input size: " + largeInput.length);
        System.out.println("Execution time: " + (endTime - startTime) + " ms");
        System.out.println("Result: " + (largePassed ? "PASS" : "FAIL"));
    }

    private static boolean validateOutput(int[] arr) {
        if (arr == null || arr.length <= 2) {
            return true;
        }
        for (int i = 0; i < arr.length - 2; i++) {
            if (arr[i] == arr[i + 1] && arr[i] == arr[i + 2]) {
                return false;
            }
        }
        return true;
    }
}
