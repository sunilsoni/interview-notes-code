package com.interview.notes.code.year.y2025.feb25.Amazon.test1;

import java.util.*;
/*
12/15 working


 */
public class Solution {

    /*
     * Function: maximizeGroups
     * --------------------------
     * Returns the maximum number of batches (groups) that can be formed
     * given a list of product counts. Each batch i requires i distinct products,
     * and a product type can only contribute at most one item per batch.
     *
     * The key idea is to find the maximum m such that:
     *      sum( min(products[i], m) for all i ) >= m*(m+1)/2
     *
     * We use binary search over m.
     */
    public static int maximizeGroups(List<Integer> products) {
        int n = products.size();
        // The maximum possible number of batches is at most 2*n - 1.
        int left = 0;
        int right = 2 * n; 
        int answer = 0;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            long sum = 0;
            
            // For each product, add the maximum items we can use in m batches (capped at mid)
            for (int p : products) {
                sum += Math.min(p, mid);
                // If we've already reached or exceeded the required value, we can break early.
                if (sum >= (long) mid * (mid + 1) / 2) {
                    break;
                }
            }
            
            long required = (long) mid * (mid + 1) / 2;
            if (sum >= required) {
                answer = mid;      // mid batches can be formed
                left = mid + 1;    // try to see if more batches can be formed
            } else {
                right = mid - 1;   // mid batches cannot be formed, reduce the search space
            }
        }
        
        return answer;
    }
    
    /*
     * runTest: A simple helper method to run and verify test cases.
     */
    public static void runTest(int testId, List<Integer> products, int expected) {
        int result = maximizeGroups(products);
        if (result == expected) {
            System.out.println("Test " + testId + " PASSED. Expected and Got: " + result);
        } else {
            System.out.println("Test " + testId + " FAILED. Expected: " + expected + ", Got: " + result);
        }
    }
    
    /*
     * main: Runs sample tests as well as additional tests including a large-data test.
     */
    public static void main(String[] args) {
        // Sample Case 0
        List<Integer> test0 = Arrays.asList(1, 2, 7);
        // Explanation: Optimal batches are 1, 2, and 3 items respectively.
        runTest(0, test0, 3);
        
        // Sample Case 1
        List<Integer> test1 = Arrays.asList(1, 2, 8, 9);
        // Explanation: Optimal batches are 1, 2, 3, and 4 items respectively.
        runTest(1, test1, 4);
        
        // Edge Case: All products have zero items.
        List<Integer> test2 = Arrays.asList(0, 0, 0, 0);
        runTest(2, test2, 0);
        
        // Edge Case: Only one product with items and others are zero.
        List<Integer> test3 = Arrays.asList(0, 100, 0, 0);
        // With only one non-zero product, at most 1 batch can be formed.
        runTest(3, test3, 1);
        
        // Additional Test: Multiple products each with very high count.
        List<Integer> test4 = Arrays.asList(1000000000, 1000000000, 1000000000, 1000000000, 1000000000);
        // With 5 products, the maximum m satisfies:
        // 5*m >= m*(m+1)/2   => m+1 <= 10, hence m <= 9.
        runTest(4, test4, 9);
        
        // Large Data Test: n = 100,000 products, each with 10^9 items.
        int n = 100000;
        List<Integer> testLarge = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            testLarge.add(1000000000);
        }
        // For n products, the maximum m is 2*n - 1 because:
        // sum(min(10^9, m)) = n * m and the condition becomes n*m >= m*(m+1)/2.
        // For m = 2*n - 1, both sides are equal.
        int expectedLarge = 2 * n - 1;
        runTest(5, testLarge, expectedLarge);
    }
}