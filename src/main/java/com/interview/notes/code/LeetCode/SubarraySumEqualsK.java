package com.interview.notes.code.LeetCode;

import java.util.HashMap;

//https://leetcode.com/problems/subarray-sum-equals-k/
public class SubarraySumEqualsK {

    /**
     * Complexity Analysis
     * Time complexity : O(n2).
     * Space complexity : O(n).
     *
     * @param nums
     * @param k
     * @return
     */
    public int subarraySum(int[] nums, int k) {
        int count = 0;

        int[] sum = new int[nums.length + 1];
        sum[0] = 0;
        for (int i = 1; i <= nums.length; i++)
            sum[i] = sum[i - 1] + nums[i - 1];

        for (int start = 0; start < sum.length; start++) {
            for (int end = start + 1; end < sum.length; end++) {
                if (sum[end] - sum[start] == k)
                    count++;
            }
        }

        return count;
    }

    /**
     * Complexity Anaysis
     * Time complexity : O(n).
     * Space complexity : O(n).
     *
     * @param nums
     * @param k
     * @return
     */
    public static int subarraySumOptimizationHashmap(int[] nums, int k) {
        int count = 0, sum = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (map.containsKey(sum - k))
                count += map.get(sum - k);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }

    public static void main(String[] args) {
        int[] nums = {1,1,1};
        int k = 2;

        System.out.println(subarraySumOptimizationHashmap(nums,k));
    }

}
