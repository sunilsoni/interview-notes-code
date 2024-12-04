package com.interview.notes.code.months.dec24.test3;

import java.util.Arrays;

/*


So you have to find the kth smallest element in a two by dimensional matrix. So you'll be given two things here, two dimensional array of integers and integer k, and you have to find this KTH, smallest number. For example, you have a matrix of a dimensional array of three into three, three rows, three columns. You'll have nine elements. He will be fine. You will be say that. Give me the fifth, smallest agreement. He will be three. Give me the third, smallest element, seventh, smallest agreement.

Find Kth smallest element in a two dimensional matrix.

Input: Two dimensional array of integers and
integer k
Output: kth smallest number (integer)
 */

public class MatrixKthSmallest {
    // Method to find kth smallest element
    public static int findKthSmallest(int[][] matrix, int k) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            throw new IllegalArgumentException("Invalid matrix input");
        }

        int rows = matrix.length;
        int cols = matrix[0].length;
        int totalElements = rows * cols;

        if (k <= 0 || k > totalElements) {
            throw new IllegalArgumentException("Invalid k value");
        }

        // Convert 2D array to 1D for easier sorting
        int[] flatArray = new int[totalElements];
        int index = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                flatArray[index++] = matrix[i][j];
            }
        }

        // Sort the array
        Arrays.sort(flatArray);

        // Return kth smallest element
        return flatArray[k - 1];
    }

    public static void main(String[] args) {
        // Test Case 1: Basic case
        int[][] matrix1 = {
                {1, 5, 9},
                {10, 11, 13},
                {12, 13, 15}
        };
        testCase(matrix1, 8, 13, "Basic Test");

        // Test Case 2: Single element matrix
        int[][] matrix2 = {{1}};
        testCase(matrix2, 1, 1, "Single Element");

        // Test Case 3: Matrix with duplicates
        int[][] matrix3 = {
                {1, 2, 2},
                {2, 3, 3},
                {3, 3, 4}
        };
        testCase(matrix3, 4, 2, "Duplicates");

        // Test Case 4: Large matrix
        int[][] matrix4 = new int[100][100];
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                matrix4[i][j] = i * 100 + j;
            }
        }
        testCase(matrix4, 5000, 4999, "Large Matrix");
    }

    private static void testCase(int[][] matrix, int k, int expected, String testName) {
        try {
            long startTime = System.nanoTime();
            int result = findKthSmallest(matrix, k);
            long endTime = System.nanoTime();

            System.out.println("Test: " + testName);
            System.out.println("Expected: " + expected + ", Got: " + result);
            System.out.println("Status: " + (result == expected ? "PASS" : "FAIL"));
            System.out.println("Time taken: " + (endTime - startTime) / 1000000.0 + " ms");
            System.out.println();
        } catch (Exception e) {
            System.out.println("Test: " + testName);
            System.out.println("Error: " + e.getMessage());
            System.out.println("Status: FAIL");
            System.out.println();
        }
    }
}
