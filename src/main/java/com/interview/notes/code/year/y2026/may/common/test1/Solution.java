package com.interview.notes.code.year.y2026.may.common.test1;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution { // Main solution class expected by the platform

    public static int bestAverageGrade(String[][] scores) { // Finds highest floored average grade
        if (scores == null || scores.length == 0) return 0; // Empty or null input should return 0

        return Arrays.stream(scores) // Convert input array into stream
                .filter(row -> row != null && row.length == 2 && row[0] != null && row[1] != null) // Keep only valid rows
                .collect(Collectors.groupingBy( // Group all scores by student name
                        row -> row[0], // Student name is the grouping key
                        Collectors.summarizingInt(row -> Integer.parseInt(row[1].trim())) // Convert score and collect sum/count
                ))
                .values() // Take only grouped score summaries
                .stream() // Convert summaries into stream
                .mapToInt(stat -> (int) Math.floor(stat.getAverage())) // Floor each student's average
                .max() // Pick the best average
                .orElse(0); // Return 0 if no valid rows exist
    }

    static void check(GradeTest test) { // Runs one test case
        int actual = bestAverageGrade(test.scores()); // Call solution method
        String result = actual == test.expected() ? "PASS" : "FAIL"; // Compare actual and expected
        System.out.println(test.name() + " : " + result + " | expected=" + test.expected() + ", actual=" + actual); // Print result
    }

    static String[][] largeScores() { // Creates large input test data
        return IntStream.range(0, 100_000) // Generate 100000 records
                .mapToObj(i -> new String[]{i % 2 == 0 ? "High" : "Low", i % 2 == 0 ? "100" : "1"}) // Alternate two students
                .toArray(String[][]::new); // Convert stream back to 2D array
    }

    public static void main(String[] args) { // Execution starts here
        var tests = List.of( // Create all test cases
                new GradeTest("sample", new String[][]{{"Bobby", "87"}, {"Charles", "100"}, {"Eric", "64"}, {"Charles", "22"}}, 87), // Given test
                new GradeTest("empty", new String[][]{}, 0), // Empty input test
                new GradeTest("single", new String[][]{{"A", "100"}}, 100), // Single score test
                new GradeTest("decimal floor", new String[][]{{"A", "91"}, {"A", "90"}, {"B", "89"}}, 90), // Average 90.5 floors to 90
                new GradeTest("negative floor", new String[][]{{"A", "-1"}, {"A", "-2"}, {"B", "-3"}}, -2), // Negative average floors correctly
                new GradeTest("mixed values", new String[][]{{"A", "10"}, {"A", "20"}, {"B", "100"}, {"B", "-50"}}, 25), // Mixed positive/negative test
                new GradeTest("large input", largeScores(), 100) // Large input test
        );

        tests.forEach(Solution::check); // Run every test case
    }

    record GradeTest(String name, String[][] scores, int expected) {} // Small test data holder
}