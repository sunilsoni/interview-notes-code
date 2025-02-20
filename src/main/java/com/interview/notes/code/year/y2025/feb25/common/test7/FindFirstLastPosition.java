package com.interview.notes.code.year.y2025.feb25.common.test7;

import java.util.Arrays;

public class FindFirstLastPosition {

    public static void main(String[] args) {
        // Test case 1: Sample input
        testCase(new int[]{5, 7, 7, 8, 8, 10}, 8, new int[]{3, 4});

        // Test case 2: Target not present
        testCase(new int[]{5, 7, 7, 8, 8, 10}, 6, new int[]{-1, -1});

        // Test case 3: Single element
        testCase(new int[]{5}, 5, new int[]{0, 0});

        // Test case 4: All elements same
        testCase(new int[]{8, 8, 8, 8}, 8, new int[]{0, 3});

        // Test case 5: Empty array
        testCase(new int[]{}, 0, new int[]{-1, -1});

        // Test case 6: Target at beginning
        testCase(new int[]{2, 2, 3, 4, 5}, 2, new int[]{0, 1});

        // Test case 7: Target at end
        testCase(new int[]{2, 3, 4, 5, 5}, 5, new int[]{3, 4});

        // Test case 8: Large input simulation (commented to save memory)
        // int[] largeInput = new int[1000000];
        // Arrays.fill(largeInput, 8);
        // testCase(largeInput, 8, new int[]{0, 999999});
    }

    private static void testCase(int[] nums, int target, int[] expected) {
        int[] result = searchRange(nums, target);
        boolean pass = Arrays.equals(result, expected);
        System.out.println("Test case: " + (pass ? "PASS" : "FAIL"));
        if (!pass) {
            System.out.println("Expected: " + Arrays.toString(expected));
            System.out.println("Actual:   " + Arrays.toString(result));
        }
    }

    public static int[] searchRange(int[] nums, int target) {
        int first = findFirst(nums, target);
        if (first == -1) {
            return new int[]{-1, -1};
        }
        int last = findLast(nums, target);
        return new int[]{first, last};
    }

    private static int findFirst(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] == target) {
                if (mid == 0 || nums[mid - 1] != target) {
                    return mid;
                } else {
                    high = mid - 1;
                }
            } else if (nums[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    private static int findLast(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] == target) {
                if (mid == nums.length - 1 || nums[mid + 1] != target) {
                    return mid;
                } else {
                    low = mid + 1;
                }
            } else if (nums[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }
}