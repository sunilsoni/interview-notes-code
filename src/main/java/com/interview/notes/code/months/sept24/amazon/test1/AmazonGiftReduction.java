package com.interview.notes.code.months.sept24.amazon.test1;

import java.util.Arrays;

public class AmazonGiftReduction {

    public static void main(String[] args) {
        // Test Case 0
        int[] prices0 = {9, 6, 7, 2, 7, 2};
        int k0 = 2;
        int threshold0 = 13;
        int expected0 = 2;
        int result0 = reduceGifts(prices0, k0, threshold0);
        System.out.println("Test Case 0: " + (result0 == expected0 ? "Pass" : "Fail") + " - Expected: " + expected0 + ", Actual: " + result0);

        // Test Case 1
        int[] prices1 = {9, 6, 3, 2, 9, 10, 10, 11};
        int k1 = 4;
        int threshold1 = 1;
        int expected1 = 5;
        int result1 = reduceGifts(prices1, k1, threshold1);
        System.out.println("Test Case 1: " + (result1 == expected1 ? "Pass" : "Fail") + " - Expected: " + expected1 + ", Actual: " + result1);

        // Additional Edge Cases
        // Test Case 2: Empty prices array
        int[] prices2 = {};
        int k2 = 3;
        int threshold2 = 10;
        int expected2 = 0;
        int result2 = reduceGifts(prices2, k2, threshold2);
        System.out.println("Test Case 2: " + (result2 == expected2 ? "Pass" : "Fail") + " - Expected: " + expected2 + ", Actual: " + result2);

        // Test Case 3: k = 1
        int[] prices3 = {5, 10, 15, 20};
        int k3 = 1;
        int threshold3 = 8;
        int expected3 = 3;
        int result3 = reduceGifts(prices3, k3, threshold3);
        System.out.println("Test Case 3: " + (result3 == expected3 ? "Pass" : "Fail") + " - Expected: " + expected3 + ", Actual: " + result3);
    }

    public static int reduceGifts(int[] prices, int k, int threshold) {
        // Handle edge case: If the array length is less than k, no removal is needed
        if (prices.length <= k) {
            return 0;
        }

        // Sort the prices array in descending order
        Arrays.sort(prices);
        for (int i = 0, j = prices.length - 1; i < j; i++, j--) {
            int temp = prices[i];
            prices[i] = prices[j];
            prices[j] = temp;
        }

        int removals = 0;
        int currentSum = 0;
        int left = 0;
        int right = 0;

        while (right < prices.length) {
            currentSum += prices[right];

            while (currentSum > threshold) {
                currentSum -= prices[left];
                left++;
            }

            if (right - left + 1 >= k) {
                // We found a subarray of size k or more with sum <= threshold
                return removals;
            }

            right++;
        }

        // If no subarray of size k or more found with sum <= threshold
        // we need to reduce the array size to less than k.
        return Math.max(0, prices.length - k + 1);
    }
}