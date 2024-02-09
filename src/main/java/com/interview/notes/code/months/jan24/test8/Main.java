package com.interview.notes.code.months.jan24.test8;

import java.util.List;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // Example cases
        List<Integer> teamA1 = new ArrayList<>(List.of(1, 2, 3, 0));
        List<Integer> teamB1 = new ArrayList<>(List.of(5, 0, 0));
        System.out.println(equalTeamSkill(teamA1, teamB1)); // Should output 7

        List<Integer> teamA2 = new ArrayList<>(List.of(1, 2, 3));
        List<Integer> teamB2 = new ArrayList<>(List.of(0, 10));
        System.out.println(equalTeamSkill(teamA2, teamB2)); // Should output -1
    }

    public static int equalTeamSkill(List<Integer> teamA, List<Integer> teamB) {
        int sumA = teamA.stream().mapToInt(Integer::intValue).sum();
        int sumB = teamB.stream().mapToInt(Integer::intValue).sum();
        
        if(sumA == sumB) return 0;

        if(sumA > sumB) return minSkill(teamA, teamB, sumA - sumB);
        else return minSkill(teamB, teamA, sumB - sumA);
    }

    private static int minSkill(List<Integer> bigger, List<Integer> smaller, int diff) {
        int minSkill = -1;
        for (int i = 0; i < smaller.size(); i++) {
            if (smaller.get(i) == 0) {
                for (int j = 1; j <= 10; j++) {
                    if (bigger.contains(diff - j)) {
                        if (minSkill == -1 || j < minSkill) {
                            minSkill = j;
                        }
                    }
                }
            }
        }
        return minSkill;
    }
}
