package com.interview.notes.code.year.y2024.aug24.amz.test2;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 1. Code Question 1
 * Data analysts at Amazon are studying the trends in movies and shows popular on Prime Video in order to enhance the user experience.
 * They have identified the best critic-rated and the best audience-rated web series, represented by integer IDs series 1, and series2. They also define the watch score of a contiguous period of some days as the number of distinct series watched by a viewer during that period.
 * Given an array watch_history, of size n, that represents the web series watched by a viewer over a period of n days, and two integers, series1 and series2, report the minimum watch score of a contiguous period of days in which both series 1 and series2 have been viewed at least once. If series 1 and series2 are the same value, one occurrence during the period is sufficient.
 * Example
 * n = 5
 * series 1 = 1
 * series2 = 2
 * watch_history = [1, 3, 2, 1, 4]
 * The contiguous periods of days in which series and series2 have been viewed at least once are:-
 * <p>
 * <p>
 * <p>
 * Period of days
 * (Subarray of watch _history)
 * Watch Score
 * (Distinct series watched)
 * [1, 3, 2]
 * 3
 * [3, 2, 1]
 * 3
 * [2, 1]
 * 2
 * [1, 3, 2, 1]
 * 3
 * [1, 3, 2, 1, 4]
 * 4
 * Return the minimum watch score, 2.
 * Function Description
 * Complete the function getMinScore in the editor below.
 * getMinScore has the following parameters:
 * int watch_historyn]: the watch history of a viewer over n days int series 1: ID of the best critic-rated web series int series2: ID of the best audience-rated web series
 * Returns
 * least once
 * int: the minimum watch score of a contiguous period of days in which series1 and series2 have been viewed at
 * Constraints
 * ・Isnsios
 * • 1 s watch_historyfi] $ 10°, where Osi<n
 * • 1 ≤ series1, series2 ≤ 109 , series and series2 are not necessarily distinct.
 * • There will be at least one occurrence of both series 1 and series2 in watch_history.
 * <p>
 * <p>
 * Input Format For Custom Testing
 * The first line contains an integer, n, the number of elements in watch_history Each of the next n contains an integer, watch_history.
 * The next line contains an integer, series 1.
 * The next line contains an integer, series2.
 * • Sample Case 0
 * Sample Input For Custom Testing
 * STDIN
 * FUNCTION
 * 6
 * 1
 * 2
 * 2
 * 2
 * 5
 * 2
 * 1
 * 5
 * →
 * →
 * →
 * Sample Output
 * 3
 * watch_history[] size n = 6
 * watch_history = [1, 2, 2, 2, 5, 2]
 * series1 = 1
 * series2 = 5
 * Explanation
 * Given n = 6, watch
 * _history = [1, 2, 2, 2, 5, 2], series1= 1, and series2= 5.
 * There is only one period in which both series 1 and series? have been watched, i.e., the subarray [1, 2, 2, 2, 5]. The watch score of this period is 3, which is the minimum possible.
 * <p>
 * <p>
 * • Sample Case 1
 * Sample Input For Custom Testing
 * STDIN
 * <p>
 * FUNCTION
 * <p>
 * 5
 * <p>
 * watch_history(] size n = 5
 * 1
 * <p>
 * watch_history = [1, 2, 3, 5, 1)
 * 2
 * <p>
 * <p>
 * 3
 * <p>
 * <p>
 * 5
 * <p>
 * <p>
 * 1
 * <p>
 * <p>
 * 5
 * series1 = 5
 * 5
 * <p>
 * series2 = 5
 * Sample Output
 * <p>
 * <p>
 * 1
 * Explanation
 * Given, n = 5, watch_history = [1, 2, 3, 5, 1] series 1= 5, and series2= 5.
 * Among all the periods of days in which both series 1 and series2, i.e. series with ID = 5 have been watched, the
 * subarray with the least watch score is [5]. Its watch score is 1.
 */
public class StreamingAnalyzer {

    public static void main(String[] args) {
        // Test Case 1
        List<Integer> watchHistory1 = Arrays.asList(1, 2, 2, 2, 5, 2);
        int series1_1 = 1;
        int series2_1 = 5;
        System.out.println(findMinimumWatchScore(watchHistory1, series1_1, series2_1)); // Expected output: 3

        // Test Case 2
        List<Integer> watchHistory2 = Arrays.asList(1, 2, 3, 5, 1);
        int series1_2 = 5;
        int series2_2 = 5;
        System.out.println(findMinimumWatchScore(watchHistory2, series1_2, series2_2)); // Expected output: 1

        // Test Case 3
        List<Integer> watchHistory3 = Arrays.asList(1, 3, 2, 1, 4);
        int series1_3 = 1;
        int series2_3 = 2;
        System.out.println(findMinimumWatchScore(watchHistory3, series1_3, series2_3)); // Expected output: 2

        // Additional Test Case
        List<Integer> watchHistory4 = Arrays.asList(1, 2, 3, 4, 5);
        int series1_4 = 1;
        int series2_4 = 5;
        System.out.println(findMinimumWatchScore(watchHistory4, series1_4, series2_4)); // Expected output: 5
    }

    // Main method to test the solution
    public static void main1(String[] args) {
        StreamingAnalyzer analyzer = new StreamingAnalyzer();

        // Test case 1
        List<Integer> history1 = Arrays.asList(1, 2, 2, 2, 5, 2);
        System.out.println("Test 1: " + findMinimumWatchScore(history1, 1, 5));

        // Test case 2
        List<Integer> history2 = Arrays.asList(1, 2, 3, 5, 1);
        System.out.println("Test 2: " + findMinimumWatchScore(history2, 5, 5));

        // Test case 3
        List<Integer> history3 = Arrays.asList(1, 3, 2, 1, 4);
        System.out.println("Test 3: " + findMinimumWatchScore(history3, 1, 2));

        // Additional test case
        List<Integer> history4 = Arrays.asList(1, 2, 3, 4, 5, 1, 2);
        System.out.println("Test 4: " + findMinimumWatchScore(history4, 1, 5));
    }

    // Method to find the minimum watch score
    public static int findMinimumWatchScore(List<Integer> watchHistory, int series1, int series2) {
        int historySize = watchHistory.size();
        int startIndex = 0;
        int lowestScore = Integer.MAX_VALUE;
        Set<Integer> uniqueShows = new HashSet<>();
        boolean foundFirst = false;
        boolean foundSecond = false;

        // Iterate through the watch history
        for (int endIndex = 0; endIndex < historySize; endIndex++) {
            int currentShow = watchHistory.get(endIndex);
            uniqueShows.add(currentShow);

            // Check if we found the target series
            if (currentShow == series1) foundFirst = true;
            if (currentShow == series2) foundSecond = true;

            // If both series are found, try to minimize the window
            while (foundFirst && foundSecond) {
                // Update the lowest score if needed
                lowestScore = Math.min(lowestScore, uniqueShows.size());

                // Try to shrink the window from the start
                int startShow = watchHistory.get(startIndex);
                if (startShow == series1 || startShow == series2) {
                    // Check if the show still exists in the remaining window
                    if (!watchHistory.subList(startIndex + 1, endIndex + 1).contains(startShow)) {
                        if (startShow == series1) foundFirst = false;
                        if (startShow == series2) foundSecond = false;
                    }
                }

                // Remove the start show if it's not in the remaining window
                if (!watchHistory.subList(startIndex + 1, endIndex + 1).contains(startShow)) {
                    uniqueShows.remove(startShow);
                }

                startIndex++;
            }
        }

        // Return the result, or -1 if no valid window was found
        return (lowestScore == Integer.MAX_VALUE) ? -1 : lowestScore;
    }
}
