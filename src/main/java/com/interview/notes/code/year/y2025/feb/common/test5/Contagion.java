package com.interview.notes.code.year.y2025.feb.common.test5;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Contagion {
    public static List<Integer> trackDisease(int[][] interactions, int initialCarrier) {
        Set<Integer> infected = new HashSet<>();
        infected.add(initialCarrier);

        // Process each round of interactions
        for (int round = 0; round < interactions[0].length; round++) {
            Set<Integer> newInfected = new HashSet<>();

            // Check each person's interaction in this round
            for (int person = 0; person < interactions.length; person++) {
                int interaction = interactions[person][round];

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

        return new ArrayList<>(infected).stream()
                .sorted()
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        // Test case from the problem
        int[][] test1 = {
                {3, 4, -1, -1, -1},
                {-1, 3, 3, -1, 4},
                {1, 2, 2, 4, -1},
                {-1, 1, -1, 3, 2}
        };

        // Additional test cases
        int[][] test2 = {
                {2, -1},
                {1, 3},
                {-1, 1},
                {2, -1}
        };

        // Run tests
        System.out.println("Test 1 (Initial carrier 2): " +
                trackDisease(test1, 2));
        System.out.println("Test 2 (Initial carrier 0): " +
                trackDisease(test2, 0));
    }
}
