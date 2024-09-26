package com.interview.notes.code.months.sept24.test11;

import java.util.*;

public class NeuralNetworkOptimizer {

    public static int getMinOperations(List<Integer> computationalTime) {
        int operations = 0;
        Map<Integer, Integer> evenCounts = new TreeMap<>(Collections.reverseOrder());

        // Count occurrences of even computational times
        for (int time : computationalTime) {
            if (time % 2 == 0) {
                evenCounts.put(time, evenCounts.getOrDefault(time, 0) + 1);
            }
        }

        // Process even times from highest to lowest
        while (!evenCounts.isEmpty()) {
            int highestEven = evenCounts.keySet().iterator().next();
            int count = evenCounts.get(highestEven);
            evenCounts.remove(highestEven);

            operations++;

            int newTime = highestEven / 2;
            if (newTime % 2 == 0) {
                evenCounts.put(newTime, evenCounts.getOrDefault(newTime, 0) + count);
            }
        }

        return operations;
    }

    public static void main(String[] args) {
        // Test cases
        List<List<Integer>> testCases = Arrays.asList(
                Arrays.asList(2, 4, 8, 16),
                Arrays.asList(3, 24),
                Arrays.asList(1, 9, 5)
        );

        for (int i = 0; i < testCases.size(); i++) {
            List<Integer> computationalTime = testCases.get(i);
            int result = getMinOperations(computationalTime);
            System.out.println("Test Case " + (i + 1) + ": " + result);
        }
    }
}