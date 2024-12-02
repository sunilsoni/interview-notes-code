package com.interview.notes.code.months.dec24.test2;

/*
WORKING



### Problem Statement

**Complete the `getMin` function below:**

- The function is expected to return an `INTEGER`.
- The function accepts a `STRING` `s` as a parameter.

```java
public static int getMin(String s) {

}
```

---

**Task:**

Given a string `s` that consists of left and right parentheses, `(` and `)`, balance the parentheses by inserting parentheses as necessary. Determine the minimum number of characters that must be inserted.

---

### Examples:

1. **Input:**
   ```
   s = "(()))"
   ```
   **Explanation:**
   - Insert 1 left parenthesis at the left end of the string to get `"((()))"`.
   - The string is balanced after **1 insertion**.

2. **Input:**
   ```
   s = "))(("
   ```
   **Explanation:**
   - Insert 2 left parentheses at the start and 2 right parentheses at the end of the string to get `"((()))(())"`.
   - The string is balanced after **4 insertions**.

---

### Sample Inputs and Outputs:

**Sample Input 1:**

```
STDIN:     Function:
(()))      s = "(()))"
```

**Sample Output 1:**

```
2
```

**Explanation:**
- Insert a `'('` two times at the beginning of the string to make it valid: `"((()))"`.

---

**Sample Case 1:**

**Sample Input 2:**

```
STDIN:     Function:
()()       s = "()()"
```

**Sample Output 2:**

```
0
```

**Explanation:**
- The string is already balanced. No insertions are needed.

---

### Constraints:

1 ≤ length of `s` ≤ 10⁵


 */
public class ParenthesesBalancerWorking {
    public static int getMin(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }

        int leftCount = 0;  // Count of unmatched left parentheses
        int additions = 0;  // Required additions to balance the string

        // First pass: handle right parentheses
        for (char c : s.toCharArray()) {
            if (c == '(') {
                leftCount++;
            } else {
                if (leftCount > 0) {
                    leftCount--;
                } else {
                    additions++;
                }
            }
        }

        // Add remaining unmatched left parentheses
        additions += leftCount;

        return additions;
    }

    // Test method
    public static void main(String[] args) {
        // Test cases
        test("(()))", 1, "Test Case 1");
        test("))((",  4, "Test Case 2");
        test("()()",  0, "Test Case 3");
        test("((()))", 0, "Test Case 4");
        test(")))",   3, "Test Case 5");
        test("(((",   3, "Test Case 6");
        test("",      0, "Empty String");
        test(")",     1, "Single Right");
        test("(",     1, "Single Left");
        
        // Large input test
        StringBuilder largeInput = new StringBuilder();
        for (int i = 0; i < 100000; i++) {
            largeInput.append(i % 2 == 0 ? '(' : ')');
        }
        test(largeInput.toString(), 0, "Large Balanced Input");
    }

    private static void test(String input, int expected, String testName) {
        long startTime = System.nanoTime();
        int result = getMin(input);
        long endTime = System.nanoTime();
        
        boolean passed = result == expected;
        System.out.printf("%-20s: %s (Expected: %d, Got: %d) Time: %.3f ms%n",
                testName,
                passed ? "PASS" : "FAIL",
                expected,
                result,
                (endTime - startTime) / 1_000_000.0);
    }
}
