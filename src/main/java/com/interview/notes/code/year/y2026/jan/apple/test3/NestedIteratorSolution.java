package com.interview.notes.code.year.y2026.jan.apple.test3;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public class NestedIteratorSolution {

    public static void main(String[] args) {
        System.out.println("--- Running Tests from Screenshot ---");

        // Test Case 1: "Standard"
        // Input: [[1,1], 2, [1,1]] -> Output: 1, 1, 2, 1, 1
        List<Object> t1 = List.of(List.of(1, 1), 2, List.of(1, 1));
        runTest("Example 1 (Standard)", t1, List.of(1, 1, 2, 1, 1));

        // Test Case 2: "Deep Nesting"
        // Input: [1, [4, [6]]] -> Output: 1, 4, 6
        List<Object> t2 = List.of(1, List.of(4, List.of(6)));
        runTest("Example 2 (Deep)", t2, List.of(1, 4, 6));

        // Test Case 3: "Complex Mixed" (From your 2nd screenshot)
        // Input: [[], [3], [[4], [5], 6], 7] -> Output: 3, 4, 5, 6, 7
        // Note: The empty list [] at the start should disappear.
        List<Object> t3 = List.of(
                List.of(),                  // []
                List.of(3),                 // [3]
                List.of(                    // [[4], [5], 6]
                        List.of(4),
                        List.of(5),
                        6
                ),
                7                           // 7
        );
        runTest("Example 3 (Mixed/Empty)", t3, List.of(3, 4, 5, 6, 7));

        // Test Case 4: Large Data Input
        // Logic: Verify performance with 10,000 items.
        List<Object> hugeList = new ArrayList<>();
        List<Integer> expectedHuge = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            hugeList.add(List.of(i)); // Add nested structure.
            expectedHuge.add(i);      // Add expected integer.
        }
        runTest("Large Data (10k)", hugeList, expectedHuge);
    }

    // --- MAIN METHOD FOR TESTING ---

    // Simple test runner to check PASS/FAIL without JUnit.
    private static void runTest(String testName, List<Object> input, List<Integer> expected) {
        var iterator = new NestedIterator(input); // Init iterator.
        var result = new ArrayList<Integer>();    // Store results.

        while (iterator.hasNext()) { // Iterate until done.
            result.add(iterator.next()); // Add to result list.
        }

        // Compare actual result vs expected result.
        if (result.equals(expected)) {
            System.out.println("PASS: " + testName);
        } else {
            System.out.println("FAIL: " + testName);
            System.out.println(" - Expected: " + expected);
            System.out.println(" - Got:      " + result);
        }
    }

    // The Iterator Class implementation using Java 21 features.
    static class NestedIterator implements Iterator<Integer> {

        // Iterator to hold the final flat sequence of integers.
        private final Iterator<Integer> flatIter;

        // Constructor: Accepts the nested structure (List of Objects).
        public NestedIterator(List<Object> nestedList) {
            // Flatten the list immediately using a recursive stream and get its iterator.
            this.flatIter = flatten(nestedList).iterator();
        }

        // Helper method: Recursively flattens the nested structure.
        private Stream<Integer> flatten(Object input) {
            // Case 1: If input is an Integer, wrap it in a stream.
            if (input instanceof Integer i) {
                return Stream.of(i);
            }
            // Case 2: If input is a List, process its elements recursively.
            if (input instanceof List<?> list) {
                return list.stream() // Convert list to stream.
                        .flatMap(this::flatten); // Recursively flatten each element.
            }
            // Case 3: Fallback for empty/null (handles the "[]" case efficiently).
            return Stream.empty();
        }

        @Override
        public Integer next() {
            // Return the next integer from the flattened iterator.
            return flatIter.next();
        }

        @Override
        public boolean hasNext() {
            // Check if there are more integers left.
            return flatIter.hasNext();
        }
    }
}