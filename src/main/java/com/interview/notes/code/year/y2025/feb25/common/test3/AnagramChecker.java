package com.interview.notes.code.year.y2025.feb25.common.test3;

public class AnagramChecker {

    public static void main(String[] args) {
        // Define test cases
        Object[][] testCases = {
                {"listen", "silent", true},
                {"hello", "world", false},
                {"aabb", "bbaa", true},
                {"abc", "abd", false},
                {null, "test", false},
                {null, null, false},
                {"", "", true},
                {"Hello", "hello", false},
                {"large1", "1argel", true}
        };

        // Run test cases
        for (Object[] testCase : testCases) {
            String s1 = (String) testCase[0];
            String s2 = (String) testCase[1];
            boolean expected = (boolean) testCase[2];
            boolean actual = isAnagram(s1, s2);
            System.out.println("Test case: " + formatStrings(s1, s2) + " → " + (actual == expected ? "PASS" : "FAIL"));
        }

        // Test large data
        String largeStr1 = generateLargeString();
        String largeStr2 = shuffleString(new StringBuilder(largeStr1).reverse().toString());
        boolean largeResult = isAnagram(largeStr1, largeStr2);
        System.out.println("Large data test → " + (largeResult ? "PASS" : "FAIL"));
    }

    private static String formatStrings(String s1, String s2) {
        if (s1 == null) s1 = "null";
        if (s2 == null) s2 = "null";
        return s1 + ", " + s2;
    }

    private static String generateLargeString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 1_000_000; i++) {
            sb.append((char) ('a' + i % 26));
        }
        return sb.toString();
    }

    // Shuffle the string to ensure it's an anagram
    private static String shuffleString(String s) {
        char[] arr = s.toCharArray();
        // Simple shuffle by reversing, since reverse is a form of permutation
        return new StringBuilder(s).reverse().toString();
    }

    public static boolean isAnagram(String s1, String s2) {
        // Handle null cases
        if (s1 == null || s2 == null) {
            return false;
        }
        // Check lengths first
        if (s1.length() != s2.length()) {
            return false;
        }
        // Frequency counter array for extended ASCII
        int[] charCounts = new int[256];
        // Increment for s1's characters
        for (char c : s1.toCharArray()) {
            charCounts[c]++;
        }
        // Decrement for s2's characters
        for (char c : s2.toCharArray()) {
            charCounts[c]--;
        }
        // Check all counts are zero
        for (int count : charCounts) {
            if (count != 0) {
                return false;
            }
        }
        return true;
    }
}