package com.interview.notes.code.year.y2024.nov24.test5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
Given an array of distinct integers candidates and a target integer target, return a list of all unique combinations of candidates where the chosen numbers sum to target. You may return the combinations in any order.
The same number may be chosen from candidates an unlimited number of times. Two combinations are unique if the
frequency
of at least one of the chosen numbers is different.

The test cases are generated such that the number of unique combinations that sum up to target is less than 150 combinations for the given input.

Example 1:
Input: candidates = [2,3,6,7], target = 7
Output: [[2,2,3],[7]]Explanation:2 and 3 are candidates, and 2 + 2 + 3 = 7. Note that 2 can be used multiple times.7 is a candidate, and 7 = 7.These are the only two combinations.
Example 2:
Input: candidates = [2,3,5], target = 8
Output: [[2,2,2,2],[2,3,3],[3,5]]
Example 3:
Input: candidates = [2], target = 1Output: []

Constraints:
1 <= candidates.length <= 30
2 <= candidates[i] <= 40
All elements of candidates are distinct.
1 <= target <= 40
 */
public class CombinationSumSolver {

    /**
     * Main method to run test cases.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        CombinationSumSolver solver = new CombinationSumSolver();

        // Define test cases
        List<TestCase> testCases = new ArrayList<>();

        // Example 1
        testCases.add(new TestCase(
                new int[]{2, 3, 6, 7},
                7,
                Arrays.asList(
                        Arrays.asList(2, 2, 3),
                        List.of(7)
                )
        ));

        // Example 2
        testCases.add(new TestCase(
                new int[]{2, 3, 5},
                8,
                Arrays.asList(
                        Arrays.asList(2, 2, 2, 2),
                        Arrays.asList(2, 3, 3),
                        Arrays.asList(3, 5)
                )
        ));

        // Example 3
        testCases.add(new TestCase(
                new int[]{2},
                1,
                new ArrayList<>()
        ));

        // Additional Test Cases

        // Edge Case: Target equals a candidate
        testCases.add(new TestCase(
                new int[]{1, 2, 3},
                3,
                Arrays.asList(
                        Arrays.asList(1, 1, 1),
                        Arrays.asList(1, 2),
                        List.of(3)
                )
        ));

        // Edge Case: No possible combinations
        testCases.add(new TestCase(
                new int[]{5, 10},
                3,
                new ArrayList<>()
        ));

        // Large Input Case
        testCases.add(new TestCase(
                new int[]{2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20,
                        21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32},
                40,
                null // We will not specify expected output due to its size
        ));

        // Run Test Cases
        int passed = 0;
        for (int i = 0; i < testCases.size(); i++) {
            TestCase tc = testCases.get(i);
            List<List<Integer>> output = solver.combinationSum(tc.candidates, tc.target);
            boolean isPass;
            if (tc.expectedOutput == null) {
                // For large input, just ensure the number of combinations is as expected
                isPass = output.size() < 150;
            } else {
                isPass = compareCombinationLists(output, tc.expectedOutput);
            }
            if (isPass) {
                System.out.println("Test Case " + (i + 1) + ": PASS");
                passed++;
            } else {
                System.out.println("Test Case " + (i + 1) + ": FAIL");
                System.out.println("Expected: " + tc.expectedOutput);
                System.out.println("Got: " + output);
            }
        }

        // Summary
        System.out.println("\nTotal Passed: " + passed + "/" + testCases.size());
    }

    /**
     * Helper method to compare two lists of combinations.
     *
     * @param output         The output from the algorithm.
     * @param expectedOutput The expected output.
     * @return True if both lists contain the same combinations, false otherwise.
     */
    private static boolean compareCombinationLists(List<List<Integer>> output, List<List<Integer>> expectedOutput) {
        if (output.size() != expectedOutput.size()) {
            return false;
        }

        // Convert lists to list of strings for easy comparison
        List<String> outputStrings = new ArrayList<>();
        for (List<Integer> combination : output) {
            List<Integer> sorted = new ArrayList<>(combination);
            sorted.sort(Integer::compareTo);
            outputStrings.add(sorted.toString());
        }

        List<String> expectedStrings = new ArrayList<>();
        for (List<Integer> combination : expectedOutput) {
            List<Integer> sorted = new ArrayList<>(combination);
            sorted.sort(Integer::compareTo);
            expectedStrings.add(sorted.toString());
        }

        // Sort both lists to ensure order does not affect comparison
        outputStrings.sort(String::compareTo);
        expectedStrings.sort(String::compareTo);

        return outputStrings.equals(expectedStrings);
    }

    /**
     * Finds all unique combinations in candidates where the numbers sum to target.
     * Each number in candidates may be used an unlimited number of times.
     *
     * @param candidates Array of distinct integers.
     * @param target     Target sum.
     * @return List of unique combinations.
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (candidates == null || candidates.length == 0) {
            return result;
        }
        Arrays.sort(candidates); // Sort to help with pruning
        backtrack(candidates, target, 0, new ArrayList<>(), result);
        return result;
    }

    /**
     * Helper method to perform backtracking.
     *
     * @param candidates Sorted array of candidates.
     * @param target     Remaining target sum.
     * @param start      Current index in candidates.
     * @param current    Current combination being built.
     * @param result     List to store all valid combinations.
     */
    private void backtrack(int[] candidates, int target, int start,
                           List<Integer> current, List<List<Integer>> result) {
        if (target == 0) {
            // Found a valid combination
            result.add(new ArrayList<>(current));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            // If the current candidate exceeds the target, no need to proceed further
            if (candidates[i] > target) {
                break;
            }
            current.add(candidates[i]);
            // Since the same number can be used unlimited times, 'i' is passed again
            backtrack(candidates, target - candidates[i], i, current, result);
            // Backtrack by removing the last added number
            current.remove(current.size() - 1);
        }
    }

    /**
     * Inner class to represent a test case.
     */
    static class TestCase {
        int[] candidates;
        int target;
        List<List<Integer>> expectedOutput;

        TestCase(int[] candidates, int target, List<List<Integer>> expectedOutput) {
            this.candidates = candidates;
            this.target = target;
            this.expectedOutput = expectedOutput;
        }
    }
}
