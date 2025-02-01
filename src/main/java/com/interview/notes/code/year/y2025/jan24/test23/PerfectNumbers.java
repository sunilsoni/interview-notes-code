package com.interview.notes.code.year.y2025.jan24.test23;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PerfectNumbers {

    private static final long[] PERFECT_NUMBERS = {
            6L,
            28L,
            496L,
            8128L,
            33550336L,
            8589869056L,
            137438691328L,
            2305843008139952128L
    };

    public static void main(String[] args) {
        // Test cases with expected results
        Object[][] testCases = {
                {6L, Arrays.asList(6L)},
                {28L, Arrays.asList(6L, 28L)},
                {100L, Arrays.asList(6L, 28L)},
                {496L, Arrays.asList(6L, 28L, 496L)},
                {8128L, Arrays.asList(6L, 28L, 496L, 8128L)},
                {33550336L, Arrays.asList(6L, 28L, 496L, 8128L, 33550336L)},
                {8589869056L, Arrays.asList(6L, 28L, 496L, 8128L, 33550336L, 8589869056L)},
                {137438691328L, Arrays.asList(6L, 28L, 496L, 8128L, 33550336L, 8589869056L, 137438691328L)},
                {2305843008139952128L, Arrays.asList(6L, 28L, 496L, 8128L, 33550336L, 8589869056L, 137438691328L, 2305843008139952128L)},
                {1L, Collections.emptyList()},
                {0L, Collections.emptyList()},
                {7L, Arrays.asList(6L)},
                {5000L, Arrays.asList(6L, 28L, 496L, 8128L)}
        };

        for (Object[] testCase : testCases) {
            long n = (Long) testCase[0];
            @SuppressWarnings("unchecked")
            List<Long> expected = (List<Long>) testCase[1];
            List<Long> actual = getPerfectNumbersUpTo(n);
            boolean passed = actual.equals(expected);
            System.out.println("Test case N=" + n + ": " + (passed ? "PASS" : "FAIL"));
            if (!passed) {
                System.out.println("Expected: " + expected);
                System.out.println("Actual:   " + actual);
            }
        }
    }

    public static List<Long> getPerfectNumbersUpTo(long n) {
        List<Long> result = new ArrayList<>();
        for (long num : PERFECT_NUMBERS) {
            if (num <= n) {
                result.add(num);
            } else {
                break; // The array is sorted, so no need to check further
            }
        }
        return result;
    }
}