package com.interview.notes.code.year.y2026.august.Bsnf.test5;

public class BNSFBruteForceRouter { // Main class to encapsulate our naive brute-force train routing logic

    public static long countRoutesNaive(int[][] grid) { // Public method to start the routing process, taking the grid as input
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0; // Guard clause: if grid is empty or null, return 0 paths immediately to prevent errors
        return explorePaths(grid, 0, 0); // Start the recursive exploration exactly at the top-left yard (row 0, column 0)
    } // End of the initial setup method

    private static long explorePaths(int[][] grid, int row, int col) { // Private helper method that calls itself (recursion) to explore the grid step-by-step
        
        if (row >= grid.length || col >= grid[0].length) { // Base case 1: Check if the train moved outside the bottom or right boundaries of our grid
            return 0; // The train is off the map, so this route is invalid, return 0
        } // End of boundary check
        
        if (grid[row][col] == 1) { // Base case 2: Check if the current cell is marked as '1', which is a blocked maintenance zone
            return 0; // The train crashed into a maintenance zone, so this route fails, return 0
        } // End of obstacle check
        
        if (row == grid.length - 1 && col == grid[0].length - 1) { // Base case 3: Check if the train's current position matches the exact bottom-right destination cell
            return 1; // The train successfully arrived! We count this as 1 valid complete route
        } // End of destination check
        
        long pathsByGoingDown = explorePaths(grid, row + 1, col); // Recursive call 1: Simulate the train moving exactly one cell DOWN (row + 1)
        
        long pathsByGoingRight = explorePaths(grid, row, col + 1); // Recursive call 2: Simulate the train moving exactly one cell RIGHT (col + 1)
        
        return pathsByGoingDown + pathsByGoingRight; // Combine the total successful routes from both the downward path and the rightward path
        
    } // End of the recursive exploration method

    public static void main(String[] args) { // Main method serving as our testing framework instead of using JUnit

        var testCases = java.util.List.of( // Using Java 10+ 'var' and List.of to quickly create an immutable list of our scenarios
            new TestCase(new int[][]{{0, 0, 0}, {0, 1, 0}, {0, 0, 0}}, 2, "Standard 3x3 with one block"), // Basic test: 3x3 grid with a center block should yield 2 routes
            new TestCase(new int[][]{{0, 1}, {0, 0}}, 1, "Small 2x2 with right block"), // Small test: Train is forced to go down then right, 1 route
            new TestCase(new int[][]{{1, 0}, {0, 0}}, 0, "Blocked at start"), // Edge test: Maintenance zone at the starting point, 0 routes
            new TestCase(new int[][]{{0, 0}, {0, 1}}, 0, "Blocked at destination"), // Edge test: Maintenance zone at the finish line, 0 routes
            new TestCase(new int[12][12], 705432, "Medium 12x12 Grid") // Stress test: A 12x12 grid tests performance. (We avoid 20x20 here as brute force takes too long)
        ); // End of the test cases list initialization

        testCases.stream().forEach(tc -> { // Using Java 8 Stream API to neatly loop over each test case in our list
            long result = countRoutesNaive(tc.grid()); // Call our brute-force logic and store the actual computed routes
            String status = (result == tc.expected()) ? "PASS" : "FAIL"; // Compare the computed result with the expected answer to flag it as PASS or FAIL
            System.out.printf("[%s] %s - Expected: %d, Got: %d%n", status, tc.name(), tc.expected(), result); // Print a formatted status message to the console for the user to read
        }); // End of stream execution

    } // End of the main testing method

    record TestCase(int[][] grid, long expected, String name) {} // Java 14+ Record to create a clean, immutable container holding our test data and expected answers
} // End of the class