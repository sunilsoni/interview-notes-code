package com.interview.notes.code.year.y2025.march.caspex.test13;

/*
WORKING:


### **Largest Odd Number**

You are given a string `S` that has lowercase letters and numbers.
Your task is to compare the number groupings and print the largest odd number.
If there is no odd number present in the given string, return `-1`.

---

### **Input**
The input contains a string `S` that has lowercase letters and numbers.

---

### **Output**
Return the largest odd number of all numeric groupings.

---

### **Constraints**
- 1 ≤ length of `S` ≤ 500

---

### **Function Signature**
```java
public static int solve(String S);
```

---

### **Example #1**

**Input:**
```
gt12cty65mt1
```

**Output:**
```
65
```

---

### **Example #2**

**Input:**
```
mkf43kd1cmk32klmv123
```

**Output:**
```
123
```

---

 */
public class LargestOddNumber {

    // Function to find the largest odd number from numeric groupings in string S
    public static int solve(String S) {
        // Use regex to split S into groups of digits
        String[] numberGroups = S.split("[^0-9]+");
        return java.util.Arrays.stream(numberGroups)
                .filter(s -> !s.isEmpty())
                .map(Integer::parseInt)
                .filter(num -> num % 2 != 0)
                .max(Integer::compare)
                .orElse(-1);
    }

    // Main method to test various cases including edge cases and large data input
    public static void main(String[] args) {
        int passCount = 0, failCount = 0;

        // Test cases
        Object[][] tests = {
                // {test input, expected output}
                {"gt12cty65mt1", 65},
                {"mkf43kd1cmk32klmv123", 123},
                {"abcdef", -1},              // No digits
                {"24680", -1},               // All even numbers
                {"13579", 13579},            // Single odd number group that is the max
                {"a1b2c3d5", 5},             // Multiple small numbers, maximum is 5
                {"abc0023xyz005", 23},       // Leading zeros and multiple groups
                {"1234567890", 123456789},   // Only odd grouping is the first 9 digits (if any)
                // Large input: repeat a pattern many times
                {new String(new char[500]).replace("\0", "a1b2c3d5e7"), 7}
        };

        for (Object[] test : tests) {
            String input = (String) test[0];
            int expected = (int) test[1];
            int result = solve(input);
            if (result == expected) {
                System.out.println("PASS: Input: \"" + input + "\" -> Expected: " + expected + ", Got: " + result);
                passCount++;
            } else {
                System.out.println("FAIL: Input: \"" + input + "\" -> Expected: " + expected + ", Got: " + result);
                failCount++;
            }
        }

        System.out.println("\nTotal Passed: " + passCount + " | Total Failed: " + failCount);
    }
}
