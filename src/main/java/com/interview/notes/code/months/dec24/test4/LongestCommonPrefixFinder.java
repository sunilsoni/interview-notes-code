package com.interview.notes.code.months.dec24.test4;

public class LongestCommonPrefixFinder {

    /**
     * Finds the longest common prefix among an array of strings.
     *
     * @param strs Array of strings to evaluate.
     * @return The longest common prefix. If none exists, returns an empty string.
     */
    public static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }

        // Start with the first string as the initial prefix
        String prefix = strs[0];

        // Iterate through each string in the array
        for (int i = 1; i < strs.length; i++) {
            // Reduce the prefix length until it matches the start of the current string
            while (strs[i].indexOf(prefix) != 0) {
                if (prefix.isEmpty()) {
                    return "";
                }
                // Remove the last character from the prefix
                prefix = prefix.substring(0, prefix.length() - 1);
            }
        }

        return prefix;
    }

    /**
     * Runs test cases to validate the longestCommonPrefix method.
     */
    public static void main(String[] args) {
        // Define test cases
        String[][] testCases = {
                // Test Case 1: Common prefix exists
                {"India", "Individual", "Indefinite", "Indifferent", "Indicate"},
                // Test Case 2: No common prefix
                {"Flow", "Flower", "Power"},
                // Test Case 3: All strings identical
                {"Test", "Test", "Test"},
                // Test Case 4: Single string
                {"Solo"},
                // Test Case 5: Empty array
                {},
                // Test Case 6: Array with empty strings
                {"", "Empty", "Another"},
                // Test Case 7: Large dataset
                generateLargeTestCase(10000, "CommonPrefixExample")
        };

        String[] expectedResults = {
                "Ind",
                "",
                "Test",
                "Solo",
                "",
                "",
                "CommonPrefixExample"
        };

        // Execute each test case
        for (int i = 0; i < testCases.length; i++) {
            String[] testCase = testCases[i];
            String expected = expectedResults[i];
            String result = longestCommonPrefix(testCase);
            if (result.equals(expected)) {
                System.out.println("Test Case " + (i + 1) + ": PASS");
            } else {
                System.out.println("Test Case " + (i + 1) + ": FAIL");
                System.out.println("Expected: \"" + expected + "\", but got: \"" + result + "\"");
            }
        }
    }

    /**
     * Generates a large test case with a specified number of strings sharing a common prefix.
     *
     * @param count        Number of strings to generate.
     * @param commonPrefix The common prefix for all generated strings.
     * @return An array of strings with the common prefix.
     */
    private static String[] generateLargeTestCase(int count, String commonPrefix) {
        String[] largeTestCase = new String[count];
        for (int i = 0; i < count; i++) {
            largeTestCase[i] = commonPrefix + i;
        }
        return largeTestCase;
    }
}
