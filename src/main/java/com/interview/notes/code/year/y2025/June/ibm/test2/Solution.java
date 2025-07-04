package com.interview.notes.code.year.y2025.June.ibm.test2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class Solution {

    /**
     * Enforces strictly increasing scores down each column by only increasing cells.
     * Returns the total cost of all increases.
     */
    public static long getMinimumCost(List<List<Integer>> grid) {
        int n = grid.size();
        if (n == 0) return 0L;
        int m = grid.get(0).size();

        long totalCost = 0L;
        // prev[j] will hold the adjusted score of grid[row-1][j]
        long[] prev = new long[m];
        // initialize prev to the first row
        for (int j = 0; j < m; j++) {
            prev[j] = grid.get(0).get(j);
        }

        // for each subsequent row
        for (int i = 1; i < n; i++) {
            List<Integer> row = grid.get(i);
            for (int j = 0; j < m; j++) {
                long cur = row.get(j);
                // if cur <= prev[j], we must bump it to prev[j]+1
                if (cur <= prev[j]) {
                    long needed = prev[j] + 1;
                    totalCost += needed - cur;
                    cur = needed;
                }
                prev[j] = cur;
            }
        }

        return totalCost;
    }

    /**
     * Simple main method – runs sample + edge + large tests and prints PASS/FAIL
     */
    public static void main(String[] args) {
        int passed = 0, total = 0;

        // Sample Case 0
        {
            total++;
            List<List<Integer>> grid = Arrays.asList(
                    Arrays.asList(2, 4, 6),
                    Arrays.asList(4, 2, 7),
                    Arrays.asList(6, 4, 7)
            );
            long expected = 6;
            long actual = getMinimumCost(grid);
            if (actual == expected) {
                System.out.println("Test 0: PASS");
                passed++;
            } else {
                System.out.format("Test 0: FAIL (expected=%d, actual=%d)%n", expected, actual);
            }
        }

        // Sample Case 1
        {
            total++;
            List<List<Integer>> grid = Arrays.asList(
                    Arrays.asList(2, 4, 6, 2, 9),
                    Arrays.asList(2, 8, 10, 2, 7),
                    Arrays.asList(8, 10, 11, 8, 2)
            );
            long expected = 14;
            long actual = getMinimumCost(grid);
            if (actual == expected) {
                System.out.println("Test 1: PASS");
                passed++;
            } else {
                System.out.format("Test 1: FAIL (expected=%d, actual=%d)%n", expected, actual);
            }
        }

        // Extra example from prompt (n=3, m=2 → output 9)
        {
            total++;
            List<List<Integer>> grid = Arrays.asList(
                    Arrays.asList(2, 5),
                    Arrays.asList(7, 4),
                    Arrays.asList(3, 5)
            );
            long expected = 9;
            long actual = getMinimumCost(grid);
            if (actual == expected) {
                System.out.println("Test 2: PASS");
                passed++;
            } else {
                System.out.format("Test 2: FAIL (expected=%d, actual=%d)%n", expected, actual);
            }
        }

        // Edge: single row → cost = 0
        {
            total++;
            List<List<Integer>> grid = Collections.singletonList(
                    Arrays.asList(100, 200, 300)
            );
            long expected = 0;
            long actual = getMinimumCost(grid);
            if (actual == expected) {
                System.out.println("Test 3: PASS");
                passed++;
            } else {
                System.out.format("Test 3: FAIL (expected=%d, actual=%d)%n", expected, actual);
            }
        }

        // Large-data sanity check (n*m = 10⁶)
        {
            total++;
            final int n = 1000, m = 1000;
            List<List<Integer>> grid = new ArrayList<>(n);
            // all zeros → column j needs 0,1,2,... → cost_per_column = n*(n-1)/2
            IntStream.range(0, n).forEach(i -> {
                Integer[] row = new Integer[m];
                Arrays.fill(row, 0);
                grid.add(Arrays.asList(row));
            });
            long expectedPerCol = ((long) n * (n - 1)) / 2;
            long expected = expectedPerCol * m;
            long actual = getMinimumCost(grid);
            if (actual == expected) {
                System.out.println("Test 4 (large): PASS");
                passed++;
            } else {
                System.out.format("Test 4 (large): FAIL (expected=%d, actual=%d)%n", expected, actual);
            }
        }

        // summary
        System.out.format("Passed %d of %d tests.%n", passed, total);
    }
}