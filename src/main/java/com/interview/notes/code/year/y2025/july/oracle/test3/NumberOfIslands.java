package com.interview.notes.code.year.y2025.july.oracle.test3;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

/*

## 🏝️ Problem: Number of Islands

### 🧠 Objective:

Given a 2D binary matrix `grid` of `0`s (water) and `1`s (land), return the number of islands.

An **island** is surrounded by water and is formed by connecting adjacent lands **horizontally or vertically**. You may assume all four edges of the grid are surrounded by water.

---

### 📥 Input:

```java
[
  [1,1,0,0],
  [1,0,0,1],
  [1,0,0,1]
]
```

### 📤 Output:

```
2
```

---

### 📥 Input:

```java
[
  [0,0,0,1],
  [1,0,0,0],
  [1,0,1,1]
]
```

### 📤 Output:

```
3
```

---


 */
public class NumberOfIslands {
    // Define all 8 possible movement directions as coordinate changes
    // Each array element represents [rowChange, columnChange]
    private static final int[][] DIRS = {
            {-1, 0}, // up: decrease row by 1
            {1, 0}, // down: increase row by 1
            {0, -1}, // left: decrease column by 1
            {0, 1}, // right: increase column by 1
            {-1, -1}, // up-left diagonal
            {-1, 1}, // up-right diagonal
            {1, -1}, // down-left diagonal
            {1, 1}  // down-right diagonal
    };

    /**
     * Main method to count number of islands
     * Time Complexity: O(rows × cols) - visits each cell once
     * Space Complexity: O(rows × cols) - for visited array + recursion stack
     */
    public static int countIslands(int[][] grid) {
        // Handle edge cases
        if (grid == null || grid.length == 0) return 0;

        int rows = grid.length;
        int cols = grid[0].length;
        // visited array to keep track of explored cells
        boolean[][] visited = new boolean[rows][cols];
        int count = 0;  // counter for number of islands

        // Iterate through each cell in the grid
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                // When we find unvisited land, we've found a new island
                if (grid[r][c] == 1 && !visited[r][c]) {
                    count++;  // increment island counter
                    // Use DFS to mark all connected land cells
                    dfsMark(grid, visited, r, c, rows, cols);
                }
            }
        }
        return count;
    }

    /**
     * DFS helper method to mark all connected land cells
     * Parameters:
     *
     * @param grid    - original grid
     * @param visited - tracking visited cells
     * @param r       - current row
     * @param c       - current column
     * @param rows    - total rows
     * @param cols    - total columns
     */
    private static void dfsMark(int[][] grid, boolean[][] visited,
                                int r, int c, int rows, int cols) {
        // Base cases for DFS recursion:
        // 1. Out of bounds
        // 2. Cell is water (0)
        // 3. Already visited
        if (r < 0 || r >= rows || c < 0 || c >= cols
                || grid[r][c] == 0 || visited[r][c]) {
            return;
        }

        visited[r][c] = true;  // Mark current cell as visited

        // Recursively explore all 8 directions
        for (int[] dir : DIRS) {
            dfsMark(grid, visited,
                    r + dir[0],  // new row = current row + direction change
                    c + dir[1],  // new col = current col + direction change
                    rows, cols);
        }
    }

    /**
     * Main method with test cases
     * Includes various scenarios:
     * - Regular cases with/without diagonal connections
     * - Edge cases (empty grid, all water, all land)
     * - Performance test (large grid)
     */
    public static void main(String[] args) {
        // List of test cases covering various scenarios
        /* test cases */
        List<TestCase> tests = List.of();

        // Stream through test cases and run each one
        IntStream.range(0, tests.size()).forEach(i -> {
            TestCase tc = tests.get(i);
            int result = countIslands(tc.grid);
            String status = result == tc.expected ? "PASS" :
                    "FAIL (exp=" + tc.expected + ", got=" + result + ")";
            System.out.println(tc.name + ": " + status);
        });
    }

    // Test case class to organize test data
    private static class TestCase {
        String name;      // test case name
        int[][] grid;     // input grid
        int expected;     // expected result

        TestCase(String name, int[][] grid, int expected) {
            this.name = name;
            this.grid = grid;
            this.expected = expected;
        }
    }
}
