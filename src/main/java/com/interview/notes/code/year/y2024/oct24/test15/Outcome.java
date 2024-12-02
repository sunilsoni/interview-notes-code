package com.interview.notes.code.year.y2024.oct24.test15;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Outcome {

    /**
     * Implement method/function with name 'solve' below.
     * The function accepts following as parameters.
     * 1. n is of type int.
     * 2. ar is of type List<Integer>.
     * return String.
     */
    public static String solve(int n, List<Integer> ar) {
        // Prices for digits 1 to 9 are in ar[0] to ar[8]
        // Find the minimum price and corresponding digits
        int minPrice = Integer.MAX_VALUE;
        for (int price : ar) {
            if (price < minPrice) {
                minPrice = price;
            }
        }

        // Find the maximum number of digits
        int maxLen = n / minPrice;
        if (maxLen == 0) {
            return "-1";
        }

        StringBuilder sb = new StringBuilder();
        int remaining = n;

        for (int pos = 0; pos < maxLen; pos++) {
            // Try to place the largest possible digit at this position
            for (int digit = 9; digit >= 1; digit--) {
                int price = ar.get(digit - 1);
                // Calculate remaining digits after this position
                int remainingDigits = maxLen - pos - 1;
                if (remaining >= price + remainingDigits * minPrice) {
                    sb.append(digit);
                    remaining -= price;
                    break;
                }
            }
        }

        return sb.toString();
    }

    // Method to run all test cases
    public static void main(String[] args) {
        List<TestCase> testCases = new ArrayList<>();

        // Test Case 1: Example #1
        testCases.add(new TestCase(
                3,
                Arrays.asList(1, 1, 1, 1, 1, 1, 1, 1, 1),
                "999"
        ));

        // Test Case 2: Louie cannot buy any balloon
        testCases.add(new TestCase(
                0,
                Arrays.asList(1, 1, 1, 1, 1, 1, 1, 1, 1),
                "-1"
        ));

        // Test Case 3: Example #2
        // Note: The original Example #2 was incomplete. Creating based on possible understanding.
        // Assuming ar = [2, 1, 2, 2, 2, 2, 2, 2, 2] for digits 1-9
        // Best is to buy one '2' (price 1) and then use remaining to buy '1's
        testCases.add(new TestCase(
                3,
                Arrays.asList(2, 1, 2, 2, 2, 2, 2, 2, 2),
                "221"
        ));

        // Test Case 4: All digits have different prices
        testCases.add(new TestCase(
                10,
                Arrays.asList(2, 3, 4, 5, 6, 7, 8, 9, 10),
                "99999"
        )); // Best to buy five '2's: total price 10, but '9's are expensive. Check the logic

        // Adjusted Test Case 4:
        // To ensure correct expectations, let's redefine Test Case 4
        testCases.add(new TestCase(
                10,
                Arrays.asList(2, 3, 4, 5, 6, 7, 8, 9, 10),
                "55555" // Minimum price is 2 (digit '1'), maximum digits =5
                // But to maximize the number, prefer higher digits. Adjust expectation
                // Let's compute manually:
                // With n=10, min price=2 (for digit '1'), max digits=5
                // Now, try to maximize digits from left
                // At first position, try digit '9' (price10). 10 >=10 + 2*(5-1)=10+8=18? No
                // Try '8' (price9). 10 >=9 +8=17? No
                // ...
                // Finally, '1' (price2). Yes. So all '1's: "11111"
                // Hence, expected "11111"
        ));

        // Corrected Test Case 4 with expected "11111"
        testCases.set(3, new TestCase(
                10,
                Arrays.asList(2, 3, 4, 5, 6, 7, 8, 9, 10),
                "11111"
        ));

        // Test Case 5: Large n with all digits price 1
        List<Integer> arLarge = new ArrayList<>(Collections.nCopies(9, 1));
        // n = 1000000, expected "999...9" (1 million '9's)
        StringBuilder expectedLarge = new StringBuilder();
        for (int i = 0; i < 1000000; i++) expectedLarge.append('9');
        testCases.add(new TestCase(
                1000000,
                arLarge,
                expectedLarge.toString()
        ));

        // Test Case 6: Large n with varied prices
        List<Integer> arLargeVaried = Arrays.asList(7, 6, 5, 4, 3, 2, 1, 2, 3); // Digit 7 cheapest
        // n=1000000, max digits=1000000 /1=1000000
        // Expected all '9's? Not necessarily. Should be all '7's since digit 7 has price1
        // To maximize number, prefer larger digits. But digit 9 costs3:
        // At each position, can we place '9'? Check if remaining >=3 + (maxLen - pos -1)*1
        // Yes, since 1000000 >=3 + 999999*1 =>1000000 >=1000002? No
        // So cannot place '9'
        // Next, '8' costs2: 1000000 >=2 + 999999*1 =>1000000 >=1000001? No
        // '7' costs1: Yes. So all '7's
        StringBuilder expectedLargeVaried = new StringBuilder();
        for (int i = 0; i < 1000000; i++) expectedLargeVaried.append('7');
        testCases.add(new TestCase(
                1000000,
                arLargeVaried,
                expectedLargeVaried.toString()
        ));

        // Test Case 7: Cannot buy any digit
        testCases.add(new TestCase(
                5,
                Arrays.asList(6, 7, 8, 9, 10, 11, 12, 13, 14),
                "-1"
        ));

        // Test Case 8: Multiple digit options with same price
        testCases.add(new TestCase(
                5,
                Arrays.asList(1, 1, 1, 1, 1, 1, 1, 1, 1),
                "99999"
        ));

        // Test Case 9: Optimal mix of digits
        testCases.add(new TestCase(
                9,
                Arrays.asList(5, 4, 3, 2, 1, 2, 3, 4, 5),
                "555555555" // Max digits=9 with digit '5'
        ));

        // Test Case 10: Single digit purchase
        testCases.add(new TestCase(
                2,
                Arrays.asList(3, 4, 5, 6, 7, 8, 9, 10, 11),
                "-1"
        ));

        // Test Case 11: Multiple high digits affordable
        testCases.add(new TestCase(
                15,
                Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9),
                "999999999999999"
        ));

        // Test Case 12: Buying higher digits first
        testCases.add(new TestCase(
                7,
                Arrays.asList(1, 1, 1, 1, 2, 2, 3, 4, 5),
                "7777777"
        ));

        // Test Case 13: Alternate prices
        testCases.add(new TestCase(
                20,
                Arrays.asList(5, 4, 3, 2, 1, 2, 3, 4, 5),
                "55555555555555555555" // 20 digits of '5'
        ));

        // Test Case 14: Maximum digit selection with budget
        testCases.add(new TestCase(
                11,
                Arrays.asList(1, 1, 1, 1, 1, 1, 1, 1, 2),
                "99999999999" // 11 digits, '9' costs2, max digits=5 (10) with 1 remaining, not enough
                // Correct expected is '99999' since 5 '9's cost10, 1 dollar left cannot buy more
        ));

        // Corrected Test Case 14
        testCases.set(13, new TestCase(
                11,
                Arrays.asList(1, 1, 1, 1, 1, 1, 1, 1, 2),
                "99999" // 5 '9's cost10, remaining 1 can buy '8'
                // So possible '999998'
                // Let's evaluate:
                // maxLen=11 /1=11
                // At first 5 positions, place '9's using 2 per '9'.
                // However '9' costs2: but n=11
                // Max number of '9's we can buy =5 (10), remaining=1
                // But to maximize digits, prefer more digits.
                // Actually, minimum price is1, maxLen=11
                // At each position, try to put '9's as much as possible:
                // For positions 0 to10:
                // For first 5 positions, put '9's (cost2 each), remaining=1 for last 6 digits: not possible
                // Hence, first 5 '9's, last 6 '8's cannot be bought
                // So possible to have '99999'

        ));

        // Test Case 15: Exact budget match
        testCases.add(new TestCase(
                9,
                Arrays.asList(1, 1, 1, 1, 1, 1, 1, 1, 1),
                "999999999"
        ));

        // Initialize test result counters
        int passed = 0;
        int failed = 0;

        // Iterate and test each case
        for (int i = 0; i < testCases.size(); i++) {
            TestCase tc = testCases.get(i);
            String output = solve(tc.n, tc.ar);
            if (output.equals(tc.expected)) {
                System.out.println("Test Case " + (i + 1) + ": PASS");
                passed++;
            } else {
                System.out.println("Test Case " + (i + 1) + ": FAIL");
                System.out.println("  Input: n=" + tc.n + ", ar=" + tc.ar);
                System.out.println("  Expected: " + tc.expected);
                System.out.println("  Got:      " + output);
                failed++;
            }
        }

        // Summary
        System.out.println("\nTotal Test Cases: " + testCases.size());
        System.out.println("Passed: " + passed);
        System.out.println("Failed: " + failed);
    }

    // TestCase class to hold each test case
    static class TestCase {
        int n;
        List<Integer> ar;
        String expected;

        TestCase(int n, List<Integer> ar, String expected) {
            this.n = n;
            this.ar = ar;
            this.expected = expected;
        }
    }
}
