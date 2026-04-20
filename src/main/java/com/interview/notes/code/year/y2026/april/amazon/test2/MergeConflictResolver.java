package com.interview.notes.code.year.y2026.april.amazon.test2;

import java.util.List;

public class MergeConflictResolver {

    public static int getMinimumConflicts(String primary, String secondary) {
        int lenA = primary.length();
        int lenB = secondary.length();
        var remA = new int[lenA + 1][26];
        var remB = new int[lenB + 1][26];

        for (int i = lenA - 1; i >= 0; i--) {
            for (int c = 0; c < 26; c++) {
                remA[i][c] = remA[i + 1][c] + (primary.charAt(i) - 'a' < c ? 1 : 0);
            }
        }

        for (int j = lenB - 1; j >= 0; j--) {
            for (int c = 0; c < 26; c++) {
                remB[j][c] = remB[j + 1][c] + (secondary.charAt(j) - 'a' < c ? 1 : 0);
            }
        }

        var dp = new int[lenA + 1][lenB + 1];
        
        for (int i = lenA; i >= 0; i--) {
            for (int j = lenB; j >= 0; j--) {
                if (i == lenA && j == lenB) {
                    continue;
                }

                int optA = i < lenA 
                    ? dp[i + 1][j] + remA[i + 1][primary.charAt(i) - 'a'] + remB[j][primary.charAt(i) - 'a'] 
                    : Integer.MAX_VALUE;

                int optB = j < lenB 
                    ? dp[i][j + 1] + remB[j + 1][secondary.charAt(j) - 'a'] + remA[i][secondary.charAt(j) - 'a'] 
                    : Integer.MAX_VALUE;

                dp[i][j] = Math.min(optA, optB);
            }
        }

        return dp[0][0];
    }

    public static void main(String[] args) {
        record TestCase(String primary, String secondary, int expected) {}

        var testCases = List.of(
            new TestCase("dae", "add", 1),
            new TestCase("aaa", "abb", 0),
            new TestCase("zc", "d", 2),
            new TestCase("ba", "ba", 3),
            new TestCase("a".repeat(1000), "b".repeat(1000), 0),
            new TestCase("z".repeat(1000), "a".repeat(1000), 0)
        );

        testCases.stream().forEach(test -> {
            int result = getMinimumConflicts(test.primary(), test.secondary());
            boolean isPass = result == test.expected();
            
            String primaryStr = test.primary().length() > 10 ? "LARGE_DATA_PRIMARY" : test.primary();
            String secondaryStr = test.secondary().length() > 10 ? "LARGE_DATA_SECONDARY" : test.secondary();
            
            System.out.printf("Status: %-4s | Primary: %-20s | Secondary: %-20s | Expected: %d | Actual: %d%n",
                isPass ? "PASS" : "FAIL",
                primaryStr,
                secondaryStr,
                test.expected(),
                result
            );
        });
    }
}