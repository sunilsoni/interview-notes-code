package com.interview.notes.code.year.y2026.feb.USTechSolutions.test4;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RemoveAllDuplicates {

    // Logic: Only keep items that appear EXACTLY once
    public static List<String> solve(List<String> list1, List<String> list2) {
        return Stream.concat(list1.stream(), list2.stream()) // 1. Combine lists
                .collect(Collectors.groupingBy( // 2. Group items map
                        Function.identity(),        // Key = the string itself
                        Collectors.counting()       // Value = how many times it appears
                ))
                .entrySet().stream()            // 3. Process the map entries
                .filter(e -> e.getValue() == 1) // 4. Keep ONLY if count is exactly 1
                .map(Map.Entry::getKey)         // 5. Extract the string key
                .sorted()                       // 6. Sort alphabetically
                .toList();                      // 7. Output result
    }

    // Main Method: Tests
    public static void main(String[] args) {
        System.out.println("--- Starting Strict Unique Tests ---");

        // --- Test 1: Standard Overlap ---
        // 'banana' and 'apple' appear in both, so they must be REMOVED entirely.
        var l1 = List.of("apple", "banana", "orange");
        var l2 = List.of("banana", "grape", "apple");
        var expected1 = List.of("grape", "orange"); // Only these appear once
        runTest("Remove Duplicates Completely", l1, l2, expected1);

        // --- Test 2: Internal Duplicates ---
        // 'a' appears twice in l3. It should be gone.
        var l3 = List.of("a", "a", "b");
        var l4 = List.of("c");
        var expected2 = List.of("b", "c"); // 'a' is gone
        runTest("Internal Duplicates Removed", l3, l4, expected2);

        // --- Test 3: Large Data Check ---
        runLargeDataTest();
    }

    // Simple Test Helper
    public static void runTest(String name, List<String> i1, List<String> i2, List<String> exp) {
        var result = solve(i1, i2); // Run logic
        boolean pass = result.equals(exp); // Check match
        System.out.println((pass ? "[PASS] " : "[FAIL] ") + name);
        if (!pass) System.out.println("  Exp: " + exp + "\n  Got: " + result);
    }

    // Large Data Test
    public static void runLargeDataTest() {
        // Create list 0-999
        List<String> d1 = java.util.stream.IntStream.range(0, 1000).mapToObj(String::valueOf).toList();
        // Create list 500-1499
        List<String> d2 = java.util.stream.IntStream.range(500, 1500).mapToObj(String::valueOf).toList();

        // 0-499 appear ONCE (Keep)
        // 500-999 appear TWICE (Remove completely)
        // 1000-1499 appear ONCE (Keep)
        // Result size should be 500 + 500 = 1000

        long start = System.currentTimeMillis();
        var res = solve(d1, d2);
        long end = System.currentTimeMillis();

        boolean sizeOk = res.size() == 1000;
        // 500 should NOT be there
        boolean duplicateRemoved = !res.contains("500");

        if (sizeOk && duplicateRemoved) {
            System.out.println("[PASS] Large Data (Processed in " + (end - start) + "ms)");
        } else {
            System.out.println("[FAIL] Large Data. Size: " + res.size());
        }
    }
}