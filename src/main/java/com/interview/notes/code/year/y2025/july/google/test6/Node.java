package com.interview.notes.code.year.y2025.july.google.test6;

import java.util.ArrayList;
import java.util.List;

/*

### 🧩 Problem Statement: Flatten Nested Function Inlined Ranges

Languages that support **function inlining** allow one function to be expanded inline into another. This leads to **nested function ranges**, which can be represented as address ranges. You are given a list of such inlined functions with their **address ranges**.

Your task is to **flatten** the function range representation such that:

1. The **innermost functions take precedence**.
2. If multiple functions cover the same range, the **more deeply nested function** overrides the outer one.
3. Functions with the same name can appear multiple times. Their nesting context and order must be respected.
4. The final output should **print the flattened view** of all function address ranges.

---

### ✅ Input (Example):

Given function inlining representation:

```
foo(): 1   - 25
bar(): 25  - 35
baz(): 35  - 45
bar(): 45  - 75
foo(): 75  - 100
```

Each line indicates a function with its **start and end address**.

---

### ✅ Expected Output:

Flattened address ranges based on nesting (deepest function overrides outer):

```
foo(): 1 - 25
bar(): 25 - 35
baz(): 35 - 45
bar(): 45 - 75
foo(): 75 - 100
```

Note: In this particular example, no function was overridden because the nesting was cleanly sequential. But in more complex examples, deeper nested functions would override outer functions for overlapping ranges.

---

Perfect. The input structure is defined using a `Node` class:

```java
class Node {
    String name;
    Long start;
    Long end;
    List<Node> children;
}
```

This implies the input forms a **tree** where each `Node` may have multiple nested child nodes, and your goal is to flatten this tree into a **sorted list of ranges**, giving **priority to deeper (more nested) children**.

---

### ✅ Final Goal

Write a function that takes the root `Node` and prints the **flattened** function ranges like:

```
foo(): 1 - 25
bar(): 25 - 35
baz(): 35 - 45
bar(): 45 - 75
foo(): 75 - 100
```

---

### ✅ Java Approach (Java 8+, Stream Optional)

We’ll:

1. Traverse the tree in **preorder** (deepest first).
2. Build a flat list of `(start, end, name)` ranges.
3. Sort by `start`, and print.

---


 */
class Node {
    String name;
    Long start;
    Long end;
    List<Node> children = new ArrayList<>();

    Node(String name, Long start, Long end) {
        this.name = name;
        this.start = start;
        this.end = end;
    }
}

class FunctionRangeFlattener {

    public static List<Range> flattenRanges(Node root) {
        List<Range> result = new ArrayList<>();

        // If no children, return the full range
        if (root.children.isEmpty()) {
            result.add(new Range(root.name, root.start, root.end));
            return result;
        }

        // Sort children by start position
        root.children.sort((a, b) -> a.start.compareTo(b.start));

        Long currentPos = root.start;

        // Process each child
        for (Node child : root.children) {
            // Add parent's range before child
            if (currentPos < child.start) {
                result.add(new Range(root.name, currentPos, child.start));
            }

            // Add child's range
            result.add(new Range(child.name, child.start, child.end));
            currentPos = child.end;
        }

        // Add final parent range if needed
        if (currentPos < root.end) {
            result.add(new Range(root.name, currentPos, root.end));
        }

        return result;
    }

    public static void main(String[] args) {
        // Test Case 1: Simple nested functions
        Node foo = new Node("foo", 1L, 100L);
        Node bar = new Node("bar", 25L, 45L);
        Node car = new Node("car", 65L, 85L);
        foo.children.add(bar);
        foo.children.add(car);

        System.out.println("Test Case 1:");
        System.out.println("Input structure:");
        System.out.println("foo(): 1-100");
        System.out.println("  bar(): 25-45");
        System.out.println("  car(): 65-85");

        List<Range> result = flattenRanges(foo);

        System.out.println("\nFlattened output:");
        for (Range r : result) {
            System.out.println(r);
        }

        // Test Case 2: Single function without children
        System.out.println("\nTest Case 2:");
        Node single = new Node("single", 1L, 50L);
        System.out.println("Input structure:");
        System.out.println("single(): 1-50");

        result = flattenRanges(single);

        System.out.println("\nFlattened output:");
        for (Range r : result) {
            System.out.println(r);
        }

        // Test Case 3: Function with one child
        System.out.println("\nTest Case 3:");
        Node parent = new Node("parent", 1L, 100L);
        Node child = new Node("child", 30L, 60L);
        parent.children.add(child);

        System.out.println("Input structure:");
        System.out.println("parent(): 1-100");
        System.out.println("  child(): 30-60");

        result = flattenRanges(parent);

        System.out.println("\nFlattened output:");
        for (Range r : result) {
            System.out.println(r);
        }
    }

    static class Range {
        String name;
        Long start;
        Long end;

        Range(String name, Long start, Long end) {
            this.name = name;
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() {
            return String.format("%s(): %d-%d", name, start, end);
        }
    }
}
