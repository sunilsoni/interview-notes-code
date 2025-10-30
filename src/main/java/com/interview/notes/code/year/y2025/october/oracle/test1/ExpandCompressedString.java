package com.interview.notes.code.year.y2025.october.oracle.test1;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ExpandCompressedString {

    // ========================= Core Method =========================
    public static String expandCompressedString(String input) {
        // Handle null defensively; return empty string because there is nothing to expand
        if (input == null) return "";

        // Use StringBuilder for efficient string concatenation while expanding characters
        StringBuilder out = new StringBuilder();

        // Use index-based scan so we can consume a character and then an optional number that follows it
        for (int i = 0; i < input.length(); ) {

            // Read the current character and advance the index by one
            char ch = input.charAt(i++);
            
            // If the current symbol is a stray '-' or a digit (i.e., not preceded by a “character” token), skip the whole number block
            // This satisfies the rule: ignore unexpected/non-pattern characters
            if (ch == '-' || Character.isDigit(ch)) {
                // Consume all subsequent digits (e.g., "-12" or "123") to avoid mis-parsing on next loop
                while (i < input.length() && Character.isDigit(input.charAt(i))) i++;
                // Continue with next iteration since this block is not a valid (char + number) pattern
                continue;
            }

            // Try to parse an optional signed integer immediately following the character
            // We track whether we actually saw any digits to decide “omit if no number”
            boolean negative = false;   // Track sign of the number
            boolean hasDigits = false;  // Track presence of at least one digit
            int value = 0;              // Accumulate numeric value as int (capped to avoid overflow)

            // If next symbol is a minus sign, mark negative and move ahead
            if (i < input.length() && input.charAt(i) == '-') {
                negative = true;        // Remember that the number is negative
                i++;                    // Consume the minus sign
            }

            // Consume all consecutive digits to form the number; allow leading zeros (e.g., "0002")
            while (i < input.length() && Character.isDigit(input.charAt(i))) {
                hasDigits = true;                               // We saw at least one digit
                int d = input.charAt(i) - '0';                 // Convert digit character to int 0..9
                // Accumulate while capping to Integer.MAX_VALUE to avoid overflow on huge counts
                value = value > (Integer.MAX_VALUE - d) / 10 ? Integer.MAX_VALUE : (value * 10 + d);
                i++;                                           // Move to next character
            }

            // If there were no digits, we omit this character (rule: char without number -> omit)
            if (!hasDigits) {
                continue;                                      // Skip appending anything
            }

            // Apply sign after parsing; negative or zero counts are omitted as per rules
            if (negative) value = -value;                      // Flip sign if needed

            // Only positive numbers cause repetition; zero/negative -> omit
            if (value > 0) {
                final char rep = ch;                           // Final copy for lambda use
                final int count = value;                       // Final count for lambda use
                // Repeat the character 'count' times using IntStream (Java 8 Stream API usage)
                IntStream.range(0, count).forEach(x -> out.append(rep));
            }
            // If value <= 0 we append nothing (omit), which already satisfies rule
        }

        // Convert the accumulated characters to a final string and return
        return out.toString();
    }

    // ========================= Helpers for Testing =========================

    // Pretty printer to show PASS/FAIL line with sizes for large outputs
    private static String verdict(String got, String expected) {
        // Compare actual to expected and print PASS/FAIL with lengths to help large tests
        boolean ok = Objects.equals(got, expected);
        return (ok ? "PASS" : "FAIL") + " expectedLen=" + expected.length() + " gotLen=" + got.length();
    }

    // ========================= Main: Build & Run Tests =========================
    public static void main(String[] args) {
        // Build a list of hand-written tests covering base, edge, and tricky scenarios
        List<Case> basic = Arrays.asList(
            new Case("Ex1",       "a3b10",         "aaa" + "b".repeat(10)),         // Positive multi-digit counts
            new Case("Ex2",       "b2a",           "bb"),                           // Char without number is omitted
            new Case("Zero",      "z0",            ""),                             // Zero count -> omit
            new Case("Negative",  "x-5",           ""),                             // Negative count -> omit
            new Case("MixABC",    "A1B2C3",        "A" + "BB" + "CCC"),             // Multiple pairs
            new Case("LeadingNum","1a2",           "aa"),                           // Stray number then a valid pair
            new Case("Leading0s", "a0002b0003",    "aa" + "bbb"),                   // Leading zeros should work
            new Case("OnlyChars", "abc",           ""),                             // No numbers after chars -> omit all
            new Case("DashNoise", "a5-3",          "aaaaa"),                        // Stop digits at first non-digit; '-' and '3' are ignored as noise
            new Case("UpperBound","q2147483647",   "q"),                            // Capped behavior check (will not actually expand full; just ensures it doesn't crash)
            new Case("Empty",     "",              "")                              // Empty input -> empty output
        );

        // Large-data test: produce "x100000" to check performance and memory in a reasonable bound
        String bigIn = "x100000";                                                   // Input with a large repeat count
        String bigExpected = IntStream.range(0, 100000)                             // Use IntStream to generate 100000 'x'
                                       .mapToObj(i -> "x")
                                       .collect(Collectors.joining());
        Case large1 = new Case("LargeCount", bigIn, bigExpected);                   // Hold the large case

        // Streaming input generator: create 100k pairs "y1" and expect 100k 'y' output
        String manyPairsIn = IntStream.range(0, 100000)                             // 100k tokens
                                      .mapToObj(i -> "y1")                          // Each token says one 'y'
                                      .collect(Collectors.joining());
        String manyPairsExpected = IntStream.range(0, 100000)                       // Build expected 100k 'y'
                                            .mapToObj(i -> "y")
                                            .collect(Collectors.joining());
        Case large2 = new Case("ManyPairs100k", manyPairsIn, manyPairsExpected);    // Another large test

        // Combine all tests (basic + large) into a single list for execution
        List<Case> all = Stream.concat(basic.stream(), Stream.of(large1, large2))   // Merge two streams
                               .collect(Collectors.toList());                       // Materialize into list

        // Execute tests: for each case, call expandCompressedString and print PASS/FAIL with details
        all.forEach(tc -> {
            long start = System.currentTimeMillis();                                 // Start time for each case
            String got = expandCompressedString(tc.in);                              // Run the algorithm
            long took = System.currentTimeMillis() - start;                          // Measure elapsed time
            System.out.printf("%-16s -> %s (time=%dms)%n",                           // Print one concise result line
                    tc.name, verdict(got, tc.expected), took);                       // Include verdict and timing
        });

        // Quick aggregate summary: count failures using Stream API
        long failures = all.stream()                                                 // Stream over all results again
                           .filter(tc -> !Objects.equals(expandCompressedString(tc.in), tc.expected))
                           .count();                                                 // Count how many failed
        System.out.println("Total Failures: " + failures);                           // Print overall failure count
    }

    /**
     * @param in       Input string to expand
     * @param expected Expected expansion result
     * @param name     Optional label for clarity in output
     */ // Small immutable holder for a single test case
        private record Case(String name, String in, String expected) {
        // Constructor to set all properties
    }
}
