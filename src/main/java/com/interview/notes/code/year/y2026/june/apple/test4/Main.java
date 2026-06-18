package com.interview.notes.code.year.y2026.june.apple.test4;

import java.util.ArrayList; // Imports ArrayList to collect actual fetched results
import java.util.Comparator; // Imports Comparator to define sorting rules
import java.util.List; // Imports List to manage our expected test results
import java.util.PriorityQueue; // Imports PriorityQueue for auto-sorting collections

// Defines Size. L=0, M=1, S=2 for natural descending sort without reverseOrder()
enum Size { L, M, S } 

// Creates a base contract (interface) that all items in any basket must follow
interface Item { 
    String name(); // Enforces that all items must return a name
    Size size(); // Enforces that all items must return a size
    int cal(); // Enforces that all items must return a calorie count
    int seq(); // Enforces that all items must return a sequence number for tie-breaking
} // Closes the interface

// Creates a specific Fruit record that implements the Item contract
record Fruit(String name, Size size, int cal, int seq) implements Item {} 

// Creates a specific Chocolate record that implements the Item contract
record Chocolate(String name, Size size, int cal, int seq) implements Item {} 

class Baskets { // Main system class managing the separate queues
    
    // Defines shared sorting rules using the base Item interface so it works for both Fruits and Chocolates
    private final Comparator<Item> rules = Comparator.comparingInt(Item::cal).reversed()
            .thenComparingInt((Item i) -> i.size().ordinal()) // Sorts size (L=0 wins)
            .thenComparingInt(Item::seq); // Tie-breaker for identical items (FIFO)
    
    // Creates a PriorityQueue specifically typed for Fruit, using the shared rules
    private final PriorityQueue<Fruit> fruits = new PriorityQueue<>(rules); 
    
    // Creates a PriorityQueue specifically typed for Chocolate, using the shared rules
    private final PriorityQueue<Chocolate> chocolates = new PriorityQueue<>(rules); 
    
    // Global sequence counter to act as a unique ticket for every item added
    private int seqCounter = 0; 

    // Dedicated method to add fruits, encapsulating the Fruit object creation
    public void addFruit(String name, Size size, int cal) { 
        // Creates a Fruit record, assigns ticket, increments counter, adds to fruit queue
        fruits.offer(new Fruit(name, size, cal, seqCounter++)); 
    } // Closes addFruit

    // Dedicated method to add chocolates, encapsulating the Chocolate object creation
    public void addChocolate(String name, Size size, int cal) { 
        // Creates a Chocolate record, assigns ticket, increments counter, adds to chocolate queue
        chocolates.offer(new Chocolate(name, size, cal, seqCounter++)); 
    } // Closes addChocolate

    // Method to fetch the highest priority Fruit
    public Fruit fetchFruit() { 
        // Pulls and returns the top fruit
        return fruits.poll(); 
    } // Closes fetchFruit

    // Method to fetch the highest priority Chocolate
    public Chocolate fetchChocolate() { 
        // Pulls and returns the top chocolate
        return chocolates.poll(); 
    } // Closes fetchChocolate

    // Method to check if the fruit basket is empty
    public boolean hasNoFruits() { 
        // Returns boolean state of fruit queue
        return fruits.isEmpty(); 
    } // Closes hasNoFruits

    // Method to check if the chocolate basket is empty
    public boolean hasNoChocolates() { 
        // Returns boolean state of chocolate queue
        return chocolates.isEmpty(); 
    } // Closes hasNoChocolates
} // Closes the Baskets class

public class Main { // Application entry point
    
    // Main method where execution begins
    public static void main(String[] args) { 
        // Initializes the dual-basket system
        Baskets baskets = new Baskets(); 
        
        // --- Populating Data ---
        // Uses the dedicated fruit method
        baskets.addFruit("Apple", Size.M, 95); 
        baskets.addFruit("Apple", Size.M, 95); 
        baskets.addFruit("Apple", Size.L, 105); 
        baskets.addFruit("Orange", Size.M, 60); 
        baskets.addFruit("Orange", Size.L, 70); 
        baskets.addFruit("Banana", Size.M, 105); 
        baskets.addFruit("Banana", Size.L, 125); 
        baskets.addFruit("Tomato", Size.M, 20); 
        baskets.addFruit("Tomato", Size.L, 70); 
        
        // Uses the dedicated chocolate method
        baskets.addChocolate("DairyMilk", Size.S, 100); 
        baskets.addChocolate("DairyMilk", Size.M, 120); 
        baskets.addChocolate("Bounty", Size.S, 100); 
        baskets.addChocolate("Snickers", Size.M, 100); 

        // --- Defining Expected Output ---
        // Immutable list of expected fruit strings
        List<String> expFruits = List.of("Banana, L, 125cal", "Apple, L, 105cal", "Banana, M, 105cal", "Apple, M, 95cal", "Apple, M, 95cal", "Orange, L, 70cal", "Tomato, L, 70cal", "Orange, M, 60cal", "Tomato, M, 20cal"); 
        
        // Immutable list of expected chocolate strings
        List<String> expChocs = List.of("DairyMilk, M, 120cal", "Snickers, M, 100cal", "DairyMilk, S, 100cal", "Bounty, S, 100cal"); 

        // --- Executing Tests ---
        // Runs validation on the fruit basket
        testFruits("Fruit", baskets, expFruits); 
        
        // Runs validation on the chocolate basket
        testChocolates("Chocolate", baskets, expChocs); 
        
        // Runs the large volume stress test
        runLargeDataTest(); 
    } // Closes the main method

    // Helper method to validate fruit extraction
    private static void testFruits(String name, Baskets sys, List<String> expected) { 
        // List to hold actual pulled data
        List<String> actual = new ArrayList<>(); 
        // Loops until fruit basket is empty
        while (!sys.hasNoFruits()) { 
            // Pulls top fruit
            Item i = sys.fetchFruit(); 
            // Formats and adds to actual list
            actual.add(i.name() + ", " + i.size() + ", " + i.cal() + "cal"); 
        } // Closes loop
        // Compares lists and prints result
        System.out.println(name + " Test: " + (expected.equals(actual) ? "PASS" : "FAIL")); 
    } // Closes testFruits

    // Helper method to validate chocolate extraction
    private static void testChocolates(String name, Baskets sys, List<String> expected) { 
        // List to hold actual pulled data
        List<String> actual = new ArrayList<>(); 
        // Loops until chocolate basket is empty
        while (!sys.hasNoChocolates()) { 
            // Pulls top chocolate
            Item i = sys.fetchChocolate(); 
            // Formats and adds to actual list
            actual.add(i.name() + ", " + i.size() + ", " + i.cal() + "cal"); 
        } // Closes loop
        // Compares lists and prints result
        System.out.println(name + " Test: " + (expected.equals(actual) ? "PASS" : "FAIL")); 
    } // Closes testChocolates

    // Stress test for system stability
    private static void runLargeDataTest() { 
        // Fresh instance for load test
        Baskets sys = new Baskets(); 
        // Loops 1 million times
        for (int i = 0; i < 1_000_000; i++) { 
            // Adds dummy fruit with oscillating calories
            sys.addFruit("Bulk", Size.M, i % 500); 
        } // Closes insertion loop
        
        // Flag for success
        boolean pass = true; 
        // Baseline comparison variable
        int lastCal = Integer.MAX_VALUE; 
        // Extraction validation loop
        for (int i = 0; i < 1_000_000; i++) { 
            // Pulls top fruit
            Item item = sys.fetchFruit(); 
            // Fails if descending sort is broken
            if (item.cal() > lastCal) pass = false; 
            // Updates baseline
            lastCal = item.cal(); 
        } // Closes extraction loop
        // Prints final result
        System.out.println("Large Data (1M) Test: " + (pass ? "PASS" : "FAIL")); 
    } // Closes runLargeDataTest
} // Closes Main