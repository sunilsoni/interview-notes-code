package com.interview.notes.code.year.y2026.august.nvidia.test4;

import java.util.Arrays; // Imports Arrays utility for deep copying our test grids
import java.util.stream.Stream; // Imports Stream API to manage and run our test cases efficiently

public class IslandCounter { // Main class containing the solution and testing logic
    
    public static int count(char[][] g) { // Method to count islands, taking the 2D char array grid 'g'
        int c = 0; // Initializes the total island count 'c' to zero
        for (int i = 0; i < g.length; i++) // Loops continuously through every row index 'i' in the grid
            for (int j = 0; j < g[i].length; j++) // Loops continuously through every column index 'j' in the current row
                if (g[i][j] == '1') { // Checks if the current grid cell contains land ('1')
                    c++; // Increments the total island count because we found a new, unvisited island
                    dfs(g, i, j); // Calls the helper method to sink this entire connected island
                } // Closes the if-statement
        return c; // Returns the final computed number of islands
    } // Closes the count method

    private static void dfs(char[][] g, int i, int j) { // Recursive Depth-First Search method to mark connected land
        if (i < 0 || i >= g.length || j < 0 || j >= g[0].length || g[i][j] == '0') return; // Exits recursion if coordinates are out of bounds or hit water
        g[i][j] = '0'; // Sinks the current land piece by changing '1' to '0' so it is not counted again
        dfs(g, i + 1, j); // Recursively searches the grid cell directly below the current one
        dfs(g, i - 1, j); // Recursively searches the grid cell directly above the current one
        dfs(g, i, j + 1); // Recursively searches the grid cell directly to the right of the current one
        dfs(g, i, j - 1); // Recursively searches the grid cell directly to the left of the current one
    } // Closes the dfs method

    public static void main(String[] args) { // Standard main method used here strictly for local testing (No JUnit)

        char[][] largeGrid = new char[300][300]; // Creates a large 300x300 grid to test maximum constraints
        for (char[] row : largeGrid) Arrays.fill(row, '1'); // Fills the entire large grid with land to test worst-case stack depth

        var tests = Stream.of( // Uses 'var' (Java 10+) and Stream API to declare and initialize our test suite
            new Test(new char[][]{{'1','1','1','1','0'},{'1','1','0','1','0'},{'1','1','0','0','0'},{'0','0','0','0','0'}}, 1), // Example 1 from the provided image
            new Test(new char[][]{{'1','1','0','0','0'},{'1','1','0','0','0'},{'0','0','1','0','0'},{'0','0','0','1','1'}}, 3), // Example 2 from the provided image
            new Test(new char[][]{{'0','0'},{'0','0'}}, 0), // Edge case: A grid that consists entirely of water
            new Test(largeGrid, 1) // Large data case: 300x300 grid completely filled with land (should equal exactly 1 island)
        ); // Closes the Stream initialization

        tests.forEach(t -> { // Iterates through each Test record within our stream
            char[][] copy = Arrays.stream(t.grid()).map(char[]::clone).toArray(char[][]::new); // Deep copies the grid using streams so we don't mutate original test data
            boolean pass = count(copy) == t.expected(); // Executes the algorithm and checks if the result matches the expected integer
            System.out.println(pass ? "PASS" : "FAIL"); // Prints "PASS" if true, or "FAIL" if false using a ternary operator
        }); // Closes the lambda expression and forEach loop

    } // Closes the main method

    record Test(char[][] grid, int expected) {} // Uses Java 21/14+ Record feature for a compact, immutable test case container
} // Closes the IslandCounter class