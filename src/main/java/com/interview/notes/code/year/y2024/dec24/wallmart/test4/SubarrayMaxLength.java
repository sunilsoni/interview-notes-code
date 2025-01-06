package com.interview.notes.code.year.y2024.dec24.wallmart.test4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
public class SubarrayMaxLength {
    public static int maxLength(List<Integer> a, int k) {
        int maxLen = 0;
        int currentSum = 0;
        int start = 0;

        // Sliding window approach
        for (int end = 0; end < a.size(); end++) {
            currentSum += a.get(end);

            // Shrink window while sum > k
            while (currentSum > k && start <= end) {
                currentSum -= a.get(start);
                start++;
            }

            // Update max length if current window is valid
            maxLen = Math.max(maxLen, end - start + 1);
        }

        return maxLen;
    }

    // Test method
    public static void main(String[] args) {
        // Test case 1: Basic case
        test(Arrays.asList(1, 2, 3), 4, 2, "Basic case");

        // Test case 2: Single element
        test(Arrays.asList(5), 4, 0, "Single element > k");

        // Test case 3: All elements valid
        test(Arrays.asList(1, 1, 1), 5, 3, "All elements valid");

        // Test case 4: Large numbers
        test(Arrays.asList(1000, 1000, 1000), 2000, 2, "Large numbers");

        // Test case 5: Edge case - empty list
        test(new ArrayList<>(), 5, 0, "Empty list");

        // Test case 6: Large input
        List<Integer> largeInput = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            largeInput.add(1);
        }
        test(largeInput, 50000, 50000, "Large input");
    }

    private static void test(List<Integer> arr, int k, int expected, String testName) {
        int result = maxLength(arr, k);
        System.out.printf("Test: %s - %s (Expected: %d, Got: %d)%n",
                testName,
                result == expected ? "PASS" : "FAIL",
                expected,
                result);
    }
}
