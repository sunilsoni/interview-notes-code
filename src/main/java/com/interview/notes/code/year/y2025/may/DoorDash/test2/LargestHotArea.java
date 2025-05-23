package com.interview.notes.code.year.y2025.may.DoorDash.test2;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class LargestHotArea {

    private static final int[][] directions = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

    // Main logic
    private static int largestHotArea(int[][] grid) {
        int n = grid.length;
        int id = 2; // Start island IDs from 2 to differentiate from initial 1s and 0s
        Map<Integer, Integer> areaMap = new HashMap<>();

        // Label islands and calculate areas
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    int area = markArea(grid, i, j, id);
                    areaMap.put(id++, area);
                }
            }
        }

        int maxArea = areaMap.values().stream().max(Integer::compare).orElse(0);

        // Optimization: avoid HashSet creation repeatedly by using an array
        boolean[] seen = new boolean[id + 2];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    Arrays.fill(seen, false);
                    int combinedArea = 1; // Include current cell

                    for (int[] d : directions) {
                        int x = i + d[0];
                        int y = j + d[1];
                        if (x >= 0 && x < n && y >= 0 && y < n && grid[x][y] > 1 && !seen[grid[x][y]]) {
                            combinedArea += areaMap.get(grid[x][y]);
                            seen[grid[x][y]] = true;
                        }
                    }

                    maxArea = Math.max(maxArea, combinedArea);
                }
            }
        }

        return maxArea;
    }

    // Helper to mark island and calculate area
    private static int markArea(int[][] grid, int i, int j, int id) {
        int n = grid.length;
        if (i < 0 || i >= n || j < 0 || j >= n || grid[i][j] != 1) return 0;
        grid[i][j] = id;
        int area = 1;

        for (int[] d : directions) {
            area += markArea(grid, i + d[0], j + d[1], id);
        }

        return area;
    }

    // Simple test method to validate test cases
    public static void main(String[] args) {
        int[][] test1 = {
                {0, 1, 0},
                {0, 0, 1},
                {0, 1, 1}
        };

        System.out.println("Test1: " + (largestHotArea(copyGrid(test1)) == 5 ? "PASS" : "FAIL")); // Expected 5

        int[][] test2 = {
                {1, 1},
                {1, 1}
        };

        System.out.println("Test2: " + (largestHotArea(copyGrid(test2)) == 4 ? "PASS" : "FAIL")); // Expected 4

        int[][] test3 = {
                {0, 0},
                {0, 0}
        };

        System.out.println("Test3: " + (largestHotArea(copyGrid(test3)) == 1 ? "PASS" : "FAIL")); // Expected 1

        int[][] test4 = {
                {1, 0, 1},
                {0, 0, 0},
                {1, 0, 1}
        };

        System.out.println("Test4: " + (largestHotArea(copyGrid(test4)) == 3 ? "PASS" : "FAIL")); // Expected 3

        // Large input test (50x50 grid)
        int[][] largeTest = IntStream.range(0, 50)
                .mapToObj(i -> IntStream.range(0, 50).map(j -> (i + j) % 2).toArray())
                .toArray(int[][]::new);

        System.out.println("Large Test: " + (largestHotArea(copyGrid(largeTest)) > 0 ? "PASS" : "FAIL"));
    }

    // Utility method to copy grid
    private static int[][] copyGrid(int[][] grid) {
        return Arrays.stream(grid)
                .map(int[]::clone)
                .toArray(int[][]::new);
    }
}
