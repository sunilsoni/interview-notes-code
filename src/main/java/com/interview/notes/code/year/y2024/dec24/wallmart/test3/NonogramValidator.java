package com.interview.notes.code.year.y2024.dec24.wallmart.test3;

import java.util.ArrayList;
import java.util.List;

public class NonogramValidator {
    public static boolean validateNonogram(char[][] matrix, int[][] rowInstructions, int[][] colInstructions) {
        // Input validation
        if (matrix == null || rowInstructions == null || colInstructions == null) {
            return false;
        }

        if (matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }

        if (matrix.length != rowInstructions.length ||
                matrix[0].length != colInstructions.length) {
            return false;
        }

        // Validate rows and columns
        if (!validateRows(matrix, rowInstructions)) {
            return false;
        }
        return validateColumns(matrix, colInstructions);
    }

    private static boolean validateRows(char[][] matrix, int[][] rowInstructions) {
        for (int i = 0; i < matrix.length; i++) {
            if (rowInstructions[i] == null) {
                rowInstructions[i] = new int[0]; // Empty instruction means no black cells
            }
            if (!validateLine(matrix[i], rowInstructions[i])) {
                return false;
            }
        }
        return true;
    }

    private static boolean validateColumns(char[][] matrix, int[][] colInstructions) {
        for (int j = 0; j < matrix[0].length; j++) {
            if (colInstructions[j] == null) {
                colInstructions[j] = new int[0]; // Empty instruction means no black cells
            }
            char[] column = extractColumn(matrix, j);
            if (!validateLine(column, colInstructions[j])) {
                return false;
            }
        }
        return true;
    }

    private static char[] extractColumn(char[][] matrix, int col) {
        char[] column = new char[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            column[i] = matrix[i][col];
        }
        return column;
    }

    private static boolean validateLine(char[] line, int[] instructions) {
        List<Integer> runs = new ArrayList<>();
        int currentRun = 0;

        // Count runs of black cells
        for (char cell : line) {
            if (cell == 'B') {
                currentRun++;
            } else if (currentRun > 0) {
                runs.add(currentRun);
                currentRun = 0;
            }
        }
        if (currentRun > 0) {
            runs.add(currentRun);
        }

        // Handle empty instructions
        if (instructions.length == 0) {
            return runs.isEmpty();
        }

        // Compare with instructions
        if (runs.size() != instructions.length) {
            return false;
        }

        for (int i = 0; i < instructions.length; i++) {
            if (runs.get(i) != instructions[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        // Test Case 1
        char[][] matrix1 = {
                {'W', 'W', 'W', 'W'},
                {'B', 'W', 'W', 'W'},
                {'B', 'W', 'B', 'B'},
                {'W', 'W', 'B', 'W'},
                {'B', 'B', 'W', 'W'}
        };

        int[][] rows1_1 = {new int[]{}, {1}, {1, 2}, {1}, {2}};
        int[][] cols1_1 = {{2, 1}, new int[]{}, {2}, {1}};

        System.out.println("Test 1: " + validateNonogram(matrix1, rows1_1, cols1_1));

        // Test Case 2 (with null instructions)
        int[][] rows1_2 = {null, {}, {1}, {1}, {1, 1}};
        int[][] cols1_2 = {{2}, {1}, {2}, {1}};

        System.out.println("Test 2: " + validateNonogram(matrix1, rows1_2, cols1_2));

        // Test Case 3 (empty matrix)
        char[][] emptyMatrix = new char[0][0];
        int[][] emptyInstructions = new int[0][];

        System.out.println("Test 3 (Empty Matrix): " +
                validateNonogram(emptyMatrix, emptyInstructions, emptyInstructions));

        // Large data test
        System.out.println("\nTesting large matrix...");
        testLargeMatrix();
    }

    private static void testLargeMatrix() {
        int size = 1000;
        char[][] largeMatrix = new char[size][size];
        int[][] largeRows = new int[size][];
        int[][] largeCols = new int[size][];

        // Initialize large matrix with a pattern
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                largeMatrix[i][j] = ((i + j) % 2 == 0) ? 'B' : 'W';
            }
            largeRows[i] = new int[]{size / 2}; // Each row has size/2 black cells
            largeCols[i] = new int[]{size / 2}; // Each column has size/2 black cells
        }

        long startTime = System.currentTimeMillis();
        boolean result = validateNonogram(largeMatrix, largeRows, largeCols);
        long endTime = System.currentTimeMillis();

        System.out.println("Large Matrix Result: " + result);
        System.out.println("Execution time: " + (endTime - startTime) + "ms");
    }
}
