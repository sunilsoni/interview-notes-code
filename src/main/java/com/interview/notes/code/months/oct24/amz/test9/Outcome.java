package com.interview.notes.code.months.oct24.amz.test9;

import java.util.*;

/*
FInal:13/16 Pasaesd

Mini-Max Numbers
Your little brother Louie wants to challenge you in Mathematics. Louie wants you to tell the smallest and the biggest numbers such that both contain d digits, and the sum of these digits is precisely k.
Input
The first line of input contains an integer d, representing the number of digits in each resulting number.
The second line of input contains an integer k, representing the sum of the digits.
Output
Print two space-separated integers: The smallest and the biggest numbers that satisfy the given conditions. If there are no such numbers print "-1 -1" without the quotes.
Constraints
1 ≤ d≤100
0≤k≤ 900
Example #1
Input
2
2
Output
11 20
Explanation: The smallest two-digit number with the sum of digits 2 is 11. The biggest one is 20.


Example #2
Input
2
1
Output
10 10
Explanation: The only number that satisfies the given conditions is 10. So it is the smallest as well as the biggest number.


 */
class Outcome {
    public static List<Long> solve(int d, int k) {
        if (d <= 0 || k < 0 || d > 19 || k > 9 * d) {
            return Arrays.asList(-1L, -1L);
        }
        if (d > 1 && k == 0) {
            return Arrays.asList(-1L, -1L);
        }

        long smallest = findSmallest(d, k);
        long largest = findLargest(d, k);

        if (smallest == -1L || largest == -1L) {
            return Arrays.asList(-1L, -1L);
        }

        return Arrays.asList(smallest, largest);
    }

    private static long findSmallest(int d, int k) {
        if (k == 0) {
            if (d == 1) {
                return 0L;
            } else {
                return -1L;
            }
        }
        if (k > 9 * d) {
            return -1L;
        }
        int[] res = new int[d];
        k -= 1; // Reserve at least 1 for the first digit to avoid leading zero

        for (int i = d - 1; i > 0; i--) {
            if (k >= 9) {
                res[i] = 9;
                k -= 9;
            } else {
                res[i] = k;
                k = 0;
            }
        }
        res[0] = k + 1; // Add back the reserved 1

        StringBuilder sb = new StringBuilder();
        for (int digit : res) {
            sb.append(digit);
        }
        String numStr = sb.toString();
        try {
            return Long.parseLong(numStr);
        } catch (NumberFormatException e) {
            return -1L;
        }
    }

    private static long findLargest(int d, int k) {
        if (k == 0) {
            if (d == 1) {
                return 0L;
            } else {
                return -1L;
            }
        }
        if (k > 9 * d) {
            return -1L;
        }
        int[] res = new int[d];

        for (int i = 0; i < d; i++) {
            if (k >= 9) {
                res[i] = 9;
                k -= 9;
            } else {
                res[i] = k;
                k = 0;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int digit : res) {
            sb.append(digit);
        }
        String numStr = sb.toString();
        try {
            return Long.parseLong(numStr);
        } catch (NumberFormatException e) {
            return -1L;
        }
    }

    public static void main(String[] args) {
        testCases();
    }

    private static void testCases() {
        // Test case 1
        assertResult(2, 2, 11L, 20L);

        // Test case 2
        assertResult(2, 1, 10L, 10L);

        // Edge cases
        assertResult(1, 9, 9L, 9L);
        assertResult(3, 27, 999L, 999L);

        // Invalid inputs
        assertResult(0, 5, -1L, -1L);
        assertResult(5, -1, -1L, -1L);
        assertResult(5, 901, -1L, -1L);

        // Large inputs within long capacity
        assertResult(18, 162, 999999999999999999L, 999999999999999999L);

        // Inputs exceeding long capacity
        assertResult(19, 171, -1L, -1L); // Should return -1 due to overflow

        // Zero sum cases
        assertResult(1, 0, 0L, 0L);
        assertResult(2, 0, -1L, -1L);

        System.out.println("All test cases passed!");
    }

    private static void assertResult(int d, int k, long expectedSmallest, long expectedLargest) {
        List<Long> result = solve(d, k);
        if (!result.get(0).equals(expectedSmallest) || !result.get(1).equals(expectedLargest)) {
            throw new AssertionError(String.format(
                "Test case failed for d=%d, k=%d. Expected: [%d, %d], Got: [%d, %d]",
                d, k, expectedSmallest, expectedLargest, result.get(0), result.get(1)));
        }
    }
}
