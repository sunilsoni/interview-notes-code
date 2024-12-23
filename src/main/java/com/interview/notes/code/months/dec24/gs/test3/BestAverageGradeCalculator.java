package com.interview.notes.code.months.dec24.gs.test3;

import java.util.HashMap;
import java.util.Map;

/*

 */
public class BestAverageGradeCalculator {

    /**
     * Calculate the best average grade from the given scores.
     *
     * @param scores an array of [studentName, score] pairs
     * @return the highest floored average score among all students, or 0 if no scores are provided
     */
    public static int bestAverageGrade(String[][] scores) {
        if (scores == null || scores.length == 0) {
            return 0;
        }

        // Map: student -> [totalScore, count]
        Map<String, int[]> scoreMap = new HashMap<>();

        for (String[] entry : scores) {
            String name = entry[0];
            int score = Integer.parseInt(entry[1]);

            scoreMap.putIfAbsent(name, new int[2]);
            scoreMap.get(name)[0] += score; // total score
            scoreMap.get(name)[1] += 1;     // count of scores
        }

        int bestAverage = Integer.MIN_VALUE;
        for (Map.Entry<String, int[]> e : scoreMap.entrySet()) {
            int total = e.getValue()[0];
            int count = e.getValue()[1];
            // Integer division automatically floors the average
            int average = total / count;
            if (average > bestAverage) {
                bestAverage = average;
            }
        }

        return bestAverage == Integer.MIN_VALUE ? 0 : bestAverage;
    }

    /**
     * A simple test runner to verify correctness.
     */
    public static void main(String[] args) {
        // Test cases
        String[][] tc1 = {
                {"Bobby", "87"},
                {"Charles", "100"},
                {"Eric", "64"},
                {"Charles", "22"}
        };
        // Expected: Bobby's avg = 87, Charles' avg = 61 ((100+22)/2), Eric's avg = 64. Max = 87.
        testCase("Test case 1", tc1, 87);

        // Test with negative and positive
        String[][] tc2 = {
                {"Alice", "90"},
                {"Alice", "-10"},
                {"Bob", "0"},
                {"Bob", "0"}
        };
        // Alice's avg = (90 + (-10)) / 2 = 40, Bob's avg = 0, max = 40
        testCase("Test case 2", tc2, 40);

        // Empty input
        String[][] tc3 = {};
        // No scores -> 0
        testCase("Test case 3", tc3, 0);

        // Multiple students, large numbers
        String[][] tc4 = {
                {"John", "1000"},
                {"John", "999"},
                {"Jane", "1000"},
                {"Jane", "1001"},
                {"Jake", "-1"}
        };
        // John avg = 1999/2 = 999, Jane avg = 2001/2=1000, Jake = -1
        // max=1000
        testCase("Test case 4", tc4, 1000);

        // One student multiple negative scores
        String[][] tc5 = {
                {"Sam", "-5"},
                {"Sam", "-10"},
                {"Sam", "-3"}
        };
        // Sam avg = (-5 + -10 + -3)/3 = -18/3 = -6
        testCase("Test case 5", tc5, -6);

        // Additional large data test (just a quick check - no huge data shown here, but the logic can handle it)
        // Suppose we have 10000 entries, all the same student, all with the same score, the code would still be O(n).
        // Here we just simulate a smaller large dataset scenario.
//        String[][] largeScores = new String[10_000];
//        for (int i = 0; i < 10_000; i++) {
//            // All entries from "LargeUser" with score = i%100, just to ensure it runs efficiently
//            largeScores[i] = new String[]{"LargeUser", String.valueOf(i % 100)};
//        }
//        // The average would be roughly the average of numbers 0 through 99, which is about 49.5 floored to 49.
//        testCase("Large data test", largeScores, 49);

        System.out.println("All tests completed.");
    }

    /**
     * A simple helper method to run a test case and print PASS/FAIL.
     */
    private static void testCase(String testName, String[][] input, int expected) {
        int result = bestAverageGrade(input);
        if (result == expected) {
            System.out.println(testName + " PASSED. Expected: " + expected + ", Got: " + result);
        } else {
            System.out.println(testName + " FAILED. Expected: " + expected + ", Got: " + result);
        }
    }
}
