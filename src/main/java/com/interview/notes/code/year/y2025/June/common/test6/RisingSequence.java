package com.interview.notes.code.year.y2025.June.common.test6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
/*
### **The Only Way is Up**

You are given a sequence of integers `ar[]` and a positive integer `B`.

A sequence is called a **rising sequence** if for every `i > 0`, `ar[i] > ar[i - 1]`.

Your task is to determine the **total number of times you need to add `B`** to any of the elements in the array to convert the entire array into a **rising sequence**.

You may add the integer `B` to any element **any number of times**.

---

### **Input Format**

1. First line: An integer `B` (the value to be added).
2. Second line: An integer `N` (size of the array).
3. Third line: `N` space-separated integers — the elements of the array.

---

### **Output Format**

* A single integer representing the **total number of times `B` needs to be added** to make the array strictly increasing.

---

### **Constraints**

* `2 ≤ N ≤ 2000`
* `1 ≤ B ≤ 10^6`
* `1 ≤ ar[i] ≤ 10^6`

---

### **Function Signature**

```java
public static int solve(int B, List<Integer> ar)
```

---

### **Example 1**

**Input:**

```
2
4
1 3 3 2
```

**Output:**

```
3
```

---

### **Example 2**

**Input:**

```
1
2
1 1
```

**Output:**

```
1
```
 */

/**
 * The Only Way is Up – minimal, self-contained solution.
 * Compiles under Java 8 -source 1.8.
 */
public class RisingSequence {

    /**
     * ---------- Required method ----------
     */
    public static int solve(int B, List<Integer> ar) {
        long ops = 0;                  // long to stay perfectly safe
        int prev = ar.get(0);

        for (int i = 1; i < ar.size(); i++) {
            int cur = ar.get(i);
            int target = prev + 1;

            if (cur <= prev) {
                long diff = (long) target - cur;           // positive gap to close
                long add = (diff + B - 1) / B;             // ceiling division
                ops += add;
                cur += add * B;                            // raise element
            }
            prev = cur;                                    // next comparison base
        }
        return (int) ops;                                  // fits comfortably
    }

    public static void main(String[] args) {
        List<Case> tests = new ArrayList<>();

        // Example 1
        tests.add(new Case(
                2,
                Arrays.asList(1, 3, 3, 2),
                3));

        // Example 2
        tests.add(new Case(
                1,
                Arrays.asList(1, 1),
                1));

        // Extra sanity: already rising
        tests.add(new Case(
                5,
                Arrays.asList(1, 6, 11, 20),
                0));

        // Large edge: 2 000 equal elements, B = 1
        final int N = 2000;
        tests.add(new Case(
                1,
                IntStream.range(0, N).map(i -> 1).boxed().collect(Collectors.toList()),
                (N - 1) * N / 2                    // 0+1+2+…+(N−1)
        ));

        // Run the suite
        int pass = 0;
        for (int i = 0; i < tests.size(); i++) {
            Case c = tests.get(i);
            int got = solve(c.B, new ArrayList<>(c.ar));   // copy; solve mutates
            boolean ok = got == c.expected;
            System.out.printf("Test #%d: %s (expected %d, got %d)%n",
                    i + 1, ok ? "PASS" : "FAIL", c.expected, got);
            if (ok) pass++;
        }
        System.out.printf("%nSummary: %d / %d tests passed%n", pass, tests.size());
    }

    /**
     * ---------- Simple test harness ----------
     */
    private static class Case {
        final int B;
        final List<Integer> ar;
        final int expected;

        Case(int B, List<Integer> ar, int expected) {
            this.B = B;
            this.ar = ar;
            this.expected = expected;
        }
    }
}
