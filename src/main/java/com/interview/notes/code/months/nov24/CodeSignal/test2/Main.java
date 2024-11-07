package com.interview.notes.code.months.nov24.CodeSignal.test2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    /**
     * Method to find the coordinate with the highest number of lamp illuminations.
     * If multiple coordinates have the same highest illumination, the smallest coordinate is returned.
     *
     * @param lamps 2D array where each sub-array contains [coordinate, radius]
     * @return the coordinate with the highest number of illuminations
     */
    public static int findMaxIlluminatedPoint(int[][] lamps) {
        // List to store all events
        List<Event> events = new ArrayList<>();

        // Create start and end events for each lamp
        for (int[] lamp : lamps) {
            int position = lamp[0];
            int radius = lamp[1];
            events.add(new Event(position - radius, 1));       // Start of illumination
            events.add(new Event(position + radius + 1, -1));  // End of illumination
        }

        // Sort events by position.
        // If positions are equal, start events come before end events.
        Collections.sort(events, (a, b) -> {
            if (a.position != b.position) {
                return Long.compare(a.position, b.position);
            }
            return Integer.compare(b.type, a.type);
        });

        int maxCount = 0;
        long result = Long.MAX_VALUE;
        int currentCount = 0;

        // Sweep through the events
        for (Event event : events) {
            currentCount += event.type;

            // Update maximum count and result
            if (currentCount > maxCount) {
                maxCount = currentCount;
                result = event.position;
            }
        }

        // Since end events are at position +1, the actual point is position -1
        // But since we sorted end events as position +1, and processed start events first,
        // the 'result' already points to the first position where the max count occurs.
        return (int) result;
    }

    /**
     * Main method to execute test cases.
     */
    public static void main(String[] args) {
        // Define test cases
        List<TestCase> testCases = new ArrayList<>();

        // Example 1
        testCases.add(new TestCase(
                new int[][]{{-2, 3}, {2, 3}, {2, 1}},
                1,
                "Example 1"
        ));

        // Example 2
        testCases.add(new TestCase(
                new int[][]{{-2, 1}, {2, 1}},
                -3,
                "Example 2"
        ));

        // Additional Test Case 1: Single Lamp
        testCases.add(new TestCase(
                new int[][]{{0, 5}},
                -5,
                "Single Lamp"
        ));

        // Additional Test Case 2: Overlapping at multiple points
        testCases.add(new TestCase(
                new int[][]{{1, 2}, {3, 2}, {5, 2}},
                3,
                "Multiple Overlaps"
        ));

        // Additional Test Case 3: All lamps cover the same point
        testCases.add(new TestCase(
                new int[][]{{0, 10}, {5, 10}, {10, 10}},
                5,
                "All Cover Same Point"
        ));

        // Additional Test Case 4: Negative coordinates
        testCases.add(new TestCase(
                new int[][]{{-10, 5}, {-5, 5}, {0, 5}},
                -5,
                "Negative Coordinates"
        ));

        // Additional Test Case 5: Large Data Input
        testCases.add(new TestCase(
                generateLargeTestCase(100000),
                0,
                "Large Data Input"
        ));

        // Execute all test cases
        int passed = 0;
        for (TestCase testCase : testCases) {
            try {
                int output = findMaxIlluminatedPoint(testCase.lamps);
                if (output == testCase.expected) {
                    System.out.println(testCase.name + ": PASS");
                    passed++;
                } else {
                    System.out.println(testCase.name + ": FAIL (Expected " + testCase.expected + ", Got " + output + ")");
                }
            } catch (Exception e) {
                System.out.println(testCase.name + ": FAIL (Exception: " + e.getMessage() + ")");
            }
        }

        System.out.println("Passed " + passed + " out of " + testCases.size() + " test cases.");
    }

    /**
     * Helper method to generate a large test case.
     * All lamps are centered at 0 with radius 100,000.
     * Expected result is 0.
     *
     * @param n Number of lamps
     * @return 2D array representing lamps
     */
    private static int[][] generateLargeTestCase(int n) {
        int[][] lamps = new int[n][2];
        for (int i = 0; i < n; i++) {
            lamps[i][0] = 0;
            lamps[i][1] = 100000;
        }
        return lamps;
    }

    /**
     * Event class to represent start and end of illumination.
     */
    static class Event {
        long position;
        int type; // 1 for start, -1 for end

        Event(long position, int type) {
            this.position = position;
            this.type = type;
        }
    }

    /**
     * Inner class to represent a test case.
     */
    static class TestCase {
        int[][] lamps;
        int expected;
        String name;

        TestCase(int[][] lamps, int expected, String name) {
            this.lamps = lamps;
            this.expected = expected;
            this.name = name;
        }
    }
}
