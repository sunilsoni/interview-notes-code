package com.interview.notes.code.year.y2025.feb.common.test5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Product {
    private int id;
    private String name;
    private String category;

    // Constructor
    public Product(int id, String name, String category) {
        this.id = id;
        this.name = name;
        this.category = category;
    }

    // Getters
    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "Product{id=" + id + ", name='" + name + "', category='" + category + "'}";
    }
}

public class ProductCategorizer {
    public static Map<String, List<Product>> groupProductsByCategory(List<Product> products) {
        return products.stream()
                .collect(Collectors.groupingBy(Product::getCategory));
    }

    public static void main(String[] args) {
        // Test case 1: Normal case
        List<Product> products = Arrays.asList(
                new Product(1, "Galaxy", "mobile"),
                new Product(2, "iPhone", "mobile"),
                new Product(3, "MacBook", "laptop"),
                new Product(4, "Dell", "laptop")
        );

        System.out.println("Test Case 1: Normal case");
        Map<String, List<Product>> result = groupProductsByCategory(products);
        boolean pass = result.size() == 2 &&
                result.get("mobile").size() == 2 &&
                result.get("laptop").size() == 2;
        System.out.println("Result: " + (pass ? "PASS" : "FAIL"));
        System.out.println("Grouped products: " + result);

        // Test case 2: Empty list
        System.out.println("\nTest Case 2: Empty list");
        result = groupProductsByCategory(new ArrayList<>());
        pass = result.isEmpty();
        System.out.println("Result: " + (pass ? "PASS" : "FAIL"));

        // Test case 3: Large data input
        List<Product> largeInput = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            largeInput.add(new Product(i, "Product" + i, "Category" + (i % 5)));
        }

        System.out.println("\nTest Case 3: Large data input (10000 products)");
        long startTime = System.currentTimeMillis();
        result = groupProductsByCategory(largeInput);
        long endTime = System.currentTimeMillis();
        pass = result.size() == 5 && // Should have 5 categories
                result.values().stream().mapToInt(List::size).sum() == 10000;
        System.out.println("Result: " + (pass ? "PASS" : "FAIL"));
        System.out.println("Processing time: " + (endTime - startTime) + "ms");
    }
}
