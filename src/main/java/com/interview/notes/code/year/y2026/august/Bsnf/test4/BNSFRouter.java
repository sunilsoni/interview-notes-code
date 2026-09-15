package com.interview.notes.code.year.y2026.august.Bsnf.test4;

public class BNSFRouter { // Main class to encapsulate our BNSF train routing logic

    public static long countRoutes(int[][] grid) { // Method to compute total paths, returns long to prevent integer overflow on large grids
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0; // Guard clause: immediately return 0 if the grid is empty or null to avoid crashes
        
        int cols = grid[0].length; // Store the total number of columns to size our tracking array
        long[] dp = new long[cols]; // Create a 1D array to store path counts for the current row, saving memory compared to a 2D array
        dp[0] = 1; // Initialize the starting position with 1 valid path (the train is standing at the yard)

        for (int[] row : grid) { // Iterate sequentially through every horizontal row of the railway grid
            for (int c = 0; c < cols; c++) { // Iterate through every column cell in the current row
                if (row[c] == 1) { // Check if the current grid cell is an active maintenance zone
                    dp[c] = 0; // Set paths to 0 because the train absolutely cannot pass through this zone
                } else if (c > 0) { // If it's active track (0) and we are not in the very first column
                    dp[c] = dp[c] + dp[c - 1]; // Add the number of paths from the left cell to the current cell's existing total (which holds the path count from the cell directly above)
                } // End of conditional logic for the current cell
            } // Move to the next column cell in this row
        } // Move to the next horizontal row in the grid
        
        return dp[cols - 1]; // The final element in our array now contains the total number of paths to the bottom-right terminal
    } // End of the countRoutes calculation method

    public static void main(String[] args) { // Standard main method used as our test runner, avoiding external frameworks like JUnit

        int[][] largeGrid = new int[20][20]; // Initialize a 20x20 grid to simulate a large data input scenario
        // A 20x20 open grid has exactly 35,345,263,800 paths, which perfectly tests our 'long' data type handling

        var testCases = java.util.List.of( // Using Java 10+ 'var' and factory methods for a concise list setup
            new TestCase(new int[][]{{0, 0, 0}, {0, 1, 0}, {0, 0, 0}}, 2, "Standard 3x3 with one block"), // Classic test case with 2 valid routes
            new TestCase(new int[][]{{0, 1}, {0, 0}}, 1, "Small 2x2 with right block"), // Train is forced to go down then right
            new TestCase(new int[][]{{1, 0}, {0, 0}}, 0, "Blocked at start"), // Impossible to start, should be 0
            new TestCase(new int[][]{{0, 0}, {0, 1}}, 0, "Blocked at destination"), // Impossible to finish, should be 0
            new TestCase(new int[][]{{0}}, 1, "Single cell grid"), // Smallest possible grid edge case
            new TestCase(largeGrid, 35345263800L, "Large Data 20x20 Grid") // Large input test to verify performance and overflow safety
        ); // End of test case list initialization

        testCases.stream().forEach(tc -> { // Using Java 8 Stream API to iterate through each test case cleanly
            long result = countRoutes(tc.grid()); // Execute our core routing logic against the current test grid
            String status = (result == tc.expected()) ? "PASS" : "FAIL"; // Compare the actual result with our expected outcome to determine PASS or FAIL
            System.out.printf("[%s] %s - Expected: %d, Got: %d%n", status, tc.name(), tc.expected(), result); // Print the formatted test result to the console
        }); // End of stream operations
    } // End of the main method

    record TestCase(int[][] grid, long expected, String name) {} // Java 14+ Record feature to create a clean, immutable data carrier for our tests
} // End of the BNSFRouter class