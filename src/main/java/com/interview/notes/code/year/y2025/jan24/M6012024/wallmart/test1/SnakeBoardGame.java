package com.interview.notes.code.year.y2025.jan24.M6012024.wallmart.test1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SnakeBoardGame {
    public static Result findPassableLanes(char[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            return new Result(new int[]{}, new int[]{});
        }

        List<Integer> passableRows = new ArrayList<>();
        List<Integer> passableCols = new ArrayList<>();

        // Check rows
        for (int i = 0; i < board.length; i++) {
            boolean passable = true;
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == '+') {
                    passable = false;
                    break;
                }
            }
            if (passable) {
                passableRows.add(i);
            }
        }

        // Check columns
        for (int j = 0; j < board[0].length; j++) {
            boolean passable = true;
            for (int i = 0; i < board.length; i++) {
                if (board[i][j] == '+') {
                    passable = false;
                    break;
                }
            }
            if (passable) {
                passableCols.add(j);
            }
        }

        return new Result(
                passableRows.stream().mapToInt(i -> i).toArray(),
                passableCols.stream().mapToInt(i -> i).toArray()
        );
    }

    public static void main(String[] args) {
        // Test cases
        char[][] board1 = {
                {'+', '+', '+', '+', '0', '0'},
                {'0', '0', '+', '0', '0', '0'},
                {'0', '0', '+', '0', '0', '0'},
                {'0', '0', '+', '0', '0', '0'}
        };

        char[][] board2 = {
                {'+', '+', '+', '+', '0', '0'},
                {'0', '0', '0', '0', '0', '0'},
                {'0', '0', '0', '0', '0', '0'},
                {'0', '0', '0', '0', '0', '0'}
        };

        // Large board test
        char[][] largeBoard = new char[1000][1000];
        for (char[] row : largeBoard) {
            Arrays.fill(row, '0');
        }

        // Run tests
        runTest("Test 1", board1, "[1], [4, 5]");
        runTest("Test 2", board2, "[1, 2, 3], [4, 5]");
        runTest("Large Board Test", largeBoard, "All rows and columns passable");
    }

    private static void runTest(String testName, char[][] board, String expectedResult) {
        try {
            long startTime = System.currentTimeMillis();
            Result result = findPassableLanes(board);
            long endTime = System.currentTimeMillis();

            System.out.println(testName);
            System.out.println("Result: " + result);
            System.out.println("Time taken: " + (endTime - startTime) + "ms");
            System.out.println("Expected: " + expectedResult);
            System.out.println("Status: " +
                    (result.toString().contains(expectedResult) ? "PASS" : "FAIL"));
            System.out.println("--------------------");
        } catch (Exception e) {
            System.out.println(testName + " FAILED with exception: " + e.getMessage());
        }
    }

    public static class Result {
        private final int[] passableRows;
        private final int[] passableColumns;

        public Result(int[] rows, int[] cols) {
            this.passableRows = rows;
            this.passableColumns = cols;
        }

        @Override
        public String toString() {
            return "Rows: " + Arrays.toString(passableRows) +
                    ", Columns: " + Arrays.toString(passableColumns);
        }
    }
}
