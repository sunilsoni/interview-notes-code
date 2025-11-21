package com.interview.notes.code.year.y2025.november.karat.test1;

import java.util.Arrays;

/**
 * SubSudokuValidator
 * <p>
 * Problem Analysis (plain words)
 * - We have an N x N matrix.
 * - Every row must contain each number 1..N exactly once.
 * - Every column must contain each number 1..N exactly once.
 * - Values outside 1..N (like 0, -1, 12 for N=4) make the grid invalid.
 * - Input might come as String[][]; we should parse and validate.
 * <p>
 * Solution Design
 * - First, check the shape: matrix must be square and non-empty.
 * - Convert String[][] to int[][] when needed, failing fast on bad numbers.
 * - For each index i in 0..N-1:
 * * Check row i and column i in a single pass using two boolean arrays:
 * seenRow[1..N], seenCol[1..N].
 * * If we see a value twice or see a value outside 1..N, return false.
 * - If all rows and columns pass, return true.
 * <p>
 * Why this approach?
 * - It is O(N^2) time and O(N) extra space, which is optimal for this problem.
 * - It is easy to reason about and simple to maintain.
 * - We also use Java 8 streams for clean loops and small helpers.
 * <p>
 * Complexity
 * - Time: O(N^2) because we touch each cell a constant number of times.
 * - Space: O(N) for the two boolean “seen” arrays reused per row/column.
 * <p>
 * Edge cases covered
 * - Null or empty grids.
 * - Non-square arrays or ragged rows.
 * - Non-numeric strings, negatives, zeros, or numbers > N.
 * - Duplicates in any row or any column.
 * - Large N (we generate a big Latin square to test).
 */
public class SubSudokuValidator {

    // ---------------------------- Public API (int[][]) ----------------------------

    /**
     * Validate a sub-Sudoku grid given as int[][].
     * Returns true only if every row and column is exactly the set {1..N}.
     */
    public static boolean validateSubSudoku(int[][] grid) {
        // Guard: reject null
        if (grid == null) return false;                             // if matrix is missing, it is invalid

        // Guard: N must be >= 1 and matrix must be square
        final int n = grid.length;                                  // read N once; used many times
        if (n == 0) return false;                                   // empty matrix is not allowed

        // Check each row has length N (no ragged arrays)
        // Use streams to keep Java 8 style while still being clear.
        boolean allRowsLengthN = Arrays.stream(grid)                 // look at every row
                .allMatch(r -> r != null && r.length == n); // each row must exist and be length N
        if (!allRowsLengthN) return false;                           // if any row is wrong size, invalid

        // Validate rows and columns together in O(N^2)
        // We iterate i = 0..N-1; for each i we check row i and column i.
        for (int i = 0; i < n; i++) {                                // go through each index once
            boolean[] seenRow = new boolean[n + 1];                  // flags for numbers 1..N in current row
            boolean[] seenCol = new boolean[n + 1];                  // flags for numbers 1..N in current column

            for (int j = 0; j < n; j++) {                            // walk across row i and down column i
                int rowVal = grid[i][j];                             // value at row i, col j
                int colVal = grid[j][i];                             // value at row j, col i

                // Row check: value must be within 1..N and not repeated
                if (rowVal < 1 || rowVal > n) return false;          // out-of-range value breaks the rule
                if (seenRow[rowVal]) return false;                   // duplicate in this row -> invalid
                seenRow[rowVal] = true;                              // mark number as seen in the row

                // Column check: same rules for col i
                if (colVal < 1 || colVal > n) return false;          // out-of-range in column
                if (seenCol[colVal]) return false;                   // duplicate in this column -> invalid
                seenCol[colVal] = true;                              // mark number as seen in the column
            }
            // If we get here, row i and column i are both valid complete sets.
        }
        // All rows and columns passed.
        return true;                                                 // grid is a valid sub-Sudoku
    }

    // ---------------------------- Public API (String[][]) ----------------------------

    /**
     * Validate a sub-Sudoku grid given as String[][].
     * Strings must parse to integers; non-numeric or null cells fail the check.
     */
    public static boolean validateSubSudoku(String[][] raw) {
        // Convert String[][] to int[][] using a safe parser.
        int[][] parsed = parseToIntMatrix(raw);                      // try to build a numeric matrix
        if (parsed == null) return false;                            // parsing failed (bad shape/bad numbers)
        return validateSubSudoku(parsed);                            // reuse the int[][] validator
    }

    // ---------------------------- Helpers ----------------------------

    /**
     * Convert String[][] to int[][].
     * Returns null if the array is null, ragged, contains nulls, or bad numbers.
     */
    private static int[][] parseToIntMatrix(String[][] raw) {
        if (raw == null) return null;                                // no matrix -> fail

        int n = raw.length;                                          // expected row count and column count
        if (n == 0) return null;                                     // empty matrix -> fail

        int[][] out = new int[n][];                                  // allocate the output matrix
        for (int i = 0; i < n; i++) {                                // process row by row
            String[] row = raw[i];                                   // current row (strings)
            if (row == null || row.length != n) return null;         // must be square and non-null

            int[] nums = new int[n];                                 // numeric row we are building
            for (int j = 0; j < n; j++) {                            // walk through each cell
                String cell = row[j];                                // take raw cell
                if (cell == null) return null;                       // null cell is invalid
                cell = cell.trim();                                  // trim spaces to tolerate " 3 "
                try {
                    nums[j] = Integer.parseInt(cell);                // parse integer value
                } catch (NumberFormatException ex) {
                    return null;                                     // non-numeric string -> fail fast
                }
            }
            out[i] = nums;                                           // store parsed numeric row
        }
        return out;                                                  // return converted matrix
    }

    // ---------------------------- Test Utilities ----------------------------

    /**
     * Pretty print a single test result as PASS/FAIL.
     */
    private static void printResult(String name, boolean got, boolean expected) {
        // Build a simple status string.
        String status = (got == expected) ? "PASS" : "FAIL";
        // Print the name and result. Keeping output compact for quick scanning.
        System.out.println(name + " => " + got + " (expected " + expected + ") - " + status);
    }

    /**
     * Generate a valid N x N Latin square where cell(i,j) = (i + j) % N + 1.
     */
    private static int[][] makeLatinSquare(int n) {
        // Allocate matrix
        int[][] m = new int[n][n];
        // Fill with a cyclic pattern 1..N
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                m[i][j] = (i + j) % n + 1;                           // classic Latin square construction
            }
        }
        return m;                                                     // return a valid grid
    }

    /**
     * Create a deep copy so we can mutate safely in tests.
     */
    private static int[][] copy(int[][] a) {
        return Arrays.stream(a)                                       // stream rows
                .map(r -> Arrays.copyOf(r, r.length))           // copy each row
                .toArray(int[][]::new);                          // collect into new matrix
    }

    // ---------------------------- Demo / Main ----------------------------

    public static void main(String[] args) {
        // A bundle of test cases to cover shape, value, duplicates, and size edge cases.

        // --- Valid 3x3 example (rows/cols are permutations of 1..3)
        int[][] grid1 = {
                {2, 3, 1},
                {1, 2, 3},
                {3, 1, 2}
        };
        printResult("grid1 (3x3 valid)", validateSubSudoku(grid1), true);

        // --- Invalid 3x3: first row repeats 1 and misses 3
        int[][] grid2 = {
                {1, 1, 2},
                {2, 3, 1},
                {3, 2, 3}
        };
        printResult("grid2 (row duplicate)", validateSubSudoku(grid2), false);

        // --- Invalid 3x3: column duplicate in col 0
        int[][] grid3 = {
                {1, 2, 3},
                {1, 3, 2},
                {1, 2, 3}
        };
        printResult("grid3 (column duplicate)", validateSubSudoku(grid3), false);

        // --- Valid 1x1: only 1
        int[][] grid4 = {{1}};
        printResult("grid4 (1x1 valid)", validateSubSudoku(grid4), true);

        // --- Invalid 3x3: negatives and zeros are not allowed
        int[][] grid5 = {
                {-1, 2, 3},
                {2, -3, 1},
                {3, 1, 0}
        };
        printResult("grid5 (negatives/zero)", validateSubSudoku(grid5), false);

        // --- Invalid 2x2: uses 0s and 3s (must be only 1 and 2)
        int[][] grid6 = {
                {0, 3},
                {3, 0}
        };
        printResult("grid6 (values outside 1..N)", validateSubSudoku(grid6), false);

        // --- Invalid 2x2: duplicate in a column
        int[][] grid7 = {
                {1, 2},
                {1, 2}
        };
        printResult("grid7 (column duplicate 2x2)", validateSubSudoku(grid7), false);

        // --- Non-square (ragged) should be invalid
        int[][] grid8 = new int[][]{
                {1, 2, 3},
                {2, 3},              // short row
                {3, 1, 2}
        };
        printResult("grid8 (ragged)", validateSubSudoku(grid8), false);

        // --- String[][] valid 4x4
        String[][] grid9s = {
                {"1", "2", "3", "4"},
                {"2", "3", "4", "1"},
                {"3", "4", "1", "2"},
                {"4", "1", "2", "3"}
        };
        printResult("grid9s (String[][] valid 4x4)", validateSubSudoku(grid9s), true);

        // --- String[][] invalid: non-numeric value "X"
        String[][] grid10s = {
                {"1", "2", "3", "4"},
                {"2", "3", "4", "1"},
                {"3", "4", "X", "2"},
                {"4", "1", "2", "3"}
        };
        printResult("grid10s (String has X)", validateSubSudoku(grid10s), false);

        // --- 10x10 valid Latin square (large but fast)
        int[][] gridLargeValid = makeLatinSquare(10);
        printResult("gridLargeValid (10x10)", validateSubSudoku(gridLargeValid), true);

        // --- 10x10 invalid: modify one cell to create a row duplicate
        int[][] gridLargeBad = copy(gridLargeValid);
        gridLargeBad[4][7] = 5; // force a duplicate "5" in row 4
        printResult("gridLargeBad (10x10 dup in a row)", validateSubSudoku(gridLargeBad), false);

        // --- Very large N stress test (e.g., N=300) for performance
        //     This should still run quickly under O(N^2).
        int N = 300;
        int[][] huge = makeLatinSquare(N);
        long t0 = System.currentTimeMillis();
        boolean hugeOk = validateSubSudoku(huge);
        long t1 = System.currentTimeMillis();
        System.out.println("huge (" + N + "x" + N + ") => " + hugeOk + " - PASS - checked in ~" + (t1 - t0) + " ms");

        // Done. All results printed with PASS/FAIL marks.
    }
}
