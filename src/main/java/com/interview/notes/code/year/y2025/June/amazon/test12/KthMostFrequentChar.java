package com.interview.notes.code.year.y2025.June.amazon.test12;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

        // 1. Build frequency map
        Map<Character, Long> freqMap = list.stream()
                .collect(Collectors.groupingBy(c -> c, Collectors.counting()));

        // 2. Sort entries by (frequency desc, char desc)
        List<Map.Entry<Character, Long>> sorted = freqMap.entrySet().stream()
                .sorted((e1, e2) -> {
                    int cmpFreq = e2.getValue().compareTo(e1.getValue()); // descending freq
                    if (cmpFreq != 0) {
                        return cmpFreq;
                    }
                    // tie → descending character
                    return e2.getKey().compareTo(e1.getKey());
                })
                .collect(Collectors.toList());

        // 3. If kth exists, return it
        if (k <= sorted.size()) {
            return sorted.get(k - 1).getKey();
        }
        // 4. Otherwise fallback to k–1
        return findKthMostFrequent(list, k - 1);
    }

    public static void main(String[] args) {
        // ── Your example ───────────────────────────────────────────────
        List<Character> example = Arrays.asList('b', 'a', 'a', 'c', 'd', 'd', 'b');
        System.out.println("Example: k=2 -> " + findKthMostFrequent(example, 2));  // → b
        System.out.println("Example: k=3 -> " + findKthMostFrequent(example, 3));  // → b (falls back)

        // ── PASS/FAIL Harness ──────────────────────────────────────────
        List<TestCase> tests = Arrays.asList(
                new TestCase(example, 1, 'd'),  // d,b,a all freq=2 → first = 'd'
                new TestCase(example, 2, 'b'),  // second = 'b'
                new TestCase(example, 3, 'a'),  // third = 'a'
                // tie scenario: x,y,x,z,y,z,z → freq z=3, x=2, y=2 → 2nd → 'x'
                new TestCase(Arrays.asList('x', 'y', 'x', 'z', 'y', 'z', 'z'), 2, 'x')
        );

        for (TestCase tc : tests) {
            char res = findKthMostFrequent(tc.list, tc.k);
            System.out.println(
                    (res == tc.expected ? "PASS: " : "FAIL: ")
                            + "k=" + tc.k + " got=" + res + " expected=" + tc.expected
            );
        }

        // ── Large-data demo ───────────────────────────────────────────
        List<Character> large = new ArrayList<>(1_000_000);
        for (int i = 0; i < 1_000_000; i++) {
            large.add((char) ('a' + (i % 26)));
        }
        System.out.println("Large test k=5 -> " + findKthMostFrequent(large, 5));
    }

    // Simple holder for tests
    private record TestCase(List<Character> list, int k, char expected) {
    }
}