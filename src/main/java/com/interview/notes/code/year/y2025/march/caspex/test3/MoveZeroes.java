package com.interview.notes.code.year.y2025.march.caspex.test3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/*
WORKING 100


### **Problem Statement: Moving Zeroes to the End**
Given an integer array `nums[]`, move all `0`s to the end of the array while maintaining the relative order of the non-zero elements.

#### **Constraints**
- **In-place operation** → No additional copies of the array allowed.
- **Maintain relative order** → Non-zero elements should stay in the same order.

---

### **Input Format**
1. First line → Integer `N` → size of the array.
2. Second line → `N` space-separated integers → array elements.

---

### **Expected Output**
- The updated array after moving **all `0`s to the end**.

---

### **Example 1**
#### **Input**
```
5
0 1 0 3 12
```
#### **Output**
```
1 3 12 0 0
```
#### **Explanation**
1. The non-zero elements **[1, 3, 12]** remain in order.
2. The zeros **[0, 0]** are moved to the end.

---

### **Example 2**
#### **Input**
```
1
0
```
#### **Output**
```
0
```
#### **Explanation**
- The array contains only a zero, so no change is needed.

 */
public class MoveZeroes {

    /**
     * Moves all zeroes to the end of the array while maintaining relative order of non-zero elements
     *
     * @param nums Input list of integers
     * @return Modified list with zeros moved to the end
     */
    public static List<Integer> solve(List<Integer> nums) {
        // Edge case: empty list or null
        if (nums == null || nums.isEmpty()) {
            return nums;
        }

        int nonZeroPos = 0; // Position where next non-zero element should go

        // First pass: place all non-zero elements in their correct positions
        for (int i = 0; i < nums.size(); i++) {
            if (nums.get(i) != 0) {
                nums.set(nonZeroPos++, nums.get(i));
            }
        }

        // Second pass: fill remaining positions with zeroes
        while (nonZeroPos < nums.size()) {
            nums.set(nonZeroPos++, 0);
        }

        return nums;
    }

    // Alternative implementation using Java 8 Stream (more concise but creates temporary collections)
    public static List<Integer> solveUsingStream(List<Integer> nums) {
        // Count zeroes and filter non-zero elements
        long zeroCount = nums.stream().filter(num -> num == 0).count();
        List<Integer> result = new ArrayList<>(nums.stream()
                .filter(num -> num != 0)
                .collect(Collectors.toList()));

        // Add zeroes at the end
        for (int i = 0; i < zeroCount; i++) {
            result.add(0);
        }

        return result;
    }

    /**
     * Test method to verify the solution against test cases
     */
    public static void main(String[] args) {
        // Test case 1: Example from problem
        runTest(Arrays.asList(0, 1, 0, 3, 12), Arrays.asList(1, 3, 12, 0, 0), "Basic test");

        // Test case 2: Single element
        runTest(Arrays.asList(0), Arrays.asList(0), "Single zero");

        // Test case 3: No zeroes
        runTest(Arrays.asList(1, 2, 3), Arrays.asList(1, 2, 3), "No zeroes");

        // Test case 4: All zeroes
        runTest(Arrays.asList(0, 0, 0), Arrays.asList(0, 0, 0), "All zeroes");

        // Test case 5: Zeroes at the end already
        runTest(Arrays.asList(1, 2, 0, 0), Arrays.asList(1, 2, 0, 0), "Zeroes at end");

        // Test case 6: Large input
        List<Integer> largeInput = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            largeInput.add(i % 3 == 0 ? 0 : i);
        }
        System.out.println("Large input test: " + testLargeInput(largeInput));
    }

    private static void runTest(List<Integer> input, List<Integer> expected, String testName) {
        List<Integer> inputCopy = new ArrayList<>(input);
        List<Integer> result = solve(inputCopy);
        boolean passed = result.equals(expected);
        System.out.println(testName + ": " + (passed ? "PASS" : "FAIL"));
        if (!passed) {
            System.out.println("  Expected: " + expected);
            System.out.println("  Got: " + result);
        }
    }

    private static boolean testLargeInput(List<Integer> input) {
        List<Integer> inputCopy = new ArrayList<>(input);
        long startTime = System.currentTimeMillis();
        List<Integer> result = solve(inputCopy);
        long endTime = System.currentTimeMillis();

        // Verify correctness - all non-zeroes come first, then all zeroes
        int firstZeroPos = -1;
        for (int i = 0; i < result.size(); i++) {
            if (result.get(i) == 0 && firstZeroPos == -1) {
                firstZeroPos = i;
            } else if (result.get(i) != 0 && firstZeroPos != -1) {
                return false; // Found non-zero after zeroes started
            }
        }

        System.out.println("Large input execution time: " + (endTime - startTime) + "ms");
        return true;
    }
}
