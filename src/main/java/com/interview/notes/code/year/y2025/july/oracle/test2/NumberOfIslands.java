package com.interview.notes.code.year.y2025.july.oracle.test2;

import java.util.*;
import java.util.stream.*;

public class NumberOfIslands {

    // Now includes 8 directions: up, down, left, right, and the four diagonals
    private static final int[][] DIRS = {
        {-1,  0}, // up
        { 1,  0}, // down
        { 0, -1}, // left
        { 0,  1}, // right
        {-1, -1}, // up-left
        {-1,  1}, // up-right
        { 1, -1}, // down-left
        { 1,  1}  // down-right
    };
    
    /**
     * Counts islands treating diagonal neighbors as connected.
     */
    public static int countIslands(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;      // empty → 0
        int rows = grid.length;
        int cols = grid[0].length;
        boolean[][] visited = new boolean[rows][cols];      // mark visited cells
        int count = 0;                                       // island counter
        
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                // land + not yet visited → new island
                if (grid[r][c] == 1 && !visited[r][c]) {
                    count++;
                    dfsMark(grid, visited, r, c, rows, cols);
                }
            }
        }
        return count;
    }
    
    /**
     * DFS from (r,c), marking all 1s in 8 directions.
     */
    private static void dfsMark(int[][] grid, boolean[][] visited,
                                int r, int c, int rows, int cols) {
        // out of bounds, water, or already visited → stop
        if (r < 0 || r >= rows || c < 0 || c >= cols
            || grid[r][c] == 0 || visited[r][c]) {
            return;
        }
        visited[r][c] = true;   // mark this land
        
        // explore all eight ways
        for (int[] d : DIRS) {
            dfsMark(grid, visited, r + d[0], c + d[1], rows, cols);
        }
    }
    
    private static class TestCase {
        String name;
        int[][] grid;
        int expected;
        TestCase(String name, int[][] grid, int expected) {
            this.name = name;
            this.grid = grid;
            this.expected = expected;
        }
    }
    
    /**
     * Simple main that prints PASS/FAIL for each test.
     */
    public static void main(String[] args) {
        List<TestCase> tests = Arrays.asList(
            new TestCase("Example 1 (no diagonal)",
                new int[][] {
                  {1,1,0,0},
                  {1,0,0,1},
                  {1,0,0,1}
                }, 2),
            new TestCase("Example 2 (no diagonal)",
                new int[][] {
                  {0,0,0,1},
                  {1,0,0,0},
                  {1,0,1,1}
                }, 3),
            new TestCase("Diagonal Connect",
                new int[][] {
                  {1,0},
                  {0,1}
                }, 1),  // corners touch diagonally → 1 island
            new TestCase("All Water",
                new int[][] {
                  {0,0,0},
                  {0,0,0}
                }, 0),
            new TestCase("All Land",
                new int[][] {
                  {1,1},
                  {1,1}
                }, 1),
            new TestCase("Empty Grid",
                new int[][] {}, 0),
            new TestCase("Large Water Grid",
                new int[1000][1000], 0)
        );
        
        IntStream.range(0, tests.size()).forEach(i -> {
            TestCase tc = tests.get(i);
            int res = countIslands(tc.grid);
            String status = res == tc.expected ? "PASS" :
                "FAIL (exp=" + tc.expected + ", got=" + res + ")";
            System.out.println(tc.name + ": " + status);
        });
    }
}