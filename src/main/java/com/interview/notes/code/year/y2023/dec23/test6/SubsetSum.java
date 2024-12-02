package com.interview.notes.code.year.y2023.dec23.test6;

import java.util.ArrayList;
import java.util.List;

public class SubsetSum {

    public static void main(String[] args) {
        int[] numbers = {1, 2, 3, 4, 5};
        int targetSum = 7;

        List<List<Integer>> subsets = findSubsetsWithSum(numbers, targetSum);

        for (List<Integer> subset : subsets) {
            System.out.println(subset);
        }
    }

    public static List<List<Integer>> findSubsetsWithSum(int[] nums, int targetSum) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> currentSubset = new ArrayList<>();

        findSubsets(nums, targetSum, 0, currentSubset, result);
        // Runnable
        return result;
    }

    private static void findSubsets(int[] nums, int targetSum, int index, List<Integer> currentSubset, List<List<Integer>> result) {
        if (targetSum == 0) {
            result.add(new ArrayList<>(currentSubset));
            return;
        }

        if (index == nums.length || targetSum < 0) {
            return;
        }

        // Include the current number in the subset
        currentSubset.add(nums[index]);
        findSubsets(nums, targetSum - nums[index], index + 1, currentSubset, result);

        // Exclude the current number from the subset
        currentSubset.remove(currentSubset.size() - 1);
        findSubsets(nums, targetSum, index + 1, currentSubset, result);
    }
}
