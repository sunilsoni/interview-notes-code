package com.interview.notes.code.year.y2025.may.common.test5;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OrderAnalysis {
    public static void main(String[] args) {
        // Create test data
        List<Order> orders = createTestData();

        // Process orders using Stream API
        Map<Long, Map<String, Double>> result = orders.stream()
                .collect(Collectors.groupingBy(
                        Order::getCustomerId,
                        Collectors.flatMapping(
                                order -> order.getItems().stream(),
                                Collectors.groupingBy(
                                        OrderItem::getProductCategory,
                                        Collectors.summingDouble(OrderItem::getAmount)
                                )
                        )
                ));

        // Print results
        printResults(result);
    }

    // Test data creation
    private static List<Order> createTestData() {
        List<Order> orders = new ArrayList<>();

        // Customer 1 orders
        Order order1 = new Order(1L);
        order1.addItem(new OrderItem("Electronics", 1000.0));
        order1.addItem(new OrderItem("Books", 50.0));

        Order order2 = new Order(1L);
        order2.addItem(new OrderItem("Electronics", 500.0));
        order2.addItem(new OrderItem("Books", 100.0));

        // Customer 2 orders
        Order order3 = new Order(2L);
        order3.addItem(new OrderItem("Clothing", 250.0));
        order3.addItem(new OrderItem("Electronics", 800.0));

        Order order4 = new Order(2L);
        order4.addItem(new OrderItem("Clothing", 200.0));
        order4.addItem(new OrderItem("Electronics", 1200.0));

        orders.add(order1);
        orders.add(order2);
        orders.add(order3);
        orders.add(order4);

        return orders;
    }

    // Print results in a formatted way
    private static void printResults(Map<Long, Map<String, Double>> result) {
        result.forEach((customerId, categoryMap) -> {
            System.out.println("\nCustomer ID: " + customerId);
            System.out.println("------------------------");
            categoryMap.forEach((category, total) -> {
                System.out.printf("%-12s: $%,.2f%n", category, total);
            });
        });
    }
}

class Order {
    private Long customerId;
    private List<OrderItem> items;

    public Order(Long customerId) {
        this.customerId = customerId;
        this.items = new ArrayList<>();
    }

    public Long getCustomerId() {
        return customerId;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void addItem(OrderItem item) {
        items.add(item);
    }
}

class OrderItem {
    private String productCategory;
    private Double amount;

    public OrderItem(String productCategory, Double amount) {
        this.productCategory = productCategory;
        this.amount = amount;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public Double getAmount() {
        return amount;
    }
}
