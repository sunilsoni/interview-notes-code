package com.interview.notes.code.year.y2025.september.common.test6;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LongestUniqueSubstringTester {

    // Method to find the longest substring without repeating characters
    public static String longestUniqueSubstring(String s) {
        if (s == null || s.isEmpty()) {                   // if input is null or empty
            return "";                                    // nothing to process, return empty string
        }
        Map<Character, Integer> lastSeen = new HashMap<>();// map to track last seen index of each character
        int windowStart = 0;                              // start index of current sliding window
        int maxLen = 0;                                   // length of the longest unique-character window found
        int maxStart = 0;                                 // start index of that maximum window

        for (int windowEnd = 0; windowEnd < s.length(); windowEnd++) { // iterate end index through the string
            char currentChar = s.charAt(windowEnd);       // get character at current end index

            // if we've seen this character before inside the current window...
            if (lastSeen.containsKey(currentChar) && lastSeen.get(currentChar) >= windowStart) {
                // move window start just after the previous occurrence
                windowStart = lastSeen.get(currentChar) + 1;
            }

            lastSeen.put(currentChar, windowEnd);         // update last seen index for this character

            // if current window is larger than any we've seen before...
            if (windowEnd - windowStart + 1 > maxLen) {
                maxLen = windowEnd - windowStart + 1;     // update maximum length
                maxStart = windowStart;                   // record where that window starts
            }
        }

        // extract and return the longest unique-character substring
        return s.substring(maxStart, maxStart + maxLen);
    }

    // Helper to generate a very large test string by repeating the alphabet
    private static String generateLargeInput() {
        StringBuilder sb = new StringBuilder();           // use StringBuilder for efficient concatenation
        for (int i = 0; i < 1000; i++) {                  // repeat 1000 times
            sb.append("abcdefghijklmnopqrstuvwxyz");     // append the full lowercase alphabet
        }
        return sb.toString();                             // return the constructed large string
    }

    // Helper to shorten or label inputs when printing test results
    private static String summarizeInput(String input) {
        if (input == null) {                              // if input is null
            return "null";                                // represent it explicitly
        }
        if (input.length() > 50) {                        // if input is very long
            return "String(length=" + input.length() + ")"; // show only its length
        }
        return "\"" + input + "\"";                       // otherwise show the actual input in quotes
    }

    // Main method to run test cases and report PASS/FAIL
    public static void main(String[] args) {
        // list of test cases: each is a [input, expectedOutput] pair
        List<String[]> testCases = Arrays.asList(
                new String[]{"abcabcbb", "abc"},              // simple repeating pattern
                new String[]{"bbbbb", "b"},                   // all same characters
                new String[]{"pwwkew", "wke"},                // mixed repeats with non-adjacent repeat
                new String[]{"", ""},                         // empty string edge case
                new String[]{null, ""},                       // null input edge case
                new String[]{generateLargeInput(), "abcdefghijklmnopqrstuvwxyz"} // large input performance test
        );

        // use Stream API to iterate through each test case
        testCases.stream().forEach(test -> {
            String input = test[0];                       // extract test input
            String expected = test[1];                    // extract expected output
            String result = longestUniqueSubstring(input);// compute actual result
            boolean passed = expected.equals(result);     // check if it matches expected

            // print test summary and PASS/FAIL
            System.out.println(
                    "Test Input: " + summarizeInput(input)
                            + " | Expected: \"" + expected + "\""
                            + " | Got:      \"" + result + "\""
                            + " => " + (passed ? "PASS" : "FAIL")
            );
        });
    }
}