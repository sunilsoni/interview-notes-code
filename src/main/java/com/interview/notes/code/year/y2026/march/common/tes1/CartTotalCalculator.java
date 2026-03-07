package com.interview.notes.code.year.y2026.march.common.tes1;

record ShoppingCart(String name, double price, int quantity) {} // Creates a concise, immutable data class representing a cart item, automatically generating a constructor and accessors

public class CartTotalCalculator { // Defines the main class that encapsulates our logic and testing
    
    public static void main(String[] args) { // The entry point of our Java program where execution begins
        runTest("Standard Case", createStandardList(), 1960.0); // Runs the test with your provided list, expecting 1960.0 ((120*4) + (80*6) + (100*10))
        runTest("Large Data Case", createLargeList(), 1500000.0); // Runs a stress test with 10,000 items to ensure it handles large data inputs properly
        runTest("Edge Case (No items > 50)", createCheapList(), 0.0); // Runs a test where no items meet the price condition, expecting 0.0
    } // Closes the main method
    
    static double calculateTotal(java.util.List<ShoppingCart> list) { // Method that takes a list of cart items and returns the total price as a double
        return list.stream() // Converts the incoming list into a sequence of elements (a Stream) for functional processing
            .filter(item -> item.price() > 50) // Evaluates each item and keeps only those where the price attribute is greater than 50
            .mapToDouble(item -> item.price() * item.quantity()) // Transforms each remaining item into a total cost (price multiplied by quantity)
            .sum(); // Aggregates all the individual total costs into one final combined sum
    } // Closes the calculation method
    
    static void runTest(String testName, java.util.List<ShoppingCart> list, double expected) { // A custom testing method to validate outcomes without needing JUnit
        var actual = calculateTotal(list); // Uses Java local variable type inference ('var') to store the calculated result
        if (actual == expected) { // Compares the calculated result against our expected result
            System.out.println(testName + " : PASS"); // If they match, prints a success message to the console
        } else { // Fallback block if the results do not match
            System.out.println(testName + " : FAIL (Expected: " + expected + ", Got: " + actual + ")"); // Prints a failure message detailing what went wrong
        } // Closes the if-else evaluation block
    } // Closes the test runner method
    
    static java.util.List<ShoppingCart> createStandardList() { // Helper method to generate the exact dataset you provided in the prompt
        return java.util.List.of( // Uses the modern List.of() factory method to create an unmodifiable list with minimal text
            new ShoppingCart("Tables", 120.0, 4), // Instantiates the first cart item and adds it to the list
            new ShoppingCart("Books", 80.0, 6), // Instantiates the second cart item and adds it to the list
            new ShoppingCart("Chair", 100.0, 10) // Instantiates the third cart item and adds it to the list
        ); // Closes the List.of() method
    } // Closes the standard list creation method

    static java.util.List<ShoppingCart> createLargeList() { // Helper method to generate a massive dataset to prove stability
        var largeList = new java.util.ArrayList<ShoppingCart>(); // Creates a new resizable ArrayList to hold thousands of items
        for (int i = 0; i < 10000; i++) { // Initiates a loop that will run exactly 10,000 times
            largeList.add(new ShoppingCart("BulkItem", 150.0, 1)); // Creates a new item with a price > 50 and adds it to the list
        } // Closes the for-loop
        return largeList; // Returns the populated list containing 10,000 items
    } // Closes the large list creation method
    
    static java.util.List<ShoppingCart> createCheapList() { // Helper method for an edge case where items are too cheap
        return java.util.List.of( // Opens the unmodifiable list creator
            new ShoppingCart("Pen", 10.0, 5) // Adds an item with a price well below 50
        ); // Closes the list creation
    } // Closes the cheap list method
    
} // Closes the main application class