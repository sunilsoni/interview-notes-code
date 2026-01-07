package com.interview.notes.code.year.y2026.jan.common.test1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;

public class Main {                          // Single public class so it runs easily in any judge.

    static int[] findAnagrams(String s, String p) {                 // Function returns all start indexes of anagrams.
        if (s == null || p == null) return new int[0];             // Null safety: no valid matches possible.
        int n = s.length();                                        // Store length of s to avoid repeated calls.
        int m = p.length();                                        // Store length of p (window size).
        if (m == 0 || n == 0 || m > n) return new int[0];          // Edge cases: empty or impossible window.

        int[] need = new int[26];                                  // need[x] = how many of letter x we still need.
        for (int i = 0; i < m; i++) need[p.charAt(i) - 'a']++;     // Build counts from p into need[].

        int remain = m;                                            // remain = total chars still needed for a match.
        int left = 0;                                              // left = start of the sliding window.
        var ans = new ArrayList<Integer>();                         // Store answers; var is Java 21 type inference.

        for (int right = 0; right < n; right++) {                  // right expands window by moving over s.
            int in = s.charAt(right) - 'a';                         // Convert incoming char to index 0..25.
            if (need[in]-- > 0) remain--;                           // If we needed it, reduce remain; always decrement.

            if (right - left + 1 == m) {                            // When window size is exactly m, we can check.
                if (remain == 0) ans.add(left);                     // If nothing remains, current window is an anagram.

                int out = s.charAt(left) - 'a';                     // Convert outgoing char (left) to index 0..25.
                if (++need[out] > 0) remain++;                      // Put it back; if now needed, increase remain.
                left++;                                             // Move window start forward by 1.
            }                                                       // End of fixed-size window handling.
        }                                                           // End of loop over s.

        return ans.stream().mapToInt(Integer::intValue).toArray();  // Convert List<Integer> to int[] for output.
    }                                                                // End of solution method.

    static boolean same(int[] a, int[] b) {                          // Helper to compare results ignoring order.
        if (a == null || b == null) return a == b;                   // Null-safe equality check.
        int[] x = a.clone();                                         // Clone so we don’t modify original arrays.
        int[] y = b.clone();                                         // Clone expected too, for fair comparison.
        Arrays.sort(x);                                              // Sort to ignore order differences.
        Arrays.sort(y);                                              // Sort expected as well.
        return Arrays.equals(x, y);                                  // True if same elements.
    }                                                                // End compare helper.

    static void test(String name, String s, String p, int[] expected) {  // Single test runner for PASS/FAIL.
        int[] got = findAnagrams(s, p);                                 // Run solution on the test input.
        boolean ok = same(got, expected);                                // Check correctness (order not important).
        System.out.println((ok ? "PASS: " : "FAIL: ") + name);           // Print PASS/FAIL label.
        if (!ok) {                                                      // If failed, print details to debug.
            System.out.println("  s=" + s);                              // Show s for debugging.
            System.out.println("  p=" + p);                              // Show p for debugging.
            System.out.println("  expected=" + Arrays.toString(expected)); // Show expected indexes.
            System.out.println("  got     =" + Arrays.toString(got));      // Show actual indexes.
        }                                                                // End failure block.
    }                                                                    // End test runner.

    public static void main(String[] args) {                              // Main method: runs all tests (no JUnit).
        test("Example", "cbaebabacd", "abc", new int[]{0, 6});             // Given example: should match [0,6].

        test("Overlapping", "abab", "ab", new int[]{0, 1, 2});             // Overlapping anagrams are valid.

        test("No match", "aaaa", "b", new int[]{});                        // No substring can match letter 'b'.

        test("p longer than s", "ab", "abc", new int[]{});                 // Impossible: window bigger than s.

        test("Exact whole string", "abc", "bca", new int[]{0});            // Whole s is an anagram of p.

        test("Repeats", "aaabaa", "aaa", new int[]{0, 1, 3});              // Repeated letters test.

        int n = 200_000;                                                   // Large input size for performance check.
        String bigS = "a".repeat(n);                                        // Big s: all 'a' (fast to build).
        String bigP = "aaa";                                                // p length 3, all 'a'.
        int[] bigExpected = IntStream.range(0, n - bigP.length() + 1).toArray(); // All windows match.
        test("Large data", bigS, bigP, bigExpected);                        // Validates both speed and correctness.
    }                                                                       // End main.
}                                                                           // End class.
