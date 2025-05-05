package com.interview.notes.code.year.y2025.april.meta.test1;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class FirstElementGreaterThanK {

    // Method to find the index of the first element greater than K
    public static int findFirstGreater(int[] arr, int k) {
        int left = 0; // start of the array
        int right = arr.length - 1; // end of the array
        int result = -1; // default if no element is found

        while (left <= right) { // standard binary search
            int mid = left + (right - left) / 2; // avoid integer overflow
            if (arr[mid] > k) {
                result = mid; // update result and move left to find earlier occurrence
                right = mid - 1;
            } else {
                left = mid + 1; // move right
            }
        }
        return result; // final result
    }

    // Main method to test different cases
    public static void main(String[] args) {
        List<TestCase> testCases = Arrays.asList(
                new TestCase(new int[]{1, 3, 5, 7, 9}, 4, 2), // 5 at index 2
                new TestCase(new int[]{1, 3, 5, 7, 9}, 9, -1), // no element > 9
                new TestCase(new int[]{1, 3, 5, 7, 9}, 0, 0), // 1 at index 0
                new TestCase(new int[]{}, 5, -1), // empty array
                new TestCase(new int[]{2, 2, 2, 2}, 2, -1), // all elements equal to K
                new TestCase(IntStream.range(1, 1000001).toArray(), 999999, 999999) // large input
        );

        for (TestCase testCase : testCases) {
            int output = findFirstGreater(testCase.arr, testCase.k);
            boolean pass = output == testCase.expected;
            System.out.println("Input: " + Arrays.toString(Arrays.copyOfRange(testCase.arr, 0, Math.min(10, testCase.arr.length))) + (testCase.arr.length > 10 ? "..." : "")
                    + ", k=" + testCase.k + " | Expected: " + testCase.expected + " | Got: " + output + " | " + (pass ? "PASS" : "FAIL"));
        }
    }

    // Helper class for test case representation
    static class TestCase {
        int[] arr;
        int k;
        int expected;

        TestCase(int[] arr, int k, int expected) {
            this.arr = arr;
            this.k = k;
            this.expected = expected;
        }
    }
}
