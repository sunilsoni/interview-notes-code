package com.interview.notes.code.year.y2024.nov24.amazon;

import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

interface myInterface {
    void addInterval(int from, int to); // Method to add an interval

    int getTotalCOveredLength(); // Method to get the total covered length
}

public class IntervalCoverage implements myInterface {
    private TreeMap<Integer, Integer> intervalMap;

    // Constructor
    public IntervalCoverage() {
        intervalMap = new TreeMap<>();
    }

    public static void main(String[] args) {
        // Test cases to verify the functionality of the implementation
        runTest("Empty List Test", new int[][]{}, 0); // Test with no intervals

        runTest("Single Interval Test",
                new int[][]{{1, 5}},
                4); // Test with a single interval

        runTest("Non-overlapping Intervals Test",
                new int[][]{{1, 3}, {4, 6}, {8, 10}},
                6); // Test with non-overlapping intervals

        runTest("Overlapping Intervals Test",
                new int[][]{{1, 4}, {2, 6}, {3, 5}},
                5); // Test with overlapping intervals

        runTest("Nested Intervals Test",
                new int[][]{{1, 6}, {2, 4}, {3, 5}},
                5); // Test with nested intervals

        runTest("Large Numbers Test",
                new int[][]{{1000000, 2000000}, {1500000, 2500000}},
                1500000); // Test with large numbers

        // Large data test to evaluate performance
        testLargeDataSet();
    }

    private static void runTest(String testName, int[][] intervals, int expectedResult) {
        IntervalCoverage ic = new IntervalCoverage();
        for (int[] interval : intervals) {
            ic.addInterval(interval[0], interval[1]); // Add each interval to the IntervalCoverage object
        }

        int result = ic.getTotalCOveredLength(); // Calculate the total covered length
        boolean passed = result == expectedResult;

        System.out.println(testName + ": " + (passed ? "PASS" : "FAIL"));
        if (!passed) {
            System.out.println("Expected: " + expectedResult + ", Got: " + result);
        }
    }

    private static void testLargeDataSet() {
        IntervalCoverage ic = new IntervalCoverage();
        Random rand = new Random();
        int count = 100000; // Number of intervals to add

        System.out.println("\nTesting with " + count + " random intervals...");
        long startTime = System.currentTimeMillis();

        // Add random intervals to the IntervalCoverage object
        for (int i = 0; i < count; i++) {
            int start = rand.nextInt(1000000);
            int length = rand.nextInt(10000) + 1;
            ic.addInterval(start, start + length);
        }

        // Calculate the total covered length and print the results
        int result = ic.getTotalCOveredLength();
        long endTime = System.currentTimeMillis();

        System.out.println("Large Data Test completed in " + (endTime - startTime) + "ms");
        System.out.println("Result: " + result);
    }

    @Override
    public void addInterval(int from, int to) {
        // Add an interval if 'from' is less than or equal to 'to'
        if (from > to) {
            return; // Invalid interval
        }

        // Find overlapping intervals in the TreeMap and merge them
        Map.Entry<Integer, Integer> lower = intervalMap.floorEntry(from);
        Map.Entry<Integer, Integer> higher = intervalMap.ceilingEntry(from);

        // Adjust the boundaries of the new interval if necessary
        if (lower != null && lower.getValue() >= from) {
            from = Math.min(from, lower.getKey());
            to = Math.max(to, lower.getValue());
            intervalMap.remove(lower.getKey());
        }

        while (higher != null && higher.getKey() <= to) {
            to = Math.max(to, higher.getValue());
            intervalMap.remove(higher.getKey());
            higher = intervalMap.ceilingEntry(from);
        }

        // Add the merged interval back to the TreeMap
        intervalMap.put(from, to);
    }

    @Override
    public int getTotalCOveredLength() {
        if (intervalMap.isEmpty()) {
            return 0; // No intervals, so total covered length is 0
        }

        int totalLength = 0;

        // Iterate through the merged intervals and calculate the total covered length
        for (Map.Entry<Integer, Integer> entry : intervalMap.entrySet()) {
            totalLength += (entry.getValue() - entry.getKey());
        }

        return totalLength;
    }

    // Represents an interval with start and end points
    private static class Interval {
        int start, end;

        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
