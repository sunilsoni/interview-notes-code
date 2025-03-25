package com.interview.notes.code.year.y2025.march.KMM.test2;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
Here’s the properly combined version of the “Sweet Interval” coding problem, including constraints, input/output details, examples, and the method signature:

---

### **Sweet Interval**

**Problem Statement:**

Find an interval in the given array of non-negative integers such that the elements in the interval sum up to a given number `S`.

---

### **Input Format:**

1. The first line of input contains an integer `N`, representing the size of the array `ar[]`.
2. The second line contains `N` space-separated non-negative integers representing the array elements.
3. The third line contains an integer `S`, representing the desired sum.

---

### **Output Format:**

- Print the **starting** and **ending** positions (1-based indexing) of the **first** such occurring interval if the sum equals `S`.
- If no such interval exists, print `-1`.

**Note:** The position of the 1st element of the array should be considered as 1.

---

### **Constraints:**

- \( 1 \leq N \leq 100 \)
- \( 1 \leq ar[i] \leq 200 \)
- The problem should be solved with an \( O(n) \) time complexity.

---

### **Examples:**

#### **Example 1**

**Input:**
```
4
1 3 7 5
10
```

**Output:**
```
2 3
```

**Explanation:**
3 + 7 = 10 → interval is from position **2 to 3**.

---

#### **Example 2**

**Input:**
```
8
1 2 3 4 5 6 7 8
21
```

**Output:**
```
1 6
```

**Explanation:**
1 + 2 + 3 + 4 + 5 + 6 = 21 → interval is from position **1 to 6**.

---

 */
public class SweetInterval {
    public static List<Integer> solve(List<Integer> ar, int S) {
        int start = 0, sum = 0;
        for (int end = 0; end < ar.size(); end++) {
            sum += ar.get(end);
            while (sum > S && start < end) {
                sum -= ar.get(start);
                start++;
            }
            if (sum == S) {
                return Arrays.asList(start + 1, end + 1); // positions 1-based
            }
        }
        return Collections.singletonList(-1);
    }

    // Simple main method for testing all provided and additional test cases
    public static void main(String[] args) {
        List<TestCase> tests = Arrays.asList(
                new TestCase(Arrays.asList(1, 3, 7, 5), 10, Arrays.asList(2, 3)),
                new TestCase(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8), 21, Arrays.asList(1, 6)),
                new TestCase(Arrays.asList(1, 2, 3, 4), 15, Arrays.asList(-1)),
                new TestCase(Arrays.asList(5), 5, Arrays.asList(1, 1)),
                new TestCase(Arrays.asList(1, 2, 3, 4, 5), 9, Arrays.asList(2, 4)),
                // Large data case
                new TestCase(generateLargeList(100, 2), 200, Arrays.asList(1, 100))
        );

        tests.forEach(TestCase::run);
    }

    static List<Integer> generateLargeList(int size, int value) {
        return Collections.nCopies(size, value);
    }

    static class TestCase {
        List<Integer> inputList;
        int sum;
        List<Integer> expected;

        TestCase(List<Integer> inputList, int sum, List<Integer> expected) {
            this.inputList = inputList;
            this.sum = sum;
            this.expected = expected;
        }

        void run() {
            List<Integer> result = solve(inputList, sum);
            System.out.printf("Test with input %s and sum %d: %s\n",
                    inputList.size() > 10 ? "Large Input" : inputList.toString(),
                    sum,
                    result.equals(expected) ? "PASS" : "FAIL - Expected " + expected + ", got " + result);
        }
    }
}
