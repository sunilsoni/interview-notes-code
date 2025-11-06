package com.interview.notes.code.year.y2025.november.common.test3;

public class FirstNonRepeatingCharStringOnly {

    // Method to find the first non-repeating character using String methods only
    public static Character findFirstUniqueChar(String input) {

        // Step 1: Handle null or empty string
        if (input == null || input.isEmpty())
            return null;

        // Step 2: Loop through each character in the string
        // We check if its first and last positions are the same
        // If so, that character appears only once
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i); // get current character

            // indexOf(ch) -> first position where character appears
            // lastIndexOf(ch) -> last position where character appears
            if (input.indexOf(ch) == input.lastIndexOf(ch)) {
                return ch; // found first non-repeating character
            }
        }

        // Step 3: If no such character found, return null
        return null;
    }

    // Main method for manual testing (no JUnit)
    public static void main(String[] args) {

        // Step 4: Define test cases
        String[] testInputs = {
                "swiss",          // expected: 'w'
                "success",        // expected: 'u'
                "aabbcc",         // expected: null
                "abacabad",       // expected: 'c'
                "",               // expected: null
                "a",              // expected: 'a'
                generateLargeInput(500_000) // large input test
        };

        Character[] expectedOutputs = {'w', 'u', null, 'c', null, 'a', null};

        // Step 5: Run each test case
        for (int i = 0; i < testInputs.length; i++) {
            long start = System.currentTimeMillis();
            Character result = findFirstUniqueChar(testInputs[i]);
            long end = System.currentTimeMillis();

            System.out.println("Test " + (i + 1) +
                    " | Input: " + summarize(testInputs[i]) +
                    " | Output: " + result +
                    " | Expected: " + expectedOutputs[i] +
                    " | Time: " + (end - start) + "ms" +
                    " | Result: " + (result == expectedOutputs[i] ? "PASS" : "FAIL"));
        }
    }

    // Utility method to summarize long strings for printing
    private static String summarize(String s) {
        if (s == null) return "null";
        if (s.length() > 20) return s.substring(0, 20) + "...(" + s.length() + ")";
        return s;
    }

    // Generates a large test input string
    private static String generateLargeInput(int size) {
        // Fill with repeating 'a's and one unique 'z' at the end
        StringBuilder sb = new StringBuilder(size);
        for (int i = 0; i < size - 1; i++) sb.append('a');
        sb.append('z');
        return sb.toString();
    }
}
