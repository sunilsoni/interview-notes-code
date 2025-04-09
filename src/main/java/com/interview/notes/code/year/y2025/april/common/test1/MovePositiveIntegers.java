package com.interview.notes.code.year.y2025.april.common.test1;

import java.util.Arrays;

public class MovePositiveIntegers {

    public static int[] movePositiveToFront(int[] arr) {
        // Edge case handling
        if (arr == null || arr.length <= 1) {
            return arr;
        }

        // Using two-pointer technique
        int writeIndex = 0;

        // First pass: Copy positive integers
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > 0) {
                int temp = arr[writeIndex];
                arr[writeIndex] = arr[i];
                arr[i] = temp;
                writeIndex++;
            }
        }

        return arr;
    }

    public static void main(String[] args) {
        // Test cases
        runTest(new int[]{6, 3, 0, 1, 0}, new int[]{6, 3, 1, 0, 0}, "Basic case");
        runTest(new int[]{0, 0, 0, 0}, new int[]{0, 0, 0, 0}, "All zeros");
        runTest(new int[]{1, 2, 3, 4}, new int[]{1, 2, 3, 4}, "All positive");
        runTest(new int[]{}, new int[]{}, "Empty array");
        runTest(new int[]{0}, new int[]{0}, "Single zero");
        runTest(new int[]{1}, new int[]{1}, "Single positive");

        // Large data test
        int[] largeInput = new int[100000];
        for (int i = 0; i < largeInput.length; i++) {
            largeInput[i] = i % 2 == 0 ? 0 : i;
        }
        long startTime = System.currentTimeMillis();
        movePositiveToFront(largeInput);
        long endTime = System.currentTimeMillis();
        System.out.println("Large data test (100000 elements) completed in: " +
                (endTime - startTime) + "ms");
    }

    private static void runTest(int[] input, int[] expected, String testName) {
        int[] original = input.clone();
        int[] result = movePositiveToFront(input);
        boolean passed = Arrays.equals(result, expected);

        System.out.println("Test: " + testName);
        System.out.println("Input: " + Arrays.toString(original));
        System.out.println("Output: " + Arrays.toString(result));
        System.out.println("Expected: " + Arrays.toString(expected));
        System.out.println("Status: " + (passed ? "PASS" : "FAIL"));
        System.out.println();
    }
}
