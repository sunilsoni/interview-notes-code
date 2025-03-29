package com.interview.notes.code.year.y2025.march.caspex.test7;

import java.math.BigInteger;

/*

### **Binary Addition**

Given two binary strings **a** and **b**, find their sum as a binary string.
i.e., perform the binary addition operation on these values.

---

#### **Input**
- The first line of input contains a string `a`, denoting the first binary number.
- The second line of input contains a string `b`, denoting the second binary number.

---

#### **Output**
- The sum of `a` and `b`, in binary form.

---

#### **Constraints**
- 1 <= a.length, b.length <= 104
- a and b consist only of `'0'` or `'1'` characters.
- Each string does not contain leading zeros except for the zero itself.

---

### **Examples**

**Example #1**
**Input:**
```
11
1
```
**Output:**
```
100
```

**Example #2**
**Input:**
```
1010
1011
```
**Output:**
```
10101
```
 */
public class BinaryAddition {

    public static String solve(String a, String b) {
        BigInteger num1 = new BigInteger(a, 2);
        BigInteger num2 = new BigInteger(b, 2);
        return num1.add(num2).toString(2);
    }

    public static void main(String[] args) {
        test("11", "1", "100");
        test("1010", "1011", "10101");
        test("0", "0", "0");
        test("1", "0", "1");
        test("111", "111", "1110");
        // Large data test
        String largeA = "1" + "0".repeat(103);
        String largeB = "1";
        String expectedLarge = "1" + "0".repeat(102) + "1";
        test(largeA, largeB, expectedLarge);
    }

    private static void test(String a, String b, String expected) {
        String result = solve(a, b);
        if (result.equals(expected)) {
            System.out.println("PASS");
        } else {
            System.out.println("FAIL: Expected=" + expected + " but got=" + result);
        }
    }
}
