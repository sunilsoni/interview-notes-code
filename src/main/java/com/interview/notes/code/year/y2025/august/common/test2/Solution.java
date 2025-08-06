package com.interview.notes.code.year.y2025.august.common.test2;

import java.util.*;
import java.util.stream.*;

class Solution {
    
    /**
     * Calculates the highest average grade among all students
     * @param scores 2D array containing student names and their scores
     * @return highest average grade (rounded down to nearest integer)
     */
    public static int bestAverageGrade(String[][] scores) {
        // Handle empty input case
        if (scores == null || scores.length == 0) {
            return 0;
        }

        // Create a Map to store student name and their list of scores
        Map<String, List<Integer>> studentScores = new HashMap<>();

        // Process each score entry and group by student name
        for (String[] score : scores) {
            String name = score[0];
            int grade = Integer.parseInt(score[1]);
            
            // Get or create list of scores for this student
            studentScores.computeIfAbsent(name, k -> new ArrayList<>())
                        .add(grade);
        }

        // Calculate highest average using streams
        return studentScores.values().stream()
                          .mapToInt(grades -> 
                              (int) grades.stream()
                                        .mapToInt(Integer::intValue)
                                        .average()
                                        .orElse(0))
                          .max()
                          .orElse(0);
    }

    public static void main(String[] args) {
        // Test cases
        runAllTests();
    }

    private static void runAllTests() {
        // Test Case 1: Normal case
        String[][] test1 = {
            {"Bobby", "87"},
            {"Charles", "100"},
            {"Eric", "64"},
            {"Charles", "22"}
        };
        
        // Test Case 2: Empty input
        String[][] test2 = {};
        
        // Test Case 3: Single student
        String[][] test3 = {{"Alice", "100"}};
        
        // Test Case 4: Large numbers
        String[][] test4 = {
            {"Student1", "1000"},
            {"Student1", "2000"}
        };
        
        // Test Case 5: Multiple students same score
        String[][] test5 = {
            {"Bob", "90"},
            {"Alice", "90"},
            {"Tom", "90"}
        };

        // Run all test cases
        runTest("Test 1 - Normal case", test1, 87);
        runTest("Test 2 - Empty input", test2, 0);
        runTest("Test 3 - Single student", test3, 100);
        runTest("Test 4 - Large numbers", test4, 1500);
        runTest("Test 5 - Same scores", test5, 90);
    }

    private static void runTest(String testName, String[][] input, int expectedOutput) {
        int result = bestAverageGrade(input);
        boolean passed = result == expectedOutput;
        
        System.out.println(testName + ": " + 
                         (passed ? "PASSED" : "FAILED") +
                         " (Expected: " + expectedOutput + 
                         ", Got: " + result + ")");
    }
}
