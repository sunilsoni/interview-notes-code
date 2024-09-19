package com.interview.notes.code.months.sept24.test8;

import java.util.*;

public class InventoryOptimizer {
    static int[][] inventory;
    static int maxValue;
    static List<Item> bestArrangement;
    static Map<String, Integer> memo;

    public static void main(String[] args) {
        List<Item> items = Arrays.asList(
                new Item("Potion of Potionentiality", 1, 1, 30),
                new Item("Jeweled Dog Draught Excluder", 3, 1, 150),
                new Item("Spartan Shield", 2, 2, 300),
                new Item("Palindromic Sword o' Drows", 1, 3, 120),
                new Item("Unobsidian Armor", 2, 3, 540),
                new Item("Wardrobe of Infinite Lions", 20, 10, 1337)
        );

        optimizeInventory(5, 4, items);
        printResult();

        // Additional test cases
        optimizeInventory(3, 3, items);
        printResult();

        optimizeInventory(10, 10, items);
        printResult();
    }

    static void optimizeInventory(int width, int height, List<Item> items) {
        inventory = new int[height][width];
        maxValue = 0;
        bestArrangement = new ArrayList<>();
        memo = new HashMap<>();

        backtrack(items, 0, 0, 0, new ArrayList<>());
    }

    static int backtrack(List<Item> items, int row, int col, int currentValue, List<Item> currentArrangement) {
        if (row >= inventory.length) {
            if (currentValue > maxValue) {
                maxValue = currentValue;
                bestArrangement = new ArrayList<>(currentArrangement);
            }
            return currentValue;
        }

        String key = row + "," + col + "," + currentValue;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        int nextCol = (col + 1) % inventory[0].length;
        int nextRow = row + (nextCol == 0 ? 1 : 0);

        int maxValueHere = backtrack(items, nextRow, nextCol, currentValue, currentArrangement);

        for (Item item : items) {
            if (canPlace(item, row, col)) {
                placeItem(item, row, col);
                currentArrangement.add(item);
                int valueWithItem = backtrack(items, nextRow, nextCol, currentValue + item.value, currentArrangement);
                maxValueHere = Math.max(maxValueHere, valueWithItem);
                removeItem(item, row, col);
                currentArrangement.remove(currentArrangement.size() - 1);
            }
        }
        memo.put(key, maxValueHere);
        return maxValueHere;
    }

    static boolean canPlace(Item item, int row, int col) {
        if (row + item.height > inventory.length || col + item.width > inventory[0].length) {
            return false;
        }
        for (int i = row; i < row + item.height; i++) {
            for (int j = col; j < col + item.width; j++) {
                if (inventory[i][j] != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    static void placeItem(Item item, int row, int col) {
        for (int i = row; i < row + item.height; i++) {
            for (int j = col; j < col + item.width; j++) {
                inventory[i][j] = 1;
            }
        }
    }

    static void removeItem(Item item, int row, int col) {
        for (int i = row; i < row + item.height; i++) {
            for (int j = col; j < col + item.width; j++) {
                inventory[i][j] = 0;
            }
        }
    }

    static void printResult() {
        System.out.println("Maximum value: " + maxValue + " gold");
        System.out.println("Best arrangement:");
        for (Item item : bestArrangement) {
            System.out.println("- " + item.name);
        }
        System.out.println();
    }

    static class Item {
        String name;
        int width, height, value;

        Item(String name, int width, int height, int value) {
            this.name = name;
            this.width = width;
            this.height = height;
            this.value = value;
        }
    }
}

/*
Maximum value: 1320 gold
Best arrangement:
- Potion of Potionentiality
- Unobsidian Armor
- Unobsidian Armor
- Potion of Potionentiality
- Potion of Potionentiality
- Jeweled Dog Draught Excluder

Maximum value: 660 gold
Best arrangement:
- Palindromic Sword o' Drows
- Unobsidian Armor

Maximum value: 2880 gold
Best arrangement:
- Unobsidian Armor
- Unobsidian Armor
- Potion of Potionentiality
- Unobsidian Armor
- Palindromic Sword o' Drows
- Unobsidian Armor
- Palindromic Sword o' Drows
- Spartan Shield
- Jeweled Dog Draught Excluder


 */