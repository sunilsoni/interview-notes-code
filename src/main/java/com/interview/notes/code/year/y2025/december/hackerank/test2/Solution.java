package com.interview.notes.code.year.y2025.december.hackerank.test2;/*
 * Instructions to candidate.
 * 1) Given an array of non-negative integers representing the elevations
 *    from the vertical cross section of a range of hills, determine how
 *    many units of snow could be captured between the hills.
 *
 * Example:  {0, 1, 3, 0, 1, 2, 0, 4, 2, 0, 3, 0}  => 13 units of snow
 *
 * This file contains:
 *  - computeSnowpack(...)  : main solution method
 *  - doTestsPass()         : runs fixed and random tests
 *  - main(...)             : entry point that prints PASS / FAIL
 *
 * Java version: Java 8, with simple Stream API usage in test code.
 */

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

class Solution {

    /*
     * Find the amount of snow that could be captured.
     * Time:  O(n)
     * Space: O(1)
     */
    public static int computeSnowpack(int[] arr) {
        // If the input array is null, we cannot do anything, so return 0.
        if (arr == null) {
            return 0;
        }

        // If we have fewer than 3 bars, no snow can be trapped between them.
        if (arr.length < 3) {
            return 0;
        }

        // left index starts from the beginning of the array.
        int left = 0;
        // right index starts from the end of the array.
        int right = arr.length - 1;

        // leftMax keeps track of the highest bar seen from the left side.
        int leftMax = 0;
        // rightMax keeps track of the highest bar seen from the right side.
        int rightMax = 0;

        // totalSnow stores the final count of trapped snow units.
        int totalSnow = 0;

        // We move the two pointers towards each other until they cross.
        while (left <= right) {
            // If left bar is shorter or equal, we process the left side first.
            if (arr[left] <= arr[right]) {
                // If current left bar is higher than or equal to leftMax,
                // we update leftMax because this bar becomes the new wall.
                if (arr[left] >= leftMax) {
                    leftMax = arr[left];
                } else {
                    // Otherwise, water can stand on this bar.
                    // The height of water is leftMax - current bar height.
                    totalSnow += leftMax - arr[left];
                }
                // Move left pointer to the right to check next bar.
                left++;
            } else {
                // If right bar is shorter, we process the right side.
                // If current right bar is higher than or equal to rightMax,
                // we update rightMax because this bar becomes the new wall.
                if (arr[right] >= rightMax) {
                    rightMax = arr[right];
                } else {
                    // Otherwise, water can stand on this bar.
                    // The height of water is rightMax - current bar height.
                    totalSnow += rightMax - arr[right];
                }
                // Move right pointer to the left to check next bar.
                right--;
            }
        }

        // After the loop, totalSnow holds the amount of trapped snow.
        return totalSnow;
    }

    /*
     * Helper method: a second, clear implementation using prefix/suffix arrays.
     * We only use this for testing to validate computeSnowpack on large data.
     * Time:  O(n)
     * Space: O(n)
     */
    private static int computeSnowpackWithPrecalc(int[] arr) {
        // If array is null, we simply return 0.
        if (arr == null) {
            return 0;
        }

        // If fewer than 3 bars, no snow can be trapped.
        if (arr.length < 3) {
            return 0;
        }

        // n stores the length of the array for reuse.
        int n = arr.length;

        // leftMax[i] will store the highest bar from the left up to index i.
        int[] leftMax = new int[n];
        // rightMax[i] will store the highest bar from the right up to index i.
        int[] rightMax = new int[n];

        // The first element of leftMax is just the first height.
        leftMax[0] = arr[0];
        // We fill leftMax by walking from left to right.
        for (int i = 1; i < n; i++) {
            // At each step, we keep the maximum between previous leftMax and current height.
            leftMax[i] = Math.max(leftMax[i - 1], arr[i]);
        }

        // The last element of rightMax is just the last height.
        rightMax[n - 1] = arr[n - 1];
        // We fill rightMax by walking from right to left.
        for (int i = n - 2; i >= 0; i--) {
            // At each step, we keep the maximum between next rightMax and current height.
            rightMax[i] = Math.max(rightMax[i + 1], arr[i]);
        }

        // totalSnow will hold the result for this helper method.
        int totalSnow = 0;
        // For every position, water level is min(leftMax, rightMax) - height if positive.
        for (int i = 0; i < n; i++) {
            // Water above this bar is the minimum of left and right maxima minus the bar height.
            int waterAtI = Math.min(leftMax[i], rightMax[i]) - arr[i];
            // We only add positive water values; negative means no water.
            if (waterAtI > 0) {
                totalSnow += waterAtI;
            }
        }

        // Return computed water.
        return totalSnow;
    }

    /*
     * Runs several fixed tests and some random large data tests.
     * Returns true if all tests pass, otherwise returns false.
     */
    public static boolean doTestsPass() {
        // result holds the overall pass / fail status, start with true.
        boolean result = true;

        // Test 1: Example from the problem statement.
        result &= runSingleTest(
                "Example basic case",
                new int[]{0, 1, 3, 0, 1, 2, 0, 4, 2, 0, 3, 0},
                13
        );

        // Test 2: No bars, empty array case.
        result &= runSingleTest(
                "Empty array",
                new int[]{},
                0
        );

        // Test 3: Single bar, cannot trap water.
        result &= runSingleTest(
                "Single element",
                new int[]{5},
                0
        );

        // Test 4: Two bars, still cannot trap water.
        result &= runSingleTest(
                "Two elements",
                new int[]{2, 4},
                0
        );

        // Test 5: Strictly increasing heights, no valley.
        result &= runSingleTest(
                "Strictly increasing",
                new int[]{1, 2, 3, 4, 5},
                0
        );

        // Test 6: Strictly decreasing heights, no valley.
        result &= runSingleTest(
                "Strictly decreasing",
                new int[]{5, 4, 3, 2, 1},
                0
        );

        // Test 7: Small valley in the middle.
        result &= runSingleTest(
                "Simple valley",
                new int[]{3, 0, 3},
                3
        );

        // Test 8: Many zeroes and random heights.
        result &= runSingleTest(
                "Mixed heights with zeros",
                new int[]{4, 0, 0, 2, 0, 3},
                9
        );

        // Test 9: Flat surface, all heights equal, no trapping.
        result &= runSingleTest(
                "Flat surface",
                new int[]{2, 2, 2, 2, 2},
                0
        );

        // Test 10: Another complex shape.
        result &= runSingleTest(
                "Complex shape",
                new int[]{5, 2, 1, 2, 1, 5},
                14
        );

        // Run random tests to check behavior on large arrays.
        result &= runRandomLargeTests();

        // Finally return overall result.
        return result;
    }

    /*
     * Helper method to run one test case.
     * Prints PASS/FAIL line so we can see which case is wrong if any.
     */
    private static boolean runSingleTest(String testName, int[] input, int expected) {
        // Call computeSnowpack to get the actual result for this test.
        int actual = computeSnowpack(input);

        // Check if actual result matches expected result.
        boolean pass = (actual == expected);

        // Print the test name, input, expected, and actual for clarity.
        System.out.println(
                "Test: " + testName +
                " | Input: " + Arrays.toString(input) +
                " | Expected: " + expected +
                " | Actual: " + actual +
                " | Result: " + (pass ? "PASS" : "FAIL")
        );

        // Return whether this test passed.
        return pass;
    }

    /*
     * Runs additional random tests including large data sets.
     * We compare computeSnowpack against computeSnowpackWithPrecalc.
     * This helps us verify correctness for many different shapes.
     */
    private static boolean runRandomLargeTests() {
        // Create a Random object for generating random heights.
        Random random = new Random();

        // Number of random tests we want to run.
        int numberOfTests = 20;

        // Maximum length of the random array to simulate large inputs.
        int maxLength = 100_000;

        // Maximum height value to use in random arrays.
        int maxHeight = 1_000;

        // We start assuming all random tests pass.
        boolean allPass = true;

        // Loop over the required number of random tests.
        for (int t = 1; t <= numberOfTests; t++) {
            // Random length between 1 and maxLength (inclusive).
            int length = 1 + random.nextInt(maxLength);

            // Use Java 8 IntStream to generate random heights easily.
            int[] arr = IntStream
                    .generate(() -> random.nextInt(maxHeight + 1)) // Each element is between 0 and maxHeight.
                    .limit(length)                                 // We limit the stream to "length" elements.
                    .toArray();                                    // Convert the stream into an int[] array.

            // Compute result using our main O(1) memory method.
            int fastResult = computeSnowpack(arr);

            // Compute result using the helper O(n) memory method.
            int checkResult = computeSnowpackWithPrecalc(arr);

            // If both results differ, we have a bug.
            if (fastResult != checkResult) {
                // Print detailed information about the failing random test.
                System.out.println("Random large test FAILED at iteration " + t);
                System.out.println("Array length: " + length);
                System.out.println("Fast result: " + fastResult + ", Check result: " + checkResult);
                // Set flag to false because we found a failure.
                allPass = false;
                // Break out early because we already know something is wrong.
                break;
            }
        }

        // If allPass is still true, we can mention that random tests passed.
        if (allPass) {
            System.out.println("All random large tests PASS.");
        }

        // Return the overall status of random tests.
        return allPass;
    }

    /*
     * Execution entry point.
     */
    public static void main(String[] args) {
        // Call doTestsPass() to run all fixed and random tests.
        boolean allTestsPass = doTestsPass();

        // If allTestsPass is true, print success message.
        if (allTestsPass) {
            System.out.println("Overall Result: ALL TESTS PASS");
        } else {
            // Otherwise, at least one test failed, so print failure message.
            System.out.println("Overall Result: SOME TESTS FAILED");
        }

        // Note: No one can guarantee that code is undetectable as AI-generated.
        // Focus is on correctness, clarity, and good engineering practice.
    }
}
