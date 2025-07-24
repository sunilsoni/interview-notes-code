package com.interview.notes.code.year.y2025.july.common.test8;

import java.util.*;
import java.util.stream.*;
/*
Here's the combined and structured version of your provided question:

---

## Jewelry Store Problem

You walk by a jewelry store. You know that **N** different kinds of jewels are numbered from **1 to N** in the store. You also know that the store has the same number of different types of jewelry. You only see **R** jewels through the store vitrine (a glass display case).

Determine the **minimum possible number** of jewels in the store.

### Input

* The first line of input contains an integer **N**, representing the number of possible different jewels in the store.
* The second line of input contains an integer **R**, representing the number of jewels you see through the store vitrine.
* The third line of input contains **R integers** K₁, K₂ ... Kᵣ, where Kᵢ represents the type of the ith jewel.

### Output

* Print the **minimum possible number** of jewels in the store.

### Constraints

* $1 \leq R \leq 10^5$
* $1 \leq N \leq 2000$

### Example 1:

**Input**

```
5
2
4 11
```

**Output**

```
2
```

**Explanation**:
There may be, at most, five different types of jewels in the store. You see two jewels (types = {4, 11}) through the store vitrine. The minimum possible number of jewels in the store is two.

---

### Example 2:

**Input**

```
5
3
4 11 4
```

**Output**

```
4
```

**Explanation**:
There may be, at most, five different types of jewels. You see three jewels of two different types (type = {4, 11}) through the store vitrine. The minimum possible number of jewels in the store is four, as there should be at least two jewels of type 11.

---

## Implementation

Implement the following method to solve the problem:

 */
public class JewelryStoreTest {

    /**
     * Your actual solution goes here.
     * It computes D = number of distinct types seen,
     * F = max frequency among those types,
     * and returns D * F.
     */
    public static int solve(int N, List<Integer> K) {
        if (K == null || K.isEmpty()) return 0;
        // count frequencies
        Map<Integer, Long> freq = K.stream()
                                   .collect(Collectors.groupingBy(i -> i,
                                          Collectors.counting()));
        int distinct = freq.size();
        long maxFreq = freq.values().stream()
                           .mapToLong(l -> l)
                           .max()
                           .getAsLong();
        return (int)(distinct * maxFreq);
    }

    public static void main(String[] args) {
        class TC {
            final int N;
            final List<Integer> K;
            final int expected;
            final String name;
            TC(int N, List<Integer> K, int expected, String name) {
                this.N = N; this.K = K;
                this.expected = expected; this.name = name;
            }
        }

        List<TC> fixedTests = Arrays.asList(
            new TC(5, Arrays.asList(4, 11),       2, "Example #1"),
            new TC(5, Arrays.asList(4, 11, 4),    4, "Example #2"),
            new TC(1, Collections.singletonList(42),
                                               1, "Single element"),
            new TC(10, IntStream.rangeClosed(1,10)
                                .boxed()
                                .collect(Collectors.toList()),
                                              10, "All distinct (R=N)"),
            new TC(10, Collections.nCopies(5, 7), 5, "All same (5 copies)")
        );

        for (TC tc : fixedTests) {
            int out = solve(tc.N, tc.K);
            if (out == tc.expected) {
                System.out.printf("%-20s PASS%n", tc.name);
            } else {
                System.out.printf("%-20s FAIL (expected=%d, got=%d)%n",
                                  tc.name, tc.expected, out);
            }
        }

        // Large random test to exercise performance
        int N = 2000;
        int R = 100_000;
        Random rnd = new Random(0);
        List<Integer> largeK = rnd.ints(R, 1, N + 1)
                                  .boxed()
                                  .collect(Collectors.toList());

        long t0 = System.currentTimeMillis();
        int result = solve(N, largeK);
        long t1 = System.currentTimeMillis();
        System.out.printf("Large random test: result=%d, time=%dms%n",
                          result, (t1 - t0));
    }
}