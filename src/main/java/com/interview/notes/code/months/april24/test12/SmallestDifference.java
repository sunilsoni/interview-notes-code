package com.interview.notes.code.months.april24.test12;

import java.util.Arrays;

public class SmallestDifference {

    public static int findSmallestDifference(int[] arr1, int[] arr2) {
        // Sort both arrays for efficient comparison
        Arrays.sort(arr1);
        Arrays.sort(arr2);

        int i = 0, j = 0;
        int minDiff = Integer.MAX_VALUE;

        // Iterate through both arrays simultaneously
        while (i < arr1.length && j < arr2.length) {
            int currentDiff = Math.abs(arr1[i] - arr2[j]);
            minDiff = Math.min(minDiff, currentDiff); // Update minDiff if smaller difference found

            // Move pointers based on which value is smaller
            if (arr1[i] < arr2[j]) {
                i++;
            } else {
                j++;
            }
        }

        return minDiff;
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 3, 15, 11, 2};
        int[] arr2 = {23, 127, 235, 19, 8};
        int smallestDiff = findSmallestDifference(arr1, arr2);
        System.out.println("Smallest difference: " + smallestDiff); // Output: Smallest difference: 3
    }
}
