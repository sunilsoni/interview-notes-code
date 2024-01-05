package com.interview.notes.code.months.dec23.test6;

import java.util.Arrays;

public class KthSmallestInMatrix {
    // Main method to test the functionality
    public static void main(String[] args) {
        int[][] matrix = {
                {10, 20, 30},
                {5, 15, 25},
                {1, 9, 11}
        };
        int k = 4; // Example: Find the 4th smallest element
        int result = findKthSmallest(matrix, k);
        System.out.println("The " + k + "th smallest element is: " + result);
    }

    // Method to find the kth smallest element in the matrix
    public static int findKthSmallest(int[][] matrix, int k) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[] flattened = new int[rows * cols];
        int index = 0;

        // Flatten the matrix
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                flattened[index++] = matrix[i][j];
            }
        }

        // Sort the flattened array
        Arrays.sort(flattened);

        // Return the kth smallest element
        return flattened[k - 1];
    }
}
