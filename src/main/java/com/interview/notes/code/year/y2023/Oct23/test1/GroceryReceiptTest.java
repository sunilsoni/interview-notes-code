package com.interview.notes.code.year.y2023.Oct23.test1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Java: Grocery Receipt
 * Description
 * A software development team for an e-commerce company is
 * working on billing software. They are given some product prices,
 * followed by discounts and the number of items purchased by a user.
 * Given a list of unique items with their prices, a list of unique items
 * with their discounts, and a list of items purchased by the user,
 * generate the final invoice. The format for each item is item, price,
 * total_price(total_price is calculated as sum_of_quantity*
 * price_of_item, and if a discount exists, it's subtracted from it). There
 * might be duplicates in the list of purchases.
 * This invoice list should be sorted in ascending order of product
 * name.
 * <p>
 * GroceryReceipt class:
 * •  Create the 'GroceryReceipt' class that extends Grocery Receipt Base
 * •  These properties will be passed.
 * o Prices - the list of items and their prices
 * o Discounts - the list of discounts for each item
 * Example
 * It is given that Bananas, Apples, and Oranges are priced at 10, 20,
 * and 5, respectively. The discount on an Orange is 10%. The customer
 * shopping list is as follows.
 * <p>
 * <p>
 * Example:
 * <p>
 * <p>
 * It is given that Bananas, Apples, and Oranges are priced at 10, 20,
 * and 5, respectively. The discount on an Orange is 10%. The customer
 * shopping list is as follows.
 * <p>
 * <p>
 * Item Quantity
 * Banana.    5
 * Orabge  2
 * Orabge 1
 * <p>
 * <p>
 * he final Invoice is
 * <p>
 * Item Proce totalPrice
 * Banana. 10.  50
 * Orange.  5.  13.5
 * <p>
 * <p>
 * <p>
 * Normally, an Orange costs 5 per unit, but there is a 10% discount.
 * For 3 units of Orange, the gross price is 15, less 10% is 13.5.
 * Function Description
 * Create GroceryReceipt class by extending the
 * GroceryReceiptBase abstract class. Implement a Calculate function
 * to create invoices. The function should return the item, quantity, and
 * the total price after the discount for every item in the grocery
 * receipt.
 */
public class GroceryReceiptTest {
    public static void main(String[] args) {
        Map<String, Double> prices = new HashMap<>();
        prices.put("Banana", 10.0);
        prices.put("Apple", 20.0);
        prices.put("Orange", 5.0);

        Map<String, Integer> discounts = new HashMap<>();
        discounts.put("Apple", 3);

        List<Node> shoppingList = new ArrayList<>();
        shoppingList.add(new Node("Apple", 31));
        shoppingList.add(new Node("Banana", 39));
        shoppingList.add(new Node("Orange", 47));

        GroceryReceipt receipt = new GroceryReceipt(prices, discounts);
        List<Grocery> invoice = receipt.Calculate(shoppingList);

        for (Grocery grocery : invoice) {
            System.out.println(grocery.fruit + " " + grocery.price + " " + grocery.total);
        }
    }
}

/**
 * 3
 * <p>
 * Apple 31
 * Banaane 39
 * Orange. 47
 */