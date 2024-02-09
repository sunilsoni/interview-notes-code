package com.interview.notes.code.months.jan24.test9;

import java.util.List;

public class Result1 {

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
}
