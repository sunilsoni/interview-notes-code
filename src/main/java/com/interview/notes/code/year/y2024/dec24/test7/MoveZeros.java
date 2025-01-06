package com.interview.notes.code.year.y2024.dec24.test7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
WORKING
### **Problem Description**
**Moving Zeros to End**

Given an integer array `nums[]`, move all 0's to the end of it while maintaining the relative order of the non-zero elements.

**Note:** You must do this **in-place** without making a copy of the array.

---

### **Input**
- The first line of input contains an integer **N**, representing the size of the array.
- The second line of input contains **N space-separated integers**, representing the array elements.

---

### **Output**
- The updated array after moving `0`s to the end of it.

---

### **Constraints**
- \( 1 \leq N \leq 104 \)
- \( -231 \leq \text{nums}[i] \leq 231 - 1 \)

---

### **Examples**

#### **Example #1**
**Input**:
```
5
0 1 0 3 12
```

**Output**:
```
1 3 12 0 0
```

#### **Example #2**
**Input**:
```
1
0
```

**Output**:
```
0
```

---

### **Function Definition**
```java
/*
* Implement method/function with name 'solve' below.
* The function accepts following as parameters.
* 1. nums is of type List<Integer>.
* return List<Integer>.

 */
public class MoveZeros {
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

    public static void main(String[] args) {
        // Test cases
        List<TestCase> testCases = new ArrayList<>();

        // Test case 1: Standard case
        testCases.add(new TestCase(
                Arrays.asList(0, 1, 0, 3, 12),
                Arrays.asList(1, 3, 12, 0, 0)
        ));

        // Test case 2: Single element
        testCases.add(new TestCase(
                Arrays.asList(0),
                Arrays.asList(0)
        ));

        // Test case 3: No zeros
        testCases.add(new TestCase(
                Arrays.asList(1, 2, 3),
                Arrays.asList(1, 2, 3)
        ));

        // Test case 4: All zeros
        testCases.add(new TestCase(
                Arrays.asList(0, 0, 0),
                Arrays.asList(0, 0, 0)
        ));

        // Test case 5: Large input
        List<Integer> largeInput = new ArrayList<>();
        List<Integer> largeExpected = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            if (i % 2 == 0) {
                largeInput.add(0);
                largeExpected.add(0);
            } else {
                largeInput.add(i);
                largeExpected.set((i - 1) / 2, i);
            }
        }
        testCases.add(new TestCase(largeInput, largeExpected));

        // Run tests
        for (int i = 0; i < testCases.size(); i++) {
            TestCase tc = testCases.get(i);
            List<Integer> input = new ArrayList<>(tc.input);
            List<Integer> result = solve(input);

            boolean passed = result.equals(tc.expected);
            System.out.printf("Test Case %d: %s\n", i + 1, passed ? "PASS" : "FAIL");

            if (!passed) {
                System.out.println("Input: " + tc.input);
                System.out.println("Expected: " + tc.expected);
                System.out.println("Got: " + result);
            }
        }
    }

    static class TestCase {
        List<Integer> input;
        List<Integer> expected;

        TestCase(List<Integer> input, List<Integer> expected) {
            this.input = input;
            this.expected = expected;
        }
    }
}
