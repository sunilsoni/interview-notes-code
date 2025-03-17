package com.interview.notes.code.year.y2025.march.meta.test1;

import java.util.Arrays;

public class SecondLargestNumber {

    public static void main(String[] args) {
        // Test cases
        int[] input1 = {1, 2, 3, 4, 5};
        int[] expected1 = {5, 4, 3, 1, 2};
        testCase(input1, expected1);

        int[] input2 = {5, 5, 5};
        int[] expected2 = {5, 5, 5};
        testCase(input2, expected2);

        int[] input3 = {9, 8, 7, 6, 5};
        int[] expected3 = {9, 8, 7, 5, 6};
        testCase(input3, expected3);

        int[] input4 = {0, 0};
        int[] expected4 = {0, 0};
        testCase(input4, expected4);

        int[] input5 = {3, 1, 4, 1, 5, 9};
        int[] expected5 = {9, 5, 4, 3, 1, 1}; // Previous permutation would be computed
        testCase(input5, expected5);
    }

    private static void testCase(int[] input, int[] expected) {
        int[] result = getSecondLargestPermutation(input);
        boolean pass = Arrays.equals(expected, result);
        System.out.println("Input: " + Arrays.toString(input) + " | Output: " + Arrays.toString(result) + " | Pass: " + pass);
    }

    public static int[] getSecondLargestPermutation(int[] arr) {
        // Create a copy and sort in ascending order
        int[] sortedArr = Arrays.copyOf(arr, arr.length);
        Arrays.sort(sortedArr);
        // Reverse to get descending order (largest permutation)
        reverse(sortedArr);

        // Find previous permutation of the sorted array
        boolean hasPrevious = previousPermutation(sortedArr);

        // If no previous permutation exists (all elements same), return the sorted array
        return sortedArr;
    }

    // Reverses the entire array
    private static void reverse(int[] arr) {
        for (int i = 0; i < arr.length / 2; i++) {
            int temp = arr[i];
            arr[i] = arr[arr.length - 1 - i];
            arr[arr.length - 1 - i] = temp;
        }
    }

    // Modifies the array to its previous permutation; returns false if not possible
    private static boolean previousPermutation(int[] arr) {
        // Step 1: Find the largest index 'k' such that arr[k] > arr[k+1]
        int k = -1;
        for (int i = arr.length - 2; i >= 0; i--) {
            if (arr[i] > arr[i + 1]) {
                k = i;
                break;
            }
        }

        // If no such index, it's the first permutation (all descending)
        if (k == -1) {
            return false;
        }

        // Step 2: Find the largest index 'l' > k such that arr[l] < arr[k]
        int l = arr.length - 1;
        while (arr[l] >= arr[k]) {
            l--;
        }

        // Step 3: Swap elements at k and l
        int temp = arr[k];
        arr[k] = arr[l];
        arr[l] = temp;

        // Step 4: Reverse the elements after index k to get the largest possible smaller number
        reverse(arr, k + 1, arr.length - 1);

        return true;
    }

    // Reverses elements from index 'start' to 'end' inclusive
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