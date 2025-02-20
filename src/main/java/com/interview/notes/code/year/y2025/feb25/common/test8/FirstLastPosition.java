package com.interview.notes.code.year.y2025.feb25.common.test8;

/*

Find first and last position of element in sorted array

int[] inputArray = [5,7,7,8,8,10];
int target = 8;
// op - [3,4]
// op - [-1,-1] if not found
 */
public class FirstLastPosition {
    public static int[] findFirstAndLastPosition(int[] nums, int target) {
        int[] result = {-1, -1};

        // Edge case: empty array or null
        if (nums == null || nums.length == 0) {
            return result;
        }

        // Find first position
        result[0] = findPosition(nums, target, true);

        // If target not found, return [-1, -1]
        if (result[0] == -1) {
            return result;
        }

        // Find last position
        result[1] = findPosition(nums, target, false);

        return result;
    }

    private static int findPosition(int[] nums, int target, boolean isFirst) {
        int left = 0;
        int right = nums.length - 1;
        int position = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                position = mid;
                if (isFirst) {
                    right = mid - 1; // continue searching left
                } else {
                    left = mid + 1;  // continue searching right
                }
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return position;
    }

    public static void main(String[] args) {
        // Test cases
        runTest(new int[]{5, 7, 7, 8, 8, 10}, 8, new int[]{3, 4}, "Basic case");
        runTest(new int[]{5, 7, 7, 8, 8, 10}, 6, new int[]{-1, -1}, "Target not found");
        runTest(new int[]{}, 0, new int[]{-1, -1}, "Empty array");
        runTest(new int[]{1}, 1, new int[]{0, 0}, "Single element array");
        runTest(new int[]{2, 2}, 2, new int[]{0, 1}, "Two same elements");

        // Large data test
        int[] largeArray = new int[1000000];
        for (int i = 0; i < 1000000; i++) {
            largeArray[i] = i / 100; // Creates groups of same numbers
        }
        runTest(largeArray, 100, new int[]{10000, 10099}, "Large array test");
    }

    private static void runTest(int[] input, int target, int[] expected, String testName) {
        int[] result = findFirstAndLastPosition(input, target);
        boolean passed = result[0] == expected[0] && result[1] == expected[1];

        System.out.printf("%s: %s\n", testName, passed ? "PASS" : "FAIL");
        if (!passed) {
            System.out.printf("Expected: [%d,%d], Got: [%d,%d]\n",
                    expected[0], expected[1], result[0], result[1]);
        }
    }
}
