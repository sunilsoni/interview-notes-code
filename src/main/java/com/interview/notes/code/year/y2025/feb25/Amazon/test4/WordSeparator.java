package com.interview.notes.code.year.y2025.feb25.Amazon.test4;

import java.util.*;

/*

 Given a string sequence of valid English words that have had its whitespace removed,
   write a function that will input whitespaces back into the sequence to return a string sequence of valid English words.
   ex: themants -> them ants
 */
public class WordSeparator {

    /**
     * Attempts to insert spaces into the given string to form valid words.
     * @param s the input string with no spaces.
     * @param dictionary the set of valid English words.
     * @return a valid segmented string if possible, otherwise returns null.
     */
    public static String insertSpaces(String s, Set<String> dictionary) {
        // Using memoization to store already computed results for substrings
        return helper(s, dictionary, new HashMap<>());
    }

    private static String helper(String s, Set<String> dictionary, Map<String, String> memo) {
        // If the substring has been processed before, return its result.
        if (memo.containsKey(s)) {
            return memo.get(s);
        }

        // If the entire substring is a valid word, we could return it.
        if (dictionary.contains(s)) {
            memo.put(s, s);
            // But we continue checking in case a segmentation with spaces exists.
        }

        // Try every possible prefix of the string.
        for (int i = 1; i < s.length(); i++) {
            String prefix = s.substring(0, i);
            if (dictionary.contains(prefix)) {
                // Recursively attempt to solve for the rest of the string.
                String rest = helper(s.substring(i), dictionary, memo);
                if (rest != null) {
                    String result = prefix + " " + rest;
                    memo.put(s, result);
                    return result;
                }
            }
        }

        // If no valid segmentation is found, mark this substring as unsolvable.
        memo.put(s, null);
        return null;
    }

    /**
     * Testing method using a simple main method.
     */
    public static void main(String[] args) {
        // Build a small dictionary for demonstration purposes.
        Set<String> dictionary = new HashSet<>(Arrays.asList(
                "them", "ants", "ant", "man", "the", "mants"
        ));

        // List of test cases.
        Map<String, String> testCases = new LinkedHashMap<>();
        testCases.put("themants", "them ants");  // Expected: "them ants"
        testCases.put("theman", "the man");       // Expected: "the man"
        testCases.put("", "");                    // Expected: "" (empty string)
        testCases.put("unknown", null);           // Expected: null (cannot segment)

        // Add a test case for large data (simulate by repeating a valid word)
        String largeInput = String.join("", Collections.nCopies(10000, "the"));
        testCases.put(largeInput, String.join(" ", Collections.nCopies(10000, "the")));

        // Run tests.
        boolean allPassed = true;
        for (Map.Entry<String, String> entry : testCases.entrySet()) {
            String input = entry.getKey();
            String expected = entry.getValue();
            String result = insertSpaces(input, dictionary);
            // For the empty string case, handle appropriately.
            if (input.isEmpty() && (result == null || result.isEmpty())) {
                System.out.println("PASS: Input is empty.");
            } else if (Objects.equals(result, expected)) {
                System.out.println("PASS: Input \"" + input + "\" -> \"" + result + "\"");
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