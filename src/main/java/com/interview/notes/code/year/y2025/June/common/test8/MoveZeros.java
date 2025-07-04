package com.interview.notes.code.year.y2025.June.common.test8;

import java.util.*;
import java.util.stream.Collectors;

/*

---

### **Problem: Moving Zeros to End**

Given an integer array `nums[]`, move all `0`s to the end of the array **in-place** while maintaining the relative order of the non-zero elements.

> **Note:**
>
> * Do **not** make a copy of the array.
> * Modify the original array directly.

---

### **Function Signature**

```java
public static List<Integer> solve(List<Integer> nums)
```

**Parameters:**

* `nums` (List<Integer>): The list of integers representing the array.

**Returns:**

* `List<Integer>`: The updated list after moving all zeros to the end.

---

### **Constraints**

* $1 \leq N \leq 10^4$
* $-231 \leq nums[i] \leq 231 - 1$

---

### **Examples**

#### **Example 1**

**Input:**
`nums = [0, 1, 0, 3, 12]`

**Output:**
`[1, 3, 12, 0, 0]`

---

#### **Example 2**

**Input:**
`nums = [0]`

**Output:**
`[0]`

---


 */
public class MoveZeros {

    /**
     * Moves all 0's in the given list to the end, preserving the
     * order of non-zero elements. Modifies the list in-place and
     * also returns it.
     */
    public static List<Integer> solve(List<Integer> nums) {
        // 1) count zeros via a stream
        long zeroCount = nums.stream()
                .filter(i -> i == 0)
                .count();

        // 2) remove all zeros in-place
        nums.removeIf(i -> i == 0);

        // 3) append that many zeros at the end
        for (int k = 0; k < zeroCount; k++) {
            nums.add(0);
        }
        return nums;
    }

    public static void main(String[] args) {
        //–– small hard-coded test cases
        List<TestCase> tests = Arrays.asList(
                new TestCase(Arrays.asList(0, 1, 0, 3, 12),
                        Arrays.asList(1, 3, 12, 0, 0)),
                new TestCase(Arrays.asList(0),
                        Collections.singletonList(0)),
                new TestCase(Arrays.asList(1, 2, 3),
                        Arrays.asList(1, 2, 3)),
                new TestCase(Collections.emptyList(),
                        Collections.emptyList())
        );

        boolean allPass = true;
        for (TestCase tc : tests) {
            // clone original so we can re-run if needed
            List<Integer> inp = new ArrayList<>(tc.input);
            List<Integer> out = solve(inp);
            if (out.equals(tc.expected)) {
                System.out.printf("PASS: %s → %s%n", tc.input, out);
            } else {
                System.out.printf("FAIL: %s → got %s, expected %s%n",
                        tc.input, out, tc.expected);
                allPass = false;
            }
        }
        System.out.println(allPass
                ? "All small tests passed."
                : "Some small tests failed.");

        //–– large-data sanity check
        final int LARGE_N = 100_000;
        List<Integer> large = new Random(0)
                .ints(LARGE_N, 0, 10)          // 10% zeros on average
                .map(i -> i == 0 ? 0 : i)      // make exactly zero or non-zero
                .boxed()
                .collect(Collectors.toList());

        long t0 = System.currentTimeMillis();
        solve(large);
        long t1 = System.currentTimeMillis();
        System.out.printf("Large test N=%d done in %d ms%n",
                LARGE_N, (t1 - t0));
    }

    // simple holder for our tests
    private static class TestCase {
        final List<Integer> input, expected;

        TestCase(List<Integer> in, List<Integer> exp) {
            this.input = in;
            this.expected = exp;
        }
    }
}