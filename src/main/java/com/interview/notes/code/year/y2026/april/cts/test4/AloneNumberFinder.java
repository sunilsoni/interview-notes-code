package com.interview.notes.code.year.y2026.april.cts.test4;

public class AloneNumberFinder {
    public static void main(String[] args) {
        int[] nums = {10, 6, 5, 8, 11, 12};

        for (int i = 1; i < nums.length - 1; i++) { // skip first & last
            int n = nums[i];
            boolean hasLeftNeighbor = contains(nums, n - 1);
            boolean hasRightNeighbor = contains(nums, n + 1);

            if (!hasLeftNeighbor && !hasRightNeighbor) {
                System.out.println("Alone number: " + n);
            }
        }
    }

    private static boolean contains(int[] arr, int target) {
        for (int num : arr) {
            if (num == target) return true;
        }
        return false;
    }
}
