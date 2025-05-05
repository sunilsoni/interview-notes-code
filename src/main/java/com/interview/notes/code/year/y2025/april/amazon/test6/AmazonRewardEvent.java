package com.interview.notes.code.year.y2025.april.amazon.test6;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AmazonRewardEvent {

    public static int countPossibleWinners(List<Integer> initialRewards) {
        int n = initialRewards.size();

        // Find the current highest reward
        int maxReward = initialRewards.stream().max(Integer::compare).orElse(0);

        // Count customers who can potentially achieve highest score by winning
        return (int) initialRewards.stream()
                .filter(reward -> reward + n >= maxReward)
                .count();
    }

    public static void main(String[] args) {
        List<List<Integer>> testCases = Arrays.asList(
                Arrays.asList(1, 3, 4), // Expected: 1
                Arrays.asList(8, 10, 9), // Expected: 2
                Arrays.asList(5, 7, 9, 11), // Expected: 1
                // Edge cases:
                Arrays.asList(1), // Single customer (Expected: 1)
                Arrays.asList(10, 10, 10), // All equal (Expected: 3)
                Arrays.asList(1, 100000, 99999), // Large values (Expected: 2)
                // Large input test case:
                IntStream.rangeClosed(1, 100000).boxed().collect(Collectors.toList()) // Large input
        );

        int[] expectedResults = {1, 2, 1, 1, 3, 2, 100000};

        for (int i = 0; i < testCases.size(); i++) {
            long startTime = System.currentTimeMillis();
            int result = countPossibleWinners(testCases.get(i));
            long endTime = System.currentTimeMillis();

            System.out.printf("Test %d: %s (Expected: %d, Actual: %d, Time: %dms)%n",
                    i + 1,
                    result == expectedResults[i] ? "PASS" : "FAIL",
                    expectedResults[i],
                    result,
                    endTime - startTime);
        }
    }
}
