package com.interview.notes.code.year.y2025.may.common.test10;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class LongestCommonPrefix {

    // Method to find longest common prefix
    public static String longestCommonPrefix(List<String> strs) {
        if (strs == null || strs.isEmpty()) return ""; // Handle empty or null lists

        // Sort the list; prefix would be common between first and last strings
        Collections.sort(strs);
        String first = strs.get(0);
        String last = strs.get(strs.size() - 1);

        // Identify common prefix length by comparing first and last strings
        int i = 0;
        while (i < first.length() && i < last.length() && first.charAt(i) == last.charAt(i)) {
            i++;
        }

        // Extract and return the common prefix substring
        return first.substring(0, i);
    }

    // Main method to execute simple test cases without external testing frameworks
    public static void main(String[] args) {
        List<List<String>> testCases = Arrays.asList(
                Arrays.asList("racecar", "race", "rat"),
                Arrays.asList("dog", "cat", "bird"),
                Arrays.asList("flower", "flow", "flight"),
                Arrays.asList("", ""),
                Arrays.asList("prefix", "pre", "prepare", "presume"),
                Collections.nCopies(100000, "performanceTest") // large input
        );

        List<String> expectedOutputs = Arrays.asList("ra", "", "fl", "", "pre", "performanceTest");

        IntStream.range(0, testCases.size()).forEach(i -> {
            String result = longestCommonPrefix(testCases.get(i));
            String status = result.equals(expectedOutputs.get(i)) ? "PASS" : "FAIL";
            System.out.printf("Test Case %d: Expected: '%s', Got: '%s' [%s]%n",
                    i + 1, expectedOutputs.get(i), result, status);
        });
    }
}
