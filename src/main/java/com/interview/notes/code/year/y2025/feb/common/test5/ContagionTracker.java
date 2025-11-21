package com.interview.notes.code.year.y2025.feb.common.test5;

import java.util.*;

public class ContagionTracker {
    public static List<Integer> trackDisease(int[][] interactions, int initialCarrier) {
        // Use Set to avoid duplicates and maintain infected people
        Set<Integer> infected = new HashSet<>();
        infected.add(initialCarrier);

        // For each time step
        for (int step = 0; step < interactions[0].length; step++) {
            Set<Integer> newInfected = new HashSet<>();

            // Check each person's interaction
            for (int person = 0; person < interactions.length; person++) {
                int interaction = interactions[person][step];

                // If interaction happened (-1 means no interaction)
                if (interaction != -1) {
                    // If either person is infected, both become infected
                    if (infected.contains(person) || infected.contains(interaction)) {
                        newInfected.add(person);
                        newInfected.add(interaction);
                    }
                }
            }
            infected.addAll(newInfected);
        }

        // Convert to sorted list for output
        List<Integer> result = new ArrayList<>(infected);
        Collections.sort(result);
        return result;
    }

    public static void main(String[] args) {
        // Test cases
        testCase1();
        testCase2();
        testCase3();
        testLargeInput();
    }

    private static void testCase1() {
        int[][] interactions = {
                {3, 4, -1, -1, -1},
                {-1, 3, 3, -1, 4},
                {1, 2, 2, 4, -1},
                {-1, 1, -1, 3, 2}
        };
        int initialCarrier = 2;
        List<Integer> result = trackDisease(interactions, initialCarrier);
        System.out.println("Test Case 1:");
        System.out.println("Expected: [2, 3, 4]");
        System.out.println("Actual: " + result);
        System.out.println("Status: " + (result.equals(Arrays.asList(2, 3, 4)) ? "PASS" : "FAIL"));
        System.out.println();
    }

    private static void testCase2() {
        // Empty party - edge case
        int[][] interactions = new int[0][0];
        List<Integer> result = trackDisease(interactions, 0);
        System.out.println("Test Case 2 (Empty party):");
        System.out.println("Expected: [0]");
        System.out.println("Actual: " + result);
        System.out.println("Status: " + (result.equals(List.of(0)) ? "PASS" : "FAIL"));
        System.out.println();
    }

    private static void testCase3() {
        // No interactions case
        int[][] interactions = {
                {-1, -1},
                {-1, -1}
        };
        List<Integer> result = trackDisease(interactions, 1);
        System.out.println("Test Case 3 (No interactions):");
        System.out.println("Expected: [1]");
        System.out.println("Actual: " + result);
        System.out.println("Status: " + (result.equals(List.of(1)) ? "PASS" : "FAIL"));
        System.out.println();
    }

    private static void testLargeInput() {
        // Large input test (1000 people, 100 rounds)
        int size = 1000;
        int rounds = 100;
        int[][] largeInteractions = new int[size][rounds];
        // Fill with some random interactions
        Random rand = new Random();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < rounds; j++) {
                largeInteractions[i][j] = rand.nextInt(3) == 0 ? -1 : rand.nextInt(size);
            }
        }

        long startTime = System.currentTimeMillis();
        List<Integer> result = trackDisease(largeInteractions, 0);
        long endTime = System.currentTimeMillis();

        System.out.println("Large Input Test:");
        System.out.println("Time taken: " + (endTime - startTime) + "ms");
        System.out.println("Number of infected: " + result.size());
        System.out.println("Status: " + (result.size() > 0 ? "PASS" : "FAIL"));
    }
}
