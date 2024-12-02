package com.interview.notes.code.year.y2023.july23.test3;

import java.util.HashMap;
import java.util.Map;

/**
 * 1. Price Check in Java
 * There is a shop with old-style cash registers. Rather than scanning items and pulling the price from a
 * database, the price of each item is typed in manually. This method sometimes leads to errors. Given a
 * list of items and their correct prices, compare the prices to those entered when each item was sold.
 * Determine the number of errors in selling prices.
 * Example
 * products = ['eggs', 'milk', 'cheese']
 * productPrices = [2.89, 3.29, 5.79]
 * productSold = ['eggs', 'eggs', 'cheese', 'milk']
 * sold Price = [2.89, 2.99, 5.97, 3.29].
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * Product  PriceActual Expected Error
 * eggs            2.89        2.89
 * eggs            2.99        2.89.       1
 * cheese          5.97        5.79        1
 * miIk            3.29            3.29
 * <p>
 * <p>
 * <p>
 * The second sale of eggs has a wrong price, as does the sale of cheese. There are 2 errors in pricing.
 * Function Description
 * Complete the function priceCheck in the editor below.
 * priceCheck has the following parameter(s):
 * string products[n]: each products[i] '\s the name of an item for sale
 * string productPrices[n]: each productPrices[i]'\s the price of products[i]
 * string productSold[m]: each product5old[j] '\s the name of a product sold
 * float soldPrice[m]: each so!dPrice[j]contains the sale price recorded for productSold[j].
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * Returns:
 * int: the number of sale prices that were entered incorrectly
 * Constraints
 * •   1<n<10?
 * •   1<m<n
 * •   1.00 < productPricesfi], soldPrice[j] < 100000.00, where 0 < i < n, and 0 < j < m
 * ▼ Input Format for Custom Testing
 * Input from stdin will be processed as follows and passed to the function.
 * The first line contains an integer nthe size of the products array.
 * The next n lines each contain an element products[i].
 * The next line contains an integer n, the size of the productPrices array.
 * The next n lines each contain an element productPricesfi].
 * The next line contains an integer m, the size of the productSoldarray.
 * The next m lines each contain an element, productSold[j].
 * The next line contains an integer, m, the size of the soldPrice array.
 * The next m lines each contain an element soldPrice[j].
 */
public class PriceCheck {
    public static void main(String[] args) {
        String[] products = {"rice", "sugar", "wheat", "cheese"};
        double[] productPrices = {16.89, 56.92, 20.89, 345.99};
        String[] productSold = {"rice", "cheese"};
        double[] soldPrice = {18.99, 400.89};

        System.out.println(priceCheck(products, productPrices, productSold, soldPrice));
    }

    public static int priceCheck(String[] products, double[] productPrices, String[] productSold, double[] soldPrice) {
        Map<String, Double> productPriceMap = new HashMap<>();

        for (int i = 0; i < products.length; i++) {
            productPriceMap.put(products[i], productPrices[i]);
        }

        int errors = 0;
        for (int i = 0; i < productSold.length; i++) {
            Double correctPrice = productPriceMap.get(productSold[i]);
            if (correctPrice == null || !correctPrice.equals(soldPrice[i])) {
                errors++;
            }
        }

        return errors;
    }
}
