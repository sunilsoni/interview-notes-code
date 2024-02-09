package com.interview.notes.code.months.jan24.test8;

import java.util.List;

class Result2 {

    public static int equalTeamSkill(List<Integer> teamA, List<Integer> teamB) {
        int sumA = teamA.stream().mapToInt(Integer::intValue).sum();
        int sumB = teamB.stream().mapToInt(Integer::intValue).sum();

        if (sumA == sumB) return sumA; // Already equal

        if (sumA < sumB) {
            // Swap the teams to always have teamA with the greater sum
            List<Integer> temp = teamA;
            teamA = teamB;
            teamB = temp;
            int tempSum = sumA;
            sumA = sumB;
            sumB = tempSum;
        }

        // Now, sumA is greater than sumB, find the smallest player skill to add to teamB to equalize
        int skillToAdd = sumA - sumB;
        for (int skill = 1; skill <= skillToAdd; skill++) {
            if (teamB.contains(skill) && !teamA.contains(skill)) {
                return sumA; // Found the skill to add to teamB to equalize
            }
        }

        return -1; // No single player skill can equalize the team skills
    }

    public static void main(String[] args) {
        // Sample Test Case 1
        List<Integer> teamA1 = List.of(1, 2, 3, 0);
        List<Integer> teamB1 = List.of(5, 0, 0);
        System.out.println(equalTeamSkill(teamA1, teamB1)); // Should output 7

        // Sample Test Case 2
        List<Integer> teamA2 = List.of(1, 2, 3);
        List<Integer> teamB2 = List.of(0, 10);
        System.out.println(equalTeamSkill(teamA2, teamB2)); // Should output -1
    }
}
