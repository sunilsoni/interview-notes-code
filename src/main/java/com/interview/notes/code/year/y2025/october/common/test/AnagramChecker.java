package com.interview.notes.code.year.y2025.october.common.test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Java 8 anagram checker with a pure main-method test harness.
 * Uses Streams + Unicode-safe codePoints + frequency maps.
 */
public class AnagramChecker {

    // Entry point to run tests without JUnit (as requested).
    public static void main(String[] args) {
        // Create a list of simple functional test cases (pairs and expected result).
        List<TestCase> tests = Arrays.asList(
                // Classic positive cases
                new TestCase("listen", "silent", true),            // same letters different order
                new TestCase("Triangle", "Integral", true),        // case-insensitive
                // Punctuation/space ignored
                new TestCase("Dormitory", "Dirty room!!", true),   // ignore spaces and punctuation
                new TestCase("A gentleman", "Elegant man", true),  // spaces ignored
                // Negatives
                new TestCase("rat", "car", false),                 // different letters
                new TestCase("aabbcc", "abcccd", false),           // different counts
                // Edge: empty strings
                new TestCase("", "", true),                        // both empty => anagrams
                new TestCase("", "   ", true),                     // only spaces on right => normalize to empty
                // Edge: null handling
                new TestCase(null, null, true),                    // both null => true (by our rule)
                new TestCase(null, "abc", false),                  // exactly one null => false
                // Digits involved
                new TestCase("ab12", "1b2a", true),                // digits allowed
                // Unicode letters (note: we do not fold diacritics here)
                new TestCase("Ångström", "strömnÅg", true),        // same letters including diacritics, case-insensitive
                new TestCase("ß", "ss", false)                     // German ß != "ss" without custom folding
        );

        // Run each small test and print PASS/FAIL with a simple index.
        System.out.println("=== Small & Edge Test Cases ===");
        IntStream.range(0, tests.size()).forEach(i -> {
            // Fetch test case
            TestCase tc = tests.get(i);
            // Compute actual result
            boolean actual = isAnagram(tc.a, tc.b);
            // Compare with expected
            boolean pass = actual == tc.expected;
            // Print outcome in a compact, human-friendly way
            System.out.printf("Test %d: %s -> Expected=%s, Got=%s, %s%n",
                    i + 1,
                    pairPreview(tc.a, tc.b),
                    tc.expected,
                    actual,
                    pass ? "PASS" : "FAIL");
        });

        // Large data tests: build big random strings and verify performance/correctness
        System.out.println("\n=== Large Data Tests ===");
        // Size chosen to be large but practical; tune up/down as needed
        final int SIZE = 200_000;

        // Generate a large random string of lowercase letters.
        String large = randomAlphaLower(SIZE);

        // Make an anagram by shuffling characters of the original string.
        String anagramOfLarge = shuffleString(large);

        // Ensure positive large test passes and measure time.
        long t1 = System.nanoTime();
        boolean largePassPositive = isAnagram(large, anagramOfLarge);
        long t2 = System.nanoTime();
        System.out.printf(
                "Large Positive Test: size=%d -> %s (time=%.2f ms)%n",
                SIZE,
                largePassPositive ? "PASS" : "FAIL",
                (t2 - t1) / 1_000_000.0
        );

        // Create a near-anagram negative by removing one char and adding a different one.
        StringBuilder sb = new StringBuilder(anagramOfLarge);
        if (sb.length() > 0) {
            // Change a single character to ensure mismatch in counts
            sb.setCharAt(0, (sb.charAt(0) == 'a') ? 'z' : 'a');
        }
        String nearAnagramNegative = sb.toString();

        // Ensure negative large test fails and measure time.
        long t3 = System.nanoTime();
        boolean largePassNegative = !isAnagram(large, nearAnagramNegative);
        long t4 = System.nanoTime();
        System.out.printf(
                "Large Negative Test: size=%d -> %s (time=%.2f ms)%n",
                SIZE,
                largePassNegative ? "PASS" : "FAIL",
                (t4 - t3) / 1_000_000.0
        );
    }

    // Pretty-printer for test case pairs to keep console output readable.
    private static String pairPreview(String a, String b) {
        // Replace null with literal "null", trim long values for neat logs
        return "[" + preview(a) + " | " + preview(b) + "]";
    }

    // Shorten long strings for logging without flooding console.
    private static String preview(String s) {
        // If null, print "null"
        if (s == null) return "null";
        // Limit preview length for readability
        int max = 30;
        // If shorter than limit, return as is
        if (s.length() <= max) return s;
        // Otherwise, show prefix..suffix with total capped length
        return s.substring(0, 14) + "..." + s.substring(s.length() - 13);
    }

    /**
     * Core API: checks if two strings are anagrams under the chosen rules.
     * Rules: case-insensitive, letters/digits only, Unicode-safe code points, null handling described above.
     */
    public static boolean isAnagram(String a, String b) {
        // If both references point to the same object (including both null), they're trivially equal
        if (a == b) return true;
        // If exactly one is null, they can't be anagrams
        if (a == null || b == null) return false;
        // Compute frequency signature (map) for the first string
        Map<Integer, Long> sigA = signature(a);
        // Compute frequency signature (map) for the second string
        Map<Integer, Long> sigB = signature(b);
        // Return true if both maps are structurally identical (same keys and same counts)
        return sigA.equals(sigB);
    }

    /**
     * Builds a frequency map of lowercase alphanumeric Unicode code points for the given string.
     * Uses codePoints() to be safe with characters beyond the BMP.
     */
    private static Map<Integer, Long> signature(String s) {
        // Stream the string as Unicode code points
        return s.codePoints()
                // Keep only letters or digits (ignore spaces, punctuation, symbols)
                .filter(Character::isLetterOrDigit)
                // Normalize to lowercase so case doesn't matter
                .map(Character::toLowerCase)
                // Box int code points for use in Collectors
                .boxed()
                // Group by code point and count how many occurrences for each
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    // Generate a random lowercase alphabetic string of given length for large-test use.
    private static String randomAlphaLower(int len) {
        // Use ThreadLocalRandom for good performance in single-threaded contexts
        ThreadLocalRandom rnd = ThreadLocalRandom.current();
        // Build the string efficiently using StringBuilder
        StringBuilder sb = new StringBuilder(len);
        // Append 'len' random lowercase letters
        for (int i = 0; i < len; i++) {
            // Random int in [0, 26), add 'a' to map into 'a'..'z'
            char c = (char) ('a' + rnd.nextInt(26));
            // Append the generated character
            sb.append(c);
        }
        // Return the completed random string
        return sb.toString();
    }

    // Create a shuffled permutation of the given string (to produce a guaranteed anagram).
    private static String shuffleString(String input) {
        // Edge case: null or empty returns as-is
        if (input == null || input.isEmpty()) return input;
        // Convert to a mutable char array for shuffling
        char[] arr = input.toCharArray();
        // Fisher-Yates shuffle using ThreadLocalRandom
        ThreadLocalRandom rnd = ThreadLocalRandom.current();
        // Iterate from the end to the start swapping with a random earlier index
        for (int i = arr.length - 1; i > 0; i--) {
            // Pick random index j in [0, i]
            int j = rnd.nextInt(i + 1);
            // Swap arr[i] and arr[j]
            char tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }
        // Build a new string from the shuffled characters
        return new String(arr);
    }

    /**
     * @param a        first input string
     * @param b        second input string
     * @param expected expected outcome
     */ // Helper class to hold a test case: two inputs and the expected boolean.
    private record TestCase(String a, String b, boolean expected) {
        // assign first string
        // assign second string
        // assign expected result
    }
}