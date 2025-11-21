package com.interview.notes.code.year.y2025.feb.common.test3;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Product {
    private final int id;
    private final String name;
    private final String category;

    public Product(int id, String name, String category) {
        this.id = id;
        this.name = name;
        this.category = category;
    }

    public String getName() {
        return name;
    }
}

public class ProductNameExtractor {
    public static List<String> getProductNames(Map<String, List<Product>> productMap) {
        return productMap.values()        // Stream of List<Product>
                .stream()                 // Create stream from map values
                .flatMap(List::stream)    // Flatten the lists into a single stream of Products
                .map(Product::getName)    // Transform Product to name
                .collect(Collectors.toList()); // Collect names into List
    }

    public static void main(String[] args) {
        // Create sample data
        Map<String, List<Product>> productMap = new HashMap<>();

        // Add mobile products
        productMap.put("mobile", Arrays.asList(
                new Product(1, "iPhone", "mobile"),
                new Product(2, "Galaxy", "mobile")
        ));

        // Add laptop products
        productMap.put("laptop", Arrays.asList(
                new Product(3, "MacBook", "laptop"),
                new Product(4, "Dell XPS", "laptop")
        ));

        // Get list of product names using streams
        List<String> productNames = getProductNames(productMap);
        System.out.println("Product Names: " + productNames);
        // Output: Product Names: [iPhone, Galaxy, MacBook, Dell XPS]
    }
}
