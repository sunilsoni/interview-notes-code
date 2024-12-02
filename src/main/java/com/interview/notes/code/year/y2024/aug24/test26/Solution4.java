package com.interview.notes.code.year.y2024.aug24.test26;

public class Solution4 {
    private static final int[] DX = {-1, 0, 1, 0};
    private static final int[] DY = {0, 1, 0, -1};
    private int[][] matrix;
    private boolean[][] visited;
    private int rows;
    private int cols;

    public static void main(String[] args) {
        Solution4 sol = new Solution4();

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

    public int solution(int[][] A) {
        this.matrix = A;
        this.rows = A.length;
        this.cols = A[0].length;

        visited = new boolean[rows][cols];
        int maxSize = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (!visited[i][j]) {
                    maxSize = Math.max(maxSize, dfs(i, j, A[i][j]));
                }
            }
        }

        return maxSize;
    }

    private int dfs(int x, int y, int cellValue) {
        visited[x][y] = true;
        int area = 1;

        for (int i = 0; i < 4; i++) {
            int nx = x + DX[i];
            int ny = y + DY[i];

            if (nx >= 0 && ny >= 0 && nx < rows && ny < cols && !visited[nx][ny]) {
                int nextValue = matrix[nx][ny];
                if (Math.abs(nextValue - cellValue) <= 1) {
                    area += dfs(nx, ny, cellValue);
                }
            }
        }

        return area;
    }
}