package com.interview.notes.code.year.y2024.sept24.visa.test1;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class SnakeBoardGame {
    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // Up, Down, Left, Right

    public static int[] findExit(char[][] board, int[] start) {
        int rows = board.length;
        int cols = board[0].length;

        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[rows][cols];

        queue.offer(new int[]{start[0], start[1], 0}); // row, col, distance
        visited[start[0]][start[1]] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int row = current[0], col = current[1], distance = current[2];

            // Check if current position is an exit
            if (isExit(row, col, rows, cols) && !(row == start[0] && col == start[1])) {
                return new int[]{row, col};
            }

            // Explore neighbors
            for (int[] dir : DIRECTIONS) {
                int newRow = row + dir[0];
                int newCol = col + dir[1];

                if (isValid(newRow, newCol, rows, cols) && !visited[newRow][newCol] && board[newRow][newCol] == '0') {
                    queue.offer(new int[]{newRow, newCol, distance + 1});
                    visited[newRow][newCol] = true;
                }
            }
        }

        return null; // No exit found
    }

    private static boolean isExit(int row, int col, int rows, int cols) {
        return row == 0 || row == rows - 1 || col == 0 || col == cols - 1;
    }

    private static boolean isValid(int row, int col, int rows, int cols) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }

    public static void main(String[] args) {
        // Test cases
        char[][] board1 = {
                {'+', '+', '+', '+', '+', '+', '0', '0', '0'},
                {'+', '0', '0', '0', '0', '0', '0', '+', '0'},
                {'0', '0', '0', '0', '0', '+', '+', '0', '0'},
                {'+', '+', '0', '+', '+', '+', '0', '0', '0'},
                {'+', '+', '0', '0', '0', '+', '0', '+', '+'},
                {'+', '+', '0', '+', '0', '0', '0', '0', '+'}
        };

        int[] start1_1 = {2, 0};
        int[] start1_2 = {0, 7};
        int[] start1_3 = {5, 2};
        int[] start1_4 = {5, 5};

        System.out.println("Test case 1_1: " + Arrays.toString(findExit(board1, start1_1)));
        System.out.println("Test case 1_2: " + Arrays.toString(findExit(board1, start1_2)));
        System.out.println("Test case 1_3: " + Arrays.toString(findExit(board1, start1_3)));
        System.out.println("Test case 1_4: " + Arrays.toString(findExit(board1, start1_4)));

        // Add more test cases here...
    }
}
