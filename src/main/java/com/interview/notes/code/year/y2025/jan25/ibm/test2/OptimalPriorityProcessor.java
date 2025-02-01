package com.interview.notes.code.year.y2025.jan25.ibm.test2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class OptimalPriorityProcessor {

    public static List<Integer> getOptimalPriority(List<Integer> priority) {
        // Create a mutable copy of the original list
        List<Integer> result = new ArrayList<>(priority);
        int n = result.size();

        // Separate even and odd numbers
        List<Integer> evenTasks = new ArrayList<>();
        List<Integer> oddTasks = new ArrayList<>();

        for (int num : result) {
            if (num % 2 == 0) {
                evenTasks.add(num);
            } else {
                oddTasks.add(num);
            }
        }

        // Sort even and odd tasks
        Collections.sort(evenTasks);
        Collections.sort(oddTasks);

        // Merge tasks to create lexicographically smallest sequence
        List<Integer> mergedTasks = new ArrayList<>();
        int evenIndex = 0, oddIndex = 0;

        while (evenIndex < evenTasks.size() && oddIndex < oddTasks.size()) {
            // Always prefer the smaller task type
            if (evenTasks.get(evenIndex) <= oddTasks.get(oddIndex)) {
                mergedTasks.add(evenTasks.get(evenIndex++));
                if (oddIndex < oddTasks.size()) {
                    mergedTasks.add(oddTasks.get(oddIndex++));
                }
            } else {
                mergedTasks.add(oddTasks.get(oddIndex++));
                if (evenIndex < evenTasks.size()) {
                    mergedTasks.add(evenTasks.get(evenIndex++));
                }
            }
        }

        // Add remaining tasks
        mergedTasks.addAll(evenTasks.subList(evenIndex, evenTasks.size()));
        mergedTasks.addAll(oddTasks.subList(oddIndex, oddTasks.size()));

        return mergedTasks;
    }

    // Main method for testing
    public static void main(String[] args) {
        // Test Case 1
        List<Integer> priority1 = Arrays.asList(2, 4, 6, 4, 3, 2);
        List<Integer> result1 = getOptimalPriority(priority1);
        System.out.println("Test Case 1: " + result1);

        // Test Case 2
        List<Integer> priority2 = Arrays.asList(0, 7, 0, 9);
        List<Integer> result2 = getOptimalPriority(priority2);
        System.out.println("Test Case 2: " + result2);

        // Test Case 3
        List<Integer> priority3 = Arrays.asList(9, 4, 8, 6, 3);
        List<Integer> result3 = getOptimalPriority(priority3);
        System.out.println("Test Case 3: " + result3);

        // Problematic Large Inputs
        testLargeInput(Arrays.asList(4, 1, 1, 7, 3, 8, 5, 6, 5, 2, 3, 7, 0, 2, 1, 5, 4, 5, 6, 6, 3, 4, 2, 3, 0, 6, 0, 0, 5, 4, 2, 4, 4, 4, 2, 3, 4, 4, 9, 5, 3, 0, 9, 7, 3, 9, 9, 0, 3, 0, 7, 1));

        testLargeInput(Arrays.asList(6, 9, 0, 5, 0, 4, 4, 6, 0, 7, 8, 3, 3, 6, 6, 3, 2, 3, 5, 4, 2, 3, 6, 9, 3, 3, 2, 3, 8, 6, 3, 3, 1, 9, 1, 7, 8, 1, 0, 1, 6, 9, 8, 1, 7, 0, 1, 5, 2, 6, 1, 6, 6, 3, 2, 9, 6, 8, 6, 8, 6, 5, 8, 1, 4, 2, 3, 7, 5, 9, 1, 7, 2, 9, 5, 5, 6, 3, 2, 5, 9, 8, 4, 5, 6, 5, 2, 8, 7, 6, 7, 2, 8, 4, 8, 9, 5, 9, 4, 6, 6, 2, 4, 8, 3, 6, 9, 0, 1, 4, 1, 3, 1, 2, 9, 4, 9, 7, 9, 6, 3, 3, 3, 6, 1, 3, 0, 9, 6, 6, 8, 4, 8, 1, 9, 4, 3, 8, 2, 5, 1, 9, 1, 5, 7, 8, 3, 2, 0, 2, 6, 8, 8, 4, 4, 4, 9, 8, 1, 1, 0, 2, 5, 4, 0, 7, 0, 8, 6, 6, 9, 9, 1, 8, 4, 1, 3, 3, 8, 2, 1, 4, 3, 5, 0, 2, 6, 6, 7, 8, 7, 8));
    }

    // Helper method to test large inputs
    private static void testLargeInput(List<Integer> input) {
        System.out.println("\nTesting Large Input:");
        System.out.println("Input Size: " + input.size());

        long startTime = System.nanoTime();
        List<Integer> result = getOptimalPriority(input);
        long endTime = System.nanoTime();

        System.out.println("Result Size: " + result.size());
        System.out.println("Execution Time: " + (endTime - startTime) / 1_000_000 + " ms");
    }
}