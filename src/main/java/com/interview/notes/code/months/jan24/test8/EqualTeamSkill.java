package com.interview.notes.code.months.jan24.test8;

import java.util.List;

public class EqualTeamSkill {

    public static int equalTeamSkill(int[] teamA, int[] teamB) {
        // Calculate total skill levels for each team
        int totalA = 0;
        int totalB = 0;
        for (int i = 0; i < teamA.length; i++) {
            if (teamA[i] > 0) {
                totalA += teamA[i];
            }
        }
        for (int i = 0; i < teamB.length; i++) {
            if (teamB[i] > 0) {
                totalB += teamB[i];
            }
        }

        // Check if initial skill levels are equal
        if (totalA != totalB) {
            return -1;
        }

        // Count missing players
        int missingA = 0;
        int missingB = 0;
        for (int skill : teamA) {
            if (skill == 0) {
                missingA++;
            }
        }
        for (int skill : teamB) {
            if (skill == 0) {
                missingB++;
            }
        }

        // Check if missing players are equal
        if (missingA != missingB) {
            return -1;
        }

        // Return existing total if both teams have missing players
        if (missingA > 0) {
            return totalA;
        }

        // Otherwise, teams are already equal
        return totalA;
    }

    public static void main(String[] args) {
        int[] teamA = {5, 10, 0, 4};
        int[] teamB = {2, 4, 0, 5, 0};

        int result = equalTeamSkill(teamA, teamB);
        System.out.println(result); // Output: 20

        // Sample Test Case 1
        int[] teamA1 = {1, 2, 3, 0};
        int[] teamB1 = { 5, 0, 0};
        System.out.println(equalTeamSkill(teamA, teamB)); // Should output 7

        // Sample Test Case 2
        List<Integer> teamA2 = List.of(1, 2, 3);
        List<Integer> teamB2 = List.of(0, 10);
       // System.out.println(equalTeamSkill(teamA2, teamB2)); // Should output -1
    }
}
