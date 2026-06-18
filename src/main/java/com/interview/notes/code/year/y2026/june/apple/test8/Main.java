package com.interview.notes.code.year.y2026.june.apple.test8;

import java.util.ArrayList; // Imports ArrayList to store fetched results for assertion
import java.util.Comparator; // Imports Comparator to define the custom sorting rules for items
import java.util.List; // Imports List interface for testing and validation collections
import java.util.PriorityQueue; // Imports PriorityQueue to maintain the ordered state of the basket

enum Size { // Defines the Size enum representing item dimensions
    S, M, L // Declared in ascending order so ordinal values are S=0, M=1, L=2 for easy comparison
} // Closes the enum block

record Item(String name, Size size, int cal, int seq) {} // Java 21 record creating an immutable Item with automatic getters, equals, and hashCode

class Basket { // Defines the Basket class to manage collections of items
    private final PriorityQueue<Item> queue; // Declares the underlying priority queue to store items
    private int seqCounter = 0; // Initializes a sequence counter to track exact insertion order for stable sorting

    public Basket() { // Constructor to initialize a new Basket instance
        this.queue = new PriorityQueue<>( // Instantiates the PriorityQueue with a custom chained comparator
            Comparator.comparingInt(Item::cal).reversed() // Rule 1: Sort by calories descending (highest first)
            .thenComparing((Item i) -> i.size().ordinal(), Comparator.reverseOrder()) // Rule 2: Sort by size descending (L > M > S)
            .thenComparingInt(Item::seq) // Rule 3: Sort by sequence ascending (oldest insertion first)
        ); // Closes the PriorityQueue instantiation
    } // Closes the constructor

    public void add(String name, Size size, int cal) { // Method to insert a new item into the basket
        queue.offer(new Item(name, size, cal, seqCounter++)); // Wraps data into an Item record, assigns current sequence, increments counter, and adds to queue
    } // Closes the add method

    public Item fetch() { // Method to retrieve and remove the highest priority item
        return queue.poll(); // Returns the top element from the queue, or null if empty
    } // Closes the fetch method

    public boolean isEmpty() { // Method to check if the basket has remaining items
        return queue.isEmpty(); // Returns true if the queue size is 0
    } // Closes the isEmpty method
} // Closes the Basket class

public class Main { // Main execution class for running the standalone test suite
    
    public static void main(String[] args) { // Main method entry point
        testFruitBasket(); // Executes the validation logic for the fruit dataset
        testChocolateBasket(); // Executes the validation logic for the chocolate dataset
        testLargeData(); // Executes the stress test for large volume data processing
    } // Closes the main method

    private static void testFruitBasket() { // Method containing the test scenario for fruits
        Basket basket = new Basket(); // Instantiates a new Basket specifically for fruits
        basket.add("Apple", Size.M, 95); // Inserts item 1 from the provided specification
        basket.add("Apple", Size.M, 95); // Inserts item 2 from the provided specification
        basket.add("Apple", Size.L, 105); // Inserts item 3 from the provided specification
        basket.add("Orange", Size.M, 60); // Inserts item 4 from the provided specification
        basket.add("Orange", Size.L, 70); // Inserts item 5 from the provided specification
        basket.add("Banana", Size.M, 105); // Inserts item 6 from the provided specification
        basket.add("Banana", Size.L, 125); // Inserts item 7 from the provided specification
        basket.add("Tomato", Size.M, 20); // Inserts item 8 from the provided specification
        basket.add("Tomato", Size.L, 70); // Inserts item 9 from the provided specification

        List<String> expected = List.of( // Defines the exact expected output order as an immutable list
            "Banana, L, 125cal", // Expected item 1
            "Apple, L, 105cal", // Expected item 2
            "Banana, M, 105cal", // Expected item 3
            "Apple, M, 95cal", // Expected item 4
            "Apple, M, 95cal", // Expected item 5
            "Orange, L, 70cal", // Expected item 6 (Validates insertion order bonus over Tomato)
            "Tomato, L, 70cal", // Expected item 7
            "Orange, M, 60cal", // Expected item 8
            "Tomato, M, 20cal" // Expected item 9
        ); // Closes the expected list definition

        evaluateTest("Fruit Basket", basket, expected); // Passes the basket and expected list to the evaluation helper
    } // Closes the fruit test method

    private static void testChocolateBasket() { // Method containing the test scenario for chocolates
        Basket basket = new Basket(); // Instantiates a new Basket specifically for chocolates
        basket.add("DairyMilk", Size.S, 100); // Inserts item 1 from the provided specification
        basket.add("DairyMilk", Size.M, 120); // Inserts item 2 from the provided specification
        basket.add("Bounty", Size.S, 100); // Inserts item 3 from the provided specification
        basket.add("Snickers", Size.M, 100); // Inserts item 4 from the provided specification

        List<String> expected = List.of( // Defines the exact expected output order as an immutable list
            "DairyMilk, M, 120cal", // Expected item 1
            "Snickers, M, 100cal", // Expected item 2 (Validates size M prioritizes over size S)
            "DairyMilk, S, 100cal", // Expected item 3 (Validates insertion order bonus over Bounty)
            "Bounty, S, 100cal" // Expected item 4
        ); // Closes the expected list definition

        evaluateTest("Chocolate Basket", basket, expected); // Passes the basket and expected list to the evaluation helper
    } // Closes the chocolate test method

    private static void testLargeData() { // Method to verify time complexity and memory handling
        Basket basket = new Basket(); // Instantiates a new Basket for volume testing
        int loadSize = 1_000_000; // Sets a high volume threshold of 1 million items
        for (int i = 0; i < loadSize; i++) { // Loops up to the load size threshold
            basket.add("BulkItem", Size.M, i % 500); // Inserts items with varying calories to force continuous queue re-sorting
        } // Closes the insertion loop
        
        boolean pass = true; // Initializes a flag to track validation success
        int lastCal = Integer.MAX_VALUE; // Sets baseline comparison value to absolute maximum
        for (int i = 0; i < loadSize; i++) { // Loops through the entire populated queue
            Item item = basket.fetch(); // Fetches the highest priority item from the basket
            if (item.cal() > lastCal) pass = false; // Fails the test if a retrieved item has higher calories than the previous one
            lastCal = item.cal(); // Updates the baseline comparison value for the next iteration
        } // Closes the validation loop
        
        System.out.println("Large Data Test (1M records): " + (pass ? "PASS" : "FAIL")); // Outputs the final performance test result
    } // Closes the large data test method

    private static void evaluateTest(String testName, Basket basket, List<String> expected) { // Helper method to automate extraction and assertion
        List<String> actual = new ArrayList<>(); // Initializes a list to hold the formatted results extracted from the basket
        while (!basket.isEmpty()) { // Loops continuously until the basket is completely empty
            Item i = basket.fetch(); // Extracts the next item based on priority rules
            actual.add(i.name() + ", " + i.size() + ", " + i.cal() + "cal"); // Formats the item data into the expected string structure and adds to list
        } // Closes the extraction loop
        
        boolean passed = expected.equals(actual); // Compares the exact contents and order of the expected list against the actual extracted list
        System.out.println(testName + " Test: " + (passed ? "PASS" : "FAIL")); // Prints the primary pass/fail metric
        
        if (!passed) { // Condition triggering only if the test fails for debugging purposes
            System.out.println("Expected: " + expected); // Prints the expected layout to console
            System.out.println("Actual  : " + actual); // Prints the actual failed layout to console
        } // Closes the failure block
    } // Closes the evaluate helper method
} // Closes the Main class