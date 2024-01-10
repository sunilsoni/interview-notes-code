package com.interview.notes.code.months.year2023.june23.test6;

/*
In Java Problem Statement #

Given an array of positive numbers and a positive number ‘k,’
find the maximum sum of any contiguous subarray of size ‘k’.

Example 1:
Input: [2, 1, 5, 1, 3, 2], k=3
Output: 9

Explanation: Subarray with maximum sum is [5, 1, 3].

Example 2:
Input: [2, 3, 4, 1, 5], k=2
Output: 7

Explanation: Subarray with maximum sum is [3, 4].
 */
public class MaxSumSubArrayOfSizeK {

    /*
   This code works in O(n) time complexity where n is the number of elements in the input array, and it uses O(1) extra space for the variable used in the computation
    */
    public static int findMaxSumSubArray(int k, int[] arr) {
        int maxSum = 0, windowSum = 0;
        int start = 0;

        for (int end = 0; end < arr.length; end++) {
            windowSum += arr[end];  // add the next element
            // slide the window, we don't need to slide if we've not hit the required window size of 'k'
            if (end >= k - 1) {
                maxSum = Math.max(maxSum, windowSum);
                windowSum -= arr[start]; // subtract the element going out
                start++; // slide the window ahead
            }
        }

        return maxSum;
    }

    public static void main(String[] args) {
        System.out.println("Maximum sum of a subarray of size K: "
                + MaxSumSubArrayOfSizeK.findMaxSumSubArray2(3, new int[]{2, 1, 5, 1, 3, 2}));
        System.out.println("Maximum sum of a subarray of size K: "
                + MaxSumSubArrayOfSizeK.findMaxSumSubArray2(2, new int[]{2, 3, 4, 1, 5}));
    }


    public static int findMaxSumSubArray2(int k, int[] arr) {
        int maxSum = 0;
        int windowSum = 0;
        int windowStart = 0;
        for (int windowEnd = 0; windowEnd < arr.length; windowEnd++) {
            windowSum += arr[windowEnd];
            if (windowEnd >= k - 1) {
                maxSum = Math.max(maxSum, windowSum);
                windowSum -= arr[windowStart];
                windowStart++;
            }
        }
        return maxSum;
    }

    public static int findMaximumSum(int[] array, int k) {
        int maxSum = Integer.MIN_VALUE;
        int currentSum = 0;

        for (int i = 0; i < array.length; i++) {
            currentSum += array[i];

            if (i >= k - 1) {
                maxSum = Math.max(maxSum, currentSum);
                currentSum -= array[i - k + 1];
            }
        }

        return maxSum;
    }
}
