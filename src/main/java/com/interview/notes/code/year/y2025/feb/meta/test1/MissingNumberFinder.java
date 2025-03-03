package com.interview.notes.code.year.y2025.feb.meta.test1;

public class MissingNumberFinder {
    public static int findKthMissing(int[] arr, int k) {
        // Keep track of missing numbers found
        int missing = 0;
        int current = arr[0];
        int i = 0;

        // Continue until we find kth missing number
        while (i < arr.length) {
            // If current number should be in sequence but isn't
            if (current != arr[i]) {
                missing++;
                // If we found kth missing number
                if (missing == k) {
                    return current;
                }
                current++;
            } else {
                current++;
                i++;
            }
        }

        // If k is bigger than missing numbers in array
        return arr[arr.length - 1] + (k - missing);
    }

    public static void main(String[] args) {
        // Test cases
        runTest(new int[]{2, 4, 7, 8, 9, 13}, 2, 5, "Basic case");
        runTest(new int[]{1, 3}, 1, 2, "Simple case");
        runTest(new int[]{1, 5}, 2, 3, "Multiple missing");
        runTest(new int[]{1, 2, 3, 4}, 1, 5, "Missing after sequence");
        runTest(new int[]{3, 4, 5}, 1, 1, "Missing before sequence");
    }

    private static void runTest(int[] arr, int k, int expected, String testName) {
        int result = findKthMissing(arr, k);
        System.out.println(testName + ": " +
                (result == expected ? "PASS" : "FAIL") +
                " (Expected: " + expected + ", Got: " + result + ")");
    }
}
