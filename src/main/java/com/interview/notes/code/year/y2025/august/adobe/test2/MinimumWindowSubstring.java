package com.interview.notes.code.year.y2025.august.adobe.test2;

public class MinimumWindowSubstring {
    public static String minWindow(String s, String t) {
        // Edge case: if either string is empty or t is longer than s
        if (s == null || t == null || s.length() < t.length()) {
            return "";
        }

        // Create frequency maps for characters in t and the sliding window
        int[] tFreq = new int[128]; // Array to store frequency of chars in t
        int[] windowFreq = new int[128]; // Array to store frequency of chars in current window

        // Count frequency of characters in string t
        t.chars().forEach(c -> tFreq[c]++);

        // Initialize variables for tracking window and result
        int start = 0; // Start of current window
        int minStart = 0; // Start of minimum window found
        int minLen = Integer.MAX_VALUE; // Length of minimum window
        int matched = 0; // Count of matched characters
        int required = (int) t.chars().distinct().count(); // Number of unique chars needed

        // Iterate through the string s using sliding window
        for (int end = 0; end < s.length(); end++) {
            char currentChar = s.charAt(end);
            windowFreq[currentChar]++; // Add current char to window

            // Check if we've matched a required character
            if (windowFreq[currentChar] == tFreq[currentChar] && tFreq[currentChar] > 0) {
                matched++;
            }

            // Try to minimize window by moving start pointer
            while (matched == required) {
                // Update minimum window if current window is smaller
                if (end - start + 1 < minLen) {
                    minStart = start;
                    minLen = end - start + 1;
                }

                // Remove character at start from window
                char startChar = s.charAt(start);
                windowFreq[startChar]--;

                // If removed char was required and now we're short, decrease matched count
                if (windowFreq[startChar] < tFreq[startChar] && tFreq[startChar] > 0) {
                    matched--;
                }
                start++;
            }
        }

        // Return the minimum window substring or empty string if not found
        return minLen == Integer.MAX_VALUE ? "" : s.substring(minStart, minStart + minLen);
    }

    public static void main(String[] args) {
        // Test cases
        testCase("ADOBECODEBANC", "ABC", "BANC", "Test Case 1");
        testCase("a", "a", "a", "Test Case 2");
        testCase("a", "aa", "", "Test Case 3");
        
        // Additional test cases
        testCase("", "ABC", "", "Empty String Test");
        testCase("ABCDEF", "", "", "Empty Pattern Test");
        testCase("AAAAAA", "AA", "AA", "Repeated Characters Test");
        
        // Large input test case
        StringBuilder largeSB = new StringBuilder();
        for (int i = 0; i < 100000; i++) {
            largeSB.append('A');
        }
        largeSB.append("BC");
        testCase(largeSB.toString(), "ABC", "ABC", "Large Input Test");
    }

    private static void testCase(String s, String t, String expected, String testName) {
        String result = minWindow(s, t);
        System.out.println(testName + ": " + 
            (result.equals(expected) ? "PASS" : "FAIL") +
            " (Expected: " + expected + ", Got: " + result + ")");
    }
}
