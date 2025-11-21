package com.interview.notes.code.year.y2024.july24.Test1;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class BestAverageGrade {

    public static int bestAverageGrade(String[][] scores) {
        if (scores == null || scores.length == 0) {
            return 0;
        }

        Map<String, int[]> studentGrades = new HashMap<>();

        // Sum scores and count tests for each student
        for (String[] scorePair : scores) {
            String student = scorePair[0];
            int score = Integer.parseInt(scorePair[1]);

            studentGrades.putIfAbsent(student, new int[2]); // [totalScore, count]
            studentGrades.get(student)[0] += score;
            studentGrades.get(student)[1]++;
        }

        double bestAverage = Double.NEGATIVE_INFINITY;

        // Calculate the best average grade
        for (int[] totals : studentGrades.values()) {
            double average = (double) totals[0] / totals[1];
            bestAverage = Math.max(bestAverage, Math.floor(average));
        }

        return (int) bestAverage;
    }

    public static boolean doTestsPass() {
        // Implement more test cases
        String[][] tc1 = {
                {"Bobby", "87"},
                {"Charles", "100"},
                {"Eric", "64"},
                {"Charles", "22"}
        };

        // Expected result is the highest integer average (Bobby's 87)
        return bestAverageGrade(tc1) == 87;
    }

    public static void main(String[] args) {
        if (doTestsPass()) {
            System.out.println("All tests pass");
        } else {
            System.out.println("There are test failures");
        }

        try {
            // Code that may throw exceptions
            // Example: File operations, database connections, etc.
            int result = 10 / 0;  // ArithmeticException
            String str = null;
            System.out.println(str.length());  // NullPointerException
            FileReader file = new FileReader("file.txt");  // FileNotFoundException
        } catch (ArithmeticException e) {
            // Handling arithmetic exceptions
            System.out.println("ArithmeticException caught: " + e.getMessage());
        } catch (NullPointerException e) {
            // Handling null pointer exceptions
            System.out.println("NullPointerException caught: " + e.getMessage());
        } catch (FileNotFoundException e) {
            // Handling file not found exceptions
            System.out.println("FileNotFoundException caught: " + e.getMessage());
        } catch (Exception e) {
            // Generic catch block for any other unhandled exceptions
            System.out.println("Exception caught: " + e.getMessage());
        }

    }
}
