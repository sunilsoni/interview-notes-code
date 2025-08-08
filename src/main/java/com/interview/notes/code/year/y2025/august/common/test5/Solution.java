package com.interview.notes.code.year.y2025.august.common.test5;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class Solution {

    /**
     * Simple test harness in main() to check PASS/FAIL for each case.
     * Also includes large-input tests.
     */
    public static void main(String[] args) {
        PangramDetector pd = new PangramDetector();

        // List of test cases: sentence → expected missing letters
        Map<String, String> tests = new LinkedHashMap<>();
        tests.put("The quick brown fox jumps over the lazy dog", "");
        tests.put("The slow purple oryx meanders past the quiescent canine", "bfgjkvz");
        tests.put("We hates Bagginses!", "cdfjklmopruvwxyz");
        tests.put("", "abcdefghijklmnopqrstuvwxyz");
        tests.put(null, "abcdefghijklmnopqrstuvwxyz");

        // Large test #1: a very long pangram (repeated phrase) → no missing letters
        String pangramPhrase = "The quick brown fox jumps over the lazy dog";
        StringBuilder sb1 = new StringBuilder();
        for (int i = 0; i < 20_000; i++) { // ~900k characters
            sb1.append(pangramPhrase).append(" ");
        }
        tests.put(sb1.toString(), "");

        // Large test #2: a million 'a's → missing b–z
        StringBuilder sb2 = new StringBuilder(1_000_000);
        for (int i = 0; i < 1_000_000; i++) {
            sb2.append('a');
        }
        tests.put(sb2.toString(), "bcdefghijklmnopqrstuvwxyz");

        // Run all tests
        boolean allPassed = true;
        int count = 1;
        for (Map.Entry<String, String> entry : tests.entrySet()) {
            String input = entry.getKey();
            String expected = entry.getValue();
            String actual = pd.findMissingLetters(input);

            boolean passed = Objects.equals(actual, expected);
            System.out.printf(
                    "Test %2d: %s → expected=\"%s\", got=\"%s\" : %s%n",
                    count++,
                    // brief description: show first 30 chars or "null"
                    (input == null ? "null"
                            : input.length() <= 30 ? "\"" + input + "\""
                            : "\"" + input.substring(0, 30) + "...\""),
                    expected,
                    actual,
                    (passed ? "PASS" : "FAIL")
            );

            if (!passed) {
                allPassed = false;
            }
        }

        // Summary
        System.out.println(allPassed
                ? "✅ All tests passed."
                : "❌ Some tests failed.");
    }

    // Inner class housing our pangram logic
    private static class PangramDetector {
        // Constant alphabet string for reference
        private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";

        /**
         * Finds which lowercase letters in 'a'–'z' are missing from the input.
         *
         * @param sentence the text to inspect (may be null)
         * @return a string of missing lowercase letters, in sorted order
         */
        public String findMissingLetters(String sentence) {
            // If input is null, treat it as empty
            if (sentence == null) {
                // All letters are missing
                return ALPHABET;
            }

            // 1. Build a set of all letters present in the sentence
            Set<Character> present = sentence
                    // Turn the string into an IntStream of Unicode code points
                    .chars()
                    // Convert each code point to lowercase
                    .map(Character::toLowerCase)
                    // Keep only ASCII letters 'a' (97) to 'z' (122)
                    .filter(c -> c >= 'a' && c <= 'z')
                    // Box ints to Character objects
                    .mapToObj(c -> (char) c)
                    // Collect into a HashSet for fast lookup
                    .collect(Collectors.toSet());

            // 2. Stream over the alphabet string, filter out letters that are present,
            //    and join the rest into a single string
            return ALPHABET
                    // Turn the alphabet into an IntStream of its code points
                    .chars()
                    // Box again to Character
                    .mapToObj(c -> (char) c)
                    // Keep only those not in the 'present' set
                    .filter(c -> !present.contains(c))
                    // Convert each Character to a String
                    .map(String::valueOf)
                    // Join all pieces without delimiter
                    .collect(Collectors.joining());
        }
    }
}
