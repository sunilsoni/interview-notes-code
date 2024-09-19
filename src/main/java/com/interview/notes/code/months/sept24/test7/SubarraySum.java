package com.interview.notes.code.months.sept24.test7;

import java.util.ArrayList;
import java.util.List;

public class SubarraySum {

    // Method to find the subarray with the target sum
    public static List<Integer> findSubarrayWithTargetSum(List<Integer> nums, int target) {
        int start = 0;
        int currentSum = 0;

        for (int end = 0; end < nums.size(); end++) {
            currentSum += nums.get(end); // Add current element to the sum

            // While currentSum exceeds the target, move the start pointer to reduce the sum
            while (currentSum > target && start <= end) {
                currentSum -= nums.get(start); // Subtract the start element
                start++; // Move start forward
            }

            // If currentSum matches the target, return the subarray
            if (currentSum == target) {
                return nums.subList(start, end + 1); // Return the subarray as a list
            }
        }

        return new ArrayList<>(); // Return empty list if no subarray is found
    }

    // Main method to test the solution
    public static void main(String[] args) {
        // Test case 1: Target sum = 5, expected output: [1, 4]
        List<Integer> nums = List.of(1, 4, 2, 3, 7, 8);
        int target = 5;

        List<Integer> result = findSubarrayWithTargetSum(nums, target);

        // Print result
        if (!result.isEmpty()) {
            System.out.println("Subarray with target sum " + target + ": " + result);
        } else {
            System.out.println("No subarray with target sum " + target);
        }
    }
}
