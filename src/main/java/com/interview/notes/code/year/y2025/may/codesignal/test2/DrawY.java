package com.interview.notes.code.year.y2025.may.codesignal.test2;

import java.util.*;
import java.util.stream.*;

//100% WORKING
/**
 * Draw-Y — minimum changes to form the letter Y in an n×n matrix.
 *
 *  ▸ Works for odd 3 ≤ n ≤ 99, values 0 .. 2
 *  ▸ Uses only plain main() – no external test libraries
 *  ▸ Java 8 Stream API where it makes sense
 */
public class DrawY {

    /** -------- core algorithm -------- */
    public static int solution(int[][] matrix) {
        int n = matrix.length;                 // matrix is n × n and n is odd
        int c = n / 2;                         // index of the middle row / column
        boolean[][] isY = new boolean[n][n];   // mark all cells that belong to the Y

        /* mark the two upper diagonals from the top to the centre */
        for (int i = 0; i <= c; i++) {
            isY[i][i] = true;                  // left arm
            isY[i][n - 1 - i] = true;          // right arm
        }
        /* mark the vertical stem (centre column from centre row downwards) */
        for (int i = c; i < n; i++) {
            isY[i][c] = true;
        }

        /* the Y can be drawn with any value 0/1/2 and the background must be a different one.
           Enumerate all 6 (letter, background) pairs and take the minimum changes.            */
        int minChanges = Integer.MAX_VALUE;
        int[] values = {0, 1, 2};
        for (int yVal : values) {
            for (int bgVal : values) {
                if (yVal == bgVal) continue;   // must differ
                int changes = 0;

                /* walk through every cell and count mismatches */
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        int want = isY[i][j] ? yVal : bgVal;      // ideal value for this cell
                        if (matrix[i][j] != want) changes++;      // needs change
                    }
                }
                minChanges = Math.min(minChanges, changes);       // keep best so far
            }
        }
        return minChanges;
    }

    /** ----------- small helper to build matrices quickly ----------- */
    private static int[][] of(int... rowMajor) {
        int n = (int) Math.sqrt(rowMajor.length);                 // works because n×n
        int[][] m = new int[n][n];
        for (int i = 0; i < rowMajor.length; i++) {
            m[i / n][i % n] = rowMajor[i];
        }
        return m;
    }

    /** -------------- Test harness – simple & self-explaining -------------- */
    public static void main(String[] args) {

        /* === predefined test cases with expected answers === */
        List<TestCase> cases = Arrays.asList(
            new TestCase(of(   // Example 1 from the statement
                1,0,2,
                1,2,0,
                0,2,0
            ), 2, "Example 1"),

            new TestCase(new int[][]{           // Example 2 from the statement
                {2,0,0,0,2},
                {1,2,1,2,0},
                {0,1,2,1,0},
                {0,0,2,1,1},
                {1,1,2,1,1}
            }, 8, "Example 2"),

            new TestCase(of(   // Already a perfect Y – should need 0 changes
                1,0,1,
                0,1,0,
                0,1,0
            ), 0, "Perfect Y"),

            new TestCase(of(   // All zeros (3×3) – needs 4 changes as explained
                0,0,0,
                0,0,0,
                0,0,0
            ), 4, "All zeros")
        );

        /* run & report */
        cases.forEach(tc -> {
            int got = solution(tc.matrix);
            System.out.printf("%-12s: expected=%d got=%d → %s%n",
                    tc.name, tc.expected, got, (got == tc.expected ? "PASS" : "FAIL"));
        });

        /* === large random test to show it works within limits === */
        int n = 99;                                                 // largest allowed (and odd)
        int[][] big = new int[n][n];
        Random rnd = new Random(42);                                // fixed seed → reproducible
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                big[i][j] = rnd.nextInt(3);                         // 0, 1 or 2
            }
        }
        long start = System.nanoTime();
        int changes = solution(big);
        long timeMs = (System.nanoTime() - start) / 1_000_000;
        System.out.printf("Large 99×99 matrix: answer=%d (computed in %d ms)%n",
                changes, timeMs);
    }

    /** tiny holder */
    private static class TestCase {
        int[][] matrix;
        int expected;
        String name;
        TestCase(int[][] m, int exp, String name) { this.matrix = m; this.expected = exp; this.name = name; }
    }
}
