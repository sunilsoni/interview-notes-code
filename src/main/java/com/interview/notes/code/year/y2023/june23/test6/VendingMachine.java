package com.interview.notes.code.year.y2023.june23.test6;

import java.util.*;

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
class VendingMachine {

    private final Map<String, Double> items;
    private final List<Double> coins;

    public VendingMachine() {
        items = new HashMap<>();
        items.put("Item 1", 0.10);
        items.put("Item 2", 0.20);
        items.put("Item 3", 0.30);
        items.put("Item 4", 0.40);

        coins = Arrays.asList(0.25, 0.10, 0.05, 0.01);
    }

    public static void main(String[] args) {
        VendingMachine vm = new VendingMachine();
        vm.purchaseItem("Item 3", 1.00);
    }

    public void purchaseItem(String item, double moneyInserted) {
        if (!items.containsKey(item)) {
            System.out.println("This item doesn't exist.");
            return;
        }

        double itemPrice = items.get(item);
        if (moneyInserted < itemPrice) {
            System.out.println("Insufficient money inserted.");
            return;
        }

        double change = moneyInserted - itemPrice;
        System.out.println("Change: " + calculateChange(change));
    }

    private Map<Double, Integer> calculateChange(double change) {
        Map<Double, Integer> changeMap = new LinkedHashMap<>();
        for (Double coin : coins) {
            if (change < coin) continue;
            int count = (int) (change / coin);
            change = change - (count * coin);
            change = Math.round(change * 100.0) / 100.0; // round to 2 decimal places
            changeMap.put(coin, count);
        }
        return changeMap;
    }
}
