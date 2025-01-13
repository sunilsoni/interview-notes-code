package com.interview.notes.code.year.y2025.jan24.test5;

public class LongestCommonPrefixFinder {

    /**
     * Finds the longest common prefix among an array of strings.
     *
     * @param strs the array of strings
     * @return the longest common prefix
     */
    public static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }

        // Start with the first string as the prefix
        String prefix = strs[0];

        // Compare the prefix with each string in the array
        for (int i = 1; i < strs.length; i++) {
            // Reduce the prefix length until it matches the start of strs[i]
            while (strs[i].indexOf(prefix) != 0) {
                // Shorten the prefix by one character
                prefix = prefix.substring(0, prefix.length() - 1);
                // If the prefix becomes empty, no common prefix exists
                if (prefix.isEmpty()) {
                    return "";
                }
            }
        }

        return prefix;
    }

    /**
     * Main method to run test cases.
     */
    public static void main(String[] args) {
        // Array of test cases
        String[][] testArrays = {
                // Test Case 1: Provided example
                {"India", "Individual", "Indefinite", "Indifferent", "Flow", "Flower", "Power"},
                // Test Case 2: Common prefix "Fl"
                {"Flower", "Flow", "Flight"},
                // Test Case 3: No common prefix
                {"Dog", "Racecar", "Car"},
                // Test Case 4: Empty array
                {},
                // Test Case 5: Single string
                {"Single"},
                // Test Case 6: Common prefix "Inter"
                {"Interstellar", "Internet", "Interval", "Interview"},
                // Test Case 7: Empty string in array
                {"", "Empty", "Emptier"},
                // Test Case 8: All strings are the same
                {"Same", "Same", "Same"}
        };

        // Expected outputs
        String[] expectedOutputs = {
                "Indi",
                "Fl",
                "",
                "",
                "Single",
                "Inter",
                "",
                "Same"
        };

        int passedTests = 0;

        // Run test cases
        for (int i = 0; i < testArrays.length; i++) {
            String result = longestCommonPrefix(testArrays[i]);
            if (result.equals(expectedOutputs[i])) {
                System.out.println("Test " + (i + 1) + ": PASS");
                passedTests++;
            } else {
                System.out.println("Test " + (i + 1) + ": FAIL");
                System.out.println("Expected Output: \"" + expectedOutputs[i] + "\"");
                System.out.println("Actual Output:   \"" + result + "\"");
            }
        }

        System.out.println(passedTests + " out of " + testArrays.length + " tests passed.");
    }
}