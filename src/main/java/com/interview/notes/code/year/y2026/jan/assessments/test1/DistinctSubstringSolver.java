package com.interview.notes.code.year.y2026.jan.assessments.test1;

import java.util.Arrays;

public class DistinctSubstringSolver { // Class name based on the problem (not a generic "Main").

    public static int longestDistinctSubstringLen(String s) { // Function returns longest distinct-substring length.
        if (s == null || s.isEmpty()) return 0; // Handle null/empty safely; no substring exists -> 0.

        int[] last = new int[Character.MAX_VALUE + 1]; // last[c] stores last seen index of char c (0..65535).
        Arrays.fill(last, -1); // Initialize all to -1 meaning "not seen yet".

        int left = 0; // Left boundary of current window of distinct characters.
        int best = 0; // Best (max) window length found so far.

        for (int i = 0; i < s.length(); i++) { // Move right boundary i across the string once.
            char ch = s.charAt(i); // Current character at position i.
            int prev = last[ch]; // Previous index where ch was seen (or -1 if never).

            if (prev >= left) left = prev + 1; // If ch repeats inside window, move left past its previous spot.

            last[ch] = i; // Update last seen index of ch to the current position.

            int len = i - left + 1; // Current window length (inclusive of both ends).
            if (len > best) best = len; // Update best if this window is larger.
        }

        return best; // Return the maximum distinct substring length.
    }

    private static void expect(String name, String s, int expected) { // Helper to run one test and print PASS/FAIL.
        int got = longestDistinctSubstringLen(s); // Compute actual answer.
        boolean ok = got == expected; // Compare with expected.
        System.out.println(name + " -> " + (ok ? "PASS" : "FAIL expected=" + expected + " got=" + got)); // Print result.
    }

    public static void main(String[] args) { // Main method used for testing (no JUnit).
        expect("Empty", "", 0); // Edge: empty string.
        expect("Null", null, 0); // Edge: null input.
        expect("Single", "a", 1); // Single char -> 1.
        expect("AllSame", "bbbbbb", 1); // All same -> 1.
        expect("Classic1", "abcabcbb", 3); // "abc" -> 3.
        expect("Classic2", "pwwkew", 3); // "wke" -> 3.
        expect("MiddleRepeat", "dvdf", 3); // "vdf" -> 3.
        expect("Longer", "anviaj", 5); // "nviaj" -> 5.
        expect("Spaces", "a b c a", 5); // "a␠b␠c" length 5 before repeat 'a'.
        expect("Symbols", "ab!@#ab$", 5); // "b!@#a" or "ab!@#" -> 5.

        String big1 = "a".repeat(1_000_000); // Large input: 1M same chars to test performance.
        long t1 = System.nanoTime(); // Start timing (optional sanity).
        int r1 = longestDistinctSubstringLen(big1); // Run algorithm.
        long t2 = System.nanoTime(); // End timing.
        System.out.println("BigSame(1M) -> " + (r1 == 1 ? "PASS" : "FAIL expected=1 got=" + r1) + " timeMs=" + ((t2 - t1) / 1_000_000)); // Print.

        String big2 = "abcdefghijklmnopqrstuvwxyz".repeat(50_000); // Large input: repeating 26 unique letters.
        long t3 = System.nanoTime(); // Start timing.
        int r2 = longestDistinctSubstringLen(big2); // Run algorithm.
        long t4 = System.nanoTime(); // End timing.
        System.out.println("BigAlphaRepeat -> " + (r2 == 26 ? "PASS" : "FAIL expected=26 got=" + r2) + " timeMs=" + ((t4 - t3) / 1_000_000)); // Print.
    }
}
