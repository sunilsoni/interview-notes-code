package com.interview.notes.code.year.y2025.november.karat.test1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * validateNonogram: checks if a given nonogram solution matches
 * the provided row and column run-length instructions.
 *
 * Each instruction (row or column) lists contiguous runs of 'B' cells.
 * Example: [2,1] means "two blacks, then at least one white, then one black".
 *
 * Input:
 *  - matrix: 2D array of chars ('B' or 'W')
 *  - rowInstr: List<List<Integer>> describing black-run lengths per row
 *  - colInstr: List<List<Integer>> describing black-run lengths per column
 *
 * Output:
 *  - true if all row and column clues match, false otherwise
 */
public class NonogramValidator {

    // ------------------ Core function ------------------

    public static boolean validateNonogram(char[][] matrix,
                                           List<List<Integer>> rowInstr,
                                           List<List<Integer>> colInstr) {

        int rows = matrix.length;
        if (rows == 0) return false;                     // empty grid invalid
        int cols = matrix[0].length;

        // Check row instructions
        for (int i = 0; i < rows; i++) {
            List<Integer> runs = extractRuns(matrix[i]); // get black-run pattern of this row
            if (!runs.equals(rowInstr.get(i))) return false;
        }

        // Check column instructions
        for (int j = 0; j < cols; j++) {
            char[] col = new char[rows];                 // build each column as char array
            for (int i = 0; i < rows; i++) col[i] = matrix[i][j];
            List<Integer> runs = extractRuns(col);
            if (!runs.equals(colInstr.get(j))) return false;
        }

        return true; // all rows and columns match
    }

    // ------------------ Helper to extract contiguous black-run lengths ------------------

    private static List<Integer> extractRuns(char[] line) {
        List<Integer> runs = new ArrayList<>();
        int count = 0;
        for (char c : line) {
            if (c == 'B') count++;
            else if (count > 0) { runs.add(count); count = 0; } // end of a run
        }
        if (count > 0) runs.add(count); // add last run if ends with 'B'
        return runs;
    }

    // ------------------ Utilities for building test cases ------------------

    private static List<Integer> li(int... arr) {
        return Arrays.stream(arr).boxed().collect(Collectors.toList());
    }

    // ------------------ Demo / Tests ------------------

    public static void main(String[] args) {

        // Example 1 (from screenshot)
        char[][] matrix1 = {
                {'W','W','W','W'},
                {'B','W','W','B'},
                {'B','W','B','W'},
                {'B','B','W','W'}
        };
        List<List<Integer>> rows1_1 = Arrays.asList(
                li(), li(1,1), li(1), li(2)
        );
        List<List<Integer>> cols1_1 = Arrays.asList(
                li(2,1), li(1), li(2), li(1)
        );
        System.out.println("validateNonogram(matrix1, rows1_1, cols1_1) => "
                + validateNonogram(matrix1, rows1_1, cols1_1));  // True

        // Example 2 (same matrix, different instructions)
        List<List<Integer>> rows1_2 = Arrays.asList(
                li(), li(1), li(1), li(1)
        );
        List<List<Integer>> cols1_2 = Arrays.asList(
                li(2), li(1), li(2), li(1)
        );
        System.out.println("validateNonogram(matrix1, rows1_2, cols1_2) => "
                + validateNonogram(matrix1, rows1_2, cols1_2));  // False

        // You can continue adding other test combinations here
        // (like matrix2, rows2_1, columns2_1, etc. from screenshot)
    }
}
