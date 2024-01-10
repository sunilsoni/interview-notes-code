package com.interview.notes.code.months.year2023.dec23.test3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//N-Queens problem using backtracking
public class NQueensSolver {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> solutions = new ArrayList<>();
        char[][] board = new char[n][n];
        for (char[] row : board) {
            Arrays.fill(row, '.');
        }
        solveNQueens(board, 0, solutions);
        return solutions;
    }

    private void solveNQueens(char[][] board, int row, List<List<String>> solutions) {
        if (row == board.length) {
            solutions.add(generateSolution(board));
            return;
        }
        for (int col = 0; col < board.length; col++) {
            if (isSafe(board, row, col)) {
                board[row][col] = 'Q';
                solveNQueens(board, row + 1, solutions);
                board[row][col] = '.';
            }
        }
    }

    private boolean isSafe(char[][] board, int row, int col) {
        // Check column
        for (int i = 0; i < row; i++) {
            if (board[i][col] == 'Q') {
                return false;
            }
        }

        // Check left diagonal
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }

        // Check right diagonal
        for (int i = row, j = col; i >= 0 && j < board.length; i--, j++) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }

        return true;
    }

    private List<String> generateSolution(char[][] board) {
        List<String> solution = new ArrayList<>();
        for (char[] row : board) {
            solution.add(new String(row));
        }
        return solution;
    }
}
