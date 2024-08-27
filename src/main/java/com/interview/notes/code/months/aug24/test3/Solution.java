package com.interview.notes.code.months.aug24.test3;

import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    private final int[] rowOffset = {-1, 1, 0, 0};
    private final int[] colOffset = {0, 0, -1, 1};
    private int[][] matrix;
    private boolean[][] visited;
    private int rows, cols;

    // Main method for testing
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test case 1
        int[][] A1 = {{3, 4, 6}, {2, 7, 6}};
        System.out.println("Test case 1 result: " + solution.solution(A1));

        // Test case 2
        int[][] A2 = {
                {3, 3, 5, 6},
                {6, 7, 2, 2},
                {5, 2, 3, 8},
                {5, 9, 2, 3},
                {1, 2, 3, 4}
        };
        System.out.println("Test case 2 result: " + solution.solution(A2));

        // Test case 3
        int[][] A3 = {{4, 4, 2, 4, 4, 4}};
        System.out.println("Test case 3 result: " + solution.solution(A3));

        // Test case 4
        int[][] A4 = {{0}, {3}, {5}};
        System.out.println("Test case 4 result: " + solution.solution(A4));
    }

    public int solution(int[][] A) {
        if (A == null || A.length == 0 || A[0].length == 0) {
            return 0;
        }

        matrix = A;
        rows = A.length;
        cols = A[0].length;
        int maxGroupSize = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                visited = new boolean[rows][cols]; // Reset visited array for each starting point
                int groupSize = bfs(i, j);
                maxGroupSize = Math.max(maxGroupSize, groupSize);
            }
        }

        return maxGroupSize;
    }

    private int bfs(int startX, int startY) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{startX, startY});
        visited[startX][startY] = true;
        int size = 1;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0], y = current[1];

            for (int i = 0; i < 4; i++) {
                int newX = x + rowOffset[i];
                int newY = y + colOffset[i];
                if (isValid(newX, newY) && !visited[newX][newY] &&
                        Math.abs(matrix[newX][newY] - matrix[x][y]) <= 1) {
                    queue.offer(new int[]{newX, newY});
                    visited[newX][newY] = true;
                    size++;
                }
            }
        }

        return size;
    }

    private boolean isValid(int x, int y) {
        return x >= 0 && x < rows && y >= 0 && y < cols;
    }
}
