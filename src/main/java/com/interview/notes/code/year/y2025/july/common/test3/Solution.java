package com.interview.notes.code.year.y2025.july.common.test3;
/*
# 💎 Problem Name: ***Optimal Path***

***PLEASE DO NOT REMOVE THIS LINE.***

### 📝 Instructions to Candidate:

1. You are an avid rock collector who lives in southern California. Some rare and desirable rocks just became available in New York, so you are planning a cross-country road trip. There are several other rare rocks that you could pick up along the way.

2. You have been given a grid filled with numbers, representing the number of rare rocks available in various cities across the country.
   Your objective is to **find the optimal path from So\_Cal to New\_York that would allow you to accumulate the most rocks along the way**.

3. **Constraints**:

   * You can only travel **north (up)** or **east (right)**.

4. **Example Input**:

```
Grid:
[
  [0, 0, 0, 0, 5],  // New York (finish)
  [0, 1, 1, 1, 0],
  [2, 0, 0, 0, 0]   // So_Cal (start)
]
```

📍Start = (2,0), End = (0,4)

➡️ Path: (2,0) → (1,0) → (1,1) → (1,2) → (0,2) → (0,3) → (0,4)
🪨 Total rocks collected = **2 + 0 + 1 + 1 + 1 + 0 + 5 = 10**

---


 */
public class Solution {

    /**
     * Compute the maximum rocks collectible from SoCal (bottom-left) to NY (top-right).
     */
    public static int optimalPath(int[][] grid) {
        // If grid is null or has no rows or no columns, nothing to collect
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        // Number of rows in the grid
        int rows = grid.length;
        // Number of columns in the grid
        int cols = grid[0].length;
        // dp[c] will hold the max rocks collectible when arriving at column c of the current row
        int[] dp = new int[cols];

        // Initialize dp for the bottom row (starting row)
        // Start position is bottom-left cell
        dp[0] = grid[rows - 1][0];
        // Fill in the bottom row: can only move right, so accumulate along the row
        for (int c = 1; c < cols; c++) {
            dp[c] = dp[c - 1] + grid[rows - 1][c];
        }

        // Process each row above the bottom, moving upward
        for (int r = rows - 2; r >= 0; r--) {
            // For the first column in this row, can only come from below
            dp[0] = dp[0] + grid[r][0];
            // For each remaining column, choose best of from-below or from-left
            for (int c = 1; c < cols; c++) {
                // If coming from below yields more rocks
                if (dp[c] > dp[c - 1]) {
                    // take value from below plus current cell
                    dp[c] = dp[c] + grid[r][c];
                } else {
                    // otherwise take value from left plus current cell
                    dp[c] = dp[c - 1] + grid[r][c];
                }
            }
        }

        // The top-right cell's dp value is our answer
        return dp[cols - 1];
    }

    /**
     * Simple main method to run tests and print PASS/FAIL.
     */
    public static void main(String[] args) {
        // Base test from problem statement
        runTest("Base Test",
            new int[][] {
                {0, 0, 0, 0, 5},
                {0, 1, 1, 1, 0},
                {2, 0, 0, 0, 0}
            },
            10
        );

        // Test with empty grid
        runTest("Empty Grid", new int[][] {}, 0);

        // Test with single-cell grid
        runTest("Single Cell", new int[][] { {7} }, 7);

        // Performance test: large 1000×1000 grid of all 1s
        int size = 1000;                                   // grid dimension
        int[][] largeGrid = new int[size][size];           // allocate grid
        for (int i = 0; i < size; i++) {                   // for each row
            for (int j = 0; j < size; j++) {               // for each column
                largeGrid[i][j] = 1;                       // set one rock per cell
            }
        }
        // Expected rocks = number of steps: size + size - 1
        runTest("Large Grid", largeGrid, size + size - 1);
    }

    /**
     * Helper to execute one test and print the result.
     */
    private static void runTest(String name, int[][] grid, int expected) {
        // Compute result
        int result = optimalPath(grid);
        // Compare with expected and print PASS or FAIL with details
        if (result == expected) {
            System.out.println(name + ": PASS");
        } else {
            System.out.println(name + ": FAIL (expected " 
                + expected + " but got " + result + ")");
        }
    }
}