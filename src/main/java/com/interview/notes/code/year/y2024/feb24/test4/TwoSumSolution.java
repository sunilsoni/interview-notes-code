package com.interview.notes.code.year.y2024.feb24.test4;

import java.util.HashMap;
import java.util.Map;

public class TwoSumSolution {

    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        int[] indices = twoSum(nums, target);
        System.out.println("Output: [" + indices[0] + ", " + indices[1] + "]");
    }

    // Method to find two indices such that their values add up to target
    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }
            map.put(nums[i], i);
        }
        // Since the problem statement guarantees a solution, this return should never be reached.
        throw new IllegalArgumentException("No two sum solution");
    }
}
