package com.interview.notes.code.year.y2025.july.codility.test1;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.stream.IntStream;

public class ShortestPathBinaryMatrix {

    /**
     * Returns the length of the shortest clear path in a binary grid
     * from (0,0) to (n-1,n-1), moving in 8 directions—but
     * forbidding diagonal moves that cut through two blocked neighbors.
     * If no such path exists, returns -1.
     */
    public static int shortestPathBinaryMatrix(int[][] grid) {
        int n = grid.length;
        if (grid[0][0] != 0 || grid[n - 1][n - 1] != 0) return -1;
        if (n == 1) return 1;

        boolean[][] seen = new boolean[n][n];
        Deque<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{0, 0, 1});
        seen[0][0] = true;

        int[][] dirs = {
                {1, 0}, {-1, 0}, {0, 1}, {0, -1},
                {1, 1}, {1, -1}, {-1, 1}, {-1, -1}
        };

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int r = cur[0], c = cur[1], d = cur[2];
            if (r == n - 1 && c == n - 1) return d;

            for (int[] dir : dirs) {
                int dr = dir[0], dc = dir[1];
                int nr = r + dr, nc = c + dc;
                // bounds & clear check
                if (nr < 0 || nr >= n || nc < 0 || nc >= n) continue;
                if (seen[nr][nc] || grid[nr][nc] != 0) continue;

                // if diagonal, ensure we’re not “cutting the corner”
                if (dr != 0 && dc != 0) {
                    if (grid[r + dr][c] != 0 || grid[r][c + dc] != 0) {
                        continue;
                    }
                }

                seen[nr][nc] = true;
                queue.add(new int[]{nr, nc, d + 1});
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        List<TestCase> tests = Arrays.asList(
                // Example 1
                new TestCase(
                        new int[][]{
                                {0, 0, 0},
                                {1, 1, 0},
                                {1, 1, 0}
                        },
                        4
                ),
                // Example 2 (should now be -1)
                new TestCase(
                        new int[][]{
                                {0, 1},
                                {1, 0}
                        },
                        -1
                ),
                // Edge: start blocked
                new TestCase(
                        new int[][]{
                                {1, 0, 0},
                                {0, 0, 0},
                                {0, 0, 0}
                        },
                        -1
                ),
                // Single 0 cell
                new TestCase(
                        new int[][]{
                                {0}
                        },
                        1
                ),
                // Single 1 cell
                new TestCase(
                        new int[][]{
                                {1}
                        },
                        -1
                )
        );

        System.out.println("Running small test cases:");
        int passCount = 0;
        for (int i = 0; i < tests.size(); i++) {
            TestCase t = tests.get(i);
            int result = shortestPathBinaryMatrix(t.grid);
            boolean pass = result == t.expected;
            System.out.printf(" Test %d: %s (expected=%d, got=%d)%n",
                    i + 1, pass ? "PASS" : "FAIL", t.expected, result);
            if (pass) passCount++;
        }
        System.out.printf("Passed %d/%d small tests.%n%n",
                passCount, tests.size());

        // Large-grid performance test
        int n = 500;
        int[][] largeGrid = IntStream.range(0, n)
                .mapToObj(i -> IntStream.generate(() -> 0).limit(n).toArray())
                .toArray(int[][]::new);

        int expectedLarge = n;
        System.out.printf("Running large %dx%d all-zero grid test...%n", n, n);
        long start = System.currentTimeMillis();
        int largeResult = shortestPathBinaryMatrix(largeGrid);
        long elapsed = System.currentTimeMillis() - start;
        boolean largePass = largeResult == expectedLarge;
        System.out.printf(" Large Test: %s (expected=%d, got=%d) in %d ms%n",
                largePass ? "PASS" : "FAIL", expectedLarge, largeResult, elapsed);
    }

    private static class TestCase {
        final int[][] grid;
        final int expected;

        TestCase(int[][] grid, int expected) {
            this.grid = grid;
            this.expected = expected;
        }
    }
}