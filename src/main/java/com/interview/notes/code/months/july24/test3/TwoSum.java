package com.interview.notes.code.months.july24.test3;

import java.util.HashMap;
import java.util.Map;

public class TwoSum {
    public static int[] findTwoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[]{complement, nums[i]};
            }
            map.put(nums[i], i);
        }

        return new int[0]; // If no such pair is found
    }

    public static void main(String[] args) {
        int[] array = {3, 5, -4, 8, 11, 1, -1, 6};
        int targetSum = 10;

        int[] output = findTwoSum(array, targetSum);
        if (output.length == 2) {
            System.out.println("Output: [" + output[0] + ", " + output[1] + "]");//Output: [11, -1]
        } else {
            System.out.println("No such pair found.");
        }
    }
}
