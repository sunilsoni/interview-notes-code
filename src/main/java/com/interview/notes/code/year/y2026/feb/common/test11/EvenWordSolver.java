package com.interview.notes.code.year.y2026.feb.common.test11;

import java.util.Arrays;

public class EvenWordSolver { // Main class to hold our solution and test runner.

    public static int solution(String S) { // The main function requested by the problem statement.
        
        var charCounts = new int[26]; // Use 'var' (modern Java) to initialize an array of size 26 to store counts for 'a' to 'z'.
        
        S.chars() // Convert the input string into a stream of integer character codes (ASCII values).
         .forEach(c -> charCounts[c - 'a']++); // For each character, subtract the ASCII value of 'a' to get an index (0-25) and increment that slot.
         
        return (int) Arrays.stream(charCounts) // Convert our integer array into a Java Stream to process the frequencies efficiently.
             .filter(count -> count % 2 != 0) // Filter the stream to only keep counts that are odd numbers (modulo 2 is not zero).
             .count(); // Count the remaining odd numbers, which perfectly equals the exact number of single deletions needed.
    }

    public static void main(String[] args) { // Simple main method for testing without using JUnit.
        
        test("acbcbba", 1); // Test Case 1 from the problem description: should return 1 (delete one 'b').
        
        test("axxaxa", 2); // Test Case 2 from the problem description: should return 2 (delete one 'a' and one 'x').
        
        test("aaaa", 0); // Test Case 3 from the problem description: should return 0 (all even, no deletions).
        
        test("abcdefghijklmnopqrstuvwxyz", 26); // Edge Case: All unique characters, all odd (1), should delete all 26.
        
        test("", 0); // Edge Case: Empty string, nothing to delete.
        
        var largeData = "a".repeat(100000) + "b".repeat(99999) + "c"; // Construct a massive string of 200,000 characters to test performance.
        test(largeData, 2); // Large Data Case: 100k 'a's (even), 99,999 'b's (odd), 1 'c' (odd). Should return 2 deletions.
    }

    private static void test(String input, int expectedOutput) { // Helper method to run test cases and print PASS or FAIL.
        
        var result = solution(input); // Call our solution method with the provided input string.
        
        var status = (result == expectedOutput) ? "PASS" : "FAIL"; // Use a ternary operator to check if the result matches what we expect.
        
        var preview = input.length() > 20 ? input.substring(0, 20) + "..." : input; // Truncate very long strings for cleaner console output.
        
        System.out.println(status + " -> Input: [" + preview + "] | Expected: " + expectedOutput + " | Got: " + result); // Print the final formatted test result to the console.
    }
}