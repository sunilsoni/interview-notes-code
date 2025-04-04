package com.interview.notes.code.year.y2025.march.GoldmanSachs.test5;/* Problem Name is &&& Best Average Grade &&& PLEASE DO NOT REMOVE THIS LINE. */
import java.util.*;

class Solution {
    /*
     * Given an array of [studentName, scoreString],
     * this method returns the best (maximum) floored average score.
     */
    public static int bestAverageGrade(String[][] scores) {
        // Handle null or empty input
        if (scores == null || scores.length == 0) {
            return 0;
        }

        // Map to store each student's scores
        Map<String, List<Integer>> studentScores = new HashMap<>();

        // Fill the map with scores
        for (String[] entry : scores) {
            // entry[0] -> student name
            // entry[1] -> score string
            String studentName = entry[0];
            int score = Integer.parseInt(entry[1]);
            studentScores.computeIfAbsent(studentName, k -> new ArrayList<>()).add(score);
        }

        // Track the best (maximum) floored average
        int bestFloorAverage = Integer.MIN_VALUE;

        // Compute each student's average, apply floor, track max
        for (Map.Entry<String, List<Integer>> entry : studentScores.entrySet()) {
            List<Integer> scoresList = entry.getValue();
            if (scoresList.isEmpty()) {
                continue;
            }
            // Sum up scores
            long sum = 0;
            for (int s : scoresList) {
                sum += s;
            }
            // Convert to double for correct floor operation (especially for negative values)
            double average = (double) sum / scoresList.size();
            int flooredAverage = (int) Math.floor(average);

            if (flooredAverage > bestFloorAverage) {
                bestFloorAverage = flooredAverage;
            }
        }

        return bestFloorAverage;
    }

    /*
     * Simple test suite to verify correctness.
     * Prints "Tests pass." if all tests succeed, otherwise "Tests fail."
     */
    public static void main(String[] args) {
        // 1) Example test case from the problem description
        String[][] tc1 = {
            {"Bobby", "87"},
            {"Charles", "100"},
            {"Eric", "64"},
            {"Charles", "22"}
        };
        // Expecting 87 (Bobby’s single score is 87, which is higher than Charles’s floor(61) and Eric’s 64)
        System.out.println("Test 1: " + (bestAverageGrade(tc1) == 87 ? "PASS" : "FAIL"));

        // 2) Empty input -> 0
        String[][] tc2 = {};
        System.out.println("Test 2: " + (bestAverageGrade(tc2) == 0 ? "PASS" : "FAIL"));

        // 3) Single entry
        String[][] tc3 = {
            {"Alice", "90"}
        };
        // Expect 90
        System.out.println("Test 3: " + (bestAverageGrade(tc3) == 90 ? "PASS" : "FAIL"));

        // 4) Negative scores
        String[][] tc4 = {
            {"Derek", "-5"},
            {"Derek", "-3"},
            {"Derek", "-2"}
        };
        // Average = (-5 + -3 + -2) / 3 = -10 / 3 = -3.333..., floor -> -4
        System.out.println("Test 4: " + (bestAverageGrade(tc4) == -4 ? "PASS" : "FAIL"));

        // 5) Mixed positives and negatives
        // Let's say 72, 100, -10 -> sum = 162 -> avg=54, floor=54
        String[][] tc5 = {
            {"Fiona", "72"},
            {"Fiona", "100"},
            {"Fiona", "-10"},
            {"George", "0"}        // Another student
        };
        System.out.println("Test 5: " + (bestAverageGrade(tc5) == 54 ? "PASS" : "FAIL"));

        // 6) Large input simulation (simplified demonstration)
        // In a real scenario, you might generate thousands or millions of records
        // to check performance. Here we just verify it runs logically for multiple entries.
        String[][] tc6 = new String[5][2];
        tc6[0] = new String[]{"Large", "1"};
        tc6[1] = new String[]{"Large", "2"};
        tc6[2] = new String[]{"Large", "3"};
        tc6[3] = new String[]{"Large", "4"};
        tc6[4] = new String[]{"Other", "100"};
        // Large’s average is floor((1+2+3+4)/4) = floor(10/4) = 2
        // Other = 100 -> best average = 100
        System.out.println("Test 6: " + (bestAverageGrade(tc6) == 100 ? "PASS" : "FAIL"));

        // Final check: if all pass, we consider all tests successful
        // (You can visually verify each line’s PASS/FAIL status)
    }
}
