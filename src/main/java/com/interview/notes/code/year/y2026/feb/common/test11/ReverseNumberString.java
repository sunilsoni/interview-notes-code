package com.interview.notes.code.year.y2026.feb.common.test11;

public class ReverseNumberString {

    // Method to reverse given string
    static String reverse(String input) {

        // Convert string into stream of characters
        return input.chars()                     // Convert to IntStream of character ASCII values
                .mapToObj(c -> (char) c)        // Convert each int to Character
                .collect(StringBuilder::new,     // Create new StringBuilder to collect result
                        (sb, ch) -> sb.insert(0, ch), // Insert each char at position 0 to reverse
                        StringBuilder::append)   // Combine (used in parallel streams)
                .toString();                     // Convert StringBuilder to final String
    }

    public static void main(String[] args) {

        // Test cases
        String[][] tests = {
                {"123456.789", "987654.321"},
                {"100.001", "100.001"},
                {"123", "321"},
                {".123", "321."},
                {"", ""}
        };

        // Process each test case
        for (String[] t : tests) {

            String input = t[0];        // Read input
            String expected = t[1];     // Expected result
            String actual = reverse(input); // Call reverse method

            // Print PASS or FAIL
            System.out.println(
                    actual.equals(expected)
                            ? "PASS: " + input + " -> " + actual
                            : "FAIL: " + input + " -> " + actual + " (Expected: " + expected + ")"
            );
        }

        // Large input test
        String large = "1234567890".repeat(10000) + ".123";
        String result = reverse(large);

        System.out.println("Large Input Test: " +
                (result.length() == large.length() ? "PASS" : "FAIL"));
    }
}