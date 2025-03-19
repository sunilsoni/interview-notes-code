package com.interview.notes.code.year.y2025.march.meta.test2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SecondLargestPermutation {

    // Main method to run tests.
    public static void main(String[] args) {
        // Define test cases as a list of int arrays.
        List<int[]> testCases = new ArrayList<>();
        // Simple case: distinct digits
        testCases.add(new int[]{1, 2, 3, 4, 5});  // Expected second largest: [5,4,3,1,2]
        // Case with duplicate digits (should still work correctly)
        testCases.add(new int[]{1, 2, 2, 3});       // Expected: Largest is [3,2,2,1], second largest would be [3,2,1,2]
        // Edge case: Only one element
        testCases.add(new int[]{9});                // Only one permutation possible.
        // Edge case: Two elements
        testCases.add(new int[]{3, 1});             // Largest: [3,1], no second largest exists, so return same.
        // Test case for large input: 100,000 digits where all are same except one digit at the end.
        int[] largeTest = new int[100000];
        Arrays.fill(largeTest, 9);
        largeTest[99999] = 8;
        testCases.add(largeTest);

        // Process each test case and check if our method produces the expected results.
        for (int[] testCase : testCases) {
            // Copy the test case array because our method modifies the array.
            int[] inputCopy = Arrays.copyOf(testCase, testCase.length);
            int[] result = getSecondLargestPermutation(inputCopy);

            // For simplicity in testing, we generate the largest permutation first.
            // Note: If no second permutation exists, our method returns the same array.
            int[] largest = Arrays.copyOf(testCase, testCase.length);
            Arrays.sort(largest);
            reverse(largest); // largest permutation

            // Determine if the computed result is exactly the second largest
            boolean pass;
            if (Arrays.equals(largest, result)) {
                // If the largest and second largest are same, it means no alternative permutation exists.
                pass = true;
            } else {
                // Check if result is less than largest and if no permutation exists in between.
                pass = isImmediatelyPreviousPermutation(largest, result);
            }
            System.out.println("Input: " + Arrays.toString(testCase));
            System.out.println("Largest Permutation: " + Arrays.toString(largest));
            System.out.println("Second Largest Permutation: " + Arrays.toString(result));
            System.out.println("Test " + (pass ? "PASSED" : "FAILED"));
            System.out.println("-----------------------------------------");
        }
    }

    /**
     * This method calculates the second largest permutation of the digits.
     * If the array is already at its smallest or only one permutation exists, it returns the original array.
     */
    public static int[] getSecondLargestPermutation(int[] arr) {
        // First, sort the array in descending order to get the largest permutation.
        Arrays.sort(arr);
        reverse(arr); // custom reverse method
        // The largest permutation is now in arr.

        // To get the second largest, we compute the previous permutation.
        // Find the first index 'i' from the right such that arr[i] > arr[i+1]
        int i = arr.length - 2;
        while (i >= 0 && arr[i] <= arr[i + 1]) {
            i--;
        }

        // If no such index exists, it means that the array is in ascending order,
        // meaning only one permutation exists. In that case, return the same array.
        if (i < 0) {
            return arr;
        }

        // Find the largest digit on right side of arr[i] that is less than arr[i]
        int j = arr.length - 1;
        while (j > i && arr[j] >= arr[i]) {
            j--;
        }
        // Swap the elements at index i and j.
        swap(arr, i, j);
        // Reverse the subarray from i+1 to the end to form the next smaller permutation.
        reverse(arr, i + 1, arr.length - 1);
        return arr;
    }

    // Helper method to reverse entire array.
    private static void reverse(int[] arr) {
        reverse(arr, 0, arr.length - 1);
    }

    // Helper method to reverse a part of the array from index 'start' to 'end'.
    private static void reverse(int[] arr, int start, int end) {
        while (start < end) {
            swap(arr, start, end);
            start++;
            end--;
        }
    }

    // Helper method to swap two elements in the array.
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * This method verifies if the 'result' array is exactly the permutation immediately smaller than 'largest'.
     * It does so by generating the next permutation (in descending order) of 'result' and comparing with 'largest'.
     */
    private static boolean isImmediatelyPreviousPermutation(int[] largest, int[] result) {
        // Make a copy of the result array
        int[] temp = Arrays.copyOf(result, result.length);
        // Compute the next permutation (which should give us the largest permutation if result is immediately previous)
        int i = temp.length - 2;
        while (i >= 0 && temp[i] <= temp[i + 1]) {
            i--;
        }
        if (i < 0) {
            return Arrays.equals(temp, largest);
        }
        int j = temp.length - 1;
        while (j > i && temp[j] >= temp[i]) {
            j--;
        }
        swap(temp, i, j);
        reverse(temp, i + 1, temp.length - 1);
        return Arrays.equals(temp, largest);
    }
}
