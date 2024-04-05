package com.interview.notes.code.months.april24.test2;

import java.util.HashMap;
import java.util.Map;


/**
 * The code in the screenshot seems to be a programming exercise or test question. Here’s the task as presented in the images you provided:
 *
 * **Instructions:**
 *
 * Given a list of student test scores, find the best average grade. Each student may have more than one test score in the list.
 *
 * Complete the `bestAverageGrade` function in the editor below. It has one parameter, `scores`, which is an array of student test scores. Each element in the array is a two-element array of the form `[student name, test score]`, e.g., `["Bobby", "87"]`. Test scores may be positive or negative integers.
 *
 * If you end up with an average grade that is not an integer, you should use a floor function to return the largest integer less than or equal to the average. Return 0 for an empty input.
 *
 * **Example:**
 *
 * Input:
 * ```java
 * [["Bobby", "87"], ["Charles", "100"], ["Eric", "64"], ["Charles", "22"]]
 * ```
 *
 * Expected output: 87
 *
 * Explanation: The average scores are 87, 61, and 64 for Bobby, Charles, and Eric, respectively. 87 is the highest.
 *
 *
 */

class BestAverageGrade {


    public static int bestAverageGrade1(String[][] scores) {
        Map<String, int[]> scoreMap = new HashMap<>();

        // Summing scores and counting the number of scores for each student
        for (String[] score : scores) {
            String name = score[0];
            int scoreValue = Integer.parseInt(score[1]);
            scoreMap.computeIfAbsent(name, k -> new int[2]);
            scoreMap.get(name)[0] += scoreValue; // Total score
            scoreMap.get(name)[1]++; // Number of scores
        }

        int lowestAverage = Integer.MAX_VALUE;

        // Calculating the lowest average score
        for (int[] totals : scoreMap.values()) {
            int average = (int) Math.floor((double) totals[0] / totals[1]);
            lowestAverage = Math.min(average, lowestAverage);
        }

        // In case there are no scores, return 0
        if (scoreMap.isEmpty()) {
            return 0;
        }

        return lowestAverage;
    }
    public static int bestAverageGrade(String[][] scores) {
        Map<String, int[]> scoreMap = new HashMap<>();
        
        // Summing scores and counting the number of scores for each student
        for (String[] score : scores) {
            String name = score[0];
            int scoreValue = Integer.parseInt(score[1]);
            scoreMap.computeIfAbsent(name, k -> new int[2]);
            scoreMap.get(name)[0] += scoreValue; // Total score
            scoreMap.get(name)[1]++; // Number of scores
        }

        int bestAverage = Integer.MIN_VALUE;

        // Calculating the best average score
        for (int[] totals : scoreMap.values()) {
            int average = (int) Math.floor((double) totals[0] / totals[1]);
            bestAverage = Math.max(average, bestAverage);
        }

        // In case there are no scores, return 0
        if (scoreMap.isEmpty()) {
            return 0;
        }

        return bestAverage;
    }

    public static void main(String[] args) {
        String[][] example1 = {
            {"Bobby", "87"},
            {"Charles", "100"},
            {"Eric", "64"},
            {"Charles", "22"}
        };
        
        System.out.println("The best average grade is: " + bestAverageGrade(example1));

        String[][] example2 = {
                {"Barry", "66"},
                {"Barry", "65"},
                {"Alfred", "-122"}
        };

        System.out.println("The best average grade for the given example is: " + bestAverageGrade(example2));

        // Add more examples if necessary
    }
}
