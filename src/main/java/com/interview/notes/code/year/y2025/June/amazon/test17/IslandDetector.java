package com.interview.notes.code.year.y2025.June.amazon.test17;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

/*

---

### **Input Matrix**

(L = Land, W = Water)

```
   0 1 2 3 4 5 6
0: L L L L L W W
1: W W W W W W W
2: W L L L L W W
3: W W W L L W W
4: W L L W L L W
5: W W W L W W W
```

---

### **Question: Island Detection**

You are given an `n x m` matrix where each cell contains either `'L'` (land) or `'W'` (water). An **island** is defined as **a group of connected 'L' cells**, connected **horizontally or vertically** (not diagonally), and **completely surrounded by water**.

Write a program that does the following:

1. **Identifies all distinct islands**.
2. Returns:

   * The **count** of such islands.
   * The **list of coordinates** belonging to each island (as a list of lists of `(row, col)` tuples).

**Example Based on the Above Matrix**:

Output should be:

```
Island Count: 2
Islands:
[
  [(2,1), (2,2), (2,3), (2,4), (3,3), (3,4)],   # First island
  [(4,1), (4,2)]                                # Second island
]
```

(Note: These groups are isolated from other land cells and surrounded by 'W')

---


 */
// Class Declaration
public class IslandDetector {
    // Main method to find enclosed islands
    public static List<List<int[]>> findIslands(char[][] grid) {
        // Get dimensions of the grid
        int n = grid.length;                             // Number of rows in the grid
        int m = grid[0].length;                          // Number of columns in the grid
        
        // Create visited array to track explored cells
        // We use boolean[][] instead of HashSet for O(1) lookup and less memory overhead
        boolean[][] visited = new boolean[n][m];         
        
        // ArrayList to store all valid enclosed islands
        // Each island is represented as a List<int[]> containing coordinates
        List<List<int[]>> islands = new ArrayList<>();

        // Iterate through each cell in the grid
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // Check if current cell is unvisited land
                // 'L' represents land, and we only process unvisited cells
                if (grid[i][j] == 'L' && !visited[i][j]) {
                    // Create new list to store coordinates of current island
                    List<int[]> island = new ArrayList<>();
                    
                    // Use boolean array of size 1 to track if island touches border
                    // We use array instead of primitive to allow modification in recursive calls
                    boolean[] touchesBorder = new boolean[1];
                    
                    // Start DFS from current cell
                    dfs(grid, visited, i, j, island, touchesBorder);
                    
                    // Only add island if it doesn't touch any border
                    if (!touchesBorder[0]) {
                        islands.add(island);
                    }
                }
            }
        }
        return islands;
    }

    // DFS helper method
    private static void dfs(char[][] grid, boolean[][] visited,
                          int i, int j, List<int[]> island, boolean[] touchesBorder) {
        int n = grid.length, m = grid[0].length;
        
        // Base cases to stop DFS:
        // 1. Out of grid bounds
        // 2. Cell is water ('W')
        // 3. Cell already visited
        if (i < 0 || i >= n || j < 0 || j >= m
            || grid[i][j] == 'W' || visited[i][j]) {
            return;
        }
        
        // Mark current cell as visited
        visited[i][j] = true;
        
        // Check if current cell is on border
        // If yes, mark entire island as touching border
        if (i == 0 || i == n - 1 || j == 0 || j == m - 1) {
            touchesBorder[0] = true;
        }
        
        // Add current cell coordinates to island
        island.add(new int[]{i, j});
        
        // Recursively explore all 4 adjacent cells (up, down, left, right)
        dfs(grid, visited, i - 1, j, island, touchesBorder); // up
        dfs(grid, visited, i + 1, j, island, touchesBorder); // down
        dfs(grid, visited, i, j - 1, island, touchesBorder); // left
        dfs(grid, visited, i, j + 1, island, touchesBorder); // right
    }

    // Test method
    public static void main(String[] args) {
        // Create test cases with different scenarios
        List<char[][]> testGrids = Arrays.asList(
            // Test case 1: Grid with 2 enclosed islands
            new char[][] {...},
            // Test case 2: Grid with no enclosed islands (all cells touch border)
            new char[][] {...},
            // Test case 3: Grid with all water cells
            new char[][] {...}
        );
        
        // Expected results for each test case
        List<String> expected = Arrays.asList("Count=2", "Count=0", "Count=0");

        // Run tests using Java 8 streams
        IntStream.range(0, testGrids.size()).forEach(i -> {
            List<List<int[]>> isl = findIslands(testGrids.get(i));
            String actual = "Count=" + isl.size();
            String result = actual.equals(expected.get(i)) ? "PASS" : "FAIL";
            System.out.println("Test " + (i+1) + ": expected " 
                + expected.get(i) + ", got " + actual + " => " + result);
        });

        // Performance test with large grid
        // Creates 1000x1000 grid with random land/water cells
        int size = 1000;
        char[][] large = new char[size][size];
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                large[r][c] = Math.random() < 0.5 ? 'L' : 'W';
            }
        }
        
        // Measure execution time
        long start = System.currentTimeMillis();
        List<List<int[]>> bigIslands = findIslands(large);
        long ms = System.currentTimeMillis() - start;
        System.out.println("Large grid " + size + "×" + size 
            + " → islands=" + bigIslands.size() 
            + ", time=" + ms + "ms");
    }
}
