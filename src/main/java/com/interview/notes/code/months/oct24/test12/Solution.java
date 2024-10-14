package com.interview.notes.code.months.oct24.test12;

import java.util.*;

public class Solution {
    private static final Map<String, int[]> testCases = new HashMap<>();

    static int[] getSubstringInfo(String input) {
        if (input == null || input.isEmpty()) {
            return new int[]{-1, 0};
        }

        int[] charCount = new int[256];
        int distinctChars = 0;
        int start = 0, maxStart = 0, maxLength = 0;

        for (int end = 0; end < input.length(); end++) {
            if (charCount[input.charAt(end)]++ == 0) {
                distinctChars++;
            }

            while (distinctChars > 2) {
                if (--charCount[input.charAt(start)] == 0) {
                    distinctChars--;
                }
                start++;
            }

            if (end - start + 1 > maxLength) {
                maxLength = end - start + 1;
                maxStart = start;
            }
        }

        return new int[]{maxStart, maxLength};
    }

    public static void main(String[] args) {
        initializeTestCases();
        runTests();
    }

    private static void initializeTestCases() {
        testCases.put("", new int[]{-1, 0});
        testCases.put("addddCdAA", new int[]{1, 4});
        testCases.put("111000111", new int[]{0, 3});
        testCases.put("abcdefg", new int[]{0, 1});
        testCases.put("aabbccddee", new int[]{0, 2});
        testCases.put("aaabbbcccdddeee", new int[]{0, 3});
        testCases.put("abcabcabc", new int[]{0, 3});
        testCases.put(generateLargeInput(), new int[]{0, 1000000});
    }

    private static String generateLargeInput() {
        return "a".repeat(1000000) + "b" + "a".repeat(1000000);
    }

    private static void runTests() {
        boolean allTestsPassed = true;
        long totalTime = 0;

        for (Map.Entry<String, int[]> testCase : testCases.entrySet()) {
            String input = testCase.getKey();
            int[] expected = testCase.getValue();

            long startTime = System.nanoTime();
            int[] result = getSubstringInfo(input);
            long endTime = System.nanoTime();

            boolean testPassed = Arrays.equals(result, expected);
            allTestsPassed &= testPassed;

            long executionTime = (endTime - startTime) / 1_000_000; // Convert to milliseconds
            totalTime += executionTime;

            System.out.printf("Input: %s\n", input.length() > 20 ? input.substring(0, 20) + "..." : input);
            System.out.printf("Expected: %s, Got: %s\n", Arrays.toString(expected), Arrays.toString(result));
            System.out.printf("Result: %s\n", testPassed ? "PASS" : "FAIL");
            System.out.printf("Execution time: %d ms\n\n", executionTime);
        }

        System.out.printf("Total execution time: %d ms\n", totalTime);
        System.out.println(allTestsPassed ? "All tests passed!" : "Some tests failed.");
    }
}
