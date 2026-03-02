package com.interview.notes.code.year.y2026.march.microsoft.test5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

public class Solution { // Main class wrapper required by Java
    
    // Global variable to keep track of the shortest path found during our recursive search
    static int minPath;
    
    // Main function to calculate the minimum moves to collect coins and reach Alex
    public static int minMoves(List<List<Integer>> maze, int x, int y) {
        int n = maze.size(), m = maze.get(0).size(); // Extract the total rows (n) and columns (m) of the maze

        // Create a list of Points of Interest (POIs) and instantly add Chris's starting point at (0, 0)
        var pois = new ArrayList<>(List.of(new Point(0, 0)));

        // Loop through all rows using Java Streams
        IntStream.range(0, n).forEach(r ->
            // Loop through all columns for the current row
            IntStream.range(0, m)
            // Filter to keep only the cells that contain a gold coin (value is 2)
            .filter(c -> maze.get(r).get(c) == 2)
            // For every coin found, add its coordinates as a Point to our POI list
            .forEach(c -> pois.add(new Point(r, c)))
        );

        pois.add(new Point(x, y)); // Add Alex's target location as the very last Point of Interest

        int k = pois.size(); // Store the total count of POIs (Start + Coins + Alex)
        int[][] dist = new int[k][k]; // Create a 2D matrix to store the shortest distance between every POI pair

        // Loop through every POI to calculate how far it is to all other POIs
        for (int i = 0; i < k; i++)
            // Call our BFS helper to fill the distance row for the current POI
            dist[i] = bfs(pois.get(i), pois, maze, n, m);

        minPath = Integer.MAX_VALUE; // Reset the global minimum path to the highest possible number

        // Start recursive search: current POI index 0 (Start), 0 coins collected, 0 distance, k-2 total coins
        dfs(0, 0, 0, k - 2, dist, new boolean[k - 1]);

        // If minPath didn't change, no valid path exists (-1). Otherwise, return the shortest path found.
        return minPath == Integer.MAX_VALUE ? -1 : minPath;
    }

    // Helper method: Breadth-First Search to find shortest direct paths from a starting point ignoring other coins
    private static int[] bfs(Point st, List<Point> pois, List<List<Integer>> maze, int n, int m) {
        var q = new LinkedList<Point>(); // Java 'var' keyword creates a Queue to manage cells we need to explore
        int[][] steps = new int[n][m]; // 2D array to track exactly how many steps it takes to reach each cell

        for (var row : steps) Arrays.fill(row, -1); // Fill the steps array with -1 to mark all cells as unvisited

        q.offer(st); // Put our starting point into the queue to begin exploration
        steps[st.r()][st.c()] = 0; // The distance from the start point to itself is exactly 0 steps

        int[] dr = {-1, 1, 0, 0}; // Row direction changes for moving Up, Down, Left, Right
        int[] dc = {0, 0, -1, 1}; // Column direction changes for moving Up, Down, Left, Right
        int[] res = new int[pois.size()]; // Array to hold the final distances specifically to our POIs

        // Keep exploring as long as there are reachable cells in the queue
        while (!q.isEmpty()) {
            var p = q.poll(); // Take the next cell out of the queue to process it

            for (int i = 0; i < 4; i++) { // Loop 4 times to try moving in all 4 directions
                int nr = p.r() + dr[i]; // Calculate the new row after moving
                int nc = p.c() + dc[i]; // Calculate the new column after moving

                // Check if new position is inside bounds, is NOT a wall (value 1), and is NOT visited (value < 0)
                if (nr >= 0 && nr < n && nc >= 0 && nc < m && maze.get(nr).get(nc) != 1 && steps[nr][nc] < 0) {
                    steps[nr][nc] = steps[p.r()][p.c()] + 1; // Record the step count for this new cell (previous + 1)
                    q.offer(new Point(nr, nc)); // Add the new valid cell to the queue to explore its neighbors later
                }
            }
        }

        // Java 8+ Arrays.setAll replaces a for-loop: extracts the distances to all POIs from the steps grid
        Arrays.setAll(res, i -> steps[pois.get(i).r()][pois.get(i).c()]);
        return res; // Return the array containing distances from the start point to all POIs
    }

    // Helper method: Depth-First Search to test all possible orders of collecting the coins
    private static void dfs(int u, int coins, int d, int total, int[][] dist, boolean[] vis) {
        // Optimization: If our current path distance is already worse than the best we've found, stop exploring it
        if (d >= minPath) return;

        // Base Condition: If the number of coins we collected equals the total coins on the map
        if (coins == total) {
            // If the path from our final coin to Alex (index total + 1) is valid (greater than -1)
            if (dist[u][total + 1] > -1)
                // Update our global shortest path if this combination resulted in a smaller total distance
                minPath = Math.min(minPath, d + dist[u][total + 1]);
            return; // Go back up the recursive chain to try other combinations
        }

        // Loop through all the coins (indexes 1 to total) to pick the next one to visit
        for (int i = 1; i <= total; i++) {
            // If the coin 'i' is NOT visited yet AND there is a clear path to it from our current spot 'u'
            if (!vis[i] && dist[u][i] > -1) {
                vis[i] = true; // Mark this specific coin as visited for the current path branch
                // Recursively call dfs: move to coin 'i', add 1 to collected, add distance traveled
                dfs(i, coins + 1, d + dist[u][i], total, dist, vis);
                vis[i] = false; // Backtrack: Unmark the coin so a different path permutation can try picking it up
            }
        }
    }

    // Simple main method to act as our test runner without needing external libraries like JUnit
    public static void main(String[] args) {
        // Test Case 0: Basic functional test from instructions
        runTest("Test Case 0", new int[][]{{0, 2, 0}, {0, 0, 1}, {1, 1, 1}}, 1, 1, 2);
        // Test Case 1: Unreachable targets, must return -1
        runTest("Test Case 1", new int[][]{{0, 1, 0}, {1, 0, 1}, {0, 2, 2}}, 1, 1, -1);
        // Test Case 2: Requires backtracking away from target to grab a coin first
        runTest("Test Case 2", new int[][]{{0, 2, 0}, {1, 1, 2}, {1, 0, 0}}, 2, 1, 5);
        // Custom Large Case: Ensures performance holds up with scattered coins and zero walls
        runTest("Large Custom", new int[][]{{0,0,0,0,0}, {0,2,0,2,0}, {0,0,0,0,0}, {0,2,0,2,0}, {0,0,0,0,0}}, 4, 4, 12);
    }

    // Helper method to convert raw arrays to Lists, run the logic, and print Pass/Fail clearly
    private static void runTest(String name, int[][] grid, int x, int y, int expected) {
        // Stream the 2D primitive int array and convert it into the required List<List<Integer>> format
        var maze = Arrays.stream(grid).map(r -> Arrays.stream(r).boxed().toList()).toList();

        long start = System.currentTimeMillis(); // Track start time for performance metric
        int result = minMoves(maze, x, y); // Call the main solver function
        long end = System.currentTimeMillis(); // Track end time

        // Print formatted output checking if the result matches the expected output
        System.out.printf("%s -> %s (Expected: %d, Got: %d) [%d ms]%n",
                name, (result == expected ? "PASS!" : "FAIL!"), expected, result, (end - start));
    }

    // Java 14+ 'record': A highly compact way to create an immutable class holding row (r) and column (c)
    record Point(int r, int c) {}
}