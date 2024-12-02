package com.interview.notes.code.year.y2023.Sep23.amazon;

public class BSTToGreaterTree {

    public static TreeNode convertBST(TreeNode root) {
        int sum = 0;
        convertBSTHelper(root, sum);
        return root;
    }

    private static void convertBSTHelper(TreeNode root, int sum) {
        if (root == null) {
            return;
        }

        convertBSTHelper(root.right, sum);
        sum += root.val;
        root.val = sum;
        convertBSTHelper(root.left, sum);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(1);
        root.right = new TreeNode(6);
        root.left.right = new TreeNode(2);
        root.right.left = new TreeNode(5);

        TreeNode greaterTreeRoot = convertBST(root);

        System.out.println(greaterTreeRoot.val); // 15
        System.out.println(greaterTreeRoot.left.val); // 13
        System.out.println(greaterTreeRoot.right.val); // 9
        System.out.println(greaterTreeRoot.left.right.val); // 11
        System.out.println(greaterTreeRoot.right.left.val); // 10
    }
}
