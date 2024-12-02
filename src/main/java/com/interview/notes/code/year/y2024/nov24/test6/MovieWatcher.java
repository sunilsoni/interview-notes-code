package com.interview.notes.code.year.y2024.nov24.test6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MovieWatcher {

    /**
     * Calculates the minimum number of days required to watch all movies.
     *
     * @param durations List of movie durations in hours.
     * @return Minimum number of days to watch all movies.
     */
    public static int minimumDays(List<Double> durations) {
        // Convert durations to integers (centihours) to avoid floating-point precision issues
        List<Integer> durationInt = new ArrayList<>();
        for (double d : durations) {
            durationInt.add((int) Math.round(d * 100));
        }

        // Sort the durations in ascending order
        Collections.sort(durationInt);

        int days = 0;
        int left = 0;
        int right = durationInt.size() - 1;

        while (left <= right) {
            if (left == right) {
                // Only one movie left; it will be watched alone
                days++;
                break;
            }

            // Check if the shortest and longest movies can be paired
            if (durationInt.get(left) + durationInt.get(right) <= 300) {
                // Pair the movies
                left++;
                right--;
            } else {
                // Can't pair; watch the longer movie alone
                right--;
            }
            days++;
        }

        return days;
    }

    public static void main(String[] args) {
        // Flag to track if all tests pass
        boolean allTestsPassed = true;

        // Test Case 1: Example provided
        List<Double> durations1 = Arrays.asList(1.90, 1.04, 1.25, 2.5, 1.75);
        int result1 = minimumDays(durations1);
        if (result1 == 3) {
            System.out.println("Test Case 1 Passed");
        } else {
            System.out.println("Test Case 1 Failed: Expected 3, Got " + result1);
            allTestsPassed = false;
        }

        // Test Case 2: All movies have minimum duration
        List<Double> durations2 = Arrays.asList(1.01, 1.01, 1.01, 1.01);
        int result2 = minimumDays(durations2);
        if (result2 == 2) {
            System.out.println("Test Case 2 Passed");
        } else {
            System.out.println("Test Case 2 Failed: Expected 2, Got " + result2);
            allTestsPassed = false;
        }

        // Test Case 3: All movies have maximum duration
        List<Double> durations3 = Arrays.asList(3.00, 3.00, 3.00);
        int result3 = minimumDays(durations3);
        if (result3 == 3) {
            System.out.println("Test Case 3 Passed");
        } else {
            System.out.println("Test Case 3 Failed: Expected 3, Got " + result3);
            allTestsPassed = false;
        }

        // Test Case 4: Mix of durations where pairing is possible
        List<Double> durations4 = Arrays.asList(1.50, 1.50, 2.00, 1.01);
        int result4 = minimumDays(durations4);
        if (result4 == 2) {
            System.out.println("Test Case 4 Passed");
        } else {
            System.out.println("Test Case 4 Failed: Expected 2, Got " + result4);
            allTestsPassed = false;
        }

        // Test Case 5: Edge case where no pairing is possible
        List<Double> durations5 = Arrays.asList(2.99, 2.99, 2.99);
        int result5 = minimumDays(durations5);
        if (result5 == 3) {
            System.out.println("Test Case 5 Passed");
        } else {
            System.out.println("Test Case 5 Failed: Expected 3, Got " + result5);
            allTestsPassed = false;
        }

        // Test Case 6: Large dataset
        List<Double> durations6 = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            durations6.add(1.50); // All durations are 1.50 hours
        }
        int result6 = minimumDays(durations6);
        if (result6 == 50000) {
            System.out.println("Test Case 6 Passed");
        } else {
            System.out.println("Test Case 6 Failed: Expected 50000, Got " + result6);
            allTestsPassed = false;
        }

        // Summary of test results
        if (allTestsPassed) {
            System.out.println("All test cases passed.");
        } else {
            System.out.println("Some test cases failed.");
        }
    }
}
