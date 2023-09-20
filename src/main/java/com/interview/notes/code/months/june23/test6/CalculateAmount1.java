package com.interview.notes.code.months.june23.test6;

public class CalculateAmount1 {
    public static void main(String[] args) {
        int[] prices = {2, 5, 1, 4};
        int totalAmount = calculateAmount(prices);
        System.out.println(totalAmount); // 8

    }

    public static int calculateAmount(int[] prices) {
        int minPrice = prices[0];
        int totalAmount = prices[0];

        for (int i = 1; i < prices.length; i++) {
            int price = prices[i];
            minPrice = Math.min(minPrice, price);
            totalAmount += Math.max(price - minPrice, 0);
        }

        return totalAmount;
    }

}
