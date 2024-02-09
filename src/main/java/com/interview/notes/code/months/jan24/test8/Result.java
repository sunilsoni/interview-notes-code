package com.interview.notes.code.months.jan24.test8;

import java.util.Collections;
import java.util.List;

class Result {

    public static int equalTeamSkill1(List<Integer> teamA, List<Integer> teamB) {
        // Calculate the sums of teamA and teamB
        int sumA = teamA.stream().mapToInt(Integer::intValue).sum();
        int sumB = teamB.stream().mapToInt(Integer::intValue).sum();

        // If the sums are already equal, return the sum
        if (sumA == sumB) {
            return sumA;
        }

        // Calculate the differences and find the minimum additional skill required
        int diff = Math.abs(sumA - sumB);
        int minAdditionalSkill = Integer.MAX_VALUE;

        // Sort teamA and teamB to start trying to add the largest skills available
        Collections.sort(teamA, Collections.reverseOrder());
        Collections.sort(teamB, Collections.reverseOrder());

        // Try to add skills from teamB to teamA and vice versa to balance the teams
        for (int skill : teamA) {
            if (skill > 0 && diff >= skill && (sumB + skill) <= sumA) {
                minAdditionalSkill = Math.min(minAdditionalSkill, sumA);
            }
        }

        for (int skill : teamB) {
            if (skill > 0 && diff >= skill && (sumA + skill) <= sumB) {
                minAdditionalSkill = Math.min(minAdditionalSkill, sumB);
            }
        }

        // If it's possible to balance the teams, return the new sum, else return -1
        return minAdditionalSkill == Integer.MAX_VALUE ? -1 : minAdditionalSkill;
    }

    public static int equalTeamSkill(List<Integer> teamA, List<Integer> teamB) {
        // Write your code here
        // Find the total skill of each team
        int sumA = 0;
        int sumB = 0;
        for (int a : teamA) {
            if (a > 0) {
                sumA += a;
            }
        }
        for (int b : teamB) {
            if (b > 0) {
                sumB += b;
            }
        }
        // Find the number of empty positions in each team
        int zeroA = 0;
        int zeroB = 0;
        for (int a : teamA) {
            if (a == 0) {
                zeroA++;
            }
        }
        for (int b : teamB) {
            if (b == 0) {
                zeroB++;
            }
        }
        // Sort the arrays in ascending order
        Collections.sort(teamA);
        Collections.sort(teamB);
        // Fill the empty positions in each team with the minimum possible skill level
        int skill = 1;
        while (zeroA > 0 || zeroB > 0) {
            if (zeroA > 0) {
                teamA.set(teamA.size() - zeroA, skill);
                sumA += skill;
                zeroA--;
            }
            if (zeroB > 0) {
                teamB.set(teamB.size() - zeroB, skill);
                sumB += skill;
                zeroB--;
            }
            skill++;
        }
        // Check if the total skill of both teams are equal
        if (sumA == sumB) {
            return sumA;
        } else {
            return -1;
        }
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
