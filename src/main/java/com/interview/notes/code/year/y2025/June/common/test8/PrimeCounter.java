package com.interview.notes.code.year.y2025.June.common.test8;

import java.util.*;
import java.util.stream.IntStream;
/*

---

### **Problem: Prime Numbers Less Than N**

You are given a non-negative integer `N`. Your task is to write a program that returns the **number of prime numbers less than `N`**.

---

### **Function Signature**

```java
public static int solve(int N)
```

**Parameters:**

* `N` (int): A non-negative integer.

**Returns:**

* `int`: The number of prime numbers strictly less than `N`.

---

### **Constraints**

* $0 \leq N \leq 5 \times 10^6$

---

### **Examples**

#### **Example 1**

**Input:**
`N = 10`

**Output:**
`4`

**Explanation:**
There are 4 prime numbers less than 10: `2, 3, 5, 7`.

---

#### **Example 2**

**Input:**
`N = 0`

**Output:**
`0`

**Explanation:**
There are no prime numbers less than 0.

---


 */
public class PrimeCounter {

    // Count primes strictly less than N
    public static int solve(int N) {
        if (N <= 2) return 0;

        // standard sieve up to N-1
        boolean[] isPrime = new boolean[N];
        Arrays.fill(isPrime, true);
        isPrime[0] = false;
        isPrime[1] = false;

        for (int i = 2; i * i < N; i++) {
            if (isPrime[i]) {
                int step = i;
                for (int j = i * i; j < N; j += step) {
                    isPrime[j] = false;
                }
            }
        }

        // Java 8 Stream to count
        return (int) IntStream.range(2, N)
                              .filter(i -> isPrime[i])
                              .count();
    }

    // simple main to run pass/fail tests + one large case
    public static void main(String[] args) {
        Map<Integer, Integer> tests = new LinkedHashMap<>();
        tests.put(0,    0);
        tests.put(1,    0);
        tests.put(2,    0);
        tests.put(3,    1);
        tests.put(10,   4);
        tests.put(20,   8);   // {2,3,5,7,11,13,17,19}
        tests.put(100, 25);   // known π(100)=25

        boolean allPass = true;
        for (Map.Entry<Integer,Integer> e : tests.entrySet()) {
            int n = e.getKey(), exp = e.getValue();
            int got = solve(n);
            if (got == exp) {
                System.out.printf("PASS: N=%d → %d%n", n, got);
            } else {
                System.out.printf("FAIL: N=%d → expected %d but got %d%n", n, exp, got);
                allPass = false;
            }
        }
        System.out.println(allPass ? "All small tests passed." : "Some small tests failed.");

        // large-input sanity check
        int largeN = 5_000_000;
        long t0 = System.currentTimeMillis();
        int cnt = solve(largeN);
        long t1 = System.currentTimeMillis();
        System.out.printf("For N=%d → %d primes (computed in %d ms)%n",
                          largeN, cnt, (t1 - t0));
    }
}