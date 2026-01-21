package com.interview.notes.code.year.y2025.december.oracle.test4;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TripletCounter {

    // Modulo value as per problem requirement
    static final int MOD = 1_000_000_007;

    public static int countTripletsWithinDifference(List<Integer> arr, int d) {

        // Convert list to sorted array - sorting helps find valid range easily
        int[] nums = arr.stream().mapToInt(i -> i).sorted().toArray();

        // Get array length for iteration bounds
        int n = nums.length;

        // Use long to handle large count values before modulo
        long count = 0;

        // Iterate each element as potential minimum of triplet
        for (int i = 0; i < n - 2; i++) {

            // Binary search to find rightmost index where diff <= d
            int j = findRightmost(nums, i, d);

            // Elements in valid range (excluding nums[i] itself)
            long range = j - i;

            // Need at least 2 more elements to form triplet
            if (range >= 2) {

                // nC2 formula: choose 2 from range elements
                // This gives count of triplets with nums[i] as min
                count = (count + range * (range - 1) / 2) % MOD;
            }
        }

        // Return final count as integer
        return (int) count;
    }

    // Binary search helper - finds rightmost index where nums[j] - nums[start] <= d
    static int findRightmost(int[] nums, int start, int d) {

        // Left pointer starts after start (need at least 2 more elements)
        int left = start + 1;

        // Right pointer at array end
        int right = nums.length - 1;

        // Track the valid end position
        int result = start;

        // Standard binary search loop
        while (left <= right) {

            // Calculate mid to avoid overflow
            int mid = left + (right - left) / 2;

            // Use long for difference to handle large values
            long diff = (long) nums[mid] - nums[start];

            // If difference within limit, search right half
            if (diff <= d) {
                result = mid;      // Update valid position
                left = mid + 1;    // Move left pointer right
            } else {
                right = mid - 1;   // Search left half
            }
        }

        // Return rightmost valid index
        return result;
    }

    // Test helper - compares result with expected value
    static void test(List<Integer> arr, int d, int expected, String name) {

        // Call the main function
        int result = countTripletsWithinDifference(arr, d);

        // Check if result matches expected
        String status = result == expected ? "PASS ✓" : "FAIL ✗";

        // Print test result with details
        System.out.println(name + ": " + status + " | Expected: " + expected + ", Got: " + result);
    }

    public static void main(String[] args) {

        System.out.println("=== Running Test Cases ===\n");

        // Example 1: arr=[-3,-2,-1,0], d=2 -> Output: 2
        // Valid triplets: [-3,-2,-1] and [-2,-1,0]
        test(Arrays.asList(-3, -2, -1, 0), 2, 2, "Example 1");

        // Example 2: arr=[2,1,3,4], d=3 -> Output: 4
        // Valid triplets: [2,1,3], [2,1,4], [2,3,4], [1,3,4]
        test(Arrays.asList(2, 1, 3, 4), 3, 4, "Example 2");

        // Edge case: Minimum size array
        test(Arrays.asList(1, 2, 3), 2, 1, "Minimum Array");

        // Edge case: All same elements, d=0
        test(Arrays.asList(5, 5, 5, 5), 0, 4, "All Same Elements");

        // Edge case: No valid triplet possible
        test(Arrays.asList(1, 10, 100), 5, 0, "No Valid Triplet");

        // Edge case: All triplets valid with large d
        test(Arrays.asList(1, 2, 3, 4, 5), 10, 10, "All Valid (5C3=10)");

        // Edge case: Negative numbers
        test(Arrays.asList(-5, -3, -1, 0, 2), 3, 4, "Negative Numbers");

        System.out.println("\n=== Large Data Test ===\n");

        // Large data: 200000 consecutive elements
        List<Integer> largeArr = IntStream.range(0, 200000)
                .boxed()
                .collect(Collectors.toList());

        // Record start time
        long startTime = System.currentTimeMillis();

        // Test with large d (all triplets valid)
        int largeResult = countTripletsWithinDifference(largeArr, 1_000_000_000);

        // Calculate execution time
        long duration = System.currentTimeMillis() - startTime;

        // Print large test result
        System.out.println("Large Test (n=200000): Completed in " + duration + "ms");
        System.out.println("Result: " + largeResult);
        System.out.println("Status: " + (duration < 5000 ? "PASS ✓ (Under 5s)" : "FAIL ✗ (Too slow)"));

        System.out.println("\n=== Boundary Value Test ===\n");

        // Test with extreme values
        test(Arrays.asList(-1_000_000_000, 0, 1_000_000_000), 2_000_000_000, 1, "Extreme Values");

        // Test with duplicate elements
        test(Arrays.asList(1, 1, 2, 2, 3, 3), 2, 20, "Duplicates");
    }
}