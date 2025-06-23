package com.interview.notes.code.year.y2025.June.common.test1;

public class StringCombinations {

    // Main method to test the solution
    public static void main(String[] args) {
        // Test cases to verify the solution
        testCombinations("ABC");
        testCombinations("");  // Edge case: empty string
        testCombinations("A"); // Edge case: single character
        testCombinations("ABCD"); // Larger input
    }

    // Method to print all combinations of a given string
    public static void printCombinations(String str) {
        // Base case: if string is null or empty
        if (str == null || str.length() == 0) {
            System.out.println("Empty or null string");
            return;
        }

        // Convert string to char array for easier manipulation
        char[] chars = str.toCharArray();

        // Boolean array to keep track of used characters
        boolean[] used = new boolean[chars.length];

        // StringBuilder to build combinations
        StringBuilder output = new StringBuilder();

        // Generate combinations of different lengths
        for (int length = 1; length <= chars.length; length++) {
            generateCombinations(chars, used, output, length, 0);
        }
    }

    // Recursive method to generate combinations
    private static void generateCombinations(char[] chars, boolean[] used,
                                             StringBuilder output, int length, int startPosition) {
        // If we've reached desired length, print the combination
        if (output.length() == length) {
            System.out.println(output.toString());
            return;
        }

        // Try each character from the starting position
        for (int i = startPosition; i < chars.length; i++) {
            // Skip if character is already used
            if (used[i]) continue;

            // Include current character
            used[i] = true;
            output.append(chars[i]);

            // Recursively generate combinations
            generateCombinations(chars, used, output, length, i + 1);

            // Backtrack: remove the character for next iteration
            used[i] = false;
            output.setLength(output.length() - 1);
        }
    }

    // Test method to verify combinations
    private static void testCombinations(String input) {
        System.out.println("\nTesting combinations for: " + input);
        System.out.println("Expected number of combinations: " +
                calculateExpectedCombinations(input.length()));
        System.out.println("Actual combinations:");
        printCombinations(input);
    }

    // Helper method to calculate expected number of combinations
    private static int calculateExpectedCombinations(int n) {
        if (n == 0) return 0;
        int total = 0;
        for (int r = 1; r <= n; r++) {
            total += nCr(n, r);
        }
        return total;
    }

    // Calculate combinations (n choose r)
    private static int nCr(int n, int r) {
        if (r > n) return 0;
        if (r == 0 || r == n) return 1;
        return nCr(n - 1, r - 1) + nCr(n - 1, r);
    }
}
