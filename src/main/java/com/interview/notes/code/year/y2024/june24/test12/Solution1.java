package com.interview.notes.code.year.y2024.june24.test12;

public class Solution1 {

    /**
     * Finds the median of two sorted arrays.
     *
     * @param A First sorted array
     * @param B Second sorted array
     * @return The median of the combined sorted arrays
     */
    public static double findMedianSortedArrays(int[] A, int[] B) {
        // Merge two sorted arrays
        int totalLength = A.length + B.length;
        int[] merged = new int[totalLength];
        int i = 0, j = 0, k = 0;

        while (i < A.length && j < B.length) {
            if (A[i] < B[j]) {
                merged[k++] = A[i++];
            } else {
                merged[k++] = B[j++];
            }
        }

        while (i < A.length) {
            merged[k++] = A[i++];
        }

        while (j < B.length) {
            merged[k++] = B[j++];
        }

        // Calculate median
        if (totalLength % 2 == 0) {
            return (merged[totalLength / 2 - 1] + merged[totalLength / 2]) / 2.0;
        } else {
            return merged[totalLength / 2];
        }
    }

    /**
     * Tests the findMedianSortedArrays method.
     *
     * @return true if all tests pass, otherwise false
     */
    public static boolean doTestsPass1() {
        boolean result = true;
        result = result && findMedianSortedArrays(new int[]{1, 3}, new int[]{2, 4}) == 2.5;
        result = result && findMedianSortedArrays(new int[]{1, 3}, new int[]{2}) == 2.0;
        return result;
    }

    public static boolean doTestsPass() {
        boolean result = true;
        //result &= findMedianSortedArrays(new int[]{1, 3}, new int[]{2, 4}) == 2.5;
        //  result &= findMedianSortedArrays(new int[]{1, 3}, new int[]{2}) == 2.0;
        //  result &= findMedianSortedArrays(new int[]{-5, -3, -1}, new int[]{1, 2, 3}) == -1;
        //   result &= findMedianSortedArrays(new int[]{-10, -8, -3, -1}, new int[]{-9, -7, -2, 0}) == -5.0;
        result &= findMedianSortedArrays(new int[]{-5, -3, -1}, new int[]{1, 2, 3}) == -0.0;
        // result &= findMedianSortedArrays(new int[]{1}, new int[]{2}) == 1.5;
        // result &= findMedianSortedArrays(new int[]{}, new int[]{1, 2, 3, 4}) == 2.5;
        // result &= findMedianSortedArrays(new int[]{2, 2, 2}, new int[]{2, 2}) == 2.0;
        //  result &= findMedianSortedArrays(new int[]{Integer.MIN_VALUE, 0, Integer.MAX_VALUE}, new int[]{Integer.MIN_VALUE, Integer.MAX_VALUE}) == 0.0;
        return result;
    }

    /**
     * Execution entry point.
     *
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        if (doTestsPass()) {
            System.out.println("All tests pass");
        } else {
            System.out.println("There are test failures");
        }
    }
}
