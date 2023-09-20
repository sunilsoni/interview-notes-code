package com.interview.notes.code.months.july23.test9;

class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test 1
        int[] nums1 = {4, 5, 6, 7, 0, 1, 2};
        int target1 = 0;
        int result1 = solution.search(nums1, target1);
        System.out.println(result1); // Expected output is 4

        // Test 2
        int[] nums2 = {4, 5, 6, 7, 0, 1, 2};
        int target2 = 3;
        int result2 = solution.search(nums2, target2);
        System.out.println(result2); // Expected output is -1

        // Test 3
        int[] nums3 = {1};
        int target3 = 0;
        int result3 = solution.search(nums3, target3);
        System.out.println(result3); // Expected output is -1
    }

    public int search(int[] nums, int target) {
        int pivot = findPivot(nums);

        if (pivot == -1) {
            // the array is not rotated
            return binarySearch(nums, 0, nums.length - 1, target);
        }

        if (nums[pivot] == target) {
            return pivot;
        }

        if (nums[0] <= target) {
            return binarySearch(nums, 0, pivot - 1, target);
        }

        return binarySearch(nums, pivot + 1, nums.length - 1, target);
    }

    int findPivot(int[] nums) {
        int start = 0;
        int end = nums.length - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (mid < end && nums[mid] > nums[mid + 1]) {
                return mid;
            }

            if (mid > start && nums[mid] < nums[mid - 1]) {
                return mid - 1;
            }

            if (nums[mid] <= nums[start]) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        return -1;
    }

    int binarySearch(int[] nums, int start, int end, int target) {
        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return -1;
    }


}
