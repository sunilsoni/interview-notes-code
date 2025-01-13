package com.interview.notes.code.year.y2025.jan24.M6012024.wallmart.test1;

import java.util.LinkedList;
import java.util.Queue;
//Partially working.....

public class SnakeExitFinder {

    /**
     * Finds the coordinates of the nearest exit (row, col) from the start position,
     * returning null if no exit is reachable. Uses BFS for shortest path.
     * <p>
     * board: 2D array of '+' (impassable) or '0' (passable)
     * start: [startRow, startCol]
     */
    public static int[] findExit(char[][] board, int[] start) {
        int rows = board.length;
        int cols = board[0].length;
        int startR = start[0];
        int startC = start[1];

        // BFS queue and visited array
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[rows][cols];

        queue.offer(new int[]{startR, startC});
        visited[startR][startC] = true;

        // Four directions: up, down, left, right
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int cr = current[0];
            int cc = current[1];

            for (int[] d : directions) {
                int nr = cr + d[0];
                int nc = cc + d[1];

                // Check boundaries
                if (nr < 0 || nr >= rows || nc < 0 || nc >= cols) {
                    continue;
                }
                // Skip impassable or visited
                if (board[nr][nc] == '+' || visited[nr][nc]) {
                    continue;
                }
                visited[nr][nc] = true;

                // If it's a valid exit (and not the start cell), return it
                if (isExit(nr, nc, startR, startC, rows, cols)) {
                    return new int[]{nr, nc};
                }

                // Otherwise, enqueue for further exploration
                queue.offer(new int[]{nr, nc});
            }
        }
        // No exit found
        return null;
    }

    /**
     * A cell is on the edge if row=0 or row=rows-1 or col=0 or col=cols-1.
     * We exclude the start cell from counting as an exit itself (if it’s on the edge).
     */
    private static boolean isExit(int r, int c,
                                  int startR, int startC,
                                  int rows, int cols) {
        if (r == startR && c == startC) {
            // do NOT treat the starting edge as a valid exit
            return false;
        }
        // On the board boundary?
        return (r == 0 || r == rows - 1 || c == 0 || c == cols - 1);
    }

    /**
     * Compare two integer coordinate arrays. Returns true if both are null or both match.
     */
    private static boolean isEqual(int[] a, int[] b) {
        if (a == null && b == null) return true;
        if (a == null || b == null) return false;
        return (a[0] == b[0] && a[1] == b[1]);
    }

    /**
     * Print test results in a "PASS/FAIL" style.
     */
    private static void testResult(String testName, int[] actual, int[] expected) {
        boolean pass = isEqual(actual, expected);
        System.out.println(testName + " => " + (pass ? "PASS" : "FAIL")
                + " (Expected: " + format(expected)
                + ", Got: " + format(actual) + ")");
    }

    /**
     * For tie-cases, we only check if 'actual' matches ANY of the 'allowed' arrays.
     */
    private static void testOneOf(String testName, int[] actual, int[]... allowed) {
        boolean pass = false;
        for (int[] a : allowed) {
            if (isEqual(actual, a)) {
                pass = true;
                break;
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append("(Expected one of: ");
        for (int i = 0; i < allowed.length; i++) {
            sb.append(format(allowed[i]));
            if (i < allowed.length - 1) sb.append(", ");
        }
        sb.append(", Got: ").append(format(actual)).append(")");

        System.out.println(testName + " => " + (pass ? "PASS " : "FAIL ") + sb.toString());
    }

    /**
     * Format an int[] coordinate for printing.
     */
    private static String format(int[] arr) {
        if (arr == null) return "null";
        return "(" + arr[0] + ", " + arr[1] + ")";
    }


    // ----------------------------------------------------------------
    // MAIN METHOD: RUN ALL PUZZLE TESTS
    // ----------------------------------------------------------------
    public static void main(String[] args) {
        // ============ BOARD 1 =========================================
        //
        // Puzzle states:
        //   start1_1 = (2, 0) => (5, 2)
        //   start1_2 = (0, 7) => null
        //   start1_3 = (5, 2) => (2, 0) OR (5, 5)
        //   start1_4 = (5, 5) => (5, 7)
        // 
        // This board has 6 rows (indices 0..5) and 9 columns (indices 0..8).
        // Make sure row2 col0 = '0', so BFS can start there.

        char[][] board1 = {
                //    c0  c1  c2  c3  c4  c5  c6  c7  c8
                {'+', '+', '+', '+', '+', '+', '0', '0', '0'}, // row0
                {'+', '0', '0', '0', '0', '0', '+', '+', '0'}, // row1
                {'0', '0', '0', '+', '+', '+', '+', '0', '0'}, // row2
                {'+', '+', '+', '+', '+', '0', '+', '0', '0'}, // row3
                {'+', '+', '+', '+', '+', '+', '0', '+', '+'}, // row4
                {'+', '+', '+', '+', '+', '+', '+', '+', '+'}  // row5
        };

        // (2,0) => expected (5,2)
        testResult("board1_start1_1",
                findExit(board1, new int[]{2, 0}),
                new int[]{5, 2});

        // (0,7) => expected null
        testResult("board1_start1_2",
                findExit(board1, new int[]{0, 7}),
                null);

        // (5,2) => expected either (2,0) or (5,5)
        int[] actual1_3 = findExit(board1, new int[]{5, 2});
        testOneOf("board1_start1_3", actual1_3,
                new int[]{2, 0}, new int[]{5, 5});

        // (5,5) => expected (5,7)
        testResult("board1_start1_4",
                findExit(board1, new int[]{5, 5}),
                new int[]{5, 7});


        // ============ BOARD 2 =========================================
        // Puzzle states:
        //   start2_1 = (1,0) => null
        //   start2_2 = (2,6) => null
        //
        // This board is smaller and apparently blocked. We'll just define it
        // so BFS can't find an exit from any start.

        char[][] board2 = {
                {'+', '+', '+', '+', '+', '+', '+'},
                {'+', '+', '+', '+', '+', '+', '+'},
                {'+', '+', '+', '+', '+', '+', '+'}
        };

        // (1,0) => null
        testResult("board2_start2_1",
                findExit(board2, new int[]{1, 0}),
                null);

        // (2,6) => null
        testResult("board2_start2_2",
                findExit(board2, new int[]{2, 6}),
                null);


        // ============ BOARD 3 =========================================
        // Puzzle states:
        //   start3_1 = (0,1) => (1,0)
        //   start3_2 = (4,1) => (3,0)
        //   start3_3 = (0,3) => (1,4)
        //   start3_4 = (4,3) => (3,4)

        char[][] board3 = {
                {'+', '0', '+', '0', '+'},
                {'0', '0', '+', '0', '0'},
                {'+', '0', '+', '0', '+'},
                {'0', '0', '+', '0', '0'},
                {'+', '0', '+', '0', '+'}
        };

        testResult("board3_start3_1",
                findExit(board3, new int[]{0, 1}),
                new int[]{1, 0});
        testResult("board3_start3_2",
                findExit(board3, new int[]{4, 1}),
                new int[]{3, 0});
        testResult("board3_start3_3",
                findExit(board3, new int[]{0, 3}),
                new int[]{1, 4});
        testResult("board3_start3_4",
                findExit(board3, new int[]{4, 3}),
                new int[]{3, 4});


        // ============ BOARD 4 =========================================
        // Puzzle states:
        //   start4_1 = (1,0) => (0,1)
        //   start4_2 = (1,4) => (0,3)
        //   start4_3 = (3,0) => (4,1)
        //   start4_4 = (3,4) => (4,3)

        char[][] board4 = {
                {'+', '+', '+', '+', '+'},
                {'0', '0', '0', '0', '0'},
                {'+', '0', '+', '0', '+'},
                {'0', '0', '0', '0', '0'},
                {'+', '+', '+', '+', '+'}
        };

        testResult("board4_start4_1",
                findExit(board4, new int[]{1, 0}),
                new int[]{0, 1});
        testResult("board4_start4_2",
                findExit(board4, new int[]{1, 4}),
                new int[]{0, 3});
        testResult("board4_start4_3",
                findExit(board4, new int[]{3, 0}),
                new int[]{4, 1});
        testResult("board4_start4_4",
                findExit(board4, new int[]{3, 4}),
                new int[]{4, 3});


        // ============ BOARD 5 =========================================
        // Puzzle states:
        //   start5_1 = (0,1) => (0,2)
        //   start5_2 = (3,1) => (3,2)
        //   start5_3 = (1,4) => (2,4)

        char[][] board5 = {
                {'+', '0', '0', '0', '+'},
                {'+', '0', '+', '0', '0'},
                {'+', '+', '0', '0', '+'},
                {'+', '0', '+', '0', '+'}
        };

        testResult("board5_start5_1",
                findExit(board5, new int[]{0, 1}),
                new int[]{0, 2});
        testResult("board5_start5_2",
                findExit(board5, new int[]{3, 1}),
                new int[]{3, 2});
        testResult("board5_start5_3",
                findExit(board5, new int[]{1, 4}),
                new int[]{2, 4});


        // ============ BOARD 6 =========================================
        // Puzzle states:
        //   start6_1 = (4,0) => (1,0)

        char[][] board6 = {
                {'+', '+', '+', '+', '+', '+'},
                {'+', '0', '+', '+', '0', '+'},
                {'+', '0', '0', '0', '0', '0'},
                {'+', '0', '0', '0', '0', '+'},
                {'+', '0', '+', '0', '+', '+'},
                {'+', '+', '+', '+', '+', '+'}
        };

        testResult("board6_start6_1",
                findExit(board6, new int[]{4, 0}),
                new int[]{1, 0});
    }
}
