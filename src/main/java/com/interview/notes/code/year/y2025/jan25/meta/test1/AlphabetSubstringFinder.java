package com.interview.notes.code.year.y2025.jan25.meta.test1;

import java.util.HashMap;
import java.util.Map;

public class AlphabetSubstringFinder {

    public static void main(String[] args) {
        runTests();
    }

    // Function to find the shortest substring containing all letters of the alphabet
    public static String shortestSubstring(String input, String alphabet) {
        if (input == null || alphabet == null || input.length() < alphabet.length() || alphabet.isEmpty()) {
            return "";
        }

        // Frequency map for alphabet characters
        Map<Character, Integer> requiredCounts = new HashMap<>();
        for (char ch : alphabet.toCharArray()) {
            requiredCounts.put(ch, requiredCounts.getOrDefault(ch, 0) + 1);
        }

        // Variables for sliding window
        int required = requiredCounts.size();
        int formed = 0;
        Map<Character, Integer> windowCounts = new HashMap<>();
        int left = 0, right = 0;
        int[] ans = {-1, 0, 0}; // length, left, right

        while (right < input.length()) {
            char ch = input.charAt(right);
            windowCounts.put(ch, windowCounts.getOrDefault(ch, 0) + 1);

            // If current character is part of alphabet and counts match requirement
            if (requiredCounts.containsKey(ch) &&
                    windowCounts.get(ch).intValue() == requiredCounts.get(ch).intValue()) {
                formed++;
            }

            // Try to contract the window till it ceases to be valid
            while (left <= right && formed == required) {
                ch = input.charAt(left);
                // Save smallest window so far
                if (ans[0] == -1 || right - left + 1 < ans[0]) {
                    ans[0] = right - left + 1;
                    ans[1] = left;
                    ans[2] = right;
                }

                windowCounts.put(ch, windowCounts.get(ch) - 1);
                if (requiredCounts.containsKey(ch) && windowCounts.get(ch) < requiredCounts.get(ch)) {
                    formed--;
                }
                left++;
            }
            right++;
        }

        return ans[0] == -1 ? "" : input.substring(ans[1], ans[2] + 1);
    }

    // Testing method
    private static void runTests() {
        // Array of test cases: {input, alphabet, expectedOutput}
        String[][] testCases = {
                {"aacbc", "abc", "accb"},
                {"adobecodebanc", "abc", "banc"},
                {"abcdef", "abc", "abc"},
                {"abcbcba", "abc", "abc"},
                {"", "abc", ""},                // Empty input
                {"abc", "", ""},                // Empty alphabet
                {"a", "ab", ""},                // Input missing characters
                // Add additional edge and large data cases if needed
        };

        for (int i = 0; i < testCases.length; i++) {
            String input = testCases[i][0];
            String alphabet = testCases[i][1];
            String expected = testCases[i][2];
            String result = shortestSubstring(input, alphabet);
            boolean pass = expected.equals(result);
            System.out.println("Test Case " + (i + 1) + ": " + (pass ? "PASS" : "FAIL"));
            if (!pass) {
                System.out.println("   Input: \"" + input + "\", Alphabet: \"" + alphabet + "\"");
                System.out.println("   Expected: \"" + expected + "\", Got: \"" + result + "\"");
            }
        }
    }
}
