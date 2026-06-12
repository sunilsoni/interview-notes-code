package com.interview.notes.code.year.y2026.june.common.test1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MergeIntervals { // Main class

    static List<Interval> merge(List<Interval> intervals) { // Method to merge intervals

        if (intervals == null || intervals.isEmpty()) return List.of(); // Handle null or empty input safely

        var sorted = intervals.stream() // Create stream from intervals
                .sorted(Comparator.comparingInt(Interval::start)) // Sort intervals by start value
                .toList(); // Convert sorted stream back to list

        var result = new ArrayList<Interval>(); // Store final merged intervals

        var current = sorted.getFirst(); // Pick first interval as current interval

        for (int i = 1; i < sorted.size(); i++) { // Loop from second interval

            var next = sorted.get(i); // Get next interval

            if (next.start() <= current.end()) { // Check if current and next overlap

                current = new Interval( // Create updated merged interval
                        current.start(), // Keep current start
                        Math.max(current.end(), next.end()) // Take bigger end value
                ); // End merged interval creation

            } else { // If intervals do not overlap

                result.add(current); // Add current interval to result

                current = next; // Move current pointer to next interval
            }
        }

        result.add(current); // Add last remaining interval

        return result; // Return final merged list
    }

    static void test(String name, List<Interval> input, List<Interval> expected) { // Simple test method

        var actual = merge(input); // Run merge method

        var passed = actual.equals(expected); // Compare actual and expected result

        System.out.println(name + " : " + (passed ? "PASS" : "FAIL")); // Print test status

        if (!passed) { // If test failed

            System.out.println("Expected: " + expected); // Print expected output

            System.out.println("Actual  : " + actual); // Print actual output
        }
    }

    public static void main(String[] args) { // Main method to run all tests

        test( // Test normal overlapping intervals
                "Normal overlap",
                List.of(new Interval(1, 3), new Interval(2, 6), new Interval(8, 10), new Interval(15, 18)),
                List.of(new Interval(1, 6), new Interval(8, 10), new Interval(15, 18))
        );

        test( // Test touching intervals
                "Touching intervals",
                List.of(new Interval(1, 4), new Interval(4, 5)),
                List.of(new Interval(1, 5))
        );

        test( // Test no overlap
                "No overlap",
                List.of(new Interval(1, 2), new Interval(3, 4), new Interval(5, 6)),
                List.of(new Interval(1, 2), new Interval(3, 4), new Interval(5, 6))
        );

        test( // Test unsorted input
                "Unsorted input",
                List.of(new Interval(8, 10), new Interval(1, 3), new Interval(2, 6)),
                List.of(new Interval(1, 6), new Interval(8, 10))
        );

        test( // Test single interval
                "Single interval",
                List.of(new Interval(1, 5)),
                List.of(new Interval(1, 5))
        );

        test( // Test empty input
                "Empty input",
                List.of(),
                List.of()
        );

        test( // Test fully nested intervals
                "Nested intervals",
                List.of(new Interval(1, 10), new Interval(2, 3), new Interval(4, 5)),
                List.of(new Interval(1, 10))
        );

        var largeInput = new ArrayList<Interval>(); // Create large input list

        for (int i = 0; i < 100_000; i++) { // Create many intervals for large test

            largeInput.add(new Interval(i, i + 1)); // Add touching intervals
        }

        test( // Test large input
                "Large input",
                largeInput,
                List.of(new Interval(0, 100_000))
        );
    }

    record Interval(int start, int end) {} // Record to store start and end of interval
}