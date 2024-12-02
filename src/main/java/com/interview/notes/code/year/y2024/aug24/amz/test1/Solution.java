package com.interview.notes.code.year.y2024.aug24.amz.test1;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {
    public static int getMinScore(List<Integer> watchHistory, int series1, int series2) {
        int n = watchHistory.size();
        int left = 0;
        int minScore = Integer.MAX_VALUE;
        Set<Integer> uniqueSeries = new HashSet<>();
        boolean foundSeries1 = false;
        boolean foundSeries2 = false;

        for (int right = 0; right < n; right++) {
            int currentSeries = watchHistory.get(right);
            uniqueSeries.add(currentSeries);

            if (currentSeries == series1) foundSeries1 = true;
            if (currentSeries == series2) foundSeries2 = true;

            while (foundSeries1 && foundSeries2) {
                minScore = Math.min(minScore, uniqueSeries.size());

                int leftSeries = watchHistory.get(left);
                if (leftSeries == series1 || leftSeries == series2) {
                    boolean stillExists = watchHistory.subList(left + 1, right + 1).contains(leftSeries);
                    if (!stillExists) {
                        if (leftSeries == series1) foundSeries1 = false;
                        if (leftSeries == series2) foundSeries2 = false;
                    }
                }

                uniqueSeries.remove(leftSeries);
                boolean existsElsewhere = watchHistory.subList(left + 1, right + 1).contains(leftSeries);
                if (!existsElsewhere) {
                    uniqueSeries.remove(leftSeries);
                }

                left++;
            }
        }

        return minScore == Integer.MAX_VALUE ? -1 : minScore;
    }

    public static void main(String[] args) {
        // Test Case 1
        List<Integer> watchHistory1 = Arrays.asList(1, 2, 2, 2, 5, 2);
        int series1_1 = 1;
        int series2_1 = 5;
        System.out.println(getMinScore(watchHistory1, series1_1, series2_1)); // Expected output: 3

        // Test Case 2
        List<Integer> watchHistory2 = Arrays.asList(1, 2, 3, 5, 1);
        int series1_2 = 5;
        int series2_2 = 5;
        System.out.println(getMinScore(watchHistory2, series1_2, series2_2)); // Expected output: 1

        // Test Case 3
        List<Integer> watchHistory3 = Arrays.asList(1, 3, 2, 1, 4);
        int series1_3 = 1;
        int series2_3 = 2;
        System.out.println(getMinScore(watchHistory3, series1_3, series2_3)); // Expected output: 2

        // Additional Test Case
        List<Integer> watchHistory4 = Arrays.asList(1, 2, 3, 4, 5);
        int series1_4 = 1;
        int series2_4 = 5;
        System.out.println(getMinScore(watchHistory4, series1_4, series2_4)); // Expected output: 5
    }
}
