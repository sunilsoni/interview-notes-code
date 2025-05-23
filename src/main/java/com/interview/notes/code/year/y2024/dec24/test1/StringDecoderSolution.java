package com.interview.notes.code.year.y2024.dec24.test1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class StringDecoderSolution {

    public static void main(String[] args) {
        // Test cases runner
        runTestCases();

        // Additional large input test
        testLargeInput();
    }

    private static List<Integer> frequency(String s) {
        List<Integer> result = new ArrayList<>(Collections.nCopies(26, 0));
        int i = 0;

        while (i < s.length()) {
            int count = 1;
            int start = i;

            // Check if next characters form a number followed by #
            if (i + 2 < s.length() && s.charAt(i + 2) == '#') {
                int value = Integer.parseInt(s.substring(i, i + 2)) - 1;
                i += 3;

                // Handle repetition count if present
                if (i < s.length() && s.charAt(i) == '(') {
                    int closeIdx = s.indexOf(')', i);
                    count = Integer.parseInt(s.substring(i + 1, closeIdx));
                    i = closeIdx + 1;
                }
                result.set(value, result.get(value) + count);
                continue;
            }

            // Handle single digit encoding
            int value = s.charAt(i) - '1';
            i++;

            // Handle repetition count if present
            if (i < s.length() && s.charAt(i) == '(') {
                int closeIdx = s.indexOf(')', i);
                count = Integer.parseInt(s.substring(i + 1, closeIdx));
                i = closeIdx + 1;
            }

            result.set(value, result.get(value) + count);
        }

        return result;
    }

    private static void runTestCases() {
        // Test case 1
        testCase("1226#24#",
                Arrays.asList(1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0),
                "Test Case 1");

        // Test case 2
        testCase("1(2)23(3)",
                Arrays.asList(2, 1, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
                "Test Case 2");

        // Test case 3
        testCase("2110#(2)",
                Arrays.asList(1, 1, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
                "Test Case 3");

        // Test case 4
        testCase("23#(2)24#25#26#23#(3)",
                Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 1, 1, 1),
                "Test Case 4");
    }

    private static void testLargeInput() {
        // Generate large input string
        StringBuilder largeInput = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            largeInput.append("1(100)23#(50)");
        }

        long startTime = System.currentTimeMillis();
        List<Integer> result = frequency(largeInput.toString());
        long endTime = System.currentTimeMillis();

        System.out.println("\nLarge Input Test:");
        System.out.println("Input length: " + largeInput.length());
        System.out.println("Processing time: " + (endTime - startTime) + "ms");
        System.out.println("Result valid: " + (result.get(0) == 100000 && result.get(22) == 50000));
    }

    private static void testCase(String input, List<Integer> expected, String testName) {
        List<Integer> result = frequency(input);
        boolean passed = result.equals(expected);

        System.out.println(testName + ": " + (passed ? "PASS" : "FAIL"));
        if (!passed) {
            System.out.println("Expected: " + expected);
            System.out.println("Got: " + result);
        }
    }
}
