package com.interview.notes.code.year.y2024.dec24.oci.test2;

import java.util.*;

/**
 * Represents a grocery item with its name, price per unit, and total price after discount.
 */
class Grocery {
    String fruit;
    double price;
    double total;

    Grocery(String fruit, double price, double total) {
        this.fruit = fruit;
        this.price = price;
        this.total = total;
    }
}

/**
 * Represents a shopping list entry with the item name and quantity purchased.
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
 * Abstract base class provided in the problem statement.
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
 * The GroceryReceipt class extends GroceryReceiptBase and implements the Calculate method.
 */
public class GroceryReceipt extends GroceryReceiptBase {

    public GroceryReceipt(Map<String, Double> prices, Map<String, Integer> discounts) {
        super(prices, discounts);
    }

    /**
     * Main method to run test cases.
     */
    public static void main(String[] args) {
        // Test Case 1: Sample Case 0
        Map<String, Double> prices1 = new HashMap<>();
        prices1.put("Apple", 34.0);
        prices1.put("Banana", 14.0);
        prices1.put("Orange", 4.0);

        Map<String, Integer> discounts1 = new HashMap<>();
        discounts1.put("Orange", 10);

        List<Node> shoppingList1 = new ArrayList<>();
        shoppingList1.add(new Node("Apple", 2));
        shoppingList1.add(new Node("Apple", 5));

        GroceryReceipt receipt1 = new GroceryReceipt(prices1, discounts1);
        List<Grocery> invoice1 = receipt1.Calculate(shoppingList1);

        boolean pass1 = invoice1.size() == 1
                && invoice1.get(0).fruit.equals("Apple")
                && invoice1.get(0).price == 34.0
                && invoice1.get(0).total == 238.0;

        if (pass1) {
            System.out.println("Test Case 1: PASS");
        } else {
            System.out.println("Test Case 1: FAIL");
            System.out.println("Expected Output:");
            System.out.println("Apple 34.0 238.0");
            System.out.println("Actual Output:");
            for (Grocery g : invoice1) {
                System.out.println(g.fruit + " " + g.price + " " + g.total);
            }
        }

        // Test Case 2: Example with discount
        Map<String, Double> prices2 = new HashMap<>();
        prices2.put("Apple", 20.0);
        prices2.put("Banana", 10.0);
        prices2.put("Orange", 5.0);

        Map<String, Integer> discounts2 = new HashMap<>();
        discounts2.put("Orange", 10);

        List<Node> shoppingList2 = new ArrayList<>();
        shoppingList2.add(new Node("Banana", 5));
        shoppingList2.add(new Node("Orange", 2));
        shoppingList2.add(new Node("Orange", 1));

        GroceryReceipt receipt2 = new GroceryReceipt(prices2, discounts2);
        List<Grocery> invoice2 = receipt2.Calculate(shoppingList2);

        boolean pass2 = invoice2.size() == 2;

        for (Grocery g : invoice2) {
            if (g.fruit.equals("Banana")) {
                pass2 &= g.price == 10.0 && g.total == 50.0;
            } else if (g.fruit.equals("Orange")) {
                pass2 &= g.price == 5.0 && g.total == 13.5;
            } else {
                pass2 = false;
            }
        }

        if (pass2) {
            System.out.println("Test Case 2: PASS");
        } else {
            System.out.println("Test Case 2: FAIL");
            System.out.println("Expected Output:");
            System.out.println("Banana 10.0 50.0");
            System.out.println("Orange 5.0 13.5");
            System.out.println("Actual Output:");
            for (Grocery g : invoice2) {
                System.out.println(g.fruit + " " + g.price + " " + g.total);
            }
        }

        // Test Case 3: Edge Case with no discounts
        Map<String, Double> prices3 = new HashMap<>();
        prices3.put("Apple", 15.0);
        prices3.put("Banana", 5.0);

        Map<String, Integer> discounts3 = new HashMap<>();

        List<Node> shoppingList3 = new ArrayList<>();
        shoppingList3.add(new Node("Apple", 3));
        shoppingList3.add(new Node("Banana", 4));

        GroceryReceipt receipt3 = new GroceryReceipt(prices3, discounts3);
        List<Grocery> invoice3 = receipt3.Calculate(shoppingList3);

        boolean pass3 = invoice3.size() == 2;

        for (Grocery g : invoice3) {
            if (g.fruit.equals("Apple")) {
                pass3 &= g.price == 15.0 && g.total == 45.0;
            } else if (g.fruit.equals("Banana")) {
                pass3 &= g.price == 5.0 && g.total == 20.0;
            } else {
                pass3 = false;
            }
        }

        if (pass3) {
            System.out.println("Test Case 3: PASS");
        } else {
            System.out.println("Test Case 3: FAIL");
            System.out.println("Expected Output:");
            System.out.println("Apple 15.0 45.0");
            System.out.println("Banana 5.0 20.0");
            System.out.println("Actual Output:");
            for (Grocery g : invoice3) {
                System.out.println(g.fruit + " " + g.price + " " + g.total);
            }
        }

        // Test Case 4: Large Data Input
        Map<String, Double> prices4 = new HashMap<>();
        Map<String, Integer> discounts4 = new HashMap<>();
        List<Node> shoppingList4 = new ArrayList<>();

        // Adding 10000 items
        for (int i = 1; i <= 10000; i++) {
            String fruitName = "Fruit" + i;
            prices4.put(fruitName, (double) i);
            if (i % 2 == 0) { // Even fruits have 10% discount
                discounts4.put(fruitName, 10);
            }
            shoppingList4.add(new Node(fruitName, i));
        }

        GroceryReceipt receipt4 = new GroceryReceipt(prices4, discounts4);
        List<Grocery> invoice4 = receipt4.Calculate(shoppingList4);

        boolean pass4 = invoice4.size() == 10000;

        // Simple validation for a few items
        for (int i = 1; i <= 5; i++) {
            String fruitName = "Fruit" + i;
            Grocery g = invoice4.get(i - 1);
            if (!g.fruit.equals(fruitName)) {
                pass4 = false;
                break;
            }
        }

        if (pass4) {
            System.out.println("Test Case 4: PASS");
        } else {
            System.out.println("Test Case 4: FAIL");
            System.out.println("Large data set test failed.");
        }
    }

    /**
     * Calculates the invoice list based on the shopping list.
     *
     * @param shoppingList List of items purchased by the customer.
     * @return Sorted list of Grocery items with calculated total prices.
     */
    @Override
    public List<Grocery> Calculate(List<Node> shoppingList) {
        // Map to store total quantities for each item
        Map<String, Integer> totalQuantities = new HashMap<>();

        // Aggregate quantities for each item
        for (Node node : shoppingList) {
            totalQuantities.put(node.fruit, totalQuantities.getOrDefault(node.fruit, 0) + node.count);
        }

        List<Grocery> invoice = new ArrayList<>();

        // Process each item in the totalQuantities map
        for (Map.Entry<String, Integer> entry : totalQuantities.entrySet()) {
            String fruit = entry.getKey();
            int quantity = entry.getValue();

            // Get the price per unit from prices map
            double pricePerUnit = getPrices().get(fruit);

            // Calculate total price before discount
            double totalPrice = quantity * pricePerUnit;

            // Apply discount if available
            if (getDiscounts().containsKey(fruit)) {
                int discountPercentage = getDiscounts().get(fruit);
                double discountAmount = totalPrice * discountPercentage / 100.0;
                totalPrice -= discountAmount;
            }

            // Create a Grocery object and add it to the invoice list
            Grocery grocery = new Grocery(fruit, pricePerUnit, totalPrice);
            invoice.add(grocery);
        }

        // Sort the invoice list by fruit name in ascending order
        invoice.sort(Comparator.comparing(g -> g.fruit));

        return invoice;
    }
}