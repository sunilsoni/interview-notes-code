package com.interview.notes.code.months.sept24.test7;

public class LongestUniformSubstring {

    /**
     * Finds the longest uniform substring in the given input string.
     *
     * @param input The input string to search
     * @return An integer array where the first element is the starting index
     * of the longest uniform substring and the second element is its length
     */
    public static int[] longestUniformSubstring(String input) {
        if (input == null || input.isEmpty()) {
            return new int[]{-1, 0};
        }

        int maxStart = 0;
        int maxLength = 1;
        int currentStart = 0;
        int currentLength = 1;

        for (int i = 1; i < input.length(); i++) {
            if (input.charAt(i) == input.charAt(i - 1)) {
                currentLength++;
                if (currentLength > maxLength) {
                    maxLength = currentLength;
                    maxStart = currentStart;
                }
            } else {
                currentStart = i;
                currentLength = 1;
            }
        }

        return new int[]{maxStart, maxLength};
    }

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
}
