package com.interview.notes.code.year.y2026.april.common.test6;

import java.util.stream.Collectors; // Imports Collectors to group and count data in the stream.

public class CharOccurrence { // Declares the class for counting character frequencies.

    public static void main(String[] args) { // Main entry point for the program.
        
        // Test Case: The string provided in your prompt.
        var s = "Hello How are you?"; // Initializes the input string using 'var' for brevity.
        
        // The Logic: Using Java 8 Streams to count occurrences.
        var result = s.chars() // Step 1: Converts the string into a stream of integer representations of characters.
            .mapToObj(c -> (char) c) // Step 2: Converts each integer back into a Character object for easier reading.
            .collect(Collectors.groupingBy(c -> c, Collectors.counting())); // Step 3: Groups by character and counts appearances.

        // Print results for visual check.
        System.out.println("Occurrences: " + result); // Outputs the map showing each character and its count.

        // Simple Test Logic to check PASS/FAIL.
        // Let's check if 'o' appears 3 times (HellO, hOw, yOu).
        var pass = result.get('o') == 3; // Checks if our stream logic correctly identified 3 'o' characters.
        
        var status = pass ? "PASS" : "FAIL"; // Assigns status based on whether the count matches expectation.
        System.out.println("Test Case Result: " + status); // Prints the final result to the console.

        // Handling Large Data Input Case.
        var largeStr = "a".repeat(1_000_000); // Creates a string with 1 million 'a' characters to test scalability.
        var largeResult = largeStr.chars().mapToObj(c -> (char) c)
                                  .collect(Collectors.groupingBy(c -> c, Collectors.counting())); // Processes large string.
        
        var largePass = largeResult.get('a') == 1_000_000; // Verifies the large data count is accurate.
        System.out.println("Large Data Test: " + (largePass ? "PASS" : "FAIL")); // Prints PASS for successful large data handling.
    }
}