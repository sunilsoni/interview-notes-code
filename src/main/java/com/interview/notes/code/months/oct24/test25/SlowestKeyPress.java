package com.interview.notes.code.months.oct24.test25;

import java.util.ArrayList;
import java.util.List;

public class SlowestKeyPress {

    /*
     * Complete the 'slowestKey' function below.
     *
     * The function is expected to return a CHARACTER.
     * The function accepts 2D_INTEGER_ARRAY keyTimes as parameter.
     */
    public static char slowestKey(List<List<Integer>> keyTimes) {
        if (keyTimes == null || keyTimes.size() == 0) {
            throw new IllegalArgumentException("keyTimes cannot be null or empty");
        }

        int maxDuration = -1;
        int resultKey = -1;
        int previousTime = 0;

        for (List<Integer> keyTime : keyTimes) {
            int key = keyTime.get(0);
            int currentTime = keyTime.get(1);
            int duration = currentTime - previousTime;

            if (duration > maxDuration) {
                maxDuration = duration;
                resultKey = key;
            }
            previousTime = currentTime;
        }

        return (char) ('a' + resultKey);
    }

    // Helper method to convert a 2D array to List<List<Integer>>
    private static List<List<Integer>> convertToList(int[][] array) {
        List<List<Integer>> list = new ArrayList<>();
        for (int[] row : array) {
            List<Integer> subList = new ArrayList<>();
            for (int num : row) {
                subList.add(num);
            }
            list.add(subList);
        }
        return list;
    }

    // Method to run a single test case
    private static boolean runTest(int testCaseNumber, int[][] keyTimesArray, char expected) {
        List<List<Integer>> keyTimes = convertToList(keyTimesArray);
        char result = slowestKey(keyTimes);
        if (result == expected) {
            System.out.println("Test Case " + testCaseNumber + ": PASS");
            return true;
        } else {
            System.out.println("Test Case " + testCaseNumber + ": FAIL (Expected: " + expected + ", Got: " + result + ")");
            return false;
        }
    }

    public static void main(String[] args) {
        int passed = 0;
        int failed = 0;

        // Sample Test Case 0
        int[][] test0 = {
                {0, 2},
                {1, 3},
                {0, 7}
        };
        if (runTest(0, test0, 'a')) passed++;
        else failed++;

        // Sample Test Case 1
        int[][] test1 = {
                {0, 1},
                {0, 3},
                {4, 5},
                {5, 6},
                {4, 10}
        };
        if (runTest(1, test1, 'e')) passed++;
        else failed++;

        // Additional Test Case 2: Only one key press
        int[][] test2 = {
                {2, 5}
        };
        if (runTest(2, test2, 'c')) passed++;
        else failed++;

        // Additional Test Case 3: Maximum duration at the first key press
        int[][] test3 = {
                {0, 10},
                {1, 15},
                {2, 20}
        };
        if (runTest(3, test3, 'a')) passed++;
        else failed++;

        // Additional Test Case 4: Maximum duration at the last key press
        int[][] test4 = {
                {0, 1},
                {1, 3},
                {2, 10}
        };
        if (runTest(4, test4, 'c')) passed++;
        else failed++;

        // Additional Test Case 5: All durations same except one
        int[][] test5 = {
                {0, 1},
                {1, 2},
                {2, 4},
                {3, 5},
                {4, 7}
        };
        if (runTest(5, test5, 'c')) passed++;
        else failed++;

        // Additional Test Case 6: Large Input
        int n = 100000;
        int[][] test6 = new int[n][2];
        for (int i = 0; i < n; i++) {
            test6[i][0] = i % 26; // Cycle through keys 'a' to 'z'
            test6[i][1] = i + 1;   // Increment time by 1
        }
        // The last key press will have duration 1, same as others
        // Since there's a constraint that there is only one key with the worst time,
        // we modify the last key to have a larger duration
        test6[n - 1][1] = n + 100; // Duration = 100
        if (runTest(6, test6, (char) ('a' + ((n - 1) % 26)))) passed++;
        else failed++;

        // Additional Test Case 7: All key presses have the same duration except one
        int[][] test7 = {
                {0, 1},
                {1, 3},
                {2, 5},
                {3, 7},
                {4, 12}, // Duration = 5, others are 2
                {5, 14}
        };
        if (runTest(7, test7, 'e')) passed++;
        else failed++;

        // Summary
        System.out.println("\nTotal Passed: " + passed);
        System.out.println("Total Failed: " + failed);
    }
}
