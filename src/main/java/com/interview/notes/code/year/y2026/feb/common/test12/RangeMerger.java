package com.interview.notes.code.year.y2026.feb.common.test12;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class RangeMerger {

    // This is our main worker method that takes a list of raw ranges and returns the clean, consolidated list.
    public static List<Range> consolidateRanges(List<Range> ranges) {
        // First, we handle the edge case: if the list is null or has 0/1 items, there is nothing to merge.
        if (ranges == null || ranges.size() <= 1) {
            // We just return the original list back to the caller to save processing time.
            return ranges;
        }

        // We use the Java 8 Stream API to sort the list. We stream the data, sort it by the 'min' value, and collect it back into a List.
        var sortedRanges = ranges.stream()
                // Comparator.comparingInt efficiently extracts the 'min' value to figure out the sorting order.
                .sorted(Comparator.comparingInt(Range::min))
                // Collects the sorted stream elements back into a brand new list so we don't mutate the original input.
                .collect(Collectors.toList());

        // We create an empty ArrayList that will eventually hold our final, merged ranges.
        var mergedResult = new ArrayList<Range>();

        // We grab the very first range from our sorted list and set it as our starting point.
        var current = sortedRanges.get(0);

        // We start a loop from the second item (index 1) all the way to the end of the sorted list.
        for (int i = 1; i < sortedRanges.size(); i++) {
            // We fetch the next range in line to compare it against our 'current' one.
            var next = sortedRanges.get(i);

            // Core logic: Check if the 'next' range starts before or exactly one number after the 'current' range ends. Adding 1 handles contiguous ranges like 1-5 and 6-10.
            if (next.min() <= current.max() + 1) {
                // Since they overlap or touch, we create a new 'current' range. The min stays the same, but the max becomes whichever max is larger.
                current = new Range(current.min(), Math.max(current.max(), next.max()));
            } else {
                // If they don't overlap, it means we found a gap. We add the fully processed 'current' range to our final result list.
                mergedResult.add(current);
                // We then reset our 'current' tracker to be this new, disconnected range, and continue the loop.
                current = next;
            }
        }

        // After the loop finishes, the last 'current' range we were tracking hasn't been added yet, so we add it to the final list here.
        mergedResult.add(current);

        // Finally, we return the completely consolidated list back to whoever called the method.
        return mergedResult;
    }

    // A simple helper method to run tests and visually print PASS or FAIL to the console.
    private static void runTest(String testName, List<Range> input, String expectedOutputStr) {
        // We call our consolidation method with the test input.
        List<Range> actualOutput = consolidateRanges(input);
        // We convert the actual output list to a string so we can easily compare it against our expected result string.
        String actualStr = actualOutput.toString();

        // We check if the actual string matches what we expected.
        if (actualStr.equals(expectedOutputStr)) {
            // If they match, we print PASS in green (using basic console output).
            System.out.println("PASS - " + testName);
        } else {
            // If they don't match, we print FAIL and show what went wrong for debugging.
            System.out.println("FAIL - " + testName + " | Expected: " + expectedOutputStr + " | Got: " + actualStr);
        }
    }

    // The main method where the program execution starts. We will define all our test cases here.
    public static void main(String[] args) {

        // Test Case 1 from the prompt: Contiguous ranges that should merge into one.
        runTest("Example 1", List.of(new Range(1, 5), new Range(6, 10)), "[Range(1, 10)]");

        // Test Case 2 from the prompt: Ranges with a large gap between them that should NOT merge.
        runTest("Example 2", List.of(new Range(1, 5), new Range(10, 15)), "[Range(1, 5), Range(10, 15)]");

        // Test Case 3 from the prompt: Ranges that share a number (5-10 and 1-10) should merge into the larger one.
        runTest("Example 3", List.of(new Range(1, 10), new Range(5, 10)), "[Range(1, 10)]");

        // Test Case 4 from the prompt: Ranges that overlap perfectly in the middle.
        runTest("Example 4", List.of(new Range(1, 10), new Range(5, 15)), "[Range(1, 15)]");

        // Test Case 5 from the prompt: Three ranges where the first two merge, but the third stays separate.
        runTest("Example 5", List.of(new Range(1, 10), new Range(5, 15), new Range(20, 25)), "[Range(1, 15), Range(20, 25)]");

        // Test Case 6 from the prompt: One large range totally swallowing a smaller range.
        runTest("Example 6", List.of(new Range(1, 10), new Range(1, 5)), "[Range(1, 10)]");

        // Custom Test Case: Unsorted input to verify that our sorting logic at the beginning of the method works correctly.
        runTest("Unsorted Input", List.of(new Range(15, 20), new Range(1, 5), new Range(4, 10)), "[Range(1, 10), Range(15, 20)]");

        // Generating a very large dataset to test performance and ensure we don't run into memory or stack overflow issues.
        var largeInput = new ArrayList<Range>();
        // Looping 100,000 times to create overlapping ranges (0-2, 1-3, 2-4, etc.)
        for (int i = 0; i < 100000; i++) {
            // Adding each small range to our large input list.
            largeInput.add(new Range(i, i + 2));
        }
        // Since every range overlaps the next, all 100,000 items should collapse into one giant range from 0 to 100001.
        runTest("Large Data Test (100k items)", largeInput, "[Range(0, 100001)]");
    }

    // Using a Java Record here. It's a clean way to create an immutable data carrier without writing getters, setters, or constructors manually.
    public record Range(int min, int max) {
        // This is a compact constructor used to validate data right when the object is created.
        public Range {
            // We check if someone accidentally passed a min that is bigger than the max.
            if (min > max) {
                // If they did, we swap them using a temporary variable so the range is always valid.
                int temp = min;
                // Assign max to min.
                min = max;
                // Assign the original min (stored in temp) to max.
                max = temp;
            }
        }

        // Overriding toString to make our test output match the exact format requested in the problem statement.
        @Override
        public String toString() {
            // Returns the formatted string, e.g., "Range(1, 5)".
            return "Range(" + min + ", " + max + ")";
        }
    }
}