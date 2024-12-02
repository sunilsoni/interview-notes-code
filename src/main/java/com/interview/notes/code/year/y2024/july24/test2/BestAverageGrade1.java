package com.interview.notes.code.year.y2024.july24.test2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BestAverageGrade1 {
    /**
     * Find the best average grade.
     */
    public static int bestAverageGrade(String[][] scores) {
        if (scores == null || scores.length == 0) {
            return 0;
        }

        // Map to store total scores and count of scores for each student
        Map<String, List<Integer>> studentScores = new HashMap<>();

        // Populate the map with student scores
        for (String[] entry : scores) {
            String student = entry[0];
            int score = Integer.parseInt(entry[1]);
            studentScores.computeIfAbsent(student, k -> new ArrayList<>()).add(score);
        }

        // Calculate the best average grade
        int bestAverage = Integer.MIN_VALUE;
        for (Map.Entry<String, List<Integer>> entry : studentScores.entrySet()) {
            List<Integer> scoresList = entry.getValue();
            int sum = 0;
            for (int score : scoresList) {
                sum += score;
            }
            int average = sum / scoresList.size();
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
