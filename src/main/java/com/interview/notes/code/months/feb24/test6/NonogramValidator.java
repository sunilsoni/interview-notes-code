package com.interview.notes.code.months.feb24.test6;

import java.util.ArrayList;
import java.util.List;

public class NonogramValidator {
    // Main validation function with modified input parameters
    public static boolean validateNonogram(char[][] matrix, int[][] rowsInstructions, int[][] columnsInstructions) {
        for (int i = 0; i < matrix.length; i++) {
            if (!validateLine(matrix[i], convertToList(rowsInstructions[i]))) {
                return false;
            }
        }
        for (int i = 0; i < matrix[0].length; i++) {
           /* if (!validateLine(extractColumn(matrix, i), convertToList(columnsInstructions[i]))) {
                return false;
            }*/
        }
        return true;
    }

    // Convert array to List<Integer>
    private static List<Integer> convertToList(int[] array) {
        List<Integer> list = new ArrayList<>();
        for (int value : array) {
            if (value != 0) { // Assuming 0 means no instruction, as in the case of empty columns
                list.add(value);
            }
        }
        return list;
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
    // ... (rest of the NonogramValidator class remains unchanged)

    // Main method for execution and testing with the new input format
    public static void main(String[] args) {
        char[][] matrix3 = {
                // ... (your matrix data here)
        };
        int[][] rows3_2 = {{1, 2, 2}};
        int[][] columns3_2 = {{1}, {}, {1}, {1}, {}, {1}};

        System.out.println("validateNonogram(matrix3, rows3_2, columns3_2) => " +
                validateNonogram(matrix3, rows3_2, columns3_2));
        // The matrix3 data should be filled in accordingly.
    }
}
