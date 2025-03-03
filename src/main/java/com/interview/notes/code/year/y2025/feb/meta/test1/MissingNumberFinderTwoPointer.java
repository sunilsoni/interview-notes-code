package com.interview.notes.code.year.y2025.feb.meta.test1;

/*
// Let's recall from math the definition of dot product. Given two vectors a and b, the dot product is a1*b1 + a2*b2 + ... + aN*bN.

// This is a simple algorithm, but what makes it interesting is application to sparse vectors. A sparse vector is a vector with most elements equal to zero -- imagine a vector with millions of elements (or even infinite, as is the case for e.g. the Fourier Transform), but only ten thousand or so are nonzero. Now, a machine learning application loads many such vectors in memory (from a file or &#8203;:contentReference[oaicite:0]{index=0}&#8203;
 */
public class MissingNumberFinderTwoPointer {
    public static int findKthMissing(int[] arr, int k) {
        int left = 0;
        int right = arr.length - 1;

        // First check if k is smaller than missing numbers before first element
        if (k < arr[0] - 1) {
            return k;
        }

        // Binary search approach
        while (left <= right) {
            int mid = left + (right - left) / 2;

            // Calculate missing numbers up to mid
            int missing = arr[mid] - (mid + 1);

            if (missing < k) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        // Calculate final result
        return k + left;
    }

    public static void main(String[] args) {
        runAllTests();
    }

    private static void runAllTests() {
        // Test Case 1: Basic case
        testCase(new int[]{2, 4, 7, 8, 9, 13}, 2, 5, "Basic case");

        // Test Case 2: Simple gap
        testCase(new int[]{1, 3}, 1, 2, "Simple gap");

        // Test Case 3: Large gap
        testCase(new int[]{1, 5}, 2, 3, "Large gap");

        // Test Case 4: Missing at start
        testCase(new int[]{3, 4, 5}, 1, 1, "Missing at start");

        // Test Case 5: Missing at end
        testCase(new int[]{1, 2, 3}, 2, 5, "Missing at end");

        // Test Case 6: Large K
        testCase(new int[]{1, 2, 4}, 5, 8, "Large K");

        // Test Case 7: Single element array
        testCase(new int[]{5}, 2, 2, "Single element");

        // Test Case 8: Large numbers
        testCase(new int[]{10, 20, 30}, 5, 15, "Large numbers");
    }

    private static void testCase(int[] arr, int k, int expected, String testName) {
        int result = findKthMissing(arr, k);
        boolean passed = result == expected;
        System.out.println(String.format("%s: %s",
                testName,
                passed ? "PASS" : "FAIL"));
        if (!passed) {
            System.out.println(String.format("  Expected: %d, Got: %d",
                    expected, result));
        }
    }
}
