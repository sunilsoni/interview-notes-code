package com.interview.notes.code.year.y2025.jan25.M6012024.wallmart.test3;

import java.util.*;

public class FindNearestExit {

    // Method to find the nearest exit for a given starting position
    public static int[] findExit(char[][] board, int[] start) {
        int rows = board.length;
        int cols = board[0].length;

        // Directions for moving up, down, left, right
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        // Queue for BFS, storing coordinates
        Queue<int[]> queue = new LinkedList<>();
        queue.add(start);

        // Visited array to track visited cells
        boolean[][] visited = new boolean[rows][cols];
        visited[start[0]][start[1]] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];

            // Check if the current position is an exit (on the edge of the board)
            if (isExit(x, y, rows, cols) && !(x == start[0] && y == start[1])) {
                return new int[]{x, y}; // Nearest exit found
            }

            // Explore neighbors
            for (int[] direction : directions) {
                int newX = x + direction[0];
                int newY = y + direction[1];

                // Check if the new position is valid and passable
                if (isValid(newX, newY, rows, cols, board, visited)) {
                    queue.add(new int[]{newX, newY});
                    visited[newX][newY] = true;
                }
            }
        }

        // If no exit is found, return null
        return null;
    }

    // Helper method to check if a position is on the edge of the board (exit)
    private static boolean isExit(int x, int y, int rows, int cols) {
        return x == 0 || y == 0 || x == rows - 1 || y == cols - 1;
    }

    // Helper method to check if a position is valid, passable, and not visited
    private static boolean isValid(int x, int y, int rows, int cols, char[][] board, boolean[][] visited) {
        return x >= 0 && y >= 0 && x < rows && y < cols && board[x][y] == '0' && !visited[x][y];
    }

    // Method to run all test cases and check results
    public static void runTestCases() {
        // Define test cases
        List<TestCase> testCases = new ArrayList<>();

        // Board 1
        char[][] board1 = {
                {'+', '+', '+', '+', '+', '0', '+'},
                {'+', '0', '0', '0', '0', '0', '+'},
                {'+', '0', '+', '+', '+', '0', '+'},
                {'+', '0', '0', '0', '0', '0', '+'},
                {'+', '+', '+', '+', '+', '+', '+'}
        };
        testCases.add(new TestCase(board1, new int[]{2, 0}, new int[]{5, 2}));
        testCases.add(new TestCase(board1, new int[]{2, 7}, new int[]{0, 8}));
        testCases.add(new TestCase(board1, new int[]{5, 5}, null));

        // Board 2
        char[][] board2 = {
                {'+', '+', '+', '+', '+', '+'},
                {'+', '0', '0', '0', '0', '+'},
                {'+', '0', '+', '+', '0', '+'},
                {'+', '0', '0', '0', '0', '+'},
                {'+', '+', '+', '+', '+', '+'}
        };
        testCases.add(new TestCase(board2, new int[]{1, 2}, null));
        testCases.add(new TestCase(board2, new int[]{2, 6}, null));

        // Board 3
        char[][] board3 = {
                {'+', '+', '0', '+', '+'},
                {'+', '0', '0', '0', '+'},
                {'+', '+', '0', '+', '+'}
        };
        testCases.add(new TestCase(board3, new int[]{1, 2}, new int[]{0, 2}));
        testCases.add(new TestCase(board3, new int[]{3, 0}, new int[]{3, 0}));
        testCases.add(new TestCase(board3, new int[]{1, 4}, new int[]{3, 3}));
        testCases.add(new TestCase(board3, new int[]{3, 3}, new int[]{3, 4}));

        // Board 4
        char[][] board4 = {
                {'+', '0', '+', '+', '+'},
                {'+', '0', '0', '0', '0'},
                {'+', '0', '+', '+', '+'}
        };
        testCases.add(new TestCase(board4, new int[]{1, 0}, new int[]{0, 1}));
        testCases.add(new TestCase(board4, new int[]{1, 4}, new int[]{0, 3}));
        testCases.add(new TestCase(board4, new int[]{3, 0}, new int[]{4, 1}));
        testCases.add(new TestCase(board4, new int[]{3, 4}, new int[]{4, 3}));

        // Board 5
        char[][] board5 = {
                {'+', '0', '+', '+', '+'},
                {'+', '0', '0', '0', '0'},
                {'+', '+', '+', '+', '+'}
        };
        testCases.add(new TestCase(board5, new int[]{0, 1}, new int[]{0, 2}));
        testCases.add(new TestCase(board5, new int[]{3, 1}, new int[]{3, 2}));
        testCases.add(new TestCase(board5, new int[]{1, 4}, new int[]{2, 4}));

        // Board 6
        char[][] board6 = {
                {'+', '0', '+', '+', '+'},
                {'+', '0', '0', '0', '0'},
                {'+', '0', '0', '0', '0'},
                {'+', '0', '0', '0', '+'},
                {'+', '+', '+', '0', '+'}
        };
        testCases.add(new TestCase(board6, new int[]{4, 0}, new int[]{1, 0}));

        // Run all test cases
        for (int i = 0; i < testCases.size(); i++) {
            TestCase testCase = testCases.get(i);
            int[] result = findExit(testCase.board, testCase.start);
            if (Arrays.equals(result, testCase.expected)) {
                System.out.println("Test Case " + (i + 1) + " => PASS");
            } else {
                System.out.println("Test Case " + (i + 1) + " => FAIL");
                System.out.println("   Expected: " + Arrays.toString(testCase.expected));
                System.out.println("   Actual:   " + Arrays.toString(result));
            }
        }
    }

    // Main method
    public static void main(String[] args) {
        runTestCases();
    }

    // Helper class to define a test case
    static class TestCase {
        char[][] board;
        int[] start;
        int[] expected;

        TestCase(char[][] board, int[] start, int[] expected) {
            this.board = board;
            this.start = start;
            this.expected = expected;
        }
    }
}
