package com.interview.notes.code.months.year2023.june23.test6;

/*
Java Vending Machine:

Pck higest denomination first and return change

Vending machine:-

Item 1 - $0.10
Item 2 - $0.20
Item 3 - $0.30
Item 4 - $0.40

Available coins:- $0.01, $0.05, $0.10, $0.25

Input - $1, Item 3

70 25+25=50
 */
public class CalculateAmount2 {

    public static int calculateAmount(int[] prices) {
        int totalCost = 0;
        int minPrice = prices[0]; // Initialize the minimum price with the first item's price

        // Iterate through the prices starting from the second item
        for (int i = 1; i < prices.length; i++) {
            // Calculate the discounted price for the current item
            int discountedPrice = Math.max(prices[i] - minPrice, 0);
            totalCost += discountedPrice; // Add the discounted price to the total cost
            minPrice = Math.min(minPrice, prices[i]); // Update the minimum price
        }

        return totalCost;
    }

    public static void main(String[] args) {
        int[] prices = {2, 5, 1, 4};
        int payableAmount = calculateAmount(prices);
        System.out.println("Payable Amount: " + payableAmount);
    }


}
