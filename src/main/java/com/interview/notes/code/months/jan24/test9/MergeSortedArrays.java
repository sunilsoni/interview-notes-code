package com.interview.notes.code.months.jan24.test9;

public class MergeSortedArrays {

    public static void main(String[] args) {
        int[] A1 = {1, 3, 5, 7};
        int[] A2 = {2, 4, 6, 8, 10};
        int[] A3 = new int[A1.length + A2.length]; // Ensure A3 can hold all elements from A1 and A2

        mergeSortedArrays(A1, A2, A3);

        // Print the merged and sorted array A3
        System.out.println("Merged and Sorted Array (A3): ");
        for (int value : A3) {
            System.out.print(value + " ");
        }
    }

    private static void mergeSortedArrays(int[] A1, int[] A2, int[] A3) {
        int i = 0, j = 0, k = 0;

        // Merge until one of the arrays is exhausted
        while (i < A1.length && j < A2.length) {
            if (A1[i] < A2[j]) {
                A3[k++] = A1[i++];
            } else {
                A3[k++] = A2[j++];
            }
        }

        // Copy any remaining elements from A1
        while (i < A1.length) {
            A3[k++] = A1[i++];
        }

        // Copy any remaining elements from A2
        while (j < A2.length) {
            A3[k++] = A2[j++];
        }
    }
}
