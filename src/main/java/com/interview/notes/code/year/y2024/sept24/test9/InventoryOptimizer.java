package com.interview.notes.code.year.y2024.sept24.test9;

import java.util.*;

/*



Inventory optimization

Finally, you got it! You found the Infinite Treasure of the Leprechaun-Dragon!
Now, you have to decide what to loot. Your inventory is a 5x4 rectangle (5 tiles width, 4 tiles height).
Each item of the treasure also fits in a rectangle and has a value:
• Potion of Potionentiality, 1x1, 30 gold,
• Jeweled Dog Draught Excluder, 3x1, 150 gold,
• Spartan Shield, 2x2, 300 gold,
• Palindromic Sword o' Drows, 1x3, 120 gold,
• Unobsidian Armor, 2x3, 540 gold,
• Wardrobe of Infinite Lions, 20x10, 1337 gold.
You can not turn the items. For example, the Unobsidian Armor is 2 tiles in width and 3 in height. It can not be 3 tiles in width and 2 in height.
Since it's an Infinite Treasure, there are as many items of each type as you want.
Which items do you take, and how do you organize them in your inventory to have the most significant value in gold?
Try to code a generic solution that works with other inventory sizes and item types with specified dimensions and values.


 */

/**
 * InventoryOptimizer class to maximize the total value of items placed in an inventory grid.
 */
public class InventoryOptimizer {
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

        // Initialize the inventory
        Inventory inventory = new Inventory(inventoryWidth, inventoryHeight);

        // Create an optimizer instance
        Optimizer optimizer = new Optimizer(items);

        // Perform optimization
        Result result = optimizer.optimize(inventory);

        // Output the results
        System.out.println("Maximum total value: " + result.getTotalValue() + " gold");
        System.out.println("Item placements:");
        for (Placement p : result.getPlacements()) {
            System.out.println("Item: " + p.getItem().getName() + ", Position: (" + p.getX() + ", " + p.getY() + ")");
        }
    }
}

/**
 * Item class representing an item with dimensions and value.
 */
class Item {
    private String name;
    private int width;
    private int height;
    private int value;

    public Item(String name, int width, int height, int value) {
        this.name = name;
        this.width = width;
        this.height = height;
        this.value = value;
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getValue() {
        return value;
    }
}

/**
 * Inventory class representing the inventory grid.
 */
class Inventory {
    private int width;
    private int height;
    private long gridState; // Each bit represents a cell: 1 = occupied, 0 = empty

    public Inventory(int width, int height) {
        this.width = width;
        this.height = height;
        this.gridState = 0L;
    }

    // Copy constructor
    public Inventory(Inventory other) {
        this.width = other.width;
        this.height = other.height;
        this.gridState = other.gridState;
    }

    /**
     * Checks if an item can be placed at the given position in the grid.
     *
     * @param x    X-coordinate in the grid.
     * @param y    Y-coordinate in the grid.
     * @param item Item to be placed.
     * @return True if the item can be placed; false otherwise.
     */
    public boolean canPlaceItem(int x, int y, Item item) {
        if (x + item.getWidth() > width || y + item.getHeight() > height) {
            return false;
        }
        for (int dy = 0; dy < item.getHeight(); dy++) {
            for (int dx = 0; dx < item.getWidth(); dx++) {
                int idx = (y + dy) * width + (x + dx);
                if (((gridState >> idx) & 1L) != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Places an item on the grid by updating the grid state.
     *
     * @param x    X-coordinate where the item is placed.
     * @param y    Y-coordinate where the item is placed.
     * @param item Item to be placed.
     */
    public void placeItem(int x, int y, Item item) {
        for (int dy = 0; dy < item.getHeight(); dy++) {
            for (int dx = 0; dx < item.getWidth(); dx++) {
                int idx = (y + dy) * width + (x + dx);
                gridState |= (1L << idx);
            }
        }
    }

    /**
     * Gets the next empty cell in the grid.
     *
     * @return An array containing the x and y coordinates of the next empty cell, or null if the grid is full.
     */
    public int[] getNextEmptyCell() {
        for (int idx = 0; idx < width * height; idx++) {
            if (((gridState >> idx) & 1L) == 0) {
                int x = idx % width;
                int y = idx / width;
                return new int[]{x, y};
            }
        }
        return null;
    }

    // Getters
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public long getGridState() {
        return gridState;
    }
}

/**
 * Placement class representing the placement of an item at a position.
 */
class Placement {
    private Item item;
    private int x;
    private int y;

    public Placement(Item item, int x, int y) {
        this.item = item;
        this.x = x;
        this.y = y;
    }

    // Getters
    public Item getItem() {
        return item;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}

/**
 * Result class to store the maximum total value and corresponding placements.
 */
class Result {
    private int totalValue;
    private List<Placement> placements;

    public Result(int totalValue, List<Placement> placements) {
        this.totalValue = totalValue;
        this.placements = placements;
    }

    // Getters
    public int getTotalValue() {
        return totalValue;
    }

    public List<Placement> getPlacements() {
        return placements;
    }
}

/**
 * Optimizer class containing the optimization logic.
 */
class Optimizer {
    private List<Item> items;
    private Map<Long, Result> memo;

    public Optimizer(List<Item> items) {
        this.items = items;
        this.memo = new HashMap<>();
    }

    /**
     * Recursive optimization function to find the best arrangement of items.
     *
     * @param inventory The current state of the inventory grid.
     * @return Result object containing the maximum value and placements.
     */
    public Result optimize(Inventory inventory) {
        long key = inventory.getGridState();
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        int[] nextEmptyCell = inventory.getNextEmptyCell();
        if (nextEmptyCell == null) {
            // No empty cells left; return the current result
            return new Result(0, new ArrayList<>());
        }

        int x = nextEmptyCell[0];
        int y = nextEmptyCell[1];
        int maxTotalValue = 0;
        List<Placement> bestPlacements = new ArrayList<>();

        // Try placing each item at the current position
        for (Item item : items) {
            if (inventory.canPlaceItem(x, y, item)) {
                Inventory newInventory = new Inventory(inventory);
                newInventory.placeItem(x, y, item);

                Result subResult = optimize(newInventory);
                int totalValue = item.getValue() + subResult.getTotalValue();

                if (totalValue > maxTotalValue) {
                    maxTotalValue = totalValue;
                    List<Placement> newPlacements = new ArrayList<>();
                    newPlacements.add(new Placement(item, x, y));
                    newPlacements.addAll(subResult.getPlacements());
                    bestPlacements = newPlacements;
                }
            }
        }

        // Option to skip placing any item at this position
        // Mark this cell as blocked to avoid infinite recursion
        Inventory newInventory = new Inventory(inventory);
        newInventory.placeItem(x, y, new Item("Blocked", 1, 1, 0));

        Result skipResult = optimize(newInventory);
        if (skipResult.getTotalValue() > maxTotalValue) {
            maxTotalValue = skipResult.getTotalValue();
            bestPlacements = skipResult.getPlacements();
        }

        Result result = new Result(maxTotalValue, bestPlacements);
        memo.put(key, result);
        return result;
    }
}
