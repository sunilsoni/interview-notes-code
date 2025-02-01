package com.interview.notes.code.year.y2025.jan25.M6012024.wallmart.test2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class SnakeBoardSolver {
    public static Result findPassableLanes(char[][] board) {
        if (board == null || board.length == 0) {
            return new Result(new ArrayList<>(), new ArrayList<>());
        }

        List<Integer> passableRows = new ArrayList<>();
        List<Integer> passableCols = new ArrayList<>();

        // Check rows
        for (int row = 0; row < board.length; row++) {
            boolean passable = true;
            for (int col = 0; col < board[row].length; col++) {
                if (board[row][col] == '+') {
                    passable = false;
                    break;
                }
            }
            if (passable) passableRows.add(row);
        }

        // Check columns
        int cols = board[0].length;
        for (int col = 0; col < cols; col++) {
            boolean passable = true;
            for (int row = 0; row < board.length; row++) {
                if (board[row][col] == '+') {
                    passable = false;
                    break;
                }
            }
            if (passable) passableCols.add(col);
        }

        return new Result(passableRows, passableCols);
    }

    public static void main(String[] args) {
        // Test cases
        char[][] board1 = {
                {'+', '+', '+', '+', '0', '0'},
                {'0', '0', '+', '0', '0', '0'},
                {'0', '0', '+', '0', '0', '0'},
                {'0', '0', '+', '0', '0', '0'}
        };

        char[][] board2 = {{'0'}};

        char[][] board3 = {
                {'0', '0'},
                {'0', '0'},
                {'0', '0'}
        };

        // Run tests
        testCase("Test 1", board1, new int[]{}, new int[]{4, 5});
        testCase("Test 2", board2, new int[]{0}, new int[]{0});
        testCase("Test 3", board3, new int[]{0, 1, 2}, new int[]{0, 1});

        // Large board test
        char[][] largeBoard = new char[1000][1000];
        for (char[] row : largeBoard) {
            Arrays.fill(row, '0');
        }
        testCase("Large Board Test", largeBoard,
                range(0, 999), range(0, 999));
    }

    private static void testCase(String testName, char[][] board,
                                 int[] expectedRows, int[] expectedCols) {
        System.out.println("Running " + testName + "...");
        Result result = findPassableLanes(board);

        boolean rowsMatch = Arrays.equals(
                result.passableRows.stream().mapToInt(i -> i).toArray(),
                expectedRows
        );
        boolean colsMatch = Arrays.equals(
                result.passableColumns.stream().mapToInt(i -> i).toArray(),
                expectedCols
        );

        System.out.println(result);
        System.out.println(testName + ": " +
                (rowsMatch && colsMatch ? "PASS" : "FAIL") + "\n");
    }

    private static int[] range(int start, int end) {
        return IntStream.rangeClosed(start, end).toArray();
    }

    public static class Result {
        List<Integer> passableRows;
        List<Integer> passableColumns;

        public Result(List<Integer> rows, List<Integer> cols) {
            this.passableRows = rows;
            this.passableColumns = cols;
        }

        @Override
        public String toString() {
            return "Rows: " + passableRows + ", Columns: " + passableColumns;
        }
    }
}
