package com.interview.notes.code.year.y2025.June.common.test7;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*


Here is the **combined and properly structured version** of the "Little Brother's Factorial Challenge" question from the images:

---

### **Little Brother's Factorial Challenge**

Your 10-year-old brother just learned about factorials and made a list of factorials from `m` to `n`. Now he challenges you:

Write a program to **find all the numbers** (if any) between `m` and `n` such that **the factorial of the number starts with an even digit**.

---

### **Input**

* First line: integer `m`
* Second line: integer `n`

---

### **Output**

* A single line of integers:

  * The first integer `x` — the number of integers whose factorial starts with an even digit
  * Followed by `x` space-separated integers in **increasing order**

---

### **Constraints**

* `1 ≤ m ≤ 100`
* `1 ≤ n ≤ 100`

---

### **Function Signature**

```java
public static List<Integer> solve(int m, int n)
```

---

### **Example 1**

**Input:**

```
1
10
```

**Output:**

```
4 2 3 4 8
```

---

### **Example 2**

**Input:**

```
5
7
```

**Output:**

```
0
```

 */
public class FactorialChallenge {
    // returns all i in [m,n] whose factorial starts with 2,4,6, or 8
    public static List<Integer> solve(int m, int n) {
        List<Integer> ans = new ArrayList<>();
        BigInteger fact = BigInteger.ONE;
        for (int i = 1; i <= n; i++) {
            fact = fact.multiply(BigInteger.valueOf(i));
            if (i >= m) {
                char first = fact.toString().charAt(0);
                if (first == '2' || first == '4' || first == '6' || first == '8') {
                    ans.add(i);
                }
            }
        }
        return ans;
    }

    /**
     * Returns all integers i in [m, n] whose factorial starts with an even digit.
     */
    public static List<Integer> solve1(int m, int n) {
        if (m > n) {
            return Collections.emptyList();
        }
        return IntStream.rangeClosed(m, n)
                .filter(i -> {
                    // compute i! via a stream of BigInteger
                    BigInteger fact = IntStream.rangeClosed(1, i)
                            .mapToObj(BigInteger::valueOf)
                            .reduce(BigInteger.ONE, BigInteger::multiply);
                    // check first digit
                    char first = fact.toString().charAt(0);
                    return first == '2' || first == '4' || first == '6' || first == '8';
                })
                .boxed()
                .collect(Collectors.toList());
    }

    /**
     * Simple main method to test solve(...) on sample and edge cases.
     * Prints PASS/FAIL for each predefined test, then demonstrates handling
     * of the full range [1,100].
     */
    public static void main(String[] args) {
        class TestCase {
            final int m, n;
            final List<Integer> expected;

            TestCase(int m, int n, List<Integer> e) {
                this.m = m;
                this.n = n;
                this.expected = e;
            }
        }

        List<TestCase> tests = Arrays.asList(
                new TestCase(1, 10, Arrays.asList(2, 3, 4, 8)),
                new TestCase(5, 7, Collections.emptyList()),
                new TestCase(10, 10, Arrays.asList()),          // 10! = 3628800 → starts with '3'
                new TestCase(8, 9, Arrays.asList(8))           // 8! starts '4', 9! starts '3'
        );

        for (TestCase tc : tests) {
            List<Integer> result = solve(tc.m, tc.n);
            boolean pass = result.equals(tc.expected);
            System.out.printf("Test [%d, %d]: expected=%s, got=%s → %s%n",
                    tc.m, tc.n, tc.expected, result, pass ? "PASS" : "FAIL");
        }

        // Large‐input demonstration (max bounds)
        System.out.println("\nFull range [1,100]:");
        List<Integer> full = solve(1, 100);
        System.out.printf("Count = %d, Values = %s%n",
                full.size(),
                full.stream()
                        .map(String::valueOf)
                        .collect(Collectors.joining(" ")));
    }
}