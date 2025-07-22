package com.interview.notes.code.year.y2025.july.common.test2;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class LetterPoolChecker {

    /**
     * Returns true if we can form 'word' from the letters in 'pool'.
     * Uses Java 8 streams to count frequencies.
     */
    public static boolean canForm(String pool, String word) {
        // Build frequency map for pool
        Map<Character, Long> freq = pool.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        // For each char in word, decrement and check availability
        Map<Character, Long> needed = word.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        // Check each needed letter
        for (Map.Entry<Character, Long> e : needed.entrySet()) {
            char c = e.getKey();
            long want = e.getValue();
            long have = freq.getOrDefault(c, 0L);
            if (want > have) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String input1 = "entertainment";
        // test cases
        Map<String, Boolean> tests = new LinkedHashMap<>();
        tests.put("mat", true);
        tests.put("maat", false);
        tests.put("pat", false);
        // large-data sanity check: can we form "entertainment" repeated 10000×?
        String bigPool = input1.repeat(10000);
        tests.put(input1.repeat(10000), true);            // exact match
        tests.put(input1.repeat(10001), false);           // one extra letter

        // Run and report
        for (Map.Entry<String, Boolean> tc : tests.entrySet()) {
            String word = tc.getKey();
            boolean expected = tc.getValue();
            boolean actual = canForm(input1, word);
            String result = (actual == expected) ? "PASS" : "FAIL";
            System.out.printf("Test \"%s\" -> expected %s, got %s : %s%n",
                    word.length() > 20 ? word.substring(0, 20) + "…" : word,
                    expected, actual, result);
        }
    }
}