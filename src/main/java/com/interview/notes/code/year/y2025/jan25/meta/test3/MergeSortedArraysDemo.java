package com.interview.notes.code.year.y2025.jan25.meta.test3;

/*
WORKING:


We have two SORTED arrays of integers: A and B. A has empty slots at the end of it. It has exactly as many empty slots as there are elements in B. Your goal is to merge the elements from B into A so that array A contains all of the elements in sorted order.

Optimize for speed and memory usage.

Input:

A = [1, 2, 3,_, _, _, _]
B = [2, 4, 6, 100]

Expected output:
A = [1, 2, 2, 3, 4, 6, 100]

 */
public class MergeSortedArraysDemo {

    /**
     * Merges array B into array A in-place, assuming A has enough empty slots.
     *
     * @param A       the first sorted array with extra slots at the end
     * @param validA  the number of valid elements initially in A
     * @param B       the second sorted array
     * @param lengthB the length of array B
     */
    public static void mergeArrays(int[] A, int validA, int[] B, int lengthB) {
        int i = validA - 1;          // Index of last valid element in A
        int j = lengthB - 1;         // Index of last element in B
        int k = validA + lengthB - 1; // Fill position from the end of A

        while (i >= 0 && j >= 0) {
            if (A[i] > B[j]) {
                A[k] = A[i];
                i--;
            } else {
                A[k] = B[j];
                j--;
            }
            k--;
        }

        // If B still has elements left, copy them in
        while (j >= 0) {
            A[k] = B[j];
            j--;
            k--;
        }
        // If A still has elements left, they are already in correct place
    }

    /**
     * A helper method to check if two arrays have the same values.
     */
    private static boolean arraysAreEqual(int[] a, int[] b) {
        if (a.length != b.length) return false;
        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * The main method for testing. We print Pass or Fail for each test.
     */
    public static void main(String[] args) {
        // Test case 1
        int[] A1 = {1, 2, 3, 0, 0, 0, 0};
        int[] B1 = {2, 4, 6, 100};
        mergeArrays(A1, 3, B1, B1.length);
        int[] expected1 = {1, 2, 2, 3, 4, 6, 100};
        System.out.println("Test 1: " + (arraysAreEqual(A1, expected1) ? "PASS" : "FAIL"));

        // Test case 2 (B is empty)
        int[] A2 = {1, 2, 3};
        int[] B2 = {};
        mergeArrays(A2, 3, B2, B2.length);
        int[] expected2 = {1, 2, 3};
        System.out.println("Test 2: " + (arraysAreEqual(A2, expected2) ? "PASS" : "FAIL"));

        // Test case 3 (All elements in B are smaller)
        int[] A3 = {10, 20, 30, 0, 0, 0};
        int[] B3 = {1, 2, 3};
        mergeArrays(A3, 3, B3, 3);
        int[] expected3 = {1, 2, 3, 10, 20, 30};
        System.out.println("Test 3: " + (arraysAreEqual(A3, expected3) ? "PASS" : "FAIL"));

        // Test case 4 (All elements in B are larger)
        int[] A4 = {1, 2, 3, 0, 0, 0};
        int[] B4 = {10, 20, 30};
        mergeArrays(A4, 3, B4, 3);
        int[] expected4 = {1, 2, 3, 10, 20, 30};
        System.out.println("Test 4: " + (arraysAreEqual(A4, expected4) ? "PASS" : "FAIL"));

        // Test case 5 (Duplicates in both arrays)
        int[] A5 = {2, 4, 4, 0, 0, 0};
        int[] B5 = {4, 4, 5};
        mergeArrays(A5, 3, B5, 3);
        int[] expected5 = {2, 4, 4, 4, 4, 5};
        System.out.println("Test 5: " + (arraysAreEqual(A5, expected5) ? "PASS" : "FAIL"));

        // Test case 6 (Large array scenario - simple demonstration)
        // We won't print the array, just check if it merges properly
        int[] largeA = new int[10_000];
        int[] largeB = new int[5_000];
        int validCountA = 5_000; // assume first 5,000 spots in A are filled
        // Fill A (first 5000 positions sorted), B (5000 positions sorted)
        for (int i = 0; i < 5_000; i++) {
            largeA[i] = i; // sorted 0 to 4999
        }
        for (int i = 0; i < 5_000; i++) {
            largeB[i] = i + 5000; // sorted 5000 to 9999
        }
        // Merge
        mergeArrays(largeA, validCountA, largeB, largeB.length);
        // Quick check: The final array should be 0..9999 sorted
        boolean largeTestPass = true;
        for (int i = 0; i < 10_000; i++) {
            if (largeA[i] != i) {
                largeTestPass = false;
                break;
            }
        }
        System.out.println("Test 6 (Large array): " + (largeTestPass ? "PASS" : "FAIL"));
    }
}
