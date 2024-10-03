package com.interview.notes.code.months.oct24.test1;

import java.util.*;

/*
Java Assessment
Part 1
Given a string s, remove duplicate letters so that every letter appears once and only once. You must make sure your result is the smallest in lexicographical order among all possible results. Example 1: Input: s =
"bcabc" Output: "abc" Note: Lexicographical order is similar to alphabetical order. For instance, "abc" is smaller than "acb".

 */
public class RemoveDuplicateLetters {

    // Method to remove duplicate letters and return the smallest lexicographical string
    public static String removeDuplicateLetters(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }

        // Frequency map to count the occurrences of each character
        int[] freq = new int[26];
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        // Stack to store the result characters
        Stack<Character> stack = new Stack<>();
        // Set to track which characters are already in the stack
        boolean[] visited = new boolean[26];

        // Traverse the string
        for (char c : s.toCharArray()) {
            // Decrement the frequency of the current character
            freq[c - 'a']--;

            // If the character is already in the stack, skip it
            if (visited[c - 'a']) {
                continue;
            }

            // Ensure lexicographical order by popping from the stack
            while (!stack.isEmpty() && stack.peek() > c && freq[stack.peek() - 'a'] > 0) {
                char removed = stack.pop();
                visited[removed - 'a'] = false;
            }

            // Add the current character to the stack
            stack.push(c);
            visited[c - 'a'] = true;
        }

        // Build the result string from the stack
        StringBuilder result = new StringBuilder();
        for (char c : stack) {
            result.append(c);
        }

        return result.toString();
    }

    // Test method to verify the solution
    public static void test() {
        String[] testCases = {"bcabc", "cbacdcbc", "abcd", "aabbcc", "a", ""};
        String[] expectedResults = {"abc", "acdb", "abcd", "abc", "a", ""};

        for (int i = 0; i < testCases.length; i++) {
            String result = removeDuplicateLetters(testCases[i]);
            if (result.equals(expectedResults[i])) {
                System.out.println("Test case " + (i + 1) + " passed.");
            } else {
                System.out.println("Test case " + (i + 1) + " failed. Expected: " + expectedResults[i] + ", Got: " + result);
            }
        }
    }

    // Main method to run the test cases
    public static void main(String[] args) {
        test();
    }
}
