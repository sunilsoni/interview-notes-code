package com.interview.notes.code.year.y2026.march.common.tes1;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

public class PalindromeFinder { // Main class wrapper required by Java to hold our methods

    public static Set<String> find(String s) { // Public method that takes a string and returns a Set of unique palindromes
        var result = new HashSet<String>(); // Uses modern 'var' for less code; HashSet automatically prevents duplicate palindromes
        if (s == null || s.isEmpty()) return result; // Edge case check: returns empty set immediately if input is invalid to prevent crashes
        
        IntStream.range(0, s.length()).forEach(i -> { // Generates a stream of indexes from 0 to string length for clean iteration
            expand(s, i, i, result); // Calls helper to find all odd-length palindromes expanding from center index 'i'
            expand(s, i, i + 1, result); // Calls helper to find all even-length palindromes expanding between 'i' and 'i+1'
        }); // Closes the Stream forEach loop
        
        return result; // Returns the fully populated set of palindromes to the caller
    } // Closes the find method

    private static void expand(String s, int left, int right, Set<String> res) { // Helper method to check characters radiating outward from a center
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) { // Loop runs as long as pointers are in bounds and characters match
            res.add(s.substring(left, right + 1)); // Extracts the valid palindrome using current pointers and adds it to our Set
            left--; // Decrements left pointer to expand the search window to the left
            right++; // Increments right pointer to expand the search window to the right
        } // Closes the while loop
    } // Closes the expand method

    public static void main(String[] args) { // Simple main method used for testing instead of JUnit
        test("banana", Set.of("b", "a", "n", "ana", "anana", "nan")); // Test 1: The provided standard test case
        test("a", Set.of("a")); // Test 2: Edge case for a single character
        test("", Set.of()); // Test 3: Edge case for an empty string
        test("aaaa", Set.of("a", "aa", "aaa", "aaaa")); // Test 4: Edge case for repeating characters testing even/odd logic
        
        var largeStr = "a".repeat(2000); // Creates a large input of 2000 'a's using modern String.repeat for stress testing
        var start = System.currentTimeMillis(); // Records the current time to measure how fast the large data is processed
        find(largeStr); // Executes the palindrome finder on the massive string
        System.out.println("Large data (2000 chars) processed in: " + (System.currentTimeMillis() - start) + "ms"); // Prints the time taken, proving efficiency
    } // Closes the main method

    private static void test(String input, Set<String> expected) { // Helper method to run tests and print PASS/FAIL cleanly
        var actual = find(input); // Executes our logic to get the actual calculated output
        var pass = actual.equals(expected); // Compares actual output with expected output to determine boolean pass status
        System.out.println("Input: '" + input + "' -> " + (pass ? "PASS" : "FAIL") + " | Found: " + actual); // Prints clear formatted output for the test case
    } // Closes the test method
} // Closes the entire class