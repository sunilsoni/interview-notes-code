package com.interview.notes.code.year.y2024.sept24.test6;

public class MaxSubstring {

    public static void main(String[] args) {
        // Test cases
        testCase("baca", "ca", 1);
        testCase("banana", "nana", 2);
        testCase("abcdefg", "g", 3);
        testCase("a", "a", 4);
        testCase("zyxwvutsrqponmlkjihgfedcba", "zyxwvutsrqponmlkjihgfedcba", 5);
        testCase("aaaaaa", "aaaaaa", 6);

        // Large input test case
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 100; i++) {
            sb.append((char) ('a' + (i % 26)));
        }
        testCase(sb.toString(), "zyxwvutsrqponmlkjihgfedcba", 7);
    }

    /**
     * Finds the alphabetically maximum substring in the given string.
     *
     * @param s The input string
     * @return The alphabetically maximum substring
     */
    public static String maxSubstring(String s) {
        if (s == null || s.isEmpty()) {
            return "";
        }

        String maxSub = "";
        for (int i = 0; i < s.length(); i++) {
            String currentSub = s.substring(i);
            if (currentSub.compareTo(maxSub) > 0) {
                maxSub = currentSub;
            }
        }
        return maxSub;
    }

    /**
     * Helper method to run and validate test cases.
     *
     * @param input      The input string
     * @param expected   The expected output
     * @param testNumber The test case number
     */
    private static void testCase(String input, String expected, int testNumber) {
        String result = maxSubstring(input);
        if (result.equals(expected)) {
            System.out.println("Test Case " + testNumber + ": Passed");
        } else {
            System.out.println("Test Case " + testNumber + ": Failed");
            System.out.println("  Expected: " + expected);
            System.out.println("  Got: " + result);
        }
    }
}
