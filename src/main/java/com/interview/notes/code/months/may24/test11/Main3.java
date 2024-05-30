package com.interview.notes.code.months.may24.test11;

import java.util.Arrays;

public class Main3 {
    public static void main(String[] args) {
        // Example 1
        int[] example1 = {1, 2, 3};
        System.out.println("Permutations for example 1 (1, 2, 3):");
        processPermutations(example1);

        // Example 2
        int[] example2 = {4, 3, 2, 1};
        System.out.println("Permutations for example 2 (4, 3, 2, 1):");
        processPermutations(example2);

        // Example 3
        int[] example3 = {1, 1, 2};
        System.out.println("Permutations for example 3 (1, 1, 2):");
        processPermutations(example3);
    }

    // Helper method to process and print permutations of the given array
    private static void processPermutations(int[] array) {
        Arrays.sort(array); // Ensure the array is sorted for the first permutation
        do {
            printArray(array);
        } while (nextPermutation(array));
        System.out.println(); // Print a newline for better separation between examples
    }

    // Function to print the elements of the array
    private static void printArray(int[] array) {
        for (int num : array) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    // Function to generate next lexicographical permutation
    private static boolean nextPermutation(int[] array) {
        // Find the first element that is smaller than the next one
        int i = array.length - 2;
        while (i >= 0 && array[i] >= array[i + 1]) {
            i--;
        }
        if (i == -1) {
            return false; // No more permutations
        }

        // Find the smallest element on the right of the `i` that is bigger than array[i]
        int j = array.length - 1;
        while (array[j] <= array[i]) {
            j--;
        }

        // Swap elements at i and j
        swap(array, i, j);

        // Reverse the sequence from i + 1 to end
        reverse(array, i + 1, array.length - 1);
        return true;
    }

    // Swap elements in the array
    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    // Reverse elements in the array from start to end
    private static void reverse(int[] array, int start, int end) {
        while (start < end) {
            swap(array, start, end);
            start++;
            end--;
        }
    }
}
