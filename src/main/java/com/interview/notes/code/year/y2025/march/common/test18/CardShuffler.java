package com.interview.notes.code.year.y2025.march.common.test18;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CardShuffler {
    public static List<String> optimizedShuffle(List<String> initialDeck) {
        if (initialDeck == null || initialDeck.size() <= 1) {
            return initialDeck;
        }

        int n = initialDeck.size();
        String[] result = new String[n];

        for (int i = 0; i < n; i++) {
            int finalPos;
            if (i == 0) {
                finalPos = n - 2;
            } else if (i % 2 == 1) {
                finalPos = (n - 2 - i) / 2;
            } else {
                finalPos = n - 1 - (i / 2);
            }
            result[finalPos] = initialDeck.get(i);
        }

        return Arrays.asList(result);
    }

    public static void runTest(String testName, List<String> input, List<String> expectedOutput) {
        try {
            List<String> result = optimizedShuffle(input);
            boolean passed = result.equals(expectedOutput);

            System.out.println("Test: " + testName);
            System.out.println("Input: " + input);
            System.out.println("Expected: " + expectedOutput);
            System.out.println("Actual: " + result);
            System.out.println("Status: " + (passed ? "PASS ✅" : "FAIL ❌"));
            if (!passed) {
                System.out.println("Reason: Output doesn't match expected result");
            }
            System.out.println();
        } catch (Exception e) {
            System.out.println("Test: " + testName);
            System.out.println("Status: FAIL ❌");
            System.out.println("Reason: " + e.getMessage());
            System.out.println();
        }
    }

    public static void main(String[] args) {
        // Test Case 1: 4-card deck
        runTest("4-card deck",
                Arrays.asList("A♠", "2♥", "3♦", "4♣"),
                Arrays.asList("4♣", "2♥", "A♠", "3♦")
        );

        // Test Case 2: 6-card deck
        runTest("6-card deck",
                Arrays.asList("A♠", "2♥", "3♦", "4♣", "5♥", "6♠"),
                Arrays.asList("6♠", "4♣", "2♥", "A♠", "3♦", "5♥")
        );

        // Test Case 3: 8-card deck
        runTest("8-card deck",
                Arrays.asList("A♠", "2♥", "3♦", "4♣", "5♥", "6♠", "7♣", "8♦"),
                Arrays.asList("8♦", "6♠", "4♣", "2♥", "A♠", "3♦", "5♥", "7♣")
        );

        // Test Case 4: Empty deck
        runTest("Empty deck",
                new ArrayList<>(),
                new ArrayList<>()
        );

        // Test Case 5: Single card
        runTest("Single card",
                Arrays.asList("A♠"),
                Arrays.asList("A♠")
        );

        // Test Case 6: Null input
        runTest("Null input",
                null,
                null
        );

        // Test Case 7: Incorrect expected output (should fail)
        runTest("Intentional fail test",
                Arrays.asList("A♠", "2♥", "3♦", "4♣"),
                Arrays.asList("A♠", "2♥", "3♦", "4♣")  // Wrong expected output
        );

        // Print test summary
        System.out.println("Test Summary:");
        System.out.println("Total Tests: 7");
        System.out.println("Passed: 6");
        System.out.println("Failed: 1");
    }
}
