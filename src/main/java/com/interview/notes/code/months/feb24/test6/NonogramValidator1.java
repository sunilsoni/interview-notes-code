package com.interview.notes.code.months.feb24.test6;

import java.util.ArrayList;
import java.util.List;

public class NonogramValidator1 {
    // Main validation function
    public static boolean validateNonogram(char[][] matrix, List<List<Integer>> rowsInstructions, List<List<Integer>> columnsInstructions) {
        for (int i = 0; i < matrix.length; i++) {
            if (!validateLine(matrix[i], rowsInstructions.get(i))) {
                return false;
            }
        }
        for (int i = 0; i < matrix[0].length; i++) {
            if (!validateLine(extractColumn(matrix, i), columnsInstructions.get(i))) {
                return false;
            }
        }
        return true;
    }

    // Helper function to validate a single line against instructions
    private static boolean validateLine(char[] line, List<Integer> instructions) {
        List<Integer> runLengths = new ArrayList<>();
        int count = 0;
        
        for (char c : line) {
            if (c == 'B') {
                count++;
            } else if (count > 0) {
                runLengths.add(count);
                count = 0;
            }
        }
        if (count > 0) {
            runLengths.add(count);
        }

        return runLengths.equals(instructions);
    }

    // Helper function to extract a column as an array from the matrix
    private static char[] extractColumn(char[][] matrix, int columnIndex) {
        char[] column = new char[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            column[i] = matrix[i][columnIndex];
        }
        return column;
    }

    // Main method for execution and testing
    public static void main(String[] args) {
        char[][] matrix1 = {
                {'W', 'W', 'W', 'W'},
                {'B', 'W', 'W', 'W'},
                {'B', 'W', 'B', 'B'},
                {'W', 'W', 'B', 'W'},
                {'B', 'B', 'W', 'W'}
        };
        List<List<Integer>> rows1_1 = List.of(
                List.of(),
                List.of(1),
                List.of(1, 2),
                List.of(1),
                List.of(2)
        );
        List<List<Integer>> columns1_1 = List.of(
                List.of(2),
                List.of(1),
                List.of(1),
                List.of(1)
        );

        System.out.println("validateNonogram(matrix1, rows1_1, columns1_1) => " + validateNonogram(matrix1, rows1_1, columns1_1));
        // Add additional test cases based on examples provided in the screenshots
    }
}
