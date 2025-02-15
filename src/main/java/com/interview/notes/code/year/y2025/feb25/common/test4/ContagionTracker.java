package com.interview.notes.code.year.y2025.feb25.common.test4;

import java.util.Arrays;

public class ContagionTracker {

    /**
     * Simulates disease spread given handshake interactions.
     *
     * @param interactions    2D array where interactions[i][r] = partner ID (1-based) of attendee i+1 at round r, or -1 if no interaction
     * @param initialInfected 1-based index of the initially infected attendee
     * @return array of infected attendees in ascending 1-based ID
     */
    public static int[] getFinalInfected(int[][] interactions, int initialInfected) {
        int n = interactions.length;    // number of attendees
        int m = (n > 0) ? interactions[0].length : 0; // number of rounds

        // Convert initialInfected to 0-based
        int start = initialInfected - 1;

        // Track infection status
        boolean[] infected = new boolean[n];
        infected[start] = true;

        // For each round, update infection spread
        for (int round = 0; round < m; round++) {
            // We'll do a first pass to figure out which pairs might cause an infection
            // then update after collecting them all (to reflect a "simultaneous" handshake)

            // However, since infection spreads within the same round anyway,
            // and we simply set both participants to infected if either is infected,
            // we can do it directly in a single pass.

            for (int i = 0; i < n; i++) {
                int partner = interactions[i][round];
                if (partner != -1) {
                    // partner is 1-based in the array, convert to 0-based
                    partner = partner - 1;

                    // If either is infected, both become infected
                    if (infected[i] || infected[partner]) {
                        infected[i] = true;
                        infected[partner] = true;
                    }
                }
            }
        }

        // Build list of final infected in ascending order (convert back to 1-based)
        int count = 0;
        for (boolean b : infected) {
            if (b) count++;
        }

        int[] result = new int[count];
        int idx = 0;
        for (int i = 0; i < n; i++) {
            if (infected[i]) {
                result[idx++] = i + 1; // convert to 1-based
            }
        }

        return result;
    }

    /**
     * A simple main method to demonstrate and test the solution without using any testing framework.
     */
    public static void main(String[] args) {
        // Test Case 1 (from the example in the problem statement):
        // 4 attendees, 5 rounds of interaction
        int[][] exampleInteractions = {
                {3, 4, -1, -1, -1},  // Attendee 1's partners by round
                {-1, 3, 3, -1, 4},  // Attendee 2's partners
                {1, 2, 2, 4, -1},   // Attendee 3's partners
                {-1, 1, -1, 3, 2}, // Attendee 4's partners
        };

        // Suppose attendee #2 was the initial carrier
        int initialCarrier = 2;
        int[] infectedResult = getFinalInfected(exampleInteractions, initialCarrier);

        System.out.println("=== Test Case 1 ===");
        System.out.println("Final infected: " + Arrays.toString(infectedResult));
        System.out.println("Expected (as per problem statement): [2, 3, 4]");

        // Check pass/fail logic (in real scenario, you'd do more robust checks)
        int[] expected = {2, 3, 4};
        if (Arrays.equals(infectedResult, expected)) {
            System.out.println("Result: PASS");
        } else {
            System.out.println("Result: FAIL");
        }

        // -----------------------------------------------------------

        // Test Case 2 (Edge Case: No handshakes)
        // 3 attendees, 3 rounds, but everyone has -1 => no interaction
        int[][] noHandshakes = {
                {-1, -1, -1},
                {-1, -1, -1},
                {-1, -1, -1}
        };
        // Attendee #1 is initially infected
        int[] resultNoHandshakes = getFinalInfected(noHandshakes, 1);

        System.out.println("\n=== Test Case 2 (No Handshakes) ===");
        System.out.println("Final infected: " + Arrays.toString(resultNoHandshakes));
        System.out.println("Expected: [1]");
        if (resultNoHandshakes.length == 1 && resultNoHandshakes[0] == 1) {
            System.out.println("Result: PASS");
        } else {
            System.out.println("Result: FAIL");
        }

        // -----------------------------------------------------------

        // Test Case 3 (Large Data Simulation - Quick Check)
        // For demonstration, just create a bigger random array or repeated pattern
        // In real usage, we might parse from a file or generate systematically
        int n = 1000;
        int m = 1000;
        int[][] largeTest = new int[n][m];

        // Fill it with all -1 for simplicity (meaning no spread beyond initial)
        // Alternatively, you can create random pairs if you want more stress testing
        for (int i = 0; i < n; i++) {
            Arrays.fill(largeTest[i], -1);
        }

        // Infect 1-based ID = 500
        int[] largeResult = getFinalInfected(largeTest, 500);

        System.out.println("\n=== Test Case 3 (Large 1000x1000) ===");
        // We expect only #500 to remain infected if no one ever shakes hands
        if (largeResult.length == 1 && largeResult[0] == 500) {
            System.out.println("Result: PASS");
        } else {
            System.out.println("Result: FAIL (unexpected infection spread)");
        }
    }
}
