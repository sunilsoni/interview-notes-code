package com.interview.notes.code.year.y2024.jan24.test9;

public class CustomMergeAndSort {
    public static void main(String[] args) {
        int[] A1 = {5, 10, 15};
        int[] A2 = {2, 4, 6, 8};
        int[] A3 = new int[A1.length + A2.length];

        mergeArrays(A1, A2, A3);
        sortArray(A3);

        System.out.println("Merged and Sorted Array (A3): ");
        for (int value : A3) {
            System.out.print(value + " ");
        }
    }

    // Method to merge two arrays into the third one
    private static void mergeArrays(int[] A1, int[] A2, int[] A3) {
        int i = 0, j = 0, k = 0;

        while (i < A1.length) {
            A3[k++] = A1[i++];
        }

        while (j < A2.length) {
            A3[k++] = A2[j++];
        }
    }

    // Method to sort an array in ascending order using insertion sort
    private static void sortArray(int[] A3) {
        for (int i = 1; i < A3.length; i++) {
            int key = A3[i];
            int j = i - 1;

            // Move elements of A3[0..i-1], that are greater than key, to one position ahead of their current position
            while (j >= 0 && A3[j] > key) {
                A3[j + 1] = A3[j];
                j = j - 1;
            }
            A3[j + 1] = key;
        }
    }
}
