package com.interview.notes.code.year.y2026.april.common.test1;

import java.util.List; // Import List interface to hold our collection of cart items
import java.util.stream.IntStream; // Import IntStream to easily generate large datasets for testing

// Use Java 'record' feature to create an immutable data class, saving dozens of lines of standard boilerplate code
record ShoppingCart(String name, double price, int quantity) {}

// Main class to encapsulate our processing logic and our custom testing framework
public class CartProcessor {

    // Main method serves as the entry point and executes our tests without needing JUnit
    public static void main(String[] args) {
        
        // Create the user's specific test case using Java 9+ List.of() for brevity instead of Arrays.asList
        List<ShoppingCart> defaultList = List.of(
            new ShoppingCart("Apple", 80.0, 6), // 80 * 6 = 480.0
            new ShoppingCart("Chair", 100.0, 10), // 100 * 10 = 1000.0
            new ShoppingCart("Banana", 20.0, 12)  // 20 * 12 = 240.0
        ); // Expected Total = 1720.0

        // Run the first test, calculate the total, and check if it exactly matches our expected math (1720.0)
        boolean test1 = calculateTotal(defaultList) == 1720.0;
        
        // Use a ternary operator to print PASS if the boolean is true, or FAIL if it is false
        System.out.println("Test 1 (Default Data): " + (test1 ? "PASS" : "FAIL"));

        // Generate a very large dataset (1 million items) to test system performance and handle large inputs
        List<ShoppingCart> largeList = IntStream.range(0, 1000000) // Create a stream of numbers from 0 to 999,999
            .mapToObj(i -> new ShoppingCart("Item"+i, 10.0, 2)) // Turn each number into a ShoppingCart item costing 20.0 total
            .toList(); // Use Java 16+ Stream.toList() feature to collect items directly into an unmodifiable list
        
        // Run the large data test and verify the math (1,000,000 items * 20.0 cost per item = 20,000,000.0)
        boolean test2 = calculateTotal(largeList) == 20000000.0;
        
        // Print PASS or FAIL based on whether the large dataset test calculated correctly
        System.out.println("Test 2 (Large Data - 1M items): " + (test2 ? "PASS" : "FAIL"));
    }

    // Processing method to calculate the total cost, accepting our List of ShoppingCart items
    public static double calculateTotal(List<ShoppingCart> cart) {
        
        // Return the final calculated double value back to whoever called the method
        return cart.stream() // Convert the list into a Stream to allow functional-style data processing
            .mapToDouble(item -> item.price() * item.quantity()) // Multiply price by quantity for each item, returning a stream of numbers
            .sum(); // Automatically add all the numbers in the stream together to get the final total
    }
}