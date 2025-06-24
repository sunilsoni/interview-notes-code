package com.interview.notes.code.year.y2025.June.amazon.test11;

import java.util.*;
import java.util.stream.*;

public class KthMostFrequentChar {

    /**
     * Finds the kth most frequent character in the given list.
     * If k is larger than the number of unique characters,
     * it “falls back” to (k–1), then (k–2), etc.
     *
     * @param list the list of characters to analyze
     * @param k    the 1-based rank to retrieve
     * @return the kth most frequent character (with fallback)
     * @throws IllegalArgumentException if k < 1
     */
    public static char findKthMostFrequent(List<Character> list, int k) {
        if (k < 1) {
            throw new IllegalArgumentException("k must be ≥ 1");
        }

        // 1) Build frequency map
        Map<Character, Long> freqMap = list.stream()
            .collect(Collectors.groupingBy(c -> c, Collectors.counting()));

        // 2) Sort entries by: frequency desc, then character desc
        List<Map.Entry<Character, Long>> sorted = freqMap.entrySet().stream()
            .sorted(
                Comparator.<Map.Entry<Character, Long>>comparingLong(Map.Entry::getValue)
                          .reversed()                      // highest frequency first
                     .thenComparing(Map.Entry::getKey)     // on tie, smallest char first
                     .reversed()                           // then reverse to get largest char first
            )
            .collect(Collectors.toList());

        // 3) If the kth exists, return it
        if (k <= sorted.size()) {
            return sorted.get(k - 1).getKey();
        }

        // 4) Otherwise fall back to (k-1)
        return findKthMostFrequent(list, k - 1);
    }

    // Simple holder for test cases
    private static class TestCase {
        final List<Character> list;
        final int k;
        final char expected;
        TestCase(List<Character> list, int k, char expected) {
            this.list = list;
            this.k = k;
            this.expected = expected;
        }
    }

    public static void main(String[] args) {
        // ── Your example ───────────────────────────────────────────────
        List<Character> example = Arrays.asList('b','a','a','c','d','d','b');
        System.out.println("Example: k=2 -> " + findKthMostFrequent(example, 2));  // → b
        System.out.println("Example: k=3 -> " + findKthMostFrequent(example, 3));  // → b (falls back)

        // ── PASS/FAIL Harness ──────────────────────────────────────────
        List<TestCase> tests = Arrays.asList(
            // In 'b a a c d d b' freqs = {d:2, b:2, a:2, c:1}
            new TestCase(example, 1, 'd'),  // highest freq & lex largest
            new TestCase(example, 2, 'b'),
            new TestCase(example, 3, 'a'),
            new TestCase(example, 4, 'c'),
            new TestCase(example, 5, 'c')   // fallback from 5→4
        );

        for (TestCase tc : tests) {
            char res = findKthMostFrequent(tc.list, tc.k);
            System.out.printf(
                "%s k=%d -> got=%c expected=%c%n",
                (res == tc.expected ? "PASS:" : "FAIL:"), tc.k, res, tc.expected
            );
        }

        // ── Large-data demo ───────────────────────────────────────────
        List<Character> large = new ArrayList<>(1_000_000);
        for (int i = 0; i < 1_000_000; i++) {
            large.add((char)('a' + (i % 26)));
        }
        System.out.println("Large test k=5 -> " + findKthMostFrequent(large, 5));
    }
}