package com.interview.notes.code.year.y2025.feb25.paypal.test1;

import java.util.*;

/*
WORKING:

# **Minimum Sum**

## **Description**
Given an array of integers, perform some number \( k \) of operations. Each operation consists of:
1. Removing an element from the array.
2. Dividing it by `2` and inserting the **ceiling** of that result back into the array.

Minimize the sum of the elements in the final array.

### **Example**
```
nums = [10, 20, 7]
k = 4
```

### **Table of Operations**
| Pick | Pick/2 | Ceiling | Result |
|------|--------|---------|--------|
| Initial array | - | - | [10, 20, 7] |
| 7 | 3.5 | 4 | [10, 20, 4] |
| 10 | 5 | 5 | [5, 20, 4] |
| 20 | 10 | 10 | [5, 10, 4] |
| 10 | 5 | 5 | [5, 5, 4] |

The sum of the final array is:
```
5 + 5 + 4 = 14
```
and that sum is minimal.

---

## **Function Description**
Complete the function `minSum` in the editor below.

### **Function Signature**
```java
public static int minSum(List<Integer> num, int k)
```

### **Parameters**
- `int nums[n]`: An array of integers, indexed from `0` to `n-1`.
- `int k`: The number of operations to perform.

### **Returns**
- `int`: The minimum sum of the array after `k` steps.

---

## **Constraints**
- \( 1 \leq n \leq 10^5 \)
- \( 1 \leq nums[i] \leq 10^4 \) (where \( 0 \leq i < n \))
- \( 1 \leq k \leq 2 \times 10^6 \)

---

## **Input Format For Custom Testing**
- The first line contains an integer `n`, the size of array `nums`.
- Each of the next `n` lines contains an integer describing the element `nums[i]` where \( 0 \leq i < n \).
- The last line contains the integer `k`.

---

## **Sample Case 0**
### **Sample Input**
```
1
2
1
```
### **Sample Output**
```
1
```
### **Explanation**
- In the first operation, the number `2` is reduced to `1`.

---

## **Java Code Skeleton**
```java
/*
 * Complete the 'minSum' function below.
 *
 * The function is expected to return an INTEGER.
 * The function accepts the following parameters:
 * 1. INTEGER_ARRAY num
 * 2. INTEGER k

public static int minSum(List<Integer> num, int k) {
    // Write your code here
}
```

 */
public class MinimumSumTreeMapSolution {

    /**
     * Returns the minimum sum of the array after performing k operations.
     * Each operation picks the current maximum element (using frequency counts)
     * and replaces one occurrence of it with the ceiling of its half.
     *
     * @param nums List of integers.
     * @param k    Number of operations.
     * @return The minimum sum after k operations.
     */
    public static int minSumTreeMap(List<Integer> nums, int k) {
        // TreeMap in descending order to access the maximum element quickly.
        TreeMap<Integer, Integer> map = new TreeMap<>(Collections.reverseOrder());
        // Build frequency map.
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        // Perform k operations.
        while (k > 0) {
            // Get the largest key (maximum element).
            int max = map.firstKey();
            int freq = map.get(max);

            // Process one operation on one occurrence of the maximum element.
            int reduced = (max + 1) / 2;

            // Decrease the frequency count of the maximum.
            if (freq == 1) {
                map.remove(max);
            } else {
                map.put(max, freq - 1);
            }

            // Insert/update the reduced value.
            map.put(reduced, map.getOrDefault(reduced, 0) + 1);
            k--;
        }

        // Compute the final sum.
        return map.entrySet().stream()
                .mapToInt(e -> e.getKey() * e.getValue())
                .sum();
    }

    /**
     * Main method for testing multiple cases, including large input scenarios.
     */
    public static void main(String[] args) {
        int testCaseNumber = 1;

        // Test Case 1: Single element.
        List<Integer> nums1 = Arrays.asList(2);
        int k1 = 1;
        int expected1 = 1;
        runTest(testCaseNumber++, nums1, k1, expected1, "TreeMap");

        // Test Case 2: Provided sample.
        List<Integer> nums2 = Arrays.asList(10, 20, 7);
        int k2 = 4;
        int expected2 = 14;
        runTest(testCaseNumber++, nums2, k2, expected2, "TreeMap");

        // Test Case 3: All elements are 1.
        List<Integer> nums3 = Arrays.asList(1, 1, 1);
        int k3 = 5;
        int expected3 = 3;
        runTest(testCaseNumber++, nums3, k3, expected3, "TreeMap");

        // Test Case 4: No operations performed.
        List<Integer> nums4 = Arrays.asList(5, 10, 15);
        int k4 = 0;
        int expected4 = 30;
        runTest(testCaseNumber++, nums4, k4, expected4, "TreeMap");

        // Test Case 5: Large input test for performance.
        List<Integer> nums5 = new ArrayList<>();
        int n5 = 100000; // 100k elements.
        for (int i = 0; i < n5; i++) {
            nums5.add(10000);
        }
        int k5 = 2000000; // 2 million operations.
        long start = System.currentTimeMillis();
        int result5 = minSumTreeMap(nums5, k5);
        long end = System.currentTimeMillis();
        System.out.println("Test " + testCaseNumber++ + " (Large Test using TreeMap): Result = "
                + result5 + ", Time = " + (end - start) + " ms");
    }

    /**
     * Helper method to run an individual test case.
     *
     * @param testCaseNumber The test case number.
     * @param nums           Input list.
     * @param k              Number of operations.
     * @param expected       Expected sum.
     * @param method         The method used ("TreeMap").
     */
    private static void runTest(int testCaseNumber, List<Integer> nums, int k, int expected, String method) {
        int result = minSumTreeMap(nums, k);
        if (result == expected) {
            System.out.println("Test " + testCaseNumber + " (" + method + "): PASS");
        } else {
            System.out.println("Test " + testCaseNumber + " (" + method + "): FAIL. Expected: "
                    + expected + ", Got: " + result);
        }
    }
}