package com.interview.notes.code.year.y2025.july.glider.test1;

public class FirstNonRepeatingCharacter {

    /**
     * Finds first non-repeating character in a string
     *
     * @param input String to process
     * @return First non-repeating character or null if none found
     */
    public static Character findFirstNonRepeating(String input) {
        // Step 1: Handle null or empty input
        if (input == null || input.isEmpty()) {
            return null;
        }

        // Step 2: Convert to lowercase for case-insensitive comparison
        input = input.toLowerCase();

        // Step 3: Create two arrays
        // - count: stores frequency of each character (256 for ASCII)
        // - firstIndex: stores first occurrence index of each character
        int[] count = new int[256];
        int[] firstIndex = new int[256];

        // Step 4: Initialize firstIndex array with -1
        for (int i = 0; i < 256; i++) {
            firstIndex[i] = -1;
        }

        // Step 5: Count characters and store first occurrence
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);

            // Increment count
            count[ch]++;

            // Store first occurrence index (only once)
            if (firstIndex[ch] == -1) {
                firstIndex[ch] = i;
            }
        }

        // Step 6: Find character with count 1 and minimum index
        int minIndex = Integer.MAX_VALUE;
        Character result = null;

        for (int i = 0; i < 256; i++) {
            // If character appears exactly once
            if (count[i] == 1 && firstIndex[i] != -1) {
                // Update result if this character appears earlier
                if (firstIndex[i] < minIndex) {
                    minIndex = firstIndex[i];
                    result = (char) i;
                }
            }
        }

        return result;
    }

    /**
     * Main method with test cases
     */
    public static void main(String[] args) {
        // Test case class to hold input and expected output
        class TestCase {
            String input;
            Character expected;
            String description;

            TestCase(String input, Character expected, String description) {
                this.input = input;
                this.expected = expected;
                this.description = description;
            }
        }

        // Array of test cases
        TestCase[] testCases = new TestCase[]{
                new TestCase("swiss", 'w', "Basic case"),
                new TestCase("aabbcc", null, "No non-repeating character"),
                new TestCase("", null, "Empty string"),
                new TestCase(null, null, "Null input"),
                new TestCase("aA", 'a', "Case insensitive"),
                new TestCase("stream", 's', "First character non-repeating"),
                new TestCase("zZ", 'z', "Case insensitive z"),
                // Large input test
                new TestCase(createLargeInput(), 'x', "Large input")
        };

        // Run all test cases
        int passed = 0;
        int total = testCases.length;

        for (TestCase test : testCases) {
            // Get actual result
            Character result = findFirstNonRepeating(test.input);

            // Compare with expected
            boolean isPassed = (result == null && test.expected == null) ||
                    (result != null && result.equals(test.expected));

            // Print test results
            System.out.println("Test: " + test.description);
            System.out.println("Input: " +
                    (test.input == null ? "null" :
                            test.input.length() > 20 ? test.input.substring(0, 20) + "..." :
                                    test.input));
            System.out.println("Expected: " + test.expected);
            System.out.println("Got: " + result);
            System.out.println("Status: " + (isPassed ? "PASS" : "FAIL"));
            System.out.println("------------------------");

            if (isPassed) passed++;
        }

        // Print summary
        System.out.println("\nTest Summary:");
        System.out.println("Total Tests: " + total);
        System.out.println("Passed: " + passed);
        System.out.println("Failed: " + (total - passed));
    }

    /**
     * Helper method to create large input string for testing
     */
    private static String createLargeInput() {
        // Create a string with 10000 'a's and one 'x'
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10000; i++) {
            sb.append('a');
        }
        sb.append('x');
        return sb.toString();
    }
}
