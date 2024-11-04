package com.interview.notes.code.months.nov24.test3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

interface myInterface {
    void addInterval(int from, int to);

    int getTotalCOveredLength();
}

public class IntervalCoverage implements myInterface {
    private List<Interval> intervals;

    // Constructor
    public IntervalCoverage() {
        intervals = new ArrayList<>();
    }

    public static void main(String[] args) {
        // Test cases
        runTest("Empty List Test", new int[][]{}, 0);

        runTest("Single Interval Test",
                new int[][]{{1, 5}},
                4);

        runTest("Non-overlapping Intervals Test",
                new int[][]{{1, 3}, {4, 6}, {8, 10}},
                6);

        runTest("Overlapping Intervals Test",
                new int[][]{{1, 4}, {2, 6}, {3, 5}},
                5);

        runTest("Nested Intervals Test",
                new int[][]{{1, 6}, {2, 4}, {3, 5}},
                5);

        runTest("Large Numbers Test",
                new int[][]{{1000000, 2000000}, {1500000, 2500000}},
                1500000);

        // Large data test
        testLargeDataSet();
    }

    private static void runTest(String testName, int[][] intervals, int expectedResult) {
        IntervalCoverage ic = new IntervalCoverage();
        for (int[] interval : intervals) {
            ic.addInterval(interval[0], interval[1]);
        }

        int result = ic.getTotalCOveredLength();
        boolean passed = result == expectedResult;

        System.out.println(testName + ": " + (passed ? "PASS" : "FAIL"));
        if (!passed) {
            System.out.println("Expected: " + expectedResult + ", Got: " + result);
        }
    }

    private static void testLargeDataSet() {
        IntervalCoverage ic = new IntervalCoverage();
        Random rand = new Random();
        int count = 100000;

        System.out.println("\nTesting with " + count + " random intervals...");
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < count; i++) {
            int start = rand.nextInt(1000000);
            int length = rand.nextInt(10000) + 1;
            ic.addInterval(start, start + length);
        }

        int result = ic.getTotalCOveredLength();
        long endTime = System.currentTimeMillis();

        System.out.println("Large Data Test completed in " + (endTime - startTime) + "ms");
        System.out.println("Result: " + result);
    }

    @Override
    public void addInterval(int from, int to) {
        if (from > to) {
            return; // Invalid interval
        }
        intervals.add(new Interval(from, to));
    }

    @Override
    public int getTotalCOveredLength() {
        if (intervals.isEmpty()) {
            return 0;
        }

        // Sort intervals by start time
        Collections.sort(intervals, (a, b) -> a.start - b.start);

        int totalLength = 0;
        int currentStart = intervals.get(0).start;
        int currentEnd = intervals.get(0).end;

        // Merge overlapping intervals
        for (int i = 1; i < intervals.size(); i++) {
            Interval current = intervals.get(i);

            if (current.start <= currentEnd) {
                // Overlapping intervals - extend the end if necessary
                currentEnd = Math.max(currentEnd, current.end);
            } else {
                // Non-overlapping interval - add previous interval length
                totalLength += (currentEnd - currentStart);
                currentStart = current.start;
                currentEnd = current.end;
            }
        }

        // Add the last interval
        totalLength += (currentEnd - currentStart);

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