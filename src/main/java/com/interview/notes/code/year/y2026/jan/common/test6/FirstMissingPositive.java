package com.interview.notes.code.year.y2026.jan.common.test6;

public class FirstMissingPositive {

    public static void main(String[] args) {

        // Given test case
        int[] arr = {9, -3, -1, 10, 0};

        // Expected result
        int expected = 1;

        // Call method
        int result = firstMissingPositive(arr);

        // PASS / FAIL output
        System.out.println(result == expected ? "PASS" : "FAIL");

        // Large data test
        int[] large = new int[1_000_000];
        for (int i = 0; i < large.length; i++) large[i] = i + 1;

        // Remove one value
        large[500_000] = -1;

        // Validate large input
        System.out.println(firstMissingPositive(large) == 500_001 ? "PASS" : "FAIL");
    }

    static int firstMissingPositive(int[] nums) {

        // Length of input array
        int n = nums.length;

        // Boolean array to mark presence of numbers 1..n
        boolean[] seen = new boolean[n + 1];

        // Loop through input
        for (int num : nums) {

            // Check only valid positive numbers within range
            if (num > 0 && num <= n) {

                // Mark number as seen
                seen[num] = true;
            }
        }

        // Find first missing positive
        for (int i = 1; i <= n; i++) {

            // If number not seen, it's the answer
            if (!seen[i]) return i;
        }

        // If all present, next positive is answer
        return n + 1;
    }
}
