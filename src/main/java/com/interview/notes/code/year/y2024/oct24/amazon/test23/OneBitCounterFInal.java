package com.interview.notes.code.year.y2024.oct24.amazon.test23;

import java.util.ArrayList;
import java.util.List;

/*
FINAL WORKING


### Problem Statement
Given an integer \( n \), determine the following:

1. How many 1-bits are in its binary representation?
2. The number \( n \)'s binary representation has \( k \) significant bits indexed from 1 to \( k \). What are the respective positions of each 1-bit, in ascending order?

The result array should contain:
- The count of 1-bits as the first element.
- The positions of each 1-bit, from left to right in the binary representation, starting from position 1.

#### Example
**Input:**
```
161
```

**Binary Representation of 161:**
```
Binary:    1 0 1 0 0 0 0 1
Location:  1 2 3 4 5 6 7 8
```

**Output:**
```
3
1
3
8
```

**Explanation:**
- The integer \( n = 161 \) in binary is \( 10100001 \).
- There are 3 '1' bits, located at positions 1, 3, and 8.

The return array is `[3, 1, 3, 8]`, where:
1. `3` is the count of 1-bits.
2. Positions `1`, `3`, and `8` are where the 1-bits are located.

---

### Function Description
Complete the function `getOneBits` with the following parameter:

- **Parameter:**
  - `int n`: the integer input

- **Returns:**
  - `List<Integer>`: A list where the first element is the count of 1-bits, followed by their positions.

### Constraints
- \( 1 < n < 10^9 \)

---

### Sample Case

#### Sample Case 0
**Input:**
```
161
```

**Output:**
```
3
1
3
8
```


 */

/**
 * This program counts the number of '1' bits in the binary representation of a given integer
 * and identifies their positions from left to right, starting from position 1.
 */
public class OneBitCounterFInal {

    /**
     * Calculates the number of '1' bits in the binary representation of the integer n
     * and their respective positions.
     *
     * @param n the integer input
     * @return a list where the first element is the count of '1' bits,
     * followed by their positions in ascending order
     */
    public static List<Integer> getOneBits(int n) {
        if (n <= 1 || n >= 1_000_000_000) {
            throw new IllegalArgumentException("n must be between 2 and 1,000,000,000 (exclusive)");
        }

        List<Integer> result = new ArrayList<>();
        String binaryStr = Integer.toBinaryString(n);
        int count = 0;
        List<Integer> positions = new ArrayList<>();
        int k = binaryStr.length(); // Total number of bits in the binary representation

        // Iterate over the binary string to find '1' bits and their positions
        for (int i = 0; i < k; i++) {
            if (binaryStr.charAt(i) == '1') {
                count++;
                positions.add(i + 1); // Positions start from 1
            }
        }

        // Add the count of '1' bits and their positions to the result list
        result.add(count);
        result.addAll(positions);
        return result;
    }

    /**
     * Tests the getOneBits function with the provided test cases.
     *
     * @param n        the integer input
     * @param expected the expected output list
     */
    public static void testGetOneBits(int n, List<Integer> expected) {
        try {
            List<Integer> actual = getOneBits(n);
            if (actual.equals(expected)) {
                System.out.println("Test case n = " + n + " PASSED");
            } else {
                System.out.println("Test case n = " + n + " FAILED");
                System.out.println("Expected: " + expected);
                System.out.println("Actual:   " + actual);
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Test case n = " + n + " FAILED with exception: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        // Sample test case
        int n1 = 161;
        List<Integer> expected1 = new ArrayList<>();
        expected1.add(3);
        expected1.add(1);
        expected1.add(3);
        expected1.add(8);
        testGetOneBits(n1, expected1);

        // Additional test case: n = 2
        int n2 = 2;
        List<Integer> expected2 = new ArrayList<>();
        expected2.add(1);
        expected2.add(1);
        testGetOneBits(n2, expected2);

        // Additional test case: n = 15
        int n3 = 15;
        List<Integer> expected3 = new ArrayList<>();
        expected3.add(4);
        expected3.add(1);
        expected3.add(2);
        expected3.add(3);
        expected3.add(4);
        testGetOneBits(n3, expected3);

        // Large input test case: n = 999,999,999
        int n4 = 999_999_999;
        try {
            List<Integer> result4 = getOneBits(n4);
            System.out.println("Test case n = " + n4 + " executed, output size: " + result4.size());
        } catch (IllegalArgumentException e) {
            System.out.println("Test case n = " + n4 + " FAILED with exception: " + e.getMessage());
        }

        // Edge case: n = 3
        int n5 = 3;
        List<Integer> expected5 = new ArrayList<>();
        expected5.add(2);
        expected5.add(1);
        expected5.add(2);
        testGetOneBits(n5, expected5);
    }
}
