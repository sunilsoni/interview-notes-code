package com.interview.notes.code.year.y2025.april.snowflake.test1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class AnagramFinder {

    /**
     * Finds all start indices of anagrams of 'p' inside 's'.
     *
     * @param s source text
     * @param p pattern (target)
     * @return list of 0-based indices
     */
    public static List<Integer> findAnagramIndices(String s, String p) {

        List<Integer> result = new ArrayList<>();          // output list

        // Guard clauses: null or pattern longer than text
        if (s == null || p == null || s.length() < p.length())
            return result;

        int[] freqP = new int[26];                         // pattern letter counts
        int[] window = new int[26];                        // sliding window counts
        int m = p.length();                                // window size = pattern size

        // Fill pattern counter using Stream API
        p.chars()
                .map(Character::toLowerCase)                      // lower-case each char
                .forEach(c -> freqP[c - 'a']++);                  // increment its bucket

        // Pre-fill window with first m-1 chars
        for (int i = 0; i < m - 1; i++) {
            char c = s.charAt(i);
            if (Character.isLetter(c))
                window[Character.toLowerCase(c) - 'a']++;
        }

        // Slide across text: i is right edge, start is left edge
        for (int i = m - 1, start = 0; i < s.length(); i++, start++) {

            // 1) add new right-edge char
            char add = s.charAt(i);
            if (Character.isLetter(add))
                window[Character.toLowerCase(add) - 'a']++;

            // 2) compare counters – Arrays.equals is O(26) = O(1)
            if (Arrays.equals(freqP, window))
                result.add(start);

            // 3) remove char that leaves on the left
            char remove = s.charAt(start);
            if (Character.isLetter(remove))
                window[Character.toLowerCase(remove) - 'a']--;
        }
        return result;
    }

    public static void main(String[] args) {

        List<TestCase> cases = Arrays.asList(
                new TestCase("arts of grats and tars are xartsr",
                        "art",
                        Arrays.asList(0, 9, 18, 28)),
                new TestCase("aaaaa", "aa", Arrays.asList(0, 1, 2, 3)),
                new TestCase("", "a", Collections.emptyList()),
                new TestCase("short", "longer", Collections.emptyList())
        );

        // Large-data performance case: 100 000 letters, pattern absent
        StringBuilder big = new StringBuilder(100_000);
        IntStream.range(0, 100_000).forEach(i -> big.append((char) ('a' + (i % 26))));
        cases.add(new TestCase(big.toString(), "xyz", Collections.emptyList()));

        // Run every case
        cases.forEach(AnagramFinder::runTest);
    }

    // Executes one case and prints PASS / FAIL
    private static void runTest(TestCase tc) {
        long t0 = System.currentTimeMillis();
        List<Integer> actual = findAnagramIndices(tc.source, tc.target);
        long dt = System.currentTimeMillis() - t0;
        System.out.println(
                (actual.equals(tc.expected) ? "PASS" : "FAIL")
                        + " | " + dt + " ms | target=\"" + tc.target + "\""
                        + " | expected=" + tc.expected + " | actual=" + actual
        );
    }

    // ---------- Simple Test Harness (no JUnit) ---------- //
    private static class TestCase {
        String source, target;
        List<Integer> expected;

        TestCase(String s, String t, List<Integer> e) {
            source = s;
            target = t;
            expected = e;
        }
    }
}