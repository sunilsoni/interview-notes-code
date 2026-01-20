package com.interview.notes.code.year.y2026.jan.common.test5;

import java.util.Map;
import java.util.stream.Collectors;

public class LetterStats {

    public static void main(String[] args) {
        // --- Test Case 1: The Problem Statement ---
        String input = "it is red, it isn't red - I don’t see anything red but I see 1 or 2 blue";
        // Expected: 'e' appears 8 times. 'o', 'a', 'y', 'h', 'g', 'l' appear 1 time.
        // We accept 'l' (or any count-1 char) as min for this test check.
        test(input, 'e', 1);

        // --- Test Case 2: Edge Case (Case Sensitivity & Symbols) ---
        // 'A' and 'a' should sum to 5. 'b' is 1. Symbols ignored.
        test("AaA a!@# a b", 'a', 1);

        // --- Test Case 3: Large Data Input ---
        // Generates a string of 1 million 'z's and one 'x'.
        String largeInput = "z".repeat(1_000_000) + "x";
        test(largeInput, 'z', 1);

        System.out.println("All tests executed.");
    }

    // Main logic method to process the string
    static Result analyze(String text) {
        // Use var for type inference (Java 10+) to keep code short
        // Convert string to IntStream of char codes
        var counts = text.chars()
            // Map int codes to Character objects for processing
            .mapToObj(c -> (char) c)
            // Filter: Keep only English letters, drop numbers/symbols
            .filter(Character::isLetter)
            // Normalize: Convert all to lowercase to ignore case sensitivity
            .map(Character::toLowerCase)
            // Collect: Group by character and count occurrences into a Map
            .collect(Collectors.groupingBy(c -> c, Collectors.counting()));

        // Guard clause: Handle empty valid input (avoid crash)
        if (counts.isEmpty()) return new Result('?', 0, '?', 0);

        // Stream the map entries to find the entry with Max value
        var max = counts.entrySet().stream().max(Map.Entry.comparingByValue()).orElseThrow();

        // Stream the map entries to find the entry with Min value
        var min = counts.entrySet().stream().min(Map.Entry.comparingByValue()).orElseThrow();

        // Return result record
        return new Result(max.getKey(), max.getValue(), min.getKey(), min.getValue());
    }

    // Helper method to validate and print PASS/FAIL status
    static void test(String input, char expectedMax, long expectedMinCount) {
        // Run analysis
        var res = analyze(input);

        // Validation Logic: Check if calculated max matches expected max
        // AND calculated min count matches expected min count (handling ties for min letter)
        boolean passed = res.maxChar() == expectedMax && res.minCount() == expectedMinCount;

        // Ternary operator to determine status string
        String status = passed ? "PASS" : "FAIL";

        // Print detailed output for verification
        // formatted() is a clean Java 15+ way to format strings
        System.out.printf(
                "[%s] Input len: %d | Max: %s(%d) | Min: %s(%d)%n", status, input.length(), res.maxChar(), res.maxCount(), res.minChar(), res.minCount()
        );
    }

    // Record to hold result data concisely (Java 16+ feature standard in 21)
    // Minimizes boilerplate like getters/setters/constructors
    record Result(char maxChar, long maxCount, char minChar, long minCount) {}
}