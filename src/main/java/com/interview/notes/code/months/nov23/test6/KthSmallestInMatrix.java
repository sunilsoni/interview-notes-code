package com.interview.notes.code.months.nov23.test6;

import java.util.Arrays;

/**
 * Find Kth smallest element in a two dimensional matrix.
 *
 * Input: Two dimensional array of integers and integer k Output: kth smallest number (integer)
 */
public class KthSmallestInMatrix {
    // Main method for example execution
    public static void main(String[] args) {
        int[][] matrix = {
            {10, 20, 30},
            {15, 25, 35},
            {5, 15, 25}
        };
        int k = 4;  // Example: Find the 4th smallest element
        System.out.println("The " + k + "th smallest element is: " + findKthSmallest(matrix, k));
    }

    // Method to find the Kth smallest element in the matrix
    public static int findKthSmallest(int[][] matrix, int k) {
        // Flatten the matrix into a single array
        int[] flatArray = flattenMatrix(matrix);

        // Sort the array
        Arrays.sort(flatArray);

        // Return the kth element (k-1 in zero-based index)
        return flatArray[k - 1];
    }

    // Helper method to flatten the matrix
    private static int[] flattenMatrix(int[][] matrix) {
        return Arrays.stream(matrix)
                     .flatMapToInt(Arrays::stream)
                     .toArray();
    }
}
