package com.interview.notes.code.year.y2026.may.common.test4;

import java.util.List; // Needed to create a collection of our test cases
import java.util.Objects; // Needed for safe null checks or equality comparisons

public class NumberToWordConverter { // Class declaration to encapsulate our conversion logic

    // Array holding words for numbers below 20, needed because 11-19 do not follow standard tens formatting
    static final String[] ONES = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    
    // Array holding words for multiples of ten, needed to map the tens digit (20-90)
    static final String[] TENS = {"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    
    // Array holding scale words, needed to attach the correct magnitude to each 3-digit chunk (up to Trillions for large data)
    static final String[] SCALES = {"", "Thousand", "Million", "Billion", "Trillion"};

    public static String convert(long num) { // Main method accepting a 'long' to successfully process very large data inputs
        if (num == 0) return "Zero"; // Base case: if the number is exactly 0, immediately return "Zero"
        
        var words = ""; // Using Java 10+ 'var' to minimize code words; this string builder will hold the final answer
        var scaleIndex = 0; // Using 'var' to track our position in the SCALES array (thousands, millions, etc.)
        
        while (num > 0) { // Loop continuously until the number is completely reduced to 0
            if (num % 1000 != 0) { // Check if the current 3-digit chunk has value; if 000, we skip adding words
                // Get words for the 3-digit chunk, append current scale, and attach to the front of existing words
                words = helper((int) (num % 1000)) + SCALES[scaleIndex] + " " + words;
            } // Close the conditional block
            num /= 1000; // Shift the number right by 3 decimal places to process the next chunk of thousands
            scaleIndex++; // Increment the scale index so the next chunk gets the next magnitude (e.g., Million instead of Thousand)
        } // Close the while loop
        
        return words.replaceAll(" +", " ").trim(); // Remove any accidental double spaces and trim edges before returning
    } // Close the convert method

    static String helper(int n) { // Helper recursive method to convert chunks strictly under 1000 into words
        if (n == 0) return ""; // Base case for recursion: if the chunk drops to 0, return an empty string
        if (n < 20) return ONES[n] + " "; // Direct array lookup for numbers 1-19, adding a space for formatting
        if (n < 100) return TENS[n / 10] + " " + helper(n % 10); // Extract tens digit, append word, then recursively solve the remaining ones digit
        return ONES[n / 100] + " Hundred " + helper(n % 100); // Extract hundreds digit, append "Hundred", then recursively solve the remaining tens/ones
    } // Close the helper method

    public static void main(String[] args) { // Simple main method used for testing instead of JUnit, as requested

        // Creating a list of test cases, including the requested 1341, edge cases, and very large data
        var testCases = List.of( // Using 'var' and Java 9+ List.of to concisely define our test scenarios
            new TestCase(0L, "Zero"), // Testing the absolute minimum boundary case
            new TestCase(1341L, "One Thousand Three Hundred Forty One"), // Testing the exact user-requested scenario
            new TestCase(1000000L, "One Million"), // Testing exact scale boundaries to ensure no extra zeros/spaces appear
            new TestCase(2147483647L, "Two Billion One Hundred Forty Seven Million Four Hundred Eighty Three Thousand Six Hundred Forty Seven"), // Testing max Integer limit to simulate large data
            new TestCase(999999999999L, "Nine Hundred Ninety Nine Billion Nine Hundred Ninety Nine Million Nine Hundred Ninety Nine Thousand Nine Hundred Ninety Nine") // Testing extreme large data near Trillions
        ); // Close the List definition

        // Using Java 8 Stream API to iterate through each test case cleanly
        testCases.forEach(tc -> { // Open a lambda function to process each TestCase object
            var result = convert(tc.input()); // Call the conversion logic with the test input and store the result
            var isPass = Objects.equals(result, tc.expected()); // Compare the actual result with the expected result safely
            var status = isPass ? "PASS" : "FAIL"; // Use a ternary operator to assign the PASS/FAIL string flag
            System.out.println(status + " -> Input: " + tc.input() + " | Result: " + result); // Print the final test status to the console
        }); // Close the forEach lambda block
    } // Close the main method

    // Using Java 14+ Record feature to create a tiny, immutable class for holding test data, reducing boilerplate
    record TestCase(long input, String expected) {} // Holds the input number and the expected word output for validation
} // Close the class declaration