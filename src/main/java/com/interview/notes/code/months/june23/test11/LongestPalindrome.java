package com.interview.notes.code.months.june23.test11;

public class LongestPalindrome {
    public static void main(String[] args) {
        LongestPalindrome lp = new LongestPalindrome();
        System.out.println(lp.findLongestPalindrome("babad"));
        System.out.println(lp.findLongestPalindrome("cbbd"));
    }

    public String findLongestPalindrome(String s) {
        if (s == null || s.length() < 1) {
            return "";
        }

        int start = 0, end = 0;

        boolean[][] dp = new boolean[s.length()][s.length()];

        for (int i = 0; i < s.length(); i++) {
            dp[i][i] = true;
            for (int j = 0; j < i; j++) {
                if (s.charAt(i) == s.charAt(j) && (i - j < 2 || dp[j + 1][i - 1])) {
                    dp[j][i] = true;
                }
                if (dp[j][i] && (i - j > end - start)) {
                    start = j;
                    end = i;
                }
            }
        }
        return s.substring(start, end + 1);
    }
}
