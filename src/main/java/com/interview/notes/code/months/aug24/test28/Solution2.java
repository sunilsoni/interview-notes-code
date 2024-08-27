package com.interview.notes.code.months.aug24.test28;

import java.util.LinkedList;
import java.util.Queue;

public class Solution2 {

    private static final int[] DIR_X = {0, 0, 1, -1}; // Directions: Right, Left, Down, Up
    private static final int[] DIR_Y = {1, -1, 0, 0};

    public static void main(String[] args) {
        Solution2 sol = new Solution2();

        int[][] matrix1 = {
                {3, 4, 6},
                {2, 7, 6}
        };
        System.out.println(sol.solution(matrix1)); // Expected output: 3

        int[][] matrix2 = {
                {3, 3, 5, 6},
                {6, 7, 2, 2},
                {5, 2, 3, 3},
                {5, 9, 3, 2},
                {1, 2, 3, 4}
        };
        System.out.println(sol.solution(matrix2)); // Expected output: 8

        // Add more test cases as needed to ensure the solution handles all scenarios.
    }

    public int solution(int[][] A) {
        int n = A.length;
        int m = A[0].length;
        boolean[][] visited = new boolean[n][m];
        int maxGroupSize = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited[i][j]) {
                    maxGroupSize = Math.max(maxGroupSize, bfs(A, visited, i, j));
                }
            }
        }

        return maxGroupSize;
    }

    private int bfs(int[][] A, boolean[][] visited, int startX, int startY) {
        int minVal = A[startX][startY];
        int maxVal = A[startX][startY];
        int groupSize = 0;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{startX, startY});
        visited[startX][startY] = true;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int x = curr[0], y = curr[1];
            groupSize++;

            for (int d = 0; d < 4; d++) {
                int newX = x + DIR_X[d];
                int newY = y + DIR_Y[d];

                if (newX >= 0 && newX < A.length && newY >= 0 && newY < A[0].length && !visited[newX][newY]) {
                    int newValue = A[newX][newY];
                    int newMinVal = Math.min(minVal, newValue);
                    int newMaxVal = Math.max(maxVal, newValue);

                    if (newMaxVal - newMinVal <= 1) {
                        queue.add(new int[]{newX, newY});
                        visited[newX][newY] = true;
                        minVal = newMinVal;
                        maxVal = newMaxVal;
                    }
                }
            }
        }

        return groupSize;
    }
}
