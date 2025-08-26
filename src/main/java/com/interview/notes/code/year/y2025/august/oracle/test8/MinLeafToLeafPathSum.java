package com.interview.notes.code.year.y2025.august.oracle.test8;

/*
Here’s the properly combined **final question** from your screenshot:

---

**Question:**
Given a binary tree in which each node element contains a number, write a function to find the minimum possible sum from one leaf node to another.

* If one side of the root is empty, the function should return **minus infinity**.

**Examples:**

Input:

```
       4
      / \
     5  -6
    / \  / \
   2 -3  1  8
```

Output:

```
1
```

Minimum sum path between two leaf nodes is:

```
-3 → 5 → 4 → -6 → 1
```

---

Input:

```
       3
      / \
     2   4
    / \
  -5   1
```

Output:

```
-2
```

---

Do you also want me to **prepare interview-ready solutions (Java/Python)** for this one in the same concise + deep-dive format as the first?

 */
class TreeNode {
    int val;
    TreeNode left, right;

    TreeNode(int val) {
        this.val = val;
        left = right = null;
    }
}

public class MinLeafToLeafPathSum {

    // Global variable to store minimum sum
    private static int minSum;

    public static int findMinLeafToLeafSum(TreeNode root) {
        // If one side of root is missing → return minus infinity
        if (root == null || root.left == null || root.right == null) {
            return Integer.MIN_VALUE;
        }

        minSum = Integer.MAX_VALUE;
        helper(root);

        return minSum;
    }

    // Returns the minimum path sum from node to leaf
    private static int helper(TreeNode node) {
        if (node == null) {
            return Integer.MAX_VALUE;
        }

        // Leaf node → return its value
        if (node.left == null && node.right == null) {
            return node.val;
        }

        // Recursive calls
        int leftSum = helper(node.left);
        int rightSum = helper(node.right);

        // If both children exist → check leaf-to-leaf path through this node
        if (node.left != null && node.right != null) {
            int candidate = leftSum + rightSum + node.val;
            minSum = Math.min(minSum, candidate);
            return node.val + Math.min(leftSum, rightSum);
        }

        // If only one child exists → propagate that path
        return node.val + (node.left != null ? leftSum : rightSum);
    }

    // ==========================
    // TESTING
    // ==========================
    public static void main(String[] args) {
        // Example 1
        TreeNode root1 = new TreeNode(4);
        root1.left = new TreeNode(5);
        root1.right = new TreeNode(-6);
        root1.left.left = new TreeNode(2);
        root1.left.right = new TreeNode(-3);
        root1.right.left = new TreeNode(1);
        root1.right.right = new TreeNode(8);

        int result1 = findMinLeafToLeafSum(root1);
        System.out.println("Output: " + result1 + " (Expected: 1)");

        // Example 2
        TreeNode root2 = new TreeNode(3);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(4);
        root2.left.left = new TreeNode(-5);
        root2.left.right = new TreeNode(1);

        int result2 = findMinLeafToLeafSum(root2);
        System.out.println("Output: " + result2 + " (Expected: -2)");

        // Edge Case: Only one subtree
        TreeNode root3 = new TreeNode(10);
        root3.left = new TreeNode(5);

        int result3 = findMinLeafToLeafSum(root3);
        System.out.println("Output: " + result3 + " (Expected: " + Integer.MIN_VALUE + ")");
    }
}