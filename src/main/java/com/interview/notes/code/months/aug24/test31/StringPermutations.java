package com.interview.notes.code.months.aug24.test31;

import java.util.*;

public class StringPermutations {
    public static void printPermutations(String str) {
        char[] chars = str.toCharArray();
        Arrays.sort(chars); // Sort to handle duplicate characters
        boolean[] used = new boolean[chars.length];
        StringBuilder sb = new StringBuilder();
        generatePermutations(chars, used, sb);
    }

    private static void generatePermutations(char[] chars, boolean[] used, StringBuilder sb) {
        if (sb.length() == chars.length) {
            System.out.print(sb + " ");
            return;
        }

        for (int i = 0; i < chars.length; i++) {
            if (used[i] || (i > 0 && chars[i] == chars[i - 1] && !used[i - 1])) continue;
            used[i] = true;
            sb.append(chars[i]);
            generatePermutations(chars, used, sb);
            used[i] = false;
            sb.setLength(sb.length() - 1);
        }
    }

    public static void main(String[] args) {
        // Test cases
        String[] testCases = {"ABC", "ABCD"};

        for (String testCase : testCases) {
            System.out.println("Permutations for " + testCase + ":");
            printPermutations(testCase);
            System.out.println("\n");
        }

        // Additional test cases
        String[] additionalTests = {"A", "AB", "AAB", "AABB"};

        for (String test : additionalTests) {
            System.out.println("Permutations for " + test + ":");
            printPermutations(test);
            System.out.println("\n");
        }
    }
}
