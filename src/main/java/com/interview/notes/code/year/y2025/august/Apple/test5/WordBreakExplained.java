package com.interview.notes.code.year.y2025.august.Apple.test5;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class WordBreakExplained {

    /**
     * Determines if the string s can be segmented into a sequence of dictionary words.
     * Bottom-up DP with pruning by maximum word length.
     * Uses Java 8 Streams to build the dictionary set and compute max word length.
     */
    public static boolean wordBreakDP(final String s, final List<String> wordDict) {
        // Guard: if s is null, we cannot segment it -> return false (defensive choice)
        if (s == null) return false; // Avoid NullPointerExceptions and define behavior for null inputs

        // Create a Set<String> from wordDict for O(1) membership checks; handles duplicates automatically
        final Set<String> dict = wordDict == null
                ? new HashSet<>() // If wordDict is null, treat as empty set so nothing matches
                : wordDict.stream().collect(Collectors.toSet()); // Stream -> Set for fast lookups

        // If s is empty: by definition, empty string can be segmented as zero words -> true
        if (s.isEmpty()) return true; // Matches standard Word Break conventions (LeetCode behavior)

        // If dictionary is empty and s is non-empty, segmentation is impossible -> false
        if (dict.isEmpty()) return false; // No words available to segment a non-empty string

        // Compute maximum word length in dictionary to prune inner loop sizes (huge speedup in practice)
        final int maxLen = dict.stream().mapToInt(String::length).max().orElse(0); // orElse(0) when dict non-empty still safe

        // Quick impossible check: if maxLen is zero (only happens if dict had empty strings, which we ignore) -> false
        if (maxLen == 0) return false; // Prevents infinite loops and meaningless checks

        // Prepare DP table: dp[i] == true means s[0..i) can be segmented into dictionary words
        final int n = s.length(); // Cache length of s to avoid repeated calls
        final boolean[] dp = new boolean[n + 1]; // We need n+1 cells to represent all prefixes, including empty prefix

        // Base case: empty prefix is always segmentable (use zero words)
        dp[0] = true; // Seed state for transitions

        // Outer loop: iterate over end index i of a prefix s[0..i)
        for (int i = 1; i <= n; i++) { // i goes from 1 to n inclusive, representing prefix length
            // Inner loop: try all possible word lengths up to maxLen but not exceeding i
            int upper = Math.min(i, maxLen); // We cannot take a word longer than current prefix length
            for (int len = 1; len <= upper; len++) { // Try each candidate word length
                // If the prefix up to i-len is segmentable, we can attempt to extend by a word of length len
                if (dp[i - len]) { // Only check dictionary if left side is reachable to reduce work
                    // Extract substring s[i-len, i) and test membership in dictionary
                    // NOTE: substring creates a new String; acceptable here due to strong pruning by maxLen
                    String candidate = s.substring(i - len, i); // Candidate word ending at position i-1
                    if (dict.contains(candidate)) { // Constant-time membership test in the dictionary set
                        dp[i] = true; // We found a valid segmentation for prefix s[0..i)
                        break; // No need to try shorter lengths once dp[i] is true (early exit optimization)
                    }
                }
            }
            // If dp[i] remains false, the prefix s[0..i) is not segmentable; loop continues to next i
        }

        // Final answer: can we segment the entire string s[0..n)?
        return dp[n]; // True if the whole string is segmentable, false otherwise
    }

    // Utility to run a single test and print PASS/FAIL with details
    private static void runTest(TestCase tc, int idx) {
        boolean actual = wordBreakDP(tc.s, tc.dict); // Invoke DP function under test
        String status = actual == tc.expected ? "PASS" : "FAIL"; // Compare to expected
        System.out.println("Test#" + idx + " -> " + status
                + " | s=\"" + tc.s + "\""
                + " | dict=" + tc.dict
                + " | expected=" + tc.expected
                + " | actual=" + actual); // Print detailed result for quick debugging
    }

    // Generate a large input case to validate performance without JUnit
    private static TestCase generateLargeCase(int repeats, String baseWord) {
        // Build s by repeating baseWord 'repeats' times (ensures it is segmentable)
        StringBuilder sb = new StringBuilder(); // Efficient string builder for concatenation
        IntStream.range(0, repeats).forEach(i -> sb.append(baseWord)); // Append baseWord 'repeats' times using a stream
        String s = sb.toString(); // Final long string
        // Create a dictionary that includes baseWord and some distractors to emulate realistic dictionaries
        List<String> dict = Arrays.asList(baseWord, "x", "y", "z", baseWord + "x"); // Minimal but includes the needed word
        return new TestCase(s, dict, true); // Expected true because s is constructed by baseWord repeats
    }

    // MAIN METHOD: runs multiple tests and prints PASS/FAIL (no JUnit as requested)
    public static void main(String[] args) {
        // Build a list to hold diverse, meaningful test cases
        List<TestCase> tests = new ArrayList<>(); // Dynamic list of test cases

        // Basic examples from the prompt
        tests.add(new TestCase("applepenapple", Arrays.asList("apple", "pen"), true)); // Should be true: "apple pen apple"
        tests.add(new TestCase("catsandog", Arrays.asList("cats", "dog", "sand", "and", "cat"), false)); // False: "og" leftover

        // Additional essential cases
        tests.add(new TestCase("", Arrays.asList("a", "b"), true)); // Empty string is segmentable by zero words
        tests.add(new TestCase("a", List.of(), false)); // No dictionary for non-empty s
        tests.add(new TestCase("leetcode", Arrays.asList("leet", "code"), true)); // Standard classic case
        tests.add(new TestCase("aaaaaaa", Arrays.asList("aaaa", "aaa"), true)); // Overlapping words should succeed
        tests.add(new TestCase("aaaaaaa", Arrays.asList("aaaa", "aa"), false)); // Greedy fails; DP correctly finds false
        tests.add(new TestCase("catsanddog", Arrays.asList("cat", "cats", "and", "sand", "dog"), true)); // Multiple valid splits
        tests.add(new TestCase("pineapplepenapple", Arrays.asList("apple", "pen", "applepen", "pine", "pineapple"), true)); // Multiple paths

        // Larger near-worst-case style: many 'a's with words up to length 10 (pruned by maxLen)
        tests.add(generateLargeCase(2000, "abc")); // s length = 6000, fast due to maxLen pruning and exact repeats

        // Run all tests and print results
        IntStream.range(0, tests.size()).forEach(i -> runTest(tests.get(i), i + 1)); // Use stream for concise iteration
    }

    /**
     * Optional alternative (commented for reference): DP that avoids substring allocation by using startsWith.
     * Keep in mind this iterates over dictionary words for each reachable index; fast when dict is small.
     * <p>
     * public static boolean wordBreakDPStartsWith(final String s, final List<String> wordDict) {
     * if (s == null) return false;
     * if (s.isEmpty()) return true;
     * final Set<String> dict = wordDict == null ? new HashSet<>() : wordDict.stream().collect(Collectors.toSet());
     * if (dict.isEmpty()) return false;
     * final int n = s.length();
     * final boolean[] dp = new boolean[n + 1];
     * dp[0] = true;
     * for (int i = 0; i < n; i++) {
     * if (!dp[i]) continue;
     * for (String w : dict) {
     * int len = w.length();
     * if (i + len <= n && s.startsWith(w, i)) {
     * dp[i + len] = true;
     * }
     * }
     * }
     * return dp[n];
     * }
     *
     * @param s        The input string to segment
     * @param dict     The dictionary of words
     * @param expected The expected boolean result
     */

        // Simple holder for test cases to keep main method tidy
        private record TestCase(String s, List<String> dict, boolean expected) {
        // Assign given string
        // Assign given dictionary
        // Assign expected outcome
    }
}