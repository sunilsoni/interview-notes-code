package com.interview.notes.code.year.y2024.dec24.gs.test1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/*
WORKING



 * Problem Name is: &&& Best Average Grade &&& PLEASE DO NOT REMOVE THIS LINE. */

/*
Instructions:

* Given a list of student test scores, find the best average grade.
* Each student may have more than one test score in the list.

* Complete the bestAverageGrade function in the editor below.
  * It has one parameter, scores, which is an array of student test scores.
  * Each element in the array is a two-element array of the form [student name, test score].
      * e.g., ["Bobby", "87"].
  * Test scores may be positive or negative integers.

* If you end up with an average grade that is not an integer, you should
  * use a floor function to return the largest integer less than or equal to the average.
  * Return 0 for an empty input.

* Example:

  Input:
  [["Bobby", "87"],
   ["Charles", "100"],
   ["Eric", "64"],
   ["Charles", "22"]]

  Expected output: 87
  Explanation: The average scores are 87, 61, and 64 for Bobby, Charles, and Eric,
  respectively. 87 is the highest.


    /**
     * Execution entry point.

    public static void main(String[] args) {
        // Run the tests
        if (doTestsPass()) {
            System.out.println("All tests pass");
        } else {
            System.out.println("Tests fail.");
        }
    }
}
 */
public class StudentGrades {
    public static int bestAverageGrade(String[][] scores) {
        if (scores == null || scores.length == 0) {
            return 0;
        }

        // Map to store student scores
        Map<String, List<Integer>> studentScores = new HashMap<>();

        // Group scores by student
        for (String[] score : scores) {
            String name = score[0];
            int grade = Integer.parseInt(score[1]);
            studentScores.computeIfAbsent(name, k -> new ArrayList<>()).add(grade);
        }

        // Calculate highest average
        int maxAverage = Integer.MIN_VALUE;
        for (List<Integer> grades : studentScores.values()) {
            int sum = 0;
            for (int grade : grades) {
                sum += grade;
            }
            int average = (int) Math.floor(sum / (double) grades.size());
            maxAverage = Math.max(maxAverage, average);
        }

        return maxAverage == Integer.MIN_VALUE ? 0 : maxAverage;
    }

    public static void main(String[] args) {
        runAllTests();
    }

    private static void runAllTests() {
        // Test 1: Original test case
        testCase(new String[][]{
                {"Bobby", "87"},
                {"Charles", "100"},
                {"Eric", "64"},
                {"Charles", "22"}
        }, 87, "Basic test case");

        // Test 2: Empty input
        testCase(new String[][]{}, 0, "Empty input");

        // Test 3: Single student
        testCase(new String[][]{
                {"Alice", "100"}
        }, 100, "Single student");

        // Test 4: Negative scores
        testCase(new String[][]{
                {"John", "-5"},
                {"John", "-15"},
                {"Sarah", "-10"}
        }, -5, "Negative scores");

        // Test 5: Decimal average
        testCase(new String[][]{
                {"Mike", "100"},
                {"Mike", "99"}
        }, 99, "Decimal average");

        // Test 6: Large numbers
        testCase(new String[][]{
                {"Max", "999999"},
                {"Max", "1000000"},
                {"Min", "-999999"}
        }, 999999, "Large numbers");

        // Test 7: Multiple students same score
        testCase(new String[][]{
                {"A", "85"},
                {"B", "85"},
                {"C", "85"}
        }, 85, "Same scores");
    }

    private static void testCase(String[][] input, int expected, String testName) {
        int result = bestAverageGrade(input);
        boolean passed = result == expected;
        System.out.printf("Test %s: %s%n", testName, passed ? "PASS" : "FAIL");
        if (!passed) {
            System.out.printf("Expected: %d, Got: %d%n", expected, result);
        }
    }
}
