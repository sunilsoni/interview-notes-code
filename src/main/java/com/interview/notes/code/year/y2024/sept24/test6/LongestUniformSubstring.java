package com.interview.notes.code.year.y2024.sept24.test6;

public class LongestUniformSubstring {

    public static void main(String[] args) {
        // Test cases
        String[] testCases = {
                "abbbccda",
                "aabbbbbCdAA",
                "",
                "a",
                "aa",
                "aabbbcccc"
        };

        for (String testCase : testCases) {
            int[] result = longestUniformSubstring(testCase);
            System.out.println("Input: \"" + testCase + "\"");
            System.out.println("Output: [" + result[0] + ", " + result[1] + "]");
            System.out.println();
        }
    }

    public static void main1(String[] args) {
        String input = "abbbcda";
        int[] result = longestUniformSubstring(input);
        System.out.println("Start Index: " + result[0] + ", Length: " + result[1]);
    }

    public static int[] longestUniformSubstring(String input) {
        if (input == null || input.isEmpty()) {
            return new int[]{0, 0}; // edge case: empty string
        }

        int start = 0;
        int maxLength = 0;
        int currentStart = 0;
        int currentLength = 1; // at least one character

        for (int i = 1; i < input.length(); i++) {
            if (input.charAt(i) == input.charAt(i - 1)) {
                currentLength++;
            } else {
                if (currentLength > maxLength) {
                    maxLength = currentLength;
                    start = currentStart;
                }
                currentStart = i;
                currentLength = 1;
            }
        }

        // Final check for the last substring
        if (currentLength > maxLength) {
            maxLength = currentLength;
            start = currentStart;
        }

        return new int[]{start, maxLength};
    }
}
