package com.interview.notes.code.year.y2024.sept24.test6;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Test cases
        List<List<Integer>> testCases = Arrays.asList(
                Arrays.asList(3, 5),  // Example #1
                Arrays.asList(10, 10, 10),  // Example #2
                Arrays.asList(1, 2, 3),
                Arrays.asList(5, 5, 5, 5),
                Arrays.asList()
        );

        for (int i = 0; i < testCases.size(); i++) {
            List<Integer> testCase = testCases.get(i);
            int result = solve(testCase);
            System.out.println("Test Case " + (i + 1) + ": " + testCase);
            System.out.println("Result: " + result);
            System.out.println();
        }
    }

    public static int solve(List<Integer> h) {
        if (h == null || h.isEmpty()) {
            return 0;
        }

        int maxHeight = h.stream().mapToInt(Integer::intValue).max().orElse(0);
        int totalBlocksNeeded = 0;
        for (int towerHeight : h) {
            totalBlocksNeeded += maxHeight - towerHeight;
        }

        return totalBlocksNeeded;
    }
}
