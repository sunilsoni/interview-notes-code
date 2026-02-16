package com.interview.notes.code.year.y2026.feb.common.test4;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PalindromePartition {

    public static void main(String[] args) {
        // --- Test Case 1: Standard Input ---
        System.out.println("Test Case 1: Standard Input");
        var input1 = List.of("radar", "level", "java", "stream", "madam");
        // Expected: true=[radar, level, madam], false=[java, stream]
        runTest(input1, 3, 2);

        // --- Test Case 2: Edge Cases (Empty strings, single letters) ---
        System.out.println("\nTest Case 2: Edge Cases");
        var input2 = List.of("", "a", "bb", "abc");
        // Expected: true=["", "a", "bb"], false=["abc"] (Empty string & single char are palindromes)
        runTest(input2, 3, 1);

        // --- Test Case 3: Large Data Simulation ---
        System.out.println("\nTest Case 3: Large Data Input (100,000 words)");
        // Generate a large list combining "aba" (palindrome) and "abc" (non-palindrome)
        var largeInput = IntStream.range(0, 100_000)
                .mapToObj(i -> i % 2 == 0 ? "aba" : "abc")
                .toList();
        runTest(largeInput, 50_000, 50_000);
    }

    // Helper method to run logic and print PASS/FAIL
    // We use 'var' (Java 10+) to keep code clean and short
    private static void runTest(List<String> input, int expectedPalindromeCount, int expectedNonPalindromeCount) {

        // --- CORE LOGIC STARTS HERE ---
        Map<Boolean, List<String>> result = input.stream() // 1. Convert list to a Stream to process data
                .collect(Collectors.partitioningBy(word ->     // 2. Split data into two groups (true/false)
                        new StringBuilder(word).reverse().toString().equals(word) // 3. Condition: Create reversed version and check equality
                ));
        // --- CORE LOGIC ENDS HERE ---

        // Extracting sizes for verification
        var palindromes = result.get(true);  // Get the list where condition was true
        var others = result.get(false);      // Get the list where condition was false

        // Verification Logic (Simple if-else to print PASS/FAIL)
        if (palindromes.size() == expectedPalindromeCount && others.size() == expectedNonPalindromeCount) {
            System.out.println("Result: PASS");
            System.out.println("Palindromes Found: " + palindromes.size());
            // Printing first few items to verify visually without spamming console
            System.out.println("Sample Output: " + result);
        } else {
            System.out.println("Result: FAIL");
            System.out.println("Expected " + expectedPalindromeCount + " palindromes, but got " + palindromes.size());
        }
    }
}