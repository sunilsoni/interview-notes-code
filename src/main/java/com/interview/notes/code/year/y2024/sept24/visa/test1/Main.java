package com.interview.notes.code.year.y2024.sept24.visa.test1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Test cases
        char[][] board1 = {
                {'+', '+', '+', '0', '+', '0', '0'},
                {'0', '0', '+', '0', '0', '0', '0'},
                {'0', '0', '0', '0', '+', '0', '0'},
                {'+', '+', '+', '0', '0', '+', '0'},
                {'0', '0', '0', '0', '0', '0', '0'}
        };

        char[][] board2 = {
                {'+', '+', '+', '0', '+', '0', '0'},
                {'0', '0', '0', '0', '0', '+', '0'},
                {'0', '0', '+', '0', '0', '0', '0'},
                {'0', '0', '0', '0', '+', '0', '0'},
                {'+', '+', '+', '0', '0', '0', '+'}
        };

        char[][] board3 = {
                {'+', '+', '+', '0', '+', '0', '0'},
                {'0', '0', '0', '0', '0', '0', '0'},
                {'0', '0', '+', '+', '0', '+', '0'},
                {'0', '0', '0', '0', '+', '0', '0'},
                {'+', '+', '+', '0', '0', '0', '+'}
        };

        char[][] board4 = {{'+'}};
        char[][] board5 = {{'0'}};
        char[][] board6 = {
                {'0', '0'},
                {'0', '0'},
                {'0', '0'},
                {'0', '0'}
        };

        // Run test cases
        runTestCase(board1, "board1");
        runTestCase(board2, "board2");
        runTestCase(board3, "board3");
        runTestCase(board4, "board4");
        runTestCase(board5, "board5");
        runTestCase(board6, "board6");
    }

    public static void runTestCase(char[][] board, String boardName) {
        List<List<Integer>> result = findPassableLanes(board);
        System.out.println(boardName + " => Rows: " + result.get(0) + ", Columns: " + result.get(1));
    }

    public static List<List<Integer>> findPassableLanes(char[][] board) {
        List<Integer> passableRows = new ArrayList<>();
        List<Integer> passableColumns = new ArrayList<>();

        // Check rows
        for (int i = 0; i < board.length; i++) {
            if (isRowPassable(board, i)) {
                passableRows.add(i);
            }
        }

        // Check columns
        for (int j = 0; j < board[0].length; j++) {
            if (isColumnPassable(board, j)) {
                passableColumns.add(j);
            }
        }

        return Arrays.asList(passableRows, passableColumns);
    }

    private static boolean isRowPassable(char[][] board, int row) {
        for (int j = 0; j < board[row].length; j++) {
            if (board[row][j] == '+') {
                return false;
            }
        }
        return true;
    }

    private static boolean isColumnPassable(char[][] board, int col) {
        for (int i = 0; i < board.length; i++) {
            if (board[i][col] == '+') {
                return false;
            }
        }
        return true;
    }
}
