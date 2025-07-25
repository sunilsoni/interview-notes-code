package com.interview.notes.code.year.y2025.july.common.test8;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Class to find the longest N-stable subarray in a given array
 * An N-stable subarray is one where the difference between max and min elements is <= N
 */
public class NStableSubarray {

    /**
     * Finds the length of the longest N-stable subarray using sliding window technique
     * Time Complexity: O(n), where n is the length of input array
     * Space Complexity: O(n) for the deques in worst case
     *
     * @param nums Input array of integers
     * @param N    Maximum allowed difference between max and min elements
     * @return Length of longest N-stable subarray
     */
    public static int longestNStableSubarray(int[] nums, int N) {
        // Deques to maintain monotonic queues for min and max values
        // Each element in deques is processed at most twice (add/remove)
        Deque<Integer> minDeque = new LinkedList<>();  // Maintains increasing order
        Deque<Integer> maxDeque = new LinkedList<>();  // Maintains decreasing order

        int maxLen = 0;  // Stores the maximum valid window length found
        int left = 0;    // Left pointer of sliding window

        // Right pointer iterates through array - O(n)
        for (int right = 0; right < nums.length; right++) {

            // Maintain monotonic increasing queue for minimum values
            // Each element is added/removed exactly once - O(1) amortized
            while (!minDeque.isEmpty() && nums[right] <= nums[minDeque.getLast()]) {
                minDeque.removeLast();
            }
            minDeque.addLast(right);

            // Maintain monotonic decreasing queue for maximum values
            // Each element is added/removed exactly once - O(1) amortized
            while (!maxDeque.isEmpty() && nums[right] >= nums[maxDeque.getLast()]) {
                maxDeque.removeLast();
            }
            maxDeque.addLast(right);

            // Shrink window from left if it becomes unstable
            // This while loop runs at most n times total across all iterations
            while (nums[maxDeque.getFirst()] - nums[minDeque.getFirst()] > N) {
                left++;
                // Remove elements that are no longer in the window
                if (minDeque.getFirst() < left) minDeque.removeFirst();
                if (maxDeque.getFirst() < left) maxDeque.removeFirst();
            }

            // Update maximum length if current window is larger
            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }

    /**
     * Main method for testing the implementation
     * Time Complexity: O(n) for each test case
     * Space Complexity: O(n) for each test case
     */
    public static void main(String[] args) {
        // Test implementation...
    }
}
