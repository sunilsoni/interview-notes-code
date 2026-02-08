package com.interview.notes.code.year.y2026.feb.microsoft.test6;

public class NumberCombinations {

    // Method to print all permutations using recursion
    // str: the digits we have left to use
    // ans: the combination we have built so far
    public static void printPermutations(String str, String ans) {
        // Base Case: If the input string is empty, we have used all digits
        if (str.isEmpty()) { 
            System.out.print(ans + " "); // Print the final formed number combination
            return; // Exit this recursive step
        }

        // Loop through the current string to pick each character once
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i); // Get the character at the current index 'i'
            
            // Form the rest of the string excluding the character we just picked
            // substring(0, i) gets parts before 'i', substring(i+1) gets parts after 'i'
            String restOfString = str.substring(0, i) + str.substring(i + 1);
            
            // Recursive Call: Pass the remaining string and add picked char to answer
            printPermutations(restOfString, ans + ch); 
        }
    }

    // Main method to run tests and logic
    public static void main(String[] args) {
        System.out.println("--- Starting Permutation Tests ---");

        // Test Case 1: The input provided by you
        int a = 1234; // The integer input
        System.out.println("Combinations for: " + a); // Label the output
        printPermutations(String.valueOf(a), ""); // Convert int to String and start recursion
        System.out.println("\n-----------------------------"); // formatting line

        // Test Case 2: Edge Case with duplicate digits
        // Note: This logic treats positions as unique. For '112', it prints duplicates.
        // To fix duplicates, we would need a Set, but keeping it simple as requested.
        int b = 112; // Input with repeating numbers
        System.out.println("Combinations for: " + b); // Label the output
        printPermutations(String.valueOf(b), ""); // Start recursion
        System.out.println("\n-----------------------------"); // formatting line
        
        // Test Case 3: Smallest valid input
        int c = 1; // Single digit
        System.out.println("Combinations for: " + c); // Label output
        printPermutations(String.valueOf(c), ""); // Start recursion
        System.out.println(); // New line
    }
}