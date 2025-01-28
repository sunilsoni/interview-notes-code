package com.interview.notes.code.year.y2025.jan24.glider.test1;

public class StringDifference {
    public static String Difference(String firstString, String scndString) {
        if (firstString == null || scndString == null) {
            return "Invalid input";
        }

        // Convert strings to char arrays and sort them
        char[] first = firstString.toCharArray();
        char[] second = scndString.toCharArray();
        java.util.Arrays.sort(first);
        java.util.Arrays.sort(second);

        // Find different character
        StringBuilder result = new StringBuilder();
        int i = 0, j = 0;

        while (i < first.length && j < second.length) {
            if (first[i] != second[j]) {
                result.append(second[j]);
                break;
            }
            i++;
            j++;
        }

        // Check remaining characters
        while (j < second.length && result.length() == 0) {
            result.append(second[j]);
            j++;
        }

        return result.length() > 0 ? result.toString() : "No difference";
    }

    public static void main(String[] args) {
        // Test cases
        testCase("abcd", "abcde", "e", "Test 1");
        testCase("sals", "salsa", "No difference", "Test 2");
        testCase("", "", "No difference", "Test 3");
        testCase(null, "abc", "Invalid input", "Test 4");
        testCase("xyz", "xyza", "a", "Test 5");
        testCase("hello", "hello", "No difference", "Test 6");

        // Large data test
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        for (int i = 0; i < 10000; i++) {
            sb1.append('a');
            sb2.append('a');
        }
        sb2.append('b');
        testCase(sb1.toString(), sb2.toString(), "b", "Large Data Test");
    }

    private static void testCase(String s1, String s2, String expected, String testName) {
        String result = Difference(s1, s2);
        System.out.println(testName + ": " +
                (result.equals(expected) ? "PASS" : "FAIL") +
                " (Expected: " + expected + ", Got: " + result + ")");
    }
}
