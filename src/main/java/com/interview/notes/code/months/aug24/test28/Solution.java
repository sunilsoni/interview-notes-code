package com.interview.notes.code.months.aug24.test28;

public class Solution {
    private int[][] matrix;
    private boolean[][] visited;
    private int rows, cols;
    private int[] dx = {-1, 1, 0, 0};
    private int[] dy = {0, 0, -1, 1};

    public int solution(int[][] A) {
        matrix = A;
        rows = A.length;
        cols = A[0].length;
        visited = new boolean[rows][cols];
        int maxGroupSize = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (!visited[i][j]) {
                    int groupSize = dfs(i, j, matrix[i][j], matrix[i][j]);
                    maxGroupSize = Math.max(maxGroupSize, groupSize);
                }
            }
        }

        return maxGroupSize;
    }

    private int dfs(int x, int y, int minVal, int maxVal) {
        if (x < 0 || x >= rows || y < 0 || y >= cols || visited[x][y]) {
            return 0;
        }

        int currentVal = matrix[x][y];
        if (currentVal < minVal - 1 || currentVal > maxVal + 1) {
            return 0;
        }

        visited[x][y] = true;
        int size = 1;

        for (int i = 0; i < 4; i++) {
            int newX = x + dx[i];
            int newY = y + dy[i];
            size += dfs(newX, newY, Math.min(minVal, currentVal), Math.max(maxVal, currentVal));
        }

        return size;
    }

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
        //Test case 2 result: 10 but expected is 8

        // Test case 3
        int[][] A3 = {{4, 4, 2, 4, 4, 4}};
        System.out.println("Test case 3 result: " + solution.solution(A3));

        // Test case 4
        int[][] A4 = {{0}, {3}, {5}};
        System.out.println("Test case 4 result: " + solution.solution(A4));
    }
}
