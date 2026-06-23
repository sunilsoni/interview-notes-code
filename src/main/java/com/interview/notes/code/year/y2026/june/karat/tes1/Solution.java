package com.interview.notes.code.year.y2026.june.karat.tes1;

import java.util.Arrays; // We need this import to convert our raw String array into a functional Java Stream

public class Solution { // Main class declaration to encapsulate the logic and the standalone test runner
    
    public static String find(String[] words, String note) { // Method signature accepting the dictionary of words and the scrambled note
        var noteCounts = new int[256]; // Create an integer array of fixed size 256 to track frequencies of all possible ASCII characters in the note
        
        for (var c : note.toCharArray()) { // Convert the note into a character array and loop through every single character
            noteCounts[c]++; // Increment the integer value at the index matching the character's ASCII value to track its frequency
        } // End of loop; we now have a complete map of available letters and their quantities
        
        return Arrays.stream(words) // Convert the raw String array 'words' into a Java Stream so we can process it declaratively
                .filter(word -> canForm(word, noteCounts)) // Keep only the words in the stream that return 'true' from our canForm helper method
                .findFirst() // Grab the very first word that successfully makes it through the filter (since the prompt guarantees at most one match)
                .orElse("-"); // If the stream is empty (meaning no words passed the filter), return the default dash string as required
    } // End of the main processing method
    
    private static boolean canForm(String word, int[] noteCounts) { // Helper method to verify if a single word can be built using the note's character frequencies
        var wordCounts = new int[256]; // Create a temporary integer array to track the character frequencies specifically needed for this current word
        
        for (var c : word.toCharArray()) { // Convert the current word into a character array and loop through every single letter
            if (++wordCounts[c] > noteCounts[c]) { // Pre-increment the count for this specific letter, then immediately check if we now need more than the note has available
                return false; // If we need more of this letter than what exists in the note, this word cannot be formed, so fail fast and return false
            } // End of the frequency boundary check
        } // End of the loop iterating over the word's characters
        
        return true; // If the loop finishes without hitting the 'return false' condition, we have enough of every letter, so return true
    } // End of the helper method
    
    public static void main(String[] args) { // Standard main method used here strictly as a simple, zero-dependency test runner
        var words = new String[]{"baby", "referee", "cat", "dada", "dog", "bird", "ax", "baz"}; // Define the exact array of target words provided in the problem description
        
        // Running all standard test cases provided in the prompt
        runTest(words, "ctay", "cat", "Test 1"); // Test 1: Letters are present but not in order, expecting "cat"
        runTest(words, "bcanihjsrrrferet", "cat", "Test 2"); // Test 2: Letters are present but scattered and not together, expecting "cat"
        runTest(words, "tbaykkjlga", "-", "Test 3"); // Test 3: Missing letters to form "baby" or "cat", expecting "-"
        runTest(words, "bbbbblkkjbaby", "baby", "Test 4"); // Test 4: Contains exactly the letters for "baby", expecting "baby"
        runTest(words, "dad", "-", "Test 5"); // Test 5: Not enough 'a's to form "dada", letters cannot be reused, expecting "-"
        runTest(words, "breadmaking", "bird", "Test 6"); // Test 6: Scrambled letters contain "bird", expecting "bird"
        runTest(words, "dadaa", "dada", "Test 7"); // Test 7: Has enough letters for "dada" with an extra 'a', expecting "dada"
        
        // Running a custom large data input test case
        var largeNote = "a".repeat(100000) + "b".repeat(100000) + "y".repeat(100000); // Simulate a massive payload by repeating characters 300,000 times
        runTest(words, largeNote, "baby", "Large Data Test"); // Ensure our O(N) frequency counting logic processes large inputs instantly without memory overflow
    } // End of main test runner method
    
    private static void runTest(String[] words, String note, String expected, String testName) { // Utility method to format the execution and output of a single test case
        var result = find(words, note); // Execute the core logic by passing the inputs and capturing the returned string
        var status = result.equals(expected) ? "PASS" : "FAIL"; // Use a ternary operator to check if actual result matches expected result, assigning PASS or FAIL
        System.out.println(testName + " : " + status + " (Expected: " + expected + ", Got: " + result + ")"); // Print the formatted outcome to the console so we can verify the results
    } // End of the test utility method
} // End of the Solution class