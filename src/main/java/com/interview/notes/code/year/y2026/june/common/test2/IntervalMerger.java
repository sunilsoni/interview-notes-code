package com.interview.notes.code.year.y2026.june.common.test2;

import java.util.ArrayList; // Imports the ArrayList class needed to build our dynamic list of merged results
import java.util.Comparator; // Imports Comparator to define how we want to sort our intervals
import java.util.List; // Imports the List interface for standard collection handling
import java.util.stream.IntStream; // Imports IntStream to easily generate large sets of data for stress testing

public class IntervalMerger { // Defines the main class that encapsulates the interval logic and test runner
    
    public static List<Interval> merge(List<Interval> intervals) { // Defines the static method that takes an unmerged list and returns a cleanly merged list
        if (intervals == null || intervals.isEmpty()) { // Checks if the input is missing or empty to prevent NullPointerExceptions and unnecessary processing
            return List.of(); // Returns an immutable empty list immediately since there is absolutely nothing to merge
        } // Closes the if-block for the empty check edge case

        var sorted = intervals.stream() // Converts the input list into a Stream API pipeline to apply functional sorting logic easily
            .sorted(Comparator.comparingInt(Interval::start)) // Sorts all intervals in ascending order based purely on their 'start' property
            .toList(); // Collects the sorted stream back into an unmodifiable List object natively

        var merged = new ArrayList<Interval>(); // Initializes an empty mutable ArrayList to store our non-overlapping merged intervals one by one
        var current = sorted.getFirst(); // Fetches the very first interval from our sorted list (using Java 21 feature) to act as our baseline tracker
        merged.add(current); // Adds this baseline tracker directly into our results list so we have a reference point to update

        for (var next : sorted) { // Begins an enhanced for-loop to iterate through every single interval in our newly sorted list
            if (next.start() <= current.end()) { // Checks if the looped interval overlaps by seeing if its start touches or precedes the tracker's end
                current = new Interval(current.start(), Math.max(current.end(), next.end())); // Creates a new merged interval maintaining the start, but expanding the end to the maximum reach of both
                merged.set(merged.size() - 1, current); // Replaces the last stored interval in our results list with this newly expanded merged interval
            } else { // Triggers if the looped interval starts strictly after our tracked interval completely finishes (no overlap at all)
                current = next; // Reassigns our baseline tracker to be this brand new, disconnected interval
                merged.add(current); // Appends this new disconnected interval securely to the end of our results list
            } // Closes the else-block handling non-overlapping intervals
        } // Closes the for-loop after all intervals have been systematically checked

        return merged; // Returns the finalized list containing strictly non-overlapping, fully merged intervals
    } // Closes the merge method
    
    public static void main(String[] args) { // Defines the standard application entry point used here to execute our custom testing framework
        runTest("Empty List", List.of(), List.of()); // Tests the edge case where absolutely no intervals are provided

        runTest("Standard Overlap", // Initiates a test for standard overlapping pairs
            List.of(new Interval(1, 3), new Interval(2, 6), new Interval(8, 10), new Interval(15, 18)), // Provides the input list of partially overlapping and separated intervals
            List.of(new Interval(1, 6), new Interval(8, 10), new Interval(15, 18))); // Provides the expected output where 1-3 and 2-6 merge into 1-6

        runTest("Fully Contained", // Initiates a test where one interval is completely swallowed by another
            List.of(new Interval(1, 4), new Interval(2, 3)), // Provides an input where 2-3 is completely inside 1-4
            List.of(new Interval(1, 4))); // Provides the expected output where only 1-4 remains

        runTest("Unsorted Reverse", // Initiates a test to ensure sorting mechanism works as intended
            List.of(new Interval(5, 6), new Interval(1, 4)), // Provides intervals out of sequence to force the algorithm to sort them first
            List.of(new Interval(1, 4), new Interval(5, 6))); // Provides the expected sorted and unmerged output

        runTest("Adjoining Edges", // Initiates a test where the end of one exactly matches the start of another
            List.of(new Interval(1, 2), new Interval(2, 3)), // Provides adjoining intervals sharing the number 2
            List.of(new Interval(1, 3))); // Provides expected output proving that adjacent edges successfully merge

        runLargeDataTest(); // Triggers a separate custom method strictly designed to test performance and memory on massive datasets
    } // Closes the main method

    private static void runTest(String testName, List<Interval> input, List<Interval> expected) { // Defines a helper method to execute a test case, compare results, and print formatting
        var result = merge(input); // Calls our core merge method with the provided test input and captures the calculated output
        if (result.equals(expected)) { // Checks if our calculated output perfectly matches the explicitly expected output
            System.out.println("PASS: " + testName); // Prints a success message to the console if the test passes
        } else { // Triggers if the calculated result diverges from the expected result
            System.out.println("FAIL: " + testName + " | Expected: " + expected + " | Got: " + result); // Prints a failure message outlining exactly what went wrong for debugging
        } // Closes the else-block
    } // Closes the runTest helper method
    
    private static void runLargeDataTest() { // Defines a method to simulate a high-load scenario to guarantee performance scaling
        var largeInput = IntStream.range(0, 100000) // Uses IntStream to swiftly generate a sequence of one hundred thousand integers
            .mapToObj(i -> new Interval(i, i + 2)) // Maps every single integer into a tiny overlapping interval spanning two units
            .toList(); // Collects this massive stream into a physical list object in memory
        var expected = List.of(new Interval(0, 100001)); // Defines the expected output: one single massive interval spanning from 0 to 100001

        long startTime = System.currentTimeMillis(); // Captures the exact system time in milliseconds right before execution begins
        var result = merge(largeInput); // Executes the merge logic on our massive 100,000 item list
        long endTime = System.currentTimeMillis(); // Captures the exact system time right after execution finishes

        if (result.equals(expected)) { // Checks if the massive list successfully collapsed into the single expected interval
            System.out.println("PASS: Large Data Test (100k items) in " + (endTime - startTime) + "ms"); // Prints a success message along with the execution time to confirm efficiency
        } else { // Triggers if the large data merge fails to produce the singular massive interval
            System.out.println("FAIL: Large Data Test"); // Prints a failure message to the console
        } // Closes the else-block
    } // Closes the runLargeDataTest method

    public record Interval(int start, int end) {} // Uses Java Record to create an immutable data class for intervals, removing boilerplate getters/setters code entirely
} // Closes the IntervalMerger class entirely