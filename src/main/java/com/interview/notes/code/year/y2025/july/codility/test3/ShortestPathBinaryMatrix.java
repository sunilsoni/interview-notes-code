package com.interview.notes.code.year.y2025.july.codility.test3;

import java.util.*;
import java.util.stream.IntStream;

public class ShortestPathBinaryMatrix {

    /**
     * Returns the number of moves in the shortest clear path
     * from (0,0) to (n-1,n-1) in an n×n binary grid,
     * moving 8-directionally but disallowing diagonal cuts.
     * Returns -1 if no path exists.
     */
    public static int shortestPathBinaryMatrix(int[][] grid) {
        int n = grid.length;
        // blocked start or end?
        if (grid[0][0] != 0 || grid[n-1][n-1] != 0) return -1;
        // single cell case
        if (n == 1) return 1;

        boolean[][] seen = new boolean[n][n];
        Deque<int[]> q = new ArrayDeque<>();
        // {row, col, movesSoFar}
        q.add(new int[]{0, 0, 0});
        seen[0][0] = true;

        int[][] dirs = {
            {1,0}, {-1,0}, {0,1}, {0,-1},
            {1,1}, {1,-1}, {-1,1}, {-1,-1}
        };

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0], c = cur[1], d = cur[2];
            if (r == n-1 && c == n-1) {
                // d is exactly the number of moves taken
                return d;
            }
            for (int[] dir : dirs) {
                int dr = dir[0], dc = dir[1];
                int nr = r + dr, nc = c + dc;
                // in bounds & not seen & clear
                if (nr < 0 || nr >= n || nc < 0 || nc >= n) continue;
                if (seen[nr][nc] || grid[nr][nc] != 0) continue;
                // if diagonal, prevent “cutting the corner”
                if (dr != 0 && dc != 0) {
                    if (grid[r + dr][c] != 0 || grid[r][c + dc] != 0) {
                        continue;
                    }
                }
                seen[nr][nc] = true;
                q.add(new int[]{nr, nc, d + 1});
            }
        }

        return -1;
    }

    private static class TestCase {
        final int[][] grid;
        final int expected;
        TestCase(int[][] grid, int expected) {
            this.grid = grid;
            this.expected = expected;
        }
    }

    public static void main(String[] args) {
        List<TestCase> tests = Arrays.asList(
            // Example 1: 4 moves
            new TestCase(
                new int[][] {
                    {0,0,0},
                    {1,1,0},
                    {1,1,0}
                },
                4
            ),
            // Example 2: no path → -1
            new TestCase(
                new int[][] {
                    {0,1},
                    {1,0}
                },
                -1
            ),
            // start blocked → -1
            new TestCase(
                new int[][] {
                    {1,0,0},
                    {0,0,0},
                    {0,0,0}
                },
                -1
            ),
            // single 0 cell → 1
            new TestCase(
                new int[][] {
                    {0}
                },
                1
            ),
            // single 1 cell → -1
            new TestCase(
                new int[][] {
                    {1}
                },
                -1
            )
        );

        System.out.println("Running small test cases:");
        int pass = 0;
        for (int i = 0; i < tests.size(); i++) {
            TestCase t = tests.get(i);
            int res = shortestPathBinaryMatrix(t.grid);
            boolean ok = res == t.expected;
            System.out.printf(" Test %d: %s (exp=%d, got=%d)%n",
                i+1, ok ? "PASS" : "FAIL", t.expected, res);
            if (ok) pass++;
        }
        System.out.printf("Passed %d/%d small tests.%n%n",
            pass, tests.size());

        // Large-grid stress test (all zeros)
        int n = 500;
        int[][] large = IntStream.range(0, n)
            .mapToObj(i -> IntStream.generate(() -> 0).limit(n).toArray())
            .toArray(int[][]::new);
        int expectedLarge = n - 1;  // diagonal moves count
        System.out.printf("Running large %dx%d test...%n", n, n);
        long start = System.currentTimeMillis();
        int gotLarge = shortestPathBinaryMatrix(large);
        long took = System.currentTimeMillis() - start;
        boolean okL = gotLarge == expectedLarge;
        System.out.printf(" Large Test: %s (exp=%d, got=%d) in %dms%n",
            okL ? "PASS" : "FAIL", expectedLarge, gotLarge, took);
    }
}