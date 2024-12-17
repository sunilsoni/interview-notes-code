package com.interview.notes.code.months.dec24.test9;

import java.util.Arrays;

/*
WORKING

java string array i want to identify common prefix in all the strings

 */
public class CommonPrefixFinder {
    public static String findCommonPrefix(String[] strs) {
        // Handle edge cases
        if (strs == null || strs.length == 0) {
            return "";
        }
        if (strs.length == 1) {
            return strs[0];
        }

        // Take first string as initial prefix
        String prefix = strs[0];

        // Compare with other strings
        for (int i = 1; i < strs.length; i++) {
            // Reduce prefix while it doesn't match current string
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) {
                    return "";
                }
            }
        }
        return prefix;
    }

    public static void main(String[] args) {
        // Test cases
        test(
                new String[]{"flower", "flow", "flight"},
                "fl",
                "Basic test with common prefix"
        );

        test(
                new String[]{"dog", "racecar", "car"},
                "",
                "No common prefix"
        );

        test(
                new String[]{"interspecies", "interstellar", "interstate"},
                "inters",
                "Longer common prefix"
        );

        test(
                new String[]{},
                "",
                "Empty array"
        );

        test(
                new String[]{"hello"},
                "hello",
                "Single string"
        );

        test(
                new String[]{"", "b"},
                "",
                "Empty string in array"
        );

        // Large input test
        String[] largeInput = new String[1000];
        Arrays.fill(largeInput, "testprefix123");
        test(
                largeInput,
                "testprefix123",
                "Large input with same strings"
        );
    }

    private static void test(String[] input, String expected, String testName) {
        String result = findCommonPrefix(input);
        boolean passed = result.equals(expected);
        System.out.printf("Test: %s - %s%n", testName, passed ? "PASS" : "FAIL");
        if (!passed) {
            System.out.printf("Expected: '%s', Got: '%s'%n", expected, result);
        }
    }
}
