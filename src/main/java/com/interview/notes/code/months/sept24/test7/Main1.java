package com.interview.notes.code.months.sept24.test7;

import java.util.Arrays;

/*

 Problem Description:
Given a non-empty array nums containing only positive integers, find if you can partition the array into two subsets such that the sum of elements in both subsets is equal.
#####Partition Equal Subset Sum
######Problem Description:
Given a non-empty array nums containing only positive integers, find if you can partition the array into two subsets such that the sum of elements in both subsets is equal.
Input:
`nums` an array of positive integers
Output:
Return true if you can partition the array into two subsets with equal sum, otherwise false.


Example 1:
Input: nums = [1, 5, 11, 5]
Output: true
Explanation: The array can be partitioned as [1, 5, 5] and [11].
Example 2:
Input: nums = [1, 2, 3, 5]
Output: false
Explanation: The array cannot be partitioned into equal sum subsets.
Example 3:
Input: nums = [2, 2, 3, 5]
Output: false
Explanation: The array cannot be partitioned into equal sum subsets.
constraints:
1 <= nums.length <= 200
1 <= nums[i] <= 100

 */
public class Main1 {

    /**
     * Determines if the array can be partitioned into two subsets with equal sum.
     *
     * @param nums array of positive integers
     * @return true if the array can be partitioned into two subsets with equal sum, otherwise false
     */
    public static boolean canPartition(int[] nums) {
        int totalSum = 0;
        // Calculate the total sum of the array
        for (int num : nums) {
            totalSum += num;
        }
        // If the total sum is odd, it's not possible to partition into two equal subsets
        if (totalSum % 2 != 0) {
            return false;
        }
        int target = totalSum / 2;
        // Initialize a boolean array for dynamic programming
        boolean[] dp = new boolean[target + 1];
        dp[0] = true; // Base case: zero sum is always achievable
        // Populate the dp array
        for (int num : nums) {
            // Traverse from target to num to avoid using the same element multiple times
            for (int i = target; i >= num; i--) {
                dp[i] = dp[i] || dp[i - num];
            }
        }
        return dp[target];
    }

    public static void main(String[] args) {
        // Test cases
        int[][] testCases = {
                {1, 5, 11, 5},
                {1, 2, 3, 5},
                {2, 2, 3, 5}
        };
        boolean[] expectedResults = {true, false, false};

        for (int i = 0; i < testCases.length; i++) {
            int[] nums = testCases[i];
            boolean result = canPartition(nums);
            System.out.println("Test Case " + (i + 1) + ": " + Arrays.toString(nums));
            System.out.println("Expected Result: " + expectedResults[i]);
            System.out.println("Actual Result: " + result);
            System.out.println("Test " + (result == expectedResults[i] ? "Passed" : "Failed"));
            System.out.println();
        }

        // Additional test cases
        // You can add more test cases here to test edge scenarios
    }
}
