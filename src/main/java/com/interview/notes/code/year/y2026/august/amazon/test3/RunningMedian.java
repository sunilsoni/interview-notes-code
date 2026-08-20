package com.interview.notes.code.year.y2026.august.amazon.test3;

import java.util.Collections; // Needed for Collections.reverseOrder() to configure the Max-Heap
import java.util.List; // Needed to structure test input sequences
import java.util.PriorityQueue; // Core data structure for O(log N) stream ingestion
import java.util.stream.IntStream; // Stream API to generate 100,000 numbers for stress testing

public class RunningMedian { // Class encapsulating hardened running median logic

    private final PriorityQueue<Integer> lower = new PriorityQueue<>(Collections.reverseOrder()); // Max-Heap storing smaller half (peek gives maximum of the smalls)
    private final PriorityQueue<Integer> upper = new PriorityQueue<>(); // Min-Heap storing larger half (peek gives minimum of the larges)

    public static void main(String[] args) { // Main method for comprehensive edge-case verification

        var testCases = List.of( // Immutable suite covering all edge cases
            new Test("Single Element", List.of(42), 42.0, false), // Single element case
            new Test("All Duplicates", List.of(7, 7, 7, 7), 7.0, false), // Duplicate numbers case
            new Test("Negative Numbers", List.of(-10, -20, -5, -30), -15.0, false), // All negative numbers
            new Test("Mixed Positive/Negative", List.of(-5, 10, -1, 0, 8), 0.0, false), // Mixed signs with zero
            new Test("Integer Overflow Guard", List.of(Integer.MAX_VALUE, Integer.MAX_VALUE), (double) Integer.MAX_VALUE, false), // Overflow test case
            new Test("Empty Stream", List.of(), null, true) // Error handling test case
        ); // End test case declarations

        System.out.println("--- EDGE CASE VERIFICATION ---"); // Output section header
        for (var t : testCases) { // Iterate over all edge case tests
            var rm = new RunningMedian(); // Fresh isolated instance
            try { // Try block to capture expected runtime exceptions
                t.input().forEach(rm::add); // Stream API: feed all input values
                double result = rm.getMedian(); // Compute median
                boolean passed = !t.shouldThrow() && Double.compare(result, t.expected()) == 0; // Check expected double value
                System.out.println((passed ? "PASS" : "FAIL") + " -> " + t.name() + " | Expected: " + t.expected() + " | Got: " + result); // Print output
            } catch (IllegalStateException e) { // Catch empty stream exception
                boolean passed = t.shouldThrow(); // Check if exception was expected
                System.out.println((passed ? "PASS" : "FAIL") + " -> " + t.name() + " | Caught Expected Exception: " + e.getMessage()); // Print exception status
            } // End try-catch
        } // End test loop

        System.out.println("\n--- LARGE DATA TEST ---"); // Load test header
        var largeRm = new RunningMedian(); // Fresh instance for 100k records
        long start = System.currentTimeMillis(); // Start timer

        IntStream.rangeClosed(1, 100_000).forEach(largeRm::add); // Insert 100,000 items sequentially
        double expectedLarge = 50000.5; // Calculated median
        boolean largePassed = Double.compare(largeRm.getMedian(), expectedLarge) == 0; // Verify accuracy
        long duration = System.currentTimeMillis() - start; // Measure time

        System.out.println((largePassed ? "PASS" : "FAIL") + " -> Large Data (100,000 items) | Time: " + duration + "ms | Median: " + largeRm.getMedian()); // Output load test result
    } // End main method

    public void add(int n) { // Ingests a new number while keeping heaps balanced
        lower.offer(n); // Step 1: Default new element into lower half Max-Heap
        upper.offer(lower.poll()); // Step 2: Transfer largest lower element to upper to ensure all lower <= all upper

        if (lower.size() < upper.size()) { // Step 3: Check if upper size exceeds lower size
            lower.offer(upper.poll()); // Transfer smallest upper element back to lower to keep lower.size() >= upper.size()
        } // End balance check
    } // End add method

    public double getMedian() { // Computes the running median safely with edge-case protection
        if (lower.isEmpty()) { // Edge Case 1: Guard against empty stream invocations
            throw new IllegalStateException("Cannot calculate median on an empty stream"); // Throw descriptive runtime exception
        } // End empty check

        if (lower.size() == upper.size()) { // Even count of elements: average of both boundaries
            return (lower.peek() + (double) upper.peek()) / 2.0; // Edge Case 2: Cast to double to prevent 32-bit Integer Overflow
        } // End even check

        return lower.peek(); // Odd count: lower half holds the exact middle element
    } // End getMedian method

    record Test(String name, List<Integer> input, Double expected, boolean shouldThrow) {} // Java 21 record for clean test definitions
} // End RunningMedian class