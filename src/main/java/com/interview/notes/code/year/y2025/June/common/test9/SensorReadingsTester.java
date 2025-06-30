package com.interview.notes.code.year.y2025.June.common.test9;

import java.util.*;
import java.util.stream.*;
/*


### Problem Statement

You are working with data collected from various sensors. Given an array of non-negative integers `readings` representing the sensor readings, transform the array by **repeatedly replacing each element with the sum of its digits**.

Continue this transformation until every element is a **single digit**. Return the **most occurring digit** in the final array.

In case of a tie, return the **highest digit**.

> Note: You are not expected to provide the most optimal solution, but a solution with time complexity not worse than **O(readings.length²)** will fit within the execution time limit.

---

### Function Signature

```java
int solution(int[] readings) {
    // Your code here
}
```

---

### Example

```java
readings = [123, 456, 789, 101]
// Output: 6

Explanation:
123 → 1+2+3 = 6
456 → 4+5+6 = 15 → 1+5 = 6
789 → 7+8+9 = 24 → 2+4 = 6
101 → 1+0+1 = 2
Final array: [6, 6, 6, 2] → Most frequent = 6
```

```java
readings = [6]
// Output: 6
// Already a single-digit number
```

```java
readings = [3, 12, 23, 32, 0]
// Output: 5

Explanation:
Transformed array:
3 → 3
12 → 1+2 = 3
23 → 2+3 = 5
32 → 3+2 = 5
0 → 0

Final array: [3, 3, 5, 5, 0] → 3 and 5 occur twice, return the highest → 5
```

 */
public class SensorReadingsTester {

    /**
     * Transforms each reading to its repeated‐digit‐sum (digital root),
     * then returns the most frequent digit in the final array.
     * In case of a tie, returns the highest digit.
     */
    public static int solution(int[] readings) {
        // count occurrences of each digit 0–9
        int[] freq = new int[10];
        for (int r : readings) {
            int d = digitalRoot(r);
            freq[d]++;
        }

        // find highest frequency
        int maxCount = Arrays.stream(freq).max().orElse(0);
        // in case of tie, pick highest digit → scan from 9 down to 0
        for (int digit = 9; digit >= 0; digit--) {
            if (freq[digit] == maxCount) {
                return digit;
            }
        }
        return 0; // unreachable
    }

    /** Computes the repeated‐sum‐of‐digits (“digital root”) in O(1) time */
    private static int digitalRoot(int n) {
        if (n == 0) return 0;
        return 1 + (n - 1) % 9;
    }

    public static void main(String[] args) {
        // define test cases: (input array → expected result)
        List<Map.Entry<int[], Integer>> tests = Arrays.asList(
            new AbstractMap.SimpleEntry<>(new int[]{123, 456, 789, 101}, 6),
            new AbstractMap.SimpleEntry<>(new int[]{6},               6),
            new AbstractMap.SimpleEntry<>(new int[]{3, 12, 23, 32, 0}, 5),
            new AbstractMap.SimpleEntry<>(new int[]{0, 0, 0},         0),
            new AbstractMap.SimpleEntry<>(new int[]{10, 19, 28, 37},   1)
        );

        int passed = 0;
        for (int i = 0; i < tests.size(); i++) {
            int[] input = tests.get(i).getKey();
            int expected = tests.get(i).getValue();
            int actual = solution(input);
            boolean ok = actual == expected;
            System.out.printf(
                "Test %02d: %-4s  expected=%d  got=%d  input=%s%n",
                i+1, ok ? "PASS" : "FAIL", expected, actual,
                Arrays.toString(input)
            );
            if (ok) passed++;
        }
        System.out.printf("Passed %d/%d tests%n",
                          passed, tests.size());
    }
}