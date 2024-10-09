package com.interview.notes.code.months.oct24.amz.test3;

import java.util.HashMap;
import java.util.Map;

public class Main1 {

    public static void main(String[] args) {
        // Test cases
        String testCase1 = "aaaaaid";  // Expected: 3
        String testCase2 = "aidfg";    // Expected: 4
        String testCase3 = "dfdffdfi"; // Expected: 13

        System.out.println("Test Case 1 (Expected: 3): " + getDominantStringCount(testCase1));
        System.out.println("Test Case 2 (Expected: 4): " + getDominantStringCount(testCase2));
        System.out.println("Test Case 3 (Expected: 13): " + getDominantStringCount(testCase3));
    }

    public static int getDominantStringCount(String s) {
        int n = s.length();
        int result = 0;

        // Limit the maximum length to 1000 to handle larger inputs efficiently
        int maxLength = Math.min(n, 1000);

        // Precompute prefix sums for each character
        int[][] prefixCounts = new int[26][n + 1];
        for (int c = 0; c < 26; c++) {
            for (int i = 0; i < n; i++) {
                prefixCounts[c][i + 1] = prefixCounts[c][i] + (s.charAt(i) - 'a' == c ? 1 : 0);
            }
        }

        // For all even lengths from 2 to maxLength
        for (int L = 2; L <= maxLength; L += 2) {
            Map<String, Integer> map = new HashMap<>();
            for (int i = 0; i <= n - L; i++) {
                int j = i + L;
                // For substring s[i..j-1]
                // Check if any character occurs exactly L / 2 times
                boolean found = false;
                for (int c = 0; c < 26; c++) {
                    int count = prefixCounts[c][j] - prefixCounts[c][i];
                    if (count == L / 2) {
                        found = true;
                        break;
                    }
                }
                if (found) {
                    result++;
                }
            }
        }

        return result;
    }
}
