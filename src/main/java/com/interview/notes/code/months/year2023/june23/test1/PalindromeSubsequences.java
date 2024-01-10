package com.interview.notes.code.months.year2023.june23.test1;

public class PalindromeSubsequences {
    public static int countPalindromicSubsequences(String s) {
        int n = s.length();
        Integer[][] memo = new Integer[n][n];
        return countPalindromicSubsequences(s, 0, n - 1, memo);
    }

    private static int countPalindromicSubsequences(String s, int i, int j, Integer[][] memo) {
        if (i > j) {
            return 0;
        }

        if (i == j) {
            return 1;
        }

        if (memo[i][j] != null) {
            return memo[i][j];
        }

        if (s.charAt(i) == s.charAt(j)) {
            memo[i][j] = countPalindromicSubsequences(s, i + 1, j, memo) + countPalindromicSubsequences(s, i, j - 1, memo) + 1;
        } else {
            memo[i][j] = countPalindromicSubsequences(s, i + 1, j, memo) + countPalindromicSubsequences(s, i, j - 1, memo) - countPalindromicSubsequences(s, i + 1, j - 1, memo);
        }

        return memo[i][j];
    }

    public static void main(String[] args) {
        String s = "abaaabaaaba";
        System.out.println("Number of palindrome subsequences: " + countPalindromicSubsequences(s));
    }
}
