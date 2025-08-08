package com.interview.notes.code.year.y2025.august.amazon.test3;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class Solution {
    // Main method - entry point of the program
    public static void main(String[] args) {
        Solution solution = new Solution();
        if (solution.doTestsPass()) {
            System.out.println("All tests passed!");
        } else {
            System.out.println("Tests failed!");
        }
    }

    // Method to find lowest prices for each time interval
    private List<Interval> getLowestPrices(List<Interval> vendors) {
        // Create result list to store final intervals with lowest prices
        List<Interval> result = new ArrayList<>();

        // Create TreeMap to store all time points and their corresponding price changes
        TreeMap<Integer, List<Integer>> timePoints = new TreeMap<>();

        // Process each vendor interval
        for (Interval vendor : vendors) {
            // Add price change at start time (add price)
            timePoints.computeIfAbsent(vendor.startTime, k -> new ArrayList<>())
                    .add(vendor.price);
            // Add price change at end time (remove price by adding negative)
            timePoints.computeIfAbsent(vendor.endTime, k -> new ArrayList<>())
                    .add(-vendor.price);
        }

        // Variables to track current active prices and previous time point
        List<Integer> activePrices = new ArrayList<>();
        int prevTime = timePoints.firstKey();

        // Process each time point
        for (var entry : timePoints.entrySet()) {
            int currentTime = entry.getKey();
            List<Integer> priceChanges = entry.getValue();

            // If we have active prices, add interval to result
            if (!activePrices.isEmpty() && prevTime < currentTime) {
                // Find minimum price in active prices
                int minPrice = activePrices.stream()
                        .mapToInt(Integer::intValue)
                        .min()
                        .getAsInt();
                result.add(new Interval(prevTime, currentTime, minPrice));
            }

            // Update active prices based on price changes
            for (int priceChange : priceChanges) {
                if (priceChange > 0) {
                    activePrices.add(priceChange);
                } else {
                    activePrices.remove(Integer.valueOf(-priceChange));
                }
            }

            prevTime = currentTime;
        }

        return result;
    }

    // Test method to verify solution
    private boolean doTestsPass() {
        // Test case 1: Basic overlapping intervals
        var test1Input = List.of(
                new Interval(1, 5, 20),
                new Interval(3, 8, 15),
                new Interval(7, 10, 8)
        );
        var test1Expected = List.of(
                new Interval(1, 3, 20),
                new Interval(3, 7, 15),
                new Interval(7, 10, 8)
        );

        // Test case 2: Large data test
        var test2Input = new ArrayList<Interval>();
        for (int i = 0; i < 1000; i++) {
            test2Input.add(new Interval(i, i + 3, 10 + i));
        }

        // Run tests and verify results
        boolean test1Passed = getLowestPrices(test1Input).equals(test1Expected);
        boolean test2Passed = getLowestPrices(test2Input) != null;

        return test1Passed && test2Passed;
    }

    // Record class to store interval information with startTime, endTime and price
    private record Interval(int startTime, int endTime, int price) {
    }
}
