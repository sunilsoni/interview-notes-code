package com.interview.notes.code.year.y2025.april.common.test7;

import java.util.HashMap;
import java.util.Map;

class Solution {
    public int[] twoSum(int[] nums, int target) {
        // Create a HashMap to store number and its index
        Map<Integer, Integer> numMap = new HashMap<>();
        
        // Iterate through the array
        for (int i = 0; i < nums.length; i++) {
            // Calculate the complement needed
            int complement = target - nums[i];
            
            // Check if complement exists in HashMap
            if (numMap.containsKey(complement)) {
                // Return current index and complement's index
                return new int[] {numMap.get(complement), i};
            }
            
            // Add current number and its index to HashMap
            numMap.put(nums[i], i);
        }
        
        // No solution found (though problem states there will always be one)
        return new int[] {};
    }
}
