package com.interview.notes.code.year.y2025.jan.M6012024.wallmart.test3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*

WORKING

We have a two-dimensional board game involving snakes. The board has two types of squares on it: '+' represents impassable squares where snakes cannot move, and '0' represents squares through which snakes can move. Snakes can only enter on the edges of the board, and each snake can move in only one direction. We'd like to find the places where a snake can pass through the entire board, moving in a straight line.

Here is an example board:

```
col-->  0  1  2  3  4  5  6
row    |
       0  +  +  +  +  0  0
       1  0  0  +  0  0  0
       2  0  0  +  0  0  0
       3  0  0  +  0  0  0
```

Write a function that takes a rectangular board with only '+' and '0's, and returns two collections:

- One containing all of the row numbers whose row is completely passable by snakes, and
- The other containing all of the column numbers where the column is completely passable by snakes.

Sample Inputs:

```
board1 = [['+', '+', '+', '+', '0', '0'],
          ['0', '0', '+', '0', '0', '0'],
          ['0', '0', '+', '0', '0', '0'],
          ['0', '0', '+', '0', '0', '0']]

board2 = [['+', '+', '+', '+', '0', '0'],
          ['0', '0', '0', '0', '0', '0'],
          ['0', '0', '0', '0', '0', '0'],
          ['0', '0', '0', '0', '0', '0']]

board3 = [['+', '+', '+', '0', '0', '0'],
          ['0', '0', '0', '0', '0', '0'],
          ['0', '0', '0', '0', '0', '0']]

board4 = [['+']]

board5 = [['0']]

board6 = [['0', '0'],
          ['0', '0'],
          ['0', '0']]

All test cases:

findPassableLanes(board1) => Rows: [4], Columns: [3, 6]
findPassableLanes(board2) => Rows: [1], Columns: [3]
findPassableLanes(board3) => Rows: [], Columns: []
findPassableLanes(board4) => Rows: [0], Columns: []
findPassableLanes(board5) => Rows: [0, 1, 2, 3], Columns: [0, 1]

Complexity Analysis:
- r: number of rows in the board
- c: number of columns in the board



Here is the content of the second image converted to text:

```java
import java.io.*;
import java.util.*;
import javafx.util.Pair;

public class Solution {
    public static void main(String[] argv) {
        char[][] board1 = {
            {'+', '+', '+', '0', '+', '0', '0'},
            {'0', '0', '+', '0', '0', '0'},
            {'0', '0', '+', '0', '0', '0'},
            {'+', '+', '+', '0', '0', '0'}
        };

        char[][] board2 = {
            {'+', '+', '+', '+', '0', '0'},
            {'0', '0', '0', '0', '0', '0'},
            {'0', '0', '0', '0', '0', '0'},
            {'0', '0', '0', '0', '0', '0'}
        };

        char[][] board3 = {
            {'+', '+', '+', '0', '0', '0'},
            {'0', '0', '0', '0', '0', '0'},
            {'0', '0', '0', '0', '0', '0'},
            {'+', '+', '+', '0', '0', '0'}
        };

        char[][] board4 = {{'+'}};

        char[][] board5 = {{'0'}};

        char[][] board6 = {
            {'0', '0'},
            {'0', '0'},
            {'0', '0'},
            {'0', '0'},
            {'0', '0'}
        };
    }
}
```

 */
public class FindPassableLanesSolution {

    // Helper method: find rows where all cells are '0'
    public static List<Integer> findPassableRows(String[][] board) {
        List<Integer> passableRows = new ArrayList<>();
        if (board == null || board.length == 0) {
            return passableRows; // Return empty if no rows
        }

        // r = number of rows, c = number of columns
        int r = board.length;
        int c = board[0].length;

        for (int row = 0; row < r; row++) {
            boolean isPassable = true;
            for (int col = 0; col < c; col++) {
                if (board[row][col].equals("+")) {
                    isPassable = false;
                    break;
                }
            }
            if (isPassable) {
                passableRows.add(row);
            }
        }
        return passableRows;
    }

    // Helper method: find columns where all cells are '0'
    public static List<Integer> findPassableColumns(String[][] board) {
        List<Integer> passableCols = new ArrayList<>();
        if (board == null || board.length == 0) {
            return passableCols; // Return empty if no columns
        }

        int r = board.length;
        int c = board[0].length;

        for (int col = 0; col < c; col++) {
            boolean isPassable = true;
            for (int row = 0; row < r; row++) {
                if (board[row][col].equals("+")) {
                    isPassable = false;
                    break;
                }
            }
            if (isPassable) {
                passableCols.add(col);
            }
        }
        return passableCols;
    }

    // Main method to run tests without JUnit
    public static void main(String[] args) {
        // Define sample boards
        // Note: Some sample boards and expected results as given in the problem statement
        //       might not perfectly match due to indexing differences; we will
        //       demonstrate the general approach and show pass/fail.

        // board1
        String[][] board1 = {
                {"+", "+", "+", "+", "0", "0"},
                {"0", "0", "+", "0", "0", "0"},
                {"0", "0", "+", "0", "0", "0"},
                {"0", "0", "+", "0", "0", "0"}
        };
        // For this layout, let's compute passable rows/columns:
        List<Integer> rows1 = findPassableRows(board1);
        List<Integer> cols1 = findPassableColumns(board1);

        // Suppose we define expected results (they may differ from the problem statement’s example):
        // We'll discover them by logic:
        //   - Row 0 has '+', so row 0 is not passable
        //   - Row 1 has '+', not passable
        //   - Row 2 has '+', not passable
        //   - Row 3 has '+', not passable
        // So expected passable rows = []
        //
        //   - Column 0 has '++0 0', includes '+', so not passable
        //   - Column 1 has '++0 0', includes '+', so not passable
        //   - Column 2 has '+++ +', all '+', definitely not passable
        //   - Column 3 has '+0 0 0', includes '+', so not passable
        //   - Column 4 has '0 0 0 0', all '0' => passable
        //   - Column 5 has '0 0 0 0', all '0' => passable
        // So expected passable columns = [4, 5]
        List<Integer> expectedRows1 = Arrays.asList();
        List<Integer> expectedCols1 = Arrays.asList(4, 5);

        checkResult("board1 rows", rows1, expectedRows1);
        checkResult("board1 cols", cols1, expectedCols1);

        // board2
        String[][] board2 = {
                {"+", "+", "+", "+", "0", "0"},
                {"0", "0", "0", "0", "0", "0"},
                {"0", "0", "0", "0", "0", "0"},
                {"0", "0", "0", "0", "0", "0"}
        };
        // Let's see the actual passable rows:
        //  - Row 0 has '+', so not passable
        //  - Row 1 all '0' => passable
        //  - Row 2 all '0' => passable
        //  - Row 3 all '0' => passable
        // passable rows => [1, 2, 3]
        //
        // For columns:
        //  - Column 0 has '+000' => includes '+', not passable
        //  - Column 1 has '+000' => includes '+', not passable
        //  - Column 2 has '+000' => includes '+', not passable
        //  - Column 3 has '+000' => includes '+', not passable
        //  - Column 4 has '0 0 0 0' => all '0', passable
        //  - Column 5 has '0 0 0 0' => all '0', passable
        // passable columns => [4, 5]
        List<Integer> rows2 = findPassableRows(board2);
        List<Integer> cols2 = findPassableColumns(board2);

        // Suppose we set the expected results:
        List<Integer> expectedRows2 = Arrays.asList(1, 2, 3);
        List<Integer> expectedCols2 = Arrays.asList(4, 5);

        checkResult("board2 rows", rows2, expectedRows2);
        checkResult("board2 cols", cols2, expectedCols2);

        // board3
        String[][] board3 = {
                {"+", "+", "+", "0", "0", "0"},
                {"0", "0", "0", "0", "0", "0"},
                {"0", "0", "0", "0", "0", "0"}
        };
        // Check passable rows:
        //  - Row 0 has '+', not passable
        //  - Row 1 all '0', passable
        //  - Row 2 all '0', passable
        // passable rows => [1, 2]
        //
        // Columns (6 columns):
        //  col0: +,0,0 => '+', not passable
        //  col1: +,0,0 => '+', not passable
        //  col2: +,0,0 => '+', not passable
        //  col3: 0,0,0 => all '0', passable
        //  col4: 0,0,0 => passable
        //  col5: 0,0,0 => passable
        // passable columns => [3,4,5]
        List<Integer> rows3 = findPassableRows(board3);
        List<Integer> cols3 = findPassableColumns(board3);
        List<Integer> expectedRows3 = Arrays.asList(1, 2);
        List<Integer> expectedCols3 = Arrays.asList(3, 4, 5);

        checkResult("board3 rows", rows3, expectedRows3);
        checkResult("board3 cols", cols3, expectedCols3);

        // board4: single cell '+'
        String[][] board4 = {
                {"+"}
        };
        // passable rows => none
        // passable columns => none
        List<Integer> rows4 = findPassableRows(board4);
        List<Integer> cols4 = findPassableColumns(board4);
        List<Integer> expectedRows4 = Arrays.asList();
        List<Integer> expectedCols4 = Arrays.asList();

        checkResult("board4 rows", rows4, expectedRows4);
        checkResult("board4 cols", cols4, expectedCols4);

        // board5: single cell '0'
        String[][] board5 = {
                {"0"}
        };
        // passable rows => [0]
        // passable columns => [0]
        List<Integer> rows5 = findPassableRows(board5);
        List<Integer> cols5 = findPassableColumns(board5);
        List<Integer> expectedRows5 = Arrays.asList(0);
        List<Integer> expectedCols5 = Arrays.asList(0);

        checkResult("board5 rows", rows5, expectedRows5);
        checkResult("board5 cols", cols5, expectedCols5);

        // board6: multiple rows, multiple columns all '0'
        String[][] board6 = {
                {"0", "0"},
                {"0", "0"},
                {"0", "0"}
        };
        // All rows are passable => [0, 1, 2]
        // All columns are passable => [0, 1]
        List<Integer> rows6 = findPassableRows(board6);
        List<Integer> cols6 = findPassableColumns(board6);
        List<Integer> expectedRows6 = Arrays.asList(0, 1, 2);
        List<Integer> expectedCols6 = Arrays.asList(0, 1);

        checkResult("board6 rows", rows6, expectedRows6);
        checkResult("board6 cols", cols6, expectedCols6);

        // Demonstrate a large board check (just to show it works quickly):
        // e.g., 1000 x 1000 with all '0' (not building it here for brevity).
        System.out.println("All tests completed.");
    }

    // Utility method to compare lists and print pass/fail
    private static void checkResult(String testName, List<Integer> actual, List<Integer> expected) {
        if (actual.equals(expected)) {
            System.out.println(testName + " => PASS. Result: " + actual);
        } else {
            System.out.println(testName + " => FAIL.");
            System.out.println("   Expected: " + expected);
            System.out.println("   Actual:   " + actual);
        }
    }
}
