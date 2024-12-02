package com.interview.notes.code.year.y2024.oct24.amazon.test22;

import java.util.*;

public class MaxNegativePnL1 {

    public static int getMaxNegativePnL(List<Integer> PnL) {
        int n = PnL.size();
        long totalSum = 0;
        for (int profit : PnL) {
            totalSum += profit;
        }

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        int count = 0;
        long currentSum = 0;

        for (int profit : PnL) {
            currentSum += profit;
            maxHeap.offer(profit);

            while (!maxHeap.isEmpty() && currentSum > totalSum / 2) {
                int maxProfit = maxHeap.poll();
                currentSum -= 2 * maxProfit;
                count++;
            }

            if (currentSum <= 0) {
                count--;
                break;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        // Test cases
        List<List<Integer>> testCases = new ArrayList<>();
        testCases.add(Arrays.asList(1, 1, 1, 1, 1));  // Sample Case 0
        testCases.add(Arrays.asList(5, 2, 3, 5, 2, 3));  // Sample Case 1
        testCases.add(Arrays.asList(5, 3, 1, 2));  // Example from previous question
        testCases.add(Arrays.asList(1));  // Edge case: single element
        testCases.add(Arrays.asList(1000000000, 1000000000));  // Large numbers

        // Large input test
        List<Integer> largeInput = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            largeInput.add(1 + (i % 1000));
        }
        testCases.add(largeInput);

        // Run test cases
        for (int i = 0; i < testCases.size(); i++) {
            List<Integer> testCase = testCases.get(i);
            int result = getMaxNegativePnL(testCase);
            System.out.println("Test Case " + i + ": " + (checkTestCase(testCase, result) ? "PASS" : "FAIL"));
        }
    }

    private static boolean checkTestCase(List<Integer> PnL, int result) {
        int n = PnL.size();
        long totalSum = 0;
        for (int profit : PnL) {
            totalSum += profit;
        }

        // Check if the result is possible
        if (result > n) return false;

        // Check if we can achieve the result
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        maxHeap.addAll(PnL);

        long negativeSum = 0;
        for (int i = 0; i < result; i++) {
            if (maxHeap.isEmpty()) return false;
            negativeSum += maxHeap.poll();
        }

        return negativeSum < totalSum - negativeSum;
    }
}
