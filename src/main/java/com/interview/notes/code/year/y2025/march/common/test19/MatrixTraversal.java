package com.interview.notes.code.year.y2025.march.common.test19;

public class MatrixTraversal {
    public static void traverseMatrix(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        StringBuilder result = new StringBuilder();

        // First row (0,0) to (0,4)
        for(int j = 0; j < cols; j++) {
            result.append(matrix[0][j]).append(" ");
        }
        result.append("| ");

        // Last row reverse (4,4) to (4,0)
        for(int j = cols-1; j >= 0; j--) {
            result.append(matrix[rows-1][j]).append(" ");
        }
        result.append("| ");

        // Diagonal from (3,0) to (1,0)
        for(int i = 3; i >= 1; i--) {
            result.append(matrix[i][0]).append(" ");
        }
        result.append("| ");

        // Right column from (1,4) to (3,4)
        for(int i = 1; i <= 3; i++) {
            result.append(matrix[i][4]).append(" ");
        }
        result.append("| ");

        // Middle elements (1,1) to (1,3)
        for(int j = 1; j <= 3; j++) {
            result.append(matrix[1][j]).append(" ");
        }
        result.append("| ");

        // Reverse diagonal elements (3,3) to (3,1)
        for(int j = 3; j >= 1; j--) {
            result.append(matrix[3][j]).append(" ");
        }
        result.append("| ");

        // Single elements
        result.append(matrix[2][1]).append(" | ");
        result.append(matrix[2][3]).append(" | ");
        result.append(matrix[2][2]);

        System.out.println(result.toString());
    }

    public static void main(String[] args) {
        int[][] matrix = {
            {1, 2, 3, 4, 5},
            {6, 7, 8, 9, 1},
            {2, 3, 4, 5, 6},
            {7, 8, 9, 1, 2},
            {3, 4, 5, 6, 7}
        };
        
        traverseMatrix(matrix);
    }
}
