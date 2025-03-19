package com.interview.notes.code.year.y2025.march.city.test1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class HugeSaleSolution {

    /**
     * getAmountOfMoney:
     * - Accepts M (maximum items to carry)
     * - Accepts arr (list of item prices)
     * - Returns the maximum money John can earn
     */
    public static int getAmountOfMoney(int M, List<Integer> arr) {
        // Filter to keep only negative prices (since only negative prices earn money).
        // Then sort them in ascending order so that the items with the largest negative
        // values come first in the list (e.g., -10 comes before -1).
        List<Integer> negativesSorted =
                arr.stream()
                        .filter(price -> price < 0)      // keep only negative prices
                        .sorted()                       // sort ascending (e.g. -20, -10, -5, ...)
                        .collect(Collectors.toList());

        // Now pick up to M of these (which will be the "smallest" in ascending order
        // but that means they are the "most negative" and hence yield the largest absolute gain).
        // We sum the absolute values of the chosen negative prices.
        // limit(M) ensures we don't exceed M items.
        int maxMoney = negativesSorted.stream()
                .limit(M)
                // convert a negative price to its positive counterpart
                // e.g., if price is -10, we add 10 to our sum
                .mapToInt(price -> -price)
                .sum();

        // Return the total money earned.
        return maxMoney;
    }

    /**
     * main:
     * - Demonstrates usage by running multiple "test" scenarios.
     * - Verifies each scenario with an expected result (pass/fail).
     */
    public static void main(String[] args) {

        // Minimal Reproducible Examples & Additional Tests
        testGetAmountOfMoney(
                1,
                Arrays.asList(-1, -10),  // 2 negative items: picking the item -10 yields 10 dollars
                10
        );

        testGetAmountOfMoney(
                2,
                Arrays.asList(10, 20, 30),  // All are positive; no money can be earned
                0
        );

        testGetAmountOfMoney(
                2,
                Arrays.asList(-1, -2, -10, 5, 3), // Sort negatives => [-10, -2, -1]
                // Taking up to 2 items -> pick -10 & -2 => 10 + 2 = 12
                12
        );

        testGetAmountOfMoney(
                5,
                Arrays.asList(-10, -5, -3, -1, 2, 4, 6), // Enough negative items that we can take 4 of them
                // M=5, but only 4 negative items => -10 + -5 + -3 + -1 => 10 + 5 + 3 + 1 = 19
                19
        );

        // Edge Case: M=1 but no negative items
        testGetAmountOfMoney(
                1,
                Arrays.asList(1, 2, 3), // No negative => Earn 0
                0
        );

        // Large data test (quick demonstration—would normally test with thousands of items)
        List<Integer> largeData = new ArrayList<>();
        // Add some negative prices
        for (int i = 0; i < 1000; i++) {
            largeData.add(-(i + 1)); // -1, -2, -3, ... up to -1000
        }
        // M is smaller, so let's pick only up to 10
        testGetAmountOfMoney(
                10,
                largeData,
                // The 10 most negative items are -1000, -999, ... -991 => sum of those absolute values is
                // (1000 + 999 + ... + 991). Quick check: 1000+999+...+991 = sum from 991..1000
                // sum(991..1000) = 9955
                9955
        );
    }

    /**
     * testGetAmountOfMoney:
     * - Helper method to quickly run a test, compare against an expected value,
     * and print pass/fail.
     */
    private static void testGetAmountOfMoney(int M, List<Integer> arr, int expected) {
        int actual = getAmountOfMoney(M, arr);
        boolean pass = (actual == expected);

        System.out.println("M=" + M
                + ", arr=" + arr
                + ", expected=" + expected
                + ", got=" + actual
                + " => " + (pass ? "PASS" : "FAIL"));
    }
}
