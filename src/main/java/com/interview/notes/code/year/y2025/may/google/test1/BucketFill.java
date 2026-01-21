package com.interview.notes.code.year.y2025.may.google.test1;

import java.util.*;

/*
Problem Description
Digital graphics tools often make available a “bucket fill” tool that will recolor only adjacent cells. In this problem, you have a modified bucket tool that recolors all adjacent cells (connected horizontally or vertically, but not diagonally) that share the same color.
Given a picture represented as a 2-dimensional array of letters (each letter is a “color”), find the minimum number of bucket-fill strokes required to repaint the entire picture.
In one fill (stroke), you pick a cell, and every cell in that cell’s maximal 4-connected region of the same letter is recolored at once.


Different connected regions of the same letter each require a separate stroke.


You must count how many total connected components exist (across all letters) and return that as the answer.

 */
public class BucketFill {

    /**
     * Computes the minimum number of bucket‐fill strokes needed to repaint
     * the entire picture. Each stroke covers one connected component of identical letters
     * (connection is horizontal or vertical).
     *
     * @param picture A List of Strings, where each String is one row of the picture.
     *                All Strings have the same length w, and picture.size() = h.
     * @return The total number of connected components (the number of strokes).
     */
    public static int strokesRequired(List<String> picture) {
        // 1) Extract height (h) and width (w) from the input List<String>.
        int h = picture.size();                       // Number of rows
        if (h == 0) {
            return 0; // (Although constraints say h >= 1, we guard against an empty list.)
        }
        int w = picture.get(0).length();              // Number of columns (all rows have same length)

        // 2) Convert the List<String> into a 2D char array (grid) using Stream API.
        //    This is purely for convenience, so we can index by grid[r][c].
        //    The call `.map(String::toCharArray)` turns each row‐string into a char[].
        //    Then `toArray(char[][]::new)` collects them into a char[][] of size [h][w].
        char[][] grid = picture.stream()
                .map(String::toCharArray)
                .toArray(char[][]::new);

        // 3) Create a boolean visited array of the same dimensions (h x w),
        //    initialized to false. We'll mark cells as visited as we discover them.
        boolean[][] visited = new boolean[h][w];

        // 4) Directions for exploring 4 orthogonal neighbors (up, down, left, right).
        //    Each pair is (rowOffset, colOffset).
        int[][] directions = {
                {-1, 0},  // move up
                {1, 0},  // move down
                {0, -1},  // move left
                {0, 1}   // move right
        };

        int strokes = 0;  // This will accumulate the number of connected components.

        // 5) Iterate over every cell in the grid.
        for (int r = 0; r < h; r++) {
            for (int c = 0; c < w; c++) {
                // If this cell is not yet visited, we've found a brand‐new component.
                if (!visited[r][c]) {
                    strokes++;  // One more stroke for the newly discovered component.

                    // Perform an iterative DFS flood‐fill from (r, c).
                    // We use a Stack<int[]> where each int[] is a 2‐element array {row, col}.
                    Stack<int[]> stack = new Stack<>();
                    stack.push(new int[]{r, c});

                    // Capture which character this component belongs to.
                    char targetChar = grid[r][c];

                    // Mark the starting cell as visited immediately.
                    visited[r][c] = true;

                    // Standard DFS loop: until stack is empty, pop & explore neighbors.
                    while (!stack.isEmpty()) {
                        int[] cell = stack.pop();   // Get the “current” cell.
                        int row = cell[0];          // row index
                        int col = cell[1];          // col index

                        // Check all 4 orthogonal neighbors
                        for (int[] dir : directions) {
                            int nr = row + dir[0];  // neighbor row
                            int nc = col + dir[1];  // neighbor col

                            // 5a) Is (nr, nc) inside the grid?
                            if (nr < 0 || nr >= h || nc < 0 || nc >= w) {
                                continue;  // out of bounds, skip
                            }

                            // 5b) Have we already visited (nr, nc)? If yes, skip.
                            if (visited[nr][nc]) {
                                continue;
                            }

                            // 5c) Does the neighbor have the same character we started with?
                            if (grid[nr][nc] != targetChar) {
                                continue;
                            }

                            // 5d) If we reach here, (nr, nc) is part of the same component.
                            visited[nr][nc] = true;         // mark as visited
                            stack.push(new int[]{nr, nc}); // add to stack for further expansion
                        }
                    }
                    // when this while‐loop ends, we've marked the entire connected component.
                }
                // if visited[r][c] was true, we simply skip it because it's already part of
                // some previously counted component.
            }
        }

        // 6) We have visited every cell exactly once. 'strokes' is the total components.
        return strokes;
    }

    /**
     * Main method: creates test cases, runs strokesRequired on each,
     * and prints PASS/FAIL along with details.
     */
    public static void main(String[] args) {
        // 1) Build a list of test cases (including sample cases and some extras).
        List<TestCase> tests = new ArrayList<>();

        // --- Sample Case 0 from prompt ---
        // picture = ["aa a b a",  "a b a b a",  "a a a c a"]
        // After filling, letter 'a' has 2 components, 'b' has 2, 'c' has 1 → total = 5.
        tests.add(new TestCase(
                Arrays.asList("aaaba", "ababa", "aaaca"),
                5
        ));

        // --- Sample Case 1 from prompt ---
        // picture = ["bbba", "abba", "acaa", "aaac"]
        // We see 'b' has 1 component, 'a' has 1 component, 'c' has 2 components → total = 4.
        tests.add(new TestCase(
                Arrays.asList("bbba", "abba", "acaa", "aaac"),
                4
        ));

        // --- Example in Description: ["aabba", "aabba", "aaacb"] ---
        // From the prompt diagram, 'a' has 2 disconnected regions, 'b' has 2, 'c' has 1 → total = 5.
        tests.add(new TestCase(
                Arrays.asList("aabba", "aabba", "aaacb"),
                5
        ));

        // --- Minimum size: h = 1, w = 1. Single cell 'c'. Answer = 1. ---
        tests.add(new TestCase(
                Collections.singletonList("c"),
                1
        ));

        // --- Uniform large grid test (stress test) ---
        // Let’s build a 1000 × 100 grid (100,000 cells total), all 'a'. → Only 1 stroke.
        {
            int H = 1000, W = 100;
            StringBuilder rowBuilder = new StringBuilder(W);
            for (int i = 0; i < W; i++) {
                rowBuilder.append('a');
            }
            String rowStr = rowBuilder.toString();

            List<String> largeUniform = new ArrayList<>(H);
            for (int i = 0; i < H; i++) {
                largeUniform.add(rowStr);
            }
            tests.add(new TestCase(largeUniform, 1));
        }

        // --- Checkerboard pattern (very fragmented) ---
        // Build a 10 × 10 grid where letters alternate in a checkerboard of 'a'/'b'.
        // Then every cell is isolated → total = 100 strokes.
        {
            int H = 10, W = 10;
            List<String> checker = new ArrayList<>(H);
            for (int r = 0; r < H; r++) {
                StringBuilder sb = new StringBuilder(W);
                for (int c = 0; c < W; c++) {
                    if (((r + c) & 1) == 0) {
                        sb.append('a');
                    } else {
                        sb.append('b');
                    }
                }
                checker.add(sb.toString());
            }
            tests.add(new TestCase(checker, 100));
        }

        // 2) Run each test case and report PASS/FAIL.
        int passedCount = 0;
        for (int i = 0; i < tests.size(); i++) {
            TestCase tc = tests.get(i);
            int actual = strokesRequired(tc.picture);
            boolean passed = (actual == tc.expected);

            // Print details: test index, PASS/FAIL, expected vs. actual.
            System.out.println("Test #" + i + ": " + (passed ? "PASS" : "FAIL"));
            if (!passed) {
                System.out.println("  Expected = " + tc.expected + ", but got = " + actual);
                // (Optionally, we could print the input for debugging, but that might be large.)
            }
            passedCount += (passed ? 1 : 0);
        }

        // Final summary
        System.out.println();
        System.out.println("Summary: Passed " + passedCount + " out of " + tests.size() + " tests.");
    }

    /**
     * A simple helper class to hold each test case.
     */
    private record TestCase(List<String> picture, int expected) {
    }
}