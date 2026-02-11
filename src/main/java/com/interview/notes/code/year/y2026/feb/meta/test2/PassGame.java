package com.interview.notes.code.year.y2026.feb.meta.test2;

import java.util.Arrays;
import java.util.stream.Collectors;

public class PassGame {

    // Main solver method
    public static int[] minPasses(int n, int[][] passes) {
        // Use Java Stream to group passes: Player ID -> List of Receivers
        var graph = Arrays.stream(passes) // Stream the 2D array
                .collect(Collectors.groupingBy(p -> p[0], // Key: The sender (index 0)
                        Collectors.mapping(p -> p[1], Collectors.toList()))); // Value: List of receivers

        var dist = new int[n]; // Array to store min passes for each player
        Arrays.fill(dist, Integer.MAX_VALUE); // Fill with max value (infinity) initially
        dist[0] = 0; // Player 0 starts with the ball, so 0 passes

        for (int i = 0; i < n; i++) { // Iterate through players in order 0 to n-1
            // Check if player 'i' is reachable (has ball) and has outgoing passes
            if (dist[i] != Integer.MAX_VALUE && graph.containsKey(i)) {
                for (int receiver : graph.get(i)) { // Loop through all players 'i' passes to
                    // Update receiver distance: min of current value OR (sender + 1)
                    dist[receiver] = Math.min(dist[receiver], dist[i] + 1);
                }
            }
        }
        return dist; // Return the final array of distances
    }

    // --- Testing Infrastructure (No JUnit, Simple Main) ---

    public static void main(String[] args) {
        System.out.println("--- Starting Tests ---"); // Log start

        // Test Case 1: From the screenshot
        int[][] t1 = {{0, 1}, {1, 2}, {0, 3}, {3, 4}}; // Input passes
        check("Screenshot Case", 5, t1, new int[]{0, 1, 2, 1, 2}); // Verify expected output

        // Test Case 2: Linear Chain (0->1->2->3)
        int[][] t2 = {{0, 1}, {1, 2}, {2, 3}}; // Linear passes
        check("Linear Chain", 4, t2, new int[]{0, 1, 2, 3}); // Should be 0,1,2,3

        // Test Case 3: Skip path (0->2 is faster than 0->1->2)
        int[][] t3 = {{0, 1}, {1, 2}, {0, 2}}; // Triangle passes
        check("Shortest Path", 3, t3, new int[]{0, 1, 1}); // Player 2 gets ball from 0 directly (1 pass)

        // Test Case 4: Unreachable Player
        int[][] t4 = {{0, 1}}; // 2 is isolated
        check("Unreachable", 3, t4, new int[]{0, 1, Integer.MAX_VALUE}); // Player 2 remains MAX

        // Test Case 5: Large Data (Performance)
        testLargeData(); // Run heavy load test
    }

    // Helper method to verify and print PASS/FAIL
    static void check(String name, int n, int[][] passes, int[] expected) {
        var result = minPasses(n, passes); // Run logic
        boolean match = Arrays.equals(result, expected); // Compare arrays
        String status = match ? "PASS" : "FAIL"; // Determine status
        System.out.printf("[%s] %s: %s%n", status, name, Arrays.toString(result)); // Print result
        if (!match) System.out.println("   Expected: " + Arrays.toString(expected)); // Print error info
    }

    // Helper to generate massive data to ensure no Time Limit Exceeded
    static void testLargeData() {
        int n = 100_000; // 100k players
        int[][] passes = new int[n - 1][2]; // Array for linear passes
        for (int i = 0; i < n - 1; i++) passes[i] = new int[]{i, i + 1}; // Create 0->1->2... chain

        long start = System.currentTimeMillis(); // Start timer
        var res = minPasses(n, passes); // Run solver
        long end = System.currentTimeMillis(); // End timer

        // Check last player distance (should be n-1)
        boolean pass = (res[n - 1] == n - 1) && (end - start < 500); // Expect < 500ms
        System.out.printf("[%s] Large Data (100k): Time=%dms%n", pass ? "PASS" : "FAIL", (end - start)); // Print stats
    }
}