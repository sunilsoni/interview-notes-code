package com.interview.notes.code.year.y2026.feb.common.test11;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class DistinctRangeFinder {

    // Main method to extract distinct, non-overlapping segments
    public static List<Range> getDistinctRanges(List<Range> ranges) {
        // Handle empty or null input immediately to avoid errors
        if (ranges == null || ranges.isEmpty()) return new ArrayList<>();

        // TreeSet automatically sorts the numbers and removes any duplicates.
        var boundaries = new TreeSet<Integer>();

        // Loop through all provided ranges to find where "cuts" should happen.
        for (var r : ranges) {
            // Add the start point of the range as a cut point.
            boundaries.add(r.min());
            // Add the number strictly AFTER the end as a cut point (the boundary).
            boundaries.add(r.max() + 1);
        }

        // Convert the unique, sorted boundaries into a list so we can access them by index.
        var points = new ArrayList<>(boundaries);
        // Prepare the final list to hold our distinct segments.
        var distinctSegments = new ArrayList<Range>();

        // Loop through the boundary points to create segments between them.
        for (int i = 0; i < points.size() - 1; i++) {
            // Start of candidate segment is the current point.
            int start = points.get(i);
            // End of candidate segment is the next point minus 1 (since the next point is an exclusive boundary).
            int end = points.get(i + 1) - 1;

            // Create the temporary candidate range.
            var candidate = new Range(start, end);

            // Check if this newly sliced segment actually belongs to ANY of our original input ranges.
            boolean isCovered = ranges.stream()
                    // If candidate's min and max fit inside any original range, it's valid.
                    .anyMatch(r -> candidate.min() >= r.min() && candidate.max() <= r.max());

            // If it is covered (not a gap between ranges), add it to our final results.
            if (isCovered) {
                distinctSegments.add(candidate);
            }
        }

        // Return the perfectly fractured, distinct ranges.
        return distinctSegments;
    }

    // Simple test runner to compare expected vs actual outputs
    private static void runTest(String testName, List<Range> input, String expected) {
        var actual = getDistinctRanges(input).toString();
        if (actual.equals(expected)) {
            System.out.println("PASS - " + testName);
        } else {
            System.out.println("FAIL - " + testName + "\n  Expected: " + expected + "\n  Actual:   " + actual);
        }
    }

    // Simple main method to run all tests without JUnit
    public static void main(String[] args) {
        // Test 1: Contiguous ranges should stay separate (Supplier A vs Supplier B)
        runTest("Contiguous", List.of(new Range(1, 5), new Range(6, 10)), "[Range(1, 5), Range(6, 10)]");

        // Test 2: Overlapping ranges should fracture at the new rule's entry point
        runTest("Overlap", List.of(new Range(1, 10), new Range(5, 10)), "[Range(1, 4), Range(5, 10)]");

        // Test 3: One large range, one small range entirely inside it
        runTest("Swallowed", List.of(new Range(1, 10), new Range(4, 7)), "[Range(1, 3), Range(4, 7), Range(8, 10)]");

        // Test 4: Ranges with a gap in between (gap should be ignored)
        runTest("Gap", List.of(new Range(1, 5), new Range(10, 15)), "[Range(1, 5), Range(10, 15)]");

        // Test 5: Multiple overlaps slicing into many pieces
        runTest("Multi-Overlap", List.of(new Range(1, 10), new Range(5, 15), new Range(8, 20)), "[Range(1, 4), Range(5, 7), Range(8, 10), Range(11, 15), Range(16, 20)]");
    }

    // Record for compact, immutable Range definition
    public record Range(int min, int max) {
        @Override
        public String toString() {
            return "Range(" + min + ", " + max + ")";
        }
    }
}