package com.interview.notes.code.year.y2025.august.adobe.test2;

/*
Here’s the combined and cleaned-up text from your screenshots, forming a complete question with all details:

---

**Validate Binary Search Tree Problem**

Given the `root` of a binary tree, determine if it is a valid binary search tree (BST).

A valid BST is defined as follows:

* The left subtree of a node contains only nodes with keys less than the node’s key.
* The right subtree of a node contains only nodes with keys greater than the node’s key.
* Both the left and right subtrees must also be binary search trees.

**Example 1:**

```
Input: root = [2,1,3]
Output: true
Explanation:
    2
   / \
  1   3
```

**Example 2:**

```
Input: root = [5,1,4,null,null,3,6]
Output: false
Explanation: The root node's value is 5.
Its right child is 4, which is less than 5.
This violates the BST property.
    5
   / \
  1   4
     / \
    3   6
```

**Example 3:**

```
Input: root = [10,5,15,null,null,6,20]
Output: false
Explanation: The node with value 6 is in the right subtree of node 10,
but 6 < 10, which violates the BST property.
    10
   /  \
  5    15
      /  \
     6    20
```

**Constraints:**

* The number of nodes in the tree is in the range \[0, 10⁴].
* -2³¹ ≤ Node.val ≤ 2³¹ - 1

---

**Class Definitions:**

```java
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {}

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
```

---

**Solution Class:**

```java
public class ValidateBST {
    public boolean isValidBST(TreeNode root) {
        // TODO: Implement your solution here
    }
}
```

---

**Main Method with Test Cases:**

```java
public static void main(String[] args) {
    ValidateBST solution = new ValidateBST();

    // Test case 1: [2,1,3] -> true
    TreeNode test1 = new TreeNode(2);
    test1.left = new TreeNode(1);
    test1.right = new TreeNode(3);
    System.out.println("\nTest 1: [2,1,3]");
    System.out.println("Expected: true");
    System.out.println("Actual: " + solution.isValidBST(test1));

    // Test case 2: [5,1,4,null,null,3,6] -> false
    TreeNode test2 = new TreeNode(5);
    test2.left = new TreeNode(1);
    test2.right = new TreeNode(4);
    test2.right.left = new TreeNode(3);
    test2.right.right = new TreeNode(6);
    System.out.println("\nTest 2: [5,1,4,null,null,3,6]");
    System.out.println("Expected: false");
    System.out.println("Actual: " + solution.isValidBST(test2));

    // Test case 3: [10,5,15,null,null,6,20] -> false
    TreeNode test3 = new TreeNode(10);
    test3.left = new TreeNode(5);
    test3.right = new TreeNode(15);
    test3.right.left = new TreeNode(6);
    test3.right.right = new TreeNode(20);
    System.out.println("\nTest 3: [10,5,15,null,null,6,20]");
    System.out.println("Expected: false");
    System.out.println("Actual: " + solution.isValidBST(test3));

    // Test case 4: Single node -> true
    TreeNode test4 = new TreeNode(1);
    System.out.println("\nTest 4: [1]");
    System.out.println("Expected: true");
    System.out.println("Actual: " + solution.isValidBST(test4));

    // Test case 5: Empty tree -> true
    System.out.println("\nTest 5: []");
    System.out.println("Expected: true");
    System.out.println("Actual: " + solution.isValidBST(null));

    // Test case 6: Edge case with Integer limits
    TreeNode test6 = new TreeNode(Integer.MAX_VALUE);
    System.out.println("\nTest 6: [" + Integer.MAX_VALUE + "]");
    System.out.println("Expected: true");
    System.out.println("Actual: " + solution.isValidBST(test6));
}
```

---

If you want, I can now **implement the `isValidBST` method** in an optimal way using recursion with min/max bounds.
This will make your tests pass.

 */
public class ValidateBST {
    // Main method with comprehensive test cases
    public static void main(String[] args) {
        ValidateBST solution = new ValidateBST();
        int testsPassed = 0;
        int totalTests = 0;

        // Test Case 1: Valid BST
        TreeNode test1 = new TreeNode(2);
        test1.left = new TreeNode(1);
        test1.right = new TreeNode(3);
        totalTests++;
        boolean result1 = solution.isValidBST(test1);
        System.out.println("Test 1: " + (result1 ? "PASS" : "FAIL"));
        if (result1) testsPassed++;

        // Test Case 2: Invalid BST (violates right subtree property)
        TreeNode test2 = new TreeNode(5);
        test2.left = new TreeNode(1);
        test2.right = new TreeNode(4);
        test2.right.left = new TreeNode(3);
        test2.right.right = new TreeNode(6);
        totalTests++;
        boolean result2 = !solution.isValidBST(test2);
        System.out.println("Test 2: " + (result2 ? "PASS" : "FAIL"));
        if (result2) testsPassed++;

        // Test Case 3: Edge case - single node
        TreeNode test3 = new TreeNode(1);
        totalTests++;
        boolean result3 = solution.isValidBST(test3);
        System.out.println("Test 3: " + (result3 ? "PASS" : "FAIL"));
        if (result3) testsPassed++;

        // Test Case 4: Edge case - empty tree
        totalTests++;
        boolean result4 = solution.isValidBST(null);
        System.out.println("Test 4: " + (result4 ? "PASS" : "FAIL"));
        if (result4) testsPassed++;

        // Test Case 5: Large tree test
        TreeNode test5 = createLargeTree(1000); // Create a large valid BST
        totalTests++;
        boolean result5 = solution.isValidBST(test5);
        System.out.println("Test 5 (Large Tree): " + (result5 ? "PASS" : "FAIL"));
        if (result5) testsPassed++;

        // Summary of test results
        System.out.println("\nTest Summary:");
        System.out.println("Total Tests: " + totalTests);
        System.out.println("Tests Passed: " + testsPassed);
        System.out.println("Success Rate: " + (testsPassed * 100.0 / totalTests) + "%");
    }

    // Helper method to create a large balanced BST for testing
    private static TreeNode createLargeTree(int size) {
        return createLargeTreeHelper(1, size);
    }

    private static TreeNode createLargeTreeHelper(int start, int end) {
        if (start > end) {
            return null;
        }
        int mid = (start + end) / 2;
        TreeNode node = new TreeNode(mid);
        node.left = createLargeTreeHelper(start, mid - 1);
        node.right = createLargeTreeHelper(mid + 1, end);
        return node;
    }

    // Method to validate if a binary tree is a valid BST
    public boolean isValidBST(TreeNode root) {
        // Call helper method with initial bounds as Long.MIN_VALUE and Long.MAX_VALUE
        // Using Long to handle edge cases where node values are Integer.MIN_VALUE or Integer.MAX_VALUE
        return isValidBSTHelper(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    // Helper method that checks if node values are within valid bounds
    private boolean isValidBSTHelper(TreeNode node, long min, long max) {
        // Base case: empty node is considered valid
        if (node == null) {
            return true;
        }

        // Check if current node's value is within the valid range
        if (node.val <= min || node.val >= max) {
            return false;
        }

        // Recursively check left subtree (must be less than current node)
        // and right subtree (must be greater than current node)
        return isValidBSTHelper(node.left, min, node.val) &&
                isValidBSTHelper(node.right, node.val, max);
    }
}
