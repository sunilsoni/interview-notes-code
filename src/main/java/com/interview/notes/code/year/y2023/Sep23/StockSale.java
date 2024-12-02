package com.interview.notes.code.year.y2023.Sep23;

import java.util.Scanner;

/**
 * Stock Sale:
 * <p>
 * You are given an array of positive integers, where each integer represents the price of a
 * stock on a given day. You want to maximize your profit by choosing a single day to buy the
 * stock and choosing a different day in the future to sell that stock. Return the maximum
 * profit you can achieve from this transaction. If you cannot achieve any profit, return 0.
 * You only need to update the code segment indicated.
 * Examples:
 * Input: 81 5 3 6 4
 * Output: 5
 * Explanation: Buy on day 2 (when price = 1) and sell on day 5 (when price = 6), so profit = 6-1
 * = 5.
 * Note that buying on day 2 and selling on day 1 is not allowed because you must buy before
 * you sell.
 * Input: 7 6 4 31
 * Output: 0
 * Explanation: In this case, no profitable transactions can be done and hence the max profit =
 * 0.
 * Constraints:
 * Assume the number of days can be between 2 and 30.
 * Assume stock prices are always positive integers between 1 and 100.
 * The input is a stream of space-separated integers.
 */
public class StockSale {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read the space-separated integers
        String[] input = scanner.nextLine().split(" ");
        int[] prices = new int[input.length];
        for (int i = 0; i < input.length; i++) {
            prices[i] = Integer.parseInt(input[i]);
        }

        int maxProfit = calculateMaxProfit(prices);
        System.out.println(maxProfit);
    }

    private static int calculateMaxProfit(int[] prices) {
        int maxProfit = 0;
        int minPrice = Integer.MAX_VALUE;

        for (int price : prices) {
            if (price < minPrice) {
                minPrice = price;
            } else {
                maxProfit = Math.max(maxProfit, price - minPrice);
            }
        }
        return maxProfit;
    }
}
