package com.interview.notes.code.year.y2025.december.microsoft.test6;

import java.util.*;

// Record for Position - Java 16+ feature for immutable data class
record Position(int row, int col, Position previous) {
}

public class MatrixPathFinder {

    // Direction vectors: up, down, left, right movements
    static int[] DR = {-1, 1, 0, 0};  // row changes
    static int[] DC = {0, 0, -1, 1};  // column changes

    // Check if position is valid and safe to visit
    static boolean isSafe(int[][] matrix, boolean[][] visited, int r, int c) {
        // Boundary check: row within limits
        // Boundary check: column within limits
        // Cell is not blocked (0 means blocked)
        // Cell not already visited
        return r >= 0 && r < matrix.length &&
                c >= 0 && c < matrix[0].length &&
                matrix[r][c] != 0 && !visited[r][c];
    }

    // BFS to check if path exists between source and destination
    static boolean isPathExists(int[][] matrix, Position src, Position dest) {
        // Edge case: empty or null matrix
        if (matrix == null || matrix.length == 0) return false;

        // Track visited cells to avoid cycles
        var visited = new boolean[matrix.length][matrix[0].length];

        // Queue for BFS traversal - FIFO order ensures shortest path
        Queue<Position> queue = new LinkedList<>();

        // Start from source position
        queue.offer(src);
        // Mark source as visited
        visited[src.row()][src.col()] = true;

        // Process until queue empty or destination found
        while (!queue.isEmpty()) {
            // Get front element
            var curr = queue.poll();

            // Check if reached destination
            if (curr.row() == dest.row() && curr.col() == dest.col()) {
                return true;  // Path found!
            }

            // Explore all 4 directions
            for (int i = 0; i < 4; i++) {
                // Calculate new position
                int nr = curr.row() + DR[i];
                int nc = curr.col() + DC[i];

                // If safe, add to queue
                if (isSafe(matrix, visited, nr, nc)) {
                    visited[nr][nc] = true;  // Mark visited before adding
                    queue.offer(new Position(nr, nc, curr));  // Link to parent
                }
            }
        }
        return false;  // No path found
    }

    // BFS to get shortest path - returns destination Position with linked path
    static Position getShortestPath(int[][] matrix, Position src, Position dest) {
        // Edge case handling
        if (matrix == null || matrix.length == 0) return null;

        // Visited array to track explored cells
        var visited = new boolean[matrix.length][matrix[0].length];

        // BFS queue
        Queue<Position> queue = new LinkedList<>();

        // Initialize with source
        queue.offer(src);
        visited[src.row()][src.col()] = true;

        // BFS traversal
        while (!queue.isEmpty()) {
            var curr = queue.poll();

            // Destination reached - return with full path linked
            if (curr.row() == dest.row() && curr.col() == dest.col()) {
                return curr;  // Contains backlink to reconstruct path
            }

            // Try all 4 directions
            for (int i = 0; i < 4; i++) {
                int nr = curr.row() + DR[i];
                int nc = curr.col() + DC[i];

                if (isSafe(matrix, visited, nr, nc)) {
                    visited[nr][nc] = true;
                    // Store parent reference for path reconstruction
                    queue.offer(new Position(nr, nc, curr));
                }
            }
        }
        return null;  // No path exists
    }

    // Utility: Extract path as list of positions
    static List<Position> extractPath(Position end) {
        var path = new ArrayList<Position>();
        // Traverse back from destination to source
        while (end != null) {
            path.add(end);
            end = end.previous();
        }
        // Reverse to get source -> destination order
        Collections.reverse(path);
        return path;
    }

    // Utility: Print path nicely
    static String pathToString(Position end) {
        if (end == null) return "No Path";
        var path = extractPath(end);
        // Stream to format each position
        return path.stream()
                .map(p -> "(" + p.row() + "," + p.col() + ")")
                .reduce((a, b) -> a + " -> " + b)
                .orElse("Empty");
    }

    // Find source (3) and destination (2) positions in matrix
    static Position[] findSrcDest(int[][] matrix) {
        Position src = null, dest = null;
        // Scan entire matrix
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 3) src = new Position(i, j, null);
                if (matrix[i][j] == 2) dest = new Position(i, j, null);
            }
        }
        return new Position[]{src, dest};
    }

    // Test helper - checks and prints result
    static void test(String name, boolean actual, boolean expected) {
        // Compare actual vs expected, print PASS/FAIL
        String result = actual == expected ? "✓ PASS" : "✗ FAIL";
        System.out.println(result + " | " + name);
    }

    // Test helper for path length
    static void testPath(String name, Position result, int expectedLen) {
        int actualLen = result == null ? 0 : extractPath(result).size();
        String status = actualLen == expectedLen ? "✓ PASS" : "✗ FAIL";
        System.out.println(status + " | " + name + " | Length: " + actualLen);
        if (result != null) System.out.println("    Path: " + pathToString(result));
    }

    public static void main(String[] args) {
        System.out.println("=== MATRIX PATH FINDER TESTS ===\n");

        // ========== TEST 1: Given Example ==========
        int[][] m1 = {
                {3, 1, 1, 1, 0},
                {1, 1, 0, 0, 0},
                {1, 1, 1, 1, 0},
                {1, 0, 1, 1, 0},
                {1, 1, 1, 1, 0},
                {2, 1, 1, 1, 0}
        };
        var pos1 = findSrcDest(m1);
        test("Test1: Path exists in given example",
                isPathExists(m1, pos1[0], pos1[1]), true);
        testPath("Test1: Shortest path",
                getShortestPath(m1, pos1[0], pos1[1]), 6);

        // ========== TEST 2: No Path Exists ==========
        int[][] m2 = {
                {3, 0, 1, 1},
                {0, 0, 1, 1},
                {1, 1, 0, 0},
                {1, 1, 0, 2}
        };
        var pos2 = findSrcDest(m2);
        test("Test2: No path (blocked)",
                isPathExists(m2, pos2[0], pos2[1]), false);
        testPath("Test2: Shortest path returns null",
                getShortestPath(m2, pos2[0], pos2[1]), 0);

        // ========== TEST 3: Source equals Destination ==========
        int[][] m3 = {{3}};  // Single cell, treat 3 as both src/dest
        var src3 = new Position(0, 0, null);
        test("Test3: Single cell (src=dest)",
                isPathExists(m3, src3, src3), true);

        // ========== TEST 4: Straight Line Path ==========
        int[][] m4 = {
                {3, 1, 1, 1, 2}
        };
        var pos4 = findSrcDest(m4);
        test("Test4: Straight horizontal path",
                isPathExists(m4, pos4[0], pos4[1]), true);
        testPath("Test4: Path length = 5",
                getShortestPath(m4, pos4[0], pos4[1]), 5);

        // ========== TEST 5: Vertical Path ==========
        int[][] m5 = {
                {3},
                {1},
                {1},
                {2}
        };
        var pos5 = findSrcDest(m5);
        test("Test5: Vertical path",
                isPathExists(m5, pos5[0], pos5[1]), true);
        testPath("Test5: Path length = 4",
                getShortestPath(m5, pos5[0], pos5[1]), 4);

        // ========== TEST 6: Multiple Paths - BFS finds shortest ==========
        int[][] m6 = {
                {3, 1, 1, 1},
                {0, 0, 0, 1},
                {2, 1, 1, 1}
        };
        var pos6 = findSrcDest(m6);
        test("Test6: Multiple paths exist",
                isPathExists(m6, pos6[0], pos6[1]), true);
        testPath("Test6: Shortest path (around obstacle)",
                getShortestPath(m6, pos6[0], pos6[1]), 8);

        // ========== TEST 7: Large Matrix Performance ==========
        System.out.println("\n--- Large Matrix Tests ---");
        int size = 500;  // 500x500 matrix
        int[][] large = generateLargeMatrix(size);
        var posL = new Position[]{new Position(0, 0, null),
                new Position(size - 1, size - 1, null)};

        long start = System.currentTimeMillis();
        boolean exists = isPathExists(large, posL[0], posL[1]);
        long time1 = System.currentTimeMillis() - start;
        test("Test7: Large " + size + "x" + size + " path exists", exists, true);
        System.out.println("    Time: " + time1 + "ms");

        start = System.currentTimeMillis();
        var pathL = getShortestPath(large, posL[0], posL[1]);
        long time2 = System.currentTimeMillis() - start;
        System.out.println("    Shortest path found in: " + time2 + "ms");
        System.out.println("    Path length: " + (pathL != null ? extractPath(pathL).size() : 0));

        // ========== TEST 8: Edge - All Blocked ==========
        int[][] m8 = {
                {3, 0, 0},
                {0, 0, 0},
                {0, 0, 2}
        };
        var pos8 = findSrcDest(m8);
        test("Test8: All paths blocked",
                isPathExists(m8, pos8[0], pos8[1]), false);

        // ========== TEST 9: Maze Pattern ==========
        int[][] m9 = {
                {3, 1, 0, 1, 1},
                {0, 1, 0, 1, 0},
                {0, 1, 1, 1, 0},
                {0, 0, 0, 1, 0},
                {2, 1, 1, 1, 0}
        };
        var pos9 = findSrcDest(m9);
        test("Test9: Maze path exists",
                isPathExists(m9, pos9[0], pos9[1]), true);
        testPath("Test9: Maze shortest path",
                getShortestPath(m9, pos9[0], pos9[1]), 9);

        // ========== TEST 10: Adjacent Source/Dest ==========
        int[][] m10 = {
                {3, 2}
        };
        var pos10 = findSrcDest(m10);
        test("Test10: Adjacent cells",
                isPathExists(m10, pos10[0], pos10[1]), true);
        testPath("Test10: Path length = 2",
                getShortestPath(m10, pos10[0], pos10[1]), 2);

        System.out.println("\n=== ALL TESTS COMPLETED ===");
    }

    // Generate large test matrix with clear path
    static int[][] generateLargeMatrix(int n) {
        int[][] m = new int[n][n];
        // Fill with 1s (traversable)
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                m[i][j] = 1;
            }
        }
        // Set source and destination
        m[0][0] = 3;        // Source at top-left
        m[n - 1][n - 1] = 2;    // Destination at bottom-right
        // Add some random obstacles (30% blocked)
        var rand = new Random(42);  // Fixed seed for reproducibility
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (m[i][j] == 1 && rand.nextDouble() < 0.3) {
                    m[i][j] = 0;
                }
            }
        }
        // Ensure path exists by clearing diagonal
        for (int i = 0; i < n; i++) {
            if (m[i][i] == 0) m[i][i] = 1;
            if (i < n - 1 && m[i][i + 1] == 0) m[i][i + 1] = 1;
        }
        return m;
    }
}