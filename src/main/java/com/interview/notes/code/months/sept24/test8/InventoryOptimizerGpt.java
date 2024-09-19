package com.interview.notes.code.months.sept24.test8;

import java.util.*;

/**
 * InventoryOptimizer class to maximize the total value of items placed in an inventory grid.
 */
public class InventoryOptimizerGpt {

    public static void main(String[] args) {
        // Inventory dimensions
        int inventoryWidth = 5;
        int inventoryHeight = 4;

        // Define items
        List<Item> items = Arrays.asList(
                new Item("Potion of Potionentiality", 1, 1, 30),
                new Item("Jeweled Dog Draught Excluder", 3, 1, 150),
                new Item("Spartan Shield", 2, 2, 300),
                new Item("Palindromic Sword o' Drows", 1, 3, 120),
                new Item("Unobsidian Armor", 2, 3, 540),
                new Item("Wardrobe of Infinite Lions", 20, 10, 1337)
        );

        // Initialize memoization map
        Map<Integer, Result> memo = new HashMap<>();

        // Start the optimization process
        Result result = optimize(0, inventoryWidth, inventoryHeight, items, memo);

        // Output the results
        System.out.println("Maximum total value: " + result.maxValue + " gold");
        System.out.println("Item placements:");
        for (Placement p : result.placements) {
            System.out.println("Item: " + p.item.name + ", Position: (" + p.x + ", " + p.y + ")");
        }
    }

    /**
     * Recursive optimization function to find the best arrangement of items.
     *
     * @param gridState Current state of the grid represented as a bitmask.
     * @param width     Width of the inventory grid.
     * @param height    Height of the inventory grid.
     * @param items     List of available items.
     * @param memo      Memoization map to store computed results.
     * @return Result object containing the maximum value and placements.
     */
    public static Result optimize(int gridState, int width, int height, List<Item> items, Map<Integer, Result> memo) {
        if (memo.containsKey(gridState)) {
            return memo.get(gridState);
        }

        Result bestResult = new Result(0, new ArrayList<>());

        boolean hasEmptyCell = false;

        // Iterate over each cell in the grid
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int idx = y * width + x;
                if ((gridState & (1 << idx)) != 0) {
                    continue; // Cell is already occupied
                }
                hasEmptyCell = true;

                // Try placing each item at the current position
                for (Item item : items) {
                    if (canPlace(gridState, x, y, item, width, height)) {
                        int newGridState = placeItem(gridState, x, y, item, width);
                        Result subResult = optimize(newGridState, width, height, items, memo);

                        int totalValue = item.value + subResult.maxValue;
                        if (totalValue > bestResult.maxValue) {
                            List<Placement> newPlacements = new ArrayList<>();
                            newPlacements.add(new Placement(item, x, y));
                            newPlacements.addAll(subResult.placements);

                            bestResult = new Result(totalValue, newPlacements);
                        }
                    }
                }
            }
        }

        if (!hasEmptyCell) {
            // No empty cells left; return the current result
            return new Result(0, new ArrayList<>());
        }

        memo.put(gridState, bestResult);
        return bestResult;
    }

    /**
     * Checks if an item can be placed at the given position in the grid.
     *
     * @param gridState Current state of the grid.
     * @param x         X-coordinate in the grid.
     * @param y         Y-coordinate in the grid.
     * @param item      Item to be placed.
     * @param width     Width of the inventory grid.
     * @param height    Height of the inventory grid.
     * @return True if the item can be placed; false otherwise.
     */
    public static boolean canPlace(int gridState, int x, int y, Item item, int width, int height) {
        // Check if item fits within the grid boundaries
        if (x + item.width > width || y + item.height > height) {
            return false;
        }

        // Check if the item's placement overlaps with occupied cells
        for (int dy = 0; dy < item.height; dy++) {
            for (int dx = 0; dx < item.width; dx++) {
                int idx = (y + dy) * width + (x + dx);
                if ((gridState & (1 << idx)) != 0) {
                    return false; // Overlaps with another item
                }
            }
        }
        return true;
    }

    /**
     * Places an item on the grid and returns the new grid state.
     *
     * @param gridState Current state of the grid.
     * @param x         X-coordinate where the item is placed.
     * @param y         Y-coordinate where the item is placed.
     * @param item      Item to be placed.
     * @param width     Width of the inventory grid.
     * @return New grid state after placing the item.
     */
    public static int placeItem(int gridState, int x, int y, Item item, int width) {
        int newGridState = gridState;

        // Mark the cells occupied by the item
        for (int dy = 0; dy < item.height; dy++) {
            for (int dx = 0; dx < item.width; dx++) {
                int idx = (y + dy) * width + (x + dx);
                newGridState |= (1 << idx);
            }
        }
        return newGridState;
    }

    /**
     * Item class representing an item with dimensions and value.
     */
    static class Item {
        String name;
        int width;
        int height;
        int value;

        public Item(String name, int width, int height, int value) {
            this.name = name;
            this.width = width;
            this.height = height;
            this.value = value;
        }
    }

    /**
     * Placement class representing the placement of an item at a position.
     */
    static class Placement {
        Item item;
        int x;
        int y;

        public Placement(Item item, int x, int y) {
            this.item = item;
            this.x = x;
            this.y = y;
        }
    }

    /**
     * Result class to store the maximum total value and corresponding placements.
     */
    static class Result {
        int maxValue;
        List<Placement> placements;

        public Result(int maxValue, List<Placement> placements) {
            this.maxValue = maxValue;
            this.placements = placements;
        }
    }
}
/*
Maximum total value: 1440 gold
Item placements:
Item: Jeweled Dog Draught Excluder, Position: (0, 0)
Item: Unobsidian Armor, Position: (3, 0)
Item: Unobsidian Armor, Position: (0, 1)
Item: Potion of Potionentiality, Position: (2, 1)
Item: Potion of Potionentiality, Position: (2, 2)
Item: Jeweled Dog Draught Excluder, Position: (2, 3)

 */