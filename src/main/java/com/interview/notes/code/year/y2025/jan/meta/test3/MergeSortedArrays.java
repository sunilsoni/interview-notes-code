package com.interview.notes.code.year.y2025.jan.meta.test3;

public class MergeSortedArrays {

    /**
     * Merges sorted array B into array A with empty slots
     *
     * @param A main array with empty slots at the end
     * @param m number of actual elements in A
     * @param B array to be merged into A
     * @param n number of elements in B
     */
    public static void merge(int[] A, int m, int[] B, int n) {
        // Handle edge cases
        if (B == null || n == 0) {
            return;  // Nothing to merge
        }

        if (A == null || A.length < m + n) {
            throw new IllegalArgumentException("Insufficient array size");
        }

        // Start from the end of the arrays
        int lastIndex = m + n - 1;  // Final position in merged array
        int lastA = m - 1;          // Last actual element in A
        int lastB = n - 1;          // Last element in B

        // Merge from the end to avoid overwriting
        while (lastB >= 0) {
            // Compare and place larger element at the end
            if (lastA >= 0 && A[lastA] > B[lastB]) {
                A[lastIndex] = A[lastA];
                lastA--;
            } else {
                A[lastIndex] = B[lastB];
                lastB--;
            }
            lastIndex--;
        }
    }

    // Test method to validate the merge functionality
    public static void main(String[] args) {
        // Test comprehensive scenarios with robust error handling
        testMergeScenarios();
    }

    private static void testMergeScenarios() {
        // Test Case 1: Basic merge
        int[] A1 = new int[7];
        A1[0] = 1;
        A1[1] = 2;
        A1[2] = 3;
        int[] B1 = {2, 4, 6, 100};
        runTest("Basic Merge", A1, 3, B1, 4);

        // Test Case 2: Empty B array
        int[] A2 = {1, 2, 3, 0, 0, 0};
        int[] B2 = {};
        runTest("Empty B Array", A2, 3, B2, 0);

        // Test Case 3: B array larger than A
        int[] A3 = new int[6];
        A3[0] = 1;
        A3[1] = 5;
        int[] B3 = {2, 3, 4, 7, 8};
        runTest("B Larger Than A", A3, 2, B3, 5);

        // Test Case 4: Duplicate elements
        int[] A4 = new int[7];
        A4[0] = 1;
        A4[1] = 2;
        A4[2] = 3;
        int[] B4 = {2, 2, 4, 6};
        runTest("Duplicate Elements", A4, 3, B4, 4);

        // Performance Test
        performanceLargeInputTest();
    }

    private static void runTest(String testName, int[] A, int m, int[] B, int n) {
        try {
            System.out.println("\n" + testName + " Test:");

            // Print initial state
            System.out.print("Before: A = ");
            printArray(A);
            System.out.print("B = ");
            printArray(B);

            // Perform merge
            merge(A, m, B, n);

            // Print result
            System.out.print("After:  A = ");
            printArray(A);
        } catch (Exception e) {
            System.err.println("Test failed: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Helper method to print array
    private static void printArray(int[] arr) {
        if (arr == null) {
            System.out.println("null");
            return;
        }

        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }

    // Performance test for large inputs
    private static void performanceLargeInputTest() {
        int size = 100_000;
        int[] largeA = new int[size * 2];
        int[] largeB = new int[size];

        // Populate sorted arrays
        for (int i = 0; i < size; i++) {
            largeA[i] = i * 2;
            largeB[i] = i * 2 + 1;
        }

        long startTime = System.nanoTime();
        merge(largeA, size, largeB, size);
        long endTime = System.nanoTime();

        System.out.println("\nLarge Input Performance Test:");
        System.out.println("Time taken: " + (endTime - startTime) / 1_000_000 + " ms");

        // Verify first few and last few elements
        System.out.println("First 10 elements: ");
        for (int i = 0; i < 10; i++) {
            System.out.print(largeA[i] + " ");
        }
        System.out.println("\nLast 10 elements: ");
        for (int i = largeA.length - 10; i < largeA.length; i++) {
            System.out.print(largeA[i] + " ");
        }
    }
}