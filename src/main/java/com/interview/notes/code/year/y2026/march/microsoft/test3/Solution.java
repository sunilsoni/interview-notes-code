package com.interview.notes.code.year.y2026.march.microsoft.test3;

import java.util.*;
import java.util.stream.IntStream;

public class Solution {
    
    // Global variable to track the minimum path length found across recursive calls
    static int minPath;

    public static int minMoves(List<List<Integer>> maze, int x, int y) {
        int n = maze.size(); // Get the total number of rows in the maze
        int m = maze.get(0).size(); // Get the total number of columns in the maze

        // Use Java 8 Streams to find and collect all gold coin coordinates cleanly
        List<Point> coins = IntStream.range(0, n).boxed() // Stream over row indices
            .flatMap(r -> IntStream.range(0, m) // FlatMap to stream over column indices
                .filter(c -> maze.get(r).get(c) == 2) // Filter only cells containing a coin (value 2)
                .mapToObj(c -> new Point(r, c))) // Map the matched coordinates to Point objects
            .toList(); // Collect the results into an unmodifiable List (Java 16+ feature)

        int numCoins = coins.size(); // Store the total count of coins to collect
        List<Point> pois = new ArrayList<>(); // Create a list for all Points of Interest (POIs)
        pois.add(new Point(0, 0)); // Index 0: Add Chris's starting position
        pois.addAll(coins); // Indices 1 to numCoins: Add all coin positions
        pois.add(new Point(x, y)); // Index numCoins + 1: Add Alex's position

        int numPois = pois.size(); // Calculate the total number of POIs
        int[][] dist = new int[numPois][numPois]; // 2D array to hold shortest distances between every POI

        // Iterate over every POI to calculate distances to all other POIs
        for (int i = 0; i < numPois; i++) {
            dist[i] = bfs(pois.get(i), pois, maze, n, m); // Run BFS from current POI and store results
        }

        minPath = Integer.MAX_VALUE; // Reset minPath for each function call to avoid static state bugs
        boolean[] visited = new boolean[numCoins + 1]; // Array to keep track of collected coins during DFS

        // Start DFS from start position (0), with 0 coins collected, and 0 distance traveled
        findShortest(0, 0, 0, numCoins, dist, visited);

        // If minPath was never updated, a valid path doesn't exist, so return -1
        return minPath == Integer.MAX_VALUE ? -1 : minPath;
    }

    // Helper method using Breadth-First Search to find distances from a start point
    private static int[] bfs(Point start, List<Point> pois, List<List<Integer>> maze, int n, int m) {
        int[] distances = new int[pois.size()]; // Array to hold distances from start to each POI
        Arrays.fill(distances, -1); // Initialize all distances to -1 (meaning unreachable initially)

        Queue<Point> queue = new LinkedList<>(); // Queue required for standard BFS traversal
        int[][] steps = new int[n][m]; // 2D array to track minimum steps to every cell in the grid
        for (int[] row : steps) Arrays.fill(row, -1); // Fill step tracker with -1 initially

        queue.offer(start); // Add the starting position to the BFS queue
        steps[start.r()][start.c()] = 0; // Steps to reach the starting position from itself is 0

        int[] dr = {-1, 1, 0, 0}; // Array representing row movements (Up, Down)
        int[] dc = {0, 0, -1, 1}; // Array representing column movements (Left, Right)

        // Continue searching as long as there are reachable cells in the queue
        while (!queue.isEmpty()) {
            Point curr = queue.poll(); // Dequeue the next cell to process
            int currSteps = steps[curr.r()][curr.c()]; // Retrieve how many steps it took to get here

            // Loop through all 4 possible movement directions
            for (int i = 0; i < 4; i++) {
                int nr = curr.r() + dr[i]; // Calculate the new row coordinate
                int nc = curr.c() + dc[i]; // Calculate the new column coordinate

                // Check if new position is inside grid, is not a wall (1), and hasn't been visited (-1)
                if (nr >= 0 && nr < n && nc >= 0 && nc < m && maze.get(nr).get(nc) != 1 && steps[nr][nc] == -1) {
                    steps[nr][nc] = currSteps + 1; // Record the steps taken to reach this new cell
                    queue.offer(new Point(nr, nc)); // Queue this new valid cell for further exploration
                }
            }
        }

        // Loop through all POIs to extract their specific distances from the steps grid
        for (int i = 0; i < pois.size(); i++) {
            distances[i] = steps[pois.get(i).r()][pois.get(i).c()]; // Map grid step count to the POI distance array
        }
        return distances; // Return the fully populated distances array
    }

    // Helper method using Depth-First Search to try all permutations of coin collection
    private static void findShortest(int currPoi, int collected, int currDist, int totalCoins, int[][] dist, boolean[] visited) {
        // Optimization (Pruning): Stop this path immediately if it's already longer than our best known path
        if (currDist >= minPath) return;

        // Base case: Check if we have successfully collected all required gold coins
        if (collected == totalCoins) {
            int distToAlex = dist[currPoi][totalCoins + 1]; // Look up the distance from here to Alex's location
            if (distToAlex != -1) { // Verify that Alex is actually reachable from this last coin
                minPath = Math.min(minPath, currDist + distToAlex); // Update our best path if this route is shorter
            }
            return; // Backtrack to explore other potential coin combinations
        }

        // Iterate over all possible coins to find the next one to collect
        for (int i = 1; i <= totalCoins; i++) {
            // If coin 'i' hasn't been picked up yet AND it's physically reachable from our current spot
            if (!visited[i] && dist[currPoi][i] != -1) {
                visited[i] = true; // Mark this coin as picked up for the current path branch
                // Recursively explore from this new coin, incrementing collected count and total distance
                findShortest(i, collected + 1, currDist + dist[currPoi][i], totalCoins, dist, visited);
                visited[i] = false; // Backtrack: Unmark this coin so other path branches can try picking it up later
            }
        }
    }

    // Main method for testing logic without needing JUnit framework
    public static void main(String[] args) {
        // Run Sample Case 0 (from the instructions)
        runTest("Test Case 0", new int[][]{{0, 2, 0}, {0, 0, 1}, {1, 1, 1}}, 1, 1, 2);
        // Run Sample Case 1 (unreachable Alex/Coins)
        runTest("Test Case 1", new int[][]{{0, 1, 0}, {1, 0, 1}, {0, 2, 2}}, 1, 1, -1);
        // Run Sample Case 2 (longer route requirement)
        runTest("Test Case 2", new int[][]{{0, 2, 0}, {1, 1, 2}, {1, 0, 0}}, 2, 1, 5);
        // Run Custom Case to verify multi-coin complex routing performance
        runTest("Large Open Grid Edge Case", new int[][]{
            {0,0,0,0,0}, {0,2,0,2,0}, {0,0,0,0,0}, {0,2,0,2,0}, {0,0,0,0,0}
        }, 4, 4, 12);
    }

    // Helper test runner to cleanly print pass/fail results
    private static void runTest(String name, int[][] grid, int x, int y, int expected) {
        // Convert native 2D array into List<List<Integer>> required by method signature using Streams
        List<List<Integer>> maze = Arrays.stream(grid) // Stream the rows
                .map(row -> Arrays.stream(row).boxed().toList()) // Box and collect each row to a List
                .toList(); // Collect all rows into the final nested List

        long start = System.nanoTime(); // Start timing the execution
        int result = minMoves(maze, x, y); // Execute the core logic
        long end = System.nanoTime(); // End timing

        // Print detailed pass/fail report to console
        if (result == expected) {
            System.out.println(name + " -> PASS! (Expected: " + expected + ", Got: " + result + ") in " + (end - start) / 1000000 + "ms");
        } else {
            System.out.println(name + " -> FAIL! (Expected: " + expected + ", Got: " + result + ")");
        }
    }

    // Record to neatly hold grid coordinates without making a bulky class (Java 14+ feature)
    record Point(int r, int c) {}
}