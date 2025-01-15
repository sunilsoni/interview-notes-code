package com.interview.notes.code.year.y2025.jan24.meta.test2;

public class MergeSortedArrays {

    // Method to merge sorted array B into sorted array A in-place
    public static void merge(int[] A, int[] B, int validLengthA, int lengthB) {
        int indexA = validLengthA - 1;           // Last valid index in A
        int indexB = lengthB - 1;                // Last index in B
        int mergeIndex = validLengthA + lengthB - 1; // Last index of merged array in A

        // Merge from the back of A and B
        while (indexB >= 0) {
            // If A is exhausted or B element is greater than current A element
            if (indexA < 0 || B[indexB] >= A[indexA]) {
                A[mergeIndex] = B[indexB];
                indexB--;
            } else {
                A[mergeIndex] = A[indexA];
                indexA--;
            }
            mergeIndex--;
        }
    }

    // Utility method to print arrays (for debugging)
    public static void printArray(int[] arr) {
        for (int n : arr) {
            System.out.print(n + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        // Test case 1: Provided test
        int[] A1 = {1, 2, 3, 0, 0, 0, 0}; // '_' represented as 0 for empty slots
        int[] B1 = {2, 4, 6, 100};
        merge(A1, B1, 3, B1.length);
        boolean pass1 = java.util.Arrays.equals(A1, new int[]{1,2,2,3,4,6,100});
        System.out.println("Test Case 1 " + (pass1 ? "PASSED" : "FAILED"));

        // Additional Test Case 2: A has no initial elements
        int[] A2 = {0, 0, 0};
        int[] B2 = {1, 2, 3};
        merge(A2, B2, 0, B2.length);
        boolean pass2 = java.util.Arrays.equals(A2, new int[]{1,2,3});
        System.out.println("Test Case 2 " + (pass2 ? "PASSED" : "FAILED"));

        // Additional Test Case 3: B is empty
        int[] A3 = {1, 2, 3};
        int[] B3 = {};
        merge(A3, B3, 3, B3.length);
        boolean pass3 = java.util.Arrays.equals(A3, new int[]{1,2,3});
        System.out.println("Test Case 3 " + (pass3 ? "PASSED" : "FAILED"));

        // Additional Test Case 4: Both A and B have elements, with duplicates
        int[] A4 = {1, 3, 5, 0, 0, 0};
        int[] B4 = {2, 3, 4};
        merge(A4, B4, 3, B4.length);
        boolean pass4 = java.util.Arrays.equals(A4, new int[]{1,2,3,3,4,5});
        System.out.println("Test Case 4 " + (pass4 ? "PASSED" : "FAILED"));

        // Edge Test Case 5: Large Data Input
        int size = 100000; // Large size for performance testing
        int[] A5 = new int[size + size]; // Enough space for merge
        int[] B5 = new int[size];
        for (int i = 0; i < size; i++) {
            A5[i] = i * 2;         // Even numbers
            B5[i] = i * 2 + 1;     // Odd numbers
        }
        merge(A5, B5, size, size);
        // Verify sorted order for large array
        boolean pass5 = true;
        for (int i = 1; i < A5.length; i++) {
            if (A5[i] < A5[i - 1]) {
                pass5 = false;
                break;
            }
        }
        System.out.println("Test Case 5 (Large Data) " + (pass5 ? "PASSED" : "FAILED"));
    }
}
