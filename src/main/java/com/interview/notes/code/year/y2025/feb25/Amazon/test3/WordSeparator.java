package com.interview.notes.code.year.y2025.feb25.Amazon.test3;

import java.util.*;

/*
 Given a string sequence of valid English words that have had its whitespace removed,
   write a function that will input whitespaces back into the sequence to return a string sequence of valid English words.
   ex: themants -> them ants
 */
public class WordSeparator {

    /**
     * Inserts spaces into the given string so that each segmented part is a valid English word.
     *
     * @param s          the input string with no spaces
     * @param dictionary the set of valid English words
     * @return a valid segmented string if possible, otherwise returns null
     */
    public static String insertSpaces(String s, Set<String> dictionary) {
        // Use memoization to avoid reprocessing substrings.
        return helper(s, dictionary, new HashMap<>());
    }

    /**
     * Helper method that uses recursion and memoization to find a valid segmentation.
     *
     * @param s          the current substring to segment
     * @param dictionary the set of valid English words
     * @param memo       a map for memoizing results for substrings
     * @return a valid segmentation (with inserted spaces) if possible; otherwise null
     */
    private static String helper(String s, Set<String> dictionary, Map<String, String> memo) {
        // Base case: if the string is empty, return an empty string.
        if (s.isEmpty()) {
            return "";
        }
        // Return memoized result if available.
        if (memo.containsKey(s)) {
            return memo.get(s);
        }

        String result = null;
        int n = s.length();

        // Try possible splits in descending order of the prefix length.
        // This helps to prefer longer valid prefixes.
        for (int i = n - 1; i >= 1; i--) {
            String prefix = s.substring(0, i);
            if (dictionary.contains(prefix)) {
                String rest = helper(s.substring(i), dictionary, memo);
                if (rest != null) {
                    // Avoid adding an extra space if the rest is empty.
                    result = rest.isEmpty() ? prefix : prefix + " " + rest;
                    memo.put(s, result);
                    return result;
                }
            }
        }
        // If no split produced a valid segmentation, check if the entire string is a word.
        if (dictionary.contains(s)) {
            memo.put(s, s);
            return s;
        }
        // Mark as unsolvable.
        memo.put(s, null);
        return null;
    }

    /**
     * Main method for testing the segmentation logic.
     * It runs several test cases (including large inputs) and prints PASS/FAIL for each.
     */
    public static void main(String[] args) {
        // Build a small dictionary for demonstration purposes.
        Set<String> dictionary = new HashSet<>(Arrays.asList(
                "them", "ants", "ant", "man", "the", "mants"
        ));

        // List of test cases with expected outputs.
        Map<String, String> testCases = new LinkedHashMap<>();
        testCases.put("themants", "them ants");  // Expect: "them ants"
        testCases.put("theman", "the man");       // Expect: "the man"
        testCases.put("", "");                    // Expect: "" (empty string)
        testCases.put("unknown", null);           // Expect: null (cannot segment)

        // Test case for large data: simulate by repeating "the" 10,000 times.
        String largeInput = String.join("", Collections.nCopies(10000, "the"));
        // Expected segmentation is 10,000 occurrences of "the" separated by spaces.
        String expectedLarge = String.join(" ", Collections.nCopies(10000, "the"));
        testCases.put(largeInput, expectedLarge);

        // Run tests.
        boolean allPassed = true;
        for (Map.Entry<String, String> entry : testCases.entrySet()) {
            String input = entry.getKey();
            String expected = entry.getValue();
            String result = insertSpaces(input, dictionary);
            // Handle the empty string case.
            if (input.isEmpty() && (result == null || result.isEmpty())) {
                System.out.println("PASS: Input is empty.");
            } else if (Objects.equals(result, expected)) {
                // For debugging, you can print a snippet of the large input test.
                if (input.length() > 50) {
                    System.out.println("PASS: Large input test passed.");
                } else {
                    System.out.println("PASS: Input \"" + input + "\" -> \"" + result + "\"");
                }
            } else {
                System.out.println("FAIL: Input \"" + input + "\" Expected \"" + expected + "\" but got \"" + result + "\"");
                allPassed = false;
            }
        }
        if (allPassed) {
            System.out.println("All test cases passed!");
        } else {
            System.out.println("Some test cases failed.");
        }
    }
}