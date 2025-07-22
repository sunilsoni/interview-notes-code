package com.interview.notes.code.year.y2025.july.common.test3;

import java.util.Map;

public class LetterPoolCheckerArray {

    /**
     * Returns true if we can form 'word' from the letters in 'pool',
     * using a fixed 26-length count[] for a–z.
     */
    public static boolean canFormWithArray(String pool, String word) {
        // 1) Count letters in pool
        int[] cnt = new int[26];
        for (char c : pool.toCharArray()) {
            cnt[c - 'a']++;
        }

        // 2) For each char in word, decrement and check
        for (char c : word.toCharArray()) {
            if (--cnt[c - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String basePool = "entertainment";

        // small tests against basePool
        Map<String, Boolean> smallTests = Map.of(
                "mat", true,
                "maat", false,
                "pat", false
        );
        for (var e : smallTests.entrySet()) {
            boolean actual = canFormWithArray(basePool, e.getKey());
            System.out.printf(
                    "Test \"%s\" -> expected %s, got %s : %s%n",
                    e.getKey(), e.getValue(), actual,
                    actual == e.getValue() ? "PASS" : "FAIL"
            );
        }

        // large-data tests
        String bigPool = basePool.repeat(10_000);
        boolean exactMatch = canFormWithArray(bigPool, bigPool);
        System.out.printf(
                "Large exact -> expected true, got %s : %s%n",
                exactMatch, exactMatch ? "PASS" : "FAIL"
        );
        boolean oneExtra = canFormWithArray(bigPool, bigPool + "e");
        System.out.printf(
                "Large + extra -> expected false, got %s : %s%n",
                oneExtra, (!oneExtra) ? "PASS" : "FAIL"
        );
    }
}