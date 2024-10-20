package com.interview.notes.code.months.oct24.test13;

public class AnagramChecker {

    public static boolean areAnagrams(String str1, String str2) {
        if (str1 == null || str2 == null || str1.length() != str2.length()) {
            return false;
        }

        int[] charCount = new int[256]; // Assuming ASCII characters

        for (int i = 0; i < str1.length(); i++) {
            charCount[str1.charAt(i)]++;
            charCount[str2.charAt(i)]--;
        }

        for (int count : charCount) {
            if (count != 0) {
                return false;
            }
        }

        return true;
    }

    public static void testAnagramChecker() {
        String[][] testCases = {
                {"listen", "silent", "true"},
                {"hello", "world", "false"},
                {"anagram", "nagaram", "true"},
                {"rat", "car", "false"},
                {"", "", "true"},
                {"a", "a", "true"},
                {"aab", "aba", "true"},
                {"aaab", "aaba", "true"},
                {null, "hello", "false"},
                {"hello", null, "false"},
                {"A", "a", "false"}, // Case-sensitive
                {"abcdefghijklmnopqrstuvwxyz", "zyxwvutsrqponmlkjihgfedcba", "true"},
                {"ThisIsAVeryLongStringToTestPerformance", "stringThisIsAVeryLongToTestPerformance", "true"}
        };

        for (String[] testCase : testCases) {
            String str1 = testCase[0];
            String str2 = testCase[1];
            boolean expected = Boolean.parseBoolean(testCase[2]);
            boolean result = areAnagrams(str1, str2);

            System.out.println("Test case: " + str1 + ", " + str2);
            System.out.println("Expected: " + expected + ", Result: " + result);
            System.out.println(expected == result ? "PASS" : "FAIL");
            System.out.println();
        }
    }

    public static void main(String[] args) {
        testAnagramChecker();
    }
}
