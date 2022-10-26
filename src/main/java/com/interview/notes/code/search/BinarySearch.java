package com.interview.notes.code.search;


//https://leetcode.com/problems/binary-search/discuss/1883825/JavaC%2B%2B-As-Simple-as-You-Think
public class BinarySearch {
    public static void main(String[] args) {
        int[] nums = {-1, 0, 3, 5, 9, 12};
        int target = 9;

        System.out.println(search(nums, target));
        System.out.println(recursiveSearch(nums, target));
    }

    /**
     * ANALYSIS :-
     * Time Complexity :- BigO(logN)
     * Space Complexity :- BigO(1)
     */
    public static int search(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (nums[mid] == target) return mid;

            else if (target > nums[mid]) low = mid + 1;
            else high = mid - 1;
        }
        return -1;
    }

    /**
     * ANALYSIS :-
     * Time Complexity :- BigO(logN)
     * Space Complexity :- BigO(logN)
     */
    public static int recursive(int[] nums, int low, int high, int target) {
        if (low > high) return -1;
        int mid = (low + high) / 2;

        if (nums[mid] == target) return mid;

        if (target > nums[mid]) return recursive(nums, mid + 1, high, target);
        else return recursive(nums, low, mid - 1, target);
    }

    public static int recursiveSearch(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        return recursive(nums, low, high, target);
    }

}
