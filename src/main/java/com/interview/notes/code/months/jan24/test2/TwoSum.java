package com.interview.notes.code.months.jan24.test2;

import java.util.*;

/*
Find the indices of two elements in a given array such that their sum equals a target value
Input: nums = [3,6,12,13], target = 18
 */
public class TwoSum {
    public static int[] findTwoSumIndices(int[] nums, int target) {
        Map<Integer, Integer> numToIndex = new HashMap<>();
        
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            
            if (numToIndex.containsKey(complement)) {
                return new int[] { numToIndex.get(complement), i };
            }
            
            numToIndex.put(nums[i], i);
        }
        
        return new int[0]; // No solution found
    }

    public static void main(String[] args) {
        int[] nums = { 3, 6, 12, 13 };
        int target = 18;
        
        int[] result = findTwoSumIndices(nums, target);
        
        if (result.length == 2) {
            System.out.println("Indices of the two elements: " + result[0] + " and " + result[1]);
        } else {
            System.out.println("No solution found.");
        }
    }
}
