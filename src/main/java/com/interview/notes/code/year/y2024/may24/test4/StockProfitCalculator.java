package com.interview.notes.code.year.y2024.may24.test4;

public class StockProfitCalculator {

    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0; // Not enough prices to make a transaction
        }

        int minPrice = Integer.MAX_VALUE; // Start with the highest possible value
        int maxProfit = 0; // Initialize max profit to 0

        for (int price : prices) {
            // Update the minimum price as the lowest found so far
            if (price < minPrice) {
                minPrice = price;
            }
            // Calculate profit if selling at the current price and update maxProfit if this profit is higher
            else {
                maxProfit = Math.max(maxProfit, price - minPrice);
            }
        }

        return maxProfit;
    }

    public static void main(String[] args) {
        int[] prices1 = {3, 6, 4, 4, 7, 7, 2};
        int[] prices2 = {8, 2, 6, 4, 7, 1, 4};
        int[] prices3 = {7, 5, 4, 3, 2, 1, 1};
        int[] prices4 = {7, 5, 4, 3, 4, 1, 1};

        System.out.println("Maximum profit for prices1: " + maxProfit(prices1));  // Outputs 4
        System.out.println("Maximum profit for prices2: " + maxProfit(prices2));  // Outputs 5
        System.out.println("Maximum profit for prices3: " + maxProfit(prices3));  // Outputs 0
        System.out.println("Maximum profit for prices4: " + maxProfit(prices4));  // Outputs 1
    }
}
