package com.interview.notes.code.year.y2025.jan24.paypal.test1;

/*

WORKING

I will process the text from the images and combine them properly. Let me extract and format the content.

Here is the properly formatted and cleaned version of the extracted text:

---

### Solitaire Tile Disappearance Game

While your players are waiting for a game, you've developed a solitaire game for the players to pass the time with.

The player is given an \(N \times M\) board of tiles from 0 to 9 like this:

```
8  8  8  8
1  1  1  8
2  1  7  1
```

#### Game Rules
- The player selects one of these tiles, and that tile will disappear.
- Any tiles with the same number that are connected **vertically** or **horizontally** (not diagonally) will also disappear.
- This chain reaction continues as long as adjacent tiles have the same number.

For example, if the `8` in the upper-left corner is selected, these five tiles disappear:

```
>B< >8< >8< >8<
 1   1   1  >8<
 2   1   7   1
```

If the `1` just below is selected, these four tiles disappear:

```
 8   8   8   8
>1< >1< >1<  8
 2  >1<  7   1
```

### Function Requirement
Write a function that, given a grid of tiles and a selected row and column of a tile, returns how many tiles will disappear.

### Example Grids

#### Grid 1:
```python
grid_1 = [
    [8, 8, 8, 8],
    [1, 1, 1, 8],
    [2, 1, 7, 1]
]
```
Test Cases:
```python
disappear(grid_1, 0, 0) => 5
disappear(grid_1, 1, 1) => 4
disappear(grid_1, 1, 0) => 4
```

---

### Additional Inputs

#### Grid 2:
```python
grid_2 = [
    [0, 3, 3, 3, 3, 3, 1],
    [0, 1, 1, 1, 1, 1, 3],
    [0, 2, 2, 0, 2, 1, 4],
    [0, 1, 2, 2, 2, 1, 3],
    [0, 1, 1, 1, 1, 1, 3],
    [0, 0, 0, 0, 0, 0, 0]
]
```

#### Grid 3:
```python
grid_3 = [[0]]
```

#### Grid 4:
```python
grid_4 = [
    [1, 1, 1],
    [1, 1, 1],
    [1, 1, 1]
]
```

#### Grid 5:
```python
grid_5 = [
    [2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2],
    [1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2],
    [1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2],
    [1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2],
    [1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2],
    [1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2],
    [1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2],
    [1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2],
    [1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2],
    [1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2],
    [1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2],
    [2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2]
]
```

---

### All Test Cases
```python
disappear(grid_1, 0, 0)  => 5
disappear(grid_1, 1, 1)  => 4
disappear(grid_1, 1, 0)  => 4
disappear(grid_2, 0, 0)  => 12
disappear(grid_2, 3, 0)  => 12
disappear(grid_2, 1, 1)  => 13
disappear(grid_2, 2, 2)  => 6
disappear(grid_2, 0, 3)  => 5
disappear(grid_3, 0, 0)  => 1
disappear(grid_4, 0, 0)  => 9
disappear(grid_5, 0, 11) => 23
```

---
**Definitions:**
- **N** - Width of the grid
- **M** - Height of the grid

---

This version ensures clarity and proper formatting of the game description, rules, grids, and test cases.
 */
public class TileGame {
    private static boolean[][] visited;
    private static int count;
    
    public static int disappear(int[][] grid, int row, int col) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        
        visited = new boolean[grid.length][grid[0].length];
        count = 0;
        
        // Get the target number and start DFS
        int targetNum = grid[row][col];
        dfs(grid, row, col, targetNum);
        
        return count;
    }
    
    private static void dfs(int[][] grid, int row, int col, int target) {
        // Check bounds and if already visited
        if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length 
            || visited[row][col] || grid[row][col] != target) {
            return;
        }
        
        // Mark as visited and increment count
        visited[row][col] = true;
        count++;
        
        // Check all four directions
        dfs(grid, row + 1, col, target); // down
        dfs(grid, row - 1, col, target); // up
        dfs(grid, row, col + 1, target); // right
        dfs(grid, row, col - 1, target); // left
    }
    
    public static void main(String[] args) {
        // Test cases
        int[][] grid1 = {
            {8, 8, 8, 8},
            {1, 1, 1, 8},
            {2, 1, 7, 1}
        };
        
        int[][] grid2 = {
            {0, 3, 3, 3, 3, 3, 1},
            {0, 1, 1, 1, 1, 1, 3},
            {0, 2, 2, 0, 2, 1, 4},
            {0, 1, 2, 2, 2, 1, 3},
            {0, 1, 1, 1, 1, 1, 3},
            {0, 0, 0, 0, 0, 0, 0}
        };
        
        // Run tests and verify results
        runTest("Test 1", grid1, 0, 0, 5);
        runTest("Test 2", grid1, 1, 1, 4);
        runTest("Test 3", grid1, 1, 0, 4);
        runTest("Test 4", grid2, 0, 0, 12);
        runTest("Test 5", grid2, 3, 0, 12);
        runTest("Test 6", grid2, 1, 1, 13);
    }
    
    private static void runTest(String testName, int[][] grid, int row, int col, int expected) {
        int result = disappear(grid, row, col);
        System.out.println(testName + ": " + 
            (result == expected ? "PASS" : "FAIL") + 
            " (Expected: " + expected + ", Got: " + result + ")");
    }
}
