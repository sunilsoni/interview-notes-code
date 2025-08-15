package com.interview.notes.code.year.y2025.august.oracle.test1;

import java.util.*;                           // Import utilities for Stack, Arrays, List, etc.
import java.util.stream.*;                    // Import Java 8 Streams for generating and formatting test data.
/*

 */
public class NumberOfIslands {                // Define a public class to hold solution and test main.

    /*
     * Complete the 'numIslands' function below.
     *
     * The function is expected to return an INTEGER.
     * We mutate the grid to mark visited cells by turning '1' -> '0' to avoid extra visited[][] memory.
     */
    public static int numIslands(char[][] grid) { // Method to count islands in the given grid.
        if (grid == null || grid.length == 0) {   // Guard: if grid is null or has no rows, answer is 0.
            return 0;                             // Return 0 since there cannot be any land.
        }

        int rows = grid.length;                   // Store number of rows for repeated use.
        int cols = grid[0].length;                // Store number of columns; assume rectangular grid per constraints.
        int islands = 0;                          // Counter for number of islands found.

        // Directions array to move up, down, left, right from a cell (r,c).
        int[][] DIRS = new int[][]{               // 4-directional movement vectors.
                {-1, 0},                          // Up: row-1, same column
                { 1, 0},                          // Down: row+1, same column
                { 0,-1},                          // Left: same row, col-1
                { 0, 1}                           // Right: same row, col+1
        };

        // Iterate over every cell in the grid.
        for (int r = 0; r < rows; r++) {          // Loop through all rows.
            for (int c = 0; c < cols; c++) {      // Loop through all columns.
                if (grid[r][c] == '1') {          // If current cell is land and not yet visited...
                    islands++;                    // We discovered a new island; increment count.

                    // Iterative DFS using a stack to flood-fill this island.
                    Deque<int[]> stack = new ArrayDeque<>(); // Use ArrayDeque as a stack for performance.
                    stack.push(new int[]{r, c}); // Push the starting land cell.

                    while (!stack.isEmpty()) {    // Process until all connected land cells are visited.
                        int[] cell = stack.pop(); // Pop one cell to process.
                        int cr = cell[0];         // Current row index.
                        int cc = cell[1];         // Current column index.

                        if (cr < 0 || cr >= rows || cc < 0 || cc >= cols) {
                            continue;             // Skip if out of bounds (safety).
                        }
                        if (grid[cr][cc] != '1') {
                            continue;             // Skip if already water/visited.
                        }

                        grid[cr][cc] = '0';       // Mark as visited by flipping land to water.

                        // Push all 4 neighbors to explore the full connected component.
                        for (int[] d : DIRS) {    // For each direction (up, down, left, right)...
                            int nr = cr + d[0];   // Neighbor row.
                            int nc = cc + d[1];   // Neighbor column.
                            // Only push if within bounds and looks like land to explore soon.
                            if (nr >= 0 && nr < rows && nc >= 0 && nc < cols && grid[nr][nc] == '1') {
                                stack.push(new int[]{nr, nc}); // Defer visiting until popped.
                            }
                        }
                    }
                }
            }
        }

        return islands;                            // After scanning all cells, return total island count.
    }

    // ---- Helper functions for testing & utilities ----

    // Deep copy a 2D char array so tests can reuse original inputs (since numIslands mutates its grid).
    private static char[][] copyGrid(char[][] grid) {
        if (grid == null) return null;            // Null guard.
        char[][] out = new char[grid.length][];   // Create new outer array with same number of rows.
        for (int i = 0; i < grid.length; i++) {   // Loop rows.
            out[i] = Arrays.copyOf(grid[i], grid[i].length); // Copy each row to avoid shared references.
        }
        return out;                                // Return deep-copied grid.
    }

    // Parse a String[][] representation into char[][] quickly for tests.
    private static char[][] toCharGrid(String[][] input) {
        return Arrays.stream(input)                           // Stream each String[] row.
                .map(row -> {                                 // Map each row to a char[].
                    char[] ca = new char[row.length];         // Prepare char[] of same length.
                    for (int i = 0; i < row.length; i++) {    // Iterate columns.
                        ca[i] = row[i].charAt(0);             // Take first character '1' or '0'.
                    }
                    return ca;                                // Return char[] row.
                })
                .toArray(char[][]::new);                      // Collect into char[][].
    }

    // Simple pretty printer for small grids (used in debug if needed).
    private static String gridToString(char[][] g) {
        return IntStream.range(0, g.length)                   // For each row index...
                .mapToObj(r -> IntStream.range(0, g[r].length)// Map row to string of chars...
                        .mapToObj(c -> String.valueOf(g[r][c]))// Each char -> String
                        .collect(Collectors.joining(" ")))    // Join columns with spaces.
                .collect(Collectors.joining("\n"));           // Join rows with newlines.
    }

    // A minimal assertion helper: compares expected vs actual, prints PASS/FAIL message.
    private static void assertEquals(String testName, int expected, int actual) {
        if (expected == actual) {                             // If values match...
            System.out.println(testName + " -> PASS");        // Print PASS.
        } else {                                              // Otherwise...
            System.out.println(testName + " -> FAIL. Expected: " + expected + ", Got: " + actual);
        }
    }

    // Build a large random grid with given dimensions and landProbability; returns char[][].
    private static char[][] buildRandomGrid(int rows, int cols, double landProbability) {
        Random rnd = new Random(42);                          // Fixed seed for reproducibility.
        char[][] g = new char[rows][cols];                    // Allocate grid.
        for (int r = 0; r < rows; r++) {                      // For each row...
            for (int c = 0; c < cols; c++) {                  // For each column...
                g[r][c] = (rnd.nextDouble() < landProbability) ? '1' : '0'; // Random land/water.
            }
        }
        return g;                                             // Return generated grid.
    }

    // ---- Main method with PASS/FAIL tests, including large data case ----
    public static void main(String[] args) {
        // Example 1:
        String[][] ex1 = {
                {"1","1","1","1","0"},
                {"1","1","0","1","0"},
                {"1","1","0","0","0"},
                {"0","0","0","0","0"}
        };
        char[][] g1 = toCharGrid(ex1);                        // Convert to char[][] for the solver.
        int ans1 = numIslands(copyGrid(g1));                  // Use a copy to avoid mutating g1 for reuse.
        assertEquals("Example 1", 1, ans1);                   // Expected 1.

        // Example 2:
        String[][] ex2 = {
                {"1","1","0","0","0"},
                {"1","1","0","0","0"},
                {"0","0","1","0","0"},
                {"0","0","0","1","1"}
        };
        char[][] g2 = toCharGrid(ex2);                        // Convert to char[][] for the solver.
        int ans2 = numIslands(copyGrid(g2));                  // Solve on a copy.
        assertEquals("Example 2", 3, ans2);                   // Expected 3.

        // Edge Case: Single cell water
        char[][] g3 = new char[][]{{'0'}};                    // 1x1 water.
        assertEquals("Single cell water", 0, numIslands(copyGrid(g3))); // No island.

        // Edge Case: Single cell land
        char[][] g4 = new char[][]{{'1'}};                    // 1x1 land.
        assertEquals("Single cell land", 1, numIslands(copyGrid(g4)));  // One island.

        // Edge Case: All water
        char[][] g5 = new char[][]{
                {'0','0','0'},
                {'0','0','0'}
        };
        assertEquals("All water", 0, numIslands(copyGrid(g5)));         // Zero islands.

        // Edge Case: All land (single big island)
        char[][] g6 = new char[][]{
                {'1','1','1'},
                {'1','1','1'}
        };
        assertEquals("All land", 1, numIslands(copyGrid(g6)));          // One island.

        // Checkerboard (no diagonal connections -> many small islands)
        char[][] g7 = new char[][]{
                {'1','0','1','0'},
                {'0','1','0','1'},
                {'1','0','1','0'},
                {'0','1','0','1'}
        };
        assertEquals("Checkerboard 4x4", 8, numIslands(copyGrid(g7)));  // Each '1' is isolated by 4-neighbor rule.

        // Large Data Test: 300x300 random grid - performance and sanity check
        char[][] large = buildRandomGrid(300, 300, 0.45);     // 45% land density.
        long t0 = System.nanoTime();                          // Start timer.
        int big = numIslands(copyGrid(large));                // Compute on a copy.
        long t1 = System.nanoTime();                          // End timer.
        long ms = (t1 - t0) / 1_000_000;                      // Duration in milliseconds.

        // We don't know the exact expected value; we just sanity-check and report execution time.
        if (big >= 0) {                                       // Sanity condition: count must be non-negative.
            System.out.println("Large 300x300 -> PASS (islands=" + big + ", time=" + ms + " ms)");
        } else {
            System.out.println("Large 300x300 -> FAIL (negative count)"); // Defensive.
        }
    }
}