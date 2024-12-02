package com.interview.notes.code.year.y2024.oct24.amazon.test24;

import java.util.HashSet;
import java.util.Set;

public class SudokuValidator {

    public static boolean isValidSudoku(char[][] board) {
        // Check rows
        for (int i = 0; i < 9; i++) {
            if (!isValidGroup(board[i])) {
                return false;
            }
        }

        // Check columns
        for (int j = 0; j < 9; j++) {
            char[] column = new char[9];
            for (int i = 0; i < 9; i++) {
                column[i] = board[i][j];
            }
            if (!isValidGroup(column)) {
                return false;
            }
        }

        // Check 3x3 sub-boxes
        for (int boxRow = 0; boxRow < 3; boxRow++) {
            for (int boxCol = 0; boxCol < 3; boxCol++) {
                char[] subBox = new char[9];
                int index = 0;
                for (int i = boxRow * 3; i < boxRow * 3 + 3; i++) {
                    for (int j = boxCol * 3; j < boxCol * 3 + 3; j++) {
                        subBox[index++] = board[i][j];
                    }
                }
                if (!isValidGroup(subBox)) {
                    return false;
                }
            }
        }

        return true;
    }

    private static boolean isValidGroup(char[] group) {
        Set<Character> seen = new HashSet<>();
        for (char c : group) {
            if (c != '.') {
                if (seen.contains(c)) {
                    return false;
                }
                seen.add(c);
            }
        }
        return true;
    }

    public static void main(String[] args) {
        // Test case 1: Valid Sudoku
        char[][] validSudoku = {
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        System.out.println("Test case 1 (Valid Sudoku): " + (isValidSudoku(validSudoku) ? "PASS" : "FAIL"));

        // Test case 2: Invalid Sudoku (repeated number in row)
        char[][] invalidSudoku1 = {
                {'8', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        System.out.println("Test case 2 (Invalid Sudoku - repeated number in row): " + (!isValidSudoku(invalidSudoku1) ? "PASS" : "FAIL"));

        // Test case 3: Invalid Sudoku (repeated number in column)
        char[][] invalidSudoku2 = {
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '5', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        System.out.println("Test case 3 (Invalid Sudoku - repeated number in column): " + (!isValidSudoku(invalidSudoku2) ? "PASS" : "FAIL"));

        // Test case 4: Invalid Sudoku (repeated number in 3x3 sub-box)
        char[][] invalidSudoku3 = {
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '3', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        System.out.println("Test case 4 (Invalid Sudoku - repeated number in 3x3 sub-box): " + (!isValidSudoku(invalidSudoku3) ? "PASS" : "FAIL"));

        // Test case 5: Large Sudoku (81x81)
        char[][] largeSudoku = new char[81][81];
        for (int i = 0; i < 81; i++) {
            for (int j = 0; j < 81; j++) {
                largeSudoku[i][j] = (char) ('1' + (i * 9 + j) % 9);
            }
        }
        long startTime = System.currentTimeMillis();
        boolean isValid = isValidSudoku(largeSudoku);
        long endTime = System.currentTimeMillis();
        System.out.println("Test case 5 (Large Sudoku 81x81): " + (isValid ? "PASS" : "FAIL"));
        System.out.println("Execution time for large Sudoku: " + (endTime - startTime) + " ms");
    }
}