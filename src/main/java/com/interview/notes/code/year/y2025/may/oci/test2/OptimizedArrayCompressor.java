package com.interview.notes.code.year.y2025.may.oci.test2;

import java.util.Arrays;
import java.util.List;

public class OptimizedArrayCompressor {

    public static int getMinLength(List<Integer> arr, int k) {
        if (arr == null || arr.isEmpty()) return 0;
        if (arr.size() == 1) return 1;

        // Convert to array for O(1) access
        int[] nums = arr.stream().mapToInt(Integer::intValue).toArray();
        int writePointer = 0;  // Position to write merged results

        for (int readPointer = 1; readPointer < nums.length; readPointer++) {
            // Try to merge with previous number if possible
            if (writePointer >= 0 && (long) nums[writePointer] * nums[readPointer] <= k) {
                nums[writePointer] = (int) ((long) nums[writePointer] * nums[readPointer]);
                writePointer--;  // Move back to try more merges
            } else {
                writePointer++;
                nums[writePointer] = nums[readPointer];
            }
        }

        return writePointer + 1;
    }

    // Test the solution
    public static void main(String[] args) {
        // Test cases
        test(Arrays.asList(1, 2, 1, 3, 6, 1), 6);  // Expected: 2
        test(Arrays.asList(2, 3, 3, 7, 3, 5), 20); // Expected: 3
        test(Arrays.asList(10, 20, 30), 5);        // Expected: 3
        test(List.of(5), 10);                // Expected: 1
    }

    private static void test(List<Integer> arr, int k) {
        System.out.println("Input: " + arr);
        System.out.println("k: " + k);
        System.out.println("Result: " + getMinLength(arr, k));
        System.out.println("------------------------");
    }
}
