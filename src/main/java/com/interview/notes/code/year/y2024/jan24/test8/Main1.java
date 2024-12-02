package com.interview.notes.code.year.y2024.jan24.test8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main1 {
    public static void main(String[] args) {
        // Example cases
        List<Integer> teamA1 = new ArrayList<Integer>(List.of(1, 2, 3, 0));
        List<Integer> teamB1 = new ArrayList<Integer>(List.of(5, 0, 0));
        System.out.println(equalTeamSkill(teamA1, teamB1)); // Should output 7

        List<Integer> teamA2 = new ArrayList<Integer>(List.of(1, 2, 3));
        List<Integer> teamB2 = new ArrayList<Integer>(List.of(0, 10));
        System.out.println(equalTeamSkill(teamA2, teamB2)); // Should output -1
    }

    public static int equalTeamSkill(List<Integer> teamA, List<Integer> teamB) {
        // Calculate the sum of skills for both teams
        int sumA = teamA.stream().mapToInt(Integer::intValue).sum();
        int sumB = teamB.stream().mapToInt(Integer::intValue).sum();

        // Sort both lists to optimize the search of skill values
        Collections.sort(teamA);
        Collections.sort(teamB);

        // Determine the difference in sums and which team has a larger total sum
        int diff = Math.abs(sumA - sumB);

        // If the difference is zero, no additional players are needed
        if (diff == 0) return 0;

        // If the sum of team A is larger, we'll try to add players to team B, and vice versa
        if (sumA > sumB) {
            return findMinimumSkillToAdd(teamB, diff);
        } else {
            return findMinimumSkillToAdd(teamA, diff);
        }
    }

    private static int findMinimumSkillToAdd(List<Integer> team, int diff) {
        // Find the minimum skill that needs to be added to a team to equalize the sum with the other team
        int minSkillToAdd = -1; // Start with -1, which indicates it's not possible to equalize the teams
        for (int i = 0; i < team.size(); i++) {
            if (team.get(i) == 0) {
                // We can only add a player to an empty spot (denoted by 0)
                for (int skillToAdd = 1; skillToAdd <= 10; skillToAdd++) {
                    // Check if adding this skill will equalize or exceed the skill difference
                    if (skillToAdd >= diff) {
                        // If we haven't set the minSkillToAdd yet or we've found a smaller skill value to add
                        if (minSkillToAdd == -1 || skillToAdd < minSkillToAdd) {
                            minSkillToAdd = skillToAdd;
                        }
                        break; // No need to check higher skills for this position
                    }
                }
            }
        }
        return minSkillToAdd;
    }
}
