package com.interview.notes.code.test.test6;

import java.util.*;

public class VendingMachine1 {
    // Define prices for items
    static final Map<String, Double> items = new HashMap<String, Double>() {{
        put("Item 1", 0.10);
        put("Item 2", 0.20);
        put("Item 3", 0.30);
        put("Item 4", 0.40);
    }};

    // Define available coins
    static final List<Double> coins = new ArrayList<Double>() {{
        add(0.25);
        add(0.10);
        add(0.05);
        add(0.01);
    }};

    public static void main(String[] args) {
        String selectedItem = "Item 3"; // The selected item
        double inputMoney = 1.0; // The amount of money given by the user

        // Check if the selected item exists and if the given amount of money is sufficient
        if (!items.containsKey(selectedItem) || inputMoney < items.get(selectedItem)) {
            System.out.println("Invalid item or insufficient money.");
            return;
        }

        double change = inputMoney - items.get(selectedItem); // Calculate the change
        System.out.println("Change: $" + String.format("%.2f", change));

        // Give change starting from the highest denomination
        for (double coin : coins) {
            int count = (int) (change / coin);
            if (count > 0) {
                System.out.println("Return " + count + " coin(s) of $" + String.format("%.2f", coin));
                change -= count * coin;
                change = Math.round(change * 100.0) / 100.0; // Rounding to keep precision
            }
        }
    }
}
