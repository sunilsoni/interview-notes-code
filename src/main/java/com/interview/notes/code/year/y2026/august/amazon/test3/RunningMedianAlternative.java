package com.interview.notes.code.year.y2026.august.amazon.test3;

import java.util.ArrayList; // Needed for the dynamic array to store our stream of numbers
import java.util.Collections; // Needed for the built-in binary search utility algorithm
import java.util.List; // Needed for grouping elements in standard test cases
import java.util.stream.IntStream; // Needed to rapidly generate large datasets for stress testing

public class RunningMedianAlternative { // Class encapsulating the binary-search based median logic
    
    private final List<Integer> sortedList = new ArrayList<>(); // A dynamic array that we will forcefully keep sorted at all times
    
    public static void main(String[] args) { // Main method used strictly for running tests, avoiding external testing frameworks

        var tests = List.of( // Java 10+ var: initializes an immutable list of our standard edge and normal test cases
            new Test(List.of(5), 5.0), // Single element case
            new Test(List.of(5, 15), 10.0), // Even element count case (average of 5 and 15)
            new Test(List.of(5, 15, 1), 5.0), // Odd element count case (sorted: 1, 5, 15)
            new Test(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), 5.5) // Sequential stream test
        ); // End of test case initialization

        System.out.println("--- STANDARD TESTS ---"); // Print a header to separate test results in the console
        for (var t : tests) { // Iterate dynamically over our defined test records
            var rm = new RunningMedianAlternative(); // Create a fresh, isolated instance for each specific test
            t.input.forEach(rm::add); // Java 8 Stream API: cleanly loop through all inputs and add them to the instance
            boolean passed = Double.compare(rm.getMedian(), t.expected) == 0; // Safely evaluate doubles to prevent floating-point mismatch bugs
            System.out.println((passed ? "PASS" : "FAIL") + " -> Input: " + t.input + " | Expected: " + t.expected + " | Got: " + rm.getMedian()); // Output the exact result of the evaluation
        } // End of standard tests iteration loop

        System.out.println("\n--- LARGE DATA TEST ---"); // Print a header to denote the heavy performance test
        var largeRm = new RunningMedianAlternative(); // Dedicated instance for the high-volume data stream
        long start = System.currentTimeMillis(); // Snapshot the start time to track insertion efficiency

        IntStream.rangeClosed(1, 100_000).forEach(largeRm::add); // Stream API: feed 100,000 sequential numbers into the sorted list
        double expectedLarge = 50000.5; // Mathematically known median for a 1-to-100,000 continuous sequence
        boolean largePassed = Double.compare(largeRm.getMedian(), expectedLarge) == 0; // Verify the algorithm calculated it perfectly
        long timeTaken = System.currentTimeMillis() - start; // Snapshot end time and calculate total elapsed milliseconds

        System.out.println((largePassed ? "PASS" : "FAIL") + " -> Large Data Input [1 to 100,000] | Time: " + timeTaken + "ms"); // Output the load test result, expecting a slightly longer time than heaps due to O(N) shifts
    } // End of main method
    
    public void add(int n) { // Method to ingest a new number into our sorted list
        int index = Collections.binarySearch(sortedList, n); // O(log N) search to find where the number 'n' exists, or where it should be inserted

        if (index < 0) { // binarySearch returns a negative number if the element doesn't exist yet
            index = -(index + 1); // Mathematical formula to convert the negative result into the exact correct insertion index
        } // End of negative index conversion check

        sortedList.add(index, n); // Insert the number at the exact sorted position; pushes subsequent elements right in O(N) time
    } // End of add method
    
    public double getMedian() { // Method to retrieve the median in O(1) instant time since the list is always sorted
        int size = sortedList.size(); // Store the total count of elements currently in the list
        int mid = size / 2; // Calculate the middle index (integer division truncates decimals)

        if (size % 2 == 0) { // Check if the total number of elements is even
            return (sortedList.get(mid - 1) + sortedList.get(mid)) / 2.0; // If even, get the two middle elements, add them, and divide by 2.0 for the exact average
        } // End of even condition

        return sortedList.get(mid); // If odd, the integer division gives us the exact exact middle index, so return it directly
    } // End of getMedian method
    
    record Test(List<Integer> input, double expected) {} // Java 16+ Record feature: creates a clean, immutable data carrier for test inputs and expected outputs
} // End of class