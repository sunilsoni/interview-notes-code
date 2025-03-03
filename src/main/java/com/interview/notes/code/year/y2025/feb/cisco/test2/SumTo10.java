package com.interview.notes.code.year.y2025.feb.cisco.test2;

import java.util.ArrayList;
import java.util.List;

public class SumTo10 {
    public static void main(String[] args) {
        // The given numbers
        int[] numbers = {1, 3, 2, 4, 7, 9, 3, 6};

        // List to hold all valid combinations
        List<List<Integer>> results = new ArrayList<>();

        // Start the backtracking process
        findSubsets(numbers, 0, 0, 10, new ArrayList<>(), results);

        // Print out the valid combinations
        System.out.println("Combinations that sum to 10:");
        for (List<Integer> combination : results) {
            System.out.println(combination);
        }
    }

    /**
     * Recursively finds subsets of numbers that sum to the target.
     *
     * @param numbers      the array of numbers
     * @param startIndex   the current index in the array
     * @param currentSum   the sum so far
     * @param target       the target sum (10 in this case)
     * @param currentCombo the current combination of numbers
     * @param results      the list to store valid combinations
     */
    private static void findSubsets(int[] numbers, int startIndex, int currentSum,
                                    int target, List<Integer> currentCombo,
                                    List<List<Integer>> results) {
        // Check if we've reached the target sum
        if (currentSum == target) {
            results.add(new ArrayList<>(currentCombo));
            // We continue the search to find other valid combinations
        }
        // Stop if we've used all numbers or exceeded the target sum
        if (startIndex >= numbers.length || currentSum > target) {
            return;
        }
        // Try including each number starting from startIndex
        for (int i = startIndex; i < numbers.length; i++) {
            currentCombo.add(numbers[i]);
            findSubsets(numbers, i + 1, currentSum + numbers[i], target, currentCombo, results);
            // Backtrack: remove the last added number
            currentCombo.remove(currentCombo.size() - 1);
        }
    }
}