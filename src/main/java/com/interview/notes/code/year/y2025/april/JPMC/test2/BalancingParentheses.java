package com.interview.notes.code.year.y2025.april.JPMC.test2;

import java.util.*;
import java.util.stream.*;
/*

### **Problem Title: Balancing Parentheses**

#### **Problem Description:**

Given a string composed only of left `'('` and right `')'` parentheses, balance the string by inserting the **minimum number** of parentheses such that it becomes valid. A valid parentheses string must satisfy:
- Every opening `'('` has a corresponding closing `')'`
- The pairs are properly nested

You need to **return the minimum number of insertions** required to make the string valid.

---

### **Function Signature:**

```java
public static int getMin(String s)
```

---

### **Input:**
- A single string `s` consisting only of `'('` and `')'`
- `1 ≤ length of s ≤ 10^5`

---

### **Output:**
- An integer: the minimum number of insertions required to balance the parentheses.

---

### **Examples:**

#### Sample Case 0:
**Input:**
`s = "()))"`
**Output:**
`2`
**Explanation:**
Insert two `'('` at the beginning to get `'((()))'`.

---

#### Sample Case 1:
**Input:**
`s = "()()"`
**Output:**
`0`
**Explanation:**
The string is already valid.

---

#### Additional Example:
**Input:**
`s = "))(("`
**Output:**
`4`
**Explanation:**
Insert 2 `'('` at the start and 2 `')'` at the end to make `'(()())'`.

---

### **Implementation Hint:**
Keep track of:
- Unmatched `'('` (open count)
- Unmatched `')'` that need `'('` to pair with (insert count)

 */
public class BalancingParentheses {

    /**
     * Returns the minimum number of insertions needed to make the parentheses string valid.
     */
    public static int getMin(String s) {
        int open = 0;       // Count of unmatched '('
        int insertions = 0; // Count of inserted '(' for unmatched ')'

        for (char c : s.toCharArray()) {
            if (c == '(') {
                open++;
            } else {
                // c == ')'
                if (open == 0) {
                    // Need to insert '('
                    insertions++;
                    open = 1; // Now we assume we have that '('
                }
                open--; // Match the ')' with one '('
            }
        }

        // If there are unmatched '(' remaining, we need to insert ')' for each
        insertions += open;

        return insertions;
    }

    /**
     * Simple main method to test various cases (PASS/FAIL).
     */
    public static void main(String[] args) {
        // Test with provided samples and some additional edge cases
        test("()))", 2);   // Sample Case 0
        test("()()", 0);   // Sample Case 1
        test("))((", 4);   // Additional Example
        test("(", 1);      // We need one ')' => total insertions = 1
        test(")", 1);      // We need one '(' => total insertions = 1
        test("(()", 1);    // Insert one ')' => total insertions = 1
        test(")))(((", 6); // All mismatched
        test("(((((((", 7); // Only '(' => need 7 ')' for 7 '('
        test(")))))))", 7); // Only ')' => need 7 '('

        // Test with a larger input (to show it handles big cases efficiently)
        // We'll create a random large string of parentheses (length ~100000) and just ensure the code runs.
        String largeInput = generateLargeParenthesesString(100000);
        int resultLarge = getMin(largeInput);
        System.out.println("Large Input Test (length=100000) => Insertions: " + resultLarge);
    }

    /**
     * Helper method to test a single input against an expected output.
     */
    private static void test(String s, int expected) {
        int result = getMin(s);
        String status = (result == expected) ? "PASS" : "FAIL";
        System.out.printf("Input: %s | Output: %d | Expected: %d | %s%n",
                s, result, expected, status);
    }

    /**
     * Generates a large random parentheses string for performance testing.
     */
    private static String generateLargeParenthesesString(int length) {
        Random rand = new Random(0); // Fixed seed for reproducibility
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            // 50-50 chance of '(' or ')'
            sb.append(rand.nextBoolean() ? '(' : ')');
        }
        return sb.toString();
    }
}
