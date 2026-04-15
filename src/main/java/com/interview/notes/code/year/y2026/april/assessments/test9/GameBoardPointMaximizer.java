package com.interview.notes.code.year.y2026.april.assessments.test9;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GameBoardPointMaximizer {

    public static int gainMaxValue(List<Integer> game_val, int k) {
        int n = game_val.size();
        int[] dp = new int[n];
        int max = Integer.MIN_VALUE;
        
        for (int i = n - 1; i >= 0; i--) {
            dp[i] = game_val.get(i) + (i + k < n ? dp[i + k] : 0);
            if (dp[i] > max) {
                max = dp[i];
            }
        }
        
        return max;
    }

    public static void main(String[] args) {
        test(Arrays.asList(2, -3, 4, 6, 1), 2, 7);
        
        List<Integer> largeDataPos = Collections.nCopies(1000000, 1);
        test(largeDataPos, 1, 1000000);
        
        List<Integer> largeDataNeg = Collections.nCopies(1000000, -1);
        test(largeDataNeg, 2, -1);
    }

    private static void test(List<Integer> gameVal, int k, int expected) {
        int result = gainMaxValue(gameVal, k);
        System.out.println(result == expected ? "PASS" : "FAIL");
    }
}