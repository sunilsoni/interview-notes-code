package com.interview.notes.code.year.y2025.august.DoorDash.test1;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DasherScheduler {

    // Main method to solve the delivery scheduling problem
    public static int findMaximumEarnings(int startTime, int endTime, int[] dStarts, int[] dEnds, int[] dPays) {
        // Create a list of Delivery objects to store all delivery information
        List<Delivery> deliveries = IntStream.range(0, dStarts.length)
                .mapToObj(i -> new Delivery(dStarts[i], dEnds[i], dPays[i]))
                .collect(Collectors.toList());

        // Sort deliveries by end time to process them in chronological order
        deliveries.sort(Comparator.comparingInt(d -> d.endTime));

        // Dynamic programming array to store maximum earnings at each step
        int[] dp = new int[deliveries.size()];

        // Initialize first delivery's earnings
        dp[0] = (deliveries.get(0).startTime >= startTime &&
                deliveries.get(0).endTime <= endTime) ? deliveries.get(0).pay : 0;

        // Iterate through all deliveries to find maximum earnings
        for (int i = 1; i < deliveries.size(); i++) {
            // Get current delivery
            Delivery current = deliveries.get(i);

            // Skip if delivery is outside working hours
            if (current.startTime < startTime || current.endTime > endTime) {
                dp[i] = dp[i - 1];
                continue;
            }

            // Find last non-overlapping delivery
            int lastNonOverlap = findLastNonOverlapping(deliveries, i);

            // Calculate maximum earnings including current delivery
            int includeCurrent = current.pay + (lastNonOverlap >= 0 ? dp[lastNonOverlap] : 0);

            // Take maximum of including current delivery or excluding it
            dp[i] = Math.max(includeCurrent, dp[i - 1]);
        }

        // Return maximum earnings from last position in dp array
        return dp[deliveries.size() - 1];
    }

    // Helper method to find the last non-overlapping delivery
    private static int findLastNonOverlapping(List<Delivery> deliveries, int currentIndex) {
        Delivery current = deliveries.get(currentIndex);

        // Search for last delivery that doesn't overlap with current
        for (int i = currentIndex - 1; i >= 0; i--) {
            if (deliveries.get(i).endTime <= current.startTime) {
                return i;
            }
        }
        return -1;
    }

    // Main method for testing
    public static void main(String[] args) {
        // Test Case 1: Simple case
        test(1, 8, 20,
                new int[]{10, 12, 15},
                new int[]{11, 14, 17},
                new int[]{5, 8, 10},
                18);

        // Test Case 2: Overlapping deliveries
        test(2, 9, 18,
                new int[]{10, 10, 13, 15},
                new int[]{12, 11, 15, 17},
                new int[]{5, 3, 8, 7},
                15);

        // Test Case 3: Large dataset
        int[] largeStarts = IntStream.range(0, 1000).map(i -> i * 2).toArray();
        int[] largeEnds = IntStream.range(0, 1000).map(i -> i * 2 + 1).toArray();
        int[] largePays = IntStream.range(0, 1000).map(i -> 5).toArray();
        test(3, 0, 2000, largeStarts, largeEnds, largePays, 5000);
    }

    // Helper method to run and validate test cases
    private static void test(int testCase, int startTime, int endTime,
                             int[] dStarts, int[] dEnds, int[] dPays, int expected) {
        int result = findMaximumEarnings(startTime, endTime, dStarts, dEnds, dPays);
        System.out.printf("Test Case %d: %s (Expected: %d, Got: %d)%n",
                testCase,
                result == expected ? "PASS" : "FAIL",
                expected,
                result);
    }

    // Delivery class to store delivery information
    static class Delivery {
        int startTime;
        int endTime;
        int pay;

        Delivery(int startTime, int endTime, int pay) {
            this.startTime = startTime;
            this.endTime = endTime;
            this.pay = pay;
        }
    }
}
