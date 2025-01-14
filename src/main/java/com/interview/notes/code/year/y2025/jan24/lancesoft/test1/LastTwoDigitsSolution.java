package com.interview.notes.code.year.y2025.jan24.lancesoft.test1;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
2 failingin:
### Problem: Last Two Digits

You are given an array consisting of **n** integers. Print the last two digits of the product of its array values.

---

### Input
- The first line of input contains an integer **n**, representing the number of elements in the given array.
- The second line of input contains **n** space-separated integers, representing the elements of the given array.

### Output
Print the last two digits of the product of the array values.
**Note:** Always print two digits.

### Constraints
- \(1 \leq n \leq 100\)
- \(1 \leq ar[i] \leq 100\)

---

### Example #1

**Input:**
```
2
25 10
```

**Output:**
```
50
```

**Explanation:**
Product = \(25 \times 10 = 250\) → Last two digits: **50**

---

### Example #2

**Input:**
```
3
2 4 5
```

**Output:**
```
40
```

**Explanation:**
Product = \(2 \times 4 \times 5 = 40\) → Last two digits: **40**

---

 */
public class LastTwoDigitsSolution {

    // 1. solve() using BigInteger for full product before extracting last two digits
    public static int solve(List<Integer> ar) {
        BigInteger product = BigInteger.ONE;
        for (int val : ar) {
            if (val < 1 || val > 100) {
                // optionally throw exception or handle invalid input
                // for now, continue or skip
            }
            product = product.multiply(BigInteger.valueOf(val));
        }
        product = product.mod(BigInteger.valueOf(100));
        return product.intValue();
    }

    // 2. Main method for testing (no JUnit, simple pass/fail)
    public static void main(String[] args) {
        // Test case #1
        List<Integer> test1 = Arrays.asList(25, 10);
        int result1 = solve(test1);
        System.out.println(
                String.format("%02d", result1).equals("50")
                        ? "Test1 PASS"
                        : "Test1 FAIL, got " + String.format("%02d", result1)
        );

        // Test case #2
        List<Integer> test2 = Arrays.asList(2, 4, 5);
        int result2 = solve(test2);
        System.out.println(
                String.format("%02d", result2).equals("40")
                        ? "Test2 PASS"
                        : "Test2 FAIL, got " + String.format("%02d", result2)
        );

        // Test case #3: Single element
        List<Integer> test3 = Collections.singletonList(100);
        int result3 = solve(test3);
        System.out.println(
                String.format("%02d", result3).equals("00")
                        ? "Test3 PASS"
                        : "Test3 FAIL, got " + String.format("%02d", result3)
        );

        // Test case #4: Contains zero
        List<Integer> test4 = Arrays.asList(12, 0, 8);
        int result4Val = solve(test4);
        System.out.println(
                String.format("%02d", result4Val).equals("00")
                        ? "Test4 PASS"
                        : "Test4 FAIL, got " + String.format("%02d", result4Val)
        );

        // Test case #5: Large input (100 elements of 100)
        List<Integer> test5 = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            test5.add(100);
        }
        int result5 = solve(test5);
        System.out.println(
                String.format("%02d", result5).equals("00")
                        ? "Test5 PASS"
                        : "Test5 FAIL, got " + String.format("%02d", result5)
        );
    }
}
