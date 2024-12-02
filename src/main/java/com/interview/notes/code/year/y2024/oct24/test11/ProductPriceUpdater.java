package com.interview.notes.code.year.y2024.oct24.test11;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ProductPriceUpdater {

    // Method to increase product prices by 10 using Java 8 streams
    public static Map<String, Double> increasePrices(Map<String, Double> products) {
        return products.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue() + 10.0
                ));
    }

    // Test method to verify if all test cases pass
    public static void runTests() {
        // Test case 1: Basic case
        Map<String, Double> products = new HashMap<>();
        products.put("Apple", 80.0);
        products.put("Banana", 50.0);
        products.put("Cherry", 110.0);
        products.put("Date", 90.0);

        Map<String, Double> expected = new HashMap<>();
        expected.put("Apple", 90.0);
        expected.put("Banana", 60.0);
        expected.put("Cherry", 120.0);
        expected.put("Date", 100.0);

        if (test(products, expected)) {
            System.out.println("Test case 1: PASS");
        } else {
            System.out.println("Test case 1: FAIL");
        }

        // Test case 2: Empty map case
        Map<String, Double> emptyProducts = new HashMap<>();
        if (test(emptyProducts, emptyProducts)) {
            System.out.println("Test case 2: PASS");
        } else {
            System.out.println("Test case 2: FAIL");
        }

        // Test case 3: Large input case
        Map<String, Double> largeProducts = new HashMap<>();
        Map<String, Double> expectedLarge = new HashMap<>();
        for (int i = 0; i < 1000000; i++) {
            largeProducts.put("Product" + i, 100.0);
            expectedLarge.put("Product" + i, 110.0);
        }

        if (test(largeProducts, expectedLarge)) {
            System.out.println("Test case 3: PASS");
        } else {
            System.out.println("Test case 3: FAIL");
        }
    }

    // Helper method to test the updated prices
    public static boolean test(Map<String, Double> original, Map<String, Double> expected) {
        Map<String, Double> updatedPrices = increasePrices(original);
        return updatedPrices.equals(expected);
    }

    public static void main(String[] args) {
        runTests();
    }
}
