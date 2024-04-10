package com.interview.notes.code.months.april24.test4;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public int[] twoSum(int[] nums, int target) {
        // Step 1: Create a hash map to store numbers and their indices
        Map<Integer, Integer> numMap = new HashMap<>();
        // Step 2: Iterate through the array elements
        for (int i = 0; i < nums.length; i++) {
            // Step 3: Calculate complement
            int complement = target - nums[i];
            // Step 4: Check if the complement exists in the map
            if (numMap.containsKey(complement)) {
                return new int[] { numMap.get(complement), i }; // Pair found
            }
            // Step 5: Store the index of the current element
            numMap.put(nums[i], i);
        }
        // Should never be reached if input has exactly one solution
        return new int[] { -1, -1 }; 
    }

    // Step 6: Main method for testing
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        int[] indices = solution.twoSum(nums, target);
        System.out.println("[" + indices[0] + ", " + indices[1] + "]");
    }
}
