package com.interview.notes.code.test.test7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {

    public int[][] merge(int[][] intervals) {

        // Sort the intervals by their start time.
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        // Create a list to store the merged intervals.
        List<int[]> mergedIntervals = new ArrayList<>();

        // Iterate over the intervals.
        for (int i = 0; i < intervals.length; i++) {

            // If the current interval does not overlap with the previous interval,
            // add it to the list of merged intervals.
            if (mergedIntervals.isEmpty() || mergedIntervals.get(mergedIntervals.size() - 1)[1] < intervals[i][0]) {
                mergedIntervals.add(intervals[i]);
            } else {

                // Otherwise, merge the current interval with the previous interval.
                mergedIntervals.get(mergedIntervals.size() - 1)[1] = Math.max(mergedIntervals.get(mergedIntervals.size() - 1)[1], intervals[i][1]);
            }
        }

        // Convert the list of merged intervals to an array.
        int[][] mergedIntervalsArray = new int[mergedIntervals.size()][2];
        for (int i = 0; i < mergedIntervals.size(); i++) {
            mergedIntervalsArray[i] = mergedIntervals.get(i);
        }

        // Return the array of merged intervals.
        return mergedIntervalsArray;
    }
}
