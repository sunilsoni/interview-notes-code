package com.interview.notes.code.months.dec24.Amazon.test11;

import java.util.*;

/*
WORKING



### Function Description
Complete the function **`getMaxSumarr`** in the editor below.

### Function Signature
```java
public static int getMaxSumarr(List<Integer> item_weights);
```

### Parameters
- **`List<Integer> item_weights`**: A list of integers where `item_weights[i]` represents the weight of the i-th item.

### Returns
- **int**: The maximum possible **sum_arr** as defined in the problem.

---

### Constraints
- \( 3 \leq n \leq 10^5 \) (where \( n \) is the size of the list `item_weights`)
- \( -10^4 \leq item\_weights[i] \leq 10^4 \)
- \( n \) is divisible by 3

---

### Input Format for Custom Testing
1. The first line contains an integer \( n \), the number of items in **`item_weights`**.
2. The next \( n \) lines each contain an integer \( item\_weights[i] \).

---

### Example Input and Output

#### **Sample Case 0**

**Input**
```
6
1
3
4
7
5
2
```

**Output**
```
4
```

**Explanation**
- \( n = 6 \), \( item\_weights = [1, 3, 4, 7, 5, 2] \).
- Remove elements **1** and **3** to leave **new_arr = [4, 7, 5, 2]**.
- Compute **sum_arr**:
  \[
  \text{First half sum} = (4 + 7) = 11, \, \text{Second half sum} = (5 + 2) = 7
  \]
  Hence, \( sum\_arr = 11 - 7 = 4 \).

---

#### **Sample Case 1**

**Input**
```
3
-3
-2
-1
```

**Output**
```
-1
```

**Explanation**
- \( n = 3 \), \( item\_weights = [-3, -2, -1] \).
- Remove element **-3** to leave **new_arr = [-2, -1]**.
- Compute **sum_arr**:
  \[
  \text{First half sum} = -2, \, \text{Second half sum} = -1
  \]
  Hence, \( sum\_arr = -2 - (-1) = -1 \).

---

### Visual Example

Given \( item\_weights = [3, 2, 1] \):

| **item_weights** | **Removing element** | **new_arr** | **sum_arr**          |
|------------------|----------------------|-------------|----------------------|
| [3, 2, 1]       | Index 2 (1-based)    | [3, 1]      | \( 3 - 1 = 2 \)      |
| [3, 2, 1]       | Index 1 (1-based)    | [2, 1]      | \( 2 - 1 = 1 \)      |
| [3, 2, 1]       | Index 3 (1-based)    | [3, 2]      | \( 3 - 2 = 1 \)      |

- Maximum **sum_arr** = **2** when removing index 2.

 */
public class MaxSumArrSolution {

    public static int getMaxSumarr(List<Integer> item_weights) {
        int n = item_weights.size();
        int m = n / 3;
        int[] arr = item_weights.stream().mapToInt(i -> i).toArray();

        // prefix_sums_max: max sum of m elements chosen from first i elements
        long[] prefix_sums_max = new long[n + 1];
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        long currentSum = 0;

        // Initially choose the first m elements
        for (int i = 0; i < m; i++) {
            currentSum += arr[i];
            minHeap.offer(arr[i]);
        }
        prefix_sums_max[m] = currentSum;

        // For i from m+1 to 2m, try to improve the chosen set
        for (int i = m; i <= 2 * m - 1; i++) {
            // Add arr[i], and if needed remove the smallest chosen element
            currentSum += arr[i];
            minHeap.offer(arr[i]);
            currentSum -= minHeap.poll(); // remove smallest to keep only m largest
            prefix_sums_max[i + 1] = currentSum;
        }

        // suffix_sums_min: min sum of m elements chosen from last n-i elements
        long[] suffix_sums_min = new long[n + 1];
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        currentSum = 0;

        // Initially choose the last m elements
        for (int i = n - 1; i >= 2 * m; i--) {
            currentSum += arr[i];
            maxHeap.offer(arr[i]);
        }
        suffix_sums_min[2 * m] = currentSum;

        // For i from 2m-1 down to m, try to improve by choosing smaller elements
        for (int i = 2 * m - 1; i >= m; i--) {
            currentSum += arr[i];
            maxHeap.offer(arr[i]);
            currentSum -= maxHeap.poll(); // remove largest to keep only m smallest
            suffix_sums_min[i] = currentSum;
        }

        // Now find the maximum prefix_sums_max[i] - suffix_sums_min[i]
        long result = Long.MIN_VALUE;
        for (int i = m; i <= 2 * m; i++) {
            long candidate = prefix_sums_max[i] - suffix_sums_min[i];
            if (candidate > result) {
                result = candidate;
            }
        }

        return (int) result;
    }

    // Simple testing in main
    public static void main(String[] args) {
        test(new int[]{1, 3, 4, 7, 5, 2}, 4); // from the example
        test(new int[]{3, 2, 1}, 2); // from given smaller example
        test(new int[]{-3, -2, -1}, -1); // negative case
        test(new int[]{6, 1, 3, 4, 7, 5, 2}, 4); // n
        test(new int[]{3, -3, -2, -1}, -1); // n
        // Add more tests as needed
    }

    private static void test(int[] input, int expected) {
        List<Integer> list = new ArrayList<>();
        for (int i : input) list.add(i);
        int result = getMaxSumarr(list);
        System.out.println("Input: " + Arrays.toString(input) +
                " | Expected: " + expected +
                " | Got: " + result +
                " | " + (result == expected ? "PASS" : "FAIL"));
    }
}
