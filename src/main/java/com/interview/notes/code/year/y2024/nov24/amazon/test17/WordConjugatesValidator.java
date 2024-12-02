package com.interview.notes.code.year.y2024.nov24.amazon.test17;

public class WordConjugatesValidator {

    public static void main(String[] args) {
        // Test cases
        runTestCases();

        // Additional stress test with large input
        runLargeInputTest();
    }

    private static long countValidSubstrings(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }

        int n = s.length();
        long count = 0;

        // Iterate through all possible substrings
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j <= n; j++) {
                if (isValidSubstring(s.substring(i, j))) {
                    count++;
                }
            }
        }

        return count;
    }

    private static boolean isValidSubstring(String sub) {
        // Count frequencies of each character
        int[] freq = new int[4]; // for a,b,c,d
        for (char c : sub.toCharArray()) {
            switch (c) {
                case 'a':
                    freq[0]++;
                    break;
                case 'b':
                    freq[1]++;
                    break;
                case 'c':
                    freq[2]++;
                    break;
                case 'd':
                    freq[3]++;
                    break;
            }
        }

        // Check if conjugate pairs are balanced
        return (freq[0] == freq[1] || (freq[0] == 0 && freq[1] == 0)) &&
                (freq[2] == freq[3] || (freq[2] == 0 && freq[3] == 0));
    }

    private static void runTestCases() {
        // Test case 1
        String test1 = "abdc";
        long expected1 = 3;
        testAndPrint("Test 1", test1, expected1);

        // Test case 2
        String test2 = "adcb";
        long expected2 = 2;
        testAndPrint("Test 2", test2, expected2);

        // Test case 3
        String test3 = "abcdad";
        long expected3 = 6;
        testAndPrint("Test 3", test3, expected3);

        // Edge cases
        testAndPrint("Empty string", "", 0);
        testAndPrint("Single char", "a", 0);
        testAndPrint("Simple pair", "ab", 1);
    }

    private static void runLargeInputTest() {
        // Generate large input string
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            sb.append("abcd");
        }
        String largeInput = sb.toString();

        System.out.println("\nRunning large input test (length: " + largeInput.length() + ")...");
        long startTime = System.currentTimeMillis();
        long result = countValidSubstrings(largeInput);
        long endTime = System.currentTimeMillis();

        System.out.println("Large input test completed in " + (endTime - startTime) + "ms");
        System.out.println("Valid substrings found: " + result);
    }

    private static void testAndPrint(String testName, String input, long expected) {
        long result = countValidSubstrings(input);
        boolean passed = result == expected;

        System.out.println(testName + ":");
        System.out.println("Input: \"" + input + "\"");
        System.out.println("Expected: " + expected);
        System.out.println("Got: " + result);
        System.out.println("Status: " + (passed ? "PASS" : "FAIL"));
        System.out.println();
    }
}