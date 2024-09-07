package com.interview.notes.code.months.sept24.test1;

import java.util.Arrays;

/*
Input: [15, 9, 7, 10, 12]
Output: 5 or 7

Local Minimum:

You've given an array. Of integers. So each integer is unique. Okay, your goals to any single local, minimum in the array, a local minimums, any value that is less than its neighbors. In the era, In this array, both five and seven are local minimums. You see why five and seven are local minimums by this less than nine, and seven is less than nine. And 10. So they both look minimums 10 is greater than seven so it's not a local minimum. Call is greater than


 */
public class LocalMinimumFinder {
    public static void main(String[] args) {
        int[][] testCases = {
                {15, 9, 7, 10, 12},
                {5, 3, 1, 7, 9},
                {1, 2, 3, 4, 5},
                {5, 4, 3, 2, 1},
                {2, 1, 3}
        };

        for (int i = 0; i < testCases.length; i++) {
            int[] arr = testCases[i];
            int result = findLocalMinimum(arr);
            System.out.println("Test Case " + (i + 1) + ": " + Arrays.toString(arr));
            System.out.println("Local Minimum: " + result);
            System.out.println();
        }
    }

    public static int findLocalMinimum(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Array is null or empty");
        }

        int n = arr.length;

        // Check first element
        if (n == 1 || arr[0] < arr[1]) {
            return arr[0];
        }

        // Check last element
        if (arr[n - 1] < arr[n - 2]) {
            return arr[n - 1];
        }

        // Check middle elements
        for (int i = 1; i < n - 1; i++) {
            if (arr[i] < arr[i - 1] && arr[i] < arr[i + 1]) {
                return arr[i];
            }
        }

        // No local minimum found (shouldn't happen with unique elements)
        return -1;
    }
}
