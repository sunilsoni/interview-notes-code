package com.interview.notes.code.year.y2026.june.apple.test3;

import java.util.ArrayList; // Imports ArrayList for collecting test output
import java.util.Comparator; // Imports Comparator for custom sorting
import java.util.List; // Imports List for testing
import java.util.PriorityQueue; // Imports PriorityQueue for the underlying basket structure

enum Type { FRUIT, CHOCOLATE } // Defines the item type to route to the correct basket

enum Size { L, M, S } // L=0, M=1, S=2. Reversing order here removes the need for .reverseOrder() later!

record Item(Type type, String name, Size size, int cal, int seq) {} // Immutable Java 21 record holding item data

class DualBasketSystem { // Single system encapsulating both baskets
    // Shared comparator: 1. Calories (Desc), 2. Size (Asc based on L=0 ordinal), 3. Sequence (Asc for FIFO bonus)
    private final Comparator<Item> rules = Comparator.comparingInt(Item::cal).reversed()
            .thenComparingInt((Item i) -> i.size().ordinal())
            .thenComparingInt(Item::seq); 

    private final PriorityQueue<Item> fruits = new PriorityQueue<>(rules); // Internal basket specifically for fruits
    private final PriorityQueue<Item> chocolates = new PriorityQueue<>(rules); // Internal basket specifically for chocolates
    private int seqCounter = 0; // Shared sequence counter for absolute chronological order across the system

    public void add(Type type, String name, Size size, int cal) { // Single entry point for all items
        Item item = new Item(type, name, size, cal, seqCounter++); // Wraps data into record and increments shared ticket
        if (type == Type.FRUIT) fruits.offer(item); // Routes to fruit basket based on Type
        else chocolates.offer(item); // Routes to chocolate basket based on Type
    } // Closes add method

    public Item fetch(Type type) { // simulates putting a hand into a specific basket
        return type == Type.FRUIT ? fruits.poll() : chocolates.poll(); // Pulls the highest priority item from the requested basket
    } // Closes fetch method

    public boolean isEmpty(Type type) { // Checks if a specific basket has items left
        return type == Type.FRUIT ? fruits.isEmpty() : chocolates.isEmpty(); // Returns boolean state of requested basket
    } // Closes isEmpty method
} // Closes the system class

public class Main { // Execution class
    public static void main(String[] args) { // Main method
        DualBasketSystem system = new DualBasketSystem(); // Instantiates our single two-basket system

        // Populate Fruits via the unified system
        system.add(Type.FRUIT, "Apple", Size.M, 95); 
        system.add(Type.FRUIT, "Apple", Size.M, 95);
        system.add(Type.FRUIT, "Apple", Size.L, 105);
        system.add(Type.FRUIT, "Orange", Size.M, 60);
        system.add(Type.FRUIT, "Orange", Size.L, 70);
        system.add(Type.FRUIT, "Banana", Size.M, 105);
        system.add(Type.FRUIT, "Banana", Size.L, 125);
        system.add(Type.FRUIT, "Tomato", Size.M, 20);
        system.add(Type.FRUIT, "Tomato", Size.L, 70);

        // Populate Chocolates via the unified system
        system.add(Type.CHOCOLATE, "DairyMilk", Size.S, 100);
        system.add(Type.CHOCOLATE, "DairyMilk", Size.M, 120);
        system.add(Type.CHOCOLATE, "Bounty", Size.S, 100);
        system.add(Type.CHOCOLATE, "Snickers", Size.M, 100);

        // Define expected output for fruits
        List<String> expectedFruits = List.of(
            "Banana, L, 125cal", "Apple, L, 105cal", "Banana, M, 105cal", 
            "Apple, M, 95cal", "Apple, M, 95cal", "Orange, L, 70cal", 
            "Tomato, L, 70cal", "Orange, M, 60cal", "Tomato, M, 20cal"
        );

        // Define expected output for chocolates
        List<String> expectedChocolates = List.of(
            "DairyMilk, M, 120cal", "Snickers, M, 100cal", 
            "DairyMilk, S, 100cal", "Bounty, S, 100cal"
        );

        // Run validation
        evaluateTest("Fruit Basket", system, Type.FRUIT, expectedFruits); // Test fruit extraction
        evaluateTest("Chocolate Basket", system, Type.CHOCOLATE, expectedChocolates); // Test chocolate extraction
    } // Closes main method

    private static void evaluateTest(String name, DualBasketSystem sys, Type type, List<String> expected) { // Helper to test
        List<String> actual = new ArrayList<>(); // List to hold polled data
        while (!sys.isEmpty(type)) { // Loop until requested basket is empty
            Item i = sys.fetch(type); // Put hand in the specific basket
            actual.add(i.name() + ", " + i.size() + ", " + i.cal() + "cal"); // Format string
        } // Closes loop
        System.out.println(name + " Test: " + (expected.equals(actual) ? "PASS" : "FAIL")); // Print result
    } // Closes helper
} // Closes class