package com.interview.notes.code.year.y2025.august.adobe.test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * AnagramChecker
 * - Provides isAnagram method using Java 8 Streams.
 * - Includes a main method that runs PASS/FAIL tests (including large data).
 */
public class AnagramChecker {

    /**
     * Public API: Check if two strings are anagrams under the rules:
     * - case-insensitive
     * - ignore non-alphanumeric characters
     * - support a-z and 0-9 (36 buckets)
     */
    public static boolean isAnagram(String str1, String str2) {
        // Handle nulls early: both null -> true; one null -> false
        if (str1 == null || str2 == null) {           // We must check for null to avoid NPE when normalizing.
            return str1 == null && str2 == null;      // If both are null, treat as equal/anagram; else false.
        }

        // Quick length check opportunity AFTER normalization, not before.
        // So first normalize both strings to filtered lowercased a-z0-9 only.
        String n1 = normalizeToAsciiAlphaNumLower(str1); // Convert to lowercase and keep only [a-z0-9].
        String n2 = normalizeToAsciiAlphaNumLower(str2); // Same normalization for str2.

        // If normalized lengths differ, they cannot be anagrams (different number of relevant characters).
        if (n1.length() != n2.length()) {             // Faster fail to avoid unnecessary counting work.
            return false;                              // Different length means frequencies can't match.
        }

        // Use fixed-size bucket counting for 26 letters + 10 digits = 36 buckets.
        // This gives O(n) time and O(1) space (tiny and constant).
        int[] buckets = new int[36];                   // Each index tracks net frequency difference.

        // For each character in n1, increment its bucket; for n2, decrement the same bucket.
        // If anagrams, final array must be all zeros.
        // We use a single pass to reduce constant factors.
        for (int i = 0; i < n1.length(); i++) {        // Iterate once across both strings in lockstep.
            char c1 = n1.charAt(i);                    // Character from normalized str1.
            char c2 = n2.charAt(i);                    // Character from normalized str2.

            buckets[indexOf(c1)]++;                    // Increment bucket for c1.
            buckets[indexOf(c2)]--;                    // Decrement bucket for c2.
        }

        // Verify all buckets are zero: if any non-zero, not anagrams.
        for (int count : buckets) {                    // Scan the 36 buckets.
            if (count != 0) {                          // Non-zero means mismatch in frequency for some char.
                return false;                          // Early exit: not an anagram.
            }
        }

        // All buckets cancel out to zero → same multiset of characters.
        return true;                                   // Strings are anagrams under the defined rules.
    }

    // -----------------------------
    // Helpers
    // -----------------------------

    /**
     * Convert input to a lowercase string with only [a-z0-9].
     * Uses Java 8 streams over code points to safely handle input text,
     * then filters to ASCII alphanumeric for consistent bucket mapping.
     */
    private static String normalizeToAsciiAlphaNumLower(String s) {
        // We use codePoints() for correctness on any input and then filter ASCII.
        // map(Character::toLowerCase) works on code points via Character.toLowerCase.
        return s.codePoints()                          // Stream of Unicode code points for robustness.
                .map(Character::toLowerCase)           // Lowercase each code point (Unicode-aware).
                .filter(cp ->                         // Keep only ASCII lowercase letters and digits:
                        (cp >= 'a' && cp <= 'z') ||    // 'a'..'z'
                        (cp >= '0' && cp <= '9'))      // '0'..'9'
                .collect(StringBuilder::new,           // Collect into a StringBuilder for performance.
                         StringBuilder::appendCodePoint, // Append each kept code point.
                         StringBuilder::append)        // Merge builders (used in parallel; here just standard).
                .toString();                           // Convert builder to String.
    }

    /**
     * Map a single ASCII lowercase alphanumeric char to an index:
     * 'a'..'z' -> 0..25, '0'..'9' -> 26..35
     */
    private static int indexOf(char c) {
        // Letter bucket: subtract 'a' to get 0..25.
        if (c >= 'a' && c <= 'z') {                    // Fast branch for letters.
            return c - 'a';                            // E.g., 'a'->0, 'z'->25.
        }
        // Digit bucket: place after letters.
        return 26 + (c - '0');                         // '0'->26, '9'->35.
    }

    // (Optional) Sort-based method for reference/testing; not used by isAnagram.
    private static boolean isAnagramBySorting(String s1, String s2) {
        String n1 = normalizeToAsciiAlphaNumLower(s1); // Normalize first string.
        String n2 = normalizeToAsciiAlphaNumLower(s2); // Normalize second string.

        if (n1.length() != n2.length()) {             // Length mismatch after normalization.
            return false;                              // Cannot be anagrams.
        }

        // Sort both normalized strings and compare.
        // Java 8 streams to sort code points.
        String sorted1 = n1.chars()                   // Stream of chars as ints.
                .sorted()                              // Sort ascending.
                .collect(StringBuilder::new,           // Collect into StringBuilder.
                        (sb, ch) -> sb.append((char) ch), // Append each sorted char.
                        StringBuilder::append)          // Combiner for parallel (no-op in sequential).
                .toString();                           // Build final string.

        String sorted2 = n2.chars()
                .sorted()
                .collect(StringBuilder::new,
                        (sb, ch) -> sb.append((char) ch),
                        StringBuilder::append)
                .toString();

        return sorted1.equals(sorted2);                // If same sorted sequence, they’re anagrams.
    }

    // -----------------------------
    // Simple test harness (no JUnit)
    // -----------------------------

    /**
     * A tiny utility to assert expected == actual and print PASS/FAIL.
     */
    private static void runTest(String name, boolean expected, boolean actual) {
        // Compose a human-readable one-line result for each test.
        String result = (expected == actual) ? "PASS" : "FAIL";       // Decide test outcome.
        System.out.println(String.format("[%s] %s | expected=%s, actual=%s",
                result, name, expected, actual));                     // Print concise summary.
    }

    /**
     * Generate a large random string of a-z characters of given length.
     * We use Random for reproducibility and performance.
     */
    private static String randomLetters(int length, long seed) {
        Random rnd = new Random(seed);                  // Seeded RNG for repeatability.
        StringBuilder sb = new StringBuilder(length);   // Pre-size builder to avoid resizing.
        for (int i = 0; i < length; i++) {              // Loop over requested length.
            char c = (char) ('a' + rnd.nextInt(26));    // Pick a random letter a..z.
            sb.append(c);                               // Append to builder.
        }
        return sb.toString();                           // Return the generated string.
    }

    /**
     * Shuffle the characters in a string (Fisher-Yates on char array).
     * Produces a permutation that should still be an anagram of the original.
     */
    private static String shuffledCopy(String s, long seed) {
        char[] arr = s.toCharArray();                   // Work on a char array for in-place shuffle.
        Random rnd = new Random(seed);                  // Seeded RNG for deterministic shuffle.
        for (int i = arr.length - 1; i > 0; i--) {      // Fisher-Yates from end to start.
            int j = rnd.nextInt(i + 1);                 // Random index in [0, i].
            char tmp = arr[i];                          // Swap arr[i] with arr[j].
            arr[i] = arr[j];
            arr[j] = tmp;
        }
        return new String(arr);                         // Return shuffled string.
    }

    /**
     * Main method: runs a suite of PASS/FAIL tests, including edge and large cases.
     * No unit frameworks; prints results to stdout.
     */
    public static void main(String[] args) {
        // 1) Basic positives
        runTest("basic: 'listen' vs 'silent'", true, isAnagram("listen", "silent"));
        runTest("basic: 'Triangle' vs 'Integral'", true, isAnagram("Triangle", "Integral"));

        // 2) With spaces and punctuation (ignored)
        runTest("spaces/punct: 'Dormitory' vs 'Dirty room!!'", true, isAnagram("Dormitory", "Dirty room!!"));
        runTest("spaces/punct: 'Astronomer' vs 'Moon starer'", true, isAnagram("Astronomer", "Moon starer"));

        // 3) Digits included
        runTest("digits: 'a1b2' vs 'b1a2'", true, isAnagram("a1b2", "b1a2"));
        runTest("digits order: '123abc' vs 'c1b2a3'", true, isAnagram("123abc", "c1b2a3"));

        // 4) Negatives (not anagrams)
        runTest("negative: 'abc' vs 'ab'", false, isAnagram("abc", "ab"));
        runTest("negative: 'hello' vs 'bello'", false, isAnagram("hello", "bello"));
        runTest("negative: 'abcd' vs 'abce'", false, isAnagram("abcd", "abce"));

        // 5) Case-insensitivity
        runTest("case-insensitive: 'AaBbCc' vs 'cCbBaA'", true, isAnagram("AaBbCc", "cCbBaA"));

        // 6) Empty and null handling
        runTest("empty: '' vs ''", true, isAnagram("", ""));
        runTest("null vs null", true, isAnagram(null, null));
        runTest("null vs 'abc'", false, isAnagram(null, "abc"));

        // 7) Only non-alphanumeric content (ignored → both normalize to empty)
        runTest("only symbols: '!!!' vs '***'", true, isAnagram("!!!", "***"));

        // 8) Unicode beyond ASCII: these get filtered out (kept only a-z0-9 after lowercase)
        // Example includes accented letters; after filtering to [a-z0-9], these vanish → compare what's left.
        runTest("unicode filtered: 'café' vs 'face'", true, isAnagram("café", "face"));

        // 9) Large data test for performance (hundreds of thousands of chars)
        // Generate a long random string and its shuffled copy (should be anagrams).
        int bigN = 300_000;                                            // Adjust up/down based on your environment.
        String big = randomLetters(bigN, 42L);                         // Large base string.
        String bigShuffled = shuffledCopy(big, 99L);                   // Same multiset of chars, different order.
        long t1 = System.currentTimeMillis();                          // Start time.
        boolean bigResult = isAnagram(big, bigShuffled);               // Run anagram check.
        long t2 = System.currentTimeMillis();                          // End time.
        runTest("large data (" + bigN + " chars) performance", true, bigResult);
        System.out.println("Large test time: " + (t2 - t1) + " ms");   // Print elapsed time for visibility.

        // 10) Cross-check with sort-based approach on a medium input (sanity)
        String mid1 = "the eyes";
        String mid2 = "they see";
        boolean sortBased = isAnagramBySorting(mid1, mid2);            // Sorting approach as a reference.
        runTest("cross-check sort-based: 'the eyes' vs 'they see'", true, sortBased);
    }
}