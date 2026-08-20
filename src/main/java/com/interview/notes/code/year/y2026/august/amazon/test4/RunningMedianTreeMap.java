package com.interview.notes.code.year.y2026.august.amazon.test4;

import java.util.List; // Needed to group our test input arrays
import java.util.TreeMap; // Needed for Red-Black Tree structure to keep data sorted dynamically in O(log N)
import java.util.stream.IntStream; // Needed to generate large data streams quickly

public class RunningMedianTreeMap { // Class to solve median without PriorityQueues or Lists
    
    private final TreeMap<Integer, Integer> lower = new TreeMap<>(); // Tree to hold the smaller half of numbers (Number -> Frequency count)
    private final TreeMap<Integer, Integer> upper = new TreeMap<>(); // Tree to hold the larger half of numbers (Number -> Frequency count)
    private int lowerSize = 0; // Tracks total count of numbers in the lower half (since map sizes only count unique keys)
    private int upperSize = 0; // Tracks total count of numbers in the upper half
    
    public static void main(String[] args) { // Main method execution block to test logic

        var tests = List.of( // Java 10+ var: Creates a list of edge and standard scenario test cases
            new Test(List.of(5), 5.0), // Edge case: Single number
            new Test(List.of(5, 15), 10.0), // Standard case: Two numbers, requires average calculation
            new Test(List.of(5, 15, 1, 3, 3), 3.0), // Duplicate case: Tests if duplicate numbers (3) break the frequency logic
            new Test(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), 5.5) // Sequence case
        ); // End test declarations

        System.out.println("--- STANDARD TESTS ---"); // Visual separator for standard tests
        for (var t : tests) { // Loop dynamically through each test case
            var rm = new RunningMedianTreeMap(); // New fresh instance so tests don't corrupt each other
            t.input.forEach(rm::add); // Stream API: Swiftly iterate and push all numbers into our tree structure
            boolean passed = Double.compare(rm.getMedian(), t.expected) == 0; // Safe floating-point comparison
            System.out.println((passed ? "PASS" : "FAIL") + " -> Input: " + t.input + " | Expected: " + t.expected + " | Got: " + rm.getMedian()); // Print exact formatted result
        } // End standard test loop

        System.out.println("\n--- LARGE DATA TEST ---"); // Visual separator for the high-volume load test
        var largeRm = new RunningMedianTreeMap(); // Dedicated isolated instance for heavy data processing
        long start = System.currentTimeMillis(); // Note exactly when we start the mass insertions

        IntStream.rangeClosed(1, 100_000).forEach(largeRm::add); // Stream API: Loop 100,000 times, adding continuous numbers
        double expectedLarge = 50000.5; // True mathematical median of numbers 1 to 100,000
        boolean largePassed = Double.compare(largeRm.getMedian(), expectedLarge) == 0; // Check if trees correctly balanced and found it
        long timeTaken = System.currentTimeMillis() - start; // Calculate time it took to do 100,000 O(log N) tree operations

        System.out.println((largePassed ? "PASS" : "FAIL") + " -> Large Data Input [1 to 100,000] | Expected: " + expectedLarge + " | Time: " + timeTaken + "ms"); // Print performance output proving array-shifting is bypassed
    } // End main method
    
    private void addNum(TreeMap<Integer, Integer> map, int n) { // Helper to add a number to a map
        map.merge(n, 1, Integer::sum); // Java 8+: If key exists, add 1 to frequency; if not, insert with frequency 1
    } // End addNum helper

    private void removeNum(TreeMap<Integer, Integer> map, int n) { // Helper to remove a number from a map
        map.compute(n, (key, count) -> count == 1 ? null : count - 1); // Java 8+: If count is 1, remove key entirely (null); otherwise, subtract 1
    } // End removeNum helper
    
    public void add(int n) { // Main method to process a new number from the stream
        if (lower.isEmpty() || n <= lower.lastKey()) { // If lower is empty, or the number belongs in the smaller half
            addNum(lower, n); // Insert it into the lower tree map
            lowerSize++; // Increment our manual size counter for the lower half
        } else { // Otherwise, the number is large and belongs in the upper half
            addNum(upper, n); // Insert it into the upper tree map
            upperSize++; // Increment our manual size counter for the upper half
        } // End routing condition

        if (lowerSize > upperSize + 1) { // Balance check 1: Lower half has grown too large
            int maxLower = lower.lastKey(); // Get the largest number currently in the smaller half
            removeNum(lower, maxLower); // Remove it from the lower half
            lowerSize--; // Decrement lower count
            addNum(upper, maxLower); // Move it into the upper half
            upperSize++; // Increment upper count
        } else if (upperSize > lowerSize) { // Balance check 2: Upper half is larger than lower half (we force lower to be equal or +1)
            int minUpper = upper.firstKey(); // Get the smallest number currently in the upper half
            removeNum(upper, minUpper); // Remove it from the upper half
            upperSize--; // Decrement upper count
            addNum(lower, minUpper); // Move it into the lower half
            lowerSize++; // Increment lower count
        } // End balancing block
    } // End add method
    
    public double getMedian() { // Method to retrieve the median instantly
        if (lowerSize == upperSize) { // If both halves hold the exact same amount of numbers
            return (lower.lastKey() + upper.firstKey()) / 2.0; // The median is the average of the largest small number and smallest large number
        } // End even size check
        return lower.lastKey(); // If sizes differ (odd total), our balancing ensures the extra element is always in the lower tree
    } // End getMedian method
    
    record Test(List<Integer> input, double expected) {} // Java 16+: Immutable structure to hold test cases cleanly
} // End class