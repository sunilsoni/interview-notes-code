package com.interview.notes.code.year.y2025.november.karat.test2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * NonogramValidatorDemo
 * Validates a nonogram solution:
 * - 'B' means black cell, 'W' means white cell.
 * - Row/column clues list the lengths of contiguous 'B' runs, in order.
 * - A grid is valid if every row AND every column exactly matches its clue.
 *
 * Time:  O(R * C)  — we scan each cell at most twice (once by row, once by col).
 * Space: O(max(R, C)) — temporary buffers while extracting runs.
 */
public class NonogramValidatorDemo {

    /* ----------------------- Public API ----------------------- */

    /** Validate using primitive int[][] clues (as in your screenshots). */
    public static boolean validateNonogram(char[][] grid, int[][] rowClues, int[][] colClues) {
        // reject missing grid or clues early
        if (grid == null || rowClues == null || colClues == null) return false; // inputs must exist

        int rows = grid.length;                                                 // number of rows
        if (rows == 0) return false;                                            // empty grid is not valid
        int cols = grid[0].length;                                              // number of columns (assume rectangular)

        if (rowClues.length != rows || colClues.length != cols) return false;   // clues must match sizes

        // check each row against its clue
        for (int r = 0; r < rows; r++) {                                        // iterate all rows
            int[] runs = extractRunsRow(grid[r]);                               // get contiguous 'B' lengths for this row
            if (!Arrays.equals(runs, rowClues[r])) return false;                // mismatch -> invalid
        }

        // check each column against its clue
        for (int c = 0; c < cols; c++) {                                        // iterate all columns
            int[] runs = extractRunsCol(grid, c);                               // get contiguous 'B' lengths for this column
            if (!Arrays.equals(runs, colClues[c])) return false;                // mismatch -> invalid
        }

        return true;                                                            // all rows and columns match
    }

    /* ----------------------- Helpers ----------------------- */

    /** Extract run lengths of 'B' for one row (char[]) and return as int[]. */
    private static int[] extractRunsRow(char[] row) {
        List<Integer> list = new ArrayList<>();                                 // dynamic list to collect run lengths
        int run = 0;                                                            // current run length
        for (char ch : row) {                                                   // scan left -> right
            if (ch == 'B') {                                                    // black extends a run
                run++;                                                          // increment count
            } else {                                                            // white ends a run
                if (run > 0) { list.add(run); run = 0; }                        // store finished run
            }
        }
        if (run > 0) list.add(run);                                             // flush trailing run at row end
        return list.stream().mapToInt(Integer::intValue).toArray();             // convert List<Integer> -> int[]
    }

    /** Extract run lengths of 'B' for one column index and return as int[]. */
    private static int[] extractRunsCol(char[][] grid, int c) {
        List<Integer> list = new ArrayList<>();                                 // dynamic list to collect run lengths
        int run = 0;                                                            // current run length
        for (int r = 0; r < grid.length; r++) {                                 // scan top -> bottom
            char ch = grid[r][c];                                               // cell at (r,c)
            if (ch == 'B') {                                                    // black extends a run
                run++;                                                          // increment count
            } else {                                                            // white ends a run
                if (run > 0) { list.add(run); run = 0; }                        // store finished run
            }
        }
        if (run > 0) list.add(run);                                             // flush trailing run at column end
        return list.stream().mapToInt(Integer::intValue).toArray();             // convert List<Integer> -> int[]
    }

    /** Tiny utility to print PASS/FAIL lines. */
    private static void check(String name, boolean got, boolean expected) {
        String status = (got == expected) ? "PASS" : "FAIL";                    // compare outputs
        System.out.println(name + " => " + got + "  (expected " + expected + ")  - " + status); // show result
    }

    /* ----------------------- Demo: examples & tests ----------------------- */

    public static void main(String[] args) {

        /* ===== Example set 1: a small 4x4 from the explanation ===== */
        char[][] matrix1 = {                                                     // 4 rows x 4 cols
                {'W','W','W','W'},
                {'B','W','W','B'},
                {'B','W','B','W'},
                {'B','B','W','W'}
        };

        // correct clues for matrix1 (rows then columns)
        int[][] rows1_1    = { {}, {1,1}, {1,1}, {2} };                          // runs per row
        int[][] columns1_1 = { {3}, {1}, {1}, {1} };                              // runs per column
        check("validateNonogram(matrix1, rows1_1, columns1_1)",
                validateNonogram(matrix1, rows1_1, columns1_1), true);           // should be True

        // wrong clues for the same matrix (to get False)
        int[][] rows1_2    = { {}, {1}, {1}, {1} };                               // rows not matching
        int[][] columns1_2 = { {2}, {1}, {2}, {1} };                              // columns not matching
        check("validateNonogram(matrix1, rows1_2, columns1_2)",
                validateNonogram(matrix1, rows1_2, columns1_2), false);          // should be False


        /* ===== Example set 2: your matrix2 with six clue-variants ===== */
        char[][] matrix2 = {                                                     // 4 rows x 2 cols
                {'W','W'},
                {'B','B'},
                {'B','B'},
                {'W','B'}
        };

        // Rows are the same in all rows2_* ([], [2], [2], [1])
        int[][] rows2_1 = { {}, {2}, {2}, {1} };                                 // valid row clues
        int[][] rows2_2 = { {}, {2}, {2}, {1} };                                 // same
        int[][] rows2_3 = { {}, {}, {}, {} };                                    // empty rows (wrong)
        int[][] rows2_4 = { {}, {2}, {2}, {1} };                                 // same
        int[][] rows2_5 = { {}, {2}, {2}, {1} };                                 // same
        int[][] rows2_6 = { {}, {2}, {2}, {1} };                                 // same

        // Column variants from your screenshot
        int[][] columns2_1 = { {1,1}, {3} };                                     // expects W,B,W,B / BBB
        int[][] columns2_2 = { {3}, {3} };                                       // expects BBB / BBB
        int[][] columns2_3 = { {}, {} };                                         // expects no blacks
        int[][] columns2_4 = { {2,1}, {3} };                                     // expects BB,W,B / BBB
        int[][] columns2_5 = { {2}, {3} };                                       // the correct one for matrix2
        int[][] columns2_6 = { {2}, {1,1} };                                     // expects BB / B,W,B

        // Expected results (from the list in your images)
        check("validateNonogram(matrix2, rows2_1, columns2_1)",
                validateNonogram(matrix2, rows2_1, columns2_1), false);
        check("validateNonogram(matrix2, rows2_2, columns2_2)",
                validateNonogram(matrix2, rows2_2, columns2_2), false);
        check("validateNonogram(matrix2, rows2_3, columns2_3)",
                validateNonogram(matrix2, rows2_3, columns2_3), false);
        check("validateNonogram(matrix2, rows2_4, columns2_4)",
                validateNonogram(matrix2, rows2_4, columns2_4), false);
        check("validateNonogram(matrix2, rows2_5, columns2_5)",
                validateNonogram(matrix2, rows2_5, columns2_5), true);
        check("validateNonogram(matrix2, rows2_6, columns2_6)",
                validateNonogram(matrix2, rows2_6, columns2_6), false);


        /* ===== Example set 3: your single-row matrix3 with two clue-sets ===== */
        char[][] matrix3 = {                                                     // 1 row x 6 cols
                {'B','W','B','B','W','B'}
        };

        // rows3_1 matches the row; rows3_2 is wrong
        int[][] rows3_1    = { {1,2,1} };                                        // B | BB | B
        int[][] rows3_2    = { {1,2,2} };                                        // wrong: claims final run length 2

        // columns3_* reflect each single cell in the 6 columns
        int[][] columns3_1 = { {1}, {}, {1}, {1}, {}, {1} };                     // B, W, B, B, W, B
        int[][] columns3_2 = { {1}, {}, {1}, {1}, {}, {1} };                     // same columns

        check("validateNonogram(matrix3, rows3_1, columns3_1)",
                validateNonogram(matrix3, rows3_1, columns3_1), true);           // should be True
        check("validateNonogram(matrix3, rows3_2, columns3_2)",
                validateNonogram(matrix3, rows3_2, columns3_2), false);          // should be False
    }
}
