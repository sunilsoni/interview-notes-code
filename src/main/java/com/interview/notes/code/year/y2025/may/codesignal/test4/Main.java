package com.interview.notes.code.year.y2025.may.codesignal.test4;

import java.util.*;

public class Main {

    public static String SearchingChallenge(String str) {
        int n = str.length();
        for (int len = n; len >= 2; len--) {
            Set<String> seen = new HashSet<>();
            for (int i = 0; i <= n - len; i++) {
                String substr = str.substring(i, i + len);
                if (seen.contains(substr)) {
                    return "yes " + substr;
                }
                seen.add(substr);
            }
        }
        return "no null";
    }

    public static void main(String[] args) {
        String[] testCases = {
            "da2kr32a2",
            "sskfssbbb9bbb",
            "123224",
            "abcdabcdabcd",
            "aaa",
            "ababab",
            "aabbccdd"
        };

        String[] expected = {
            "yes a2",
            "yes bbb",
            "no null",
            "yes abcd",
            "yes aa",
            "yes abab",
            "yes aa"
        };

        for (int i = 0; i < testCases.length; i++) {
            String result = SearchingChallenge(testCases[i]);
            System.out.println("Input: " + testCases[i]);
            System.out.println("Output: " + result);
            System.out.println("Expected: " + expected[i]);
            System.out.println(result.equals(expected[i]) ? "PASS\n" : "FAIL\n");
        }
    }
}
