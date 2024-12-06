package com.interview.notes.code.months.dec24.test4;

public class LongestCommonPrefix {

    public static String findLongestCommonPrefix(String[] arr) {
        if (arr == null || arr.length == 0) {
            return "";
        }

        // Take first string as reference
        String firstWord = arr[0];

        for (int i = 0; i < firstWord.length(); i++) {
            char currentChar = firstWord.charAt(i);

            // Compare with other strings
            for (int j = 1; j < arr.length; j++) {
                // If we reached end of any string or character doesn't match
                if (i >= arr[j].length() || arr[j].charAt(i) != currentChar) {
                    return firstWord.substring(0, i);
                }
            }
        }

        return firstWord;
    }

    public static void main(String[] args) {
        // Test Case 1: Basic test with given array
        String[] test1 = {"India", "Individual", "Indefinite", "Indifferent", "Flow", "Flower", "Power"};
        testCase(test1, "");  // Expected: "" (no common prefix due to Flow, Power)

        // Test Case 2: Array with common prefix
        String[] test2 = {"India", "Individual", "Indefinite", "Indifferent"};
        testCase(test2, "Ind");  // Expected: "Ind"

        // Test Case 3: Empty array
        String[] test3 = {};
        testCase(test3, "");  // Expected: ""

        // Test Case 4: Single element
        String[] test4 = {"Hello"};
        testCase(test4, "Hello");  // Expected: "Hello"

        // Test Case 5: Large data test
        String[] test5 = new String[1000];
        for (int i = 0; i < 1000; i++) {
            test5[i] = "TestPrefix" + i;
        }
        testCase(test5, "TestPrefix");  // Expected: "TestPrefix"
    }

    private static void testCase(String[] input, String expected) {
        String result = findLongestCommonPrefix(input);
        System.out.println("Input: " + java.util.Arrays.toString(input));
        System.out.println("Expected: '" + expected + "'");
        System.out.println("Got: '" + result + "'");
        System.out.println("Test " + (result.equals(expected) ? "PASSED" : "FAILED"));
        System.out.println("------------------------");
    }
}
