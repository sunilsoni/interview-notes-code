package com.interview.notes.code.year.y2026.feb.common.test11;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.IntStream;

// Using Java 14+ 'record' to instantly create an immutable Product class with zero boilerplate (no getters/setters needed).
record Product(int id, String name, String description, Double price) {} // Defines our custom object with the requested fields.

public class GenericSecondLargest { // Declares the main class to hold our generic logic and tests.

    // Generic method: <T> is the object type (e.g., Product), <U> is the field type to compare (e.g., Double for price).
    static <T, U extends Comparable<? super U>> Optional<T> findGeneric(List<T> items, Function<T, U> extractor) { // Method signature accepting the list and the field extractor.
        if (items == null) return Optional.empty(); // Prevents system crashes if a null list is passed.
        
        return items.stream() // Step 1: Start a stream on the generic objects.
            .map(extractor) // Transforms the stream of Objects (Products) into a stream of just their target values (Prices).
            .distinct() // Filters out duplicate values so we only have strictly unique prices.
            .sorted(Comparator.reverseOrder()) // Sorts those unique values from highest to lowest.
            .skip(1) // Skips the absolute highest value in the stream.
            .findFirst() // Grabs the second highest distinct value.
            .flatMap(secondVal -> items.stream() // If we found a second highest value, we stream the original objects again.
                .filter(item -> extractor.apply(item).equals(secondVal)) // Finds the object whose field exactly matches that second highest value.
                .findFirst() // Returns that specific matched object safely wrapped in an Optional.
            ); // Closes the flatMap pipeline.
    } // Closes the generic method.

    public static void main(String[] args) { // Main method to execute all PASS/FAIL testing.
        
        // --- STANDARD & EDGE CASE TESTS --- //
        var p1 = new Product(1, "A", "Desc", 100.0); // Creates product with price 100.
        var p2 = new Product(2, "B", "Desc", 150.0); // Creates product with price 150 (Highest).
        var p3 = new Product(3, "C", "Desc", 150.0); // Creates duplicate highest price product.
        var p4 = new Product(4, "D", "Desc", 120.0); // Creates product with price 120 (Second highest distinct).
        
        var list1 = List.of(p1, p2, p3, p4); // Bundles products into a test list.
        test(list1, p4, "Basic Products", Product::price); // Tests finding the second highest price. Expects p4.
        
        var list2 = List.of(p1); // Creates a list with only one product.
        test(list2, null, "Single Product", Product::price); // Tests edge case. Expects null because no second largest exists.

        // --- LARGE DATA TEST --- //
        var largeList = IntStream.rangeClosed(1, 100000).boxed() // Generates 100,000 numbers.
            .map(i -> new Product(i, "Name" + i, "Desc", (double) i)) // Converts each number into a Product object with a matching price.
            .toList(); // Collects the massive dataset into a list.
            
        var expectedLarge = largeList.get(99998); // The 99,999th item will have the second highest price (99999.0).
        test(largeList, expectedLarge, "Large Data Products", Product::price); // Tests performance and correctness on the massive dataset.
    } // Closes the main method.

    // Generic test helper: Takes the list, expected object, test name, and the field extractor.
    static <T, U extends Comparable<? super U>> void test(List<T> input, T expected, String name, Function<T, U> extractor) { // Test helper signature.
        var result = findGeneric(input, extractor).orElse(null); // Runs the target method, defaults to null if empty.
        var status = (Objects.equals(expected, result)) ? "PASS" : "FAIL"; // Validates if the actual result matches expected.
        System.out.println(status + " - " + name + " | Expected ID: " + (expected != null ? expected.hashCode() : "null")); // Prints clean PASS/FAIL output.
    } // Closes the test method.

} // Closes the application class.