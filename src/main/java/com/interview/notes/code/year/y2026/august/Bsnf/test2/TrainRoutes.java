package com.interview.notes.code.year.y2026.august.Bsnf.test2;

public class TrainRoutes {

    // Existing API - backward compatible.
    static int countWays(int[][] grid) {

        // Invalid or empty grid.
        if (grid == null || grid.length == 0 || grid[0].length == 0)
            return 0;

        // Default: start = (0,0), end = bottom-right.
        return countWays(
            grid,
            0, 0,
            grid.length - 1, grid[0].length - 1
        );
    }

    // New API - allows custom start and end positions.
    static int countWays(
        int[][] grid,
        int startRow, int startCol,
        int endRow, int endCol
    ) {

        // Invalid grid.
        if (grid == null || grid.length == 0 || grid[0].length == 0)
            return 0;

        // Invalid start/end positions.
        if (startRow < 0 || startCol < 0 ||
            endRow >= grid.length || endCol >= grid[0].length ||
            startRow > endRow || startCol > endCol)
            return 0;

        // Start or destination is blocked.
        if (grid[startRow][startCol] == 1 || grid[endRow][endCol] == 1)
            return 0;

        // Start recursion from destination.
        return ways(grid, startRow, startCol, endRow, endCol);
    }

    // Recursive helper.
    static int ways(
        int[][] grid,
        int startRow, int startCol,
        int row, int col
    ) {

        // We crossed the allowed starting boundary.
        if (row < startRow || col < startCol)
            return 0;

        // Maintenance zone.
        if (grid[row][col] == 1)
            return 0;

        // Reached requested starting position.
        if (row == startRow && col == startCol)
            return 1;

        // Ways = from top + from left.
        return ways(grid, startRow, startCol, row - 1, col)
             + ways(grid, startRow, startCol, row, col - 1);
    }

    public static void main(String[] args) {

        var grid = new int[][] {
            {0, 0, 0},
            {0, 1, 0},
            {0, 0, 0}
        };

        // Old API - still works.
        System.out.println(countWays(grid)); // 2

        // New API - custom start/end.
        System.out.println(countWays(grid, 0, 1, 2, 2)); // 1
    }
}