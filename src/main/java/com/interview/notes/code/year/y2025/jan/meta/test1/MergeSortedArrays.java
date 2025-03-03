package com.interview.notes.code.year.y2025.jan.meta.test1;

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
        // Test Case 1: Basic merge
        int[] A1 = new int[7];
        A1[0] = 1;
        A1[1] = 2;
        A1[2] = 3;
        int[] B1 = {2, 4, 6, 100};
        merge(A1, 3, B1, 4);
        printArray("Test Case 1", A1);

        // Test Case 2: Empty B array
        int[] A2 = {1, 2, 3, 0, 0, 0};
        int[] B2 = {};
        merge(A2, 3, B2, 0);
        printArray("Test Case 2", A2);

        // Test Case 3: B array larger than A
        int[] A3 = new int[6];
        A3[0] = 1;
        A3[1] = 5;
        int[] B3 = {2, 3, 4, 7, 8};
        merge(A3, 2, B3, 5);
        printArray("Test Case 3", A3);

        // Test Case 4: Duplicate elements
        int[] A4 = new int[7];
        A4[0] = 1;
        A4[1] = 2;
        A4[2] = 3;
        int[] B4 = {2, 2, 4, 6};
        merge(A4, 3, B4, 4);
        printArray("Test Case 4", A4);

        // Performance Test
        performanceTest();
    }

    // Helper method to print array
    private static void printArray(String testCase, int[] arr) {
        System.out.print(testCase + ": ");
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    // Performance test for large inputs
    private static void performanceTest() {
        int size = 100000;
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

        System.out.println("Large Input Performance Test:");
        System.out.println("Time taken: " + (endTime - startTime) / 1_000_000 + " ms");
    }
}