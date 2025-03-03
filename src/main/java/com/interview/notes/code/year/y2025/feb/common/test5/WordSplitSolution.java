package com.interview.notes.code.year.y2025.feb.common.test5;

public class WordSplitSolution {

    /**
     * Splits words of length >= 4 (counting only letters),
     * inserting a single space in the middle (if even),
     * or slightly off-center (if odd).
     */
    public static String splitWords(String input) {
        // Split by whitespace
        String[] tokens = input.split("\\s+");
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < tokens.length; i++) {
            String token = tokens[i];
            String transformed = transformToken(token);

            if (i > 0) {
                result.append(" ");
            }
            result.append(transformed);
        }

        return result.toString();
    }

    /**
     * Transforms a single token if it has >= 4 letters,
     * splitting it around the correct letter index.
     */
    private static String transformToken(String token) {
        // Count letters to decide if we need splitting
        int letterCount = 0;
        for (char c : token.toCharArray()) {
            if (Character.isLetter(c)) {
                letterCount++;
            }
        }

        // If not enough letters, no split
        if (letterCount < 4) {
            return token;
        }

        // Figure out where to split among the letters
        int splitIndex;
        if (letterCount % 2 == 0) {
            splitIndex = letterCount / 2;
        } else {
            splitIndex = (letterCount + 1) / 2;
        }

        // Build the transformed token
        StringBuilder sb = new StringBuilder();
        int lettersSeen = 0;
        for (int i = 0; i < token.length(); i++) {
            char c = token.charAt(i);

            // If it's a letter, increase count
            if (Character.isLetter(c)) {
                lettersSeen++;
            }

            // Insert a space if we hit the split point
            if (lettersSeen == splitIndex) {
                sb.append(c);
                if (i < token.length() - 1) {
                    sb.append(" ");
                }
            } else {
                sb.append(c);
            }
        }

        return sb.toString();
    }

    /**
     * Main method for basic testing.
     */
    public static void main(String[] args) {
        // Test 1: The sample from the prompt
        String input1 = "A dog can't walk in off the street and order a large soda.";
        String expected1 = "A dog ca n't wa lk in off the str eet and order a lar ge so da.";
        runTest(input1, expected1);

        // Test 2: A short string with no splits
        String input2 = "Hi I am Sam.";
        String expected2 = "Hi I am Sam.";
        runTest(input2, expected2);

        // Test 3: Odd length word with punctuation
        String input3 = "pizza!";
        // 'pizza' has 5 letters => splitIndex = 3 => "piz za!"
        String expected3 = "piz za!";
        runTest(input3, expected3);

        // Test 4: Large input (simulate big data)
        // We'll just confirm it runs without error and returns something plausible.
        StringBuilder largeInput = new StringBuilder();
        for (int i = 0; i < 10000; i++) {
            largeInput.append("supercalifragilisticexpialidocious ").append(i).append(" ");
        }
        // Just check we can handle it without throwing an error
        String outputLarge = splitWords(largeInput.toString());
        System.out.println("Large Input Test Output length: " + outputLarge.length() + " (PASS if no error)");

        // Additional edge cases you may want to manually verify:
        // - Strings with only punctuation
        // - Strings with exactly 4-letter words, or exactly 3
        // - Words with mixed numbers and letters
    }

    private static void runTest(String input, String expected) {
        String actual = splitWords(input);
        boolean pass = actual.equals(expected);

        System.out.println("Input:      " + input);
        System.out.println("Expected:   " + expected);
        System.out.println("Actual:     " + actual);
        System.out.println("Test Result: " + (pass ? "PASS" : "FAIL"));
        System.out.println();
    }
}
