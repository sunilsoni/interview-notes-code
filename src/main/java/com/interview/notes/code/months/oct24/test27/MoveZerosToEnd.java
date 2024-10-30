package com.interview.notes.code.months.oct24.test27;

import java.util.*;

/*
WORKING


### Moving Zeros to End

Given an integer array `nums[]`, move all `0`s to the end of it while maintaining the relative order of the non-zero elements.

**Note:** You must do this in-place without making a copy of the array.

---

**Input**

- The first line of input contains an integer `N`, representing the size of the array.
- The second line of input contains `N` space-separated integers, representing the array elements.

**Output**

- The updated array after moving `0`s to the end of it.

**Constraints**

- \( 1 \leq N \leq 10^4 \)
- \(-231 \leq nums[i] \leq 231 - 1\)

**Example #1**

- **Input**:
  ```
  5
  0 1 0 3 12
  ```

- **Output**:
  ```
  1 3 12 0 0
  ```

**Example #2**

- **Input**:
  ```
  1
  0
  ```

- **Output**:
  ```
  0
  ```

---

### Function Signature

```java
/*
 * Implement method/function with name 'solve' below.
 * The function accepts the following as parameters:
 *   1. nums is of type List<Integer>.
 * Returns: List<Integer>.

public static List<Integer> solve(List<Integer> nums){
    // Write your code here

    return; // return type "List<Integer>".
}
```


        */
public class MoveZerosToEnd {
    
    // Solution method to move zeros to end while maintaining order of non-zero elements
    public static List<Integer> solve(List<Integer> nums) {
        if (nums == null || nums.size() <= 1) {
            return nums;
        }
        
        int nonZeroIndex = 0;
        
        // First pass: Move all non-zero elements to the front
        for (int i = 0; i < nums.size(); i++) {
            if (nums.get(i) != 0) {
                nums.set(nonZeroIndex++, nums.get(i));
            }
        }
        
        // Second pass: Fill remaining positions with zeros
        while (nonZeroIndex < nums.size()) {
            nums.set(nonZeroIndex++, 0);
        }
        
        return nums;
    }
    
    // Test method to verify solution
    private static boolean runTest(List<Integer> input, List<Integer> expected) {
        List<Integer> original = new ArrayList<>(input);
        List<Integer> result = solve(input);
        
        if (!result.equals(expected)) {
            System.out.println("FAIL: Input: " + original);
            System.out.println("Expected: " + expected);
            System.out.println("Got: " + result);
            return false;
        }
        return true;
    }
    
    public static void main(String[] args) {
        int testsPassed = 0;
        int totalTests = 0;
        
        // Test Case 1: Basic test
        totalTests++;
        List<Integer> test1 = Arrays.asList(0, 1, 0, 3, 12);
        List<Integer> expected1 = Arrays.asList(1, 3, 12, 0, 0);
        if (runTest(new ArrayList<>(test1), expected1)) testsPassed++;
        
        // Test Case 2: Single element
        totalTests++;
        List<Integer> test2 = Arrays.asList(0);
        List<Integer> expected2 = Arrays.asList(0);
        if (runTest(new ArrayList<>(test2), expected2)) testsPassed++;
        
        // Test Case 3: No zeros
        totalTests++;
        List<Integer> test3 = Arrays.asList(1, 2, 3);
        List<Integer> expected3 = Arrays.asList(1, 2, 3);
        if (runTest(new ArrayList<>(test3), expected3)) testsPassed++;
        
        // Test Case 4: All zeros
        totalTests++;
        List<Integer> test4 = Arrays.asList(0, 0, 0);
        List<Integer> expected4 = Arrays.asList(0, 0, 0);
        if (runTest(new ArrayList<>(test4), expected4)) testsPassed++;
        
        // Test Case 5: Large input
        totalTests++;
        List<Integer> test5 = new ArrayList<>();
        List<Integer> expected5 = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            test5.add(i % 2 == 0 ? 0 : i);
            if (i % 2 != 0) {
                expected5.add(i);
            }
        }
        for (int i = 0; i < 5000; i++) {
            expected5.add(0);
        }
        if (runTest(test5, expected5)) testsPassed++;
        
        // Print test results
        System.out.println("\nTest Results:");
        System.out.println("Tests Passed: " + testsPassed + "/" + totalTests);
        if (testsPassed == totalTests) {
            System.out.println("All tests PASSED!");
        } else {
            System.out.println("Some tests FAILED!");
        }
    }
}