package com.interview.notes.code.months.aug24.test27;

import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int solution(int[][] A) {
        if (A == null || A.length == 0 || A[0].length == 0) return 0;

        int n = A.length;
        int m = A[0].length;
        boolean[][] visited = new boolean[n][m];
        int maxGroupSize = 0;

        // Directions for moving up, down, left, right
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited[i][j]) {
                    maxGroupSize = Math.max(maxGroupSize, bfs(A, visited, i, j, directions));
                }
            }
        }

        return maxGroupSize;
    }

    private int bfs(int[][] A, boolean[][] visited, int startX, int startY, int[][] directions) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{startX, startY});
        visited[startX][startY] = true;

        int minValue = A[startX][startY];
        int maxValue = A[startX][startY];
        int groupSize = 0;

        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            groupSize++;

            for (int[] direction : directions) {
                int newX = cell[0] + direction[0];
                int newY = cell[1] + direction[1];

                if (newX >= 0 && newX < A.length && newY >= 0 && newY < A[0].length && !visited[newX][newY]) {
                    if (Math.abs(A[newX][newY] - A[cell[0]][cell[1]]) <= 1) {
                        visited[newX][newY] = true;
                        queue.offer(new int[]{newX, newY});
                        minValue = Math.min(minValue, A[newX][newY]);
                        maxValue = Math.max(maxValue, A[newX][newY]);
                    }
                }
            }
        }

        // Check if the group meets the criteria
        return (maxValue - minValue <= 1) ? groupSize : 0;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        int[][] testCase1 = {
            {3, 4, 6},
            {2, 7, 6}
        };
        System.out.println(sol.solution(testCase1)); // Expected output: 3

        int[][] testCase2 = {
            {3, 3, 5, 6},
            {6, 7, 2, 2},
            {5, 2, 3, 8},
            {5, 9, 2, 3},
            {1, 2, 3, 4}
        };
        System.out.println(sol.solution(testCase2)); // Expected output: 8

        int[][] testCase3 = {
            {4, 4, 2, 4, 4, 4},
            {4, 4, 4, 4, 4, 4}
        };
        System.out.println(sol.solution(testCase3)); // Expected output: 3

        int[][] testCase4 = {
            {0},
            {3},
            {5}
        };
        System.out.println(sol.solution(testCase4)); // Expected output: 1
    }
}