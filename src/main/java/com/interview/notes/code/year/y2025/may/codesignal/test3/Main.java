package com.interview.notes.code.year.y2025.may.codesignal.test3;

import java.util.stream.IntStream;

//WORKING 100%

public class Main {

    // core routine
    public static String SearchingChallenge(String str) {
        int n = str.length();

        // try longer substrings first
        for (int len = n - 1; len >= 2; len--) {
            for (int i = 0; i + len <= n; i++) {
                String sub = str.substring(i, i + len);

                long repeats = IntStream.range(0, n - len + 1)
                        .filter(j -> str.startsWith(sub, j))
                        .count();

                if (repeats >= 2) {
                    return "yes " + sub;
                }
            }
        }
        return "no null";
    }

    // minimal self-contained test harness (no JUnit)
    public static void main(String[] args) {

        String[] inputs = {
                "da2kr32a2",
                "sskfssbbb9bbb",
                "123224",
                "aaaa",
                "ababab",
                "abcabcabcabc",     // worst-case length = 12
                "a1a1b2b2c3c3"
        };

        String[] expected = {
                "yes a2",
                "yes bbb",
                "no null",
                "yes aaa",
                "yes abab",
                "yes abcabc",
                "yes a1"
        };

        System.out.println("Testing SearchingChallenge\n");

        boolean allPass = true;
        for (int k = 0; k < inputs.length; k++) {
            String result = SearchingChallenge(inputs[k]);
            boolean pass = result.equals(expected[k]);
            allPass &= pass;
            System.out.printf("Input: %-15s  Expected: %-10s  Got: %-10s  %s\n",
                    inputs[k], expected[k], result, pass ? "PASS" : "FAIL");
        }

        // quick random stress check (length 20, 100 000 strings)
        // comment-in if you want to see it; left out for speed in normal runs
        /*
        java.util.Random rnd = new java.util.Random();
        for (int t = 0; t < 100_000; t++) {
            StringBuilder sb = new StringBuilder(20);
            for (int i = 0; i < 20; i++) sb.append((char) ('a' + rnd.nextInt(26)));
            SearchingChallenge(sb.toString());   // must not throw
        }
        */

        System.out.println("\nAll tests passed: " + allPass);
    }
}
