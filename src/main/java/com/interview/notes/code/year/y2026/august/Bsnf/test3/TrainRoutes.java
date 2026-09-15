package com.interview.notes.code.year.y2026.august.Bsnf.test3;

public class TrainRoutes {

    // Old API - keeps backward compatibility.
    static int countWays(int[][] grid) {

        // Invalid or empty grid.
        if (isInvalidGrid(grid))
            return 0;

        // Default start position is top-left.
        var start = new Position(0, 0);

        // Default end position is bottom-right.
        var end = new Position(grid.length - 1, grid[0].length - 1);

        // Reuse the new API.
        return countWays(grid, start, end);
    }

    // New API - supports custom start and end positions.
    static int countWays(int[][] grid, Position start, Position end) {

        // Validate grid first.
        if (isInvalidGrid(grid))
            return 0;

        // Validate start and end positions.
        if (!isValidPosition(grid, start) || !isValidPosition(grid, end))
            return 0;

        // Since movement is only right or down, end cannot be before start.
        if (end.row() < start.row() || end.col() < start.col())
            return 0;

        // Start or end cannot be a blocked maintenance cell.
        if (isBlocked(grid, start) || isBlocked(grid, end))
            return 0;

        // Start recursive calculation from the end position.
        return ways(grid, start, end);
    }

    // Recursive method that counts all valid ways.
    static int ways(int[][] grid, Position start, Position current) {

        // If we move before the start boundary, this path is invalid.
        if (current.row() < start.row() || current.col() < start.col())
            return 0;

        // If current cell is blocked, this path is invalid.
        if (isBlocked(grid, current))
            return 0;

        // If we reached the start position, one valid path is found.
        if (current.equals(start))
            return 1;

        // Move one cell up.
        var top = new Position(current.row() - 1, current.col());

        // Move one cell left.
        var left = new Position(current.row(), current.col() - 1);

        // Ways to current cell = ways from top + ways from left.
        return ways(grid, start, top) + ways(grid, start, left);
    }

    // Checks whether grid input is valid.
    static boolean isInvalidGrid(int[][] grid) {

        // Null, no rows, or no columns means invalid input.
        return grid == null || grid.length == 0 || grid[0].length == 0;
    }

    // Checks whether a position exists inside the grid.
    static boolean isValidPosition(int[][] grid, Position position) {

        // Null position is invalid.
        if (position == null)
            return false;

        // Check row and column boundaries.
        return position.row() >= 0 &&
               position.col() >= 0 &&
               position.row() < grid.length &&
               position.col() < grid[0].length;
    }

    // Checks whether a cell is blocked.
    static boolean isBlocked(int[][] grid, Position position) {

        // Value 1 means maintenance zone.
        return grid[position.row()][position.col()] == 1;
    }

    // Simple test method without JUnit.
    static void test(
        int number,
        int[][] grid,
        Position start,
        Position end,
        int expected
    ) {

        // Use old API when start and end are not supplied.
        var actual = start == null && end == null
            ? countWays(grid)
            : countWays(grid, start, end);

        // Compare expected and actual results.
        var result = actual == expected ? "PASS" : "FAIL";

        // Print test result.
        System.out.printf(
            "Test %d: %s | Expected: %d | Actual: %d%n",
            number, result, expected, actual
        );
    }

    // Main method for testing.
    public static void main(String[] args) {

        // Common sample grid.
        var grid = new int[][] {
            {0, 0, 0},
            {0, 1, 0},
            {0, 0, 0}
        };

        // Test old API: start = (0,0), end = bottom-right.
        test(1, grid, null, null, 2);

        // Test custom start and end.
        test(
            2,
            grid,
            new Position(0, 1),
            new Position(2, 2),
            1
        );

        // Test simple 2 x 2 grid.
        test(
            3,
            new int[][] {
                {0, 0},
                {0, 0}
            },
            null,
            null,
            2
        );

        // Test blocked destination.
        test(
            4,
            new int[][] {
                {0, 0},
                {0, 1}
            },
            null,
            null,
            0
        );

        // Test same start and end.
        test(
            5,
            grid,
            new Position(0, 0),
            new Position(0, 0),
            1
        );

        // Test invalid direction because end is before start.
        test(
            6,
            grid,
            new Position(2, 2),
            new Position(0, 0),
            0
        );

        // Test single row.
        test(
            7,
            new int[][] {
                {0, 0, 0, 0}
            },
            null,
            null,
            1
        );
    }

    // Represents one position in the grid.
    record Position(int row, int col) {}
}