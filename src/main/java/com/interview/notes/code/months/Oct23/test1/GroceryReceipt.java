package com.interview.notes.code.months.Oct23.test1;

import java.util.*;

class GroceryReceipt extends GroceryReceiptBase {

    public GroceryReceipt(Map<String, Double> prices, Map<String, Integer> discounts) {
        super(prices, discounts);
    }

    @Override
    public List<Grocery> Calculate(List<Node> shoppingList) {
        Map<String, Integer> itemCounts = new HashMap<>();
        for (Node node : shoppingList) {
            itemCounts.put(node.fruit, itemCounts.getOrDefault(node.fruit, 0) + node.count);
        }

        List<Grocery> invoice = new ArrayList<>();

        for (Map.Entry<String, Integer> entry : itemCounts.entrySet()) {
            String item = entry.getKey();
            int quantity = entry.getValue();
            double price = getPrices().get(item);
            double totalPrice = price * quantity;

            if (getDiscounts().containsKey(item)) {
                double discount = getDiscounts().get(item) / 100.0;
                totalPrice -= totalPrice * discount;
            }

            invoice.add(new Grocery(item, price, totalPrice));
        }

        Collections.sort(invoice, (g1, g2) -> g1.fruit.compareTo(g2.fruit));

        return invoice;
    }
}