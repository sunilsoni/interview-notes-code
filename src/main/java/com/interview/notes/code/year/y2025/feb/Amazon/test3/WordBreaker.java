package com.interview.notes.code.year.y2025.feb.Amazon.test3;

import java.util.*;

public class WordBreaker {

    // A sample dictionary of valid English words.
    // For a real application, this dictionary should be extended.
    private static final Set<String> DICTIONARY = new HashSet<>(Arrays.asList(
            "the", "them", "ant", "ants", "man", "mants", "an", "a", "at", "i", "am", "at"
    ));

    /**
     * Inserts whitespaces back into a string that is a concatenation of valid English words.
     *
     * @param s The input string with no spaces.
     * @return The segmented string with spaces, or null if no valid segmentation exists.
     */
    public static String addSpaces(String s) {
        // Use a HashMap to memoize results for starting indices.
        Map<Integer, List<String>> memo = new HashMap<>();
        List<String> segmentation = helper(s, 0, memo);
        return segmentation == null ? null : String.join(" ", segmentation);
    }

    /**
     * Helper function to recursively segment the string.
     *
     * @param s     The input string.
     * @param start The current starting index in the string.
     * @param memo  A map used for memoization to avoid redundant work.
     * @return A list of words that forms a valid segmentation, or null if segmentation is not possible.
     */
    private static List<String> helper(String s, int start, Map<Integer, List<String>> memo) {
        // When we've reached the end of the string, return an empty list as a successful segmentation.
        if (start == s.length()) {
            return new ArrayList<>();
        }

        // Return memoized result if available.
        if (memo.containsKey(start)) {
            return memo.get(start);
        }

        // Try every possible end index for the current prefix.
        for (int end = start + 1; end <= s.length(); end++) {
            String word = s.substring(start, end);
            if (DICTIONARY.contains(word)) {
                // If the prefix is a valid word, try to segment the remaining string.
                List<String> result = helper(s, end, memo);
                if (result != null) {
                    List<String> segmentation = new ArrayList<>();
                    segmentation.add(word);
                    segmentation.addAll(result);
                    memo.put(start, segmentation);
                    return segmentation;
                }
            }
        }
        // No valid segmentation found for this index; memoize the failure.
        memo.put(start, null);
        return null;
    }

    /**
     * Main method to run test cases.
     */
    public static void main(String[] args) {
        // Define test cases.
        // Each test case is a mapping of input string to the expected segmented string.
        // If segmentation is not possible, expected result is null.
        Map<String, String> testCases = new LinkedHashMap<>();
        testCases.put("themants", "them ants");  // Example provided
        testCases.put("theman", "the man");       // Expected segmentation: "the" "man"
        testCases.put("", "");                    // Edge case: empty string
        testCases.put("iam", "i am");             // Expected segmentation: "i" "am"
        testCases.put("xyz", null);               // No valid segmentation

        // For simulating a larger data input, we can construct a longer string.
        // Here, we use a repetition of valid segments if the dictionary supports them.
        // For example, "theantatant" should segment as "the ant at ant" if "at" is in the dictionary.
        testCases.put("theantatant", "the ant at ant");

        boolean allPassed = true;
        int testNumber = 1;

        // Process each test case.
        for (Map.Entry<String, String> test : testCases.entrySet()) {
            String input = test.getKey();
            String expected = test.getValue();
            String result = addSpaces(input);

            // Check if both expected and result are null or if they match exactly.
            boolean passed = (expected == null && result == null) ||
                    (expected != null && expected.equals(result));

            System.out.println("Test Case " + testNumber + ":");
            System.out.println("   Input:    \"" + input + "\"");
            System.out.println("   Expected: \"" + expected + "\"");
            System.out.println("   Got:      \"" + result + "\"");
            System.out.println("   Result:   " + (passed ? "PASS" : "FAIL"));
            System.out.println();

            if (!passed) {
                allPassed = false;
            }
            testNumber++;
        }

        System.out.println(allPassed ? "All test cases passed." : "Some test cases failed.");
    }
}
