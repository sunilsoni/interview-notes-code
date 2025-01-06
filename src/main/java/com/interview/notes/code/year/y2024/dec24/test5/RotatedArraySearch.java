package com.interview.notes.code.year.y2024.dec24.test5;

import java.util.Random;

public class RotatedArraySearch {

    // Method to find the index of the key in a rotated sorted array
    public static int searchInRotatedArray(int[] arr, int key) {
        if (arr == null || arr.length == 0) return -1;
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] == key) {
                return mid;
            }

            // Check which half is sorted
            if (arr[left] <= arr[mid]) {
                // Left half is sorted
                if (key >= arr[left] && key < arr[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                // Right half is sorted
                if (key > arr[mid] && key <= arr[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }

        return -1; // key not found
    }

    public static void main(String[] args) {
        // Test Cases
        // Format: arr, key, expectedIndex
        // We will print PASS/FAIL based on the result

        int[][] testArrays = {
                {4, 5, 6, 7, 0, 1, 2},    // rotated at index 3
                {1},                // single element
                {5, 1, 2, 3, 4},        // rotated at index 1
                {7, 8, 9, 1, 2, 3, 4},    // rotated
                {2, 3, 4, 5, 6, 7, 8, 9, 1} // rotated
        };

        int[] keys = {0, 1, 5, 9, 9};
        int[] expected = {4, 0, 0, 2, 7}; // expected indices for the keys above

        for (int i = 0; i < testArrays.length; i++) {
            int result = searchInRotatedArray(testArrays[i], keys[i]);
            if (result == expected[i]) {
                System.out.println("Test " + (i + 1) + " PASS");
            } else {
                System.out.println("Test " + (i + 1) + " FAIL: Expected " + expected[i] + ", got " + result);
            }
        }

        // Edge Case: Key not in array
        int[] arrNotFound = {4, 5, 6, 7, 0, 1, 2};
        int resultNotFound = searchInRotatedArray(arrNotFound, 10);
        if (resultNotFound == -1) {
            System.out.println("Key not found test PASS");
        } else {
            System.out.println("Key not found test FAIL: Expected -1, got " + resultNotFound);
        }

        // Large Data Test
        // Generate a large rotated array and test searching for a known element
        int size = 1000000;
        int[] largeArr = generateLargeRotatedArray(size);
        int knownValue = largeArr[size / 2]; // pick a value from the middle
        int largeResult = searchInRotatedArray(largeArr, knownValue);
        if (largeResult != -1 && largeArr[largeResult] == knownValue) {
            System.out.println("Large data test PASS");
        } else {
            System.out.println("Large data test FAIL: Could not find " + knownValue);
        }
    }

    // Helper method to generate a large rotated array for testing
    // This creates a sorted array of distinct integers and then rotates it randomly
    private static int[] generateLargeRotatedArray(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = i; // distinct sorted
        }

        // Rotate at a random pivot
        Random rand = new Random();
        int pivot = rand.nextInt(size);

        reverse(arr, 0, pivot - 1);
        reverse(arr, pivot, size - 1);
        reverse(arr, 0, size - 1);

        return arr;
    }

    // Utility method to reverse a subarray
    private static void reverse(int[] arr, int start, int end) {
        while (start < end) {
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }
}
