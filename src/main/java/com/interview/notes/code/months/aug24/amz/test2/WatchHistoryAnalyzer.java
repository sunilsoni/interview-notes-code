package com.interview.notes.code.months.aug24.amz.test2;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WatchHistoryAnalyzer {

    public static void main(String[] args) {
        // Test cases
        System.out.println(findMinWatchScore(Arrays.asList(1, 2, 2, 2, 5, 2), 1, 5)); // Expected: 3
        System.out.println(findMinWatchScore(Arrays.asList(1, 2, 3, 5, 1), 5, 5)); // Expected: 1
        System.out.println(findMinWatchScore(Arrays.asList(1, 3, 2, 1, 4), 1, 2)); // Expected: 3

        // My additional test case
        System.out.println(findMinWatchScore(Arrays.asList(1, 2, 3, 4, 5, 1, 2), 1, 5)); // Expected: 5
    }

    // Core method to find minimum watch score
    public static int findMinWatchScore(List<Integer> watchHistory, int series1, int series2) {
        int n = watchHistory.size();
        int left = 0;
        int minScore = Integer.MAX_VALUE;
        Set<Integer> uniqueSeries = new HashSet<>();
        boolean foundSeries1 = false;
        boolean foundSeries2 = false;

        // My comment: Using a sliding window approach to check contiguous periods
        for (int right = 0; right < n; right++) {
            int currentSeries = watchHistory.get(right);
            uniqueSeries.add(currentSeries);

            if (currentSeries == series1) foundSeries1 = true;
            if (currentSeries == series2) foundSeries2 = true;

            // My comment: Shrink the window if both series are found
            while (foundSeries1 && foundSeries2) {
                minScore = Math.min(minScore, uniqueSeries.size());

                int leftSeries = watchHistory.get(left);
                if (leftSeries == series1 || leftSeries == series2) {
                    if (!watchHistory.subList(left + 1, right + 1).contains(leftSeries)) {
                        if (leftSeries == series1) foundSeries1 = false;
                        else foundSeries2 = false;
                    }
                }

                // My comment: Remove the left series if it's not in the remaining window
                if (!watchHistory.subList(left + 1, right + 1).contains(leftSeries)) {
                    uniqueSeries.remove(leftSeries);
                }

                left++;
            }
        }

        // My comment: Return -1 if no valid window found
        return minScore == Integer.MAX_VALUE ? -1 : minScore;
    }
}
