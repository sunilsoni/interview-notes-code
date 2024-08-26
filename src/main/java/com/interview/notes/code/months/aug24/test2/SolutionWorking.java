package com.interview.notes.code.months.aug24.test2;

/*
WORKING
 Task 2
You are given a matrix consisting of N rows and M columns. Each cell of the matrix contains one digit (0-9). Cells are adjacent if they share a common edge. It is possible to move from one cell to another directly only if they are adjacent.
Find the largest group of cells such that:
• you can get from any cell in the group to any other by moving only through cells that belong to the group;
• the difference between the largest and the smallest values of the cells in the group is at most 1.
Write a function:
class Solution { public int solution (int[lll A); }
that, given a matrix A of N rows and M columns containing integers from the range 0-9, returns the maximal size of the group, fulfilling the above criteria.
Examples:
 */

public class SolutionWorking {
    private int[][] matrix;
    private boolean[][] visited;
    private int rows, cols;
    private final int[] rowOffset = {-1, 1, 0, 0};
    private final int[] colOffset = {0, 0, -1, 1};

    public int solution(int[][] A) {
        if (A == null || A.length == 0 || A[0].length == 0) {
            return 0;
        }

        matrix = A;
        rows = A.length;
        cols = A[0].length;
        visited = new boolean[rows][cols];
        int maxGroupSize = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (!visited[i][j]) {
                    int[] minMax = {A[i][j], A[i][j]};
                    int groupSize = dfs(i, j, minMax);
                    maxGroupSize = Math.max(maxGroupSize, groupSize);
                }
            }
        }

        return maxGroupSize;
    }

    private int dfs(int x, int y, int[] minMax) {
        if (x < 0 || x >= rows || y < 0 || y >= cols || visited[x][y]) {
            return 0;
        }

        int currentValue = matrix[x][y];
        int newMin = Math.min(minMax[0], currentValue);
        int newMax = Math.max(minMax[1], currentValue);

        if (newMax - newMin > 1) {
            return 0;
        }

        visited[x][y] = true;
        minMax[0] = newMin;
        minMax[1] = newMax;

        int size = 1;

        for (int i = 0; i < 4; i++) {
            int newX = x + rowOffset[i];
            int newY = y + colOffset[i];
            size += dfs(newX, newY, minMax);
        }

        return size;
    }

    // Main method for testing
    public static void main(String[] args) {
        SolutionWorking solution = new SolutionWorking();
        
        // Test case 1
        int[][] A1 = {{3, 4, 6}, {2, 7, 6}};
        System.out.println("Test case 1 result: " + solution.solution(A1)); // Expected: 3

        // Test case 2
        int[][] A2 = {
            {3, 3, 5, 6},
            {6, 7, 2, 2},
            {5, 2, 3, 8},
            {5, 9, 2, 3},
            {1, 2, 3, 4}
        };
        System.out.println("Test case 2 result: " + solution.solution(A2)); // Expected: 8

        // Test case 3
        int[][] A3 = {{4, 4, 2, 4, 4, 4}};
        System.out.println("Test case 3 result: " + solution.solution(A3)); // Expected: 3

        // Test case 4
        int[][] A4 = {{0}, {3}, {5}};
        System.out.println("Test case 4 result: " + solution.solution(A4)); // Expected: 1
    }
}
