package com.interview.notes.code.year.y2025.June.common.test1;

public class StringPermutations {
    
    // Main method containing test cases
    public static void main(String[] args) {
        // Test different scenarios
        testPermutations("ABC");  // Normal case
        testPermutations("");     // Edge case: empty string
        testPermutations("A");    // Edge case: single char
        testPermutations("ABCD"); // Larger input
    }

    // Method to print all permutations of a string
    public static void printPermutations(String str) {
        // Handle null or empty string
        if (str == null || str.isEmpty()) {
            System.out.println("Empty or null string");
            return;
        }

        // Convert string to char array for easier manipulation
        char[] chars = str.toCharArray();
        
        // Track which characters have been used
        boolean[] used = new boolean[chars.length];
        
        // StringBuilder to build each permutation
        StringBuilder currentPerm = new StringBuilder();
        
        // Start recursive permutation generation
        generatePermutations(chars, used, currentPerm);
    }

    // Recursive method to generate permutations
    private static void generatePermutations(char[] chars, boolean[] used, 
                                           StringBuilder currentPerm) {
        // Base case: if current permutation length equals input length
        if (currentPerm.length() == chars.length) {
            System.out.println(currentPerm.toString());
            return;
        }

        // Try each character in the array
        for (int i = 0; i < chars.length; i++) {
            // Skip if character is already used
            if (used[i]) {
                continue;
            }

            // Use current character
            used[i] = true;
            currentPerm.append(chars[i]);

            // Recursive call to generate next character
            generatePermutations(chars, used, currentPerm);

            // Backtrack: remove character and mark as unused
            used[i] = false;
            currentPerm.setLength(currentPerm.length() - 1);
        }
    }

    // Test method to verify permutations
    private static void testPermutations(String input) {
        System.out.println("\nTesting permutations for: " + input);
        System.out.println("Expected number of permutations: " + 
                          calculateFactorial(input.length()));
        System.out.println("Actual permutations:");
        printPermutations(input);
    }

    // Helper method to calculate factorial for expected permutations
    private static int calculateFactorial(int n) {
        if (n <= 1) return n == 0 ? 1 : n;
        return n * calculateFactorial(n - 1);
    }
}
