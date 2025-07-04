package com.interview.notes.code.year.y2025.June.OCI.test4;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class SearchInSortedMatrix {

    /**
     * Searches for target in a row- and column- sorted matrix.
     */
    public static boolean search(int[][] matrix, int target) {
        // handle empty matrix
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;                                    // no data
        }
        int m = matrix.length;                               // number of rows
        int n = matrix[0].length;                            // number of cols
        int row = 0, col = n - 1;                            // start at top-right

        // iterate until we run out of bounds
        while (row < m && col >= 0) {
            int val = matrix[row][col];                      // current element
            if (val == target) {
                return true;                                 // found it
            }
            if (val > target) {
                col--;                                       // drop this column
            } else {
                row++;                                       // drop this row
            }
        }
        return false;                                        // not found
    }

    public static boolean searchFromBottomLeft(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;                          // empty check
        }
        int m = matrix.length;                     // rows
        int n = matrix[0].length;                  // cols
        int row = m - 1, col = 0;                  // bottom-left

        while (row >= 0 && col < n) {
            int val = matrix[row][col];            // current element
            if (val == target) {
                return true;                       // found it
            }
            if (val > target) {
                row--;                             // go up to find smaller
            } else {
                col++;                             // go right to find larger
            }
        }
        return false;                              // not found
    }

    /**
     * Simple main method to test multiple cases.
     */
    public static void main(String[] args) {
        List<Object[]> tests = Arrays.asList(
                new Object[]{new int[][]{{1, 2, 3}, {3, 4, 5}, {7, 8, 9}}, 5, true},
                new Object[]{new int[][]{{1, 2, 3}, {3, 4, 5}, {7, 8, 9}}, 10, false},
                new Object[]{new int[][]{{}}, 1, false},
                new Object[]{new int[][]{{-5, -4, 0}, {-3, 1, 2}}, 1, true},
                new Object[]{
                        // large 10000×1 matrix 0…9999
                        IntStream.range(0, 10000).mapToObj(i -> new int[]{i})
                                .toArray(int[][]::new),
                        9999, true
                },
                new Object[]{
                        IntStream.range(0, 10000).mapToObj(i -> new int[]{i})
                                .toArray(int[][]::new),
                        -1, false
                }
        );

        for (int i = 0; i < tests.size(); i++) {
            int[][] mat = (int[][]) tests.get(i)[0];
            int tgt = (int) tests.get(i)[1];
            boolean exp = (boolean) tests.get(i)[2];
            boolean res = search(mat, tgt);

            // determine if test passed
            boolean ok = (res == exp);
            System.out.printf("Test %2d (target=%5d): %s%n",
                    i + 1,
                    tgt,
                    ok ? "PASS" : "FAIL");
        }
    }
}