package com.interview.notes.code.year.y2025.october.oracle.test1;

import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CompressedStringExpander {

    /**
     * Expands a compressed string following the specified rules
     *
     * @param input the compressed string to expand
     * @return the expanded string
     */
    public static String expandCompressedString(String input) {
        // Handle null or empty input - return empty string for safety
        if (input == null || input.isEmpty()) {
            return "";
        }

        // Create regex pattern to match character followed by optional number
        // Pattern explanation: ([a-zA-Z0-9]) captures any alphanumeric character
        // (-?\d*) captures optional minus sign followed by zero or more digits
        Pattern pattern = Pattern.compile("([a-zA-Z0-9])(-?\\d*)");

        // Use Stream API to process all matches and expand them
        return pattern.matcher(input)
                .results() // Convert matcher to stream of MatchResult objects
                .map(match -> {
                    // Extract the character from first capturing group
                    char character = match.group(1).charAt(0);
                    // Extract the number string from second capturing group
                    String numberStr = match.group(2);

                    // Determine repeat count based on the number string
                    int repeatCount;
                    if (numberStr.isEmpty()) {
                        // No number means repeat once (default behavior)
                        repeatCount = 1;
                    } else {
                        // Parse the number string to integer
                        int number = Integer.parseInt(numberStr);
                        // Only positive numbers result in repetition, others are omitted
                        repeatCount = Math.max(0, number);
                    }

                    // Generate repeated character string using IntStream
                    // IntStream.range creates sequence from 0 to repeatCount
                    // mapToObj converts each index to the character
                    // collect joins all characters into a single string
                    return IntStream.range(0, repeatCount)
                            .mapToObj(i -> String.valueOf(character))
                            .collect(Collectors.joining());
                })
                .collect(Collectors.joining()); // Join all expanded parts into final result
    }

    /**
     * Test method to verify the solution with various test cases
     */
    public static void main(String[] args) {
        // Test case counter for tracking results
        int testCount = 0;
        int passCount = 0;

        System.out.println("=== Compressed String Expander Tests ===\n");

        // Test Case 1: Basic positive number expansion
        testCount++;
        String test1Input = "a3b10";
        String test1Expected = "aaabbbbbbbbbb";
        String test1Result = expandCompressedString(test1Input);
        boolean test1Pass = test1Expected.equals(test1Result);
        if (test1Pass) passCount++;

        System.out.println("Test 1 - Basic expansion:");
        System.out.println("Input: \"" + test1Input + "\"");
        System.out.println("Expected: \"" + test1Expected + "\"");
        System.out.println("Got: \"" + test1Result + "\"");
        System.out.println("Result: " + (test1Pass ? "PASS" : "FAIL"));
        System.out.println();

        // Test Case 2: Character with no number (default to 1)
        testCount++;
        String test2Input = "b2a";
        String test2Expected = "bb";
        String test2Result = expandCompressedString(test2Input);
        boolean test2Pass = test2Expected.equals(test2Result);
        if (test2Pass) passCount++;

        System.out.println("Test 2 - Character without number:");
        System.out.println("Input: \"" + test2Input + "\"");
        System.out.println("Expected: \"" + test2Expected + "\"");
        System.out.println("Got: \"" + test2Result + "\"");
        System.out.println("Result: " + (test2Pass ? "PASS" : "FAIL"));
        System.out.println();

        // Test Case 3: Negative numbers (should be omitted)
        testCount++;
        String test3Input = "a-5b3c0d2";
        String test3Expected = "bbbdd";
        String test3Result = expandCompressedString(test3Input);
        boolean test3Pass = test3Expected.equals(test3Result);
        if (test3Pass) passCount++;

        System.out.println("Test 3 - Negative and zero numbers:");
        System.out.println("Input: \"" + test3Input + "\"");
        System.out.println("Expected: \"" + test3Expected + "\"");
        System.out.println("Got: \"" + test3Result + "\"");
        System.out.println("Result: " + (test3Pass ? "PASS" : "FAIL"));
        System.out.println();

        // Test Case 4: Empty string
        testCount++;
        String test4Input = "";
        String test4Expected = "";
        String test4Result = expandCompressedString(test4Input);
        boolean test4Pass = test4Expected.equals(test4Result);
        if (test4Pass) passCount++;

        System.out.println("Test 4 - Empty string:");
        System.out.println("Input: \"" + test4Input + "\"");
        System.out.println("Expected: \"" + test4Expected + "\"");
        System.out.println("Got: \"" + test4Result + "\"");
        System.out.println("Result: " + (test4Pass ? "PASS" : "FAIL"));
        System.out.println();

        // Test Case 5: Single character with large number
        testCount++;
        String test5Input = "x1000";
        String test5Expected = "x".repeat(1000); // Create string with 1000 x's
        String test5Result = expandCompressedString(test5Input);
        boolean test5Pass = test5Expected.equals(test5Result);
        if (test5Pass) passCount++;

        System.out.println("Test 5 - Large number expansion:");
        System.out.println("Input: \"" + test5Input + "\"");
        System.out.println("Expected length: " + test5Expected.length());
        System.out.println("Got length: " + test5Result.length());
        System.out.println("Result: " + (test5Pass ? "PASS" : "FAIL"));
        System.out.println();

        // Test Case 6: Mixed alphanumeric characters
        testCount++;
        String test6Input = "a2b1c3d0e-1f5";
        String test6Expected = "aabcccfffff";
        String test6Result = expandCompressedString(test6Input);
        boolean test6Pass = test6Expected.equals(test6Result);
        if (test6Pass) passCount++;

        System.out.println("Test 6 - Mixed scenarios:");
        System.out.println("Input: \"" + test6Input + "\"");
        System.out.println("Expected: \"" + test6Expected + "\"");
        System.out.println("Got: \"" + test6Result + "\"");
        System.out.println("Result: " + (test6Pass ? "PASS" : "FAIL"));
        System.out.println();

        // Test Case 7: Numbers as characters
        testCount++;
        String test7Input = "12a33b2";
        String test7Expected = "1aaaaabb";
        String test7Result = expandCompressedString(test7Input);
        boolean test7Pass = test7Expected.equals(test7Result);
        if (test7Pass) passCount++;

        System.out.println("Test 7 - Numeric characters:");
        System.out.println("Input: \"" + test7Input + "\"");
        System.out.println("Expected: \"" + test7Expected + "\"");
        System.out.println("Got: \"" + test7Result + "\"");
        System.out.println("Result: " + (test7Pass ? "PASS" : "FAIL"));
        System.out.println();

        // Test Case 8: Large data input with performance test
        testCount++;
        StringBuilder largeInputBuilder = new StringBuilder();
        StringBuilder largeExpectedBuilder = new StringBuilder();

        // Create large input: a100b200c300...z2600 (26 letters with increasing counts)
        for (int i = 0; i < 26; i++) {
            char letter = (char) ('a' + i);
            int count = (i + 1) * 100;
            largeInputBuilder.append(letter).append(count);
            // Build expected result
            for (int j = 0; j < count; j++) {
                largeExpectedBuilder.append(letter);
            }
        }

        String test8Input = largeInputBuilder.toString();
        String test8Expected = largeExpectedBuilder.toString();

        // Measure performance for large data
        long startTime = System.currentTimeMillis();
        String test8Result = expandCompressedString(test8Input);
        long endTime = System.currentTimeMillis();

        boolean test8Pass = test8Expected.equals(test8Result);
        if (test8Pass) passCount++;

        System.out.println("Test 8 - Large data performance:");
        System.out.println("Input length: " + test8Input.length());
        System.out.println("Expected output length: " + test8Expected.length());
        System.out.println("Got output length: " + test8Result.length());
        System.out.println("Processing time: " + (endTime - startTime) + " ms");
        System.out.println("Result: " + (test8Pass ? "PASS" : "FAIL"));
        System.out.println();

        // Test Case 9: Null input handling
        testCount++;
        String test9Input = null;
        String test9Expected = "";
        String test9Result = expandCompressedString(test9Input);
        boolean test9Pass = test9Expected.equals(test9Result);
        if (test9Pass) passCount++;

        System.out.println("Test 9 - Null input:");
        System.out.println("Input: null");
        System.out.println("Expected: \"" + test9Expected + "\"");
        System.out.println("Got: \"" + test9Result + "\"");
        System.out.println("Result: " + (test9Pass ? "PASS" : "FAIL"));
        System.out.println();

        // Final results summary
        System.out.println("=== Test Results Summary ===");
        System.out.println("Total tests: " + testCount);
        System.out.println("Passed: " + passCount);
        System.out.println("Failed: " + (testCount - passCount));
        System.out.println("Success rate: " + String.format("%.1f%%", (passCount * 100.0 / testCount)));

        // Overall result
        if (passCount == testCount) {
            System.out.println("\n🎉 ALL TESTS PASSED! 🎉");
        } else {
            System.out.println("\n❌ SOME TESTS FAILED ❌");
        }
    }
}
