package com.interview.notes.code.year.y2025.June.common.test8;

import java.util.*;
import java.util.stream.*;
/*

---

### **Problem: Zigzag Traversal of a Binary Tree**

You are given a binary tree in the form of edge definitions. Your task is to **traverse the tree in a zigzag (spiral) level order**:

* Nodes at **odd levels** (1, 3, ...) should be traversed **right to left**.
* Nodes at **even levels** (2, 4, ...) should be traversed **left to right**.

---

### **Input Format**

* The first line contains an integer `N`, the number of edges in the tree.
* The second line contains `N` space-separated triplets in the form:

  ```
  node1 node2 L/R
  ```

  where `L` indicates `node2` is the **left child** of `node1`, and `R` indicates the **right child**.

---

### **Output Format**

* Print the node values of the tree in **zigzag level order** traversal.

---

### **Constraints**

* Time complexity of the solution should be **O(n)**.
* You may assume all node values are unique integers.

---

### **Example 1**

**Input**

```
2
10 20 R 10 30 L
```

**Output**

```
10 30 20
```

**Explanation**
Tree:

```
    10
   /  \
  30  20
```

Zigzag traversal:
Level 1 (right to left): 10
Level 2 (left to right): 30 20

---

### **Example 2**

**Input**

```
4
2 4 L 2 6 R 4 8 L 4 10 R
```

**Output**

```
2 4 6 10 8
```

**Explanation**
Tree:

```
        2
       / \
      4   6
     / \
    8  10
```

Zigzag traversal:
Level 1 (right to left): 2
Level 2 (left to right): 4 6
Level 3 (right to left): 10 8

---

Would you like the complete Java code that:

1. Builds the tree from input
2. Performs the zigzag traversal
3. Returns the result in the correct order?

 */
public class ZigzagTraversal {
    // ---- given classes ----
    static class Node {
        int data;
        Node left, right;
        Node(int value) { data = value; }
    }

    static class BT {
        Node root;
        Map<Integer, Node> map = new HashMap<>();

        // call this for each (parent, child, 'L' or 'R')
        void addNode(int parent, int child, char dir) {
            Node p = map.computeIfAbsent(parent, k -> new Node(k));
            Node c = map.computeIfAbsent(child,  k -> new Node(k));
            if (root == null) root = p;
            if (dir == 'L') p.left  = c;
            else           p.right = c;
        }

        // returns the zigzag (spiral) order as an int[]
        public int[] getLevelSpiral() {
            if (root == null) return new int[0];

            List<Integer> result = new ArrayList<>();
            Deque<Node> queue = new ArrayDeque<>();
            queue.add(root);
            boolean rtl = true;  // level 1: right->left

            while (!queue.isEmpty()) {
                int size = queue.size();
                List<Integer> level = new ArrayList<>(size);

                for (int i = 0; i < size; i++) {
                    Node n = queue.poll();
                    level.add(n.data);
                    if (n.left  != null) queue.add(n.left);
                    if (n.right != null) queue.add(n.right);
                }

                if (rtl) Collections.reverse(level);
                result.addAll(level);
                rtl = !rtl;
            }

            // Java 8 Stream to convert List<Integer> → int[]
            return result.stream().mapToInt(i -> i).toArray();
        }
    }

    // ---- simple test harness ----
    public static void main(String[] args) {
        // small holder types for tests
        class Edge  { int p, c; char d; Edge(int p,int c,char d){this.p=p;this.c=c;this.d=d;} }
        class Test  { List<Edge> edges; int[] expect;
                      Test(List<Edge> e, int[] x){ edges = e; expect = x; } }

        List<Test> tests = Arrays.asList(
            // Example #1
            new Test(
                Arrays.asList(new Edge(10,20,'R'), new Edge(10,30,'L')),
                new int[]{10, 30, 20}
            ),
            // Example #2
            new Test(
                Arrays.asList(
                  new Edge(2,4,'L'), new Edge(2,6,'R'),
                  new Edge(4,8,'L'), new Edge(4,10,'R')
                ),
                new int[]{2, 4, 6, 10, 8}
            ),
            // empty‐tree
            new Test(Collections.emptyList(), new int[]{})
        );

        boolean allPass = true;
        for (Test t : tests) {
            BT tree = new BT();
            t.edges.forEach(e -> tree.addNode(e.p, e.c, e.d));
            int[] out = tree.getLevelSpiral();

            if (Arrays.equals(out, t.expect)) {
                System.out.println("PASS → " + Arrays.toString(out));
            } else {
                System.out.printf("FAIL → got %s; expected %s%n",
                                  Arrays.toString(out),
                                  Arrays.toString(t.expect));
                allPass = false;
            }
        }
        System.out.println(allPass
                           ? "All tests passed."
                           : "Some tests failed.");

        // optional performance sanity‐check:
        // build a complete tree of depth ~10 (≈1023 nodes)
        BT big   = new BT();
        for (int i = 1; i <= 512; i++) {
            big.addNode(i, 2*i,   'L');
            big.addNode(i, 2*i+1, 'R');
        }
        long t0 = System.currentTimeMillis();
        big.getLevelSpiral();
        long t1 = System.currentTimeMillis();
        System.out.printf("Complete‐tree (1023 nodes) in %d ms%n", (t1 - t0));
    }
}