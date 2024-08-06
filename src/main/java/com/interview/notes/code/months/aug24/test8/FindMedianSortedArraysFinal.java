package com.interview.notes.code.months.aug24.test8;

/**
 *
 /*• Problem Name• is &&& Median Two Sorted Arrays &od PLEASE • DO NOT •
 */
public class FindMedianSortedArraysFinal {
    public static double findMedianSortedArrays(int[] A, int[] B) {
        int totalLength = A.length + B.length;
        int[] merged = new int[totalLength];
        int i = 0, j = 0, k = 0;

        // Merge the two arrays
        while (i < A.length && j < B.length) {
            if (A[i] < B[j]) {
                merged[k++] = A[i++];
            } else {
                merged[k++] = B[j++];
            }
        }

        // If there are remaining elements in A
        while (i < A.length) {
            merged[k++] = A[i++];
        }

        // If there are remaining elements in B
        while (j < B.length) {
            merged[k++] = B[j++];
        }

        // Calculate the median
        if (totalLength % 2 == 0) {
            return (merged[totalLength / 2 - 1] + merged[totalLength / 2]) / 2.0;
        } else {
            return merged[totalLength / 2];
        }
    }

    public static boolean doTestsPass() {
        boolean result = true;
        result = result && (findMedianSortedArrays(new int[]{1, 3}, new int[]{2, 4}) == 2.5);
        result = result && (findMedianSortedArrays(new int[]{1, 3}, new int[]{2}) == 2.0);
        result = result && (findMedianSortedArrays(new int[]{1, 2}, new int[]{3, 4}) == 2.5);
        result = result && (findMedianSortedArrays(new int[]{0, 0}, new int[]{0, 0}) == 0.0);
        result = result && (findMedianSortedArrays(new int[]{}, new int[]{1}) == 1.0);
        result = result && (findMedianSortedArrays(new int[]{2}, new int[]{}) == 2.0);
        return result;
    }

    public static void main(String[] args) {
        if (doTestsPass()) {
            System.out.println("All tests passed!");
        } else {
            System.out.println("Some tests failed.");
        }
    }
}