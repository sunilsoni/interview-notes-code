package com.interview.notes.code.months.oct24.test7;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Solution {
    public static String solution(String number) {
        String result = number;
        boolean hasConsecutiveEquals = true;

        while (hasConsecutiveEquals) {
            StringBuilder newResult = new StringBuilder();
            int i = 0;
            hasConsecutiveEquals = false;

            while (i < result.length()) {
                char currentChar = result.charAt(i);
                int count = 1;

                while (i + 1 < result.length() && result.charAt(i + 1) == currentChar) {
                    count++;
                    i++;
                }

                if (count > 1) {
                    hasConsecutiveEquals = true;
                    newResult.append(String.valueOf(Character.getNumericValue(currentChar) * count));
                } else {
                    newResult.append(currentChar);
                }

                i++;
            }

            result = newResult.toString();
        }

        return result;
    }

    public static void main(String[] args) {
        runTests();
    }

    public static void runTests() {
        List<TestCase> testCases = new ArrayList<>();

        // Provided test cases
        testCases.add(new TestCase("66644319333", "26328"));
        testCases.add(new TestCase("0044886", "084"));
        testCases.add(new TestCase("429201", "429201"));

        // Additional test cases
        testCases.add(new TestCase("1", "1"));
        testCases.add(new TestCase("11", "2"));
        testCases.add(new TestCase("9999999999", "90"));
        testCases.add(new TestCase("123456789", "123456789"));

        // Large data case
        StringBuilder largeInput = new StringBuilder();
        for (int i = 0; i < 100; i++) {
            largeInput.append("9");
        }
        testCases.add(new TestCase(largeInput.toString(), "900"));

        // Run tests
        List<TestCase> failedTests = testCases.stream()
                .filter(testCase -> !testCase.runTest())
                .collect(Collectors.toList());

        // Print results
        System.out.println("Total test cases: " + testCases.size());
        System.out.println("Passed: " + (testCases.size() - failedTests.size()));
        System.out.println("Failed: " + failedTests.size());

        if (!failedTests.isEmpty()) {
            System.out.println("\nFailed test cases:");
            failedTests.forEach(TestCase::printFailure);
        }
    }

    static class TestCase {
        String input;
        String expectedOutput;

        TestCase(String input, String expectedOutput) {
            this.input = input;
            this.expectedOutput = expectedOutput;
        }

        boolean runTest() {
            String actualOutput = solution(input);
            return expectedOutput.equals(actualOutput);
        }

        void printFailure() {
            System.out.println("Input: " + input);
            System.out.println("Expected: " + expectedOutput);
            System.out.println("Actual: " + solution(input));
            System.out.println();
        }
    }
}
