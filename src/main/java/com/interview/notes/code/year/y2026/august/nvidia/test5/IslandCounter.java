package com.interview.notes.code.year.y2026.august.nvidia.test5;

import java.util.ArrayDeque; // Imports ArrayDeque for iterative DFS.
import java.util.Arrays; // Imports Arrays for copying test grids.
import java.util.stream.Stream; // Imports Stream API for running tests.

public class IslandCounter { // Defines the main class.

    public static int count(char[][] grid) { // Counts the total number of islands.
        int islands = 0; // Stores the island count.

        for (int row = 0; row < grid.length; row++) { // Goes through every row.
            for (int col = 0; col < grid[row].length; col++) { // Goes through every column.

                if (grid[row][col] == '1') { // Checks for new unvisited land.
                    islands++; // Counts the newly found island.
                    dfs(grid, row, col); // Visits the complete connected island.
                } // Ends land check.
            } // Ends column loop.
        } // Ends row loop.

        return islands; // Returns the total number of islands.
    } // Ends count method.

    private static void dfs(char[][] grid, int row, int col) { // Performs DFS without recursion.
        var stack = new ArrayDeque<int[]>(); // Creates a stack to avoid StackOverflowError.
        stack.push(new int[]{row, col}); // Adds the starting land cell.
        grid[row][col] = '0'; // Marks the starting cell as visited.

        while (!stack.isEmpty()) { // Continues until the island is completely visited.
            int[] cell = stack.pop(); // Gets the next land cell.
            int r = cell[0]; // Gets the current row.
            int c = cell[1]; // Gets the current column.

            add(grid, stack, r + 1, c); // Checks below.
            add(grid, stack, r - 1, c); // Checks above.
            add(grid, stack, r, c + 1); // Checks right.
            add(grid, stack, r, c - 1); // Checks left.
        } // Ends DFS loop.
    } // Ends DFS method.

    private static void add(char[][] grid, ArrayDeque<int[]> stack, int row, int col) { // Adds valid land to the stack.
        if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length
                || grid[row][col] != '1') return; // Skips invalid, water, or visited cells.

        grid[row][col] = '0'; // Marks the land cell as visited.
        stack.push(new int[]{row, col}); // Adds the land cell to continue DFS.
    } // Ends add method.

    public static void main(String[] args) { // Runs only the two provided examples.

        var tests = Stream.of( // Creates the two test cases.

                new Test(new char[][]{ // Creates Example 1.
                        {'1', '1', '1', '1', '0'}, // Row 1.
                        {'1', '1', '0', '1', '0'}, // Row 2.
                        {'1', '1', '0', '0', '0'}, // Row 3.
                        {'0', '0', '0', '0', '0'}  // Row 4.
                }, 1), // Expected answer is 1.

                new Test(new char[][]{ // Creates Example 2.
                        {'1', '1', '0', '0', '0'}, // Row 1.
                        {'1', '1', '0', '0', '0'}, // Row 2.
                        {'0', '0', '1', '0', '0'}, // Row 3.
                        {'0', '0', '0', '1', '1'}  // Row 4.
                }, 3) // Expected answer is 3.
        ); // Ends test creation.

        tests.forEach(test -> { // Runs both test cases.
            char[][] copy = Arrays.stream(test.grid()) // Reads every row.
                    .map(char[]::clone) // Copies each row because our solution modifies the grid.
                    .toArray(char[][]::new); // Creates the copied 2D grid.

            int actual = count(copy); // Calculates the actual number of islands.
            boolean pass = actual == test.expected(); // Checks actual against expected.

            System.out.printf( // Prints the test result.
                    "%s | Expected: %d | Actual: %d%n", // Defines output format.
                    pass ? "PASS" : "FAIL", // Prints PASS or FAIL.
                    test.expected(), // Prints expected value.
                    actual // Prints actual value.
            ); // Ends print statement.
        }); // Ends test execution.
    } // Ends main method.

    record Test(char[][] grid, int expected) {} // Stores grid and expected result.
} // Ends IslandCounter class.