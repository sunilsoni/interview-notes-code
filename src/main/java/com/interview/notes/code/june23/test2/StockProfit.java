package com.interview.notes.code.june23.test2;

/**
 * Find the maximum single sell profit
 * Problem Statement: Given a list of daily stock prices (integers for simplicity), return the buy and sell prices that will maximize the single buy/sell profit. If you can’t make any profit, try to minimize the loss.
 */
public class StockProfit {
    public static int[] findMaxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            throw new IllegalArgumentException("At least two prices are required.");
        }

        int minPrice = prices[0];
        int maxProfit = prices[1] - prices[0];
        int buyPrice = minPrice;
        int sellPrice = prices[1];

        for (int i = 1; i < prices.length; i++) {
            int currentPrice = prices[i];
            int potentialProfit = currentPrice - minPrice;

            if (potentialProfit > maxProfit) {
                maxProfit = potentialProfit;
                buyPrice = minPrice;
                sellPrice = currentPrice;
            }

            if (currentPrice < minPrice) {
                minPrice = currentPrice;
            }
        }

        return new int[]{buyPrice, sellPrice};
    }

    public static void main(String[] args) {
        int[] prices = {100, 180, 260, 310, 40, 535, 695};
        int[] result = findMaxProfit(prices);
        System.out.println("Buy at: " + result[0] + ", Sell at: " + result[1]);
    }
}
