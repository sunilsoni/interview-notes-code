package com.interview.notes.code.year.y2024.nov24.amazon.test22;

import java.util.HashMap;
import java.util.Map;

public class DominantSubstringCounter {

    /**
     * Counts the number of dominant substrings in the given string.
     * <p>
     * A dominant substring is defined as an even-length substring that contains
     * at least one character with a frequency exactly equal to half the substring's length.
     *
     * @param s The input string consisting of lowercase letters from 'a' to 'j'.
     * @return The number of dominant substrings as a long integer.
     */
    public static long getDominantStringCount(String s) {
        long total = 0;
        // Initialize counts for each character 'a' to 'j'
        Map<Character, Long> perCharCounts = new HashMap<>();
        for (char c = 'a'; c <= 'j'; c++) {
            perCharCounts.put(c, 0L);
        }

        // Compute per-character counts
        for (char c = 'a'; c <= 'j'; c++) {
            Map<Long, Integer> map = new HashMap<>();
            map.put(0L, 1); // Initialize with transformed value 0
            long count_c = 0;
            for (int j = 0; j < s.length(); j++) {
                if (s.charAt(j) == c) {
                    count_c++;
                }
                long transformed = 2 * count_c - (j + 1);
                total += map.getOrDefault(transformed, 0);
                map.put(transformed, map.getOrDefault(transformed, 0) + 1);
            }
            perCharCounts.put(c, Long.valueOf(map.get(0l))); // Not necessary, but kept for clarity
        }

        // Compute per-pair counts
        long totalPairCounts = 0;
        char[] chars = new char[10];
        for (int i = 0; i < 10; i++) {
            chars[i] = (char) ('a' + i);
        }

        for (int i = 0; i < 10; i++) {
            for (int j = i + 1; j < 10; j++) {
                char c1 = chars[i];
                char c2 = chars[j];
                Map<Long, Integer> map = new HashMap<>();
                // Unique encoding for pair (trans_c1, trans_c2)
                // Key = trans_c1 * 1_000_000 + trans_c2
                map.put(0L * 1_000_000 + 0L, 1);
                long count_c1 = 0;
                long count_c2 = 0;
                long count_c1c2 = 0;
                for (int k = 0; k < s.length(); k++) {
                    if (s.charAt(k) == c1) {
                        count_c1++;
                    }
                    if (s.charAt(k) == c2) {
                        count_c2++;
                    }
                    long transformed_c1 = 2 * count_c1 - (k + 1);
                    long transformed_c2 = 2 * count_c2 - (k + 1);
                    long key = transformed_c1 * 1_000_000L + transformed_c2;
                    count_c1c2 += map.getOrDefault(key, 0);
                    map.put(key, map.getOrDefault(key, 0) + 1);
                }
                totalPairCounts += count_c1c2;
            }
        }

        // Apply Inclusion-Exclusion Principle
        long result = total;
        result -= totalPairCounts;

        return result;
    }

    /**
     * Main method to run test cases.
     */
    public static void main(String[] args) {
        // Test Case 0
        String test0 = "aaaaid";
        long expected0 = 3;
        long result0 = getDominantStringCount(test0);
        System.out.println("Test Case 0: " + (result0 == expected0 ? "PASS" : "FAIL") + " | Expected: " + expected0 + ", Got: " + result0);

        // Test Case 1
        String test1 = "abab";
        long expected1 = 4;
        long result1 = getDominantStringCount(test1);
        System.out.println("Test Case 1: " + (result1 == expected1 ? "PASS" : "FAIL") + " | Expected: " + expected1 + ", Got: " + result1);

        // Additional Test Cases

        // Test Case 2: Minimal length, dominant
        String test2 = "ab";
        long expected2 = 2; // "ab" has 'a' and 'b' each once
        long result2 = getDominantStringCount(test2);
        System.out.println("Test Case 2: " + (result2 == expected2 ? "PASS" : "FAIL") + " | Expected: " + expected2 + ", Got: " + result2);

        // Test Case 3: All characters the same
        String test3 = "aaaaaaaaaa";
        long expected3 = 5; // "aa", "aaaa", "aaaaaa", "aaaaaaaa", "aaaaaaaaaa"
        long result3 = getDominantStringCount(test3);
        System.out.println("Test Case 3: " + (result3 == expected3 ? "PASS" : "FAIL") + " | Expected: " + expected3 + ", Got: " + result3);

        // Test Case 4: Multiple dominant substrings with overlaps
        String test4 = "idafddfii";
        long expected4 = 13;
        long result4 = getDominantStringCount(test4);
        System.out.println("Test Case 4: " + (result4 == expected4 ? "PASS" : "FAIL") + " | Expected: " + expected4 + ", Got: " + result4);

        // Test Case 5: Large input with no dominant substrings
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 100000; i++) {
            sb.append('a');
        }
        sb.append('b');
        String test5 = sb.toString();
        long expected5 = 0; // No dominant substrings as 'a's dominate but 'a' counts are not exactly half
        long result5 = getDominantStringCount(test5);
        System.out.println("Test Case 5: " + (result5 == expected5 ? "PASS" : "FAIL") + " | Expected: " + expected5 + ", Got: " + result5);
    }
}
