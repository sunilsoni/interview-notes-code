package com.interview.notes.code.year.y2025.december.common.test1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

// Java 17 Record - compact way to define immutable data class
// Record automatically generates constructor, getters, equals, hashCode, toString
record Product(String name, int mark) {
}

public class ProductSorter {

    /**
     * Sorts products by mark (0 first, 1 last) and then by name alphabetically
     *
     * @param products - list of products to sort
     * @return sorted list of products
     */
    public static List<Product> sortProducts(List<Product> products) {

        // Using Stream API to sort the products
        return products.stream()
                // First compare by mark (0 comes before 1)
                // Then compare by name alphabetically within same mark
                .sorted(Comparator
                        .comparingInt(Product::mark)  // Primary sort: by mark (0 < 1)
                        .thenComparing(Product::name)) // Secondary sort: by name A-Z
                // Collect sorted stream back to list
                .collect(Collectors.toList());
    }

    /**
     * Test method to check if actual result matches expected result
     *
     * @param testName - name of the test case
     * @param input    - input list of products
     * @param expected - expected sorted output
     */
    public static void runTest(String testName, List<Product> input, List<Product> expected) {

        // Call the sorting method
        List<Product> actual = sortProducts(input);

        // Check if actual matches expected
        boolean passed = actual.equals(expected);

        // Print result with PASS or FAIL
        System.out.println("Test: " + testName);
        System.out.println("Input: " + input);
        System.out.println("Expected: " + expected);
        System.out.println("Actual: " + actual);
        System.out.println("Result: " + (passed ? "✓ PASS" : "✗ FAIL"));
        System.out.println("-".repeat(60));
    }

    public static void main(String[] args) {

        System.out.println("=".repeat(60));
        System.out.println("PRODUCT SORTING TEST CASES");
        System.out.println("=".repeat(60));

        // ============ TEST CASE 1: Basic Test (5 products as per requirement) ============
        // Create 5 product objects with different names and marks
        List<Product> test1Input = List.of(
                new Product("Banana", 1),   // mark=1, should go to right side
                new Product("Apple", 0),    // mark=0, should go to left side
                new Product("Cherry", 1),   // mark=1, should go to right side
                new Product("Date", 0),     // mark=0, should go to left side
                new Product("Elderberry", 0) // mark=0, should go to left side
        );

        // Expected: first all mark=0 sorted by name, then all mark=1 sorted by name
        List<Product> test1Expected = List.of(
                new Product("Apple", 0),      // mark=0, name starts with A
                new Product("Date", 0),       // mark=0, name starts with D
                new Product("Elderberry", 0), // mark=0, name starts with E
                new Product("Banana", 1),     // mark=1, name starts with B
                new Product("Cherry", 1)      // mark=1, name starts with C
        );

        runTest("Basic Test - 5 Products", test1Input, test1Expected);

        // ============ TEST CASE 2: All products have mark=0 ============
        List<Product> test2Input = List.of(
                new Product("Zebra", 0),
                new Product("Apple", 0),
                new Product("Mango", 0)
        );

        // Expected: sorted alphabetically since all have same mark
        List<Product> test2Expected = List.of(
                new Product("Apple", 0),
                new Product("Mango", 0),
                new Product("Zebra", 0)
        );

        runTest("All Mark=0", test2Input, test2Expected);

        // ============ TEST CASE 3: All products have mark=1 ============
        List<Product> test3Input = List.of(
                new Product("Cat", 1),
                new Product("Ant", 1),
                new Product("Bird", 1)
        );

        // Expected: sorted alphabetically since all have same mark
        List<Product> test3Expected = List.of(
                new Product("Ant", 1),
                new Product("Bird", 1),
                new Product("Cat", 1)
        );

        runTest("All Mark=1", test3Input, test3Expected);

        // ============ TEST CASE 4: Empty List ============
        List<Product> test4Input = List.of();
        List<Product> test4Expected = List.of();

        runTest("Empty List", test4Input, test4Expected);

        // ============ TEST CASE 5: Single Element ============
        List<Product> test5Input = List.of(new Product("Solo", 0));
        List<Product> test5Expected = List.of(new Product("Solo", 0));

        runTest("Single Element", test5Input, test5Expected);

        // ============ TEST CASE 6: Already Sorted ============
        List<Product> test6Input = List.of(
                new Product("A", 0),
                new Product("B", 0),
                new Product("C", 1),
                new Product("D", 1)
        );

        List<Product> test6Expected = List.of(
                new Product("A", 0),
                new Product("B", 0),
                new Product("C", 1),
                new Product("D", 1)
        );

        runTest("Already Sorted", test6Input, test6Expected);

        // ============ TEST CASE 7: Reverse Order ============
        List<Product> test7Input = List.of(
                new Product("D", 1),
                new Product("C", 1),
                new Product("B", 0),
                new Product("A", 0)
        );

        List<Product> test7Expected = List.of(
                new Product("A", 0),
                new Product("B", 0),
                new Product("C", 1),
                new Product("D", 1)
        );

        runTest("Reverse Order", test7Input, test7Expected);

        // ============ TEST CASE 8: Same Names Different Marks ============
        List<Product> test8Input = List.of(
                new Product("Apple", 1),
                new Product("Apple", 0),
                new Product("Banana", 1),
                new Product("Banana", 0)
        );

        List<Product> test8Expected = List.of(
                new Product("Apple", 0),
                new Product("Banana", 0),
                new Product("Apple", 1),
                new Product("Banana", 1)
        );

        runTest("Same Names Different Marks", test8Input, test8Expected);

        // ============ TEST CASE 9: Large Data Test ============
        System.out.println("Running Large Data Test...");

        // Create large dataset with 100000 products
        List<Product> largeInput = new ArrayList<>();
        Random random = new Random(42); // Fixed seed for reproducibility

        // Generate 100000 random products
        for (int i = 0; i < 100000; i++) {
            // Create random product name
            String name = "Product_" + String.format("%06d", i);
            // Random mark either 0 or 1
            int mark = random.nextInt(2);
            // Add to list
            largeInput.add(new Product(name, mark));
        }

        // Measure time for sorting
        long startTime = System.currentTimeMillis();
        List<Product> largeResult = sortProducts(largeInput);
        long endTime = System.currentTimeMillis();

        // Verify large data result
        boolean largeTestPassed = verifyLargeSorting(largeResult);

        System.out.println("Test: Large Data Test (100,000 products)");
        System.out.println("Time taken: " + (endTime - startTime) + " ms");
        System.out.println("Result: " + (largeTestPassed ? "✓ PASS" : "✗ FAIL"));
        System.out.println("-".repeat(60));

        // ============ TEST CASE 10: Names with Special Characters ============
        List<Product> test10Input = List.of(
                new Product("Zebra!", 1),
                new Product("Apple@", 0),
                new Product("123Num", 0),
                new Product("_Under", 1)
        );

        List<Product> test10Expected = List.of(
                new Product("123Num", 0),  // Numbers come before letters in ASCII
                new Product("Apple@", 0),
                new Product("Zebra!", 1),
                new Product("_Under", 1)   // Underscore comes after letters
        );

        runTest("Names with Special Characters", test10Input, test10Expected);

        System.out.println("=".repeat(60));
        System.out.println("ALL TEST CASES COMPLETED");
        System.out.println("=".repeat(60));
    }

    /**
     * Verifies if large sorted list is correctly sorted
     *
     * @param sorted - the sorted list to verify
     * @return true if correctly sorted, false otherwise
     */
    public static boolean verifyLargeSorting(List<Product> sorted) {

        // Check each adjacent pair
        for (int i = 0; i < sorted.size() - 1; i++) {

            // Get current and next product
            Product current = sorted.get(i);
            Product next = sorted.get(i + 1);

            // Check mark ordering: 0 should come before 1
            if (current.mark() > next.mark()) {
                // Found a 1 before 0 - invalid
                return false;
            }

            // If same mark, check name ordering
            if (current.mark() == next.mark()) {
                // Names should be in alphabetical order
                if (current.name().compareTo(next.name()) > 0) {
                    // Name order wrong - invalid
                    return false;
                }
            }
        }

        // All checks passed
        return true;
    }
}