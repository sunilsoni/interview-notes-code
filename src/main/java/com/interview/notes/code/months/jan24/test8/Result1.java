package com.interview.notes.code.months.jan24.test8;

import java.util.List;

class Result1 {

    /*
     * Complete the 'equalTeamSkill' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY teamA
     *  2. INTEGER_ARRAY teamB
     */

    public static int equalTeamSkill(List<Integer> teamA, List<Integer> teamB) {
        int sumA = teamA.stream().mapToInt(Integer::intValue).sum();
        int sumB = teamB.stream().mapToInt(Integer::intValue).sum();
        
        // If one team is stronger than the other by an amount that is not in the weaker team, return -1
        if (Math.abs(sumA - sumB) > teamA.stream().filter(i -> i > 0).max(Integer::compare).orElse(0) &&
            Math.abs(sumA - sumB) > teamB.stream().filter(i -> i > 0).max(Integer::compare).orElse(0)) {
            return -1;
        }

        int diff = Math.abs(sumA - sumB);
        for (int i = 1; i <= diff; i++) {
            if ((diff % i == 0) && (teamA.contains(i) || teamB.contains(i))) {
                return sumA > sumB ? sumA : sumB;
            }
        }

        return -1;
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
