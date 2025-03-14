package com.interview.notes.code.year.y2025.march.common.test16;

import java.util.*;

public class RemoveDuplicatesRecursion {

    // Recursive function to remove duplicates
    public static String removeDuplicates(String str) {
        // Base case: if the string is empty or has one character, return as is
        if (str.length() <= 1)
            return str;

        // If first character repeats in the next position, skip it
        if (str.charAt(0) == str.charAt(1))
            return removeDuplicates(str.substring(1)); // Recursive call without first char
        else
            // Include first char and call recursively for the rest
            return str.charAt(0) + removeDuplicates(str.substring(1));
    }

    public static void main(String[] args) {
        // Define test cases
        Map<String, String> testCases = new LinkedHashMap<>();
        testCases.put("aaaabddd", "abd");
        testCases.put("aabbcc", "abc");
        testCases.put("abcd", "abcd");
        testCases.put("aaaa", "a");
        testCases.put("", "");
        testCases.put("aaabbbcccaaa", "abca");

        // Large input test case (edge case)
        char[] largeInput = new char[10000];
        Arrays.fill(largeInput, 'a');
        testCases.put(new String(largeInput), "a");

        // Process and validate each test case
        testCases.forEach((input, expected) -> {
            String result = removeDuplicates(input);
            boolean pass = result.equals(expected);
            System.out.printf("Input: '%s' | Expected: '%s' | Result: '%s' | Test: %s\n",
                    input.length() > 20 ? input.substring(0, 20) + "..." : input,
                    expected, result, pass ? "PASS" : "FAIL");
        });
    }
}
