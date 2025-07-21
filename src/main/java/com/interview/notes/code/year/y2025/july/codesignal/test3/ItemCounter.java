package com.interview.notes.code.year.y2025.july.codesignal.test3;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ItemCounter {

    /**
     * Counts occurrences of each non-null item in the list, sorts them, and prints "item-count".
     */
    public static void countAndPrint(List<String> items) {
        // 1. Stream the list
        // 2. Filter out nulls to avoid NullPointerException
        // 3. Group by item string, using TreeMap to sort by key
        // 4. Count each group
        Map<String, Long> counts = items.stream()
                .filter(Objects::nonNull)                                     // skip any null entries
                .collect(Collectors.groupingBy(
                        Function.identity(),                                      // grouping key = the string itself
                        TreeMap::new,                                             // supplier for a sorted map
                        Collectors.counting()                                     // downstream collector to count occurrences
                ));

        // Print each entry as "key-count"
        counts.forEach((key, count) ->
                System.out.println(key + " - " + count)
        );
    }

    public static void main(String[] args) {
        // Define test cases: each entry has a name, input list, and expected map
        Map<String, TestCase> tests = new LinkedHashMap<>();

        // 1. Basic example from prompt
        tests.put("Basic",
                new TestCase(
                        Arrays.asList("Pen", "Eraser", "Note Book", "Pen", "Pencil", "Stapler", "Note Book", "Pencil"),
                        new TreeMap<>(Map.of(
                                "Eraser", 1L,
                                "Note Book", 2L,
                                "Pen", 2L,
                                "Pencil", 2L,
                                "Stapler", 1L
                        ))
                )
        );

        // 2. Empty list
        tests.put("EmptyList",
                new TestCase(
                        Collections.emptyList(),
                        new TreeMap<>()
                )
        );

        // 3. All same
        tests.put("AllSame",
                new TestCase(
                        Arrays.asList("X", "X", "X"),
                        new TreeMap<>(Map.of("X", 3L))
                )
        );

        // 4. Contains nulls and blanks
        tests.put("NullsAndEmpty",
                new TestCase(
                        Arrays.asList("A", null, "", "A", ""),
                        new TreeMap<>(Map.of(
                                "", 2L,
                                "A", 2L
                        ))
                )
        );

        // Run each test
        tests.forEach((name, tc) -> {
            Map<String, Long> result = tc.input.stream()
                    .filter(Objects::nonNull)
                    .collect(Collectors.groupingBy(Function.identity(), TreeMap::new, Collectors.counting()));
            boolean pass = result.equals(tc.expected);
            System.out.printf("Test %-12s: %s%n", name, pass ? "PASS" : "FAIL");
            if (!pass) {
                System.out.println("  Expected: " + tc.expected);
                System.out.println("  Got     : " + result);
            }
        });

        // Performance test on large data
        int largeN = 1_000_000;
        List<String> largeList = new ArrayList<>(largeN);
        for (int i = 0; i < largeN; i++) {
            largeList.add(i % 2 == 0 ? "Even" : "Odd");
        }
        long start = System.currentTimeMillis();
        countAndPrint(largeList);
        long duration = System.currentTimeMillis() - start;
        System.out.println("Large data processed in " + duration + " ms");
    }

    // Helper to bundle test data
    private static class TestCase {
        final List<String> input;
        final Map<String, Long> expected;

        TestCase(List<String> input, Map<String, Long> expected) {
            this.input = input;
            this.expected = expected;
        }
    }
}