package com.interview.notes.code.year.y2025.july.codesignal.test2;

import java.util.Random;

/*
Here’s the **fully combined and clean version** of your problem description and sample method signature from all screenshots:

---

### 🔁 Resource Exchange Cycles

You are given an array of resources, each being an `"A"` or `"P"`. All `"A"`s are **at the beginning** and `"P"`s are **at the end**.

A process runs on this array in cycles until it halts.

---

### 🧾 Input

* `resources[]` – An array of strings with values `"A"` or `"P"`, where all `"A"`s are at the beginning and `"P"`s are at the end.

  * `2 ≤ resources.length ≤ 500`
* `conversionRate` – An integer representing the number of `"P"`s that can be deleted and converted into one `"A"`.

  * `2 ≤ conversionRate ≤ 500`

---

### 🔁 Process Rules (per cycle)

Each cycle, **exactly one** of the following operations occurs (in order):

1. **If there are at least `conversionRate` `"P"`s at the end**:

   * Remove the last `conversionRate` `"P"`s and **add one `"A"`** at the beginning.

2. **Else, if there is at least one `"A"` at the beginning**:

   * Convert the **last** `"A"` to `"P"`.

3. **Else, stop the process.**

---

### ✅ Function Signature

```java
int solution(String[] resources, int conversionRate)
```

---

### 🎯 Output

* Return the **number of cycles** until the process halts.

---

### 🧪 Examples

#### Example 1:

```java
resources = ["A", "A", "A", "P", "P", "P"]
conversionRate = 2
Output: 13
```

Explanation:

```
Cycle 1: ["A", "A", "A", "A", "P", "P"]
Cycle 2: ["A", "A", "A", "A", "A", "P"]
Cycle 3: ["A", "A", "A", "A", "A", "A"]
Cycle 4: ["A", "A", "A", "A", "P"]
...
Cycle 13: ["P"]
```

#### Example 2:

```java
resources = ["A", "A"]
conversionRate = 2
Output: 4
```

Explanation:

```
Cycle 1: ["A", "P"]
Cycle 2: ["P", "P"]
Cycle 3: ["A"]
Cycle 4: ["P"]
```

#### Example 3:

```java
resources = ["P", "P", "P"]
conversionRate = 3
Output: 2
```

Explanation:

```
Cycle 1: ["A"]
Cycle 2: ["P"]
```

---


 */
public class ResourceConverter {

    /**
     * Simulates the cycles until no more events can happen.
     *
     * @param resources      array of "A" and "P" (all "A"s in front, "P"s in back)
     * @param conversionRate number of "P"s that can be removed to produce one "A"
     * @return number of cycles until the process halts
     */
    public static int solution(String[] resources, int conversionRate) {
        // Count initial A's and P's
        int countA = 0, countP = 0;
        for (String s : resources) {
            if ("A".equals(s)) countA++;
            else countP++;
        }

        int cycles = 0;
        // Each cycle we try Option 1, then Option 2, else break
        while (true) {
            if (countP >= conversionRate) {
                // Option 1: remove conversionRate P's, add one A
                countP -= conversionRate;
                countA += 1;
                cycles++;
            } else if (countA >= 1) {
                // Option 2: turn one A into P
                countA--;
                countP++;
                cycles++;
            } else {
                // Option 3: halt
                break;
            }
        }
        return cycles;
    }

    /**
     * Helper to run & report a single test case.
     */
    private static void testCase(String[] resources, int rate, int expected, int id) {
        int got = solution(resources, rate);
        String passFail = (got == expected) ? "PASS" : "FAIL";
        System.out.printf(
                "Test %2d [%s, rate=%d]: %s (expected %d, got %d)%n",
                id, String.join("", resources), rate, passFail, expected, got
        );
    }

    public static void main(String[] args) {
        // —— Provided examples —— 
        testCase(new String[]{"A", "A", "A", "P", "P", "P"}, 2, 13, 1);
        testCase(new String[]{"A", "A"}, 2, 4, 2);
        testCase(new String[]{"P", "P", "P"}, 3, 2, 3);

        // —— Edge / small cases —— 
        testCase(new String[]{"A", "P"}, 2, 3, 4);
        testCase(new String[]{"P", "P"}, 3, 0, 5);
        testCase(new String[]{"A", "A", "P", "P"}, 5, 4, 6);

        // —— Randomized stress test —— 
        int N = 500;
        String[] large = new String[N];
        Random rnd = new Random(123);
        for (int i = 0; i < N; i++) {
            large[i] = (i < N / 2 ? "A" : "P");
        }
        long start = System.currentTimeMillis();
        int cycles = solution(large, 5);
        long millis = System.currentTimeMillis() - start;
        System.out.printf(
                "Large test (N=%d, rate=5): %d cycles in %d ms%n",
                N, cycles, millis
        );
    }
}