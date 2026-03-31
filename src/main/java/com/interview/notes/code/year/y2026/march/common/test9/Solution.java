package com.interview.notes.code.year.y2026.march.common.test9;

import java.util.stream.Stream;

public class Solution {

    // Standard execution entry point to run the program without JUnit
    public static void main(String[] args) { // Main method signature as required by the JVM
        var solution = new Solution(); // Instantiate our class to access the non-static testing methods

        if (solution.doTestsPass()) { // Evaluate the result of our Stream-based test suite
            System.out.println("All tests passed"); // Output clear success criteria if true
        } else { // Fallback block if any single test in the stream fails
            System.out.println("Tests failed"); // Output failure state
        }
    }

    // Method to calculate the minimum characters required to encode the potion
    private int minimalSteps(String ingredients) {
        var n = ingredients.length(); // Get the length of the string to properly size our DP array
        var dp = new int[n + 1]; // Initialize an array where dp[i] holds the minimum steps to build a prefix of length i

        for (var i = 1; i <= n; i++) { // Loop through every possible prefix length starting from 1 up to n
            dp[i] = dp[i - 1] + 1; // Default fallback: the cost is just 1 more than the prefix before it (adding one char)

            if (i % 2 == 0) { // Check if the current prefix length is even, which is required for a perfect duplication
                var half = i / 2; // Calculate the exact midpoint index of the current prefix

                if (ingredients.substring(0, half).equals(ingredients.substring(half, i))) { // Check if the first half exactly matches the second half
                    dp[i] = Math.min(dp[i], dp[half] + 1); // If they match, take the minimum of the current cost vs using '*' (half cost + 1)
                }
            }
        }
        return dp[n]; // Return the final optimal cost calculated for the entire string length
    }

    // Method to execute tests and return an overall pass/fail boolean
    private boolean doTestsPass() {
        record TestCase(String input, int expected) {} // Use a Java 14+ record to define a concise data structure for our test parameters

        var tests = Stream.of( // Create a Java Stream of our test cases to cleanly process them in one pipeline
            new TestCase("ABCDABCE", 8), // Provided test case 1: no valid duplications available, so length equals characters typed
            new TestCase("ABCABCE", 5), // Provided test case 2: duplicates "ABC" into "ABCABC" then adds "E"
            new TestCase("ABABCABABCD", 6), // The complex 11-char sequence detailed in the problem description
            new TestCase("ABC".repeat(4), 5), // Large data scenario: repeating sequence "ABCABCABCABC" to test scaling logic
            new TestCase("A".repeat(16), 5) // Large data scenario: perfectly duplicatable sequence to ensure continuous halving works
        );

        return tests.allMatch(test -> { // Use Stream.allMatch to iterate and ensure every single test case evaluates to true
            var result = minimalSteps(test.input()); // Execute our core logic method against the current test string
            var passed = result == test.expected(); // Compare the actual result with our expected mathematical result

            if (!passed) { // If the test fails, we need to log it to understand what broke
                System.out.println("Failed: " + test.input() + " | Expected: " + test.expected() + ", Got: " + result); // Print the mismatch details
            }
            return passed; // Return the boolean outcome to the allMatch evaluator
        });
    }
}