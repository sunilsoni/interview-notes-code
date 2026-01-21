package com.interview.notes.code.year.y2025.december.common.test3;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.Collectors;

class Solution {

    /**
     * Finds the best (highest) average grade among all students.
     * Each student may have multiple scores that need to be averaged.
     *
     * @param scores - 2D array where each row contains [studentName, score]
     * @return the highest floored average grade, or 0 if input is empty
     */
    public static int bestAverageGrade(String[][] scores) {

        // Edge case: if input is null or empty, return 0 as per requirement
        // This prevents NullPointerException and handles empty data gracefully
        if (scores == null || scores.length == 0) {
            return 0;  // Return 0 for empty input as stated in problem
        }

        // Use Java 8 Stream API to process the scores efficiently
        // Step 1: Convert the 2D array into a stream for processing
        return Arrays.stream(scores)

                // Step 2: Group all scores by student name
                // Collectors.groupingBy creates a Map<String, List<String[]>>
                // where key is student name and value is list of their score entries
                .collect(Collectors.groupingBy(

                        // Extract student name from first element of each entry
                        // entry[0] contains the name like "Bobby", "Charles", etc.
                        entry -> entry[0],

                        // For each group, calculate the average of all scores
                        // Collectors.averagingDouble computes mean of integer scores
                        // We parse entry[1] which contains the score as String
                        Collectors.averagingDouble(
                                entry -> Integer.parseInt(entry[1])  // Convert score string to int
                        )
                ))

                // Step 3: Get all the average values from the map
                // At this point we have Map<StudentName, AverageScore>
                // We only need the averages, not the names
                .values()

                // Step 4: Convert collection of averages to a stream
                // This allows us to find the maximum value
                .stream()

                // Step 5: Convert each Double average to int using floor
                // Math.floor returns largest integer <= the average
                // This handles both positive and negative numbers correctly
                // For example: 61.5 -> 61, -3.7 -> -4
                .mapToInt(avg -> (int) Math.floor(avg))

                // Step 6: Find the maximum average among all students
                // This gives us the "best" average grade
                .max()

                // Step 7: If no max found (shouldn't happen with valid input)
                // return 0 as default value
                .orElse(0);
    }

    /**
     * Test method to validate all test cases
     * Prints PASS or FAIL for each test case
     *
     * @return true if all tests pass, false otherwise
     */
    public static boolean doTestsPass() {

        // Track if all tests pass
        // Initialize to true, will set to false if any test fails
        boolean allPass = true;

        // Test Case 1: Basic test from problem statement
        // Bobby: 87 (avg=87), Charles: 100,22 (avg=61), Eric: 64 (avg=64)
        // Expected best average: 87
        String[][] tc1 = {
                {"Bobby", "87"},
                {"Charles", "100"},
                {"Eric", "64"},
                {"Charles", "22"}
        };
        int result1 = bestAverageGrade(tc1);  // Calculate result
        boolean pass1 = (result1 == 87);  // Check if matches expected
        System.out.println("Test 1 - Basic test: " + (pass1 ? "PASS" : "FAIL") +
                " (Expected: 87, Got: " + result1 + ")");
        allPass = allPass && pass1;  // Update overall status

        // Test Case 2: Empty input
        // Should return 0 as per problem requirement
        String[][] tc2 = {};
        int result2 = bestAverageGrade(tc2);
        boolean pass2 = (result2 == 0);
        System.out.println("Test 2 - Empty input: " + (pass2 ? "PASS" : "FAIL") +
                " (Expected: 0, Got: " + result2 + ")");
        allPass = allPass && pass2;

        // Test Case 3: Null input
        // Should return 0 to handle edge case gracefully
        int result3 = bestAverageGrade(null);
        boolean pass3 = (result3 == 0);
        System.out.println("Test 3 - Null input: " + (pass3 ? "PASS" : "FAIL") +
                " (Expected: 0, Got: " + result3 + ")");
        allPass = allPass && pass3;

        // Test Case 4: Single student with single score
        // Alice: 95 -> average = 95
        String[][] tc4 = {{"Alice", "95"}};
        int result4 = bestAverageGrade(tc4);
        boolean pass4 = (result4 == 95);
        System.out.println("Test 4 - Single student: " + (pass4 ? "PASS" : "FAIL") +
                " (Expected: 95, Got: " + result4 + ")");
        allPass = allPass && pass4;

        // Test Case 5: Negative scores
        // Testing with negative values as problem allows negative integers
        // John: -10, -20 -> avg = -15
        // Jane: -5 -> avg = -5 (highest)
        String[][] tc5 = {
                {"John", "-10"},
                {"Jane", "-5"},
                {"John", "-20"}
        };
        int result5 = bestAverageGrade(tc5);
        boolean pass5 = (result5 == -5);
        System.out.println("Test 5 - Negative scores: " + (pass5 ? "PASS" : "FAIL") +
                " (Expected: -5, Got: " + result5 + ")");
        allPass = allPass && pass5;

        // Test Case 6: Floor function test
        // Testing that non-integer averages are floored correctly
        // Tom: 90, 91 -> avg = 90.5 -> floor = 90
        // Jim: 89 -> avg = 89
        // Best is 90
        String[][] tc6 = {
                {"Tom", "90"},
                {"Tom", "91"},
                {"Jim", "89"}
        };
        int result6 = bestAverageGrade(tc6);
        boolean pass6 = (result6 == 90);
        System.out.println("Test 6 - Floor function: " + (pass6 ? "PASS" : "FAIL") +
                " (Expected: 90, Got: " + result6 + ")");
        allPass = allPass && pass6;

        // Test Case 7: Negative floor test
        // Verify floor works for negative decimals
        // Mike: -10, -11 -> avg = -10.5 -> floor = -11
        // Sara: -12 -> avg = -12
        // Best is -11
        String[][] tc7 = {
                {"Mike", "-10"},
                {"Mike", "-11"},
                {"Sara", "-12"}
        };
        int result7 = bestAverageGrade(tc7);
        boolean pass7 = (result7 == -11);
        System.out.println("Test 7 - Negative floor: " + (pass7 ? "PASS" : "FAIL") +
                " (Expected: -11, Got: " + result7 + ")");
        allPass = allPass && pass7;

        // Test Case 8: Large data test
        // Testing performance with 100,000 entries
        // Creates students named Student_0 to Student_999
        // Each student gets 100 random scores
        System.out.println("Test 8 - Large data test: Running...");
        int numStudents = 1000;  // Number of unique students
        int scoresPerStudent = 100;  // Scores per student
        int totalEntries = numStudents * scoresPerStudent;  // Total 100,000 entries

        String[][] tc8 = new String[totalEntries][2];  // Create large array
        Random random = new Random(42);  // Fixed seed for reproducibility

        // Fill the array with test data
        int index = 0;  // Track current position in array
        for (int i = 0; i < numStudents; i++) {
            String studentName = "Student_" + i;  // Generate unique student name
            for (int j = 0; j < scoresPerStudent; j++) {
                // Generate random score between 0 and 100
                int score = random.nextInt(101);
                tc8[index][0] = studentName;  // Set student name
                tc8[index][1] = String.valueOf(score);  // Set score as string
                index++;  // Move to next position
            }
        }

        // Measure execution time for large data
        long startTime = System.currentTimeMillis();  // Start timer
        int result8 = bestAverageGrade(tc8);  // Process large dataset
        long endTime = System.currentTimeMillis();  // End timer

        // For large random data, just verify result is in valid range (0-100)
        boolean pass8 = (result8 >= 0 && result8 <= 100);
        System.out.println("Test 8 - Large data: " + (pass8 ? "PASS" : "FAIL") +
                " (Result: " + result8 + ", Time: " + (endTime - startTime) + "ms)");
        allPass = allPass && pass8;

        // Test Case 9: All same scores
        // When all students have same average
        // Dan: 50, 50 -> avg = 50
        // Eve: 50 -> avg = 50
        String[][] tc9 = {
                {"Dan", "50"},
                {"Dan", "50"},
                {"Eve", "50"}
        };
        int result9 = bestAverageGrade(tc9);
        boolean pass9 = (result9 == 50);
        System.out.println("Test 9 - Same scores: " + (pass9 ? "PASS" : "FAIL") +
                " (Expected: 50, Got: " + result9 + ")");
        allPass = allPass && pass9;

        // Test Case 10: Zero scores
        // Testing with zero values
        // Zero: 0, 0 -> avg = 0
        // One: 1 -> avg = 1
        String[][] tc10 = {
                {"Zero", "0"},
                {"Zero", "0"},
                {"One", "1"}
        };
        int result10 = bestAverageGrade(tc10);
        boolean pass10 = (result10 == 1);
        System.out.println("Test 10 - Zero scores: " + (pass10 ? "PASS" : "FAIL") +
                " (Expected: 1, Got: " + result10 + ")");
        allPass = allPass && pass10;

        // Return overall test status
        return allPass;
    }

    /**
     * Main method - entry point of the program
     * Executes all tests and displays final result
     */
    public static void main(String[] args) {

        // Print header for test output
        System.out.println("========================================");
        System.out.println("Running Best Average Grade Tests");
        System.out.println("========================================\n");

        // Execute all tests and capture result
        // doTestsPass() returns true only if ALL tests pass
        boolean allTestsPass = doTestsPass();

        // Print final summary
        System.out.println("\n========================================");
        if (allTestsPass) {
            // All tests passed - success message
            System.out.println("RESULT: All tests PASSED!");
        } else {
            // Some test failed - failure message
            System.out.println("RESULT: Some tests FAILED!");
        }
        System.out.println("========================================");
    }
}