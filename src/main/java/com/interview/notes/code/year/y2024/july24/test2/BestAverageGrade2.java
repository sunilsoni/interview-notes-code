package com.interview.notes.code.year.y2024.july24.test2;

import java.util.HashMap;
import java.util.Map;

public class BestAverageGrade2 {
    /**
     * Find the best average grade.
     */
    public static int bestAverageGrade(String[][] scores) {
        if (scores == null || scores.length == 0) {
            return 0;
        }

        // Map to store sum of scores and count of scores for each student
        Map<String, int[]> studentScores = new HashMap<>();

        // Populate the map with student scores
        for (String[] entry : scores) {
            String student = entry[0];
            int score = Integer.parseInt(entry[1]);
            studentScores.computeIfAbsent(student, k -> new int[2]);
            studentScores.get(student)[0] += score; // sum of scores
            studentScores.get(student)[1] += 1;     // count of scores
        }

        // Calculate the best average grade
        int bestAverage = Integer.MIN_VALUE;
        for (Map.Entry<String, int[]> entry : studentScores.entrySet()) {
            int[] sumAndCount = entry.getValue();
            int sum = sumAndCount[0];
            int count = sumAndCount[1];
            int average = sum / count; // floor division
            bestAverage = Math.max(bestAverage, average);
        }

        return bestAverage;
    }

    /**
     * Returns true if the tests pass. Otherwise, returns false.
     */
    public static boolean doTestsPass() {
        // Add more test cases as needed
        String[][] tc1 = {
                {"Bobby", "87"},
                {"Charles", "100"},
                {"Eric", "64"},
                {"Charles", "22"}
        };
        return bestAverageGrade(tc1) == 87;
    }

    /**
     * Execution entry point.
     */
    public static void main(String[] args) {
        // Run the tests
        if (doTestsPass()) {
            System.out.println("All tests pass");
        } else {
            System.out.println("There are test failures");
        }
    }
}
