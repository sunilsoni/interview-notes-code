package com.interview.notes.code.year.y2025.feb25.paypal.test1;

import java.util.*;

/*
WORKING


### **Minimum Unique Array Sum**

#### **Description**
Given an array, increment any duplicate elements until all elements are unique. The sum of the elements must be the minimum possible.

#### **Example**
```
arr = [3, 2, 1, 2, 7]
```
The minimum unique array becomes:
```
arr_unique = [3, 2, 1, 4, 7]
```
The sum of elements in the new array is:
```
3 + 2 + 1 + 4 + 7 = 17
```

---

### **Function Description**
Complete the `getMinimumUniqueSum` function in the editor below.

**Function Signature:**
```java
public static int getMinimumUniqueSum(List<Integer> arr)
```

**Parameters:**
- `int arr[n]`: An array of integers to process.

**Returns:**
- `int`: The sum of the resulting unique array.

---

### **Constraints**
- \( 1 \leq n \leq 2000 \)
- \( 1 \leq arr[i] \leq 3000 \) where \( 0 \leq i < n \)

---

### **Sample Input**
```
3
1
2
2
```

### **Sample Output**
```
6
```

### **Explanation**
The duplicate element `2` must be addressed. Increment one of the twos by `1`, resulting in:
```
arr_unique = [1, 2, 3]
```
The sum of elements in the new array is:
```
1 + 2 + 3 = 6
```

 */
public class MinimumUniqueArraySumSolution {

    /**
     * Given a list of integers, this method increments duplicates
     * so that all elements become unique and returns the minimum possible sum.
     *
     * @param arr List of integers to be processed.
     * @return The sum of the resulting unique array.
     */
    public static int getMinimumUniqueSum(List<Integer> arr) {
        // Use a HashSet to track used numbers
        Set<Integer> used = new HashSet<>();
        int sum = 0;

        // Process each number in the original order
        for (int num : arr) {
            // Increment until the number is unique
            while (used.contains(num)) {
                num++;
            }
            used.add(num);
            sum += num;
        }
        return sum;
    }

    /**
     * Main method for testing the getMinimumUniqueSum function.
     * It runs several test cases and prints PASS/FAIL messages.
     */
    public static void main(String[] args) {
        // Create a resizable list of test cases
        List<TestCase> testCases = new ArrayList<>(Arrays.asList(
                // Sample test case from the problem statement.
                new TestCase(Arrays.asList(1, 2, 2), 6),
                // Example from the description.
                new TestCase(Arrays.asList(3, 2, 1, 2, 7), 17),
                // Edge case: single element.
                new TestCase(Arrays.asList(1), 1),
                // Edge case: all elements are duplicates.
                new TestCase(Arrays.asList(1, 1, 1, 1), 10), // Expected: 1, 2, 3, 4
                // Additional case: mixed duplicates.
                new TestCase(Arrays.asList(5, 3, 3, 3, 2, 2, 1), 28)
        ));

        // Large data test: 2000 elements, all are 1.
        List<Integer> largeTest = new ArrayList<>();
        for (int i = 0; i < 2000; i++) {
            largeTest.add(1);
        }
        // The expected unique sequence is 1, 2, 3, ..., 2000
        // Its sum is (2000 * (2000 + 1)) / 2 = 2001000.
        testCases.add(new TestCase(largeTest, 2001000));

        // Run each test case and output results
        boolean allPassed = true;
        for (int i = 0; i < testCases.size(); i++) {
            TestCase tc = testCases.get(i);
            int result = getMinimumUniqueSum(tc.input);
            if (result == tc.expected) {
                System.out.println("Test case " + (i + 1) + " PASSED");
            } else {
                System.out.println("Test case " + (i + 1) + " FAILED: Expected " + tc.expected + " but got " + result);
                allPassed = false;
            }
        }

        if (allPassed) {
            System.out.println("All test cases passed.");
        } else {
            System.out.println("Some test cases failed.");
        }
    }

    // Helper class to store test cases
    static class TestCase {
        List<Integer> input;
        int expected;

        TestCase(List<Integer> input, int expected) {
            this.input = input;
            this.expected = expected;
        }
    }
}