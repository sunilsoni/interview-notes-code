package com.interview.notes.code.year.y2026.march.common.tes1;

import java.util.Arrays;
import java.util.PriorityQueue;

public class MinPathFourDirections {

    // Main method to find the shortest path in 4 directions
    public static int solve(int[][] grid) {
        // Safety check: if grid is null or empty, return 0
        if (grid == null || grid.length == 0) return 0;

        // Use 'var' to minimize wordiness for grid dimensions
        var rows = grid.length;
        var cols = grid[0].length;

        // Create a 2D array to keep track of the cheapest cost to reach each cell
        var minCost = new int[rows][cols];
        // Fill the array with "Infinity" (Max Value) initially, as we haven't visited them yet
        for (var row : minCost) Arrays.fill(row, Integer.MAX_VALUE);
        
        // The cost to reach the starting cell is just its own value
        minCost[0][0] = grid[0][0];

        // Priority Queue to always process the cheapest current path first.
        // It stores arrays of [row, col, current_total_cost].
        // The lambda compares the 3rd element (cost) so the lowest cost stays at the top.
        var pq = new PriorityQueue<int[]>((a, b) -> Integer.compare(a[2], b[2]));
        
        // Add the starting cell to the queue to begin our search
        pq.offer(new int[]{0, 0, grid[0][0]});

        // Define the 4 possible moves: Right, Down, Left, Up (Row change, Col change)
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        // Keep searching as long as there are paths to explore
        while (!pq.isEmpty()) {
            
            // Pull the absolute cheapest cell out of the queue
            var current = pq.poll();
            var r = current[0]; // Current Row
            var c = current[1]; // Current Column
            var cost = current[2]; // Total cost to get here

            // If we have reached the bottom-right corner, we are completely done!
            if (r == rows - 1 && c == cols - 1) return cost;

            // If we already found a cheaper way to this cell earlier, ignore this path
            if (cost > minCost[r][c]) continue;

            // Try moving in all 4 directions from our current spot
            for (var dir : directions) {
                // Calculate the coordinates for the next cell
                var nextRow = r + dir[0];
                var nextCol = c + dir[1];

                // Check boundary limits to ensure we don't fall off the grid
                if (nextRow >= 0 && nextRow < rows && nextCol >= 0 && nextCol < cols) {
                    
                    // Calculate what the total cost WOULD be if we move there
                    var newCost = cost + grid[nextRow][nextCol];
                    
                    // If this new path is strictly cheaper than any previous path to that cell
                    if (newCost < minCost[nextRow][nextCol]) {
                        // Update our record book with the new, lower cost
                        minCost[nextRow][nextCol] = newCost;
                        // Add this new state to the queue so we can explore from it later
                        pq.offer(new int[]{nextRow, nextCol, newCost});
                    }
                }
            }
        }
        // Fallback return (though the queue guarantees we hit the target above)
        return minCost[rows - 1][cols - 1];
    }

    // Simple main method for testing, avoiding JUnit
    public static void main(String[] args) {
        
        // Test 1: Standard case where going Right/Down is optimal
        int[][] grid1 = {{1, 2, 3}, {4, 5, 6}};
        check("Standard 2-Way Path", 12, solve(grid1));

        // Test 2: A case where going UP and LEFT is actually cheaper!
        // Path: (0,0)->(1,0)->(2,0)->(2,1)->(1,1)->(0,1)->(0,2)->(1,2)->(2,2)
        // Visually: It snakes up and down to avoid the massive '100' blockers.
        int[][] grid2 = {
            {1,   1, 100},
            {1, 100,   1},
            {1,   1,   1}
        };
        check("4-Way Snake Path", 7, solve(grid2));

        // Test 3: Large Data (500x500)
        // To ensure the Priority Queue doesn't crash on large inputs
        int[][] largeGrid = new int[500][500];
        for (int[] row : largeGrid) Arrays.fill(row, 1);
        check("Large Data (250k cells)", 999, solve(largeGrid));
    }

    // Helper to print results cleanly
    private static void check(String testName, int expected, int actual) {
        System.out.printf("%-25s | Expected: %4d | Got: %4d | %s%n", 
            testName, expected, actual, (expected == actual ? "PASS" : "FAIL"));
    }
}