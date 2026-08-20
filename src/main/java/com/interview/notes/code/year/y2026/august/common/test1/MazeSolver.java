package com.interview.notes.code.year.y2026.august.common.test1;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.IntStream;

public class MazeSolver { // Declares the main class which encapsulates our BFS maze-solving logic and the test runner

    // Main algorithm method to find the minimum jumps; takes the grid and the max jump distance 'k'
    public static int minJumps(int[][] grid, int k) { // Returns the shortest path distance or -1 if impossible
        int n = grid.length; // Extracts the total number of rows in the grid to manage boundaries
        int m = grid[0].length; // Extracts the total number of columns in the grid to manage boundaries

        // Edge Case: If the starting cell or the destination cell contains an obstacle, no path is possible
        if (grid[0][0] == 1 || grid[n - 1][m - 1] == 1) return -1; // Immediately return -1 indicating failure

        // Edge Case: If the grid is only 1x1, the start is the destination, requiring 0 jumps
        if (n == 1 && m == 1) return 0; // Return 0 jumps because we are already at the target

        int[][] dist = new int[n][m]; // Creates a 2D array to track the minimum moves to reach each specific cell
        for (int[] row : dist) Arrays.fill(row, Integer.MAX_VALUE); // Fills all distances with MAX_VALUE representing 'unvisited'
        dist[0][0] = 0; // The starting position at (0, 0) takes exactly 0 moves to reach

        Queue<Cell> q = new LinkedList<>(); // Initializes a Queue to process cells in Breadth-First Search (BFS) order
        q.offer(new Cell(0, 0, 0)); // Adds the initial starting cell into the queue to begin the search

        // A compact direction array representing Up, Right, Down, and Left movements using coordinate offsets
        int[] dirs = {-1, 0, 1, 0, -1}; // Pairs are: (-1,0) Up, (0,1) Right, (1,0) Down, (0,-1) Left

        while (!q.isEmpty()) { // Continues the BFS loop as long as there are reachable cells left to process
            var curr = q.poll(); // Java 21 'var': Polls (retrieves and removes) the front cell from the BFS queue

            // Checks if the current cell is the bottom-right destination cell
            if (curr.r == n - 1 && curr.c == m - 1) return curr.d; // If reached, return the recorded minimum distance

            for (int i = 0; i < 4; i++) { // Loops exactly 4 times to cover all four cardinal directions
                for (int step = 1; step <= k; step++) { // Explores jump sizes from 1 up to the maximum limit 'k'
                    int nr = curr.r + dirs[i] * step; // Calculates the new row position based on direction and step size
                    int nc = curr.c + dirs[i + 1] * step; // Calculates the new column position similarly using dirs offset

                    if (nr < 0 || nr >= n || nc < 0 || nc >= m) break; // Stops exploring this direction if it goes out of grid bounds
                    if (grid[nr][nc] == 1) break; // Stops exploring this direction completely if an obstacle is hit

                    // Optimization: If this cell was already reached in fewer or equal moves, its own ray will cover paths ahead
                    if (dist[nr][nc] <= curr.d) break; // We safely abort this ray to prevent redundant work and TLE on large inputs

                    if (dist[nr][nc] > curr.d + 1) { // Checks if jumping here provides a strictly shorter path than before
                        dist[nr][nc] = curr.d + 1; // Updates the distance matrix with this new, shorter move count
                        q.offer(new Cell(nr, nc, curr.d + 1)); // Enqueues the cell to explore its future neighbors later
                    } // Closes the distance update condition
                } // Closes the jump steps loop
            } // Closes the cardinal directions loop
        } // Closes the BFS while-loop
        return -1; // If the queue empties and destination wasn't reached, it's impossible, so return -1
    } // Closes the minJumps method

    // The main method serving as our entry point for simple testing without using JUnit dependency
    public static void main(String[] args) { // Executes our test cases sequentially
        int[][] largeGrid = new int[1000][1000]; // Creates a massive 1000x1000 empty grid for performance testing

        List<TestCase> tests = List.of( // Java 9+ feature: Creates an immutable list of various test scenarios
            new TestCase(new int[][]{{0, 0, 0}, {1, 1, 0}, {0, 0, 0}}, 1, 4), // Standard small maze with 1-step jumps
            new TestCase(new int[][]{{0, 0, 0}, {1, 1, 0}, {0, 0, 0}}, 2, 2), // Same maze but with 2-step jumps (faster)
            new TestCase(new int[][]{{0, 1}, {1, 0}}, 1, -1), // Blocked maze where destination is completely unreachable
            new TestCase(new int[][]{{0, 0, 0, 0}, {0, 1, 1, 0}, {0, 0, 0, 0}}, 3, 2), // Demonstrates safely jumping over obstacles
            new TestCase(new int[][]{{0}}, 5, 0), // Edge case: A 1x1 grid should immediately return 0 moves
            new TestCase(largeGrid, 500, 4) // Large data case: Requires 4 optimal max jumps of 500 to cover 1000x1000
        ); // Closes the list of test cases

        // Java 8 Stream API: Iterates over the indices of our test cases cleanly
        IntStream.range(0, tests.size()).forEach(i -> { // Loops from 0 to the number of test cases minus one
            TestCase t = tests.get(i); // Retrieves the test case at the current index 'i'
            long startTime = System.currentTimeMillis(); // Records the start time to track performance for large data sets
            int result = minJumps(t.grid, t.k); // Executes our maze solver algorithm on the current test case
            long timeTaken = System.currentTimeMillis() - startTime; // Calculates the total time taken by the algorithm
            String status = (result == t.expected) ? "PASS" : "FAIL"; // Compares the actual result with expected to determine status
            // Prints the test results and execution time to the console in a clean format
            System.out.println("Test " + (i + 1) + ": " + status + " (Expected: " + t.expected + ", Got: " + result + ") - " + timeTaken + "ms");
        }); // Closes the stream loop
    } // Closes the main method

    // Java 21 feature: A concise 'record' to replace bulky classes, storing row, column, and current distance
    record Cell(int r, int c, int d) {} // 'r' is row, 'c' is column, 'd' is the number of jumps taken so far

    // A simple record to hold our test case inputs and expected outputs cleanly
    record TestCase(int[][] grid, int k, int expected) {} // Stores the grid, max jumps, and what the answer should be
} // Closes the MazeSolver class