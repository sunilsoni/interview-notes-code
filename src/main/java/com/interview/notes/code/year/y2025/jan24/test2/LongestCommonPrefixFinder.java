package com.interview.notes.code.year.y2025.jan24.test2;

public class LongestCommonPrefixFinder {

    /**
     * Finds the longest common prefix among an array of strings.
     * @param strs Array of input strings.
     * @return The longest common prefix.
     */
    public static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        // Start with the first string as the prefix.
        String prefix = strs[0];
        // Compare the prefix with each string in the array.
        for (int i = 1; i < strs.length; i++) {
            // Trim prefix until it's a prefix of strs[i].
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) return "";
            }
        }
        return prefix;
    }

    /**
     * Utility method to run a single test case.
     */
    public static void runTest(String[] input, String expected, int testCaseNumber) {
        String result = longestCommonPrefix(input);
        if (result.equals(expected)) {
            System.out.println("Test case " + testCaseNumber + " PASS");
        } else {
            System.out.println("Test case " + testCaseNumber + " FAIL - Expected: '" 
                               + expected + "', but got: '" + result + "'");
        }
    }

    public static void main(String[] args) {
        // Test array provided in the problem statement.
        String[] filledArr = {"India", "Individual", "Indefinite", "Indifferent", "Flow", "Flower", "Power"};

        // Since longest common prefix among all these isn't "Indi" (due to "Flow"), 
        // we consider the first four where "Indi" is common.
        String[] firstFour = {"India", "Individual", "Indefinite", "Indifferent"};
        int testCaseNumber = 1;

        // Expected output for the first four strings
        runTest(firstFour, "Indi", testCaseNumber++);

        // Additional test cases

        // 1. All strings share a prefix "abc"
        String[] arr1 = {"abcdef", "abcxyz", "abcpqr"};
        runTest(arr1, "abc", testCaseNumber++);

        // 2. No common prefix
        String[] arr2 = {"apple", "banana", "carrot"};
        runTest(arr2, "", testCaseNumber++);

        // 3. Single string input, prefix is the string itself.
        String[] arr3 = {"singleton"};
        runTest(arr3, "singleton", testCaseNumber++);

        // 4. Empty array of strings.
        String[] arr4 = {};
        runTest(arr4, "", testCaseNumber++);

        // 5. Array with empty string among others.
        String[] arr5 = {"", "abc", "abd"};
        runTest(arr5, "", testCaseNumber++);

        // 6. Large data input
        StringBuilder sb = new StringBuilder();
        // Create a large prefix repeated many times
        String largePrefix = "commonprefix";
        for (int i = 0; i < 10000; i++) {
            sb.append(largePrefix);
        }
        String commonBase = sb.toString();
        String[] largeInput = {commonBase + "suffix1", commonBase + "suffix2", commonBase + "suffix3"};
        runTest(largeInput, commonBase, testCaseNumber++);
    }
}
