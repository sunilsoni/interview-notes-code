package com.interview.notes.code.months.sept24.test5;

/*

Maximum Substring
Description
A substring is a contiguous sequence of characters within a string. Given a string determine the alphabetically maximum substring.
Example
s = 'baca'
The unique substrings: ['b', 'ba', 'bac', 'baca', 'a', 'ac', 'aca', 'c', 'ca'] Arranging the substrings alphabetically: l'a', 'ac', 'aca', 'b', 'ba',
'bac', 'baca', 'c', 'ca']
The maximum substring alphabetically: 'ca'
Function Description
Complete the function maxSubstring in the editor below.
maxSubstring has the following parameter(s):
strings: a string
Returns
string: the maximum substring in s
Constraints
• 1 ≤ length of s ≤ 100
• all characters of s are in the range asciila-z]

 */
public class MaxSubstring {

    public static void main(String[] args) {
        // Test cases
        testCase("baca", "ca", 1);
        testCase("banana", "nana", 2);
        testCase("zzzzz", "zzzzz", 3);
        testCase("a", "a", 4);
        testCase("abcdefghijklmnopqrstuvwxyz", "z", 5);
        testCase("zyxwvutsrqponmlkjihgfedcba", "zyxwvutsrqponmlkjihgfedcba", 6);

        // Large input test case
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 100; i++) {
            sb.append((char) ('a' + (i % 26)));
        }
        testCase(sb.toString(), "zyxwvutsrqponmlkjihgfedcba", 7);
    }

    public static String maxSubstring(String s) {
        // Edge case: empty string
        if (s == null || s.isEmpty()) {
            return "";
        }

        // Initialize the result with the first character
        String maxSub = s.substring(s.length() - 1);

        // Iterate through the string from right to left
        for (int i = s.length() - 2; i >= 0; i--) {
            String currentSub = s.substring(i);
            // Compare the current substring with the max substring found so far
            if (currentSub.compareTo(maxSub) > 0) {
                maxSub = currentSub;
            }
        }

        return maxSub;
    }

    private static void testCase(String input, String expected, int testNumber) {
        String result = maxSubstring(input);
        System.out.println("Test Case " + testNumber + ": " +
                (result.equals(expected) ? "Passed" : "Failed"));
        if (!result.equals(expected)) {
            System.out.println("  Expected: " + expected);
            System.out.println("  Got: " + result);
        }
    }
}
