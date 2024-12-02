package com.interview.notes.code.year.y2024.oct24.test14;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Outcome {

    /**
     * Determines the largest possible number Louie can create with his budget.
     *
     * @param n  The amount of money Louie has.
     * @param ar A list of 9 integers representing the prices of digits 1 through 9.
     * @return The largest possible number as a String or "-1" if no digits can be purchased.
     */
    public static String solve(int n, List<Integer> ar) {
        // Find the minimum price and corresponding digit
        int minPrice = Integer.MAX_VALUE;
        int minDigit = 1;
        for (int i = 0; i < 9; i++) {
            if (ar.get(i) < minPrice) {
                minPrice = ar.get(i);
                minDigit = i + 1; // Digits are from 1 to 9
            }
        }

        // Calculate the maximum number of digits that can be bought
        int maxDigits = n / minPrice;
        if (maxDigits == 0) {
            return "-1";
        }

        // Initialize the number with the smallest digit
        StringBuilder number = new StringBuilder();
        for (int i = 0; i < maxDigits; i++) {
            number.append(minDigit);
        }

        int remaining = n - (maxDigits * minPrice);

        // Attempt to replace each digit with the largest possible digit
        for (int i = 0; i < maxDigits; i++) {
            // Check from digit 9 to digit+1
            for (int d = 9; d > minDigit; d--) {
                int price = ar.get(d - 1);
                if (price - minPrice <= remaining) {
                    number.setCharAt(i, (char) (d + '0'));
                    remaining -= (price - minPrice);
                    break;
                }
            }
            if (remaining < 0) {
                break;
            }
        }

        return number.toString();
    }

    /**
     * Runs a series of test cases to validate the solve method.
     */
    public static void main(String[] args) {
        List<TestCase> testCases = new ArrayList<>();

        // Test Case 1: Example 1
        testCases.add(new TestCase(
                3,
                Arrays.asList(1, 1, 1, 1, 1, 1, 1, 1, 1),
                "999"
        ));

        // Test Case 2: Example 2
        testCases.add(new TestCase(
                4,
                Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9),
                "21"
        ));

        // Test Case 3: No budget
        testCases.add(new TestCase(
                0,
                Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9),
                "-1"
        ));

        // Test Case 4: Only one digit can be bought
        testCases.add(new TestCase(
                2,
                Arrays.asList(3, 4, 5, 6, 7, 8, 9, 10, 11),
                "-1"
        ));

        // Test Case 5: Multiple options with different prices
        testCases.add(new TestCase(
                10,
                Arrays.asList(2, 2, 2, 2, 2, 2, 2, 2, 2),
                "99999"
        ));

        // Test Case 6: Large budget and large prices
        testCases.add(new TestCase(
                1000000,
                Arrays.asList(100000, 99999, 99998, 99997, 99996, 99995, 99994, 99993, 99992),
                "9999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999" // 10 digits
        ));

        // Test Case 7: Best digit not the cheapest
        testCases.add(new TestCase(
                5,
                Arrays.asList(5, 4, 3, 2, 1, 2, 3, 4, 5),
                "9"
        ));

        // Test Case 8: All digits have the same price
        testCases.add(new TestCase(
                9,
                Arrays.asList(1, 1, 1, 1, 1, 1, 1, 1, 1),
                "999999999"
        ));

        // Test Case 9: Only digit '1' is cheapest
        testCases.add(new TestCase(
                10,
                Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9),
                "9999999999"
        ));

        // Test Case 10: Multiple replacements possible
        testCases.add(new TestCase(
                7,
                Arrays.asList(5, 6, 7, 2, 3, 2, 4, 3, 2),
                "7777"
        ));

        // Execute all test cases
        int passed = 0;
        for (int i = 0; i < testCases.size(); i++) {
            TestCase tc = testCases.get(i);
            String result = solve(tc.n, tc.ar);
            if (result.equals(tc.expected)) {
                System.out.println("Test Case " + (i + 1) + ": PASS");
                passed++;
            } else {
                System.out.println("Test Case " + (i + 1) + ": FAIL");
                System.out.println("   Expected: " + tc.expected);
                System.out.println("   Got     : " + result);
            }
        }
        System.out.println("Passed " + passed + " out of " + testCases.size() + " test cases.");
    }

    /**
     * A helper class to represent a test case.
     */
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
