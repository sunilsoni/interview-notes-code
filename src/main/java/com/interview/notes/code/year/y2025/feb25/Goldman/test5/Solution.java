package com.interview.notes.code.year.y2025.feb25.Goldman.test5;

import java.util.HashMap;
import java.util.Map;
/*

```java
/* Problem Name: Best Average Grade - PLEASE DO NOT REMOVE THIS LINE. */

/**
 * Instructions:
 * - Given a list of student test scores, find the best average grade.
 * - Each student may have more than one test score in the list.
 *
 * - Complete the bestAverageGrade function in the editor below.
 *   - It has one parameter, `scores`, which is an array of student test scores.
 *   - Each element in the array is a two-element array of the form [student name, test score].
 *   - e.g., ["Bobby", "87"]
 * - Test scores may be positive or negative integers.
 *
 * - If you end up with an average grade that is not an integer, you should
 *   use a floor function to return the largest integer less than or equal to the average.
 * - Return 0 for an empty input.
 *
 * Example:
 *
 * Input:
 * [
 *   ["Bobby", "87"],
 *   ["Charles", "100"],
 *   ["Eric", "64"],
 *   ["Charles", "22"]
 * ]
 *
 * Expected output: 87
 * Explanation: The average scores are 87, 61, and 64 for Bobby, Charles, and Eric,
 * respectively. 87 is the highest.
 */


class Solution {
    
    /**
     * Find the best average grade.
     * Time complexity: O(n) where n is the number of scores
     * Space complexity: O(m) where m is the number of unique students
     */
    public static int bestAverageGrade(String[][] scores) {
        if (scores == null || scores.length == 0) {
            return 0;
        }
        
        // Map to store sum of scores and count for each student
        Map<String, Integer[]> studentScores = new HashMap<>();
        
        // Process all scores
        for (String[] score : scores) {
            String name = score[0];
            int value = Integer.parseInt(score[1]);
            
            // Get or create the student's record [sum, count]
            Integer[] record = studentScores.getOrDefault(name, new Integer[]{0, 0});
            record[0] += value;  // Add to sum
            record[1]++;         // Increment count
            studentScores.put(name, record);
        }
        
        // Find the best average
        int bestAverage = Integer.MIN_VALUE;
        for (Map.Entry<String, Integer[]> entry : studentScores.entrySet()) {
            Integer[] record = entry.getValue();
            // Calculate average and floor it
            int average = (int) Math.floor((double) record[0] / record[1]);
            bestAverage = Math.max(bestAverage, average);
        }
        
        return bestAverage;
    }

    /**
     * Returns true if the tests pass. Otherwise, returns false.
     */
    public static boolean doTestsPass() {
        // Test case 1: Example from the problem
        String[][] tc1 = {
            {"Bobby", "87"},
            {"Charles", "100"},
            {"Eric", "64"},
            {"Charles", "22"}
        };
        boolean pass = bestAverageGrade(tc1) == 87;
        System.out.println("Test case 1: " + (pass ? "PASS" : "FAIL"));
        
        // Test case 2: Empty input
        String[][] tc2 = {};
        boolean pass2 = bestAverageGrade(tc2) == 0;
        pass = pass && pass2;
        System.out.println("Test case 2: " + (pass2 ? "PASS" : "FAIL"));
        
        // Test case 3: Single student with one score
        String[][] tc3 = {{"Alice", "42"}};
        boolean pass3 = bestAverageGrade(tc3) == 42;
        pass = pass && pass3;
        System.out.println("Test case 3: " + (pass3 ? "PASS" : "FAIL"));
        
        // Test case 4: Negative scores
        String[][] tc4 = {
            {"David", "-10"},
            {"David", "-20"},
            {"Eve", "-5"}
        };
        boolean pass4 = bestAverageGrade(tc4) == -5;
        pass = pass && pass4;
        System.out.println("Test case 4: " + (pass4 ? "PASS" : "FAIL"));
        
        // Test case 5: Floating point average that needs to be floored
        String[][] tc5 = {
            {"Frank", "60"},
            {"Frank", "65"},
            {"Grace", "82"},
            {"Grace", "79"}
        };
        boolean pass5 = bestAverageGrade(tc5) == 80; // Frank: 62.5 -> 62, Grace: 80.5 -> 80
        pass = pass && pass5;
        System.out.println("Test case 5: " + (pass5 ? "PASS" : "FAIL"));
        
        // Test case 6: Large dataset
        String[][] tc6 = new String[10000][2];
        for (int i = 0; i < 5000; i++) {
            tc6[i] = new String[]{"Student1", "85"};
            tc6[i+5000] = new String[]{"Student2", "90"};
        }
        boolean pass6 = bestAverageGrade(tc6) == 90;
        pass = pass && pass6;
        System.out.println("Test case 6: " + (pass6 ? "PASS" : "FAIL"));
        
        return pass;
    }

    /**
     * Execution entry point.
     */
    public static void main(String[] args) {
        // Run the tests
        if (doTestsPass()) {
            System.out.println("All tests pass");
        } else {
            System.out.println("Tests fail.");
        }
    }
}