package com.interview.notes.code.year.y2025.feb25.Walmart.test5;

import java.util.ArrayList;
import java.util.List;

/*
WORKING 100%


### **Nonogram Validation**

For each row and column, the corresponding instruction gives the lengths of contiguous runs of black ('B') cells.
For example, the instruction **[2, 1]** for a specific row indicates that there must be a run of **two black cells**, followed later by another run of **one black cell**, and the rest of the row is filled with **white cells**.

#### **Valid Solutions**
- `[ W, B, B, W, B ]`
- `[ B, B, W, W, B ]`
- `[ B, B, W, B, W ]`

#### **Invalid Solutions**
- `[ W, B, W, B, B ]` → The runs are not in the correct order.
- `[ W, B, B, B, W ]` → The two runs of B's are not separated by W's.

---

## **Function Task**
Your job is to write a function to validate a **possible solution** against a **set of instructions**.
Given a **2D matrix** representing a player's solution, and instructions for each **row** along with additional instructions for each **column**, return **True** or **False** based on whether both sets of instructions match.

---

### **Example Solution Matrix**
#### **Matrix Representation**
```plaintext
+------------+
| W  W  W  W |  <-- []
| B  W  W  W |  <-- [1]
| B  W  B  B |  <-- [1,2]
| W  W  B  W |  <-- [1]
| B  B  W  W |  <-- [2]
+------------+
```
#### **Column Instructions**
```plaintext
   ^  ^  ^  ^
   |  |  |  |
[2,1] | [2] | [1] | [1]
```

#### **Validation Call**
```python
validateNonogram(matrix1, rows1_1, columns1_1) => True
```

---

## **Example Instructions #2**
(Same matrix as above)

```python
rows1_2    = [[], [], [1], [1], [1,1]]
columns1_2 = [[2], [1], [2], [1]]
validateNonogram(matrix1, rows1_2, columns1_2) => False
```
> The **second, third, and last rows** and the **first column** do not match their respective instructions.

---

## **All Test Cases**
```python
validateNonogram(matrix1, rows1_1, columns1_1) => True
validateNonogram(matrix1, rows1_2, columns1_2) => False
validateNonogram(matrix1, rows1_3, columns1_3) => False
validateNonogram(matrix1, rows1_4, columns1_4) => False
validateNonogram(matrix1, rows1_5, columns1_5) => False
validateNonogram(matrix1, rows1_6, columns1_6) => False
validateNonogram(matrix1, rows1_7, columns1_7) => False
validateNonogram(matrix1, rows1_8, columns1_8) => False
validateNonogram(matrix2, rows2_1, columns2_1) => False
validateNonogram(matrix2, rows2_2, columns2_2) => False
validateNonogram(matrix2, rows2_3, columns2_3) => False
validateNonogram(matrix2, rows2_4, columns2_4) => False
validateNonogram(matrix2, rows2_5, columns2_5) => True
validateNonogram(matrix2, rows2_6, columns2_6) => False
validateNonogram(matrix3, rows3_1, columns3_1) => True
validateNonogram(matrix3, rows3_2, columns3_2) => False
```

---

### **Complexity Analysis Variables**
- **n** = Number of rows in the matrix
- **m** = Number of columns in the matrix

---

 */
public class NonogramValidator {

    /**
     * Validates a nonogram solution.
     * <p>
     * For each row and column in the matrix, the method generates the contiguous run lengths
     * of black cells ('B') and compares them to the provided instructions.
     *
     * @param matrix          A 2D character array where each cell is either 'B' (black) or 'W' (white)
     * @param rowInstructions A 2D integer array; each subarray specifies the expected run lengths for the corresponding row
     * @param colInstructions A 2D integer array; each subarray specifies the expected run lengths for the corresponding column
     * @return true if the solution matches all row and column instructions; false otherwise.
     */
    public static boolean validateNonogram(char[][] matrix, int[][] rowInstructions, int[][] colInstructions) {
        // Check for null/empty matrix.
        if (matrix == null || matrix.length == 0) return false;
        int n = matrix.length;         // Number of rows.
        int m = matrix[0].length;      // Number of columns.

        // Ensure all rows have the same length.
        for (int i = 0; i < n; i++) {
            if (matrix[i].length != m) return false;
        }
        // Check if the instructions dimensions match the matrix dimensions.
        if (rowInstructions.length != n) return false;
        if (colInstructions.length != m) return false;

        // Validate each row.
        for (int i = 0; i < n; i++) {
            int[] runs = getRuns(matrix[i]);
            if (!compareRuns(runs, rowInstructions[i])) {
                return false;
            }
        }

        // Validate each column.
        for (int j = 0; j < m; j++) {
            char[] col = new char[n];
            for (int i = 0; i < n; i++) {
                col[i] = matrix[i][j];
            }
            int[] runs = getRuns(col);
            if (!compareRuns(runs, colInstructions[j])) {
                return false;
            }
        }

        return true;
    }

    /**
     * Generates the contiguous run lengths of black cells ('B') in a given line.
     *
     * @param line A one-dimensional array of characters representing a row or column.
     * @return An array of integers representing the lengths of contiguous runs of 'B' cells.
     */
    private static int[] getRuns(char[] line) {
        List<Integer> runsList = new ArrayList<>();
        int count = 0;
        for (char c : line) {
            if (c == 'B') {
                count++;
            } else { // Encountered a white cell ('W').
                if (count > 0) {
                    runsList.add(count);
                    count = 0;
                }
            }
        }
        // Add any run that reaches the end of the line.
        if (count > 0) {
            runsList.add(count);
        }
        // Convert the list to an array.
        int[] runs = new int[runsList.size()];
        for (int i = 0; i < runsList.size(); i++) {
            runs[i] = runsList.get(i);
        }
        return runs;
    }

    /**
     * Compares two integer arrays for equality.
     *
     * @param runs     The array generated from the solution.
     * @param expected The instruction array that represents the expected runs.
     * @return true if both arrays have the same length and identical elements; false otherwise.
     */
    private static boolean compareRuns(int[] runs, int[] expected) {
        if (runs.length != expected.length) return false;
        for (int i = 0; i < runs.length; i++) {
            if (runs[i] != expected[i]) return false;
        }
        return true;
    }

    /**
     * A helper method to run a single test case.
     *
     * @param testName        A string representing the name of the test case.
     * @param matrix          The nonogram solution matrix to test.
     * @param rowInstructions The expected run instructions for each row.
     * @param colInstructions The expected run instructions for each column.
     * @param expected        The expected boolean result.
     */
    private static void runTest(String testName, char[][] matrix, int[][] rowInstructions, int[][] colInstructions, boolean expected) {
        boolean result = validateNonogram(matrix, rowInstructions, colInstructions);
        if (result == expected) {
            System.out.println(testName + ": PASS");
        } else {
            System.out.println(testName + ": FAIL (expected " + expected + ", got " + result + ")");
        }
    }

    /**
     * The main method runs all test cases including edge scenarios and large data inputs.
     */
    public static void main(String[] args) {
        // ------------------------------
        // Test Cases for matrix1 (5 rows x 4 columns)
        // ------------------------------
        // Example solution matrix:
        // Row 0: [W, W, W, W]          -> Expected instruction: []
        // Row 1: [B, W, W, W]          -> Expected instruction: [1]
        // Row 2: [B, W, B, B]          -> Expected instruction: [1,2]
        // Row 3: [W, W, B, W]          -> Expected instruction: [1]
        // Row 4: [B, B, W, W]          -> Expected instruction: [2]
        char[][] matrix1 = {
                {'W', 'W', 'W', 'W'},
                {'B', 'W', 'W', 'W'},
                {'B', 'W', 'B', 'B'},
                {'W', 'W', 'B', 'W'},
                {'B', 'B', 'W', 'W'}
        };
        int[][] rows1_1 = {
                new int[]{},       // Row 0
                new int[]{1},      // Row 1
                new int[]{1, 2},   // Row 2
                new int[]{1},      // Row 3
                new int[]{2}       // Row 4
        };
        int[][] cols1_1 = {
                new int[]{2, 1},   // Column 0
                new int[]{2},      // Column 1
                new int[]{1},      // Column 2
                new int[]{1}       // Column 3
        };
        runTest("matrix1 rows1_1", matrix1, rows1_1, cols1_1, true);

        // Example Instructions #2 (should fail):
        int[][] rows1_2 = {
                new int[]{},       // Row 0
                new int[]{},       // Row 1 (should be [1])
                new int[]{1},      // Row 2 (should be [1,2])
                new int[]{1},      // Row 3
                new int[]{1, 1}    // Row 4 (should be [2])
        };
        int[][] cols1_2 = {
                new int[]{2},      // Column 0 (should be [2,1])
                new int[]{1},      // Column 1 (should be [2])
                new int[]{2},      // Column 2 (should be [1])
                new int[]{1}       // Column 3
        };
        runTest("matrix1 rows1_2", matrix1, rows1_2, cols1_2, false);

        // Additional invalid test cases for matrix1:
        int[][] rows1_3 = {
                new int[]{},
                new int[]{1},
                new int[]{2, 1},  // Wrong order; expected [1,2]
                new int[]{1},
                new int[]{2}
        };
        runTest("matrix1 rows1_3", matrix1, rows1_3, cols1_1, false);

        int[][] cols1_4 = {
                new int[]{2},     // Wrong; should be [2,1]
                new int[]{2},
                new int[]{1},
                new int[]{1}
        };
        runTest("matrix1 rows1_4", matrix1, rows1_1, cols1_4, false);

        int[][] rows1_5 = {
                new int[]{1},     // Wrong; should be []
                new int[]{1},
                new int[]{1, 2},
                new int[]{1},
                new int[]{2}
        };
        runTest("matrix1 rows1_5", matrix1, rows1_5, cols1_1, false);

        int[][] cols1_6 = {
                new int[]{2, 1},
                new int[]{2},
                new int[]{1, 1},  // Wrong; should be [1]
                new int[]{1}
        };
        runTest("matrix1 rows1_6", matrix1, rows1_1, cols1_6, false);

        int[][] cols1_7 = {
                new int[]{2, 1},
                new int[]{2},
                new int[]{1},
                new int[]{1, 1}   // Wrong; should be [1]
        };
        runTest("matrix1 rows1_7", matrix1, rows1_1, cols1_7, false);

        int[][] rows1_8 = {
                new int[]{},
                new int[]{1},
                new int[]{1, 2},
                new int[]{1, 1},  // Wrong; should be [1]
                new int[]{2}
        };
        runTest("matrix1 rows1_8", matrix1, rows1_8, cols1_1, false);

        // ------------------------------
        // Test Cases for matrix2 (3 rows x 3 columns)
        // ------------------------------
        // Define matrix2:
        // Row 0: [B, W, B]  -> Runs: [1, 1]
        // Row 1: [W, B, W]  -> Runs: [1]
        // Row 2: [B, B, W]  -> Runs: [2]
        char[][] matrix2 = {
                {'B', 'W', 'B'},
                {'W', 'B', 'W'},
                {'B', 'B', 'W'}
        };
        // Correct instructions:
        int[][] rows2_5 = {
                new int[]{1, 1},
                new int[]{1},
                new int[]{2}
        };
        int[][] cols2_5 = {
                new int[]{1, 1},  // Column 0: B, W, B
                new int[]{2},     // Column 1: W, B, B
                new int[]{1}      // Column 2: B, W, W
        };
        runTest("matrix2 rows2_5", matrix2, rows2_5, cols2_5, true);

        // Other invalid instruction variations for matrix2:
        int[][] rows2_1 = {
                new int[]{},
                new int[]{1},
                new int[]{2}
        };
        runTest("matrix2 rows2_1", matrix2, rows2_1, cols2_5, false);

        int[][] rows2_2 = {
                new int[]{1, 1},
                new int[]{},
                new int[]{2}
        };
        runTest("matrix2 rows2_2", matrix2, rows2_2, cols2_5, false);

        int[][] rows2_3 = {
                new int[]{1, 1},
                new int[]{1},
                new int[]{}
        };
        runTest("matrix2 rows2_3", matrix2, rows2_3, cols2_5, false);

        int[][] cols2_4 = {
                new int[]{},  // Wrong: should be [1,1]
                new int[]{2},
                new int[]{1}
        };
        runTest("matrix2 rows2_4", matrix2, rows2_5, cols2_4, false);

        int[][] cols2_6 = {
                new int[]{1, 1},
                new int[]{1},  // Wrong: should be [2]
                new int[]{1}
        };
        runTest("matrix2 rows2_6", matrix2, rows2_5, cols2_6, false);

        // ------------------------------
        // Test Cases for matrix3 (4 rows x 4 columns)
        // ------------------------------
        // Define matrix3:
        // Row 0: [W, B, B, W] -> Runs: [2]
        // Row 1: [B, B, W, B] -> Runs: [2, 1]
        // Row 2: [W, W, B, B] -> Runs: [2]
        // Row 3: [B, W, W, W] -> Runs: [1]
        char[][] matrix3 = {
                {'W', 'B', 'B', 'W'},
                {'B', 'B', 'W', 'B'},
                {'W', 'W', 'B', 'B'},
                {'B', 'W', 'W', 'W'}
        };
        int[][] rows3_1 = {
                new int[]{2},
                new int[]{2, 1},
                new int[]{2},
                new int[]{1}
        };
        int[][] cols3_1 = {
                new int[]{1, 1}, // Column 0: [W, B, W, B] -> [1,1]
                new int[]{2},    // Column 1: [B, B, W, W] -> [2]
                new int[]{1, 1}, // Column 2: [B, W, B, W] -> [1,1]
                new int[]{2}     // Column 3: [W, B, B, W] -> [2]
        };
        runTest("matrix3 rows3_1", matrix3, rows3_1, cols3_1, true);

        int[][] rows3_2 = {
                new int[]{2},
                new int[]{2},    // Wrong: row1 should be [2,1]
                new int[]{2},
                new int[]{1}
        };
        runTest("matrix3 rows3_2", matrix3, rows3_2, cols3_1, false);

        // ------------------------------
        // Additional Large Data Test
        // ------------------------------
        // Create a large all-white matrix (1000x1000). All rows and columns should have empty instructions.
        int largeRows = 1000;
        int largeCols = 1000;
        char[][] largeMatrix = new char[largeRows][largeCols];
        for (int i = 0; i < largeRows; i++) {
            for (int j = 0; j < largeCols; j++) {
                largeMatrix[i][j] = 'W';
            }
        }
        // For an all-white grid, every row and column should have no black runs.
        int[][] largeRowsInstr = new int[largeRows][0];
        int[][] largeColsInstr = new int[largeCols][0];
        runTest("largeMatrix all white", largeMatrix, largeRowsInstr, largeColsInstr, true);
    }
}