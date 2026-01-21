package com.interview.notes.code.year.y2026.jan.apple.test1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SubarraySumEqualsK {

    // Method to count subarrays with sum equal to k
    static int subarraySum(int[] nums, int k) {
        // Map stores prefix sum frequencies
        Map<Integer, Integer> prefixMap = new HashMap<>();

        // Initialize: empty subarray has sum 0
        prefixMap.put(0, 1);

        int count = 0;  // Result counter
        int sum = 0;    // Running prefix sum

        // Iterate through each element
        for (int num : nums) {
            // Add current element to running sum
            sum += num;

            // If (sum - k) exists, we found subarrays ending here
            // Because: sum - (sum - k) = k
            count += prefixMap.getOrDefault(sum - k, 0);

            // Store current prefix sum in map
            prefixMap.merge(sum, 1, Integer::sum);
        }

        return count;
    }

    // Test helper method
    static void test(int[] nums, int k, int expected) {
        int result = subarraySum(nums, k);
        String status = result == expected ? "PASS" : "FAIL";
        System.out.println(status + " | Input: " + Arrays.toString(nums) +
                ", k=" + k + " | Expected: " + expected +
                " | Got: " + result);
    }

    public static void main(String[] args) {
        // Test Case 1: Basic case
        test(new int[]{1, 1, 1}, 2, 2);

        // Test Case 2: With negative numbers
        test(new int[]{1, 2, 3}, 3, 2);

        // Test Case 3: Single element equals k
        test(new int[]{1}, 1, 1);

        // Test Case 4: No subarray found
        test(new int[]{1, 2, 3}, 10, 0);

        // Test Case 5: Negative numbers
        test(new int[]{-1, -1, 1}, 0, 1);

        // Test Case 6: All zeros, k=0
        test(new int[]{0, 0, 0}, 0, 6);

        // Test Case 7: Mixed positive and negative
        test(new int[]{3, 4, 7, 2, -3, 1, 4, 2}, 7, 4);

        // Test Case 8: Large data test
        int n = 100000;
        int[] largeArray = new int[n];
        Arrays.fill(largeArray, 1);  // All 1s
        long start = System.currentTimeMillis();
        int largeResult = subarraySum(largeArray, 50);
        long end = System.currentTimeMillis();
        System.out.println("Large test (n=" + n + "): " +
                (largeResult == 99951 ? "PASS" : "FAIL") +
                " | Time: " + (end - start) + "ms");
    }
}