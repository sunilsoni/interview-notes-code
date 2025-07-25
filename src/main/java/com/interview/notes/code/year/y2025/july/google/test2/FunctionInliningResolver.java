package com.interview.notes.code.year.y2025.july.google.test2;

import java.util.*;

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






 */
public class FunctionInliningResolver {

    // Given a list of FunctionRange and an address, returns the innermost function name
    static String resolveAddress(List<FunctionRange> funcs, int address) {
        // Stream over all functions
        return funcs.stream()
                // Keep only those whose range covers the address
                .filter(f -> f.start <= address && address <= f.end)
                // Of those, pick the one with the smallest span (innermost)
                .min(Comparator.comparingInt(f -> (f.end - f.start)))
                // If found, return its name; otherwise "NOT_FOUND"
                .map(f -> f.name)
                .orElse("NOT_FOUND");
    }

    // Main method for testing PASS/FAIL
    public static void main(String[] args) {
        // --- Define a small test suite ---
        List<FunctionRange> funcs = Arrays.asList(
                new FunctionRange("foo()", 1, 100),  // outermost
                new FunctionRange("bar()", 25, 75),   // inlined into foo
                new FunctionRange("baz()", 35, 45)    // inlined into bar
        );

        // Map of query address → expected function
        Map<Integer, String> tests = new LinkedHashMap<>();
        tests.put(10, "foo()");    // only foo covers 10
        tests.put(30, "bar()");    // foo+bar cover 30, bar is smaller
        tests.put(40, "baz()");    // foo+bar+baz cover 40, baz is smallest
        tests.put(80, "foo()");    // only foo covers 80
        tests.put(200, "NOT_FOUND");// no function covers 200

        System.out.println("=== Small Test Suite ===");
        tests.forEach((addr, expected) -> {
            String actual = resolveAddress(funcs, addr);
            // Print PASS or FAIL
            System.out.printf("Address %3d: expected=%-11s actual=%-11s %s%n",
                    addr, expected, actual,
                    expected.equals(actual) ? "PASS" : "FAIL");
        });

        // --- Large data test to demonstrate handling big inputs ---
        // Build a deeply nested list of 10,000 functions
        List<FunctionRange> largeFuncs = new ArrayList<>();
        int MAX = 10_000;
        for (int i = 1; i <= MAX; i++) {
            // Each one nests inside the previous: [i, MAX*10]
            largeFuncs.add(new FunctionRange("f" + i, i, MAX * 10));
        }
        // Query address in the very deep nesting
        int query = MAX / 2 * 10;  // somewhere in the middle
        String insideMost = resolveAddress(largeFuncs, query);
        // We expect the last one f10000 (smallest span among all that include it)
        System.out.println("\n=== Large Data Test ===");
        System.out.printf("Address %d → %s (%s)%n",
                query, insideMost,
                insideMost.equals("f" + MAX) ? "PASS" : "FAIL");
    }

    // Simple class to hold a function's name and its address range
    static class FunctionRange {
        String name;   // function name
        int start;     // inclusive start address
        int end;       // inclusive end address

        FunctionRange(String name, int start, int end) {
            this.name = name;
            this.start = start;
            this.end = end;
        }
    }
}