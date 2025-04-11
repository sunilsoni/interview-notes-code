package com.interview.notes.code.year.y2025.april.common.test5;

public class LongestSubstringFinder {
    public static String findLongestSubstring(String input) {
        if (input == null || input.isEmpty()) {
            return "";
        }

        String longest = "";
        String current = "";

        for (char c : input.toCharArray()) {
            // If character already exists in current substring
            int index = current.indexOf(c);
            
            if (index != -1) {
                // Start new substring from character after the repeated one
                current = current.substring(index + 1);
            }
            current += c;
            
            // Update longest if current is longer
            if (current.length() > longest.length()) {
                longest = current;
            }
        }
        return longest;
    }

    public static void main(String[] args) {
        // Test cases
        test("abcabcbb", "abc");
        test("bbbbb", "b");
        test("pwwkew", "wke");
        test("", "");
        test("a", "a");
        test("aab", "ab");
        test("dvdf", "vdf");
        
        // Large input test
        StringBuilder large = new StringBuilder();
        for (int i = 0; i < 10000; i++) {
            large.append((char)('a' + (i % 26)));
        }
        test(large.toString(), large.substring(0, 26));
    }

    private static void test(String input, String expected) {
        String result = findLongestSubstring(input);
        System.out.printf("Input: %s\nExpected: %s\nGot: %s\nTest: %s\n\n",
            input.length() > 50 ? input.substring(0, 47) + "..." : input,
            expected,
            result,
            expected.length() == result.length() ? "PASS" : "FAIL");
    }
}
