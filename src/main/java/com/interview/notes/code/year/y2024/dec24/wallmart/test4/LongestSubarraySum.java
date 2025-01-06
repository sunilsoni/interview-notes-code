package com.interview.notes.code.year.y2024.dec24.wallmart.test4;

import java.util.Random;

/*
WORKING



### Problem Description
Given an array `a` and an integer `k`, determine the **longest subarray** whose sum is less than or equal to `k`.

- **Subarray Definition**: A contiguous block of `a`'s elements.
- **Objective**: Return the **length** of the longest subarray where the sum of its elements is **≤ k**.

---

### Function Details
Complete the function:

```java
public static int maxLength(List<Integer> a, int k);
```

- **Parameters**:
   - `a`: A list of integers representing the array.
   - `k`: An integer representing the sum threshold.

- **Returns**:
   An integer, the length of the longest valid subarray.

---

### Example
#### Input:
```
3
1
2
3
4
```

#### Output:
```
2
```

#### Explanation:
Subarrays of `[1, 2, 3]` with sums ≤ `k=4` are `[1]`, `[2]`, `[3]`, and `[1, 2]`.
The **longest** of these subarrays is `[1, 2]` with a **length** of `2`.

---

### Constraints
- \( 1 \leq n \leq 10^5 \)
- \( 1 \leq a[i] \leq 10^3 \)
- \( 1 \leq k \leq 10^9 \)

---
### Input Format
1. First line: Single integer `n` (number of elements).
2. Next `n` lines: Each contains an integer `a[i]`.
3. Last line: Single integer `k`.

 */
public class LongestSubarraySum {
    public static void main(String[] args) {
        // Test cases
        test(new int[]{1, 2, 3}, 4, 2); // expected 2
        test(new int[]{2, 2, 2}, 3, 1); // expected 1
        test(new int[]{1, 1, 1, 1}, 5, 4); // expected 4
        test(new int[]{5, 6, 7}, 4, 0); // expected 0
        test(largeTest(), 1000000000, -1); // large input test, just check it runs
    }

    public static int maxLength(int[] a, int k) {
        int start = 0;
        int currentSum = 0;
        int maxLen = 0;
        for (int end = 0; end < a.length; end++) {
            currentSum += a[end];
            while (currentSum > k && start <= end) {
                currentSum -= a[start++];
            }
            // Here currentSum <= k
            int length = end - start + 1;
            if (length > maxLen) {
                maxLen = length;
            }
        }
        return maxLen;
    }

    private static void test(int[] arr, int k, int expected) {
        int result = maxLength(arr, k);
        if (expected == -1) {
            System.out.println("Test Result: " + result); // For large test, no strict expected
        } else {
            System.out.println((result == expected) ? "PASS" : "FAIL - got " + result + ", expected " + expected);
        }
    }

    // Generate a large test array
    private static int[] largeTest() {
        int n = 100000;
        int[] arr = new int[n];
        Random rand = new Random();
        for (int i = 0; i < n; i++) {
            arr[i] = rand.nextInt(1000) + 1; // values 1 to 1000
        }
        return arr;
    }
}
