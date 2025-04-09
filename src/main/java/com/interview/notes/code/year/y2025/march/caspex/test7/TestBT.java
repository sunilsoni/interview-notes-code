package com.interview.notes.code.year.y2025.march.caspex.test7;

import java.util.*;

/*
100% WORKING

Here is the combined version of the Zigzag Tree Traversal problem:

---

### **Zigzag Tree Traversal**

Traverse the given binary tree in a zigzag manner and print the node values.
- Odd levels should be printed **right to left**.
- Even levels should be printed **left to right**.
- Assume the root node is at **level 1**.

---

### **Input**

- The first line contains an integer `N`, representing the number of edges in the tree.
- The second line contains `N` space-separated edge descriptions in the format:
  ```
  node1 node2 L/R
  ```
  Where `L` indicates `node2` is the left child of `node1`, and `R` indicates `node2` is the right child.

---

### **Output**

- Print the zigzag level order traversal of the tree as space-separated integers.

---

### **Constraints**

- 1 ≤ Number of nodes ≤ 3000
- 1 ≤ Data of a node ≤ 1000

---

### **Examples**

**Example #1**
**Input:**
```
2
10 20 R 10 30 L
```
**Output:**
```
10 30 20
```

**Example #2**
**Input:**
```
4
2 4 L 2 6 R 4 8 L 4 10 R
```
**Output:**
```
2 4 6 10 8
```

---

```
 */
class Node {
    int data;
    Node left, right;

    Node(int value) {
        data = value;
        left = right = null;
    }
}

class BT {
    Node root;
    Map<Integer, Node> dangling = new HashMap<>();

    void addNode(int parent, int child, char direction) {
        Node childNode = dangling.getOrDefault(child, new Node(child));
        dangling.put(child, childNode);

        Node parentNode = dangling.getOrDefault(parent, new Node(parent));
        dangling.put(parent, parentNode);

        if (root == null) root = parentNode;

        if (direction == 'L') parentNode.left = childNode;
        else parentNode.right = childNode;
    }

    int[] getLevelSpiral() {
        List<Integer> result = new ArrayList<>();
        if (root == null) return new int[0];

        Deque<Node> deque = new LinkedList<>();
        deque.add(root);
        boolean leftToRight = false;

        while (!deque.isEmpty()) {
            int size = deque.size();
            List<Integer> level = new ArrayList<>();

            for (int i = 0; i < size; i++) {
                Node curr = leftToRight ? deque.pollFirst() : deque.pollLast();
                level.add(curr.data);

                if (leftToRight) {
                    if (curr.left != null) deque.addLast(curr.left);
                    if (curr.right != null) deque.addLast(curr.right);
                } else {
                    if (curr.right != null) deque.addFirst(curr.right);
                    if (curr.left != null) deque.addFirst(curr.left);
                }
            }

            result.addAll(level);
            leftToRight = !leftToRight;
        }

        return result.stream().mapToInt(Integer::intValue).toArray();
    }
}

public class TestBT {
    public static void main(String[] args) {
        BT tree = new BT();

        // Provided test case 1
        tree.addNode(10, 20, 'R');
        tree.addNode(10, 30, 'L');
        printResult(tree.getLevelSpiral(), new int[]{10, 30, 20}, "Test Case 1");

        tree = new BT();
        // Provided test case 2
        tree.addNode(2, 4, 'L');
        tree.addNode(2, 6, 'R');
        tree.addNode(4, 8, 'L');
        tree.addNode(4, 10, 'R');
        printResult(tree.getLevelSpiral(), new int[]{2, 4, 6, 10, 8}, "Test Case 2");

        tree = new BT();
        // Additional large test case
        for (int i = 1; i <= 1000; i++) {
            tree.addNode(i, i * 2, 'L');
            tree.addNode(i, i * 2 + 1, 'R');
        }
        int[] result = tree.getLevelSpiral();
        System.out.println("Large Test Case Result Length: " + result.length);
        System.out.println(result.length == 2001 ? "Large Test Case: PASS" : "Large Test Case: FAIL");
    }

    static void printResult(int[] actual, int[] expected, String testCaseName) {
        if (Arrays.equals(actual, expected))
            System.out.println(testCaseName + ": PASS");
        else
            System.out.println(testCaseName + ": FAIL (Expected: " + Arrays.toString(expected) + ", Actual: " + Arrays.toString(actual) + ")");
    }
}