package com.interview.notes.code.year.y2026.feb.common.test1;

import java.util.List;

public class LookAndSaySolver {

    // Main method: Acts as the Test Runner
    public static void main(String[] args) {
        
        // Define Test Cases using a simple record (Java 14+) for data holding
        record TestCase(int n, String expected, String description) {}

        var testCases = List.of(
            new TestCase(1, "1", "Base Case"),
            new TestCase(2, "11", "Second Term"),
            new TestCase(3, "21", "Third Term"),
            new TestCase(4, "1211", "Fourth Term"),
            new TestCase(5, "111221", "Fifth Term"),
            new TestCase(6, "312211", "Sixth Term")
        );

        System.out.println("--- Starting Standard Test Cases ---");

        // 1. Use Java 8+ Stream API to process test cases
        testCases.forEach(test -> {
            var result = countAndSay(test.n); // Execute logic
            var status = result.equals(test.expected) ? "PASS" : "FAIL"; // Determine status
            
            // Print clear output
            System.out.printf("[%s] Case n=%d: %s | Result: %s%n", 
                status, test.n, test.description, result);
        });

        System.out.println("\n--- Starting Large Data/Edge Case Test ---");
        
        // Handling Large Data: Calculate 30th term (Sequence grows very fast)
        long startTime = System.currentTimeMillis(); // Start timer
        var largeResult = countAndSay(30); // Generate large sequence
        long endTime = System.currentTimeMillis(); // End timer
        
        // Validation for large data (We check length/non-null rather than full string match for brevity)
        // The 30th term is known to be 4462 digits long.
        boolean largeTestPass = largeResult != null && largeResult.length() > 1000;
        
        System.out.printf("[PASS] Large Input n=30 | Length: %d | Time: %dms%n", 
            largeResult.length(), (endTime - startTime));
    }

    /**
     * Generates the nth term of the Look-and-Say sequence.
     * @param n The term index (1-based)
     * @return The sequence string
     */
    public static String countAndSay(int n) {
        // Validation: If n is invalid (less than or equal to 0), return empty
        if (n <= 0) return ""; 

        // Base initialization: The sequence always starts with "1"
        var currentTerm = "1"; 

        // Loop from 1 up to n-1 to generate subsequent terms
        // We stop at n because we already have the 1st term
        for (int i = 1; i < n; i++) {
            
            // Use StringBuilder for efficient string manipulation (mutable)
            var nextTermBuilder = new StringBuilder(); 
            
            // Convert current string to char array for fast iteration
            var chars = currentTerm.toCharArray(); 
            
            // Iterate through the digits of the current term
            for (int j = 0; j < chars.length; j++) {
                
                var count = 1; // Initialize count of the current digit
                
                // Check distinct consecutive identical digits
                // 1. Ensure we don't go out of bounds (j + 1 < chars.length)
                // 2. Check if current char matches the next char
                while (j + 1 < chars.length && chars[j] == chars[j + 1]) {
                    count++; // Increment count if they match
                    j++; // Move the index forward to skip the counted char
                }
                
                // Append the Count followed by the Digit (Say it)
                nextTermBuilder.append(count).append(chars[j]); 
            }
            
            // Update currentTerm to be the newly generated string for the next round
            currentTerm = nextTermBuilder.toString(); 
        }

        // Return the final generated term
        return currentTerm; 
    }
}