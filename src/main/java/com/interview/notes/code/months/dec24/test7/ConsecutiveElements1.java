package com.interview.notes.code.months.dec24.test7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
WORKING


### **Not Increasing, Not Decreasing Problem**

---

### **Description**
You are given an array consisting of **distinct integers**. Print the **minimum number of elements** to be removed such that no three consecutive elements in the array are either **increasing** or **decreasing**.

---

### **Input**
1. The first line of input contains an integer \( n \), representing the size of the array.
2. The second line of input contains \( n \) space-separated integers, representing the array elements.

---

### **Output**
Print the **minimum number of elements** to be removed such that no three consecutive elements in the array are either increasing or decreasing.

---

### **Constraints**
- \( 1 \leq n \leq 10^4 \)

---

### **Example #1**
**Input**:
```
5
1 2 4 1 2
```

**Output**:
```
1
```

**Explanation**:
We need to remove one element (4).
Transformation:
`{1, 2, 4, 1, 2} -> {1, 2, 1, 2}`.
Now, there are no three consecutive elements that are either increasing or decreasing.

---

### **Example #2**
**Input**:
```
4
1 2 3 5
```

**Output**:
```
2
```

**Explanation**:
We need to remove two elements. There are several ways of doing this:
1) \({1, 2, 3, 5} \to {3, 5}\)
2) \({1, 2, 3, 5} \to {2, 5}\)
... and other valid solutions.

---

### **Function Definition**
```java
/*
* Implement method/function with name 'solve' below.
* The function accepts following as parameters.
* 1. ar is of type List<Integer>.
* return int.

 */
public class ConsecutiveElements1 {
    public static int solve(List<Integer> ar) {
        if (ar == null || ar.size() <= 2) {
            return 0;
        }

        // Dynamic programming approach
        int n = ar.size();
        int[][] dp = new int[n][2]; // dp[i][0] - keep element, dp[i][1] - remove element

        // Initialize with max value
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], n);
        }

        // Base cases
        dp[0][0] = 0; // keep first element
        dp[0][1] = 1; // remove first element
        dp[1][0] = 0; // keep second element
        dp[1][1] = 1; // remove second element

        for (int i = 2; i < n; i++) {
            // Check if keeping current element creates violation
            if (!isViolation(ar, i)) {
                dp[i][0] = Math.min(dp[i - 1][0], dp[i - 1][1]);
            }

            // Always possible to remove current element
            dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][1]) + 1;
        }

        return Math.min(dp[n - 1][0], dp[n - 1][1]);
    }

    private static boolean isViolation(List<Integer> ar, int index) {
        if (index < 2) return false;

        // Check if three consecutive elements are increasing
        if (ar.get(index - 2) < ar.get(index - 1) && ar.get(index - 1) < ar.get(index)) {
            return true;
        }

        // Check if three consecutive elements are decreasing
        if (ar.get(index - 2) > ar.get(index - 1) && ar.get(index - 1) > ar.get(index)) {
            return true;
        }

        return false;
    }

    public static void main(String[] args) {
        // Test cases
        List<TestCase> testCases = new ArrayList<>();

        // Test case 1: Example from problem
        testCases.add(new TestCase(
                Arrays.asList(1, 2, 4, 1, 2),
                1
        ));

        // Test case 2: Another example from problem
        testCases.add(new TestCase(
                Arrays.asList(1, 2, 3, 5),
                2
        ));

        // Test case 3: Empty array
        testCases.add(new TestCase(
                new ArrayList<>(),
                0
        ));

        // Test case 4: Array with two elements
        testCases.add(new TestCase(
                Arrays.asList(1, 2),
                0
        ));

        // Test case 5: Strictly decreasing
        testCases.add(new TestCase(
                Arrays.asList(5, 4, 3, 2, 1),
                2
        ));

        // Test case 6: Large input
        List<Integer> largeInput = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            largeInput.add(i);
        }
        testCases.add(new TestCase(largeInput, 334));

        // Run tests
        for (int i = 0; i < testCases.size(); i++) {
            TestCase tc = testCases.get(i);
            int result = solve(tc.input);
            boolean passed = result == tc.expected;

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
        int expected;

        TestCase(List<Integer> input, int expected) {
            this.input = input;
            this.expected = expected;
        }
    }
}
