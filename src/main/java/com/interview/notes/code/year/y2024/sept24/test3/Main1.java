package com.interview.notes.code.year.y2024.sept24.test3;


import java.math.BigInteger;

public class Main1 {
    static final int MOD = 1000000007;

    public static void main(String[] args) {
        Main1 solution = new Main1();

        // Test case 1
        int[] nums1 = {2, 1, 3};
        int result1 = numOfWays(nums1);
        System.out.println("Test case 1: " + (result1 == 1 ? "PASS" : "FAIL") + " (Expected: 1, Got: " + result1 + ")");

        // Test case 2
        int[] nums2 = {3, 4, 5, 1, 2};
        int result2 = numOfWays(nums2);
        System.out.println("Test case 2: " + (result2 == 5 ? "PASS" : "FAIL") + " (Expected: 5, Got: " + result2 + ")");

        // Test case 3
        int[] nums3 = {1, 2, 3};
        int result3 = numOfWays(nums3);
        System.out.println("Test case 3: " + (result3 == 0 ? "PASS" : "FAIL") + " (Expected: 0, Got: " + result3 + ")");

        // Additional test case
        int[] nums4 = {4, 2, 1, 3, 5};
        int result4 = numOfWays(nums4);
        System.out.println("Test case 4: " + (result4 == 4 ? "PASS" : "FAIL") + " (Expected: 4, Got: " + result4 + ")");
    }

    public static void main1(String[] args) {
        // Example test cases
        int[] nums1 = {2, 1, 3};
        int[] nums2 = {1, 2, 3};
        int[] nums4 = {4, 2, 1, 3, 5};

        System.out.println(numOfWays(nums1)); // Expected output: 2
        System.out.println(numOfWays(nums2)); // Expected output: 1
        System.out.println(numOfWays(nums4)); // Expected output: 1
    }

    public static int numOfWays(int[] nums) {
        TreeNode root = buildBST(nums);
        return countWays(root);
    }

    private static TreeNode buildBST(int[] nums) {
        TreeNode root = null;
        for (int num : nums) {
            root = insert(root, num);
        }
        return root;
    }

    private static TreeNode insert(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        if (val < root.val) {
            root.left = insert(root.left, val);
        } else {
            root.right = insert(root.right, val);
        }
        return root;
    }

    private static int countWays(TreeNode node) {
        if (node == null) return 1;

        int leftSize = size(node.left);
        int rightSize = size(node.right);

        int ways = (int) comb(leftSize + rightSize, leftSize);
        ways = (int) ((ways * (long) countWays(node.left) % MOD) * countWays(node.right) % MOD);
        return ways;
    }

    private static int size(TreeNode node) {
        if (node == null) return 0;
        return 1 + size(node.left) + size(node.right);
    }

    private static long comb(int n, int k) {
        if (k > n) return 0;
        if (k == 0 || k == n) return 1;

        BigInteger result = BigInteger.ONE;
        for (int i = 0; i < k; i++) {
            result = result.multiply(BigInteger.valueOf(n - i)).divide(BigInteger.valueOf(i + 1));
        }
        return result.mod(BigInteger.valueOf(MOD)).longValue();
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}
