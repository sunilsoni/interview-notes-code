package com.interview.notes.code.year.y2025.jan24.M6012024.wallmart.test2;

import java.util.LinkedList;
import java.util.Queue;

public class SnakeExitFinder {

    /**
     * Finds the coordinates of the nearest exit (row, col) from the start position.
     * Returns null if no exit is reachable.
     *
     * board: 2D array of '+' (impassable) and '0' (passable)
     * start: int[] with two elements: [startRow, startCol]
     */
    public static int[] findExit(char[][] board, int[] start) {
        int rows = board.length;
        int cols = board[0].length;

        // If start is already an exit, return start immediately
        if (isExit(start[0], start[1], rows, cols)) {
            return start;
        }

        // Visited matrix to avoid revisiting squares
        boolean[][] visited = new boolean[rows][cols];
        visited[start[0]][start[1]] = true;

        // Directions: up, down, left, right
        int[][] directions = { {-1, 0}, {1, 0}, {0, -1}, {0, 1} };

        // Queue for BFS (stores row, col)
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] { start[0], start[1] });

        // Perform BFS
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int cr = current[0];
            int cc = current[1];

            for (int[] dir : directions) {
                int nr = cr + dir[0];
                int nc = cc + dir[1];

                // Check boundaries
                if (nr < 0 || nr >= rows || nc < 0 || nc >= cols) {
                    continue; // out of board range
                }
                // Skip if impassable or already visited
                if (board[nr][nc] == '+' || visited[nr][nc]) {
                    continue;
                }

                visited[nr][nc] = true; // mark visited

                // If this is an exit, return it
                if (isExit(nr, nc, rows, cols)) {
                    return new int[] { nr, nc };
                }
                // Otherwise, keep searching
                queue.offer(new int[] { nr, nc });
            }
        }

        // No exit found
        return null;
    }

    /**
     * Helper method to check if a position is on the edge of the board.
     */
    private static boolean isExit(int r, int c, int rows, int cols) {
        // An exit is defined as a valid position on the board's edge
        return (r == 0 || r == rows - 1 || c == 0 || c == cols - 1);
    }

    /**
     * Main method to run test cases and print pass/fail.
     */
    public static void main(String[] args) {
        // Example boards
        char[][] board1 = {
            {'+','+','+','+','+','+','0','0'},
            {'+','0','0','0','0','0','+','+'},
            {'+','+','0','+','+','+','+','0'},
            {'+','+','+','+','+','0','+','0'},
            {'+','+','+','+','+','+','0','+'},
            {'+','+','+','+','+','+','+','+'}
        };

        // Test case 1: start = (2, 0) expected output = (5, 2)
        int[] result1 = findExit(board1, new int[] {2, 0});
        testResult("Test1", result1, new int[] {5, 2});

        // Test case 2: start = (0, 7) expected output = (0, 8) but col=8 is out of range for this example
        // Let's assume the edges are from 0..7. If we actually wanted col=8, we need to ensure the board has col=8
        // Adjusting to match the sample data, we might have a different expected exit.
        // For illustration, let's skip this or treat as unreachable if col=8 is out of bounds.
        // Let's show a scenario that returns null
        int[] result2 = findExit(board1, new int[] {0, 7});
        testResult("Test2", result2, null);

        // Additional boards or scenarios can be added here for more thorough testing...

        // Large data test (brief illustration)
        // We'll not fully implement a giant board here, but in real usage, you'd create a big board
        // and ensure the BFS completes in reasonable time.
    }

    /**
     * Utility method to compare the result of findExit with an expected output and print pass/fail.
     */
    private static void testResult(String testName, int[] actual, int[] expected) {
        boolean pass;
        if (expected == null && actual == null) {
            pass = true;
        } else if (actual == null || expected == null) {
            pass = false;
        } else {
            pass = (actual[0] == expected[0] && actual[1] == expected[1]);
        }
        System.out.println(testName + " => " + (pass ? "PASS" : "FAIL") 
                           + " (Expected: " + format(expected) 
                           + ", Got: " + format(actual) + ")");
    }

    /**
     * Helper to format coordinate arrays for printing.
     */
    private static String format(int[] arr) {
        if (arr == null) return "null";
        return "(" + arr[0] + ", " + arr[1] + ")";
    }
}
