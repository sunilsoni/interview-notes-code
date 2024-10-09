package com.interview.notes.code.months.oct24.amz.test3;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        // Test cases
        String testCase1 = "aaaaaid";  // Expected: 3
        String testCase2 = "aidfg";    // Expected: 4
        String testCase3 = "dfdffdfi"; // Expected: 13

        System.out.println("Test Case 1 (Expected: 3): " + getDominantStringCount(testCase1));
        System.out.println("Test Case 2 (Expected: 4): " + getDominantStringCount(testCase2));
        System.out.println("Test Case 3 (Expected: 13): " + getDominantStringCount(testCase3));
    }
    public static long getDominantStringCount(String s) {
        int n = s.length();
        long result = 0;
        int maxLen = Math.min(n, 1000); // Adjust as needed based on constraints

        // Precompute prefix sums
        int[][] prefixCounts = new int[26][n + 1];
        for (int i = 0; i < n; i++) {
            for (int c = 0; c < 26; c++) {
                prefixCounts[c][i + 1] = prefixCounts[c][i];
            }
            char ch = s.charAt(i);
            if (ch >= 'a' && ch <= 'z') {
                prefixCounts[ch - 'a'][i + 1]++;
            }
        }

        // Check all substrings of even lengths up to maxLen
        for (int len = 2; len <= maxLen; len += 2) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len;
                boolean counted = false;
                for (int c = 0; c < 26; c++) {
                    int count = prefixCounts[c][j] - prefixCounts[c][i];
                    if (count == len / 2) {
                        result++;
                        break; // Only count the substring once
                    }
                }
            }
        }

        return result;
    }

}
