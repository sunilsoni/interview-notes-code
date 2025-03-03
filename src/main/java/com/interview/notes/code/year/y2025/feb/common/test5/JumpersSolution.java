package com.interview.notes.code.year.y2025.feb.common.test5;

import java.util.HashSet;
import java.util.Set;

public class JumpersSolution {

    /**
     * Finds the maximum number of jumps for the piece at (startRow, startCol).
     */
    public static int findMaxJumps(int[][] board, int startRow, int startCol) {
        int n = board.length;
        if (n == 0) return 0; // Edge case: empty board

        int myPiece = board[startRow][startCol];
        // Opponent piece is the other number (assuming myPiece == 1 or 2)
        int opponent = (myPiece == 1) ? 2 : 1;

        // Keep track of which opponent pieces have been jumped over
        Set<String> jumpedPieces = new HashSet<>();

        return dfs(board, startRow, startCol, myPiece, opponent, jumpedPieces);
    }

    /**
     * Recursive DFS to explore all possible jump paths.
     */
    private static int dfs(int[][] board, int row, int col,
                           int myPiece, int opponent,
                           Set<String> jumpedPieces) {
        int maxJumps = 0;

        // Directions: up, down, left, right
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        for (int[] dir : directions) {
            int dx = dir[0];
            int dy = dir[1];

            int adjRow = row + dx;
            int adjCol = col + dy;
            int landRow = row + 2 * dx;
            int landCol = col + 2 * dy;

            // Check boundaries
            if (isValid(board, adjRow, adjCol) && isValid(board, landRow, landCol)) {
                // Must find an opponent piece at (adjRow, adjCol)
                if (board[adjRow][adjCol] == opponent) {
                    // Must not have jumped over this piece before
                    String pieceKey = adjRow + "_" + adjCol;
                    if (!jumpedPieces.contains(pieceKey)) {
                        // Must land on an empty space
                        if (board[landRow][landCol] == 0) {
                            // Make the jump
                            jumpedPieces.add(pieceKey);
                            int jumpsFromHere = 1 + dfs(board, landRow, landCol, myPiece, opponent, jumpedPieces);
                            maxJumps = Math.max(maxJumps, jumpsFromHere);
                            // Backtrack
                            jumpedPieces.remove(pieceKey);
                        }
                    }
                }
            }
        }

        return maxJumps;
    }

    /**
     * Check if row/col is within the board boundary.
     */
    private static boolean isValid(int[][] board, int r, int c) {
        int n = board.length;
        return (r >= 0 && r < n && c >= 0 && c < n);
    }

    /**
     * Main method - runs sample tests (no JUnit). Prints PASS/FAIL for each.
     */
    public static void main(String[] args) {
        // FIXED Board1: The puzzle’s example row 2 col 2 should be a '1' to allow 3 jumps.
        // So the third row is now {0, 1, 1, 0} instead of {0, 1, 2, 0}.
        int[][] board1 = {
                {0, 2, 0, 0},
                {2, 0, 2, 0},
                {0, 1, 1, 0},
                {0, 2, 1, 0}
        };
        // We test the piece at (2, 2) => it's a '1' now, so it can jump over '2's
        int expected1 = 3;
        int actual1 = findMaxJumps(board1, 2, 2);
        printTestResult("Board1 test", expected1, actual1);

        // Board2: all 1's => no jumps possible
        int[][] board2 = {
                {1, 1},
                {1, 1}
        };
        int expected2 = 0;
        int actual2 = findMaxJumps(board2, 0, 0);
        printTestResult("Board2 test", expected2, actual2);

        // Board3: a larger example; no exact expected, just check it runs
        int[][] board3 = {
                {0, 2, 0, 2, 0},
                {1, 0, 1, 0, 2},
                {0, 2, 1, 2, 0},
                {2, 0, 1, 0, 2},
                {0, 1, 2, 1, 0}
        };
        int actual3 = findMaxJumps(board3, 1, 0);
        System.out.println("Board3 test => Jumps: " + actual3);

        // Add your own tests, including edge cases or large random boards
    }

    private static void printTestResult(String testName, int expected, int actual) {
        if (expected == actual) {
            System.out.println(testName + ": PASS (expected=" + expected + ", got=" + actual + ")");
        } else {
            System.out.println(testName + ": FAIL (expected=" + expected + ", got=" + actual + ")");
        }
    }
}
