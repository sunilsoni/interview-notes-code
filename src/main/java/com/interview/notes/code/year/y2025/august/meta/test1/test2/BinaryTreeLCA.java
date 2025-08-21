package com.interview.notes.code.year.y2025.august.meta.test1.test2;
/*
Here’s the **final combined question** after merging both images and your discussion points:

---

**Question:**
Balanced Parentheses with Triangle Brackets

You are given input strings containing only triangular parentheses `<` and `>`.

### Part A – Valid Parentheses Check

A string is considered **valid** if:

1. It contains an equal number of opening `<` and closing `>` parentheses.
2. At any point in the string, the number of closing `>` parentheses never exceeds the number of opening `<` parentheses.

* **Examples (Valid):** `<>`, `<><>`, `<<>>>`
* **Counter-examples (Invalid):** `<<>`, `<<>><`

👉 Write a function that checks whether a given string is valid.

---

### Part B – Minimum Additions to Make Valid

If the input is **not valid**, determine the **minimum number of parentheses that must be added** to make it valid.

* **Examples:**

  * Input: `<<<` → Output: `3` (needs 3 closing `>` → `<<<>>>`)
  * Input: `<>` → Output: `1` (needs 1 opening `<` → `<><`)

👉 Write a function that returns the number of parentheses that need to be added.

---

Do you want me to also **provide the full solution code** (Java/Python) for both **Part A** and **Part B**?

 */
public class BinaryTreeLCA {
    // Node structure for binary tree
    static class TreeNode {
        int val;            // Value stored in the node
        TreeNode left;      // Reference to left child
        TreeNode right;     // Reference to right child
        
        // Constructor to create a new node with given value
        TreeNode(int val) {
            this.val = val;
        }
    }
    
    // Main method to find LCA of two nodes in binary tree
    public static TreeNode findLCA(TreeNode root, TreeNode p, TreeNode q) {
        // Base case: if root is null or matches either p or q, return root
        if (root == null || root == p || root == q) {
            return root;
        }
        
        // Recursively search in left subtree
        TreeNode leftLCA = findLCA(root.left, p, q);
        
        // Recursively search in right subtree
        TreeNode rightLCA = findLCA(root.right, p, q);
        
        // If both left and right returned non-null, root is LCA
        if (leftLCA != null && rightLCA != null) {
            return root;
        }
        
        // Return non-null value (either left or right)
        return leftLCA != null ? leftLCA : rightLCA;
    }
    
    // Helper method to create test tree
    private static TreeNode createTestTree() {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(6);
        root.right.right = new TreeNode(4);
        return root;
    }
    
    // Test method to verify solution
    public static void main(String[] args) {
        // Create test tree
        TreeNode root = createTestTree();
        
        // Test Case 1: LCA of 2 and 6
        TreeNode p1 = root.left.left;  // node with value 2
        TreeNode q1 = root.left.right; // node with value 6
        TreeNode lca1 = findLCA(root, p1, q1);
        System.out.println("Test Case 1: " + 
            (lca1.val == 9 ? "PASS" : "FAIL") + 
            " (Expected: 9, Got: " + lca1.val + ")");
        
        // Test Case 2: LCA of 7 and 6
        TreeNode p2 = root.right;      // node with value 7
        TreeNode q2 = root.left.right; // node with value 6
        TreeNode lca2 = findLCA(root, p2, q2);
        System.out.println("Test Case 2: " + 
            (lca2.val == 3 ? "PASS" : "FAIL") + 
            " (Expected: 3, Got: " + lca2.val + ")");
        
        // Test Case 3: LCA when one node is root
        TreeNode lca3 = findLCA(root, root, root.left);
        System.out.println("Test Case 3: " + 
            (lca3.val == 3 ? "PASS" : "FAIL") + 
            " (Expected: 3, Got: " + lca3.val + ")");
        
        // Test Case 4: LCA of leaf nodes in different subtrees
        TreeNode p4 = root.left.left;   // node with value 2
        TreeNode q4 = root.right.right; // node with value 4
        TreeNode lca4 = findLCA(root, p4, q4);
        System.out.println("Test Case 4: " + 
            (lca4.val == 3 ? "PASS" : "FAIL") + 
            " (Expected: 3, Got: " + lca4.val + ")");
    }
}
