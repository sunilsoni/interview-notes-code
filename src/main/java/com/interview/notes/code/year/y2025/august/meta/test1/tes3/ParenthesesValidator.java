package com.interview.notes.code.year.y2025.august.meta.test1.tes3;

import java.util.stream.IntStream;

public class ParenthesesValidator {

    // Part A: Check if string is valid
    public static boolean isValid(String input) {
        // Counter to track balance of brackets
        int balance = 0;

        // Iterate through each character in the input string
        for (char c : input.toCharArray()) {
            // For opening bracket, increment balance
            if (c == '<') {
                balance++;
            }
            // For closing bracket, decrement balance
            else if (c == '>') {
                balance--;
                // If balance becomes negative, we have more closing than opening
                if (balance < 0) {
                    return false;
                }
            }
        }
        // String is valid only if final balance is 0
        return balance == 0;
    }

    // Part B: Calculate minimum additions needed
    public static int minAdditions(String input) {
        // Track opening and closing brackets needed
        int openCount = 0;
        int closeCount = 0;

        // Process each character in input
        for (char c : input.toCharArray()) {
            if (c == '<') {
                // Each opening bracket needs a closing one
                closeCount++;
            } else if (c == '>') {
                closeCount--;
                // If closeCount becomes negative, we need an opening bracket
                if (closeCount < 0) {
                    openCount++;
                    closeCount = 0;
                }
            }
        }
        // Total additions needed is sum of remaining required brackets
        return openCount + closeCount;
    }

    public static void main(String[] args) {
        // Test cases array containing input and expected results
        String[][] testCases = {
                {"<>", "true", "0"},
                {"<><>", "true", "0"},
                {"<<>>>", "true", "0"},
                {"<<>", "false", "1"},
                {"<<<", "false", "3"},
                {">", "false", "1"},
                {"", "true", "0"},
                // Large input test case
                {"<".repeat(10000) + ">".repeat(10000), "true", "0"}
        };

        // Process each test case using streams
        IntStream.range(0, testCases.length)
                .forEach(i -> {
                    String input = testCases[i][0];
                    boolean expectedValid = Boolean.parseBoolean(testCases[i][1]);
                    int expectedAdditions = Integer.parseInt(testCases[i][2]);

                    // Test Part A
                    boolean actualValid = isValid(input);
                    System.out.printf("Test Case %d (Part A) - Input: %s%n",
                            i + 1,
                            input.length() > 20 ? input.substring(0, 20) + "..." : input);
                    System.out.printf("Expected: %b, Actual: %b - %s%n",
                            expectedValid,
                            actualValid,
                            expectedValid == actualValid ? "PASS" : "FAIL");

                    // Test Part B
                    int actualAdditions = minAdditions(input);
                    System.out.printf("Test Case %d (Part B) - Additions needed%n", i + 1);
                    System.out.printf("Expected: %d, Actual: %d - %s%n%n",
                            expectedAdditions,
                            actualAdditions,
                            expectedAdditions == actualAdditions ? "PASS" : "FAIL");
                });
    }
}
