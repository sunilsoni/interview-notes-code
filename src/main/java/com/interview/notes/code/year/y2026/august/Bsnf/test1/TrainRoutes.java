package com.interview.notes.code.year.y2026.august.Bsnf.test1;

public class TrainRoutes {

    // Finds number of ways to reach cell (r, c).
    static int ways(int[][] grid, int r, int c) {

        // If row or column goes outside the grid, this path is invalid.
        if (r < 0 || c < 0)
            return 0;

        // If current cell is blocked, train cannot use this path.
        if (grid[r][c] == 1)
            return 0;

        // If we reached the starting cell, we found one valid path.
        if (r == 0 && c == 0)
            return 1;

        // Ways to current cell = ways from top + ways from left.
        return ways(grid, r - 1, c) + ways(grid, r, c - 1);
    }

    // Starts calculation from the bottom-right destination.
    static int ways(int[][] grid) {

        // Checks null or empty grid input.
        if (grid == null || grid.length == 0 || grid[0].length == 0)
            return 0;

        // Calls recursion from the destination cell.
        return ways(grid, grid.length - 1, grid[0].length - 1);
    }

    // Simple test method to print PASS or FAIL.
    static void test(int no, int[][] grid, int expected) {

        // Runs the solution.
        var actual = ways(grid);

        // Checks expected result against actual result.
        var result = actual == expected ? "PASS" : "FAIL";

        // Prints the test result.
        System.out.printf(
            "Test %d: %s | Expected: %d | Actual: %d%n",
            no, result, expected, actual
        );
    }

    // Main method used instead of JUnit.
    public static void main(String[] args) {

        // Normal grid with one blocked cell.
        var grid1 = new int[][] {
            {0, 0, 0},
            {0, 1, 0},
            {0, 0, 0}
        };

        // Empty 2 x 2 grid.
        var grid2 = new int[][] {
            {0, 0},
            {0, 0}
        };

        // No possible route.
        var grid3 = new int[][] {
            {0, 1},
            {1, 0}
        };

        // Only one cell.
        var grid4 = new int[][] {
            {0}
        };

        // Starting cell is blocked.
        var grid5 = new int[][] {
            {1}
        };

        // Single row.
        var grid6 = new int[][] {
            {0, 0, 0, 0}
        };

        // Single column.
        var grid7 = new int[][] {
            {0},
            {0},
            {0},
            {0}
        };

        // Larger simple grid for brute-force testing.
        var grid8 = new int[10][10];

        // Runs all test cases.
        test(1, grid1, 2);
        test(2, grid2, 2);
        test(3, grid3, 0);
        test(4, grid4, 1);
        test(5, grid5, 0);
        test(6, grid6, 1);
        test(7, grid7, 1);

        // 10 x 10 empty grid has 48,620 possible routes.
        test(8, grid8, 48620);
    }
}