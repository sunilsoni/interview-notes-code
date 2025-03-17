package com.interview.notes.code.year.y2025.march.meta.test2;

import java.util.Arrays;
import java.util.stream.Collectors;

public class SecondLargestNumber {

    /**
     * This method returns the second largest possible arrangement (as an int array)
     * of the given digits. The idea is:
     * 1. Sort the array in descending order to get the largest number.
     * 2. Compute the previous lexicographic permutation of that array to get the second largest.
     *
     * @param digits an array of integers (each between 0 and 9)
     * @return an array representing the second largest number arrangement.
     */
    public static int[] getSecondLargest(int[] digits) {
        // Check if the input is null or too short (one element has no "second largest")
        if (digits == null || digits.length < 2) {
            return digits; // For one-element or null arrays, just return as is.
        }

        // Step 1: Sort the array in descending order to get the largest possible arrangement.
        // We use Java8 streams to box, sort, and then unbox the integers.
        int[] largest = Arrays.stream(digits)
                              .boxed()
                              .sorted((a, b) -> b - a) // sort in descending order
                              .mapToInt(Integer::intValue)
                              .toArray();

        // Step 2: Find the "previous permutation" of the sorted array.
        // We start from the right end to find the first index 'i' where largest[i] > largest[i+1].
        int i = largest.length - 2;
        while (i >= 0 && largest[i] <= largest[i + 1]) {
            i--; // Move left until we find the right spot.
        }
        // If i becomes -1, then the array is already the smallest permutation
        // (which happens only if all digits are identical). In that case, no second largest exists.
        if (i < 0) {
            return largest;
        }

        // Find index 'j' from the right such that largest[j] is less than largest[i].
        int j = largest.length - 1;
        while (largest[j] >= largest[i]) {
            j--; // Move left until condition is met.
        }

        // Swap the elements at indices i and j.
        int temp = largest[i];
        largest[i] = largest[j];
        largest[j] = temp;

        // Reverse the sub-array from i+1 to the end.
        // This step ensures the remainder of the array is in descending order,
        // forming the immediate previous permutation.
        reverse(largest, i + 1, largest.length - 1);

        // Return the resulting array which is the second largest arrangement.
        return largest;
    }

    /**
     * Helper method that reverses a portion of the array between start and end indices.
     *
     * @param arr   the array to reverse
     * @param start the starting index of the segment to reverse
     * @param end   the ending index of the segment to reverse
     */
    private static void reverse(int[] arr, int start, int end) {
        while (start < end) {
            int temp = arr[start]; // Store the value at the start.
            arr[start] = arr[end]; // Swap with the value at the end.
            arr[end] = temp;       // Complete the swap.
            start++;               // Move the start pointer right.
            end--;                 // Move the end pointer left.
        }
    }

    /**
     * The main method runs several test cases to validate the solution.
     * It prints pass/fail for each test case and includes a test for large data.
     */
    public static void main(String[] args) {
        boolean allTestsPassed = true; // Flag to track if all tests pass

        // Test 1: Provided example: [1,2,3,4,5] should give [5,4,3,1,2]
        int[] input1 = {1, 2, 3, 4, 5};
        int[] expected1 = {5, 4, 3, 1, 2};
        int[] output1 = getSecondLargest(input1);
        if (!Arrays.equals(output1, expected1)) {
            allTestsPassed = false;
            System.out.println("Test 1 Failed: Expected " + Arrays.toString(expected1)
                    + " but got " + Arrays.toString(output1));
        } else {
            System.out.println("Test 1 Passed");
        }

        // Test 2: All digits are the same: [2,2,2] has only one unique permutation.
        int[] input2 = {2, 2, 2};
        int[] expected2 = {2, 2, 2};
        int[] output2 = getSecondLargest(input2);
        if (!Arrays.equals(output2, expected2)) {
            allTestsPassed = false;
            System.out.println("Test 2 Failed: Expected " + Arrays.toString(expected2)
                    + " but got " + Arrays.toString(output2));
        } else {
            System.out.println("Test 2 Passed");
        }

        // Test 3: Two-digit array: [3,1] -> Largest is [3,1], so second largest is [1,3]
        int[] input3 = {3, 1};
        int[] expected3 = {1, 3};
        int[] output3 = getSecondLargest(input3);
        if (!Arrays.equals(output3, expected3)) {
            allTestsPassed = false;
            System.out.println("Test 3 Failed: Expected " + Arrays.toString(expected3)
                    + " but got " + Arrays.toString(output3));
        } else {
            System.out.println("Test 3 Passed");
        }

        // Test 4: Large data input test.
        // We create an array with 1,000,000 random digits (0-9) and ensure the method runs quickly.
        int size = 1_000_000;
        int[] largeInput = new int[size];
        for (int i = 0; i < size; i++) {
            largeInput[i] = (int) (Math.random() * 10); // random digit between 0 and 9
        }
        long startTime = System.currentTimeMillis();
        int[] largeOutput = getSecondLargest(largeInput);
        long endTime = System.currentTimeMillis();
        System.out.println("Large data test executed in " + (endTime - startTime) + " ms.");

        // Final overall test result.
        if (allTestsPassed) {
            System.out.println("All tests passed.");
        } else {
            System.out.println("Some tests failed.");
        }
    }
}