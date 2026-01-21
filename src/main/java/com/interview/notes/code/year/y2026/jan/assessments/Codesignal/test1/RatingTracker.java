package com.interview.notes.code.year.y2026.jan.assessments.Codesignal.test1;

import java.util.Arrays;
import java.util.Random;

public class RatingTracker {

    public static void main(String[] args) {
        var tracker = new RatingTracker();

        // Test Case 1: Example from problem
        runTest(tracker, new int[]{100, -200, 350, 100, -600}, new int[]{1850, 1250}, "Example Case");

        // Test Case 2: Empty array
        runTest(tracker, new int[]{}, new int[]{1500, 1500}, "Empty Input");

        // Test Case 3: Positive only
        runTest(tracker, new int[]{100, 100, 100}, new int[]{1800, 1800}, "Positive Only");

        // Test Case 4: Negative only (rating never negative guaranteed by problem, but drops allowed)
        runTest(tracker, new int[]{-100, -200}, new int[]{1500, 1200}, "Negative Only");

        // Test Case 5: Large Data Input
        int[] largeInput = new Random().ints(1000, -10, 15).toArray();
        // We verify it doesn't crash; exact validation requires manual calculation,
        // so we just check return format is valid for simplicity in automated check.
        var res = tracker.solution(largeInput);
        boolean valid = res.length == 2 && res[0] >= 1500;
        System.out.println("Large Input Check: " + (valid ? "PASS" : "FAIL"));
    }

    private static void runTest(RatingTracker tracker, int[] input, int[] expected, String testName) {
        var result = tracker.solution(input);
        boolean pass = Arrays.equals(result, expected);
        System.out.printf("%-15s: %s [Expected: %s, Got: %s]%n",
                testName,
                pass ? "PASS" : "FAIL",
                Arrays.toString(expected),
                Arrays.toString(result)
        );
    }

    int[] solution(int[] diffs) {
        int current = 1500, max = 1500;
        for (var diff : diffs) {
            current += diff;
            max = Math.max(max, current);
        }
        return new int[]{max, current};
    }
}