package com.interview.notes.code.year.y2026.august.nvidia.test2;

import java.util.PriorityQueue; // Import PriorityQueue for optimal pathfinding using a Min-Heap data structure.
import java.util.stream.Stream; // Import Stream API to handle and iterate through test cases cleanly.

public class DeliveryPathFinder { // Main class name reflecting the delivery person context from the problem.

    public static int leastTime(int[][] grid) { // Core algorithm method taking the 2D grid of obstacles as input.
        var n = grid.length; // Use 'var' (modern Java feature) to implicitly type the grid dimension variable.
        var visited = new boolean[n][n]; // Boolean matrix to track visited cells so the delivery person doesn't walk backwards in cycles.
        var pq = new PriorityQueue<Cell>(); // PriorityQueue that always surfaces the cell with the lowest required waiting time.
        var dirs = new int[]{-1, 0, 1, 0, -1}; // Direction array trick: adjacent pairs (e.g., -1,0 and 0,1) represent up, right, down, left vectors.

        pq.offer(new Cell(0, 0, grid[0][0])); // Push the starting point (0,0) alongside its initial obstacle time into the queue.
        visited[0][0] = true; // Mark the starting coordinate as visited immediately to prevent re-evaluation.

        while (!pq.isEmpty()) { // Keep exploring as long as we have accessible frontier cells waiting in our queue.
            var curr = pq.poll(); // Extract the cell that currently requires the absolute least amount of wait time.

            if (curr.r == n - 1 && curr.c == n - 1) { // Check if we have finally reached the target cell at the bottom-right corner.
                return curr.maxTime; // If true, return the maximum time encountered on this path; Dijkstra guarantees it is the optimal minimum.
            } // Close target check block.

            for (var i = 0; i < 4; i++) { // Iterate exactly 4 times to check all four adjacent orthogonal neighbors.
                var nr = curr.r + dirs[i]; // Calculate the neighbor's row index by applying the directional shift.
                var nc = curr.c + dirs[i + 1]; // Calculate the neighbor's column index using the offset in the direction array.

                if (nr >= 0 && nr < n && nc >= 0 && nc < n && !visited[nr][nc]) { // Ensure the neighbor is safely inside grid bounds and has never been visited.
                    visited[nr][nc] = true; // Immediately secure this cell by marking it visited to avoid redundant processing by other paths.
                    var newTime = Math.max(curr.maxTime, grid[nr][nc]); // The bottleneck logic: path time is dictated by the highest obstacle encountered so far or the new cell itself.
                    pq.offer(new Cell(nr, nc, newTime)); // Enqueue this valid neighbor to be evaluated in future iterations based on its newly calculated bottleneck time.
                } // Close boundary and visited check block.
            } // Close the 4-direction neighbor iteration loop.
        } // Close the main while loop governing the PriorityQueue.
        return -1; // Fallback return if the grid is somehow completely disconnected (technically impossible based on problem constraints).
    } // Close the leastTime core algorithm method.

    public static void main(String[] args) { // Standard main entry point to act as our independent, zero-dependency test runner.
        record TestCase(int[][] grid, int expected, String name) {} // Temporary nested record to neatly group test inputs with their expected answers.

        Stream.of( // Utilize Java 8+ Stream API to process multiple test scenarios functionally and sequentially.
            new TestCase(new int[][]{ // Define the primary test case array directly extracted from the user's provided problem image.
                {0, 1, 2, 3, 4}, // Row 0 values transcribed directly from the image example.
                {24, 16, 22, 21, 5}, // Row 1 values transcribed directly from the image example.
                {12, 13, 14, 15, 23}, // Row 2 values transcribed directly from the image example.
                {11, 17, 18, 19, 20}, // Row 3 values transcribed directly from the image example.
                {10, 9, 8, 7, 6} // Row 4 values transcribed directly from the image example.
            }, 16, "Screenshot 2026-08-05 at 1.34.36 AM.jpg Example"), // Expected answer is 16, naming it explicitly after the referenced file.
            new TestCase(new int[][]{{0, 2}, {1, 3}}, 3, "Small 2x2 Edge Grid"), // Add a minimal boundary edge case to ensure basic logic holds on small footprints.
            new TestCase(generateLargeGrid(100), 198, "Large Data Scale Test") // Inject a dynamically generated 100x100 grid to prove the algorithm handles large data efficiently.
        ).forEach(test -> { // Use the Stream's functional forEach method to process each bundled TestCase object efficiently.
            var result = leastTime(test.grid); // Run our main Dijkstra logic against the specific test case's grid data.
            var status = (result == test.expected) ? "PASS" : "FAIL"; // Evaluate correctness using a simple ternary operator for a clean boolean check.
            System.out.printf("[%s] %s | Expected: %d, Got: %d%n", status, test.name, test.expected, result); // Print structured logs to the console confirming the exact test status.
        }); // Close the forEach stream processor block.
    } // Close the main test runner method.

    static int[][] generateLargeGrid(int size) { // Utility method to fabricate large arrays dynamically, avoiding massive, unreadable hardcoded data structures.
        var grid = new int[size][size]; // Create a blank 2D integer array of the dynamically requested dimensions.
        for (var i = 0; i < size; i++) { // Loop vertically over all rows in the new grid.
            for (var j = 0; j < size; j++) { // Loop horizontally over all columns in the current row.
                grid[i][j] = i + j; // Populate cells with a simple predictable rising number pattern so the expected bottleneck (bottom-right) is easily calculable (size*2 - 2).
            } // Close the inner column loop.
        } // Close the outer row loop.
        return grid; // Yield the fully populated heavy grid object back to the test runner.
    } // Close the large grid utility method.

    record Cell(int r, int c, int maxTime) implements Comparable<Cell> { // Java 21 record to hold state immutably, minimizing code words by auto-generating getters.
        @Override // Signal to the compiler we are intentionally overriding the standard comparison logic.
        public int compareTo(Cell other) { // Compare method to determine how cells are ordered in our priority queue.
            return Integer.compare(this.maxTime, other.maxTime); // Sort by maxTime in ascending order so lowest time is always evaluated first.
        } // Close the compareTo method block.
    } // Close the record definition block.
} // Close the outer DeliveryPathFinder class.