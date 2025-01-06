package com.interview.notes.code.year.y2024.dec24.wallmart.test3;

import java.util.HashSet;
import java.util.Set;

/*
WORKING



You are working on a logic game made up of a series of puzzles. The first type of puzzle you settle on is "sub-Sudoku," a game where the player has to position the numbers **1..N** on an NxN matrix.

Your job is to write a function that, given an NxN matrix, returns **true** if every row and column contains the numbers **1..N**.

The UI for the game does not do any validation on the numbers the player enters, so the matrix can contain any signed integer.

---

### Test Cases:

```plaintext
grid1 = [[2, 3, 1],
         [1, 2, 3],
         [3, 1, 2]] -> True (A grid of size 3: every row and column contains the numbers 1,2,3)

grid2 = [[1, 2, 3],
         [3, 2, 1],
         [3, 1, 2]] -> False (The first column is missing the value 2. It should contain 1,2,3.
                             Similarly, the second column is missing the value 3.)

grid3 = [[2, 2, 3],
         [3, 1, 2],
         [2, 3, 1]] -> False (The first row is missing the value 1. Same for the first column.)

grid4 = [[1]] -> True (A grid of size one: it contains 1 as the single value.)

grid5 = [[-1, -2, -3],
         [-2, -3, -1],
         [-3, -1, -2]] -> False (The rows and columns need to contain the values 1,2,3 and not -1, -2, -3)

grid6 = [[1, 3, 3],
         [3, 1, 2],
         [2, 3, 1]] -> False

grid7 = [[1, 2, 3, 4],
         [4, 3, 2, 1],
         [1, 3, 2, 4],
         [4, 2, 3, 1]] -> False

grid8 = [[0, 3],
         [3, 0]] -> False (For a grid of size 2, all rows and columns should contain 1 and 2, not 0 and 3.)

grid9 = [[0, 1],
         [1, 0]] -> False (Same issue as above.)

grid10 = [[1, 1, 6],
          [1, 6, 1],
          [6, 1, 1]] -> False

grid11 = [[1, 2, 3, 4],
          [2, 3, 4, 1],
          [3, 1, 2, 4],
          [4, 2, 3, 1]] -> False

grid12 = [[-1, -2, 12, 1],
          [12, -1, 1, -2],
          [-2, 1, -1, 12],
          [1, 12, -2, -1]] -> False (All rows and columns should contain the values 1,2,3,4. Input contains positive and negative numbers.)

grid13 = [[2, 3, 3],
          [1, 2, 1],
          [3, 1, 2]] -> False

grid14 = [[1, 3],
          [3, 1]] -> False

grid15 = [[2, 3],
          [3, 2]] -> False

grid16 = [[1, 2],
          [2, 2]] -> False

grid17 = [[2, 3, 1],
          [1, 2, 3],
          [2, 3, 1]] -> False
```

---

### Validation Results:

```plaintext
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

### Complexity Analysis:

**N** = The number of rows/columns in the matrix.

Here is the **final merged text** from all images, cleaned up and combined into a proper structure:

---

### Problem Statement:
You are working on a logic game made up of a series of puzzles. The first type of puzzle you settle on is "sub-Sudoku," a game where the player has to position the numbers **1..N** on an **NxN matrix**.

Your job is to write a function that, given an NxN matrix, returns **true** if every row and column contains the numbers **1..N**.

The UI for the game does not do any validation on the numbers the player enters, so the matrix can contain any signed integer.

---

### Test Matrices:

```java
public static void main(String[] argv) {
    int[][] grid1 = {
        {2, 3, 1},
        {1, 2, 3},
        {3, 1, 2}
    }; // True

    int[][] grid2 = {
        {1, 2, 3},
        {3, 2, 1},
        {3, 1, 2}
    }; // False

    int[][] grid3 = {
        {2, 2, 3},
        {3, 1, 2},
        {2, 3, 1}
    }; // False

    int[][] grid4 = {
        {1}
    }; // True

    int[][] grid5 = {
        {-1, -2, -3},
        {-2, -3, -1},
        {-3, -1, -2}
    }; // False

    int[][] grid6 = {
        {1, 3, 3},
        {3, 1, 2},
        {2, 3, 1}
    }; // False

    int[][] grid7 = {
        {1, 2, 3, 4},
        {4, 3, 2, 1},
        {1, 3, 2, 4},
        {4, 2, 3, 1}
    }; // False

    int[][] grid8 = {
        {0, 3},
        {3, 0}
    }; // False

    int[][] grid9 = {
        {0, 1},
        {1, 0}
    }; // False

    int[][] grid10 = {
        {1, 1, 6},
        {1, 6, 1},
        {6, 1, 1}
    }; // False

    int[][] grid11 = {
        {1, 2, 3, 4},
        {2, 3, 4, 1},
        {3, 1, 2, 4},
        {4, 2, 3, 1}
    }; // False

    int[][] grid12 = {
        {-1, -2, 12, 1},
        {12, -1, 1, -2},
        {-2, 1, -1, 12},
        {1, 12, -2, -1}
    }; // False

    int[][] grid13 = {
        {2, 3, 3},
        {1, 2, 1},
        {3, 1, 2}
    }; // False

    int[][] grid14 = {
        {1, 3},
        {3, 1}
    }; // False

    int[][] grid15 = {
        {2, 3},
        {3, 2}
    }; // False

    int[][] grid16 = {
        {1, 2},
        {2, 2}
    }; // False

    int[][] grid17 = {
        {2, 3, 1},
        {1, 2, 3},
        {2, 3, 1}
    }; // False
}
```

---

### Expected Results:

```plaintext
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

### Complexity Analysis:

- **Time Complexity**: \(O(N^2)\), where \(N\) is the number of rows/columns in the matrix.
- **Space Complexity**: \(O(N)\), for tracking unique numbers in each row and column.

---

This merges all test cases and results cleanly into a final, structured representation.
 */
public class SubSudokuValidator {

    /**
     * Validates whether the given NxN matrix is a valid sub-Sudoku.
     * Each row and each column must contain exactly the numbers from 1 to N.
     *
     * @param grid The NxN matrix to validate.
     * @return true if valid, false otherwise.
     */
    public static boolean validateSudoku(int[][] grid) {
        if (grid == null) {
            return false;
        }

        int N = grid.length;

        // Check if the grid is square
        for (int[] row : grid) {
            if (row.length != N) {
                return false;
            }
        }

        // Create the valid set {1, 2, ..., N}
        Set<Integer> validSet = new HashSet<>();
        for (int i = 1; i <= N; i++) {
            validSet.add(i);
        }

        // Validate rows
        for (int i = 0; i < N; i++) {
            Set<Integer> rowSet = new HashSet<>();
            for (int j = 0; j < N; j++) {
                int num = grid[i][j];
                rowSet.add(num);
            }
            if (!rowSet.equals(validSet)) {
                return false;
            }
        }

        // Validate columns
        for (int j = 0; j < N; j++) {
            Set<Integer> colSet = new HashSet<>();
            for (int i = 0; i < N; i++) {
                int num = grid[i][j];
                colSet.add(num);
            }
            if (!colSet.equals(validSet)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Runs predefined test cases and additional edge cases to validate the validateSudoku function.
     * <p>
     * Outputs PASS or FAIL for each test case.
     */
    public static void main(String[] args) {
        // Define test cases
        int[][] grid1 = {
                {2, 3, 1},
                {1, 2, 3},
                {3, 1, 2}
        }; // True

        int[][] grid2 = {
                {1, 2, 3},
                {3, 2, 1},
                {3, 1, 2}
        }; // False

        int[][] grid3 = {
                {2, 2, 3},
                {3, 1, 2},
                {2, 3, 1}
        }; // False

        int[][] grid4 = {
                {1}
        }; // True

        int[][] grid5 = {
                {-1, -2, -3},
                {-2, -3, -1},
                {-3, -1, -2}
        }; // False

        int[][] grid6 = {
                {1, 3, 3},
                {3, 1, 2},
                {2, 3, 1}
        }; // False

        int[][] grid7 = {
                {1, 2, 3, 4},
                {4, 3, 2, 1},
                {1, 3, 2, 4},
                {4, 2, 3, 1}
        }; // False

        int[][] grid8 = {
                {0, 3},
                {3, 0}
        }; // False

        int[][] grid9 = {
                {0, 1},
                {1, 0}
        }; // False

        int[][] grid10 = {
                {1, 1, 6},
                {1, 6, 1},
                {6, 1, 1}
        }; // False

        int[][] grid11 = {
                {1, 2, 3, 4},
                {2, 3, 4, 1},
                {3, 1, 2, 4},
                {4, 2, 3, 1}
        }; // False

        int[][] grid12 = {
                {-1, -2, 12, 1},
                {12, -1, 1, -2},
                {-2, 1, -1, 12},
                {1, 12, -2, -1}
        }; // False

        int[][] grid13 = {
                {2, 3, 3},
                {1, 2, 1},
                {3, 1, 2}
        }; // False

        int[][] grid14 = {
                {1, 3},
                {3, 1}
        }; // False

        int[][] grid15 = {
                {2, 3},
                {3, 2}
        }; // False

        int[][] grid16 = {
                {1, 2},
                {2, 2}
        }; // False

        int[][] grid17 = {
                {2, 3, 1},
                {1, 2, 3},
                {2, 3, 1}
        }; // False

        // Additional Edge Cases
        int[][] grid18 = {}; // False (Empty grid)
        int[][] grid19 = {
                {1, 2},
                {3, 4}
        }; // False (Incorrect numbers for N=2)
        int[][] grid20 = {
                {1, 2, 3, 4, 5},
                {5, 4, 3, 2, 1},
                {2, 3, 4, 5, 1},
                {3, 5, 1, 4, 2},
                {4, 1, 2, 3, 5}
        }; // True

        // Large Grid Test Case (N=1000)
        int N = 1000;
        int[][] gridLarge = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                gridLarge[i][j] = (j + 1);
            }
            // Rotate the row to make columns valid
            for (int j = 0; j < N; j++) {
                gridLarge[i][j] = (j + i) % N + 1;
            }
        } // True

        // Define an array of test cases and their expected results
        TestCase[] testCases = {
                new TestCase("grid1", grid1, true),
                new TestCase("grid2", grid2, false),
                new TestCase("grid3", grid3, false),
                new TestCase("grid4", grid4, true),
                new TestCase("grid5", grid5, false),
                new TestCase("grid6", grid6, false),
                new TestCase("grid7", grid7, false),
                new TestCase("grid8", grid8, false),
                new TestCase("grid9", grid9, false),
                new TestCase("grid10", grid10, false),
                new TestCase("grid11", grid11, false),
                new TestCase("grid12", grid12, false),
                new TestCase("grid13", grid13, false),
                new TestCase("grid14", grid14, false),
                new TestCase("grid15", grid15, false),
                new TestCase("grid16", grid16, false),
                new TestCase("grid17", grid17, false),
                new TestCase("grid18", grid18, false),
                new TestCase("grid19", grid19, false),
                new TestCase("grid20", grid20, true),
                new TestCase("gridLarge", gridLarge, true)
        };

        // Run test cases
        for (TestCase testCase : testCases) {
            boolean result;
            try {
                result = validateSudoku(testCase.grid);
            } catch (Exception e) {
                result = false;
            }
            if (result == testCase.expected) {
                System.out.println(testCase.name + " => PASS");
            } else {
                System.out.println(testCase.name + " => FAIL (Expected " + testCase.expected + " but got " + result + ")");
            }
        }
    }

    /**
     * Helper class to define test cases.
     */
    static class TestCase {
        String name;
        int[][] grid;
        boolean expected;

        TestCase(String name, int[][] grid, boolean expected) {
            this.name = name;
            this.grid = grid;
            this.expected = expected;
        }
    }
}
