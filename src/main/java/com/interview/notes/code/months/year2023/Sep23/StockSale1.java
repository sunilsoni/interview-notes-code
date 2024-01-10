package com.interview.notes.code.months.year2023.Sep23;

import java.util.Scanner;

public class StockSale1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] input = scanner.nextLine().split(" ");
        int[] prices = new int[input.length];
        for (int i = 0; i < input.length; i++) {
            prices[i] = Integer.parseInt(input[i]);
        }

        // BEGIN OF MISSING CODE SEGMENT
        int maxProfit = 0;
        int minPrice = Integer.MAX_VALUE;

        for (int price : prices) {
            if (price < minPrice) {
                minPrice = price;
            } else {
                maxProfit = Math.max(maxProfit, price - minPrice);
            }
        }
        System.out.println(maxProfit);
        // END OF MISSING CODE SEGMENT
    }
}
