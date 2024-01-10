package com.interview.notes.code.months.year2023.Oct23.test6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Open Instructions In New Window M
 * You’re given an array of time intervals. Each interval is an inclusive start time and end time. The array is
 * sorted by interval start time.
 * Write a function to merge the overlapping intervals. Your function should not change the input array.
 * Example:
 * . input: 1(1.5], [3,7], [4,6], (6,8], [7,9], (11. 13]]
 * . output: ([1.9], (11. 13])
 * <p>
 * <p>
 * import java.util.*;
 * /•
 * You're given an array of time intervals.
 * Each interval is an inclusive start time and end time.
 * The array is sorted by interval start time.
 * Write a function to merge the overlapping intervals.
 * Your function should not change the input array.
 */


class MergeIntervals {

    public static List<Interval> merge(final List<Interval> intervals) {
        if (intervals == null || intervals.size() <= 1) {
            return intervals;
        }

        List<Interval> result = new ArrayList<>();
        Interval currentInterval = intervals.get(0);

        for (int i = 1; i < intervals.size(); i++) {
            Interval nextInterval = intervals.get(i);

            if (currentInterval.endTime >= nextInterval.startTime) { // Overlapping
                currentInterval.endTime = Math.max(currentInterval.endTime, nextInterval.endTime);
            } else {
                result.add(currentInterval);
                currentInterval = nextInterval;
            }
        }

        result.add(currentInterval);  // Add the last interval
        return result;
    }

    /**
     * Running tests...
     * Test 1
     * ... failed
     * ... expected [[1, 9]]
     * ... actual [[1, 5], [1, 5], [1, 5], [6, 8], [6, 8]]
     * Test 2
     * ... passed
     * Test 3
     * ... passed
     * Test 4
     * ... failed
     * expected [[-1, 4], [6, 9]]
     * ... actual [[-1, 3], [-1, 3], [6, 8], [6, 8]]
     * Test 5
     * ... failed
     * ... expected [[0, 10]]
     * ... actual [[0, 10], [0, 10]]
     *
     * @param args
     */
    public static void main(String[] args) {

        List<Interval> intervals = Arrays.asList(new Interval(1, 5), new Interval(3, 7), new Interval(4, 6),
                new Interval(6, 8), new Interval(7, 9), new Interval(11, 13));

        //List<Interval> intervals = Arrays.asList(new Interval(1, 5), new Interval(3, 7), new Interval(4, 6),
        //      new Interval(6, 8), new Interval(7, 9), new Interval(11, 13));
        List<Interval> merged = merge(intervals);
        for (Interval i : merged) {
            System.out.println("[" + i.startTime + "," + i.endTime + "]");
        }
    }
}

class Interval {
    public int startTime;
    public int endTime;

    public Interval(final int startTime, final int endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Interval interval = (Interval) o;
        return startTime == interval.startTime && endTime == interval.endTime;
    }

    @Override
    public int hashCode() {
        return Objects.hash(startTime, endTime);
    }
}


