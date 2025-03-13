package com.interview.notes.code.year.y2025.march.jpmc.test1;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
/*
WORKING 100

## **Problem Statement**
Given an array of integers, determine the number of ways the entire array can be split into two non-empty subarrays, left and right, such that the sum of elements in the left subarray is **greater** than the sum of elements in the right subarray.

---

## **Example**
### **Input**
```
arr = [10, 4, -8, 7]
```
### **Possible Splits**
1. `[10]` and `[4, -8, 7]`
   - Left sum = 10
   - Right sum = 3
   - ✅ Left sum > Right sum

2. `[10, 4]` and `[-8, 7]`
   - Left sum = 10 + 4 = 14
   - Right sum = -8 + 7 = -1
   - ✅ Left sum > Right sum

3. `[10, 4, -8]` and `[7]`
   - Left sum = 6
   - Right sum = 7
   - ❌ Left sum < Right sum

### **Output**
```
2
```
**Explanation:**
Only the first two splits satisfy the condition (`Left sum > Right sum`).

---

## **Function Definition**
```java
public static int splitIntoTwo(List<Integer> arr) {
    // Implement logic
}
```
**Parameters:**
- `arr`: Integer list representing the array.

**Returns:**
- `int`: Number of valid splits where left sum > right sum.

---

## **Constraints**
- \(2 \leq n \leq 10^5\)
- \(-10^4 \leq arr[i] \leq 10^4\)

---

## **Sample Cases**
### **Sample Case 0**
#### **Input**
```
n = 3
arr = [10, -5, 6]
```
#### **Output**
```
1
```
#### **Explanation**
Possible splits:
1. `[10]` and `[-5, 6]` → Left sum = 10, Right sum = 1 ✅
2. `[10, -5]` and `[6]` → Left sum = 5, Right sum = 6 ❌

Only one valid split.

---

### **Sample Case 1**
#### **Input**
```
n = 5
arr = [-3, -2, 10, 20, -30]
```
#### **Output**
```
2
```
#### **Explanation**
Possible splits:
1. `[-3, -2, 10]` and `[20, -30]` → Left sum = 5, Right sum = -10 ✅
2. `[-3, -2, 10, 20]` and `[-30]` → Left sum = 25, Right sum = -30 ✅

Both splits satisfy `Left sum > Right sum`.

---


 */

/**
 * This class contains the solution to the "splitIntoTwo" problem along
 * with a main method for testing.
 */
public class SplitIntoTwoSolution {

    /**
     * The splitIntoTwo method calculates how many ways we can split
     * the array into two non-empty parts such that:
     * sum(left subarray) > sum(right subarray).
     *
     * @param arr The input list of integers.
     * @return The number of valid splits.
     */
    public static int splitIntoTwo(List<Integer> arr) {
        // Step 1: Compute the total sum of the array elements.
        // We use a LongStream because the array elements can be up to 10^4,
        // and there can be up to 10^5 elements. Summation can reach ~10^9,
        // which still fits into an 'int', but using 'long' is safer.
        long totalSum = arr.stream()
                .mapToLong(Integer::longValue)
                .sum();

        // We'll keep a running sum of the left side as we iterate
        // from the first element to the second-last element.
        long leftSum = 0;

        // This variable counts the number of valid splits where
        // leftSum > (totalSum - leftSum).
        int countSplits = 0;

        // Step 2: Iterate through the array (except the last element)
        // to form the left subarray. We only go to arr.size() - 1
        // because the right subarray cannot be empty.
        for (int i = 0; i < arr.size() - 1; i++) {
            // Add the current element to the running left sum.
            leftSum += arr.get(i);

            // Calculate the sum on the right side by subtracting
            // leftSum from totalSum.
            long rightSum = totalSum - leftSum;

            // Check if left sum is strictly greater than right sum.
            if (leftSum > rightSum) {
                countSplits++;
            }
        }

        // Step 3: Return the number of valid splits.
        return countSplits;
    }

    /**
     * Helper method to test our splitIntoTwo function for a single test case.
     * Prints PASS if the result matches the expected value; otherwise FAIL.
     *
     * @param testCaseId A label for the test (e.g., "Test 1").
     * @param arr        The input array of integers.
     * @param expected   The expected number of valid splits.
     */
    private static void runTest(String testCaseId, List<Integer> arr, int expected) {
        int result = splitIntoTwo(arr);
        System.out.printf("%s => ", testCaseId);
        if (result == expected) {
            System.out.println("PASS (Result: " + result + ")");
        } else {
            System.out.println("FAIL (Expected: " + expected + ", Got: " + result + ")");
        }
    }

    /**
     * Main method to execute multiple tests (including edge cases).
     * We do not use JUnit; instead, we use a simple pass/fail printout.
     */
    public static void main(String[] args) {

        // 1. Sample Test Case 0 (from the prompt)
        // arr = [10, -5, 6]
        // There are two ways to split:
        //  [10], [-5, 6] => sum(left)=10, sum(right)=1 => valid
        //  [10, -5], [6] => sum(left)=5, sum(right)=6 => not valid
        // Actually, the prompt mentions there are 2 ways, so let's confirm:
        //   1) left=[10], right=[-5,6],  leftSum=10 > 1 => valid
        //   2) left=[10,-5], right=[6], leftSum=5 < 6 => invalid
        // The prompt also had a second example with sums 10 & 1, and 5 & 6,
        // but it concluded "sum left> sum right" and "sum left< sum right"
        // are two different scenarios. Possibly there's a misunderstanding
        // in the sample. We'll trust their final statement "Sample Output=1".
        // We'll test it as a single valid split.
        runTest("Sample Case 0", Arrays.asList(10, -5, 6), 1);

        // 2. Sample Test Case 1 (from the prompt)
        // arr = [-3, -2, 10, 20, -30]
        // They said the result is 2.
        runTest("Sample Case 1", Arrays.asList(-3, -2, 10, 20, -30), 2);

        // 3. Another example from the description:
        // arr = [10, 4, -8, 7]
        // Splits:
        //   1) [10], [4,-8,7] => sum(left)=10, sum(right)=3 => left>right => valid
        //   2) [10,4], [-8,7] => sum(left)=14, sum(right)=-1 => left>right => valid
        //   3) [10,4,-8], [7] => sum(left)=6, sum(right)=7 => not valid
        // So total valid splits = 2
        runTest("Example [10,4,-8,7]", Arrays.asList(10, 4, -8, 7), 2);

        // 4. Smallest constraint test: n=2
        // arr = [1, 2]
        // There's only one place to split: [1], [2]
        // leftSum=1, rightSum=2 => not valid => 0
        runTest("Edge Min Size [1,2]", Arrays.asList(1, 2), 0);

        // 5. All negative numbers:
        // e.g. arr = [-1, -1, -1]
        // Splits:
        //   [ -1 ], [ -1, -1 ] => leftSum=-1, rightSum=-2 => left>-2 => -1>-2 => valid
        //   [ -1, -1], [ -1 ] => leftSum=-2, rightSum=-1 => -2>-1 => false => invalid
        // So we expect 1 valid split
        runTest("All Negative [-1,-1,-1]", Arrays.asList(-1, -1, -1), 1);

        // 6. Large data test (performance check).
        // We'll create a random array of size 100,000 to ensure the method
        // runs efficiently. We'll just do a quick pass/fail check that it
        // doesn’t crash or take too long. The expected result is unknown,
        // so we'll just run it and print the result. This ensures
        // it handles large inputs within time constraints.
        // NOTE: This won't conclusively "fail" or "pass" on correctness
        // since we don’t have an exact expected value. We'll see if it
        // executes quickly and prints a result.
        int largeSize = 100_000;
        Random rand = new Random(42);
        List<Integer> largeList = rand.ints(largeSize, -10_000, 10_001)
                .boxed()
                .collect(Collectors.toList());
        long startTime = System.nanoTime();
        int largeResult = splitIntoTwo(largeList);
        long endTime = System.nanoTime();
        long durationMillis = (endTime - startTime) / 1_000_000;
        System.out.println("Large Data Test => Executed in " + durationMillis + " ms. Result: " + largeResult);

        // Final note: each runTest call above prints PASS/FAIL based on
        // the comparison of the actual result to the expected value.
        // For the large test, we just show how long it took and the result.
    }
}
