package com.interview.notes.code.months.sept24.test5;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        // Test cases
        String[][] testCases = {
                {"abc", "pqr"},
                {"ab", "pqrs"},
                {"abcd", "pq"},
                {"", "pqr"},
                {"abc", ""}
        };

        for (int i = 0; i < testCases.length; i++) {
            String s1 = testCases[i][0];
            String s2 = testCases[i][1];
            String result = alternateMerge(s1, s2);
            System.out.println("Test Case " + (i + 1) + ":");
            System.out.println("Input: " + Arrays.toString(testCases[i]));
            System.out.println("Output: " + result);
            System.out.println();
        }
    }

    /**
     * Merges two strings alternately.
     *
     * @param s1 The first string
     * @param s2 The second string
     * @return The merged string
     */
    public static String alternateMerge(String s1, String s2) {
        StringBuilder result = new StringBuilder();
        int i = 0, j = 0;

        // Alternate merging until one string is exhausted
        while (i < s1.length() && j < s2.length()) {
            result.append(s1.charAt(i++));
            result.append(s2.charAt(j++));
        }

        // Append remaining characters from s1, if any
        while (i < s1.length()) {
            result.append(s1.charAt(i++));
        }

        // Append remaining characters from s2, if any
        while (j < s2.length()) {
            result.append(s2.charAt(j++));
        }

        return result.toString();
    }
}
