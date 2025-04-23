package com.interview.notes.code.year.y2025.april.common.test5;

import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;

class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> numMap = new HashMap<>();
        
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            
            if (numMap.containsKey(complement)) {
                return new int[] {numMap.get(complement), i};
            }
            
            numMap.put(nums[i], i);
        }
        
        return new int[] {};
    }
}

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        
        // Test Case 1
        int[] nums1 = {2, 7, 11, 15};
        int target1 = 9;
        System.out.println("Test Case 1:");
        System.out.println("Input: nums = " + Arrays.toString(nums1) + ", target = " + target1);
        System.out.println("Output: " + Arrays.toString(solution.twoSum(nums1, target1)));
        System.out.println();
        
        // Test Case 2
        int[] nums2 = {3, 2, 4};
        int target2 = 6;
        System.out.println("Test Case 2:");
        System.out.println("Input: nums = " + Arrays.toString(nums2) + ", target = " + target2);
        System.out.println("Output: " + Arrays.toString(solution.twoSum(nums2, target2)));
        System.out.println();
        
        // Test Case 3
        int[] nums3 = {3, 3};
        int target3 = 6;
        System.out.println("Test Case 3:");
        System.out.println("Input: nums = " + Arrays.toString(nums3) + ", target = " + target3);
        System.out.println("Output: " + Arrays.toString(solution.twoSum(nums3, target3)));
        
        // Additional test case
        int[] nums4 = {1, 5, 8, 3, 2};
        int target4 = 7;
        System.out.println("\nTest Case 4:");
        System.out.println("Input: nums = " + Arrays.toString(nums4) + ", target = " + target4);
        System.out.println("Output: " + Arrays.toString(solution.twoSum(nums4, target4)));
    }
}
