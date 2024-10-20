package com.interview.notes.code.months.oct24.test16;

import java.util.*;
/*

Louie And Number Balloons
Louie saw giant yellow number balloons in a store and decided to buy some of them. Louie wants to create a number as big as possible using these balloons and has n dollars in the wallet. Some of the balloons may have different prices. There are no zero shape balloons in the store.
Louie needs your help to determine which balloons Louie should buy to create the biggest number.
Input
The first line of input contains the n, representing the amount of money Louie has.
The second line of input contains an integer m, representing the size of an array.
The third line of input contains m integers P1,P2...
Pn - pi represents the price of i shape balloon.
Output
Print the string value that represents the biggest number Louie can create.
If Louie cannot buy even a single number balloon, print -1.
Constraints
0≤n≤ 106
m=9
1≤pi≤ 103
Example #1
Input
3
9
111111111
Output
999

Constraints
0≤n≤ 10 power 6
m=9
1 ≤ pi≤ 10 power 5
Example #1
Input
3
9
11 1111111
Output
999
Explanation: Louie has three dollars. Each number balloon has the same price, so Louie should buy three nine shape balloons. Using them, Louie can create 999.
Example #2
Input
Output
21
Explanation: Louie can buy these combinations of
numbers.
• {1, 1}
• 11,2}
Louie should buy the second combination to create number 21.
 */
public class Outcome {
    /**
     * Implement method/function with name 'solve' below.
     * The function accepts following as parameters.
     * 1. n is of type int.
     * 2. ar is of type List<Integer>.
     * return String.
     */
    public static String solve(int n, List<Integer> ar) {
        // Map to store price of each digit
        int[] priceOfDigit = new int[10]; // Index 1 to 9

        int minPrice = Integer.MAX_VALUE;
        int minDigit = -1;

        // Populate priceOfDigit array and find the minimum price and its corresponding digit
        for (int i = 0; i < ar.size(); i++) {
            int price = ar.get(i);
            int digit = i + 1; // Digits from 1 to 9
            priceOfDigit[digit] = price;

            if (price < minPrice) {
                minPrice = price;
                minDigit = digit;
            } else if (price == minPrice && digit > minDigit) {
                // Prefer higher digit if prices are equal
                minDigit = digit;
            }
        }

        // Calculate the maximum number of digits we can buy
        int maxDigits = n / minPrice;
        if (maxDigits == 0) {
            return "-1";
        }

        StringBuilder result = new StringBuilder();
        int remainingMoney = n;

        // Construct the largest number possible
        for (int pos = 0; pos < maxDigits; pos++) {
            // Start from the highest digit to maximize the number
            for (int d = 9; d >= 1; d--) {
                int price_d = priceOfDigit[d];
                if (price_d == 0) continue; // Skip if the digit is not available

                int costToReplace = price_d - minPrice;
                int minCostForRemaining = (maxDigits - pos - 1) * minPrice;

                // Check if we can afford this digit and still buy minimum digits for remaining positions
                if (remainingMoney - price_d >= minCostForRemaining && remainingMoney - price_d >= 0) {
                    result.append(d);
                    remainingMoney -= price_d;
                    break;
                }
            }
        }
        return result.toString();
    }

    // Main method to run test cases
    public static void main(String[] args) {
        testCase1();
        testCase2();
        testCaseLargeInput();
    }

    // Test Case 1: All digits cost the same
    public static void testCase1() {
        int n = 3;
        List<Integer> ar = Arrays.asList(1, 1, 1, 1, 1, 1, 1, 1, 1);
        String expectedOutput = "999";
        String actualOutput = solve(n, ar);
        if (expectedOutput.equals(actualOutput)) {
            System.out.println("Test Case 1: PASS");
        } else {
            System.out.println("Test Case 1: FAIL");
            System.out.println("Expected: " + expectedOutput);
            System.out.println("Actual: " + actualOutput);
        }
    }

    // Test Case 2: Prices matching Example #2
    public static void testCase2() {
        int n = 5;
        // Prices for digits 1 to 9
        List<Integer> ar = Arrays.asList(2, 3, 1000, 1000, 1000, 1000, 1000, 1000, 1000);
        String expectedOutput = "21";
        String actualOutput = solve(n, ar);
        if (expectedOutput.equals(actualOutput)) {
            System.out.println("Test Case 2: PASS");
        } else {
            System.out.println("Test Case 2: FAIL");
            System.out.println("Expected: " + expectedOutput);
            System.out.println("Actual: " + actualOutput);
        }
    }

    // Test Case for Large Input
    public static void testCaseLargeInput() {
        int n = 1000000;
        List<Integer> ar = Arrays.asList(100000, 90000, 80000, 70000, 60000, 50000, 40000, 30000, 20000);
        StringBuilder expectedOutput = new StringBuilder();
        int maxDigits = n / 20000; // Price of digit '9' is 20000
        for (int i = 0; i < maxDigits; i++) {
            expectedOutput.append('9');
        }
        String actualOutput = solve(n, ar);
        if (expectedOutput.toString().equals(actualOutput)) {
            System.out.println("Test Case Large Input: PASS");
        } else {
            System.out.println("Test Case Large Input: FAIL");
            System.out.println("Expected: " + expectedOutput);
            System.out.println("Actual: " + actualOutput);
        }
    }
}
