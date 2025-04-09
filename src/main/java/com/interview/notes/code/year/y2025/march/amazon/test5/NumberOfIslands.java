package com.interview.notes.code.year.y2025.march.amazon.test5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NumberOfIslands {

    // Method to count islands in a given grid
    public static int countIslands(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;

        int numIslands = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    dfs(grid, i, j);
                    numIslands++;
                }
            }
        }
        return numIslands;
    }

    // Depth-First Search to mark connected lands
    private static void dfs(char[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == '0') return;

        grid[i][j] = '0';

        dfs(grid, i + 1, j); // down
        dfs(grid, i - 1, j); // up
        dfs(grid, i, j + 1); // right
        dfs(grid, i, j - 1); // left
        // Uncomment the lines below for diagonal connections:
        // dfs(grid, i + 1, j + 1);
        // dfs(grid, i + 1, j - 1);
        // dfs(grid, i - 1, j + 1);
        // dfs(grid, i - 1, j - 1);
    }

    // Testing method with provided and additional test cases
    public static void main(String[] args) {
        List<char[][]> testCases = new ArrayList<>();

        testCases.add(new char[][]{
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '1'},
                {'0', '0', '0', '1', '0'}
        });

        testCases.add(new char[][]{
                {'1', '0', '1'},
                {'0', '1', '0'},
                {'1', '0', '1'}
        });

        testCases.add(new char[][]{
                {'0', '0', '0'},
                {'0', '0', '0'},
                {'0', '0', '0'}
        });

        // Large test case
        char[][] largeGrid = new char[100][100];
        for (int i = 0; i < 100; i++) {
            Arrays.fill(largeGrid[i], '1');
        }
        testCases.add(largeGrid);

        int[] expectedResults = {3, 5, 0, 1};

        for (int i = 0; i < testCases.size(); i++) {
            char[][] gridCopy = deepCopy(testCases.get(i));
            int result = countIslands(gridCopy);
            System.out.println("Test Case " + (i + 1) + ": " +
                    (result == expectedResults[i] ? "PASS" : "FAIL") +
                    " (Expected: " + expectedResults[i] + ", Got: " + result + ")");
        }
    }

    // Utility method for deep copy to preserve original grid data
    private static char[][] deepCopy(char[][] original) {
        char[][] copy = new char[original.length][];
        for (int i = 0; i < original.length; i++) {
            copy[i] = original[i].clone();
        }
        return copy;
    }
}
