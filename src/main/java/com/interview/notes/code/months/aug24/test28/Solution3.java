package com.interview.notes.code.months.aug24.test28;

import java.util.*;

class Solution3 {
    public int solution(int[][] A) {
        int n = A.length;
        if (n == 0) return 0;
        int m = A[0].length;
        boolean[][] visited = new boolean[n][m];
        int maxSize = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited[i][j]) {
                    maxSize = Math.max(maxSize, exploreGroup(A, visited, i, j, n, m));
                }
            }
        }
        return maxSize;
    }
    
    private int exploreGroup(int[][] A, boolean[][] visited, int x, int y, int n, int m) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y});
        visited[x][y] = true;
        int size = 0;
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int cx = cell[0];
            int cy = cell[1];
            size++;

            for (int d = 0; d < 4; d++) {
                int nx = cx + dx[d];
                int ny = cy + dy[d];
                if (nx >= 0 && nx < n && ny >= 0 && ny < m && !visited[nx][ny] && Math.abs(A[cx][cy] - A[nx][ny]) <= 1) {
                    queue.add(new int[]{nx, ny});
                    visited[nx][ny] = true;
                }
            }
        }
        return size;
    }

    public static void main(String[] args) {
        Solution3 sol = new Solution3();
        int[][] matrix1 = {{3, 4, 6}, {2, 7, 6}};
        System.out.println("Expected output: 3, Actual output: " + sol.solution(matrix1));

        int[][] matrix2 = {{3, 3, 5, 6}, {6, 7, 2, 2}, {5, 2, 3, 8}, {5, 9, 2, 3}, {1, 2, 3, 4}};
        System.out.println("Expected output: 8, Actual output: " + sol.solution(matrix2));
    }
}
