package com.interview.notes.code.year.y2025.feb.Walmart.test4;

/*

### Sub-Sudoku Validation

You are working on a logic game made up of a series of puzzles. The first type of puzzle you settle on is **"sub-Sudoku"**, a game where the player has to position the numbers **1..N** on an **NxN** matrix.

Your job is to write a function that, given an **NxN** matrix, returns **true** if every row and column contains the numbers **1..N**.

The UI for the game does not do any validation on the numbers the player enters, so the matrix can contain any signed integer.

---

### Grids and Validation Results

#### ✅ **Valid Sudoku Grids**
1. **grid1**
   ```python
   [[2, 3, 1],
    [1, 2, 3],
    [3, 1, 2]]
   ```
   **Result:** `True` (A grid of size 3 where every row and column contains the numbers 1,2,3)

2. **grid4**
   ```python
   [[1]]
   ```
   **Result:** `True` (A grid of size one where it contains only 1 as the single value)

---

#### ❌ **Invalid Sudoku Grids**
3. **grid2**
   ```python
   [[1, 2, 3],
    [3, 2, 1],
    [3, 1, 2]]
   ```
   **Result:** `False` (First column is missing value 2, second column is missing value 3)

4. **grid3**
   ```python
   [[2, 2, 3],
    [3, 1, 2],
    [2, 3, 1]]
   ```
   **Result:** `False` (First row is missing value 1, first column is also incorrect)

5. **grid5**
   ```python
   [[-1, -2, -3],
    [-2, -3, -1],
    [-3, -1, -2]]
   ```
   **Result:** `False` (Rows and columns should contain positive values 1,2,3, not -1, -2, -3)

6. **grid6**
   ```python
   [[1, 3, 3],
    [3, 1, 2],
    [2, 3, 1]]
   ```
   **Result:** `False` (Duplicate numbers in a row)

7. **grid7**
   ```python
   [[1, 2, 3, 4],
    [4, 3, 2, 1],
    [1, 3, 2, 4],
    [4, 2, 3, 1]]
   ```
   **Result:** `False`

8. **grid8**
   ```python
   [[0, 3],
    [3, 0]]
   ```
   **Result:** `False` (For a grid of size 2, all rows and columns should contain 1 and 2, not 0 and 3)

9. **grid9**
   ```python
   [[0, 1],
    [1, 0]]
   ```
   **Result:** `False` (Rows and columns should contain 1 and 2, not 0 and 1)

10. **grid10**
    ```python
    [[1, 1, 6],
     [1, 6, 1],
     [6, 1, 1]]
    ```
    **Result:** `False`

11. **grid11**
    ```python
    [[1, 2, 3, 4],
     [2, 3, 1, 4],
     [3, 1, 2, 4],
     [4, 2, 3, 1]]
    ```
    **Result:** `False`

12. **grid12**
    ```python
    [[-1, -2, 12, 1],
     [1, 12, -2, -1]]
    ```
    **Result:** `False` (All rows and columns should contain only values 1,2,3,4)

13. **grid13**
    ```python
    [[2, 3, 3],
     [1, 2, 1],
     [3, 1, 2]]
    ```
    **Result:** `False`

14. **grid14**
    ```python
    [[1, 3],
     [3, 1]]
    ```
    **Result:** `False`

15. **grid15**
    ```python
    [[2, 3],
     [3, 2]]
    ```
    **Result:** `False`

16. **grid16**
    ```python
    [[1, 2],
     [2, 2]]
    ```
    **Result:** `False`

17. **grid17**
    ```python
    [[2, 3, 1],
     [1, 2, 3],
     [2, 3, 1]]
    ```
    **Result:** `False`

---

### **Final Validation Results**
```python
validateSudoku(grid1)  => True
validateSudoku(grid2)  => False
validateSudoku(grid3)  => False
validateSudoku(grid4)  => True
validateSudoku(grid5)  => False
validateSudoku(grid6)  => False
validateSudoku(grid7)  => False
validateSudoku(grid8)  => False
validateSudoku(grid9)  => False
validateSudoku(grid10) => False
validateSudoku(grid11) => False
validateSudoku(grid12) => False
validateSudoku(grid13) => False
validateSudoku(grid14) => False
validateSudoku(grid15) => False
validateSudoku(grid16) => False
validateSudoku(grid17) => False
```

---

### **Complexity Analysis**
- **N =** Number of rows/columns in the matrix

---
 */
public class SubSudokuValidator {

    /**
     * Validates that the provided grid is a sub-Sudoku.
     * Each row and each column must contain exactly the numbers 1..N, where N is grid.length.
     *
     * @param grid The NxN matrix to validate.
     * @return true if the grid is valid; false otherwise.
     */
    public static boolean validateSudoku(int[][] grid) {
        // Check if grid is null or empty.
        if (grid == null || grid.length == 0) return false;
        int n = grid.length;

        // Ensure every row has the same length as n (i.e., the grid is square).
        for (int i = 0; i < n; i++) {
            if (grid[i].length != n) return false;
        }

        // Validate each row.
        for (int i = 0; i < n; i++) {
            boolean[] seen = new boolean[n + 1];
            for (int j = 0; j < n; j++) {
                int value = grid[i][j];
                if (value < 1 || value > n) return false; // Value out of allowed range.
                if (seen[value]) return false;              // Duplicate in row.
                seen[value] = true;
            }
        }

        // Validate each column.
        for (int j = 0; j < n; j++) {
            boolean[] seen = new boolean[n + 1];
            for (int i = 0; i < n; i++) {
                int value = grid[i][j];
                if (value < 1 || value > n) return false; // Value out of allowed range.
                if (seen[value]) return false;              // Duplicate in column.
                seen[value] = true;
            }
        }
        return true;
    }

    /**
     * Helper method to run an individual test case.
     *
     * @param testName Name of the test case.
     * @param grid     The grid to validate.
     * @param expected The expected result.
     */
    public static void runTest(String testName, int[][] grid, boolean expected) {
        boolean result = validateSudoku(grid);
        if (result == expected) {
            System.out.println(testName + ": PASS");
        } else {
            System.out.println(testName + ": FAIL (expected " + expected + ", got " + result + ")");
        }
    }

    /**
     * Main method to run all test cases.
     */
    public static void main(String[] args) {
        // Provided test cases

        // grid1: Valid 3x3
        int[][] grid1 = {
                {2, 3, 1},
                {1, 2, 3},
                {3, 1, 2}
        };

        // grid2: Invalid (columns missing correct values)
        int[][] grid2 = {
                {1, 2, 3},
                {3, 2, 1},
                {3, 1, 2}
        };

        // grid3: Invalid (duplicate values in row/column)
        int[][] grid3 = {
                {2, 2, 3},
                {3, 1, 2},
                {2, 3, 1}
        };

        // grid4: Valid 1x1 grid
        int[][] grid4 = {
                {1}
        };

        // grid5: Invalid (negative numbers)
        int[][] grid5 = {
                {-1, -2, -3},
                {-2, -3, -1},
                {-3, -1, -2}
        };

        // grid6: Invalid (duplicate in row)
        int[][] grid6 = {
                {1, 3, 3},
                {3, 1, 2},
                {2, 3, 1}
        };

        // grid7: Invalid 4x4 grid (duplicate/missing values in rows/columns)
        int[][] grid7 = {
                {1, 2, 3, 4},
                {4, 3, 2, 1},
                {1, 3, 2, 4},
                {4, 2, 3, 1}
        };

        // grid8: Invalid (values out of range for 2x2 grid)
        int[][] grid8 = {
                {0, 3},
                {3, 0}
        };

        // grid9: Invalid (values out of range)
        int[][] grid9 = {
                {0, 1},
                {1, 0}
        };

        // grid10: Invalid (duplicates and out-of-range)
        int[][] grid10 = {
                {1, 1, 6},
                {1, 6, 1},
                {6, 1, 1}
        };

        // grid11: Invalid (4x4 with duplicates/missing values)
        int[][] grid11 = {
                {1, 2, 3, 4},
                {2, 3, 1, 4},
                {3, 1, 2, 4},
                {4, 2, 3, 1}
        };

        // grid12: Invalid (mixed negatives and out-of-range)
        int[][] grid12 = {
                {-1, -2, 12, 1},
                {1, 12, -2, -1}
        };

        // grid13: Invalid (duplicates in row and column)
        int[][] grid13 = {
                {2, 3, 3},
                {1, 2, 1},
                {3, 1, 2}
        };

        // grid14: Invalid (2x2 but missing correct set)
        int[][] grid14 = {
                {1, 3},
                {3, 1}
        };

        // grid15: Invalid (2x2, incorrect numbers)
        int[][] grid15 = {
                {2, 3},
                {3, 2}
        };

        // grid16: Invalid (duplicate in row)
        int[][] grid16 = {
                {1, 2},
                {2, 2}
        };

        // grid17: Invalid (duplicate row pattern)
        int[][] grid17 = {
                {2, 3, 1},
                {1, 2, 3},
                {2, 3, 1}
        };

        // Run the provided tests
        runTest("grid1", grid1, true);
        runTest("grid2", grid2, false);
        runTest("grid3", grid3, false);
        runTest("grid4", grid4, true);
        runTest("grid5", grid5, false);
        runTest("grid6", grid6, false);
        runTest("grid7", grid7, false);
        runTest("grid8", grid8, false);
        runTest("grid9", grid9, false);
        runTest("grid10", grid10, false);
        runTest("grid11", grid11, false);
        runTest("grid12", grid12, false);
        runTest("grid13", grid13, false);
        runTest("grid14", grid14, false);
        runTest("grid15", grid15, false);
        runTest("grid16", grid16, false);
        runTest("grid17", grid17, false);

        // Additional Testing: Large Data Inputs

        // Large Valid Grid: Create a valid grid of size 1000x1000 using a cyclic pattern.
        int largeN = 1000;
        int[][] largeValidGrid = new int[largeN][largeN];
        for (int i = 0; i < largeN; i++) {
            for (int j = 0; j < largeN; j++) {
                // This produces a cyclic permutation so each row and column is valid.
                largeValidGrid[i][j] = (i + j) % largeN + 1;
            }
        }
        runTest("largeValidGrid", largeValidGrid, true);

        // Large Invalid Grid: Duplicate a value in the first row.
        int[][] largeInvalidGrid = new int[largeN][largeN];
        for (int i = 0; i < largeN; i++) {
            for (int j = 0; j < largeN; j++) {
                largeInvalidGrid[i][j] = (i + j) % largeN + 1;
            }
        }
        // Introduce an error: duplicate the first element in the first row.
        largeInvalidGrid[0][largeN - 1] = largeInvalidGrid[0][0];
        runTest("largeInvalidGrid", largeInvalidGrid, false);
    }
}