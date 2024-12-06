package com.interview.notes.code.months.dec24.test6;

import java.util.Arrays;

public class RotatedArraySearch1 {

    /**
     * Finds the index of a key in a sorted and rotated array.
     *
     * @param arr The sorted and rotated array.
     * @param key The key to search for.
     * @return The index of the key if found; otherwise, -1.
     */
    public static int search(int[] arr, int key) {
        if (arr == null || arr.length == 0)
            return -1;

        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (arr[mid] == key)
                return mid;

            // Determine which side is properly sorted
            if (arr[low] <= arr[mid]) { // Left side is sorted
                if (key >= arr[low] && key < arr[mid]) {
                    high = mid - 1; // Search left
                } else {
                    low = mid + 1; // Search right
                }
            } else { // Right side is sorted
                if (key > arr[mid] && key <= arr[high]) {
                    low = mid + 1; // Search right
                } else {
                    high = mid - 1; // Search left
                }
            }
        }

        return -1; // Key not found
    }

    /**
     * Runs predefined test cases and outputs pass/fail results.
     */
    public static void main(String[] args) {
        int testCaseNumber = 1;

        // Define test cases as arrays and keys with expected results
        TestCase[] testCases = new TestCase[]{
                new TestCase(new int[]{4, 5, 6, 7, 0, 1, 2}, 0, 4),
                new TestCase(new int[]{4, 5, 6, 7, 0, 1, 2}, 3, -1),
                new TestCase(new int[]{1}, 0, -1),
                new TestCase(new int[]{1}, 1, 0),
                new TestCase(new int[]{}, 1, -1),
                new TestCase(new int[]{1, 2, 3, 4, 5, 6, 7}, 5, 4),
                new TestCase(new int[]{6, 7, 1, 2, 3, 4, 5}, 6, 0),
                new TestCase(generateLargeArray(1000000), 999999, 999999),
                new TestCase(generateLargeArray(1000000), -1, -1),
                new TestCase(new int[]{5, 1, 2, 3, 4}, 5, 0),
                new TestCase(new int[]{5, 1, 2, 3, 4}, 1, 1),
                new TestCase(new int[]{5, 1, 2, 3, 4}, 4, 4),
                new TestCase(new int[]{5, 1, 2, 3, 4}, 6, -1),
                new TestCase(new int[]{2, 3, 4, 5, 6, 7, 8, 1}, 1, 7),
                new TestCase(new int[]{2, 3, 4, 5, 6, 7, 8, 1}, 8, 6)
        };

        for (TestCase testCase : testCases) {
            int result = search(testCase.array, testCase.key);
            boolean passed = result == testCase.expectedIndex;
            System.out.println("Test Case " + testCaseNumber++ + ": " + (passed ? "PASS" : "FAIL"));
            if (!passed) {
                System.out.println("   Array: " + Arrays.toString(testCase.array));
                System.out.println("   Key: " + testCase.key);
                System.out.println("   Expected: " + testCase.expectedIndex + ", Got: " + result);
            }
        }
    }

    /**
     * Generates a large sorted array and rotates it.
     *
     * @param size The size of the array.
     * @return A rotated sorted array.
     */
    private static int[] generateLargeArray(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = i;
        }
        // Rotate the array by size/2
        rotateArray(arr, size / 2);
        return arr;
    }

    /**
     * Rotates an array to the right by a given number of times.
     *
     * @param arr   The array to rotate.
     * @param times Number of times to rotate.
     */
    private static void rotateArray(int[] arr, int times) {
        int n = arr.length;
        times = times % n;
        reverse(arr, 0, n - 1);
        reverse(arr, 0, times - 1);
        reverse(arr, times, n - 1);
    }

    /**
     * Reverses elements in an array from start to end indices.
     *
     * @param arr   The array.
     * @param start Starting index.
     * @param end   Ending index.
     */
    private static void reverse(int[] arr, int start, int end) {
        while (start < end) {
            int temp = arr[start];
            arr[start++] = arr[end];
            arr[end--] = temp;
        }
    }

    /**
     * Helper class to represent a test case.
     */
    static class TestCase {
        int[] array;
        int key;
        int expectedIndex;

        TestCase(int[] array, int key, int expectedIndex) {
            this.array = array;
            this.key = key;
            this.expectedIndex = expectedIndex;
        }
    }
}
