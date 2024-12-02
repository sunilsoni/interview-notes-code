package com.interview.notes.code.year.y2024.sept24.test8;

public class InventoryOptimization {

    public static void main(String[] args) {
        int inventoryWidth = 5;
        int inventoryHeight = 4;
        Item[] items = {
                new Item(1, 1, 30),
                new Item(3, 1, 150),
                new Item(2, 2, 300),
                new Item(1, 3, 120),
                new Item(2, 3, 540),
                new Item(20, 10, 1337) // This item won't fit
        };

        int maxValue = optimizeInventory(inventoryWidth, inventoryHeight, items);
        System.out.println("Maximum Value: " + maxValue);
    }

    public static int optimizeInventory(int width, int height, Item[] items) {
        int[][] dp = new int[width + 1][height + 1];

        for (Item item : items) {
            for (int w = width; w >= item.width; w--) {
                for (int h = height; h >= item.height; h--) {
                    dp[w][h] = Math.max(dp[w][h], dp[w - item.width][h - item.height] + item.value);
                }
            }
        }
        return dp[width][height];
    }

    static class Item {
        int width, height, value;

        Item(int width, int height, int value) {
            this.width = width;
            this.height = height;
            this.value = value;
        }
    }
}
