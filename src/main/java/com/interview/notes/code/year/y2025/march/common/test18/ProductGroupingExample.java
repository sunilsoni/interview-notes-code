package com.interview.notes.code.year.y2025.march.common.test18;

import java.util.Arrays;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// Product class
class Product {
    private String name;
    private String category;
    private double price;

    // Constructor
    public Product(String name, String category, double price) {
        this.name = name;
        this.category = category;
        this.price = price;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price +
                '}';
    }
}

public class ProductGroupingExample {
    public static void main(String[] args) {
        // Create a list of products
        List<Product> products = Arrays.asList(
                new Product("iPhone", "Electronics", 999.99),
                new Product("Samsung TV", "Electronics", 799.99),
                new Product("Nike Shoes", "Clothing", 89.99),
                new Product("Adidas T-shirt", "Clothing", 29.99),
                new Product("Book", "Books", 19.99),
                new Product("Magazine", "Books", 9.99),
                new Product("Headphones", "Electronics", 149.99)
        );

        // Method 1: Group by category and calculate total price
        Map<String, Double> categoryTotalPrice = products.stream()
                .collect(Collectors.groupingBy(
                        Product::getCategory,
                        Collectors.summingDouble(Product::getPrice)
                ));

        // Print results
        System.out.println("Total price by category (Method 1):");
        categoryTotalPrice.forEach((category, total) ->
                System.out.println(category + ": $" + String.format("%.2f", total)));

        // Method 2: Group by category and get more detailed information
        Map<String, DoubleSummaryStatistics> categoryStats = products.stream()
                .collect(Collectors.groupingBy(
                        Product::getCategory,
                        Collectors.summarizingDouble(Product::getPrice)
                ));

        System.out.println("\nDetailed statistics by category (Method 2):");
        categoryStats.forEach((category, stats) -> {
            System.out.println(category + ":");
            System.out.println("  Total: $" + String.format("%.2f", stats.getSum()));
            System.out.println("  Average: $" + String.format("%.2f", stats.getAverage()));
            System.out.println("  Count: " + stats.getCount());
            System.out.println("  Max: $" + String.format("%.2f", stats.getMax()));
            System.out.println("  Min: $" + String.format("%.2f", stats.getMin()));
        });

        // Method 3: Group products by category
        Map<String, List<Product>> productsByCategory = products.stream()
                .collect(Collectors.groupingBy(Product::getCategory));

        System.out.println("\nProducts grouped by category (Method 3):");
        productsByCategory.forEach((category, productList) -> {
            System.out.println(category + ":");
            productList.forEach(product ->
                    System.out.println("  - " + product.getName() + ": $" +
                            String.format("%.2f", product.getPrice())));
        });
    }
}
