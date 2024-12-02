package com.interview.notes.code.year.y2023.dec23.test2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IntervalMerger {

    // Example execution
    public static void main(String[] args) {
        IntervalMerger merger = new IntervalMerger();
        int[][] intervals = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        int[][] mergedIntervals = merger.merge(intervals);

        System.out.println("Merged Intervals: " + Arrays.deepToString(mergedIntervals));
    }

    public int[][] merge(int[][] intervals) {
        // Sort intervals based on the start time
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        // List to store merged intervals
        List<int[]> merged = new ArrayList<>();

        for (int[] interval : intervals) {
            // If the list of merged intervals is empty or if the current
            // interval does not overlap with the previous, simply append it.
            if (merged.isEmpty() || merged.get(merged.size() - 1)[1] < interval[0]) {
                merged.add(interval);
            } else {
                // Otherwise, there is overlap, so we merge the current and previous intervals.
                merged.get(merged.size() - 1)[1] = Math.max(merged.get(merged.size() - 1)[1], interval[1]);
            }
        }

        // Convert merged list to array and return
        return merged.toArray(new int[merged.size()][]);
    }
}
