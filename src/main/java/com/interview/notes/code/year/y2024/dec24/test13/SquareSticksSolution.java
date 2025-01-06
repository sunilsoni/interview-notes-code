package com.interview.notes.code.year.y2024.dec24.test13;

/*

**Problem Statement:**

There are two wooden sticks of lengths A and B respectively. Each of them can be cut into shorter sticks of integer lengths. Our goal is to construct the largest possible square. In order to do this, we want to cut the sticks in such a way as to achieve four sticks of the same length (note that there can be some leftover pieces). What is the longest side of square that we can achieve?

Write a function:

```java
class Solution {
    public int solution(int A, int B) {
        // Implement your solution here
    }
}
```

That, given two integers A, B, returns the side length of the largest square that we can obtain. If it is not possible to create any square, the function should return 0.

**Examples:**
1. Given A = 10, B = 21, the function should return 7. We can split the second stick into three sticks of length 7 and shorten the first stick by 3.
2. Given A = 13, B = 11, the function should return 5. We can cut two sticks of length 5 from each of the given sticks.
3. Given A = 2, B = 1, the function should return 0. It is not possible to make any square from the given sticks.
4. Given A = 1, B = 8, the function should return 2. We can cut stick B into four parts.

**Write an efficient algorithm for the following assumptions:**
- A and B are integers within the range [1..1,000,000,000].

---
 */
public class SquareSticksSolution {
    public static int solution(int A, int B) {
        // Maximum possible length is min of (A+B)/4 or max(A,B)
        int maxPossibleLength = Math.min((A + B) / 4, Math.max(A, B));

        // Binary search approach to find the largest possible square side
        int left = 0;
        int right = maxPossibleLength;
        int result = 0;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (canMakeSquare(A, B, mid)) {
                result = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return result;
    }

    private static boolean canMakeSquare(int A, int B, int sideLength) {
        if (sideLength == 0) return false;

        // Calculate how many pieces we can get from each stick
        int piecesFromA = A / sideLength;
        int piecesFromB = B / sideLength;

        // We need total of 4 pieces to make a square
        return (piecesFromA + piecesFromB) >= 4;
    }

    public static void main(String[] args) {
        // Test cases
        testCase(10, 21, 7, "Basic test case 1");
        testCase(13, 11, 5, "Basic test case 2");
        testCase(2, 1, 0, "Edge case - impossible");
        testCase(1, 8, 2, "Basic test case 3");
        testCase(1000000000, 1000000000, 500000000, "Large input test");
        testCase(1, 1, 0, "Minimum input test");
        testCase(4, 4, 2, "Equal sticks test");
    }

    private static void testCase(int A, int B, int expected, String testName) {
        int result = solution(A, B);
        boolean passed = result == expected;
        System.out.printf("Test: %s - %s%n", testName, passed ? "PASS" : "FAIL");
        if (!passed) {
            System.out.printf("Expected: %d, Got: %d%n", expected, result);
        }
    }
}
