package com.interview.notes.code.year.y2026.august.nvidia.test1;

import java.util.PriorityQueue; // Import PriorityQueue to utilize a Min-Heap for optimal path selection based on time.
import java.util.stream.Stream; // Import Stream API to cleanly execute our test case.

public class DeliveryPathFinder { // Define the main class to house our delivery routing logic and test execution.

    public static int leastTime(int[][] grid) { // Define the core pathfinding method that accepts the 2D grid as an argument.
        var n = grid.length; // Use 'var' to implicitly type the grid size variable, reducing boilerplate.
        var visited = new boolean[n][n]; // Initialize a 2D boolean array to keep track of which cells we have already evaluated.
        var pq = new PriorityQueue<Cell>(); // Instantiate the priority queue that will manage our frontier of reachable cells.
        var dirs = new int[]{-1, 0, 1, 0, -1}; // Define a directional array to easily calculate up, right, down, and left neighbor coordinates.

        pq.offer(new Cell(0, 0, grid[0][0])); // Add the starting position (0, 0) and its initial obstacle wait time to the queue.
        visited[0][0] = true; // Mark the starting cell as visited immediately so we don't process it again.

        while (!pq.isEmpty()) { // Begin a loop that continues as long as there are accessible cells left to explore.
            var curr = pq.poll(); // Retrieve and remove the cell with the lowest maxTime from the front of the queue.

            if (curr.r == n - 1 && curr.c == n - 1) { // Check if the current cell's coordinates match the bottom-right destination cell.
                return curr.maxTime; // If we reached the destination, return its maxTime as Dijkstra guarantees it's the optimal minimum.
            } // Close the destination check block.

            for (var i = 0; i < 4; i++) { // Loop exactly 4 times to evaluate all orthogonally adjacent neighbors.
                var nr = curr.r + dirs[i]; // Calculate the neighbor's row index using the directional offset.
                var nc = curr.c + dirs[i + 1]; // Calculate the neighbor's column index using the next directional offset.

                if (nr >= 0 && nr < n && nc >= 0 && nc < n && !visited[nr][nc]) { // Verify the neighbor is within grid bounds and hasn't been visited yet.
                    visited[nr][nc] = true; // Mark the valid neighbor as visited to prevent duplicate processing from other paths.
                    var newTime = Math.max(curr.maxTime, grid[nr][nc]); // Determine the new bottleneck time, which is the maximum of the path so far or the new cell's time.
                    pq.offer(new Cell(nr, nc, newTime)); // Add the valid neighbor to the queue with its updated bottleneck time for future evaluation.
                } // Close the boundary and visited validation block.
            } // Close the neighbor iteration loop.
        } // Close the main while loop.
        return -1; // Return a default fallback value in case no path exists (impossible under given problem constraints).
    } // Close leastTime method.

    public static void main(String[] args) { // Define the main method to serve as an independent test execution point.
        record TestCase(int[][] grid, int expected, String name) {} // Define a temporary local record to neatly pair grid inputs with their expected results.

        Stream.of( // Open a Java 8 Stream to process our singular test case functionally.
            new TestCase(new int[][]{ // Instantiate the test case representing the exact data from the user's provided image.
                {0, 1, 2, 3, 4}, // First row of the grid as shown in the screenshot.
                {24, 16, 22, 21, 5}, // Second row of the grid as shown in the screenshot.
                {12, 13, 14, 15, 23}, // Third row of the grid as shown in the screenshot.
                {11, 17, 18, 19, 20}, // Fourth row of the grid as shown in the screenshot.
                {10, 9, 8, 7, 6} // Fifth row of the grid as shown in the screenshot.
            }, 16, "Screenshot 2026-08-05 at 1.34.36 AM.jpg Example") // Supply the expected output of 16 and name the test verbatim after the file.
        ).forEach(test -> { // Iterate over the stream elements (the single test case) using forEach.
            var result = leastTime(test.grid); // Execute our pathfinding algorithm on the provided test grid.
            var status = (result == test.expected) ? "PASS" : "FAIL"; // Use a ternary operator to verify if the computed result matches the expected answer.
            System.out.printf("[%s] %s | Expected: %d, Got: %d%n", status, test.name, test.expected, result); // Format and print the test outcome to the console.
        }); // Close the Stream execution block.
    } // Close main method.

    record Cell(int r, int c, int maxTime) implements Comparable<Cell> { // Use Java 21 record for a concise, immutable data carrier representing a grid cell.
        @Override // Override the default compareTo method to define custom sorting logic for our priority queue.
        public int compareTo(Cell other) { // Method to compare this cell with another cell based on the maximum time encountered.
            return Integer.compare(this.maxTime, other.maxTime); // Sort ascending by maxTime so the path with the least wait time is processed first.
        } // Close compareTo method.
    } // Close Cell record.
} // Close DeliveryPathFinder class.