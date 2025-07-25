package com.interview.notes.code.year.y2025.july.common.test7;

// Main class to test longest N-stable subarray logic
public class NStableSubarrayTest {

    // Entry point: run tests from here
    public static void main(String[] args) {
        // Sample arrays to test against
        int[][] testArrays = {
                {4, 2, 3, 6, 2, 2, 3, 2, 7},   // Test case 1 array
                {8, 2, 4, 7},                  // Test case 2 array
                {1, 3, 4, 5, 6, 7, 8, 9, 9, 9} // Test case 3 array
        };
        // N values for each sample
        int[] Ns = {1, 4, 7};
        // Expected results for each sample
        int[] expected = {4, 2, 9};

        // Loop through each sample to run the test
        for (int i = 0; i < testArrays.length; i++) {
            // Compute result for current test case
            int result = longestNStableSubarray(testArrays[i], Ns[i]);
            // Check if computed result matches expected
            if (result == expected[i]) {
                // Print PASS if it matches
                System.out.println("Test " + (i + 1) + ": PASS");
            } else {
                // Print FAIL with details if it does not
                System.out.println("Test " + (i + 1) + ": FAIL - expected "
                        + expected[i] + " but got " + result);
            }
        }

        // Define size for a large test to check performance on big inputs
        int largeSize = 100000;
        // Generate a large array filled with the same number (7) using Java 8 Streams
        int[] largeTest = java.util.stream.IntStream
                .generate(() -> 7)  // always produce 7
                .limit(largeSize)    // repeat 100,000 times
                .toArray();          // collect into an int[]

        // Set N to 0 so all identical elements form a stable subarray
        int Nlarge = 0;
        // For identical elements, the entire array should be stable
        int expectedLarge = largeSize;

        // Compute result for the large test
        int largeResult = longestNStableSubarray(largeTest, Nlarge);
        // Print PASS or FAIL for the large test
        if (largeResult == expectedLarge) {
            System.out.println("Large test: PASS");
        } else {
            System.out.println("Large test: FAIL - expected "
                    + expectedLarge + " but got " + largeResult);
        }
    }

    // Method to find the longest N-stable subarray in an array
    public static int longestNStableSubarray(int[] arr, int N) {
        // Deque to track indices of potential maximums in current window
        java.util.Deque<Integer> maxDeque = new java.util.ArrayDeque<>();
        // Deque to track indices of potential minimums in current window
        java.util.Deque<Integer> minDeque = new java.util.ArrayDeque<>();
        // Left pointer of sliding window
        int left = 0;
        // Stores the length of the longest valid window found so far
        int result = 0;

        // Expand the window by moving the right pointer
        for (int right = 0; right < arr.length; right++) {
            // Maintain maxDeque in decreasing order of values
            while (!maxDeque.isEmpty() && arr[right] > arr[maxDeque.peekLast()]) {
                maxDeque.pollLast(); // remove smaller values
            }
            maxDeque.addLast(right); // add current index

            // Maintain minDeque in increasing order of values
            while (!minDeque.isEmpty() && arr[right] < arr[minDeque.peekLast()]) {
                minDeque.pollLast(); // remove larger values
            }
            minDeque.addLast(right); // add current index

            // Shrink window from left if difference > N
            while (arr[maxDeque.peekFirst()] - arr[minDeque.peekFirst()] > N) {
                // If left index is at front of maxDeque, remove it
                if (maxDeque.peekFirst() == left) {
                    maxDeque.pollFirst();
                }
                // If left index is at front of minDeque, remove it
                if (minDeque.peekFirst() == left) {
                    minDeque.pollFirst();
                }
                left++; // move left pointer to the right
            }

            // Update result if current window is larger
            result = Math.max(result, right - left + 1);
        }

        // Return the size of the longest N-stable subarray
        return result;
    }
}