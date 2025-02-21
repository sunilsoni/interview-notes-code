package com.interview.notes.code.year.y2025.feb25.paypal.test1;

import java.util.*;
import java.util.stream.*;
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
public class MinimumSumSolution {

    /**
     * Returns the minimum sum of the array after performing k operations.
     * Each operation removes the largest element, replaces it with the ceiling of its half.
     *
     * @param nums List of integers representing the array.
     * @param k    Number of operations to perform.
     * @return     The minimum sum after k operations.
     */
    public static int minSum(List<Integer> nums, int k) {
        // Use a max-heap to always process the largest element first.
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
        maxHeap.addAll(nums);
        
        // Perform k operations.
        for (int i = 0; i < k; i++) {
            // Remove the largest element.
            int current = maxHeap.poll();
            // Compute the ceiling of half of the current element.
            int reduced = (current + 1) / 2;
            // Add the reduced value back to the heap.
            maxHeap.offer(reduced);
        }
        
        // Sum the final elements using Java 8 Streams.
        return maxHeap.stream().mapToInt(Integer::intValue).sum();
    }

    /**
     * Main method to run test cases.
     */
    public static void main(String[] args) {
        int testCaseNumber = 1;
        
        // Test Case 1: Single element reduction.
        List<Integer> nums1 = Arrays.asList(2);
        int k1 = 1;
        int expected1 = 1; // 2 becomes 1 after one operation.
        runTest(testCaseNumber++, nums1, k1, expected1);
        
        // Test Case 2: Provided sample case.
        List<Integer> nums2 = Arrays.asList(10, 20, 7);
        int k2 = 4;
        int expected2 = 14;
        runTest(testCaseNumber++, nums2, k2, expected2);
        
        // Test Case 3: Edge case where all numbers are 1.
        List<Integer> nums3 = Arrays.asList(1, 1, 1);
        int k3 = 5;
        int expected3 = 3; // All remain 1.
        runTest(testCaseNumber++, nums3, k3, expected3);
        
        // Test Case 4: No operations performed.
        List<Integer> nums4 = Arrays.asList(5, 10, 15);
        int k4 = 0;
        int expected4 = 30;
        runTest(testCaseNumber++, nums4, k4, expected4);
        
        // Test Case 5: Large input test to validate performance.
        // For demonstration, we use 100,000 elements each initialized to 10,000 and k = 2,000,000.
        List<Integer> nums5 = new ArrayList<>();
        int n5 = 100000;
        for (int i = 0; i < n5; i++) {
            nums5.add(10000);
        }
        int k5 = 2000000;
        long start = System.currentTimeMillis();
        int result5 = minSum(nums5, k5);
        long end = System.currentTimeMillis();
        System.out.println("Test " + testCaseNumber++ + " (Large Test): Result = " + result5 + 
                           ", Time = " + (end - start) + " ms");
    }

    /**
     * Helper method to run a test case and output PASS/FAIL.
     *
     * @param testCaseNumber The test case number.
     * @param nums           The input list of numbers.
     * @param k              The number of operations.
     * @param expected       The expected result after operations.
     */
    private static void runTest(int testCaseNumber, List<Integer> nums, int k, int expected) {
        int result = minSum(nums, k);
        if (result == expected) {
            System.out.println("Test " + testCaseNumber + ": PASS");
        } else {
            System.out.println("Test " + testCaseNumber + ": FAIL. Expected: " + expected + ", Got: " + result);
        }
    }
}