package com.interview.notes.code.year.y2026.jan.assessments.test1;

import java.util.*;

public class SubarraySumEqualsK {

    static long count(int[] nums, int k) {
        // Map stores prefix sum -> list of ending indices
        Map<Long, List<Integer>> prefixIndices = new HashMap<>();

        long prefix = 0;  // Running prefix sum
        long ans = 0;     // Result counter

        // Initialize: prefix sum 0 exists at index -1 (before array starts)
        prefixIndices.put(0L, new ArrayList<>(List.of(-1)));

        System.out.println("Finding subarrays with sum = " + k);
        System.out.println("Array: " + Arrays.toString(nums));
        System.out.println("\n--- Processing ---");

        // Process each element
        for (int i = 0; i < nums.length; i++) {
            prefix += nums[i];  // Update running sum

            long target = prefix - k;  // What prefix sum we need

            // If target exists, we found valid subarrays
            if (prefixIndices.containsKey(target)) {
                List<Integer> startIndices = prefixIndices.get(target);

                // Print each valid subarray
                for (int startIdx : startIndices) {
                    int from = startIdx + 1;  // Subarray starts after prefix end
                    int to = i;               // Subarray ends at current index

                    // Extract and print the subarray
                    int[] subarray = Arrays.copyOfRange(nums, from, to + 1);
                    int sum = Arrays.stream(subarray).sum();

                    System.out.println("Found: " + Arrays.toString(subarray) +
                            " (index " + from + " to " + to + ") = " + sum);
                    ans++;
                }
            }

            // Store current prefix sum with its index
            prefixIndices.computeIfAbsent(prefix, x -> new ArrayList<>()).add(i);
        }

        return ans;
    }

    static void test(String name, int[] nums, int k, long exp) {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("TEST: " + name);
        System.out.println("=".repeat(50));

        long res = count(nums, k);

        System.out.println("\n--- Result ---");
        System.out.println("Total subarrays: " + res);
        System.out.println("Status: " + (res == exp ? "PASS ✓" : "FAIL ✗"));
    }

    public static void main(String[] args) {
        // Test 1: Custom example
        test("Custom Example", new int[]{7, 3, 6, 8, 1, 2}, 9, 2);

        // Test 2: Basic case
        test("Basic [1,1,1] k=2", new int[]{1, 1, 1}, 2, 2);

        // Test 3: Multiple occurrences
        test("Multiple matches", new int[]{1, 2, 3, 4, 5}, 5, 2);

        // Test 4: With zeros
        test("With zeros", new int[]{0, 0, 0}, 0, 6);

        // Test 5: Negative numbers
        test("Negative nums", new int[]{1, -1, 1, -1, 1}, 0, 4);
    }
}