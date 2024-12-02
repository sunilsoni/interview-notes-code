package com.interview.notes.code.year.y2024.nov24.test11;

import java.util.Arrays;

public class ArrayRearranger {

    public static int[] rearrangeArray(int[] arr) {
        if (arr == null || arr.length <= 2) {
            return arr;
        }

        int n = arr.length;
        int[] result = new int[n];
        int index = 0;

        // First pass: Place elements while maintaining order and avoiding more than 2 consecutive repeats
        for (int i = 0; i < n; i++) {
            if (index <= 1 || arr[i] != result[index - 1] || arr[i] != result[index - 2]) {
                result[index++] = arr[i];
            }
        }

        // Second pass: Place remaining elements at the end
        for (int i = 0; i < n; i++) {
            if (index == n) break;
            if (arr[i] != result[index - 1] || (index > 1 && arr[i] != result[index - 2])) {
                result[index++] = arr[i];
            }
        }

        return result;
    }

    public static void main(String[] args) {
        // Test cases
        int[][] testCases = {
                {1, 1, 2, 2, 2, 3, 4, 4, 4, 4, 5},
                {1, 1, 1, 2, 2, 3, 3, 3},
                {1, 2, 3, 4, 5},
                {1, 1, 1, 1, 1},
                {1},
                {},
                {1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 4, 5, 5, 5}
        };

        for (int i = 0; i < testCases.length; i++) {
            System.out.println("Test Case " + (i + 1) + ":");
            System.out.println("Input: " + Arrays.toString(testCases[i]));
            int[] result = rearrangeArray(testCases[i]);
            System.out.println("Output: " + Arrays.toString(result));

            boolean passed = validateResult(result);
            System.out.println(passed ? "PASS" : "FAIL");
            System.out.println();
        }
    }

    private static boolean validateResult(int[] arr) {
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
