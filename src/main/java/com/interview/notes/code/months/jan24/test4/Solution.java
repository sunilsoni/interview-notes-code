package com.interview.notes.code.months.jan24.test4;


//https://leetcode.com/problems/remove-duplicates-from-sorted-array/solutions/
public class Solution {
    /**
     * Removes duplicates from a sorted array.
     *
     * @param nums The input array sorted in non-decreasing order.
     * @return The number of unique elements in the array.
     */
    public static int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;

        int k = 1; // Initialize k to 1 since the first element is always unique

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1]) {
                nums[k] = nums[i];
                k++;
            }
        }

        return k; // Return the number of unique elements
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 2};
        int k = removeDuplicates(nums);
        System.out.println("Number of unique elements: " + k);
        // Output the modified array
        for (int i = 0; i < k; i++) {
            System.out.print(nums[i] + " ");
        }
    }
}
