package com.interview.notes.code.year.y2025.feb.Goldman.test4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BestAverageGrade {
    public static int bestAverageGrade(String[][] scores) {
        if (scores == null || scores.length == 0) {
            return 0;
        }

        // Map to store sum and count of scores for each student
        Map<String, List<Integer>> studentScores = new HashMap<>();

        // Group scores by student
        for (String[] score : scores) {
            String name = score[0];
            int value = Integer.parseInt(score[1]);
            studentScores.computeIfAbsent(name, k -> new ArrayList<>()).add(value);
        }

        // Calculate best average
        int bestAverage = Integer.MIN_VALUE;
        for (Map.Entry<String, List<Integer>> entry : studentScores.entrySet()) {
            List<Integer> scoreList = entry.getValue();
            int sum = 0;
            for (int score : scoreList) {
                sum += score;
            }
            int average = (int) Math.floor(sum / (double) scoreList.size());
            bestAverage = Math.max(bestAverage, average);
        }

        return bestAverage;
    }

    public static void main(String[] args) {
        // Test cases
        runTest(1, new String[][]{
                {"Bobby", "87"},
                {"Charles", "100"},
                {"Eric", "64"},
                {"Charles", "22"}
        }, 87);

        runTest(2, new String[][]{}, 0);

        runTest(3, new String[][]{
                {"Sarah", "-95"},
                {"Sarah", "-96"},
                {"Sarah", "-94"}
        }, -95);

        runTest(4, new String[][]{
                {"Zoe", "100"},
                {"Zoe", "100"},
                {"Alex", "1"},
                {"Alex", "1"}
        }, 100);

        // Large data test
        String[][] largeTest = new String[10000][];
        for (int i = 0; i < 10000; i++) {
            largeTest[i] = new String[]{"Student" + (i % 100), String.valueOf(i % 50)};
        }
        runTest(5, largeTest, 49);
    }

    private static void runTest(int testNumber, String[][] input, int expectedOutput) {
        int result = bestAverageGrade(input);
        boolean passed = result == expectedOutput;
        System.out.printf("Test %d: %s\n", testNumber,
                passed ? "PASS" : "FAIL (Expected: " + expectedOutput + ", Got: " + result + ")");
    }
}
