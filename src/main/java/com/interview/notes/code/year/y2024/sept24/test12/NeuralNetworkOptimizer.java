package com.interview.notes.code.year.y2024.sept24.test12;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class NeuralNetworkOptimizer {


    public static int getMinOperations(List<Integer> computationalTime) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for (int time : computationalTime) {
            if (time % 2 == 0) {
                maxHeap.add(time);
            }
        }

        int operations = 0;

        while (!maxHeap.isEmpty()) {
            int largestEvenTime = maxHeap.poll();
            while (largestEvenTime % 2 == 0) {
                largestEvenTime /= 2;
                operations++;
                if (largestEvenTime % 2 == 0) {
                    maxHeap.add(largestEvenTime);
                }
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