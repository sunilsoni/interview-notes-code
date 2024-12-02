package com.interview.notes.code.year.y2024.nov24.test12;

import java.util.Arrays;

/*
WORKING
int[][] matrix = {

    { -3, -2, -1,  1 },

    { -2, -2,  1,  2 },

    { -1,  1,  2,  3 },

    {  1,  2,  3,  4 }

};

Write a Program to Count Negative numbers in a column wise and row wise of a matrix

 */
public class NegativeMatrixCounter {

    public static int[] countNegativesRowWise(int[][] matrix) {
        int[] rowCounts = new int[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int num : matrix[i]) {
                if (num < 0) {
                    rowCounts[i]++;
                }
            }
        }
        return rowCounts;
    }

    public static int[] countNegativesColumnWise(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return new int[0];
        }
        int[] columnCounts = new int[matrix[0].length];
        for (int j = 0; j < matrix[0].length; j++) {
            for (int i = 0; i < matrix.length; i++) {
                if (matrix[i][j] < 0) {
                    columnCounts[j]++;
                }
            }
        }
        return columnCounts;
    }

    public static void main(String[] args) {
        // Test case 1: Given example
        int[][] matrix1 = {
                {-3, -2, -1, 1},
                {-2, -2, 1, 2},
                {-1, 1, 2, 3},
                {1, 2, 3, 4}
        };
        testCase(matrix1, new int[]{3, 2, 1, 0}, new int[]{3, 2, 1, 0}, "Test Case 1");

        // Test case 2: All negative
        int[][] matrix2 = {
                {-1, -2, -3},
                {-4, -5, -6},
                {-7, -8, -9}
        };
        testCase(matrix2, new int[]{3, 3, 3}, new int[]{3, 3, 3}, "Test Case 2");

        // Test case 3: All positive
        int[][] matrix3 = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        testCase(matrix3, new int[]{0, 0, 0}, new int[]{0, 0, 0}, "Test Case 3");

        // Test case 4: Empty matrix
        int[][] matrix4 = {};
        testCase(matrix4, new int[]{}, new int[]{}, "Test Case 4");

        // Test case 5: Single element matrix
        int[][] matrix5 = {{-1}};
        testCase(matrix5, new int[]{1}, new int[]{1}, "Test Case 5");

        // Test case 6: Large matrix (100x100)
        int[][] matrix6 = new int[100][100];
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                matrix6[i][j] = (i + j) % 2 == 0 ? -1 : 1;
            }
        }
        int[] expectedRow6 = new int[100];
        int[] expectedCol6 = new int[100];
        Arrays.fill(expectedRow6, 50);
        Arrays.fill(expectedCol6, 50);
        testCase(matrix6, expectedRow6, expectedCol6, "Test Case 6 (Large Matrix)");
    }

    private static void testCase(int[][] matrix, int[] expectedRowCounts, int[] expectedColumnCounts, String testName) {
        int[] actualRowCounts = countNegativesRowWise(matrix);
        int[] actualColumnCounts = countNegativesColumnWise(matrix);

        boolean rowPass = Arrays.equals(actualRowCounts, expectedRowCounts);
        boolean columnPass = Arrays.equals(actualColumnCounts, expectedColumnCounts);

        System.out.println(testName + ":");
        System.out.println("Row-wise count: " + (rowPass ? "PASS" : "FAIL"));
        System.out.println("Column-wise count: " + (columnPass ? "PASS" : "FAIL"));
        System.out.println();
    }
}
