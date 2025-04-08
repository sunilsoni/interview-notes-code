package com.interview.notes.code.year.y2025.april.goldman_sachs.test1;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MatrixZeroConverter {
    
    public static int[][] changeMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return matrix;
        
        int rows = matrix.length;
        int cols = matrix[0].length;
        
        // Store rows and columns that need to be zeroed
        Set<Integer> zeroRows = new HashSet<>();
        Set<Integer> zeroCols = new HashSet<>();
        
        // First pass: identify rows and columns to be zeroed
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == 0) {
                    zeroRows.add(i);
                    zeroCols.add(j);
                }
            }
        }
        
        // Second pass: set elements to zero
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (zeroRows.contains(i) || zeroCols.contains(j)) {
                    matrix[i][j] = 0;
                }
            }
        }
        
        return matrix;
    }

    public static void main(String[] args) {
        // Test Case 1: Given example
        int[][] test1 = {
            {1, 1, 0, 1, 1},
            {1, 1, 1, 1, 1},
            {1, 1, 1, 0, 1},
            {1, 1, 1, 1, 1},
            {0, 1, 1, 1, 1}
        };
        
        System.out.println("Test Case 1:");
        System.out.println("Before:");
        printMatrix(test1);
        
        int[][] result = changeMatrix(test1);
        
        System.out.println("After:");
        printMatrix(result);
        
        // Test Case 2: Empty matrix
        int[][] test2 = new int[0][0];
        System.out.println("\nTest Case 2 (Empty matrix): " + 
            (changeMatrix(test2).length == 0 ? "PASS" : "FAIL"));
        
        // Test Case 3: Large matrix (100x100)
        int[][] test3 = new int[100][100];
        test3[50][50] = 0;  // Place a zero in the middle
        int[][] result3 = changeMatrix(test3);
        boolean pass = result3[0][50] == 0 && result3[50][0] == 0;
        System.out.println("Test Case 3 (Large matrix): " + 
            (pass ? "PASS" : "FAIL"));
    }
    
    private static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }
}
