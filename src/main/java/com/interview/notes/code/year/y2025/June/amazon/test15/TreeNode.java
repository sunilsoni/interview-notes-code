package com.interview.notes.code.year.y2025.June.amazon.test15;

import java.util.LinkedList;
import java.util.Queue;

/*

 Right. So basically, the question is, basically what you need to do is you have a binary tree. Okay. And then what you have to do is you call some function.

And then that function should be like connecting all the nodes on the signal in a zigzag. Manner. Okay. Okay. Do we need to call this and connect all the nodes? Say that again. So, do we need to connect all the nodes?

Like, suppose taking the root like five, then four, six. Then three to seven. So if you look at. The notes, right? You have, like, five in the root node, and then you have four, six on. One level, and then you have to seven on one.

Yes.
### **Question: Connect nodes in Zig Zag order**

Given a binary tree, connect each node to its adjacent node on the same level in **zig-zag** order (alternating left-to-right and right-to-left for each level).

#### **Example Tree:**

```
        5
       / \
      4   6
     / \ / \
    3  2 7  8
```

#### **Zig Zag Connections:**

```
Level 0: 5 -> null
Level 1: null <- 4 <- 6
Level 2: 3 -> 2 -> 7 -> 8 -> null
```

---

### **Node Definition:**

```java
class Node {
    public int val;
    public Node left, right;
    public Node next;

    public Node(int val) {
        this.val = val;
    }
}
```

---
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode next;
    
    TreeNode(int val) {
        this.val = val;
    }
}

class Solution {
    public void connect(TreeNode root) {
        if (root == null) return;
        
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        q.offer(null);
        
        TreeNode prev = null;
        int level = 0;
        
        while (q.size() > 1) {
            TreeNode curr = q.poll();
            
            if (curr == null) {
                level++;
                q.offer(null);
                prev = null;
                continue;
            }
            
            if (curr.left != null) q.offer(curr.left);
            if (curr.right != null) q.offer(curr.right);
            
            if (prev != null) {
                if (level % 2 == 0) {
                    prev.next = curr;
                } else {
                    curr.next = prev;
                }
            }
            prev = curr;
        }
    }

    // Helper method to print the tree connections
    public void printConnections(TreeNode root) {
        TreeNode levelStart = root;
        
        while (levelStart != null) {
            TreeNode curr = levelStart;
            while (curr != null) {
                System.out.print(curr.val);
                if (curr.next != null) {
                    System.out.print(" -> ");
                }
                curr = curr.next;
            }
            System.out.println();
            levelStart = levelStart.left;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        // Example 1
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(3);
        root1.left.left = new TreeNode(4);
        root1.left.right = new TreeNode(5);
        root1.right.left = new TreeNode(6);
        root1.right.right = new TreeNode(7);

        System.out.println("Example 1:");
        solution.connect(root1);
        solution.printConnections(root1);
        /*
        Output:
        1
        2 -> 3
        7 -> 6 -> 5 -> 4
        */

        // Example 2
        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(3);
        root2.left.right = new TreeNode(4);
        root2.right.right = new TreeNode(5);

        System.out.println("\nExample 2:");
        solution.connect(root2);
        solution.printConnections(root2);
        /*
        Output:
        1
        2 -> 3
        5 -> 4
        */
    }
}
