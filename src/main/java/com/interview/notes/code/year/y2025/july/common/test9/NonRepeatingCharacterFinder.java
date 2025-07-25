package com.interview.notes.code.year.y2025.july.common.test9;

public class NonRepeatingCharacterFinder {

    // Main method to find first non-repeating character in a string using Java 8 Stream API
    public static Character findFirstNonRepeatingChar(String input) {
        // Handle null input case
        if (input == null || input.isEmpty()) {
            return null;
        }

        // Convert string to character array and use Stream API to process
        return input.chars()
                // Map each character to its frequency in the string
                .mapToObj(ch -> (char) ch)
                // Filter characters that appear only once
                .filter(ch -> input.chars().filter(c -> c == ch).count() == 1)
                // Get the first such character or null if none exists
                .findFirst()
                .orElse(null);
    }

    public static void main(String[] args) {
        // Test cases array containing input and expected output pairs
        Object[][] testCases = {
                {"aabbccd", 'd'},          // Basic case with one non-repeating char
                {"aabbcc", null},          // All chars repeat
                {"", null},                // Empty string
                {null, null},              // Null input
                {"abcdef", 'a'},           // All unique chars
                {"aaaaaa", null},          // All same chars
                {"abcabc", null},          // All chars repeat in pairs
                // Large input test case
                {new String(new char[100000]).replace("\0", "a") + "b", 'b'}
        };

        // Process each test case
        for (int i = 0; i < testCases.length; i++) {
            String input = (String) testCases[i][0];
            Character expected = (Character) testCases[i][1];
            Character result = findFirstNonRepeatingChar(input);

            // Compare result with expected output
            boolean passed = (result == null && expected == null) ||
                    (result != null && result.equals(expected));

            // Print test results
            System.out.printf("Test Case %d: %s\n", i + 1, passed ? "PASS" : "FAIL");
            System.out.printf("Input: %s\n", input == null ? "null" :
                    input.length() > 50 ? input.substring(0, 47) + "..." : input);
            System.out.printf("Expected: %s\n", expected);
            System.out.printf("Result: %s\n\n", result);
        }
    }
}
