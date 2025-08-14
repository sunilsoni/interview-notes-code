package com.interview.notes.code.year.y2025.august.adobe.test1;

import java.util.*; // import utilities for List, Arrays, etc.
import java.util.stream.*; // import Java 8 Stream API for test construction and formatting

public class MinimumWindowSubstring {

    // Public API method that solves the problem: returns minimum window substring or empty string if none.
    public static String minWindow(String s, String t) {
        // If t is longer than s, impossible to cover; return empty immediately.
        if (t.length() > s.length()) return "";

        // Frequency array to record how many of each ASCII character we still need to include.
        // Using 128 (standard ASCII) covers uppercase/lowercase English letters as required.
        int[] need = new int[128];

        // Fill the need array: for each char c in t, we need one more c in our window.
        for (char c : t.toCharArray()) {
            need[c]++; // increase required count for this character
        }

        // 'required' counts how many characters (total, including duplicates) are still missing from the current window.
        int required = t.length();

        // Two pointers define the sliding window [left, right)
        int left = 0;  // left boundary inclusive
        int right = 0; // right boundary exclusive

        // Track the best (minimum) window found so far.
        int bestStart = 0;             // starting index of best window
        int bestLen = Integer.MAX_VALUE; // length of best window (minimize this)

        // Iterate by expanding the right pointer over s.
        while (right < s.length()) {
            // Take the current right character.
            char rc = s.charAt(right); // character entering the window

            // If we still need this character (need[rc] > 0), then adding rc reduces the 'required' counter.
            if (need[rc] > 0) {
                required--; // one less character needed to satisfy t
            }

            // Decrement need[rc] regardless; values can go negative which means "extra" copies beyond what's needed.
            need[rc]--; // include this character into the window
            right++;    // expand window to the right

            // When required == 0, the window [left, right) contains all characters needed.
            while (required == 0) {
                // Update best window if the current one is smaller.
                if (right - left < bestLen) {
                    bestLen = right - left; // update length
                    bestStart = left;       // update start index
                }

                // Now try to shrink from the left to remove unnecessary characters and keep the window valid.
                char lc = s.charAt(left); // character leaving the window from the left

                // When we move left, we "give back" lc to the need array.
                need[lc]++; // restore one count for lc since it's no longer in the window

                // If after giving back lc, need[lc] became > 0, it means we just removed a required character.
                // The window is no longer valid and we must stop shrinking.
                if (need[lc] > 0) {
                    required++; // we now miss one required character
                }

                // Move left forward to continue shrinking.
                left++; // shrink window from the left
            }
        }

        // If bestLen is still MAX_VALUE, no valid window was found; otherwise return the substring.
        return bestLen == Integer.MAX_VALUE ? "" : s.substring(bestStart, bestStart + bestLen);
    }

    // Simple container to hold a test case: s, t, expected answer, and a name/label.
    static class TestCase {
        final String name;   // label to identify the test
        final String s;      // input string s
        final String t;      // input string t
        final String expect; // expected result

        // Constructor to initialize all fields.
        TestCase(String name, String s, String t, String expect) {
            this.name = name;   // assign label
            this.s = s;         // assign s
            this.t = t;         // assign t
            this.expect = expect; // assign expected answer
        }
    }

    // Helper to run a single test case and print PASS/FAIL with details.
    private static boolean runTest(TestCase tc) {
        // Call the solution for given s and t.
        String ans = minWindow(tc.s, tc.t);

        // Compare with expected and print result line.
        boolean pass = Objects.equals(ans, tc.expect); // exact string match

        // Build an output message using Streams to keep code concise and readable.
        String message = Stream.of(
                "Test: " + tc.name,
                "s=\"" + (tc.s.length() <= 80 ? tc.s : tc.s.substring(0, 80) + "...") + "\"",
                "t=\"" + tc.t + "\"",
                "expected=\"" + tc.expect + "\"",
                "actual=\"" + ans + "\"",
                pass ? "RESULT=PASS" : "RESULT=FAIL"
        ).collect(Collectors.joining(" | ")); // join parts with separators

        // Print the message to console.
        System.out.println(message);

        // Return whether test passed.
        return pass;
    }

    // Entry point: builds a set of test cases (including large data), runs them, and prints a summary.
    public static void main(String[] args) {
        // Prepare a list of well‑chosen test cases covering varied scenarios.
        List<TestCase> tests = new ArrayList<>(); // create mutable list

        // Example tests from the prompt.
        tests.add(new TestCase("Example-1", "ADOBECODEBANC", "ABC", "BANC")); // minimal window sample
        tests.add(new TestCase("Example-2", "a", "a", "a"));                  // entire s is the window
        tests.add(new TestCase("Example-3", "a", "aa", ""));                  // impossible case

        // Additional edge and typical cases.
        tests.add(new TestCase("Duplicates-in-t", "aaflslflsldkalskaaa", "aaa", "aaa")); // duplicates need
        tests.add(new TestCase("Case-Sensitive", "aAbBcC", "ABC", ""));                   // case matters
        tests.add(new TestCase("Case-Sensitive-2", "AaaBccC", "ABC", "AaaBccC"));         // must include uppercase A,B,C
        tests.add(new TestCase("All-same-chars", "aaaaab", "ab", "ab"));                  // tight window at end
        tests.add(new TestCase("No-solution", "xyz", "a", ""));                            // missing char

        // A crafted case with multiple options but a unique minimal answer.
        tests.add(new TestCase("Unique-min", "abcbcba", "aba", "abcba")); // smallest that has two 'a's and one 'b' (actually "abcbcba" has 'a's at ends; minimum window is "abcba")

        // Build a large string for performance: length ~100,000 with a known small answer near the end.
        // We'll place the minimal "xyz" near the end to ensure algorithm scans properly.
        String largePrefix = IntStream.range(0, 50000) // create 50k chars
                .mapToObj(i -> "a")                   // all 'a'
                .collect(Collectors.joining());       // join into a big string of 'a's
        String largeMiddle = IntStream.range(0, 49998) // ~50k more minus a few
                .mapToObj(i -> "b")                   // all 'b'
                .collect(Collectors.joining());       // join
        String largeS = largePrefix + "x" + largeMiddle + "y" + "zzzzzzzzzz"; // ensure 'x', 'y', and many 'z'
        String largeT = "xyz"; // target requiring x, y, z

        // The true minimal window ends at the first 'z' after 'y': "...x...y z"
        // That window is "x" + (49998 'b's) + "y" + "z". But "b"s are not required; shrinking removes them.
        // Actual minimal window is "yz"? No (needs 'x' too). The minimal valid starts at 'x' and ends at the first 'z' => "x" + many 'b' + "y" + "z".
        // After shrinking left, we cannot drop 'x', so the minimal includes from the 'x' to the first 'z'. There's no other 'x' later.
        // We'll compute expected using the algorithm itself to avoid miscount—here we leave expect as computed at runtime.
        // To keep the harness simple and independent, we accept whatever the function returns here and still time it.

        // Add the large case with an empty expected placeholder (we will not assert exact equality; we will time it).
        tests.add(new TestCase("Large-100k", largeS, largeT, null)); // 'null' means "skip equality check; just time it"

        // Run all tests and count passes (for the large test, we consider PASS if it returns a non-empty valid window).
        int passCount = 0; // counter for passes
        int totalCount = tests.size(); // total tests

        // Iterate through all prepared test cases.
        for (TestCase tc : tests) {
            // If this is the large case, measure time and validate minimal correctness (contains all t).
            if ("Large-100k".equals(tc.name)) {
                long start = System.nanoTime();           // start timer
                String ans = minWindow(tc.s, tc.t);       // run solution
                long end = System.nanoTime();             // end timer
                long ms = (end - start) / 1_000_000;      // convert ns to ms

                // Check basic correctness: answer should be non-empty and contain all required chars with multiplicity.
                boolean basicValid = !ans.isEmpty()
                        && containsAllWithMultiplicity(ans, tc.t); // helper validation below

                // Print a compact performance/correctness line for the large case.
                System.out.println("Test: " + tc.name
                        + " | length(s)=" + tc.s.length()
                        + " | timeMs=" + ms
                        + " | ansLen=" + ans.length()
                        + " | basicValid=" + basicValid
                        + " | RESULT=" + (basicValid ? "PASS" : "FAIL"));

                // Update pass counter.
                if (basicValid) passCount++;
            } else {
                // For regular cases with explicit expected result, use runTest for PASS/FAIL.
                passCount += runTest(tc) ? 1 : 0; // increment if test passes
            }
        }

        // Print final summary of how many tests passed.
        System.out.println("Summary: PASS " + passCount + " / " + totalCount);
    }

    // Helper that checks if 'window' contains all characters from 't' with at least the same multiplicity.
    // This is used only for validating the large test case result (no exact expected string).
    private static boolean containsAllWithMultiplicity(String window, String t) {
        // Arrays for counting characters in window and t.
        int[] countWin = new int[128]; // counts in window
        int[] countT = new int[128];   // counts in t

        // Count characters in window.
        for (int i = 0; i < window.length(); i++) {
            countWin[window.charAt(i)]++; // increment count for each char in window
        }

        // Count characters in t.
        for (int i = 0; i < t.length(); i++) {
            countT[t.charAt(i)]++; // increment count for each char in t
        }

        // For every ASCII character, window must have at least as many as t requires.
        for (int c = 0; c < 128; c++) {
            if (countWin[c] < countT[c]) return false; // missing multiplicity
        }
        return true; // all good
    }
}