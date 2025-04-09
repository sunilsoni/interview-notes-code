package com.interview.notes.code.year.y2025.march.GoldmanSachs.test4;

import java.util.HashMap;
import java.util.Map;

/*

### **Problem Statement**

/* Problem Name is &&& Second Smallest &&& PLEASE DO NOT REMOVE THIS LINE.
        ###Instructions to candidate:
        1. Run this code in the REPL to observe its behavior.
        2. The execution entry point is `main()`.
        3. Consider adding some additional tests in `doTestsPass()`.
        4. Implement `secondSmallest()` correctly.
5. If time permits, some possible follow-ups.
        */
public class Solution {

    /**
     * Finds the best (highest) average grade among students.
     * Each score entry is a two-element array: [student name, test score].
     * If no scores are provided, returns 0.
     */
    public static int bestAverageGrade(String[][] scores) {
        if (scores == null || scores.length == 0) return 0;

        // Map to hold student -> [sum, count]
        Map<String, int[]> studentScores = new HashMap<>();

        // Process each score entry
        for (String[] entry : scores) {
            String name = entry[0];
            int score = Integer.parseInt(entry[1]);
            studentScores.putIfAbsent(name, new int[2]);
            int[] sumCount = studentScores.get(name);
            sumCount[0] += score; // cumulative sum
            sumCount[1] += 1;     // count of tests
        }

        // Use Java 8 streams to compute the maximum average (integer division floors automatically)
        return studentScores.values().stream()
                .mapToInt(arr -> arr[0] / arr[1])
                .max()
                .orElse(0);
    }

    /**
     * Simple tests for the bestAverageGrade method.
     * Returns true if all tests pass; otherwise, false.
     */
    public static boolean doTestsPass() {
        boolean testsPass = true;

        // Test case 1: Provided example
        String[][] tc1 = {
                {"Bobby", "87"},
                {"Charles", "100"},
                {"Eric", "64"},
                {"Charles", "22"}
        };
        testsPass &= bestAverageGrade(tc1) == 87;

        // Test case 2: Empty input
        String[][] tc2 = {};
        testsPass &= bestAverageGrade(tc2) == 0;

        // Test case 3: Single student with multiple tests
        String[][] tc3 = {
                {"Alice", "90"},
                {"Alice", "80"}
        };
        // (90 + 80) / 2 = 85
        testsPass &= bestAverageGrade(tc3) == 85;

        // Test case 4: Negative scores
        String[][] tc4 = {
                {"Bob", "-10"},
                {"Bob", "0"},
                {"Alice", "-20"}
        };
        // Bob's average: (-10 + 0) / 2 = -5; Alice's average: -20; best average is -5.
        testsPass &= bestAverageGrade(tc4) == -5;

        // Test case 5: Large data input
        int largeSize = 1000000;
        String[][] tc5 = new String[largeSize][2];
        for (int i = 0; i < largeSize; i++) {
            // Alternate between two students
            if (i % 2 == 0) {
                tc5[i][0] = "Student1";
                tc5[i][1] = "100";  // always 100
            } else {
                tc5[i][0] = "Student2";
                tc5[i][1] = "0";    // always 0
            }
        }
        // Student1's average should be 100, which is the maximum.
        testsPass &= bestAverageGrade(tc5) == 100;

        return testsPass;
    }

    /**
     * Main method to execute tests.
     * It prints "All tests pass" if every test case passes, otherwise "Tests fail."
     */
    public static void main(String[] args) {
        if (doTestsPass()) {
            System.out.println("All tests pass");
        } else {
            System.out.println("Tests fail.");
        }
    }
}