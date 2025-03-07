package com.interview.notes.code.year.y2025.march.amazon.test1;

import java.util.*;
import java.util.stream.*;

public class AmazonSearchAutocomplete {

    // Method to simulate product search autocomplete
    public static List<String> getAutocompleteSuggestions(List<String> products, String userInput) {
        String lowerInput = userInput.toLowerCase();

        // Stream products and filter based on user input
        return products.stream()
                .filter(product -> product.toLowerCase().startsWith(lowerInput)) // Match starting characters
                .sorted() // Sort alphabetically
                .collect(Collectors.toList());
    }

    // Test method to validate autocomplete logic
    public static void runTests() {
        List<String> products = Arrays.asList("handsoap", "hairbrush", "hammer", "hat", "hanger", "headphones");

        // Basic test case
        testAutocomplete(products, "ha", Arrays.asList("hairbrush", "hammer", "handsoap", "hanger", "hat"));

        // Edge case: No matches
        testAutocomplete(products, "xyz", Collections.emptyList());

        // Edge case: Single character
        testAutocomplete(products, "h", Arrays.asList("hairbrush", "hammer", "handsoap", "hanger", "hat", "headphones"));

        // Large input test
        List<String> largeProductList = IntStream.range(0, 1000000)
                .mapToObj(i -> "product" + i)
                .collect(Collectors.toList());
        largeProductList.add("hammer");

        testAutocomplete(largeProductList, "ham", Collections.singletonList("hammer"));
    }

    // Helper method for testing with result validation
    private static void testAutocomplete(List<String> products, String input, List<String> expected) {
        List<String> actual = getAutocompleteSuggestions(products, input);
        if (actual.equals(expected)) {
            System.out.println("Test with input '" + input + "' PASSED");
        } else {
            System.out.println("Test with input '" + input + "' FAILED");
            System.out.println("Expected: " + expected);
            System.out.println("Actual: " + actual);
        }
    }

    // Entry point
    public static void main(String[] args) {
        runTests();
    }
}
