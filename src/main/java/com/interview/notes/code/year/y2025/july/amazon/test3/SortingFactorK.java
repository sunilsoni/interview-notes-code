package com.interview.notes.code.year.y2025.july.amazon.test3;

import java.util.*;
import java.util.stream.IntStream;

/*
Here is the **combined and structured version of the question** based on your screenshots:

---

## 🔧 Problem: Bitwise AND-Based Sorting

**Context:**

Developers at Amazon are working on a new algorithm to sort a permutation of `n` integers using the **bitwise AND** operation. For an array of integers `arr` and a chosen non-negative integer `k`, you can **swap** the `i`-th and `j`-th elements **only if**:

```
arr[i] & arr[j] == k
```

Here, `&` denotes the bitwise AND operator.

The array is a **permutation of integers from `0` to `n-1`**, meaning it contains each integer in that range exactly once.

---

## ✅ Objective:

**Determine the maximum possible value of `k`** for which the array can be sorted using the above swap rule.

If the array is already sorted, return `0`.

---

## 🔁 Function Signature:

```java
public static int getSortingFactorK(List<Integer> arr)
```

### Parameters:

* `arr`: A list of integers, a permutation of numbers from `0` to `n-1`.

### Returns:

* An integer representing the **maximum possible non-negative integer `k`** for which the array can be sorted using the described swap rule.

---

## 🧩 Constraints:

* 1 ≤ `n` ≤ 10⁵
* 0 ≤ `arr[i]` ≤ `n - 1`
* The array contains all numbers from `0` to `n - 1` exactly once.

---

## 🧪 Sample Cases:

### 🔹 Sample Case 0:

**Input:**

```
n = 4
arr = [3, 0, 2, 1]
```

**Output:**

```
0
```

**Explanation:**
The optimal way to sort involves:

* Swap 0 and 1 → \[3, 1, 2, 0]
* Swap 0 and 3 → \[0, 1, 2, 3]

This works even when `k = 0`, so return 0.

---

### 🔹 Sample Case 1:

**Input:**

```
n = 5
arr = [0, 1, 3, 2, 4]
```

**Output:**

```
2
```

**Explanation:**
Swap elements 3 and 2 as `3 & 2 = 2`. This gives \[0, 1, 2, 3, 4].

---


 */
public class SortingFactorK {

    /**
     * Returns the maximum k such that you can sort the given permutation
     * by swapping arr[i] and arr[j] only when (arr[i] & arr[j]) == k.
     *
     * By inspection & by exhaustive small-n testing, the answer is simply
     * the bitwise-AND of all values that are not already in place
     * (i.e. all v with arr[i] != i).  If the array is already sorted,
     * we return 0 by problem statement.
     */
    public static int getSortingFactorK(List<Integer> arr) {
        // Quick check: if already sorted, return 0
        boolean sorted = IntStream.range(0, arr.size())
                                  .allMatch(i -> arr.get(i) == i);
        if (sorted) return 0;
        
        // Otherwise AND together all values that need moving
        return IntStream.range(0, arr.size())
                        .filter(i -> arr.get(i) != i)
                        .map(arr::get)
                        .reduce((a, b) -> a & b)
                        .orElse(0);
    }

    public static void main(String[] args) {
        // name, input array, expected k
        class Test {
            final String name;
            final List<Integer> arr;
            final int expected;
            Test(String name, List<Integer> arr, int expected) {
                this.name = name;
                this.arr = arr;
                this.expected = expected;
            }
        }

        List<Test> tests = Arrays.asList(
            new Test("Sample 0 (custom)",    Arrays.asList(3, 0, 2, 1), 0),
            new Test("Example in desc [0,3,2,1]", Arrays.asList(0, 3, 2, 1), 1),
            new Test("Sample 1",             Arrays.asList(0, 1, 3, 2, 4), 2),
            new Test("Already sorted",       Arrays.asList(0, 1, 2, 3, 4), 0),
            new Test("Tiny swap [1,0]",      Arrays.asList(1, 0),       0),
            new Test("3-cycle [1,2,0,3]",    Arrays.asList(1, 2, 0, 3), 0),
            new Test("Another [2,0,1]",      Arrays.asList(2, 0, 1),    0)
        );

        System.out.println("=== getSortingFactorK Tests ===");
        for (Test t : tests) {
            int got = getSortingFactorK(t.arr);
            String pass = got == t.expected ? "PASS" : "FAIL";
            System.out.printf(
                "%-24s arr=%s → got=%d, exp=%d [%s]%n",
                t.name, t.arr, got, t.expected, pass
            );
        }
    }
}