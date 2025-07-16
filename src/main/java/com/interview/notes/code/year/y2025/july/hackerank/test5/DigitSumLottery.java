package com.interview.notes.code.year.y2025.july.hackerank.test5;

import java.util.*;
/*
Here is the combined and properly formatted version of the **"Digit Sum"** problem from the provided screenshots:

---

## 💡 **Problem: Digit Sum**

In a lottery game, tickets ranging from `lowLimit` to `highLimit` are distributed to participants.
Each coupon code is equal to the **sum of the digits** of the ticket number.

> Example: Ticket number `10` has a code of `1 + 0 = 1`.

Each participant holding a coupon code has a chance to win. The lottery prize is **split equally among all winners**.

---

### 🎯 **Goal**

Determine:

1. The number of ways to choose the **maximum number of winners**.
2. The number of participants (coupon codes) who will win the lottery.

---

### 🧠 Example

**Input:**

```
lowLimit = 1
highLimit = 10
```

**Ticket Numbers & Coupon Codes:**

| Ticket | Coupon Code (Digit Sum) |
| ------ | ----------------------- |
| 1      | 1                       |
| 2      | 2                       |
| 3      | 3                       |
| 4      | 4                       |
| 5      | 5                       |
| 6      | 6                       |
| 7      | 7                       |
| 8      | 8                       |
| 9      | 9                       |
| 10     | 1                       |

* Coupon code `1` appears **twice** (ticket `1` and `10`)
* Other codes appear only once.

**Output:**

```
1
2
```

Explanation:

* Only **coupon code 1** has 2 winners → maximum.
* So:
  → **1 way** to choose max winners
  → **2 participants** will win.

---

### 📥 Input Format

* The first line contains a long integer: `lowLimit`
* The second line contains a long integer: `highLimit`

---

### 📤 Output Format

Return a list/array of two `long` values:

```
[waysToChooseMaxWinners, numberOfMaxWinners]
```

---

### ✅ Constraints

```
1 ≤ lowLimit < highLimit ≤ 10^18
```

---

### 🔁 Sample Cases

#### Sample Case 0:

**Input:**

```
1
5
```

**Output:**

```
5
1
```

**Explanation:**

* Ticket numbers: 1 to 5 → codes = \[1, 2, 3, 4, 5]
* All unique → each has 1 winner → 5 ways to have 1 winner

---

#### Sample Case 1:

**Input:**

```
3
12
```

**Output:**

```
1
2
```

**Explanation:**

* Tickets 3 to 12 → codes = \[3, 4, 5, 6, 7, 8, 9, 1, 2, 3]
* Code `3` appears **twice**, others once.
* So: 1 way to have 2 winners (code 3)

---

### 🧑‍💻 Function Signature

```java
public static List<Long> waysToChooseSum(long lowLimit, long highLimit)
```

---

Would you like me to provide a complete Java implementation using `Stream API` and a simple `main` method for testing?

 */
/**
 * DigitSumLottery solves the ticket lottery problem:
 * tickets from lowLimit to highLimit have coupon codes equal to digit sums.
 * We need the number of digit sums that occur most often (ways)
 * and that maximum occurrence count (maxCount).
 */
public class DigitSumLottery {

    /**
     * Compute the result [ways, maxCount] for the input range.
     * @param lowLimit inclusive lower bound of ticket numbers
     * @param highLimit inclusive upper bound of ticket numbers
     * @return List of two elements: [number of sums with max frequency, max frequency]
     */
    public static List<Long> waysToChooseSum(long lowLimit, long highLimit) {
        // get distribution of digit sums for [0, highLimit]
        long[] highDist = countDigitSumDistribution(highLimit);
        // get distribution of digit sums for [0, lowLimit - 1]
        long[] lowDist  = countDigitSumDistribution(lowLimit - 1);

        long maxCount = 0;
        long ways     = 0;

        // loop over all possible sums
        for (int sum = 0; sum < highDist.length; sum++) {
            long count = highDist[sum] - (sum < lowDist.length ? lowDist[sum] : 0);
            if (count > maxCount) {
                maxCount = count;
                ways     = 1;
            } else if (count == maxCount) {
                ways++;
            }
        }
        return Arrays.asList(ways, maxCount);
    }

    /**
     * Digit DP: count how many numbers from 0..N have each possible digit sum.
     */
    private static long[] countDigitSumDistribution(long N) {
        if (N < 0) return new long[0];
        String s = Long.toString(N);
        int len    = s.length();
        int maxSum = 9 * len;

        long[][] dpPrev = new long[2][maxSum + 1];
        dpPrev[1][0] = 1;

        for (int pos = 0; pos < len; pos++) {
            long[][] dpCurr = new long[2][maxSum + 1];
            int digit = s.charAt(pos) - '0';

            for (int tight = 0; tight <= 1; tight++) {
                int limit = tight == 1 ? digit : 9;
                for (int sum = 0; sum <= maxSum; sum++) {
                    long ways = dpPrev[tight][sum];
                    if (ways == 0) continue;
                    for (int d = 0; d <= limit; d++) {
                        int nextTight = (tight == 1 && d == limit) ? 1 : 0;
                        dpCurr[nextTight][sum + d] += ways;
                    }
                }
            }
            dpPrev = dpCurr;
        }

        long[] dist = new long[maxSum + 1];
        for (int sum = 0; sum <= maxSum; sum++) {
            dist[sum] = dpPrev[0][sum] + dpPrev[1][sum];
        }
        return dist;
    }

    /**
     * Main method to test various cases without JUnit.
     */
    public static void main(String[] args) {
        List<TestCase> tests = Arrays.asList(
            new TestCase(1,   5,  5, 1),  // sample 0
            new TestCase(3,  12,  1, 2),  // sample 1
            new TestCase(1,   9,  9, 1),  // new test
            new TestCase(1,  10,  1, 2)   // description example
        );

        for (TestCase t : tests) {
            List<Long> out = waysToChooseSum(t.low, t.high);
            boolean pass = out.get(0).equals(t.expWays) && out.get(1).equals(t.expMaxCount);
            System.out.printf("Test [%d, %d]: expected=(%d,%d), got=(%d,%d) => %s%n",
                    t.low, t.high,
                    t.expWays, t.expMaxCount,
                    out.get(0), out.get(1),
                    pass ? "PASS" : "FAIL");
        }

        // performance check
        long start = System.currentTimeMillis();
        waysToChooseSum(1, 1_000_000_000_000L);
        System.out.println("Large test time: " + (System.currentTimeMillis() - start) + " ms");
    }

    private static class TestCase {
        long low, high, expWays, expMaxCount;
        TestCase(long low, long high, long w, long m) {
            this.low = low;
            this.high = high;
            this.expWays = w;
            this.expMaxCount = m;
        }
    }
}