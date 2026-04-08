package com.interview.notes.code.year.y2026.april.common.test6;

public class MapToLongExample { // Main class to demonstrate primitive long streams.

    public static void main(String[] args) { // Entry point for the application.

        // Test Case 1: Provided String logic.
        var s = "Hello How are you?"; // The input string.
        
        // Logic: Calculate the sum of all ASCII values of the characters.
        long asciiSum = s.chars() // Step 1: IntStream of character codes (e.g., 'H' becomes 72).
            .mapToLong(c -> (long) c) // Step 2: Explicitly transform each int to a primitive long.
            .sum(); // Step 3: Use the LongStream terminal operation to add them all up.

        // Validation for Test Case 1 (The sum for "Hello How are you?" is exactly 1633).
        boolean pass1 = (asciiSum == 1633); // Check if the math matches the expected total.
        System.out.println("ASCII Sum Test: " + (pass1 ? "PASS" : "FAIL")); // Output result.

        // Test Case 2: Large Data handling.
        // We will create a massive string and count specific character codes.
        var largeData = "A".repeat(1_000_000); // 1 million 'A' characters (ASCII 65).
        
        long totalValue = largeData.chars() // Start the stream.
            .mapToLong(c -> (long) c) // Convert to long primitives to avoid overflow during large sums.
            .sum(); // Summing 1,000,000 instances of 65.

        // Validation for Test Case 2.
        boolean pass2 = (totalValue == 65_000_000L); // Check if the calculation is correct.
        System.out.println("Large Data Sum Test: " + (pass2 ? "PASS" : "FAIL")); // Output result.
        
        // Example of why we use mapToLong instead of mapToObj:
        System.out.println("Total numeric weight of string: " + asciiSum); // Final visual output.
    }
}