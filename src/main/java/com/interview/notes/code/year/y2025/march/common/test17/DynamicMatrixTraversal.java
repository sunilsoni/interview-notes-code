package com.interview.notes.code.year.y2025.march.common.test17;

public class DynamicMatrixTraversal {
    public static void traverseMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            System.out.println("Empty matrix");
            return;
        }

        int rows = matrix.length;
        int cols = matrix[0].length;
        StringBuilder result = new StringBuilder();

        // First row
        for (int j = 0; j < cols && 0 < rows; j++) {
            result.append(matrix[0][j]).append(" ");
        }
        result.append("| ");

        // Last row reverse
        if (rows > 1) {
            for (int j = cols - 1; j >= 0; j--) {
                result.append(matrix[rows - 1][j]).append(" ");
            }
        }
        result.append("| ");

        // Left diagonal (excluding first and last row)
        for (int i = rows - 2; i >= 1; i--) {
            if (0 < cols) {
                result.append(matrix[i][0]).append(" ");
            }
        }
        result.append("| ");

        // Right column (excluding first and last row)
        for (int i = 1; i < rows - 1; i++) {
            if (cols > 1) {
                result.append(matrix[i][cols - 1]).append(" ");
            }
        }
        result.append("| ");

        // Middle row elements (excluding first and last columns)
        if (rows > 1 && cols > 2) {
            for (int j = 1; j < cols - 1; j++) {
                result.append(matrix[1][j]).append(" ");
            }
        }
        result.append("| ");

        // Second last row reverse (excluding first and last columns)
        if (rows > 3 && cols > 2) {
            for (int j = cols - 2; j >= 1; j--) {
                result.append(matrix[rows - 2][j]).append(" ");
            }
        }
        result.append("| ");

        // Middle elements
        if (rows > 2 && cols > 1) {
            result.append(matrix[rows/2][1]).append(" | ");
        }
        
        if (rows > 2 && cols > 3) {
            result.append(matrix[rows/2][cols-2]).append(" | ");
        }
        
        if (rows > 2 && cols > 2) {
            result.append(matrix[rows/2][cols/2]);
        }

        System.out.println(result.toString());
    }

    public static void main(String[] args) {
        // Test with different matrix sizes
        
        // Original 5x5 matrix
        int[][] matrix5x5 = {
            {1, 2, 3, 4, 5},
            {6, 7, 8, 9, 1},
            {2, 3, 4, 5, 6},
            {7, 8, 9, 1, 2},
            {3, 4, 5, 6, 7}
        };
        System.out.println("5x5 Matrix:");
        traverseMatrix(matrix5x5);

        // 3x3 matrix
        int[][] matrix3x3 = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        System.out.println("\n3x3 Matrix:");
        traverseMatrix(matrix3x3);

        // 4x4 matrix
        int[][] matrix4x4 = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 16}
        };
        System.out.println("\n4x4 Matrix:");
        traverseMatrix(matrix4x4);

        // 2x2 matrix
        int[][] matrix2x2 = {
            {1, 2},
            {3, 4}
        };
        System.out.println("\n2x2 Matrix:");
        traverseMatrix(matrix2x2);
    }
}
