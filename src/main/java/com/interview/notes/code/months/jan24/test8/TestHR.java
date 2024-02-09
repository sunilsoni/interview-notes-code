package com.interview.notes.code.months.jan24.test8;

import java.util.ArrayList;
import java.util.List;

public class TestHR {
    public static int equalTeamSkill(List<Integer> teamA, List<Integer> teamB) {
        int sumA = 0, sumB = 0;
        for (int skill : teamA) sumA += skill;
        for (int skill : teamB) sumB += skill;

        if (sumA == sumB) return 0;
        if (sumA > sumB) return findMinSkillAddition(teamB, teamA, sumA - sumB);
        return findMinSkillAddition(teamA, teamB, sumB - sumA);
    }

    private static int findMinSkillAddition(List<Integer> smaller, List<Integer> larger, int diff) {
        int minAddition = Integer.MAX_VALUE;
        for (int i = 0; i < smaller.size(); i++) {
            if (smaller.get(i) == 0) {
                for (int j = 1; j <= 10; j++) {
                    if (larger.contains(diff + j)) {
                        minAddition = Math.min(minAddition, j);
                    }
                }
            }
        }
        return (minAddition == Integer.MAX_VALUE) ? -1 : minAddition;
    }

    public static void main(String[] args) {

        // Example cases
        List<Integer> teamA1 = new ArrayList<Integer>(List.of(1, 2, 3, 0));
        List<Integer> teamB1 = new ArrayList<Integer>(List.of(5, 0, 0));
        System.out.println(equalTeamSkill(teamA1, teamB1)); // Should output 7

        List<Integer> teamA2 = new ArrayList<Integer>(List.of(1, 2, 3));
        List<Integer> teamB2 = new ArrayList<Integer>(List.of(0, 10));
        System.out.println(equalTeamSkill(teamA2, teamB2)); // Should output -1


        // Example cases
       /* List<Integer> teamA1 = new ArrayList<Integer>(List.of(5, 10, 0, 4));
        List<Integer> teamB1 = new ArrayList<Integer>(List.of(2, 4, 0, 5, 0));
        System.out.println(equalTeamSkill(teamA1, teamB1)); // Output for the first example

        List<Integer> teamA2 = new ArrayList<Integer>(List.of(1, 2, 3, 0));
        List<Integer> teamB2 = new ArrayList<Integer>(List.of(5, 0, 0));
        System.out.println(equalTeamSkill(teamA2, teamB2)); // Output for the second example

        List<Integer> teamA3 = new ArrayList<Integer>(List.of(1, 2, 3));
        List<Integer> teamB3 = new ArrayList<Integer>(List.of(0, 10));
        System.out.println(equalTeamSkill(teamA3, teamB3)); // Output for the third example

        */
    }

}
