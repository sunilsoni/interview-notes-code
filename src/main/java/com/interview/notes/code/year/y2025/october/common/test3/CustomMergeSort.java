package com.interview.notes.code.year.y2025.october.common.test3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CustomMergeSort {
    public static void sortArray(int[] arr) {
        // Create separate lists for odds, evens, and count zeros
        List<Integer> odds = new ArrayList<>();
        List<Integer> evens = new ArrayList<>();
        int zeroCount = 0;

        // Separate numbers
        for (int num : arr) {
            if (num == 0) {
                zeroCount++;
            } else if (num % 2 != 0) {
                odds.add(num);
            } else {
                evens.add(num);
            }
        }

        // Convert lists to arrays for merge sort
        int[] oddArray = odds.stream().mapToInt(i -> i).toArray();
        int[] evenArray = evens.stream().mapToInt(i -> i).toArray();

        // Sort both arrays using merge sort
        mergeSort(oddArray, 0, oddArray.length - 1);
        mergeSort(evenArray, 0, evenArray.length - 1);

        // Combine results
        int index = 0;
        // Add odds
        for (int odd : oddArray) {
            arr[index++] = odd;
        }
        // Add evens
        for (int even : evenArray) {
            arr[index++] = even;
        }
        // Add zeros
        while (index < arr.length) {
            arr[index++] = 0;
        }
    }

    private static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;

            // Sort first and second halves
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);

            // Merge the sorted halves
            merge(arr, left, mid, right);
        }
    }

    private static void merge(int[] arr, int left, int mid, int right) {
        // Find sizes of two subarrays to be merged
        int n1 = mid - left + 1;
        int n2 = right - mid;

        // Create temp arrays
        int[] L = new int[n1];
        int[] R = new int[n2];

        // Copy data to temp arrays
        System.arraycopy(arr, left, L, 0, n1);
        for (int j = 0; j < n2; j++) {
            R[j] = arr[mid + 1 + j];
        }

        // Merge the temp arrays
        int i = 0, j = 0;
        int k = left;

        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        // Copy remaining elements of L[] if any
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        // Copy remaining elements of R[] if any
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    public static void main(String[] args) {
        int[] arr = {4, 9, 0, 2, 0, 7, 0, 0, 3, 8};
        System.out.println("Original array: " + Arrays.toString(arr));

        sortArray(arr);

        System.out.println("Sorted array: " + Arrays.toString(arr));
    }
}
