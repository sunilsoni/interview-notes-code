package com.interview.notes.code.months.oct24.amz.test10;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
WORKING

Louie And Number Balloons
Louie saw giant yellow number balloons in a store and decided to buy some of them. Louie wants to create a number as big as possible using these balloons and has n dollars in the wallet. Some of the balloons may have different prices. There are no zero shape balloons in the store.
Louie needs your help to determine which balloons Louie should buy to create the biggest number.
Input
The first line of input contains the n, representing the amount of money Louie has.
The second line of input contains an integer m, representing the size of an array.
The third line of input contains m integers P1, Pa ...
Pn- pi represents the price of ishape balloon.
Output
Print the string value that represents the biggest number Louie can create.
If Louie cannot buy even a single number balloon, print -1.
Constraints
0 sn≤10 power 6
m=9
1 spis 10 power 5
Example #1
Input
3
9
1
1 1 1 1 1 111
Output
999
Explanation: Louie has three dollars. Each number balloon has the same price, so Louie should buy three nine shape balloons. Using them, Louie can create 999.
Example #2
Input
5
9
23 5 5 5 55 55
Output
21
Explanation: Louie can buy these combinations of
numbers.
• (1, 1)
• {1, 2}
Louie should buy the second combination to create number 21.
 */
public class LouieNumberBalloons {
    /**
     * Louie wants to create the biggest number possible using number balloons given a certain amount of money.
     * This function computes the largest number Louie can create with the given constraints.
     *
     * @param n  the amount of money Louie has
     * @param ar the list of prices for balloons shaped like digits 1 to 9
     * @return the string representation of the biggest number Louie can create
     */
    public static String solve(int n, List<Integer> ar) {
        // Map prices to digits 1-9
        int[] price = new int[10];
        for (int i = 1; i <= 9; i++) {
            price[i] = ar.get(i - 1);
        }

        // Find the minimal price
        int minPrice = Integer.MAX_VALUE;
        for (int i = 1; i <= 9; i++) {
            if (price[i] < minPrice) {
                minPrice = price[i];
            }
        }

        // Calculate the maximum length of the number
        int length = n / minPrice;
        if (length == 0) {
            return "-1";
        }

        StringBuilder result = new StringBuilder();
        int remainingMoney = n;

        // Construct the largest possible number
        for (int i = 0; i < length; i++) {
            for (int d = 9; d >= 1; d--) {
                int cost = price[d];
                int totalCost = remainingMoney - cost;
                int minRequired = (length - i - 1) * minPrice;

                if (totalCost >= minRequired && totalCost >= 0) {
                    result.append(d);
                    remainingMoney -= cost;
                    break;
                }
            }
        }

        return result.toString();
    }

    /**
     * Main method to test the solve function with various test cases.
     */
    public static void main(String[] args) {
        List<TestCase> testCases = new ArrayList<>();

        // Example #1
        testCases.add(new TestCase(
                3,
                Arrays.asList(1, 1, 1, 1, 1, 1, 1, 1, 1),
                "999"
        ));

        // Example #2
        testCases.add(new TestCase(
                5,
                Arrays.asList(2, 3, 4, 5, 6, 7, 8, 9, 10),
                "21"
        ));

        // Edge case: Cannot buy any balloons
        testCases.add(new TestCase(
                0,
                Arrays.asList(1, 1, 1, 1, 1, 1, 1, 1, 1),
                "-1"
        ));

        // Edge case: Large n, minimal prices
        testCases.add(new TestCase(
                1000000,
                Arrays.asList(1, 1, 1, 1, 1, 1, 1, 1, 1),
                "9".repeat(1000000)
        ));

        // Edge case: n is less than any price
        testCases.add(new TestCase(
                1,
                Arrays.asList(2, 3, 4, 5, 6, 7, 8, 9, 10),
                "-1"
        ));

        // Run test cases
        for (int i = 0; i < testCases.size(); i++) {
            TestCase tc = testCases.get(i);
            String output = solve(tc.n, tc.prices);
            if (output.equals(tc.expectedOutput)) {
                System.out.println("Test Case #" + (i + 1) + ": PASS");
            } else {
                System.out.println("Test Case #" + (i + 1) + ": FAIL");
                System.out.println("Expected: " + tc.expectedOutput);
                System.out.println("Got: " + output);
            }
        }
    }
}

/**
 * A helper class to represent a test case.
 */
class TestCase {
    int n;
    List<Integer> prices;
    String expectedOutput;

    public TestCase(int n, List<Integer> prices, String expectedOutput) {
        this.n = n;
        this.prices = prices;
        this.expectedOutput = expectedOutput;
    }
}
