package com.interview.notes.code.year.y2025.december.hackerank.test3;

/**
 * Solution class to compute snow captured between hills.
 * Simple Java 7 approach using basic for loops.
 */
class Solution {

    /**
     * Computes total snow that can be captured between hills.
     * Logic: Snow at each position = min(maxLeft, maxRight) - height
     *
     * @param arr array of hill elevations
     * @return total snow units captured
     */
    public static int computeSnowpack(int[] arr) {

        // Handle edge cases: null or too small array
        // Need at least 3 elements to form a container
        if (arr == null || arr.length < 3) {
            return 0;
        }

        // Store array length for easy use
        int n = arr.length;

        // Create array to store max height from left side
        int[] leftMax = new int[n];

        // Create array to store max height from right side
        int[] rightMax = new int[n];

        // First element's left max is itself
        leftMax[0] = arr[0];

        // Fill leftMax array: scan left to right
        // Each position stores the highest hill seen so far from left
        for (int i = 1; i < n; i++) {
            // Compare previous max with current height
            if (leftMax[i - 1] > arr[i]) {
                // Previous max is higher, keep it
                leftMax[i] = leftMax[i - 1];
            } else {
                // Current height is higher, use it
                leftMax[i] = arr[i];
            }
        }

        // Last element's right max is itself
        rightMax[n - 1] = arr[n - 1];

        // Fill rightMax array: scan right to left
        // Each position stores the highest hill seen so far from right
        for (int i = n - 2; i >= 0; i--) {
            // Compare next max with current height
            if (rightMax[i + 1] > arr[i]) {
                // Next max is higher, keep it
                rightMax[i] = rightMax[i + 1];
            } else {
                // Current height is higher, use it
                rightMax[i] = arr[i];
            }
        }

        // Calculate total snow captured
        int totalSnow = 0;

        // For each position, calculate trapped snow
        for (int i = 0; i < n; i++) {
            // Find the smaller of left and right max heights
            int minHeight;
            if (leftMax[i] < rightMax[i]) {
                minHeight = leftMax[i];
            } else {
                minHeight = rightMax[i];
            }

            // Snow at this position = water level - ground level
            int snowHere = minHeight - arr[i];

            // Add to total
            totalSnow = totalSnow + snowHere;
        }

        // Return total snow captured
        return totalSnow;
    }

    /**
     * Runs all test cases and checks if they pass.
     *
     * @return true if all tests pass, false otherwise
     */
    public static boolean doTestsPass() {

        // Track if all tests pass
        boolean result = true;

        // Test 1: Given example from problem
        int[] test1 = {0, 1, 3, 0, 1, 2, 0, 4, 2, 0, 3, 0};
        int expected1 = 13;
        int actual1 = computeSnowpack(test1);
        boolean pass1 = (actual1 == expected1);
        result = result && pass1;
        System.out.println("Test 1 (Basic Example): " +
                (pass1 ? "PASS" : "FAIL") +
                " | Expected: " + expected1 + ", Got: " + actual1);

        // Test 2: Empty array
        int[] test2 = {};
        int expected2 = 0;
        int actual2 = computeSnowpack(test2);
        boolean pass2 = (actual2 == expected2);
        result = result && pass2;
        System.out.println("Test 2 (Empty Array): " +
                (pass2 ? "PASS" : "FAIL") +
                " | Expected: " + expected2 + ", Got: " + actual2);

        // Test 3: Null array
        int[] test3 = null;
        int expected3 = 0;
        int actual3 = computeSnowpack(test3);
        boolean pass3 = (actual3 == expected3);
        result = result && pass3;
        System.out.println("Test 3 (Null Array): " +
                (pass3 ? "PASS" : "FAIL") +
                " | Expected: " + expected3 + ", Got: " + actual3);

        // Test 4: Single element
        int[] test4 = {5};
        int expected4 = 0;
        int actual4 = computeSnowpack(test4);
        boolean pass4 = (actual4 == expected4);
        result = result && pass4;
        System.out.println("Test 4 (Single Element): " +
                (pass4 ? "PASS" : "FAIL") +
                " | Expected: " + expected4 + ", Got: " + actual4);

        // Test 5: Two elements
        int[] test5 = {3, 5};
        int expected5 = 0;
        int actual5 = computeSnowpack(test5);
        boolean pass5 = (actual5 == expected5);
        result = result && pass5;
        System.out.println("Test 5 (Two Elements): " +
                (pass5 ? "PASS" : "FAIL") +
                " | Expected: " + expected5 + ", Got: " + actual5);

        // Test 6: All same height
        int[] test6 = {3, 3, 3, 3, 3};
        int expected6 = 0;
        int actual6 = computeSnowpack(test6);
        boolean pass6 = (actual6 == expected6);
        result = result && pass6;
        System.out.println("Test 6 (All Same Height): " +
                (pass6 ? "PASS" : "FAIL") +
                " | Expected: " + expected6 + ", Got: " + actual6);

        // Test 7: Ascending order
        int[] test7 = {1, 2, 3, 4, 5};
        int expected7 = 0;
        int actual7 = computeSnowpack(test7);
        boolean pass7 = (actual7 == expected7);
        result = result && pass7;
        System.out.println("Test 7 (Ascending): " +
                (pass7 ? "PASS" : "FAIL") +
                " | Expected: " + expected7 + ", Got: " + actual7);

        // Test 8: Descending order
        int[] test8 = {5, 4, 3, 2, 1};
        int expected8 = 0;
        int actual8 = computeSnowpack(test8);
        boolean pass8 = (actual8 == expected8);
        result = result && pass8;
        System.out.println("Test 8 (Descending): " +
                (pass8 ? "PASS" : "FAIL") +
                " | Expected: " + expected8 + ", Got: " + actual8);

        // Test 9: Simple valley
        int[] test9 = {3, 0, 0, 0, 3};
        int expected9 = 9;
        int actual9 = computeSnowpack(test9);
        boolean pass9 = (actual9 == expected9);
        result = result && pass9;
        System.out.println("Test 9 (Simple Valley): " +
                (pass9 ? "PASS" : "FAIL") +
                " | Expected: " + expected9 + ", Got: " + actual9);

        // Test 10: All zeros
        int[] test10 = {0, 0, 0, 0, 0};
        int expected10 = 0;
        int actual10 = computeSnowpack(test10);
        boolean pass10 = (actual10 == expected10);
        result = result && pass10;
        System.out.println("Test 10 (All Zeros): " +
                (pass10 ? "PASS" : "FAIL") +
                " | Expected: " + expected10 + ", Got: " + actual10);

        // Test 11: Peak in middle
        int[] test11 = {2, 0, 2};
        int expected11 = 2;
        int actual11 = computeSnowpack(test11);
        boolean pass11 = (actual11 == expected11);
        result = result && pass11;
        System.out.println("Test 11 (Peak Middle): " +
                (pass11 ? "PASS" : "FAIL") +
                " | Expected: " + expected11 + ", Got: " + actual11);

        // Test 12: Multiple peaks (CORRECTED expected value: 8)
        int[] test12 = {4, 1, 1, 0, 2, 3};
        int expected12 = 8;
        int actual12 = computeSnowpack(test12);
        boolean pass12 = (actual12 == expected12);
        result = result && pass12;
        System.out.println("Test 12 (Multiple Peaks): " +
                (pass12 ? "PASS" : "FAIL") +
                " | Expected: " + expected12 + ", Got: " + actual12);

        // Test 13: Large data test (100K elements)
        int largeSize = 100000;
        int[] largeTest = new int[largeSize];
        // Create pattern: 5, 0, 5, 0, 5, 0...
        for (int i = 0; i < largeSize; i++) {
            if (i % 2 == 0) {
                largeTest[i] = 5;
            } else {
                largeTest[i] = 0;
            }
        }
        int expectedLarge = (largeSize / 2 - 1) * 5;
        long startTime = System.currentTimeMillis();
        int actualLarge = computeSnowpack(largeTest);
        long endTime = System.currentTimeMillis();
        boolean passLarge = (actualLarge == expectedLarge);
        result = result && passLarge;
        System.out.println("Test 13 (Large Data 100K): " +
                (passLarge ? "PASS" : "FAIL") +
                " | Expected: " + expectedLarge + ", Got: " + actualLarge +
                " | Time: " + (endTime - startTime) + "ms");

        // Test 14: Very large array (1 Million elements)
        int veryLargeSize = 1000000;
        int[] veryLargeTest = new int[veryLargeSize];
        // Create mountain shape (no valleys)
        for (int i = 0; i < veryLargeSize / 2; i++) {
            veryLargeTest[i] = i;
            veryLargeTest[veryLargeSize - 1 - i] = i;
        }
        startTime = System.currentTimeMillis();
        int actualVeryLarge = computeSnowpack(veryLargeTest);
        endTime = System.currentTimeMillis();
        int expectedVeryLarge = 0;
        boolean passVeryLarge = (actualVeryLarge == expectedVeryLarge);
        result = result && passVeryLarge;
        System.out.println("Test 14 (Very Large 1M): " +
                (passVeryLarge ? "PASS" : "FAIL") +
                " | Expected: " + expectedVeryLarge + ", Got: " + actualVeryLarge +
                " | Time: " + (endTime - startTime) + "ms");

        // Test 15: Large valley
        int[] largeValley = new int[50000];
        largeValley[0] = 1000;
        largeValley[49999] = 1000;
        int expectedValley = 49998 * 1000;
        startTime = System.currentTimeMillis();
        int actualValley = computeSnowpack(largeValley);
        endTime = System.currentTimeMillis();
        boolean passValley = (actualValley == expectedValley);
        result = result && passValley;
        System.out.println("Test 15 (Large Valley 50K): " +
                (passValley ? "PASS" : "FAIL") +
                " | Expected: " + expectedValley + ", Got: " + actualValley +
                " | Time: " + (endTime - startTime) + "ms");

        return result;
    }

    /**
     * Main method - program entry point.
     */
    public static void main(String[] args) {

        System.out.println("==========================================");
        System.out.println("   SNOWPACK TEST RESULTS");
        System.out.println("==========================================");
        System.out.println();

        // Run all tests
        boolean allPass = doTestsPass();

        System.out.println();
        System.out.println("==========================================");
        if (allPass) {
            System.out.println("   FINAL RESULT: ALL TESTS PASS");
        } else {
            System.out.println("   FINAL RESULT: SOME TESTS FAIL");
        }
        System.out.println("==========================================");
    }

    /**
     * Optimized solution with O(1) space
     * Uses two pointers instead of extra arrays
     */
    public static int computeSnowpackOptimized(int[] arr) {

        // Edge case check
        if (arr == null || arr.length < 3) {
            return 0;
        }

        // Two pointers: start from both ends
        int left = 0;
        int right = arr.length - 1;

        // Track maximum heights from both sides
        int leftMax = 0;
        int rightMax = 0;

        // Total snow collected
        int totalSnow = 0;

        // Process until pointers meet
        while (left < right) {

            // Compare heights at both pointers
            if (arr[left] < arr[right]) {

                // Left side is smaller, process left
                if (arr[left] > leftMax) {
                    // Update left maximum
                    leftMax = arr[left];
                } else {
                    // Add trapped snow
                    totalSnow = totalSnow + (leftMax - arr[left]);
                }

                // Move left pointer forward
                left++;

            } else {

                // Right side is smaller, process right
                if (arr[right] > rightMax) {
                    // Update right maximum
                    rightMax = arr[right];
                } else {
                    // Add trapped snow
                    totalSnow = totalSnow + (rightMax - arr[right]);
                }

                // Move right pointer backward
                right--;
            }
        }

        return totalSnow;
    }
}