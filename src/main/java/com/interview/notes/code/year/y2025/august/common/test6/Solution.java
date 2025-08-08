package com.interview.notes.code.year.y2025.august.common.test6;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.Map;
import java.util.stream.Collectors;

class Solution {
    /**
     * Method to compute the highest floor-average grade among all students.
     */
    public static int bestAverageGrade(String[][] scores) { // Method signature
        if (scores == null || scores.length == 0) {         // If input is missing or empty
            return 0;                                       //   return 0 as specified
        }
        // Group entries by student name and collect sum/count statistics of their scores
        Map<String, IntSummaryStatistics> stats = Arrays.stream(scores)
                .collect(Collectors.groupingBy(
                        entry -> entry[0],                          //   key: student name at index 0
                        Collectors.summarizingInt(
                                entry -> Integer.parseInt(entry[1])    //   parse score at index 1
                        )
                ));
        // From each student's statistics, compute floor-average and pick the maximum
        return stats.values().stream()
                .mapToInt(stat -> (int) (stat.getSum() / stat.getCount())) // floor(sum/count)
                .max()                                                 // maximum average
                .orElse(0);                                            // or 0 if no students
    }

    /**
     * Helper to run a single test and print whether it passes or fails.
     */
    private static void checkTest(String testName, String[][] input, int expected) {
        int result = bestAverageGrade(input);                    // Run our method
        if (result == expected) {                                // Compare to expected
            System.out.println(testName + " PASS");              // Print PASS
        } else {
            System.out.println(
                    testName + " FAIL: expected=" + expected         // Print FAIL with details
                            + ", actual=" + result
            );
        }
    }

    /**
     * Main method to execute all test cases, including a large-data check.
     */
    public static void main(String[] args) {
        // Test 1: Example from the prompt
        String[][] tc1 = {
                {"Bobby", "87"},
                {"Charles", "100"},
                {"Eric", "64"},
                {"Charles", "22"}
        };
        checkTest("Test1", tc1, 87);    // Bobby=87, Charles=(100+22)/2=61, Eric=64 → max=87

        // Test 2: Empty input should return 0
        String[][] tc2 = {};
        checkTest("Test2", tc2, 0);

        // Test 3: Two students with different averages
        String[][] tc3 = {
                {"Alice", "90"},
                {"Bob", "80"},
                {"Alice", "100"},
                {"Bob", "85"}
        };
        checkTest("Test3", tc3, 95);    // Alice=(90+100)/2=95, Bob=(80+85)/2=82 → max=95

        // Test 4: Large-data performance test (100,000 entries)
        int n = 100_000;                                    // number of simulated entries
        String[][] tc4 = new String[n][2];                  // allocate the array
        for (int i = 0; i < n; i++) {                       // fill with identical data
            tc4[i][0] = "Student";                          // same student name
            tc4[i][1] = "50";                               // same score
        }
        checkTest("Test4", tc4, 50);    // All 50s → average=50 → max=50
    }
}