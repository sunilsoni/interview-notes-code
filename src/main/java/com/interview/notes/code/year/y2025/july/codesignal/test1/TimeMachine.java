package com.interview.notes.code.year.y2025.july.codesignal.test1;

import java.util.Random;
import java.util.stream.IntStream;

/*WORKING SOLUTION
 *
 * This solution calculates the total hours required to travel through a sequence of years.
 * The rules are:
 * - 0 hours if the years are the same
 * - 1 hour if traveling forward in time (A < B)
 * - 2 hours if traveling backward in time (A > B)
 *
 Here’s the **combined and formatted version** of the question based on your screenshots:

---

### 🧭 Time Travel with a Time Machine

Imagine that you have a time machine. You are given an array of integers `years`. You start in the year `years[0]`. Then, you want to travel to `years[1]`, then to `years[2]`, and so on.

Your task is to calculate the total number of **hours** required to visit all the years from the list in order.

---

### 🕒 Travel Time Rules:

For any two years `A` and `B`, the time taken to go from year `A` to `B` is:

* `0` hours if `A == B` (same year)
* `1` hour if `A < B` (forward in time)
* `2` hours if `A > B` (backward in time)

---

### ✅ Function Signature

```java
int solution(int[] years)
```

### 📥 Input

* An array `years` of integers representing years to travel through.
* Constraints:

  * `1 ≤ years.length ≤ 100`
  * `1 ≤ years[i] ≤ 10⁴`

### 📤 Output

* Return an integer: the number of hours required to visit all the years in order.

---

### 💡 Example:

```java
Input:  [2000, 1990, 2005, 2050]
Output: 4

Explanation:
- 2000 → 1990 = 2 hours (backward)
- 1990 → 2005 = 1 hour (forward)
- 2005 → 2050 = 1 hour (forward)
Total = 2 + 1 + 1 = 4 hours
```

---

### 🧪 More Examples:

```java
Input:  [2000, 2021, 2005]
Output: 3
// 2000 → 2021 = 1 hour
// 2021 → 2005 = 2 hours

Input:  [2021, 2021, 2005]
Output: 2
// 2021 → 2021 = 0 hour
// 2021 → 2005 = 2 hours
```

---

### 🔧 Sample Implementation Template

```java
int solution(int[] years) {
    int totalHours = 0;
    for (int i = 1; i < years.length; i++) {
        if (years[i] == years[i - 1]) {
            totalHours += 0;
        } else if (years[i] > years[i - 1]) {
            totalHours += 1;
        } else {
            totalHours += 2;
        }
    }
    return totalHours;
}
```


*
 */
public class TimeMachine {

    /**
     * Returns the total hours required to travel through the given years in order.
     * - 0 hours if A == B
     * - 1 hour  if A < B (forward)
     * - 2 hours if A > B (backward)
     */
    public static int solution(int[] years) {
        // Sum over each consecutive pair
        return IntStream.range(0, years.length - 1)
                .map(i -> {
                    int a = years[i], b = years[i + 1];
                    return (a == b) ? 0 : (a < b ? 1 : 2);
                })
                .sum();
    }

    /**
     * Helper for running a single test case and printing PASS/FAIL.
     */
    private static void testCase(int[] years, int expected, int id) {
        int got = solution(years);
        if (got == expected) {
            System.out.printf("Test %2d: PASS (got %d)%n", id, got);
        } else {
            System.out.printf(
                    "Test %2d: FAIL (expected %d but got %d)%n",
                    id, expected, got
            );
        }
    }

    public static void main(String[] args) {
        // ——— Provided examples ———
        testCase(new int[]{2000, 1990, 2005, 2050}, 4, 1);
        testCase(new int[]{2000, 2021, 2005}, 3, 2);
        testCase(new int[]{2021, 2021, 2005}, 2, 3);

        // ——— Edge cases ———
        testCase(new int[]{2025}, 0, 4);
        testCase(new int[]{1000, 1000, 1000}, 0, 5);
        testCase(new int[]{1000, 999, 1001, 1001}, 3, 6);

        // ——— Large randomized input for performance check ———
        int N = 100_000;
        int[] large = new int[N];
        Random rnd = new Random(42);
        for (int i = 0; i < N; i++) {
            large[i] = 1 + rnd.nextInt(10_000);
        }
        long start = System.currentTimeMillis();
        int result = solution(large);
        long elapsed = System.currentTimeMillis() - start;
        System.out.printf(
                "Large input of %,d years processed in %d ms, result = %d%n",
                N, elapsed, result
        );
    }
}