package com.interview.notes.code.year.y2024.aug24.test33;

class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test Case 1
        TreeNode root1 = new TreeNode(5);
        root1.left = new TreeNode(5);
        root1.right = new TreeNode(15);
        root1.left.left = new TreeNode(3);
        root1.left.right = new TreeNode(7);
        root1.right.right = new TreeNode(18);

        int result1 = solution.rangeSumBST(root1, 7, 15);
        System.out.println("Test Case 1 Result: " + result1);
        System.out.println("Test Case 1 " + (result1 == 32 ? "Passed" : "Failed"));

        // Test Case 2
        TreeNode root2 = new TreeNode(10);
        root2.left = new TreeNode(5);
        root2.right = new TreeNode(15);
        root2.left.left = new TreeNode(3);
        root2.left.right = new TreeNode(7);
        root2.right.left = new TreeNode(13);
        root2.right.right = new TreeNode(18);
        root2.left.left.left = new TreeNode(1);
        root2.left.right.left = new TreeNode(6);

        int result2 = solution.rangeSumBST(root2, 6, 10);
        System.out.println("Test Case 2 Result: " + result2);
        System.out.println("Test Case 2 " + (result2 == 23 ? "Passed" : "Failed"));

        // Test Case 3: Empty tree
        int result3 = solution.rangeSumBST(null, 1, 100);
        System.out.println("Test Case 3 Result: " + result3);
        System.out.println("Test Case 3 " + (result3 == 0 ? "Passed" : "Failed"));

        // Test Case 4: Single node tree
        TreeNode root4 = new TreeNode(5);
        int result4 = solution.rangeSumBST(root4, 5, 5);
        System.out.println("Test Case 4 Result: " + result4);
        System.out.println("Test Case 4 " + (result4 == 5 ? "Passed" : "Failed"));

        // Test Case 5: No nodes in range
        TreeNode root5 = new TreeNode(10);
        root5.left = new TreeNode(5);
        root5.right = new TreeNode(15);
        int result5 = solution.rangeSumBST(root5, 20, 30);
        System.out.println("Test Case 5 Result: " + result5);
        System.out.println("Test Case 5 " + (result5 == 0 ? "Passed" : "Failed"));
    }

    public int rangeSumBST2(TreeNode root, int low, int high) {
        if (root == null) {
            return 0;
        }

        int sum = 0;

        if (root.val >= low && root.val <= high) {
            sum += root.val;
        }

        if (root.val > low) {
            sum += rangeSumBST(root.left, low, high);
        }

        if (root.val < high) {
            sum += rangeSumBST(root.right, low, high);
        }

        return sum;
    }

    public int rangeSumBST(TreeNode root, int low, int high) {
        if (root == null) {
            return 0;
        }

        int sum = 0;

        if (root.val >= low && root.val <= high) {
            sum += root.val;
        }

        if (root.val > low) {
            sum += rangeSumBST(root.left, low, high);
        }

        if (root.val < high) {
            sum += rangeSumBST(root.right, low, high);
        }

        return sum;
    }

    public int rangeSumBST1(TreeNode root, int low, int high) {
        if (root == null) {
            return 0;
        }

        int sum = 0;

        // If current node's value is within range, add it to sum
        if (root.val >= low && root.val <= high) {
            sum += root.val;
        }

        // If current value is greater than low, explore left subtree
        if (root.val > low) {
            sum += rangeSumBST(root.left, low, high);
        }

        // If current value is less than high, explore right subtree
        if (root.val < high) {
            sum += rangeSumBST(root.right, low, high);
        }

        return sum;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }
}
