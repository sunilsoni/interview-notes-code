package com.interview.notes.code.year.y2026.march.Cognizant.test3;

import java.util.Arrays;

public class LastWord { // Defining the main class to hold our string processing logic and test methods


    public static int lengthOfLastWord2(String s) {
        // We use a simple 1-element array as a 'flag' to track if we found a second word
        boolean[] hasSecondWord = {false};

        String lastWord = Arrays.stream(s.trim().split("\\s+"))
                .reduce((first, second) -> {
                    hasSecondWord[0] = true; // This block ONLY runs if a second word exists
                    return second;           // Keep the second word as the new 'last word'
                })
                .orElse("");

        // If the flag is still false, it means reduce logic never ran (0 or 1 word found)
        if (!hasSecondWord[0]) {
            System.out.println("Notice: There is no second word available in the segment.");
        }

        return lastWord.length();
    }
    public static int lengthOfLastWord(String s) { // Method signature taking the input string 's' and returning an integer length
        return Arrays.stream(s.trim().split("\\s+")) // Trims outer spaces, splits by internal spaces, and converts the resulting array into a Stream
                     .reduce((first, second) -> second) // Continuously replaces the 'first' element with the 'second', leaving only the very last word
                     .orElse("") // Provides an empty string fallback just in case the stream is somehow completely empty
                     .length(); // Calculates and returns the total number of characters in that final isolated word
    }

    public static void main(String[] args) { // Main method to execute our custom test cases without needing the JUnit framework
        runTest("Hello World", 5); // Runs Example 1 from the problem description, expecting an output of 5
        runTest("   fly me   to   the moon  ", 4); // Runs Example 2, testing if leading, trailing, and multiple spaces are handled
        runTest("luffy is still joyboy", 6); // Runs Example 3, a standard sentence with standard spacing
        
        var largeData = "word ".repeat(100000) + "finalWord"; // Uses modern Java 'var' and 'repeat' features to generate a massive string with 100,001 words
        runTest(largeData, 9); // Tests the large data input to ensure our stream processing handles heavy memory loads perfectly
    }

    private static void runTest(String input, int expected) { // Helper method to process inputs, compare them to expectations, and print PASS/FAIL
        var result = lengthOfLastWord(input); // Calls our main logic method and stores the returned integer using modern 'var' syntax
        var status = (result == expected) ? "PASS" : "FAIL"; // Uses a simple ternary operator to determine if our result perfectly matches the expectation
        var preview = input.length() > 20 ? input.substring(0, 20) + "..." : input; // Truncates the input string for cleaner console logging if it's too long
        System.out.println("[" + status + "] Expected: " + expected + " | Got: " + result + " | Input: '" + preview + "'"); // Prints the formatted PASS/FAIL test result directly to the console
    }
}