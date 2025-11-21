package com.interview.notes.code.year.y2025.march.meta.test6;

public class TicTacToe {
    private final int n;           // size of the board (n x n)
    private final int[] rows;      // track sum of moves in each row
    private final int[] cols;      // track sum of moves in each column
    private int diagonal;    // track sum of moves in main diagonal
    private int antiDiagonal; // track sum of moves in anti-diagonal

    // Constructor initializes the game board
    public TicTacToe(int n) {
        this.n = n;
        rows = new int[n];
        cols = new int[n];
        diagonal = 0;
        antiDiagonal = 0;
    }

    /**
     * Makes a move and returns if the player won
     *
     * @param row    - row position (0-based)
     * @param col    - column position (0-based)
     * @param player - player number (1 or 2)
     * @return true if current player won, false otherwise
     */
    public boolean move(int row, int col, int player) {
        // Convert player number to score (+1 for player 1, -1 for player 2)
        int currentPlayer = (player == 1) ? 1 : -1;

        // Update row and column counts
        rows[row] += currentPlayer;
        cols[col] += currentPlayer;

        // Update diagonal if move is on it
        if (row == col) {
            diagonal += currentPlayer;
        }

        // Update anti-diagonal if move is on it
        if (row + col == n - 1) {
            antiDiagonal += currentPlayer;
        }

        // Check if current player won
        return Math.abs(rows[row]) == n ||
                Math.abs(cols[col]) == n ||
                Math.abs(diagonal) == n ||
                Math.abs(antiDiagonal) == n;
    }
}
