package com.interview.notes.code.months.Oct23.test7;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class MergeIntervals {
    public static List<Interval> merge(final List<Interval> intervals) {
        // Initialize an empty list to store merged intervals.
        List<Interval> mergedList = new ArrayList<>();

        // Iterate through the sorted list of intervals.
        for (Interval currentInterval : intervals) {
            // Merge Logic.
            // If the merged list is empty or the current interval doesn't overlap
            // with the last interval in the merged list, add it to the merged list.
            if (mergedList.isEmpty() || mergedList.get(mergedList.size() - 1).endTime < currentInterval.startTime) {
                // Make a deep copy of the current interval to avoid modifying the input.
                mergedList.add(new Interval(currentInterval.startTime, currentInterval.endTime));
            } else {
                // If the current interval overlaps with the last interval in the merged list,
                // merge them by updating the end time of the last interval in the merged list.
                // No need for a deep copy here, as the last interval in the merged list is already a separate object.
                mergedList.get(mergedList.size() - 1).endTime = Math.max(mergedList.get(mergedList.size() - 1).endTime, currentInterval.endTime);
            }
        }

        // Return the merged list.
        return mergedList;
    }

    public static void main(String[] args) {
        List<Interval> intervals = new ArrayList<>();
        intervals.add(new Interval(1, 5));
        intervals.add(new Interval(3, 7));
        intervals.add(new Interval(4, 6));
        intervals.add(new Interval(6, 8));
        intervals.add(new Interval(7, 9));
        intervals.add(new Interval(11, 13));

        List<Interval> mergedIntervals = merge(intervals);
        for (Interval interval : mergedIntervals) {
            System.out.println("[" + interval.startTime + ", " + interval.endTime + "]");
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
