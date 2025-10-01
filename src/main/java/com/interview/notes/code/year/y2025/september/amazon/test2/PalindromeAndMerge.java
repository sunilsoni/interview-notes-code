package com.interview.notes.code.year.y2025.september.amazon.test2;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PalindromeAndMerge { // Single public class so file compiles/run easily

    // -------------------------
    // Palindrome: Longest Substring
    // -------------------------

    // Finds the longest palindromic substring using expand-around-center
    public static String longestPalindromicSubstring(String s) { // Public so tests can call it
        if (s == null || s.isEmpty()) return ""; // Handle null/empty safely; LPS is empty
        int n = s.length(); // Cache length to avoid repeated calls
        int bestStart = 0; // Track best palindrome start index
        int bestEnd = 0;   // Track best palindrome end index (inclusive)

        for (int center = 0; center < n; center++) { // Iterate every index as a center
            int[] odd = expand(s, center, center); // Expand for odd-length palindromes
            if (odd[1] - odd[0] > bestEnd - bestStart) { // If this odd palindrome is longer
                bestStart = odd[0]; // Update best start
                bestEnd = odd[1];   // Update best end
            }
            int[] even = expand(s, center, center + 1); // Expand for even-length palindromes
            if (even[1] - even[0] > bestEnd - bestStart) { // If this even palindrome is longer
                bestStart = even[0]; // Update best start
                bestEnd = even[1];   // Update best end
            }
        }

        return s.substring(bestStart, bestEnd + 1); // Return longest palindromic substring
    }

    // Helper that expands around [left,right] and returns [start,end] (inclusive)
    private static int[] expand(String s, int left, int right) { // Private: internal helper
        int n = s.length(); // Cache string length
        int L = left;       // Working left pointer
        int R = right;      // Working right pointer
        while (L >= 0 && R < n && s.charAt(L) == s.charAt(R)) { // Expand while chars match and within bounds
            L--; // Move left outward
            R++; // Move right outward
        }
        return new int[]{L + 1, R - 1}; // Step back to last valid matching window
    }

    // Returns all unique palindromic substrings (order not guaranteed)
    public static Set<String> allPalindromicSubstrings(String s) { // Public for testing/use
        Set<String> result = new HashSet<>(); // Use Set to keep unique palindromes only
        if (s == null || s.isEmpty()) return result; // Early return for empty input

        // Loop all centers and add discovered palindromes to the set
        IntStream.range(0, s.length()) // Use IntStream to stay in Java 8 style
                .forEach(center -> { // For each index as center
                    addPalindromesFromCenter(s, center, center, result);     // Odd-length palindromes
                    addPalindromesFromCenter(s, center, center + 1, result); // Even-length palindromes
                });

        return result; // Return unique palindromic substrings
    }

    // Expand from (L,R) and add all palindromic substrings encountered into result
    private static void addPalindromesFromCenter(String s, int L, int R, Set<String> result) { // Helper for set fill
        int n = s.length(); // Cache length for bounds
        int left = L;       // Local left pointer
        int right = R;      // Local right pointer
        while (left >= 0 && right < n && s.charAt(left) == s.charAt(right)) { // While palindrome around center
            result.add(s.substring(left, right + 1)); // Add the current palindrome substring
            left--;  // Expand further left
            right++; // Expand further right
        }
    }

    // -------------------------
    // Merge Intervals
    // -------------------------

    // Merge overlapping or touching intervals; each interval is int[]{start,end}
    public static List<int[]> mergeIntervals(List<int[]> intervals) { // Public for testing/use
        if (intervals == null || intervals.isEmpty()) return Collections.emptyList(); // Safe empty

        List<int[]> sorted = intervals.stream() // Use Stream to sort elegantly
                .sorted(Comparator.comparingInt(a -> a[0])) // Sort by start ascending
                .collect(Collectors.toList()); // Back to a List

        List<int[]> merged = new ArrayList<>(); // Output list for merged intervals
        int[] current = Arrays.copyOf(sorted.get(0), 2); // Start with the first interval (copy to avoid mutating input)

        for (int i = 1; i < sorted.size(); i++) { // Sweep through sorted intervals
            int[] next = sorted.get(i); // Next interval
            if (next[0] <= current[1]) { // Overlap or touch if next.start <= current.end
                current[1] = Math.max(current[1], next[1]); // Extend current's end if needed
            } else {
                merged.add(current);         // No overlap: push current
                current = Arrays.copyOf(next, 2); // Start a new current
            }
        }
        merged.add(current); // Add the last accumulated interval

        return merged; // Return merged list
    }

    // -------------------------
    // Utilities for Testing
    // -------------------------

    // Compares two lists of intervals (as int[]), ignoring order if specified
    private static boolean intervalsEqualOrdered(List<int[]> a, List<int[]> b) { // Check equality with order
        if (a.size() != b.size()) return false; // Size mismatch fails immediately
        for (int i = 0; i < a.size(); i++) { // Compare each interval
            if (a.get(i)[0] != b.get(i)[0] || a.get(i)[1] != b.get(i)[1]) return false; // Any mismatch → false
        }
        return true; // All intervals matched
    }

    // Convenience to build intervals from ints for tests
    private static List<int[]> intervals(int... vals) { // Build list: pairs of start,end
        List<int[]> list = new ArrayList<>(); // Collector
        for (int i = 0; i < vals.length; i += 2) { // Step through pairs
            list.add(new int[]{vals[i], vals[i + 1]}); // Add each pair as interval
        }
        return list; // Return built list
    }

    // Pretty-print intervals for debugging
    private static String show(List<int[]> xs) { // Convert intervals to friendly string
        return xs.stream() // Use stream to map each interval
                .map(a -> "[" + a[0] + "," + a[1] + "]") // Format each interval
                .collect(Collectors.joining(",")); // Join with commas
    }

    // -------------------------
    // Main with PASS/FAIL tests (no JUnit)
    // -------------------------
    public static void main(String[] args) { // Single entry point as requested
        // --- Palindrome tests ---
        System.out.println("== Palindrome: Longest Substring Tests =="); // Section header
        testLPS("babad", new HashSet<>(Arrays.asList("bab", "aba"))); // Classic case, two valid answers
        testLPS("cbbd", Collections.singleton("bb")); // Even length center
        testLPS("a", Collections.singleton("a")); // Single char
        testLPS("", Collections.singleton("")); // Empty input
        testLPS("forgeeksskeegfor", Collections.singleton("geeksskeeg")); // Long mirror
        testLPS("abacdfgdcaba", new HashSet<>(List.of("aba"))); // Multiple palindromes, one longest

        System.out.println("\n== Palindrome: All Substrings Tests =="); // Section header
        testAllPals("aaa", new HashSet<>(Arrays.asList("a", "aa", "aaa"))); // All variants in aaa
        testAllPals("abc", new HashSet<>(Arrays.asList("a", "b", "c"))); // Only single letters

        // Large data test for palindrome
        System.out.println("\n== Palindrome: Large Data Test =="); // Header for perf
        String big = repeat('a', 20000); // 20k of 'a' => the entire string is palindrome
        long t1 = System.currentTimeMillis(); // Start timing
        String lps = longestPalindromicSubstring(big); // Compute LPS
        long t2 = System.currentTimeMillis(); // End timing
        boolean ok = lps.length() == big.length(); // Validate result equals full length
        System.out.println("Large Data Test: " + (ok ? "PASS" : "FAIL") // Print pass/fail with timing
                + " -> length=" + lps.length() + " in " + (t2 - t1) + "ms");

        // --- Merge intervals tests ---
        System.out.println("\n== Merge Intervals Tests =="); // Section header
        // Given example: [1,2][2,5] -> [1,5]
        testMerge(intervals(1, 2, 2, 5), intervals(1, 5)); // Touching should merge

        // Mixed overlaps and ordering
        testMerge(intervals(1, 3, 8, 10, 2, 6, 15, 18), intervals(1, 6, 8, 10, 15, 18)); // Standard example

        // Nested intervals
        testMerge(intervals(1, 5, 2, 3), intervals(1, 5)); // Inner is swallowed

        // Non-overlapping
        testMerge(intervals(1, 2, 3, 4), intervals(1, 2, 3, 4)); // No merge when no touch

        // Single and empty
        testMerge(intervals(5, 7), intervals(5, 7)); // Single
        testMerge(Collections.emptyList(), Collections.emptyList()); // Empty

        // Large data test for intervals
        System.out.println("\n== Merge Intervals: Large Data Test =="); // Header
        List<int[]> many = new ArrayList<>(); // Build many intervals
        for (int i = 0; i < 100000; i++) { // 100k small intervals
            many.add(new int[]{i * 2, i * 2 + 1}); // Disjoint tiny intervals
        }
        long m1 = System.currentTimeMillis(); // Start timing
        List<int[]> merged = mergeIntervals(many); // Merge (should remain same count)
        long m2 = System.currentTimeMillis(); // End timing
        boolean okMerge = merged.size() == many.size(); // Should be identical size
        System.out.println("Large Data Test: " + (okMerge ? "PASS" : "FAIL") // Print result and timing
                + " -> intervals=" + merged.size() + " in " + (m2 - m1) + "ms");
    }

    // Reusable small tester for LPS: accept any of the expected winners
    private static void testLPS(String input, Set<String> accepted) { // Test helper
        String got = longestPalindromicSubstring(input); // Compute
        boolean pass = accepted.contains(got); // Check against accepted set
        System.out.println("Input=\"" + input + "\" -> " + (pass ? "PASS" : "FAIL") // Print verdict
                + " Expected " + accepted + ", Got \"" + got + "\""); // Show expected/got
    }

    // Reusable small tester for all-palindromes set
    private static void testAllPals(String input, Set<String> expected) { // Test helper
        Set<String> got = allPalindromicSubstrings(input); // Compute set
        boolean pass = got.containsAll(expected) && expected.containsAll(got); // Exact match
        System.out.println("Input=\"" + input + "\" -> " + (pass ? "PASS" : "FAIL") // Print verdict
                + " Expected " + expected + ", Got " + got); // Show sets
    }

    // Reusable small tester for merge intervals
    private static void testMerge(List<int[]> input, List<int[]> expected) { // Test helper
        List<int[]> got = mergeIntervals(input); // Compute merge
        boolean pass = intervalsEqualOrdered(got, expected); // Compare ordered lists
        System.out.println("Input=" + show(input) + " -> " + (pass ? "PASS" : "FAIL") // Print verdict
                + " Expected=" + show(expected) + " Got=" + show(got)); // Show expected/got
    }

    // Small utility to build large pal strings quickly
    private static String repeat(char c, int n) { // Create string of c repeated n times
        char[] arr = new char[n]; // Allocate char array
        Arrays.fill(arr, c); // Fill with c
        return new String(arr); // Convert to String
    }
}