package com.interview.notes.code.months.oct24.test13;

public class LongestSubstringWithoutRepeats {

    public static int longestSubstring(String str) {
        if (str == null || str.isEmpty()) {
            return 0;
        }

        int[] lastIndex = new int[256]; // Assuming ASCII characters
        for (int i = 0; i < 256; i++) {
            lastIndex[i] = -1;
        }

        int maxLength = 0;
        int start = 0;

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (lastIndex[c] >= start) {
                start = lastIndex[c] + 1;
            }
            lastIndex[c] = i;
            maxLength = Math.max(maxLength, i - start + 1);
        }

        return maxLength;
    }

    public static void testLongestSubstring() {
        String[][] testCases = {
            {"ABCDEFGABEF", "7"},
            {"GEEKSFORGEEKS", "7"},
            {"", "0"},
            {"A", "1"},
            {"AAAAA", "1"},
            {"ABCABCBB", "3"},
            {"PWWKEW", "3"},
            {"ABBA", "2"},
            {null, "0"},
            {"aAbBcC", "6"}, // Case-sensitive
            {"ThisIsAVeryLongStringToTestPerformance", "10"},
            {"abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789", "62"}
        };

        for (String[] testCase : testCases) {
            String input = testCase[0];
            int expected = Integer.parseInt(testCase[1]);
            int result = longestSubstring(input);

            System.out.println("Test case: " + input);
            System.out.println("Expected: " + expected + ", Result: " + result);
            System.out.println(expected == result ? "PASS" : "FAIL");
            System.out.println();
        }
    }

    public static void main(String[] args) {
        testLongestSubstring();
    }
}
