package com.interview.notes.code.months.Oct23.test8;

public class StockProfit {

    public static void main(String[] args) {
        int[] prices = {5, 1, 7, 4, 8};
        int maxProfit = getMaxProfit(prices);
        System.out.println(maxProfit);  // Output: 7
    }

    public static int getMaxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;

        for (int price : prices) {
            minPrice = Math.min(minPrice, price);
            maxProfit = Math.max(maxProfit, price - minPrice);
        }

        return maxProfit;
    }
}
