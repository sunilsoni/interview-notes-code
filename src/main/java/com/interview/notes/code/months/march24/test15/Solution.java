package com.interview.notes.code.months.march24.test15;

import java.util.HashMap;

class Solution {
    public int[] twoSum(int[] nums, int target) {
        // Create a hashmap to store the complement of each element
        HashMap<Integer, Integer> map = new HashMap<>();

        // Iterate through the array
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];

            // Check if the complement exists in the hashmap
            if (map.containsKey(complement)) {
                // Return the indices of the current element and its complement
                return new int[]{map.get(complement), i};
            }

            // Put the current element and its index into the hashmap
            map.put(nums[i], i);
        }

        // If no solution is found, return an empty array
        return new int[0];
    }
}
