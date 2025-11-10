package com.interview.notes.code.year.y2025.november.common.test3;

import java.util.AbstractMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RomanToInteger {                // Main class that holds the conversion logic and test code.

    // Static final map so it is created only once and reused for all conversions.
    private static final Map<Character, Integer> ROMAN_VALUE_MAP =    // Map to store Roman character and its integer value.
            Stream.of(                                               // Use Stream.of to create a stream of entries.
                    new AbstractMap.SimpleEntry<>('I', 1),           // Mapping I -> 1.
                    new AbstractMap.SimpleEntry<>('V', 5),           // Mapping V -> 5.
                    new AbstractMap.SimpleEntry<>('X', 10),          // Mapping X -> 10.
                    new AbstractMap.SimpleEntry<>('L', 50),          // Mapping L -> 50.
                    new AbstractMap.SimpleEntry<>('C', 100),         // Mapping C -> 100.
                    new AbstractMap.SimpleEntry<>('D', 500),         // Mapping D -> 500.
                    new AbstractMap.SimpleEntry<>('M', 1000)         // Mapping M -> 1000.
            ).collect(Collectors.toMap(                               // Collect the stream into a Map.
                    Map.Entry::getKey,                               // Use the entry key (Roman character) as the map key.
                    Map.Entry::getValue                              // Use the entry value (integer) as the map value.
            ));                                                       // End of map creation.

    public static void main(String[] args) {                         // Main method to run and validate our test cases.

        // Test 1: simple additive case.
        String input1 = "III";                                       // Roman numeral III which means 3.
        int expected1 = 3;                                           // Expected integer value for "III".
        runSingleTest("Test 1", input1, expected1);                  // Run Test 1 and print PASS/FAIL.

        // Test 2: mixed symbols without subtraction patterns beyond standard.
        String input2 = "LVIII";                                     // Roman numeral LVIII which means 58.
        int expected2 = 58;                                          // Expected integer value for "LVIII".
        runSingleTest("Test 2", input2, expected2);                  // Run Test 2 and print PASS/FAIL.

        // Test 3: typical example with multiple subtraction patterns.
        String input3 = "MCMXCIV";                                   // Roman numeral MCMXCIV which means 1994.
        int expected3 = 1994;                                        // Expected integer value for "MCMXCIV".
        runSingleTest("Test 3", input3, expected3);                  // Run Test 3 and print PASS/FAIL.

        // Test 4: another example with subtraction pattern (XC + IX).
        String input4 = "XCIX";                                      // Roman numeral XCIX which means 99.
        int expected4 = 99;                                          // Expected integer value for "XCIX".
        runSingleTest("Test 4", input4, expected4);                  // Run Test 4 and print PASS/FAIL.

        // Edge case: empty string or null should safely return 0.
        String input5 = "";                                          // Empty Roman numeral.
        int expected5 = 0;                                           // We treat empty as 0 for safety.
        runSingleTest("Test 5 (empty)", input5, expected5);          // Run Test 5 and print PASS/FAIL.

        // Edge case: single symbol.
        String input6 = "M";                                         // Single Roman numeral M.
        int expected6 = 1000;                                       // Expected value for "M".
        runSingleTest("Test 6 (single symbol)", input6, expected6);  // Run Test 6 and print PASS/FAIL.

        // Large data style test: long Roman string, repeated many times.
        String longInput = "MMMDCCCLXXXVIII";                        // 3888, near the typical Roman upper bound.
        int expectedLong = 3888;                                     // Expected result for the long Roman numeral.
        int repeatCount = 100000;                                   // Number of times we will convert it to simulate heavy load.

        System.out.println("---------- Large Data Test ----------"); // Separator for readability.
        boolean largeTestPassed = runLargeDataTest(longInput, expectedLong, repeatCount); // Run large data test.
        System.out.println("Large Data Test Result: "               // Print result of large data test.
                + (largeTestPassed ? "PASS" : "FAIL"));             // Show PASS if all runs were correct, else FAIL.
        System.out.println("---------- End of Tests ----------");   // Final separator to mark end of all tests.
    }                                                                // End of main method.

    private static void runSingleTest(String testName,               // Helper method to run a single test case.
                                      String input,                  // The Roman numeral input for this test.
                                      int expected) {                // The expected integer value for this test.
        System.out.println("[" + testName + "]");                    // Print the test name to identify the case.
        System.out.println("Input: " + input);                       // Show the Roman numeral being tested.
        System.out.println("Expected: " + expected);                 // Show the expected integer result.

        int actual = romanToInt(input);                              // Call conversion method to compute actual result.
        System.out.println("Output: " + actual);                     // Print the actual computed value.

        if (actual == expected) {                                    // Compare expected and actual values.
            System.out.println("Result: PASS");                      // Print PASS when they match.
        } else {                                                     // If they do not match:
            System.out.println("Result: FAIL");                      // Print FAIL to indicate a problem.
        }
        System.out.println();                                        // Print a blank line for better readability between tests.
    }                                                                // End of runSingleTest method.

    private static boolean runLargeDataTest(String input,            // Method to stress test conversion under repeated use.
                                            int expected,            // Expected result for each single conversion.
                                            int repeatCount) {       // Number of iterations to run the conversion.
        boolean allCorrect = true;                                   // Flag to track if all repeated conversions were correct.
        int lastResult = 0;                                          // Variable to keep the last conversion result for logging.

        for (int i = 0; i < repeatCount; i++) {                      // Loop repeatCount times to simulate heavy usage.
            int current = romanToInt(input);                         // Convert the Roman numeral in each iteration.
            lastResult = current;                                    // Store the last result for later printing.
            if (current != expected) {                               // If any result does not match the expected value:
                allCorrect = false;                                  // Mark flag as false, meaning test failed.
                break;                                               // Break early because we already found a problem.
            }
        }

        System.out.println("Large Data Test Input: " + input);       // Print the input used in the large data test.
        System.out.println("Expected per run: " + expected);         // Print expected value per run.
        System.out.println("Last computed result: " + lastResult);   // Print the last computed result for transparency.
        System.out.println("Total iterations run: " +                // Print how many times we ran the conversion.
                (allCorrect ? repeatCount : "stopped early due to mismatch")); // If mismatch, mention early stop.
        return allCorrect;                                           // Return whether all runs produced the expected value.
    }                                                                // End of runLargeDataTest method.

    public static int romanToInt(String s) {                         // Core method that converts a Roman numeral string to an integer.
        if (s == null || s.isEmpty()) {                              // Check if input is null or empty to avoid errors.
            return 0;                                                // Return 0 for null or empty as a safe default.
        }

        int total = 0;                                               // Variable to keep the running total of the integer value.
        int length = s.length();                                     // Cache the string length to avoid repeated calls to length().

        for (int i = 0; i < length; i++) {                           // Loop through each character in the string from left to right.
            char currentChar = s.charAt(i);                          // Get the current Roman character at position i.
            int currentValue = ROMAN_VALUE_MAP.get(currentChar);     // Look up its integer value from the map.

            int nextValue = 0;                                       // Default next value as 0 in case there is no next character.
            if (i + 1 < length) {                                    // Check if there is a next character in the string.
                char nextChar = s.charAt(i + 1);                     // Get the next Roman character.
                nextValue = ROMAN_VALUE_MAP.get(nextChar);           // Look up the integer value of the next character.
            }

            if (currentValue < nextValue) {                          // If the current value is less than the next value:
                total -= currentValue;                               // Subtract current value (handles cases like IV, IX, etc.).
            } else {                                                 // Otherwise (current value >= next value):
                total += currentValue;                               // Add current value to the total (normal additive rule).
            }
        }

        return total;                                                // After the loop, total holds the full integer value.
    }                                                                // End of romanToInt method.
}                                                                    // End of RomanToInteger class.
