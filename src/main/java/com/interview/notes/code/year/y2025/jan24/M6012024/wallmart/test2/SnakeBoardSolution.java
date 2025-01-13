package com.interview.notes.code.year.y2025.jan24.M6012024.wallmart.test2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SnakeBoardSolution {
    public static void main(String[] args) {
        // Minimal boards (replace or add more for large tests)
        char[][] board1 = {
                {'+', '+', '+', '+', '0', '0'},
                {'0', '0', '+', '0', '0', '0'},
                {'0', '0', '+', '0', '0', '0'},
                {'0', '0', '+', '0', '0', '0'}
        };

        // Expected: rows [4], columns [3, 6] (example from problem)
        List<Integer> expectedRows1 = Collections.singletonList(4);
        List<Integer> expectedCols1 = Arrays.asList(3, 6);

        // Run test
        TestResult result1 = testBoard(board1, expectedRows1, expectedCols1);
        System.out.println("Board1 test: " + (result1.passed ? "PASS" : "FAIL"));
    }

    // Main checker
    private static TestResult testBoard(char[][] board, List<Integer> expRows, List<Integer> expCols) {
        Pair result = findPassableLanes(board);
        boolean pass = result.rows.equals(expRows) && result.cols.equals(expCols);
        return new TestResult(pass, result.rows, result.cols);
    }

    // Core logic
    public static Pair findPassableLanes(char[][] board) {
        int r = board.length, c = board[0].length;
        List<Integer> passableRows = new ArrayList<>();
        List<Integer> passableCols = new ArrayList<>();

        // Check rows
        for (int i = 0; i < r; i++) {
            boolean allZero = true;
            for (int j = 0; j < c; j++) {
                if (board[i][j] != '0') {
                    allZero = false;
                    break;
                }
            }
            if (allZero) passableRows.add(i);
        }

        // Check columns
        for (int j = 0; j < c; j++) {
            boolean allZero = true;
            for (int i = 0; i < r; i++) {
                if (board[i][j] != '0') {
                    allZero = false;
                    break;
                }
            }
            if (allZero) passableCols.add(j);
        }

        return new Pair(passableRows, passableCols);
    }

    // Simple container for test result
    static class TestResult {
        boolean passed;
        List<Integer> rows;
        List<Integer> cols;

        TestResult(boolean p, List<Integer> r, List<Integer> c) {
            passed = p;
            rows = r;
            cols = c;
        }
    }

    // Pair of lists
    static class Pair {
        List<Integer> rows;
        List<Integer> cols;

        Pair(List<Integer> r, List<Integer> c) {
            rows = r;
            cols = c;
        }
    }
}
