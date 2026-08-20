package com.interview.notes.code.year.y2026.august.amazon.test2;

import java.util.Collections; // Needed for Collections.reverseOrder() to create a Max-Heap
import java.util.List; // Needed to group input elements for standard test cases
import java.util.PriorityQueue; // Needed for the core data structure to hold stream halves efficiently
import java.util.stream.IntStream; // Needed to generate large data streams concisely using Stream API

public class RunningMedian { // Main class encapsulating the running median logic
    
    private final PriorityQueue<Integer> lower = new PriorityQueue<>(Collections.reverseOrder()); // Max-Heap holding the smaller half of numbers; peek gives the largest of the smalls
    private final PriorityQueue<Integer> upper = new PriorityQueue<>(); // Min-Heap holding the larger half of numbers; peek gives the smallest of the larges
    
    public static void main(String[] args) { // Main method used strictly for execution and testing, avoiding JUnit as requested

        var tests = List.of( // Java 10+ var and Java 9+ List.of feature: cleanly initializes an immutable list of test cases
            new Test(List.of(5), 5.0), // Edge case: Single element
            new Test(List.of(5, 15), 10.0), // Standard case: Even count of elements (average of 5 and 15)
            new Test(List.of(5, 15, 1), 5.0), // Standard case: Odd count of elements (sorted: 1, 5, 15 -> middle is 5)
            new Test(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), 5.5) // Sequence of numbers case
        ); // End of test case declarations

        System.out.println("--- STANDARD TESTS ---"); // Console formatting for readability
        for (var t : tests) { // For-each loop using var to dynamically iterate over our defined test cases
            var rm = new RunningMedian(); // Instantiate a fresh RunningMedian object for each test to ensure state isolation
            t.input.forEach(rm::add); // Java 8 Stream API method reference: concisely loop through input list and add all elements
            boolean passed = Double.compare(rm.getMedian(), t.expected) == 0; // Compare double values safely using Double.compare to prevent precision-loss bugs
            System.out.println((passed ? "PASS" : "FAIL") + " -> Input: " + t.input + " | Expected: " + t.expected + " | Got: " + rm.getMedian()); // Print strictly formatted PASS/FAIL test results
        } // End of standard tests loop

        System.out.println("\n--- LARGE DATA TEST ---"); // Console formatting separating the large dataset execution
        var largeRm = new RunningMedian(); // Create a dedicated instance specifically for the heavy load test
        long start = System.currentTimeMillis(); // Track system start time to measure algorithmic efficiency

        IntStream.rangeClosed(1, 100_000).forEach(largeRm::add); // Java 8 Stream API: rapidly generate and sequentially insert numbers 1 to 100,000
        double expectedLarge = 50000.5; // The mathematical median of a sequential list of numbers from 1 through 100,000
        boolean largePassed = Double.compare(largeRm.getMedian(), expectedLarge) == 0; // Validate our O(1) calculation against the mathematical truth
        long timeTaken = System.currentTimeMillis() - start; // Calculate total elapsed execution time in milliseconds

        System.out.println((largePassed ? "PASS" : "FAIL") + " -> Large Data Input [1 to 100,000] | Expected: " + expectedLarge + " | Got: " + largeRm.getMedian() + " | Time: " + timeTaken + "ms"); // Print performance test result, proving O(log N) insertion handles scale instantly
    } // End of main method
    
    public void add(int n) { // Method to ingest a new number from the stream into our data structure
        lower.offer(n); // Step 1: Default to adding the new number into the lower half max-heap
        upper.offer(lower.poll()); // Step 2: Push the largest from lower to upper to guarantee lower half values are always <= upper half values

        if (lower.size() < upper.size()) { // Step 3: Check if the upper half now has more elements, breaking our rule that lower half size must be >= upper half size
            lower.offer(upper.poll()); // Balance it back by moving the smallest of the upper half into the lower half
        } // End of balancing logic block
    } // End of add method
    
    public double getMedian() { // Method to compute and return the current median in O(1) time
        if (lower.size() == upper.size()) { // If both heaps have the same size, we have an even total number of elements in the stream
            return (lower.peek() + upper.peek()) / 2.0; // The median is the exact average of the two middle elements (tops of both heaps)
        } // End of even condition check
        return lower.peek(); // If odd count, lower half deliberately holds the extra element (the true median), so return it directly
    } // End of getMedian method
    
    record Test(List<Integer> input, double expected) {} // Java 16+ Record feature: concisely defines an immutable data carrier for test cases without boilerplate getters/setters
} // End of class