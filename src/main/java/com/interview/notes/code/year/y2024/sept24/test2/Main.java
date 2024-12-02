package com.interview.notes.code.year.y2024.sept24.test2;

import java.util.ArrayList;
import java.util.List;

/*
Java Assessment
Given an array nums that represents a permutation of integers from 1 to n. We are going to construct a binary search tree (BST) by inserting the elements of nums in order into an initially empty BST. Find the number of different ways to reorder nums so that the constructed BST is identical to
that formed from the original array nums. For example, given nums = [2,1,3], we will have 2 as the root, 1 as a left child, and 3 as a right child. The
array [2,3,1] also yields the same BST but [3,2,1] yields a different BST. Return the number of ways to reorder nums such that the BST formed is identical to the original BST formed from nums. Note : BST is, For each node, all values in its left subtree are smaller, and all values in its right subtree are larger. Since the answer may be very large, return it modulo 109 + 7.
 */
class Solution {
    private static final int MOD = 1000000007;
    private long[][] memo;

    public int numOfWays(int[] nums) {
        int n = nums.length;
        memo = new long[n + 1][n + 1];
        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            list.add(num);
        }
        return (int) ((dfs(list) - 1 + MOD) % MOD);
    }

    private long dfs(List<Integer> nums) {
        int n = nums.size();
        if (n <= 2) return 1;

        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        int root = nums.get(0);

        for (int i = 1; i < n; i++) {
            if (nums.get(i) < root) {
                left.add(nums.get(i));
            } else {
                right.add(nums.get(i));
            }
        }

        long leftWays = dfs(left);
        long rightWays = dfs(right);

        return (((leftWays * rightWays) % MOD) * comb(n - 1, left.size())) % MOD;
    }

    private long comb(int n, int k) {
        if (k == 0 || k == n) return 1;
        if (memo[n][k] != 0) return memo[n][k];
        memo[n][k] = (comb(n - 1, k - 1) + comb(n - 1, k)) % MOD;
        return memo[n][k];
    }
}

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test case 1
        int[] nums1 = {2, 1, 3};
        int result1 = solution.numOfWays(nums1);
        System.out.println("Test case 1: " + (result1 == 1 ? "PASS" : "FAIL") + " (Expected: 1, Got: " + result1 + ")");

        // Test case 2
        int[] nums2 = {3, 4, 5, 1, 2};
        int result2 = solution.numOfWays(nums2);
        System.out.println("Test case 2: " + (result2 == 5 ? "PASS" : "FAIL") + " (Expected: 5, Got: " + result2 + ")");

        // Test case 3
        int[] nums3 = {1, 2, 3};
        int result3 = solution.numOfWays(nums3);
        System.out.println("Test case 3: " + (result3 == 0 ? "PASS" : "FAIL") + " (Expected: 0, Got: " + result3 + ")");

        // Additional test case
        int[] nums4 = {4, 2, 1, 3, 5};
        int result4 = solution.numOfWays(nums4);
        System.out.println("Test case 4: " + (result4 == 4 ? "PASS" : "FAIL") + " (Expected: 4, Got: " + result4 + ")");
    }
}
