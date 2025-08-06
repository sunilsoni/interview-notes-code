package com.interview.notes.code.year.y2025.august.common.test3;/*
1. Version: Java 8 using Stream API
Here is the complete and cleanly combined version of **Question 2** based on the screenshots:

---

### ✅ Problem: Minimum Operations to Reach a Value

There are **two operations** allowed starting from **0**:

* **ADD\_1**: Increment the number by 1
* **MULTIPLY\_2**: Multiply the number by 2

You are given a list of integers `kValues`.

🔹 For each `kValues[i]`, determine the **minimum number of operations** needed to transform `0` into `kValues[i]` using only the above operations.

Return a list of integers, each representing the result for `kValues[i]`.

---

### 🔍 Function Signature

```java
public static List<Integer> getMinOperations(List<Long> kValues)
```

---

### 📥 Input

* `kValues[n]`: A list of integers representing the target values

---

### 📤 Output

* A list of integers: each value is the minimum number of operations to reach the corresponding `kValues[i]` from 0

---

### 🔄 Example

**Input:**

```text
kValues = [5, 3]
```

**Output:**

```text
[4, 3]
```

**Explanation:**

* For 5:
  `0 → 1 (ADD) → 2 (MUL) → 4 (MUL) → 5 (ADD)` = 4 operations

* For 3:
  `0 → 1 (ADD) → 2 (MUL) → 3 (ADD)` = 3 operations

---

### 🔧 Constraints

* 1 ≤ n ≤ 10⁴
* 0 ≤ kValues\[i] ≤ 10¹⁶

---

### 🧪 Input Format (For Custom Testing)

* First line: an integer `n` — number of elements in the list
* Next `n` lines: each line contains a single long integer, `kValues[i]`

---

### ✅ Sample Case 0

**Input:**

```
2
5
3
```

**Output:**

```
4
3
```

---

Would you like me to implement the `getMinOperations` function using Java?

2. Problem Analysis:
   - We need to transform 0 into each target value k using two operations:
       * ADD_1: increment by 1
       * MULTIPLY_2: multiply by 2
   - Starting from 0, the only way to make progress is to first ADD_1 to reach 1.
   - Thereafter, a sequence of multiplies and adds builds any positive integer.
   - Observing the binary representation of k yields the optimal strategy:
       * For a binary string of length L, we need L-1 MULTIPLY_2 operations to build up place value.
       * For each '1' bit, we need one ADD_1 to introduce that bit.
       * Total ops = (L - 1) + (number of '1' bits).
   - Edge case: k = 0 requires 0 operations.
   - Constraints: n ≤ 10^4, k ≤ 10^16 — an O(n) scan with O(1) work per k is efficient.

3. Solution Design:
   - Compute bitLength = 64 - Long.numberOfLeadingZeros(k)  (0→special)
   - Compute ones = Long.bitCount(k)
   - Answer = (k == 0 ? 0 : (bitLength - 1 + ones))
   - Use Java 8 streams to map List<Long> → List<Integer> succinctly.

4. Implementation:
   - getMinOperations implements this logic in one pass.
   - main method runs PASS/FAIL tests for samples, edge cases, and a large random test.

5. Testing:
   - Sample Case 0: [5, 3] → [4, 3]
   - Edge cases: 0, 1, powers of two, random values.
   - Large test: generate 10,000 random k-values to ensure performance.
*/

import java.util.*;
import java.util.stream.*;

public class MinOperations {

    public static List<Integer> getMinOperations(List<Long> kValues) {
        return kValues.stream()
            .map(k -> {
                if (k == 0L) {
                    return 0;
                }
                int bitLength = Long.SIZE - Long.numberOfLeadingZeros(k);
                int ones = Long.bitCount(k);
                return (bitLength - 1) + ones;
            })
            .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        // Sample Case 0
        List<Long> sample0 = Arrays.asList(5L, 3L);
        List<Integer> expect0 = Arrays.asList(4, 3);
        runTest(sample0, expect0, "Sample Case 0");

        // Edge Case: zero
        List<Long> sample1 = Arrays.asList(0L, 1L, 2L, 16L);
        List<Integer> expect1 = Arrays.asList(0, 1, 2, 5); // 16: bits=5, ones=1 → (5-1)+1=5
        runTest(sample1, expect1, "Edge Case Zero & Powers of Two");

        // Random small values
        List<Long> small = IntStream.rangeClosed(1, 20)
            .mapToObj(i -> (long) i)
            .collect(Collectors.toList());
        List<Integer> calcSmall = getMinOperations(small);
        boolean allMatch = IntStream.range(0, small.size())
            .allMatch(i -> calcSmall.get(i).equals(manualCalc(small.get(i))));
        System.out.printf("Random 1–20 Test: %s%n", allMatch ? "PASS" : "FAIL");

        // Large random test
        Random rnd = new Random(0);
        int nLarge = 10_000;
        List<Long> large = Stream.generate(() -> Math.abs(rnd.nextLong()) % 1_000_000_000_000_000L)
            .limit(nLarge)
            .collect(Collectors.toList());
        long start = System.currentTimeMillis();
        List<Integer> outLarge = getMinOperations(large);
        long duration = System.currentTimeMillis() - start;
        System.out.printf("Large Random Test: Completed %d ops in %d ms%n", nLarge, duration);
    }

    private static void runTest(List<Long> input, List<Integer> expected, String name) {
        List<Integer> actual = getMinOperations(input);
        boolean ok = actual.equals(expected);
        System.out.printf("%s: %s\n", name, ok ? "PASS" : "FAIL");
        if (!ok) {
            System.out.println("  Expected: " + expected);
            System.out.println("  Actual:   " + actual);
        }
    }

    // Fallback for manual verification
    private static int manualCalc(long k) {
        if (k == 0) return 0;
        int bits = Long.SIZE - Long.numberOfLeadingZeros(k);
        int ones = Long.bitCount(k);
        return (bits - 1) + ones;
    }
}
