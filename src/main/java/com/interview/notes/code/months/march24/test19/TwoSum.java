package com.interview.notes.code.months.march24.test19;

import java.util.Arrays;

public class TwoSum {
    public static int[] findTwoSum(int[] nums, int target) {
        Arrays.sort(nums); // Sorts the array
        
        int left = 0;
        int right = nums.length - 1;
        
        while (left < right) {
            int sum = nums[left] + nums[right];
            
            if (sum == target) {
                return new int[] { nums[left], nums[right] }; // Return values
            } else if (sum < target) {
                left++; // Move towards larger values
            } else {
                right--; // Move towards smaller values
            }
        }
        
        throw new IllegalArgumentException("No two sum solution");
    }
    
    public static void main(String[] args) {
        int[] nums = {7, 2, 11, 15};
        int target = 9;
        
        // This will print the values that sum up to the target
        int[] result = findTwoSum(nums, target);
        System.out.println("Values: " + result[0] + ", " + result[1]);
    }
}
