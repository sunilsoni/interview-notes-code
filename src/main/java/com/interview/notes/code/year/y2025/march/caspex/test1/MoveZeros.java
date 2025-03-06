package com.interview.notes.code.year.y2025.march.caspex.test1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
WORKING 100%


### **Moving Zeros to End**

Given an integer array **nums[]**, move all **0's** to the end of it while maintaining the relative order of the non-zero elements.

**Note:** You must do this **in-place** without making a copy of the array.

#### **Input**
- The first line of input contains an integer **N**, representing the size of the array.
- The second line of input contains **N** space-separated integers, representing the array elements.

#### **Output**
The updated array after moving **0's** to the end of it.

#### **Constraints**
- \( 1 \leq N \leq 10^4 \)
- \( -2^{31} \leq \text{nums}[i] \leq 2^{31} - 1 \)

---

### **Example #1**
**Input**
```
5
0 1 0 3 12
```
**Output**
```
1 3 12 0 0
```

---

### **Example #2**
**Input**
```
1
0
```
**Output**
```
0
```

 */
public class MoveZeros {

    /**
     * Moves all zeros to the end of the array while preserving the order of non-zero elements.
     *
     * @param nums The input list of integers
     * @return The modified list with zeros at the end
     */
    public static List<Integer> solve(List<Integer> nums) {
        // If the list is empty or has only one element, return as is
        if (nums == null || nums.size() <= 1) {
            return nums;
        }

        // Count of non-zero elements
        int nonZeroIndex = 0;

        // First pass: move all non-zero elements to the front
        for (int i = 0; i < nums.size(); i++) {
            if (nums.get(i) != 0) {
                nums.set(nonZeroIndex, nums.get(i));
                nonZeroIndex++;
            }
        }

        // Second pass: fill the remaining positions with zeros
        for (int i = nonZeroIndex; i < nums.size(); i++) {
            nums.set(i, 0);
        }

        return nums;
    }

    // Alternative solution using Java 8 Streams (less efficient for this problem)
    public static List<Integer> solveWithStreams(List<Integer> nums) {
        List<Integer> nonZeros = nums.stream()
                .filter(n -> n != 0)
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);

        int zeroCount = nums.size() - nonZeros.size();
        for (int i = 0; i < zeroCount; i++) {
            nonZeros.add(0);
        }

        return nonZeros;
    }

    /**
     * Test method to verify our solution against test cases
     */
    public static void main(String[] args) {
        // Test cases
        testCase(Arrays.asList(0, 1, 0, 3, 12), Arrays.asList(1, 3, 12, 0, 0), "Example 1");
        testCase(Arrays.asList(0), Arrays.asList(0), "Example 2");

        // Additional test cases
        testCase(Arrays.asList(1, 2, 3, 4, 5), Arrays.asList(1, 2, 3, 4, 5), "All non-zeros");
        testCase(Arrays.asList(0, 0, 0, 0, 0), Arrays.asList(0, 0, 0, 0, 0), "All zeros");
        testCase(Arrays.asList(1, 0, 2, 0, 3), Arrays.asList(1, 2, 3, 0, 0), "Alternating");
        testCase(new ArrayList<>(), new ArrayList<>(), "Empty list");

        // Large test case
        List<Integer> largeInput = new ArrayList<>();
        List<Integer> largeExpected = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            if (i % 2 == 0) {
                largeInput.add(0);
            } else {
                largeInput.add(i);
                largeExpected.add(i);
            }
        }
        for (int i = 0; i < 5000; i++) {
            largeExpected.add(0);
        }
        testCase(largeInput, largeExpected, "Large input (10,000 elements)");
    }

    /**
     * Helper method to test and validate a single test case
     */
    private static void testCase(List<Integer> input, List<Integer> expected, String testName) {
        List<Integer> original = new ArrayList<>(input);
        List<Integer> result = solve(input);

        boolean passed = result.equals(expected);
        System.out.println("Test: " + testName);
        System.out.println("Input: " + original);
        System.out.println("Output: " + result);
        System.out.println("Expected: " + expected);
        System.out.println("Status: " + (passed ? "PASS" : "FAIL"));
        System.out.println();
    }
}
