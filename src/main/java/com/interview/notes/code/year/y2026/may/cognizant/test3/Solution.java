package com.interview.notes.code.year.y2026.may.cognizant.test3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {

    /*
     * Find the best average grade using simple loops and a HashMap.
     */
    public static int bestAverageGrade(String[][] scores) {
        // Step 1: Handle empty inputs
        if (scores == null || scores.length == 0) {
            return 0; 
        }

        // Step 2: Create a map to group each student's name with a list of their scores
        Map<String, List<Integer>> studentScores = new HashMap<>();

        // Loop through every row in the 2D array
        for (String[] row : scores) {
            String studentName = row[0];
            int testScore = Integer.parseInt(row[1]);

            // If we haven't seen this student yet, create a new empty list for them
            if (!studentScores.containsKey(studentName)) {
                studentScores.put(studentName, new ArrayList<>());
            }
            
            // Add the score to the student's list
            studentScores.get(studentName).add(testScore);
        }

        // Step 3: Calculate the highest average
        // We start with the lowest possible integer just in case all averages are negative
        int maxAverage = Integer.MIN_VALUE; 

        // Loop through each student in our map
        for (Map.Entry<String, List<Integer>> entry : studentScores.entrySet()) {
            List<Integer> grades = entry.getValue();

            // Add up all the grades for this student
            double sum = 0;
            for (int grade : grades) {
                sum += grade;
            }

            // Divide the sum by the number of tests to get the exact decimal average
            double exactAverage = sum / grades.size();

            // Round down to the nearest integer using Math.floor, then convert to an int
            int flooredAverage = (int) Math.floor(exactAverage);

            // If this student's average is the highest we've seen, save it
            if (flooredAverage > maxAverage) {
                maxAverage = flooredAverage;
            }
        }

        // Return the highest average we found
        return maxAverage;
    }

    /*
     * Simple testing method
     */
    public static boolean doTestsPass() {
        // Test 1: Standard input
        String[][] tc1 = {
            {"Bobby", "87"},
            {"Charles", "100"},
            {"Eric", "64"},
            {"Charles", "22"}
        };
        boolean pass1 = bestAverageGrade(tc1) == 87;

        // Test 2: Negative numbers (Testing that -2.5 floors to -3)
        String[][] tc2 = {
            {"Alice", "-5"},
            {"Alice", "0"}
        };
        boolean pass2 = bestAverageGrade(tc2) == -3;

        // Test 3: Empty input
        String[][] tc3 = {};
        boolean pass3 = bestAverageGrade(tc3) == 0;

        // Test 4: Large dataset to test performance limits
        int largeDataSize = 100000;
        String[][] tc4 = new String[largeDataSize][2];
        for (int i = 0; i < largeDataSize; i++) {
            tc4[i][0] = "Student" + (i % 100);
            tc4[i][1] = String.valueOf(i % 100);
        }
        boolean pass4 = bestAverageGrade(tc4) == 99;

        return pass1 && pass2 && pass3 && pass4;
    }

    /*
     * Main execution point
     */
    public static void main(String[] args) {
        if (doTestsPass()) {
            System.out.println("All tests pass!");
        } else {
            System.out.println("Tests fail.");
        }
    }
}