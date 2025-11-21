package com.interview.notes.code.year.y2024.dec24.oci.test1;

import java.util.*;
import java.util.stream.Collectors;

/**
 * WORKING
 * Abstract base class provided by the problem statement.
 * <p>
 * ## Sample Case 0
 * <p>
 * ### Sample Input
 * *STDIN**       | **FUNCTION**
 * ---             | ---
 * 3               | `n = 3`
 * Apple 34        | `[fruit, price per unit] = [["Apple", 34], ["Banana", 14], ["Orange", 4]]`
 * 1               | `m = 1`
 * Orange 10       | `[fruit, percentage discount] = [["Orange", 10]]`
 * 2               | `k = 2`
 * Apple 2         | `[fruit, units] = [["Apple", 2], ["Apple", 5]]`
 * Apple 5         |
 * <p>
 * ### Sample Output
 * ```
 * Apple 34.0 238.0
 * ```
 * <p>
 * ### Explanation
 * The shopping list is 2 units of Apple and 5 units of Apple. A total of 7 units of Apple costs \( 7 \times 34 = 238 \), and there is no discount.
 * <p>
 * ---
 * <p>
 * ## Example
 * <p>
 * ### Input
 * It is given that Bananas, Apples, and Oranges are priced at 10, 20, and 5, respectively.
 * The discount on Orange is 10%.
 * The customer shopping list is:
 * ```
 * item     quantity
 * Banana   5
 * Orange   2
 * Orange   1
 * ```
 * <p>
 * ### Output
 * ```
 * item     price   total_price
 * Banana   10      50
 * Orange   5       13.5
 * ```
 * <p>
 * *Explanation:**
 * Normally, an Orange costs 5 per unit. A 10% discount applies, so for 3 units of Orange, the gross price is \( 15 - 10\% = 13.5 \).
 * <p>
 * ---
 * <p>
 * ## Function Description
 * <p>
 * Create a `GroceryReceipt` class by extending the `GroceryReceiptBase` abstract class. Implement a `Calculate` function to create invoices. The function should return:
 * - **item**: Name of the product.
 * - **quantity**: Total quantity of the product purchased.
 * - **total price**: The total price after applying any discounts.
 * <p>
 * This invoice list should be sorted in ascending order of product name.
 * <p>
 * ---
 * <p>
 * This is now updated with all the missing sections. Let me know if there’s anything else to include or clarify!
 * ## Description: Java Grocery Receipt
 * <p>
 * A software development team for an e-commerce company is working on billing software. They are given some product prices, followed by discounts and the number of items purchased by a user.
 * <p>
 * Given a list of unique items with their prices, a list of unique items with their discounts, and a list of items purchased by the user, generate the final invoice. The format for each item is:
 * <p>
 * ```
 * item, price, total_price
 * ```
 * <p>
 * Where:
 * - **total_price** is calculated as \( \text{sum_of_quantity} \times \text{price_of_item} \).
 * - If a discount exists, it's subtracted from the **total_price**.
 * <p>
 * *Note:** There might be duplicates in the list of purchases. The invoice list should be sorted in ascending order of product name.
 * <p>
 * ---
 * <p>
 * ### GroceryReceipt Class
 * - Create the `GroceryReceipt` class that extends `GroceryReceiptBase`.
 * - Properties:
 * - **Prices:** The list of items and their prices.
 * - **Discounts:** The list of discounts for each item.
 * <p>
 * ---
 * <p>
 * This is now added to the previous extracted content. Let me know if you'd like to proceed with any additional steps!
 * <p>
 * <p>
 * <p>
 * ## Sample Case 0
 * <p>
 * ### Sample Input
 * *STDIN**       | **FUNCTION**
 * ---             | ---
 * 3               | `n = 3`
 * Apple 34        | `[fruit, price per unit] = [["Apple", 34], ["Banana", 14], ["Orange", 4]]`
 * 1               | `m = 1`
 * Orange 10       | `[fruit, percentage discount] = [["Orange", 10]]`
 * 2               | `k = 2`
 * Apple 2         | `[fruit, units] = [["Apple", 2], ["Apple", 5]]`
 * Apple 5         |
 * <p>
 * ### Sample Output
 * ```
 * Apple 34.0 238.0
 * ```
 * <p>
 * ### Explanation
 * The shopping list is 2 units of Apple and 5 units of Apple. A total of 7 units of Apple costs \( 7 \times 34 = 238 \), and there is no discount.
 * <p>
 * ---
 * <p>
 * ## Function Description
 * Create a `GroceryReceipt` class by extending the `GroceryReceiptBase` abstract class. Implement a `Calculate` function to create invoices. The function should return the item, quantity, and the total price after applying the discount for every item in the grocery receipt.
 * <p>
 * ---
 * <p>
 * ## Example
 * ### Input:
 * - Bananas, Apples, and Oranges are priced at 10, 20, and 5, respectively.
 * - The discount on Orange is 10%.
 * - The customer shopping list is:
 * ```
 * item     quantity
 * Banana   5
 * Orange   2
 * Orange   1
 * ```
 * <p>
 * ### Output:
 * ```
 * item     price   total_price
 * Banana   10      50
 * Orange   5       13.5
 * ```
 * <p>
 * *Explanation:**
 * Normally, an Orange costs 5 per unit. A 10% discount applies, so for 3 units of Orange, the gross price is \( 15 - 10\% = 13.5 \).
 */
abstract class GroceryReceiptBase {
    private final Map<String, Double> prices;
    private final Map<String, Integer> discounts;

    public GroceryReceiptBase(Map<String, Double> prices, Map<String, Integer> discounts) {
        this.prices = prices;
        this.discounts = discounts;
    }

    public abstract List<Grocery> Calculate(List<Node> shoppingList);

    public Map<String, Double> getPrices() {
        return prices;
    }

    public Map<String, Integer> getDiscounts() {
        return discounts;
    }
}

/**
 * Holds the final invoice details for each item.
 */
class Grocery {
    String fruit;
    double price;       // price per unit
    double total;       // total cost after discount

    Grocery(String fruit, double price, double total) {
        this.fruit = fruit;
        this.price = price;
        this.total = total;
    }

    @Override
    public String toString() {
        return fruit + " " + price + " " + total;
    }

    // For testing equality in test cases (within a margin due to double comparisons)
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Grocery other)) return false;
        return this.fruit.equals(other.fruit)
                && Math.abs(this.price - other.price) < 0.0001
                && Math.abs(this.total - other.total) < 0.0001;
    }
}

/**
 * Represents an item purchased along with quantity.
 */
class Node {
    String fruit;
    int count;

    Node(String fruit, int count) {
        this.fruit = fruit;
        this.count = count;
    }
}

/**
 * The class we need to implement. It extends the provided abstract class.
 */
class GroceryReceipt extends GroceryReceiptBase {

    public GroceryReceipt(Map<String, Double> prices, Map<String, Integer> discounts) {
        super(prices, discounts);
    }

    /**
     * Calculate the final invoice for the provided shopping list.
     * Steps:
     * 1. Aggregate total quantities by item.
     * 2. Retrieve price per item.
     * 3. Apply any available discount.
     * 4. Sort results by item name.
     */
    @Override
    public List<Grocery> Calculate(List<Node> shoppingList) {
        // Aggregate quantities of each fruit purchased
        Map<String, Integer> quantityMap = new HashMap<>();
        for (Node n : shoppingList) {
            quantityMap.put(n.fruit, quantityMap.getOrDefault(n.fruit, 0) + n.count);
        }

        // Build final invoice list
        List<Grocery> invoice = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : quantityMap.entrySet()) {
            String fruit = entry.getKey();
            int totalUnits = entry.getValue();

            double unitPrice = getPrices().get(fruit);
            double gross = unitPrice * totalUnits;

            // Apply discount if available
            int discountPercent = getDiscounts().getOrDefault(fruit, 0);
            double discountAmount = gross * discountPercent / 100.0;
            double finalTotal = gross - discountAmount;

            invoice.add(new Grocery(fruit, unitPrice, finalTotal));
        }

        // Sort by fruit name
        invoice = invoice.stream().sorted((a, b) -> a.fruit.compareTo(b.fruit)).collect(Collectors.toList());
        return invoice;
    }
}

public class GroceryReceiptMain {
    /**
     * This method is a simple utility to verify if two lists of Grocery items match.
     * It checks both size and content.
     */
    private static boolean compareLists(List<Grocery> expected, List<Grocery> actual) {
        if (expected.size() != actual.size()) return false;
        for (int i = 0; i < expected.size(); i++) {
            if (!expected.get(i).equals(actual.get(i))) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        // Test case 1 (Sample Case 0 from the problem)
        {
            Map<String, Double> prices = new HashMap<>();
            prices.put("Apple", 34.0);
            prices.put("Banana", 14.0);
            prices.put("Orange", 4.0);

            Map<String, Integer> discounts = new HashMap<>();
            discounts.put("Orange", 10);  // discount exists, but we won't buy Orange here

            List<Node> shoppingList = new ArrayList<>();
            shoppingList.add(new Node("Apple", 2));
            shoppingList.add(new Node("Apple", 5));
            // total Apple units = 7, price = 34 * 7 = 238, no discount for Apple

            GroceryReceipt receipt = new GroceryReceipt(prices, discounts);
            List<Grocery> result = receipt.Calculate(shoppingList);

            List<Grocery> expected = List.of(
                    new Grocery("Apple", 34.0, 238.0)
            );

            System.out.println("Test Case 1: " + (compareLists(expected, result) ? "PASS" : "FAIL"));
        }

        // Test case 2 (Provided example)
        // Bananas(10), Apples(20), Oranges(5) and Orange has 10% discount.
        {
            Map<String, Double> prices = new HashMap<>();
            prices.put("Banana", 10.0);
            prices.put("Apple", 20.0);
            prices.put("Orange", 5.0);

            Map<String, Integer> discounts = new HashMap<>();
            discounts.put("Orange", 10); // 10% discount

            List<Node> shoppingList = new ArrayList<>();
            shoppingList.add(new Node("Banana", 5));
            shoppingList.add(new Node("Orange", 2));
            shoppingList.add(new Node("Orange", 1));
            // Banana total = 5 * 10 = 50 (no discount)
            // Orange total units = 3 * 5 = 15 minus 10% = 13.5

            GroceryReceipt receipt = new GroceryReceipt(prices, discounts);
            List<Grocery> result = receipt.Calculate(shoppingList);

            // Sort by fruit name in expected
            List<Grocery> expected = Arrays.asList(
                    new Grocery("Banana", 10.0, 50.0),
                    new Grocery("Orange", 5.0, 13.5)
            );

            System.out.println("Test Case 2: " + (compareLists(expected, result) ? "PASS" : "FAIL"));
        }

        // Test Case 3: No discounts and multiple items
        {
            Map<String, Double> prices = new HashMap<>();
            prices.put("Apple", 2.0);
            prices.put("Grape", 3.0);
            prices.put("Mango", 1.5);

            Map<String, Integer> discounts = new HashMap<>();
            // no discounts

            List<Node> shoppingList = new ArrayList<>();
            shoppingList.add(new Node("Mango", 10));
            shoppingList.add(new Node("Mango", 5));
            shoppingList.add(new Node("Apple", 1));
            shoppingList.add(new Node("Grape", 2));

            // Apple: 1 unit * 2.0 = 2.0
            // Grape: 2 units * 3.0 = 6.0
            // Mango: 15 units * 1.5 = 22.5

            GroceryReceipt receipt = new GroceryReceipt(prices, discounts);
            List<Grocery> result = receipt.Calculate(shoppingList);

            List<Grocery> expected = Arrays.asList(
                    new Grocery("Apple", 2.0, 2.0),
                    new Grocery("Grape", 3.0, 6.0),
                    new Grocery("Mango", 1.5, 22.5)
            );

            System.out.println("Test Case 3: " + (compareLists(expected, result) ? "PASS" : "FAIL"));
        }

        // Test Case 4: Large data scenario (just a logic check, no actual huge data here)
        // Simulate many entries for the same item
        {
            Map<String, Double> prices = new HashMap<>();
            prices.put("Orange", 4.0);

            Map<String, Integer> discounts = new HashMap<>();
            discounts.put("Orange", 5); // 5% discount

            List<Node> shoppingList = new ArrayList<>();
            int totalUnits = 0;
            for (int i = 0; i < 1000; i++) {
                shoppingList.add(new Node("Orange", 1));
                totalUnits++;
            }

            // totalUnits = 1000
            // gross = 1000 * 4 = 4000
            // discount 5% = 4000 * 0.05 = 200
            // final = 3800

            GroceryReceipt receipt = new GroceryReceipt(prices, discounts);
            List<Grocery> result = receipt.Calculate(shoppingList);

            List<Grocery> expected = List.of(
                    new Grocery("Orange", 4.0, 3800.0)
            );

            System.out.println("Test Case 4 (Large Input): " + (compareLists(expected, result) ? "PASS" : "FAIL"));
        }

        // Edge Case 5: Item in purchase that has zero quantity (should not happen normally, but let's test)
        // If zero quantity is given, it should not affect total.
        {
            Map<String, Double> prices = new HashMap<>();
            prices.put("Banana", 10.0);

            Map<String, Integer> discounts = new HashMap<>();

            List<Node> shoppingList = new ArrayList<>();
            shoppingList.add(new Node("Banana", 0)); // no real purchase
            // total = 0

            GroceryReceipt receipt = new GroceryReceipt(prices, discounts);
            List<Grocery> result = receipt.Calculate(shoppingList);

            // zero quantity means it's still included as we processed it, total=0
            List<Grocery> expected = List.of(
                    new Grocery("Banana", 10.0, 0.0)
            );

            System.out.println("Test Case 5 (Zero Quantity): " + (compareLists(expected, result) ? "PASS" : "FAIL"));
        }
    }
}
