package com.interview.notes.code.year.y2026.jan.common.test7;

import java.util.List;

public class PhoneDialer {

    // Main method handles testing directly without JUnit
    public static void main(String[] args) {
        System.out.println("Running Tests...\n");

        // Test Case 1: Single Digit "2" -> Expect [A, B, C]
        runTest("2", List.of("A", "B", "C"));

        // Test Case 2: Provided Example "23" -> Expect 9 combinations
        // Expected: AD, AE, AF, BD, BE, BF, CD, CE, CF
        runTest("23", List.of("AD", "AE", "AF", "BD", "BE", "BF", "CD", "CE", "CF"));

        // Test Case 3: Empty Input -> Expect Empty List
        runTest("", List.of());

        // Test Case 4: Large Data/Stress Test (5 digits = 3^5 = 243 combinations)
        // We verify the size logic rather than printing all 243 items
        runStressTest("23456", 243); 
    }

    // Core Solution Logic
    static List<String> letterCombinations(String digits) {
        // Handle edge case: empty input returns empty list immediately
        if (digits == null || digits.isEmpty()) return List.of();

        // Mapping array where index matches digit (0,1 unused, 2="ABC"...)
        var map = new String[]{"", "", "ABC", "DEF", "GHI", "JKL", "MNO", "PQRS", "TUV", "WXYZ"};

        // 1. Stream over the input characters (digits)
        // 2. Map digit char to its letters (e.g. '2' -> "ABC")
        // 3. Map string "ABC" to List of chars ['A','B','C']
        // 4. Reduce: Start with [""] and progressively combine lists
        return digits.chars()                                                            // Stream of int (ASCII values of digits)
            .mapToObj(d -> map[d - '0'])                                                 // Convert ASCII '2' to index 2 -> get "ABC"
            .map(s -> s.chars().mapToObj(c -> (String.valueOf((char)c))).toList())       // Convert "ABC" to List["A","B","C"]
            .reduce(List.of(""),                                                         // Initial accumulator: List with one empty string
                (acc, letters) -> acc.stream()                                           // Stream current combinations (e.g. ["A","B"...])
                    .flatMap(prefix -> letters.stream().map(l -> prefix + l))            // Combine every prefix with every new letter
                    .toList(),                                                           // Collect results to a new List (Java 16+)
                (a, b) -> a);                                                            // Combiner (needed for syntax, unused in sequential)
    }

    // Helper method to verify results
    static void runTest(String input, List<String> expected) {
        var result = letterCombinations(input);                                          // Run logic
        var pass = result.equals(expected);                                              // Compare lists
        System.out.printf("Input: '%-4s' | Status: %-4s | Got: %s%n",                    // Print formatted status
             input, (pass ? "PASS" : "FAIL"), (result.size() > 9 ? result.size() + " items" : result));
    }

    // Helper for large data logic check
    static void runStressTest(String input, int expectedSize) {
        long startTime = System.nanoTime();                                              // Start timer
        var result = letterCombinations(input);                                          // Run logic
        boolean pass = result.size() == expectedSize;                                    // Check size correctness
        System.out.printf("Input: '%-4s' | Status: %-4s | Size: %d | Time: %.2fms%n",    // Print performance stats
            input, (pass ? "PASS" : "FAIL"), result.size(), (System.nanoTime() - startTime) / 1e6);
    }
}