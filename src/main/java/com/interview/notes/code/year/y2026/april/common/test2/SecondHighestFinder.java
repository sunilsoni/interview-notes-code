package com.interview.notes.code.year.y2026.april.common.test2;

import java.util.Comparator; // Import Comparator to help us define how to sort the items
import java.util.List; // Import List interface to hold our collection of cart items
import java.util.stream.IntStream; // Import IntStream to easily generate large datasets for testing

// Use Java 21 'record' to create an immutable class, avoiding getters/setters boilerplate
record ShoppingCart(String name, double price, int quantity) {}

// Main class to encapsulate our logic and our simple testing framework
public class SecondHighestFinder {

    // Main method serves as the entry point and executes our tests without needing JUnit
    public static void main(String[] args) {
        
        // Create the specific test case you provided using Java's List.of()
        List<ShoppingCart> defaultList = List.of(
            new ShoppingCart("Apple", 80.0, 6),   // 2nd highest price (Target)
            new ShoppingCart("Chair", 100.0, 10), // 1st highest price
            new ShoppingCart("Banana", 20.0, 12)  // 3rd highest price
        ); 
        
        // Find the second highest item and verify if its name is indeed "Apple"
        boolean test1 = findSecondHighest(defaultList).name().equals("Apple");
        
        // Print PASS if the boolean is true (it found Apple), or FAIL if it didn't
        System.out.println("Test 1 (Default Data): " + (test1 ? "PASS" : "FAIL"));

        // Generate a large dataset (100,000 items) to test system performance with large inputs
        List<ShoppingCart> largeList = IntStream.range(0, 100000) // Create a stream from 0 to 99,999
            .mapToObj(i -> new ShoppingCart("Item"+i, i, 1)) // Make price equal to 'i' (0.0 up to 99999.0)
            .toList(); // Collect items into a list using Java 16+ Stream.toList()
        
        // The highest price is 99999.0 ("Item99999"), so the second highest MUST be 99998.0 ("Item99998")
        boolean test2 = findSecondHighest(largeList).name().equals("Item99998");
        
        // Print PASS or FAIL based on whether the large dataset test found the correct 2nd item
        System.out.println("Test 2 (Large Data - 100k items): " + (test2 ? "PASS" : "FAIL"));
    }

    // Processing method to find the second highest priced item, returning a ShoppingCart object
    public static ShoppingCart findSecondHighest(List<ShoppingCart> cart) {
        
        // Return the final found object back to the caller
        return cart.stream() // Convert the list into a Stream to allow functional-style data processing
            .sorted(Comparator.comparingDouble(ShoppingCart::price).reversed()) // Sort by price descending (highest first)
            .skip(1) // Skip exactly 1 element (which drops the highest priced item)
            .findFirst() // Grab the very next element in the stream (which is the second highest)
            .orElse(null); // Safely return null if the list somehow had fewer than 2 items to prevent crashes
    }
}