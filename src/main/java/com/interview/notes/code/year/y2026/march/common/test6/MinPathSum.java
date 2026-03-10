package com.interview.notes.code.year.y2026.march.common.test6;

import java.util.Arrays;
import java.util.stream.Stream;

public class MinPathSum {

    // Main method to calculate the minimum path sum
    public static int minPathSum(int[][] grid) {
        // Check if grid is null or empty to prevent NullPointerException
        if (grid == null || grid.length == 0) return 0;
        
        // Use 'var' (Java 10+) for type inference to minimize code words
        var rows = grid.length;
        // Store the number of columns based on the first row
        var cols = grid[0].length;

        // Loop through every row in the grid sequentially
        for (var i = 0; i < rows; i++) {
            // Loop through every column in the current row
            for (var j = 0; j < cols; j++) {
                
                // Skip the top-left cell because it is the starting point
                if (i == 0 && j == 0) continue;
                
                // If we are in the very first row, we can only arrive from the left
                if (i == 0) grid[i][j] += grid[i][j - 1];
                
                // If we are in the very first column, we can only arrive from directly above
                else if (j == 0) grid[i][j] += grid[i - 1][j];
                
                // Otherwise, add the smaller value between the cell above and the cell to the left
                else grid[i][j] += Math.min(grid[i - 1][j], grid[i][j - 1]);
            }
        }
        // The bottom-rightmost cell now contains the lowest possible sum
        return grid[rows - 1][cols - 1];
    }

    // Use a simple main method for testing instead of JUnit, as requested
    public static void main(String[] args) {

        // Create a massive 1000x1000 grid to verify large data handling
        int[][] largeGrid = new int[1000][1000];
        // Fill every cell in the large grid with the number 1
        for (var row : largeGrid) Arrays.fill(row, 1);

        // Use Java 8 Stream API to process all test cases concisely
        Stream.of(
            // Test Case 1: The standard example provided in your screenshot
            new TestCase("Example Case", new int[][]{{1, 2, 3}, {4, 5, 6}}, 12),
            // Test Case 2: Edge case where the grid is only a single row
            new TestCase("Single Row", new int[][]{{1, 2, 3, 4}}, 10),
            // Test Case 3: Edge case where the grid is only a single column
            new TestCase("Single Col", new int[][]{{1}, {2}, {3}}, 6),
            // Test Case 4: The large data input to ensure memory and speed efficiency
            new TestCase("Large 1000x1000", largeGrid, 1999)

        // Iterate through each test case in the stream
        ).forEach(test -> {

            // Execute our algorithm and store the result
            var result = minPathSum(test.grid());
            // Check if our result matches the expected output using a ternary operator
            var status = (result == test.expected()) ? "PASS" : "FAIL";
            // Print the formatted test output to the console
            System.out.printf("Test '%s': %s (Expected: %d, Got: %d)%n", test.name(), status, test.expected(), result);
        });
    }

    // Use Java 14+ 'record' feature to cleanly define test case structures with zero boilerplate
    record TestCase(String name, int[][] grid, int expected) {}
}