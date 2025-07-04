package com.interview.notes.code.year.y2025.June.amazon.test15;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class IslandDetector {
    // findIslands: returns lists of enclosed‐island coordinates
    public static List<List<int[]>> findIslands(char[][] grid) {
        int n = grid.length;                             // total rows
        int m = grid[0].length;                          // total cols
        boolean[][] visited = new boolean[n][m];         // to mark cells we’ve seen
        List<List<int[]>> islands = new ArrayList<>();   // result holder

        // scan every cell
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // start DFS if it’s unvisited land
                if (grid[i][j] == 'L' && !visited[i][j]) {
                    List<int[]> island = new ArrayList<>(); // coords of this island
                    boolean[] touchesBorder = new boolean[1]; // flag array so it’s mutable
                    dfs(grid, visited, i, j, island, touchesBorder);
                    // only keep fully enclosed islands
                    if (!touchesBorder[0]) {
                        islands.add(island);
                    }
                }
            }
        }
        return islands;
    }

    // recursive DFS helper
    private static void dfs(char[][] grid, boolean[][] visited,
                            int i, int j, List<int[]> island, boolean[] touchesBorder) {
        int n = grid.length, m = grid[0].length;
        // stop if out of bounds, water, or visited
        if (i < 0 || i >= n || j < 0 || j >= m
                || grid[i][j] == 'W' || visited[i][j]) {
            return;
        }
        visited[i][j] = true;                           // mark current
        // mark if we touch any border cell
        if (i == 0 || i == n - 1 || j == 0 || j == m - 1) {
            touchesBorder[0] = true;
        }
        island.add(new int[]{i, j});                    // record this coord
        // explore 4 directions
        dfs(grid, visited, i - 1, j, island, touchesBorder); // up
        dfs(grid, visited, i + 1, j, island, touchesBorder); // down
        dfs(grid, visited, i, j - 1, island, touchesBorder); // left
        dfs(grid, visited, i, j + 1, island, touchesBorder); // right
    }

    // simple main method for PASS/FAIL tests and a large‐grid benchmark
    public static void main(String[] args) {
        // define test cases
        List<char[][]> testGrids = Arrays.asList(
                new char[][]{
                        {'L', 'L', 'L', 'L', 'L', 'W', 'W'},
                        {'W', 'W', 'W', 'W', 'W', 'W', 'W'},
                        {'W', 'L', 'L', 'L', 'L', 'W', 'W'},
                        {'W', 'W', 'W', 'L', 'L', 'W', 'W'},
                        {'W', 'L', 'L', 'W', 'L', 'L', 'W'},
                        {'W', 'W', 'W', 'L', 'W', 'W', 'W'}
                },
                new char[][]{
                        {'L', 'L', 'L'},
                        {'L', 'W', 'L'},
                        {'L', 'L', 'L'}
                },
                new char[][]{
                        {'W', 'W', 'W'},
                        {'W', 'W', 'W'},
                        {'W', 'W', 'W'}
                }
        );
        List<String> expected = Arrays.asList("Count=2", "Count=0", "Count=0");

        // run small tests
        IntStream.range(0, testGrids.size()).forEach(i -> {
            List<List<int[]>> isl = findIslands(testGrids.get(i));
            String actual = "Count=" + isl.size();
            String result = actual.equals(expected.get(i)) ? "PASS" : "FAIL";
            System.out.println("Test " + (i + 1) + ": expected "
                    + expected.get(i) + ", got " + actual + " => " + result);
        });

        // large‐grid benchmark (1000×1000 random)
        int size = 1000;
        char[][] large = new char[size][size];
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                large[r][c] = Math.random() < 0.5 ? 'L' : 'W';
            }
        }
        long start = System.currentTimeMillis();
        List<List<int[]>> bigIslands = findIslands(large);
        long ms = System.currentTimeMillis() - start;
        System.out.println("Large grid " + size + "×" + size
                + " → islands=" + bigIslands.size()
                + ", time=" + ms + "ms");
    }
}