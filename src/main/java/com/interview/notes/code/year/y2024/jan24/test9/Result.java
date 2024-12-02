package com.interview.notes.code.year.y2024.jan24.test9;

import java.util.Arrays;
import java.util.List;


//Woring soln
public class Result {

    public static int equalTeamSkill(List<Integer> teamA, List<Integer> teamB) {
        int sumA = 0, sumB = 0; // Sums of team A and B
        int zerosA = 0, zerosB = 0; // Count of zeros in team A and B

        // Calculate sum and count zeros for team A
        for (int skill : teamA) {
            if (skill == 0) zerosA++;
            else sumA += skill;
        }

        // Calculate sum and count zeros for team B
        for (int skill : teamB) {
            if (skill == 0) zerosB++;
            else sumB += skill;
        }

        // Calculate the difference and the number of players needed to balance the teams
        int diff = Math.abs(sumA - sumB);
        int totalZeros = zerosA + zerosB;

        // Check if it's possible to balance the teams with the available zeros
        if (diff == 0) {
            return sumA; // Teams are already balanced
        } else if (diff <= totalZeros && ((sumA > sumB && diff <= zerosB) || (sumB > sumA && diff <= zerosA))) {
            return Math.max(sumA, sumB) + diff; // Minimum equal sum after adding players
        } else {
            return -1; // Impossible to balance
        }
    }

    public static void main(String[] args) {
        // Example 1
        List<Integer> teamA1 = Arrays.asList(1, 2, 3, 0);
        List<Integer> teamB1 = Arrays.asList(5, 0, 0);
        System.out.println("Example 1 Result: " + equalTeamSkill(teamA1, teamB1));

        // Example 2
        List<Integer> teamA2 = Arrays.asList(1, 2, 3);
        List<Integer> teamB2 = Arrays.asList(0, 10);
        System.out.println("Example 2 Result: " + equalTeamSkill(teamA2, teamB2));
    }
}
