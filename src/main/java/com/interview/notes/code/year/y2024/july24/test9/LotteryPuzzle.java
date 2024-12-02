package com.interview.notes.code.year.y2024.july24.test9;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LotteryPuzzle {

    public static int solve(List<Integer> ar) {
        Set<Integer> set = new HashSet<>(ar);
        int longestStreak = 0;

        for (int num : set) {
            if (!set.contains(num - 1)) { // start of a sequence
                int currentNum = num;
                int currentStreak = 1;

                while (set.contains(currentNum + 1)) {
                    currentNum += 1;
                    currentStreak += 1;
                }

                longestStreak = Math.max(longestStreak, currentStreak);
            }
        }
        return longestStreak;
    }

    public static void main(String[] args) {
        // Example 1
        List<Integer> ar1 = List.of(2, 6, 1, 9, 4, 5, 3, 8);
        System.out.println("Lottery Point for example 1: " + solve(ar1));

        // Example 2
        List<Integer> ar2 = List.of(8, 4, 6, 9, 5, 2);
        System.out.println("Lottery Point for example 2: " + solve(ar2));
    }
}
