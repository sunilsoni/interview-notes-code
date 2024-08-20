package com.interview.notes.code.months.aug24.test21;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class TileDisappearance {
    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // Up, Down, Left, Right

    public static int disappear(int[][] grid, int row, int col) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int M = grid.length;
        int N = grid[0].length;
        int targetValue = grid[row][col];

        if (targetValue == -1) {
            return 0;
        }

        int count = 0;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{row, col});

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int r = current[0], c = current[1];

            if (r < 0 || r >= M || c < 0 || c >= N || grid[r][c] != targetValue) {
                continue;
            }

            grid[r][c] = -1; // Mark as visited
            count++;

            for (int[] dir : DIRECTIONS) {
                queue.offer(new int[]{r + dir[0], c + dir[1]});
            }
        }

        return count;
    }

    public static void main(String[] args) {
        int[][] grid1 = {
                {8, 8, 8, 8},
                {1, 1, 1, 8},
                {2, 1, 7, 1}
        };

        System.out.println(disappear(grid1, 0, 0));  // 5
        System.out.println(disappear(grid1, 1, 1));  // 4
        System.out.println(disappear(grid1, 1, 0));  // 1

        // Test with a large grid
        int size = 1000;
        int[][] largeGrid = new int[size][size];
        for (int[] row : largeGrid) {
            Arrays.fill(row, 1);
        }
        System.out.println(disappear(largeGrid, 0, 0));  // Should print 1000000
    }
}
