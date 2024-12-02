package com.interview.notes.code.year.y2024.nov24.test3;

/*
WORKING



### Binary Addition

Given two binary strings **a** and **b**, find their sum as a binary string, i.e., perform the binary addition operation on these values.

#### Input
- The first line of input contains a string **a**, denoting the first binary number.
- The second line of input contains a string **b**, denoting the second binary number.

#### Output
- The sum of **a** and **b**, in binary form.

#### Constraints
- \(1 \leq a.\text{length}, b.\text{length} \leq 10^4\)
- **a** and **b** consist only of '0' or '1' characters.
- Each string does not contain leading zeros except for the zero itself.

#### Example #1
**Input**
```
11
1
```

**Output**
```
100
```

**Explanation:** Binary addition of these values is 100.

---

#### Example #2
**Input**
```
1010
1011
```

**Output**
```
10101
```

**Explanation:** Binary addition of these values is 10101.

---

### Function Signature

```java
public static String solve(String a, String b) {
    // Write your code here

    return ""; // return type "String"
}
```
 */
public class BinaryAddition {

    // Core solution method
    public static String solve(String a, String b) {
        StringBuilder result = new StringBuilder();
        int carry = 0;
        int i = a.length() - 1;
        int j = b.length() - 1;

        while (i >= 0 || j >= 0 || carry > 0) {
            int sum = carry;

            if (i >= 0) {
                sum += a.charAt(i) - '0';
                i--;
            }
            if (j >= 0) {
                sum += b.charAt(j) - '0';
                j--;
            }

            result.insert(0, sum % 2);
            carry = sum / 2;
        }

        return result.toString();
    }

    // Test helper method
    private static void testCase(String a, String b, String expected) {
        String result = solve(a, b);
        boolean passed = result.equals(expected);

        System.out.println("Test Case:");
        System.out.println("Input a: " + a);
        System.out.println("Input b: " + b);
        System.out.println("Expected: " + expected);
        System.out.println("Got: " + result);
        System.out.println("Status: " + (passed ? "PASS" : "FAIL"));
        System.out.println("------------------------");
    }

    public static void main(String[] args) {
        // Basic test cases
        testCase("11", "1", "100");
        testCase("1010", "1011", "10101");

        // Edge cases
        testCase("0", "0", "0");
        testCase("1", "1", "10");
        testCase("1111", "1111", "11110");

        // Unequal length test cases
        testCase("1", "1111", "10000");
        testCase("1010", "1", "1011");

        // Large number test cases
        StringBuilder a = new StringBuilder();
        StringBuilder b = new StringBuilder();
        for (int i = 0; i < 10000; i++) {
            a.append("1");
            b.append("1");
        }
        String result = solve(a.toString(), b.toString());
        boolean passed = result.length() == 10001 &&
                result.charAt(0) == '1' &&
                result.substring(1).matches("0+");

        System.out.println("Large Number Test Case (10000 digits):");
        System.out.println("Status: " + (passed ? "PASS" : "FAIL"));
        System.out.println("Result length: " + result.length());
        System.out.println("------------------------");
    }
}