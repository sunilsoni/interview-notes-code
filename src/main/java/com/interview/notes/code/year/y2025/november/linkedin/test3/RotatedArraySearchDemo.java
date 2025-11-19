package com.interview.notes.code.year.y2025.november.linkedin.test3;

import java.util.stream.IntStream;

// Public class that holds the search logic and a manual test harness
public class RotatedArraySearchDemo {

    // Static method that checks whether the targetValue exists in the rotated sorted array
    public static boolean isInList(float targetValue, float[] arr) {
        // First guard: if the array reference itself is null, we cannot search anything, so return false
        if (arr == null) {
            // Return false to signal that a null list does not contain the target value
            return false;
        }

        // Second guard: if the array has no elements, there is nothing to search, so return false
        if (arr.length == 0) {
            // Empty array cannot contain the target
            return false;
        }

        // Define an integer variable that represents the left boundary index of our current search window
        int left = 0;

        // Define another integer variable that represents the right boundary index of our current search window
        int right = arr.length - 1;

        // Define a small epsilon to compare floats more safely instead of strict equality
        final float EPS = 1e-6f;

        // Use a while-loop to perform a modified binary search as long as the search window is valid
        while (left <= right) {
            // Compute the mid index in a way that avoids integer overflow by using the difference
            int mid = left + (right - left) / 2;

            // Read the value at the mid index from the array
            float midVal = arr[mid];

            // Check if the mid value is "close enough" to the target using the epsilon tolerance
            if (Math.abs(midVal - targetValue) < EPS) {
                // If we are within the epsilon range, we consider the values equal and return true
                return true;
            }

            // At this point the mid is not equal to target, so we must decide which half is sorted

            // Check if the left half [left..mid] is sorted by comparing the boundary values
            if (arr[left] <= midVal) {
                // This branch means the segment from left to mid is in non-decreasing order

                // Now check if the target falls within the sorted left half range [arr[left], midVal)
                if (targetValue >= arr[left] && targetValue < midVal) {
                    // If the target is inside this left sorted range, we move the right boundary to mid - 1
                    right = mid - 1;
                } else {
                    // Otherwise, the target cannot be in the left half, so we move the left boundary to mid + 1
                    left = mid + 1;
                }
            } else {
                // If we are here, it means the right half [mid..right] must be the sorted segment

                // Check if the target falls within the sorted right half range (midVal, arr[right]]
                if (targetValue > midVal && targetValue <= arr[right]) {
                    // If the target is in this sorted right range, move the left boundary to mid + 1
                    left = mid + 1;
                } else {
                    // Otherwise, the target is not in the right half, so we move the right boundary to mid - 1
                    right = mid - 1;
                }
            }
            // The loop then continues, shrinking the search window until we either find the target or exhaust the range
        }

        // If we exit the loop, it means we did not find the target value anywhere in the array
        return false;
    }

    // Helper method to print the result for a single test case in a consistent PASS/FAIL format
    private static void runSingleTest(float target, float[] arr, boolean expected, int testIndex) {
        // Call the isInList method to compute the actual result for this test case
        boolean actual = isInList(target, arr);

        // Convert the array to a human-readable string using a simple loop (no Arrays.toString to keep control)
        StringBuilder builder = new StringBuilder();
        // Add opening bracket to show start of array representation
        builder.append("[");
        // Use a for-loop to append each element with commas between them
        for (int i = 0; i < arr.length; i++) {
            // Append the current float value
            builder.append(arr[i]);
            // If this is not the last element, append a comma and a space
            if (i < arr.length - 1) {
                builder.append(", ");
            }
        }
        // Close the bracket for the array representation
        builder.append("]");

        // Construct a string that clearly shows test index, input, expected result, actual result, and pass/fail status
        String resultLine = "Test " + testIndex
                + " | Target=" + target
                + " | Array=" + builder
                + " | Expected=" + expected
                + " | Actual=" + actual
                + " | Result=" + ((expected == actual) ? "PASS" : "FAIL");

        // Print the result line to the console
        System.out.println(resultLine);
    }

    // Main method used to manually verify multiple test cases including large data input
    public static void main(String[] args) {
        // Print a header message so the console output is easy to understand
        System.out.println("Running RotatedArraySearchDemo tests...");

        // Define a first small rotated array example, similar to the one mentioned in the problem statement
        float[] arr1 = new float[] {6f, 7f, 1f, 2f, 3f, 4f, 5f};

        // Test case #0: search for 1 in arr1, which should exist and return true
        runSingleTest(1f, arr1, true, 0);

        // Test case #1: search for 4 in arr1, which should also exist and return true
        runSingleTest(4f, arr1, true, 1);

        // Test case #2: search for a number not present in the array (e.g., 8) which should return false
        runSingleTest(8f, arr1, false, 2);

        // Define a second rotated array with a different pivot, to check behavior in other positions
        float[] arr2 = new float[] {3f, 4f, 5f, 6f, 7f, 1f, 2f};

        // Test case #3: search for 6 in arr2 which lies in the sorted left half of the first step
        runSingleTest(6f, arr2, true, 3);

        // Test case #4: search for 2 in arr2 which lies near the end of the array
        runSingleTest(2f, arr2, true, 4);

        // Define a simple non-rotated sorted array to confirm the algorithm still works as normal binary search
        float[] arr3 = new float[] {1f, 2f, 3f, 4f, 5f, 6f};

        // Test case #5: target at beginning, should return true
        runSingleTest(1f, arr3, true, 5);

        // Test case #6: target at end, should return true
        runSingleTest(6f, arr3, true, 6);

        // Test case #7: target not present, should return false
        runSingleTest(9f, arr3, false, 7);

        // Define an array with a single element to test the smallest non-empty case
        float[] arr4 = new float[] {42f};

        // Test case #8: searching for the only element, should return true
        runSingleTest(42f, arr4, true, 8);

        // Test case #9: searching for a different value, should return false
        runSingleTest(41f, arr4, false, 9);

        // Define an empty array to confirm the function handles it correctly
        float[] emptyArr = new float[] {};

        // Test case #10: searching inside an empty array should always be false
        runSingleTest(5f, emptyArr, false, 10);

        // Now build a large sorted array to test performance and behavior on big inputs
        int size = 1_000_00; // choose one hundred thousand elements for a realistic large test

        // Create an array of the chosen size to store float values from 0 to size-1
        float[] largeSorted = new float[size];

        // Use IntStream to fill the array in a compact Java 8 style
        IntStream.range(0, size).forEach(i -> {
            // For each index i, set the value to i as a float, so the array is sorted ascending
            largeSorted[i] = (float) i;
        });

        // Decide a rotation pivot index where the array will be split and rotated
        int pivot = size / 3;  // rotate roughly one-third into the array

        // Create a new array to hold the rotated version of the large sorted array
        float[] largeRotated = new float[size];

        // First copy the tail part from the pivot to end into the beginning of the rotated array
        int rotatedIndex = 0;  // track current index in the rotated array
        for (int i = pivot; i < size; i++) {
            // Place element from original array at position i to rotated array at current rotatedIndex
            largeRotated[rotatedIndex++] = largeSorted[i];
        }

        // Then copy the head part from the beginning up to the pivot into the remaining positions
        for (int i = 0; i < pivot; i++) {
            // Place element from original array at position i to rotated array at current rotatedIndex
            largeRotated[rotatedIndex++] = largeSorted[i];
        }

        // Choose a target value that we know exists in the large array, for example the middle value
        float largeTargetPresent = (float) (size / 2);

        // Call the helper test method with expected true for the present target
        runSingleTest(largeTargetPresent, largeRotated, true, 11);

        // Choose another target that is definitely not present, such as size + 10
        float largeTargetAbsent = (float) (size + 10);

        // Call the helper test method with expected false for the absent target
        runSingleTest(largeTargetAbsent, largeRotated, false, 12);

        // Finally print a footer line to indicate that all tests have finished running
        System.out.println("All tests completed.");
    }
}
