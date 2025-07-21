package com.interview.notes.code.year.y2025.july.oracle.test1;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class NumberOfIslands {

    // Directions: up, down, left, right
    private static final int[][] DIRS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    /**
     * Counts the number of islands in a binary grid.
     *
     * @param grid 2D int array of 0s and 1s
     * @return number of islands
     */
    public static int countIslands(int[][] grid) {
        // If grid is empty, no islands
        if (grid == null || grid.length == 0) return 0;

        int rows = grid.length;             // number of rows
        int cols = grid[0].length;          // number of columns
        boolean[][] visited = new boolean[rows][cols];  // track visited cells
        int count = 0;                      // island counter

        // scan every cell
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                // if it's land and not yet visited, it's a new island
                if (grid[r][c] == 1 && !visited[r][c]) {
                    count++;                 // found one more island
                    dfsMark(grid, visited, r, c, rows, cols);
                }
            }
        }
        return count;                       // total islands found
    }

    /**
     * DFS to mark all connected land cells from (r,c).
     */
    private static void dfsMark(int[][] grid, boolean[][] visited,
                                int r, int c, int rows, int cols) {
        // boundary or water or already visited → stop
        if (r < 0 || r >= rows || c < 0 || c >= cols
                || grid[r][c] == 0 || visited[r][c]) {
            return;
        }
        visited[r][c] = true;               // mark current as visited

        // explore all four directions
        for (int[] d : DIRS) {
            dfsMark(grid, visited, r + d[0], c + d[1], rows, cols);
        }
    }

    /**
     * Main method: runs a suite of tests and prints PASS/FAIL.
     */
    public static void main(String[] args) {
        // Prepare test cases (including large empty grid)
        List<TestCase> tests = Arrays.asList(
                new TestCase("Example 1",
                        new int[][]{
                                {1, 1, 0, 0},
                                {1, 0, 0, 1},
                                {1, 0, 0, 1}
                        }, 2),
                new TestCase("Example 2",
                        new int[][]{
                                {0, 0, 0, 1},
                                {1, 0, 0, 0},
                                {1, 0, 1, 1}
                        }, 3),
                new TestCase("All Water",
                        new int[][]{
                                {0, 0, 0},
                                {0, 0, 0}
                        }, 0),
                new TestCase("All Land",
                        new int[][]{
                                {1, 1},
                                {1, 1}
                        }, 1),
                new TestCase("Empty Grid",
                        new int[][]{}, 0),
                new TestCase("Large Water Grid",
                        new int[1000][1000], 0)
        );

        // Run each test via Stream API
        IntStream.range(0, tests.size()).forEach(i -> {
            TestCase tc = tests.get(i);
            int result = countIslands(tc.grid);
            if (result == tc.expected) {
                System.out.println(tc.name + ": PASS");
            } else {
                System.out.println(tc.name + ": FAIL (expected="
                        + tc.expected + ", got=" + result + ")");
            }
        });
    }

    /**
     * Simple holder for test cases.
     */
    private static class TestCase {
        int[][] grid;
        int expected;
        String name;

        TestCase(String name, int[][] grid, int expected) {
            this.name = name;
            this.grid = grid;
            this.expected = expected;
        }
    }
}