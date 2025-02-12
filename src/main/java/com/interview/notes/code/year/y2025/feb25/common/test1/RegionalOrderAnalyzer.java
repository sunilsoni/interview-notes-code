package com.interview.notes.code.year.y2025.feb25.common.test1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RegionalOrderAnalyzer {
    public static Map<String, Double> analyzeOrders(List<Order> orders) {
        return orders.stream()
                .filter(order -> order.value > 500)
                .collect(Collectors.groupingBy(
                        order -> order.region,
                        Collectors.summingDouble(order -> order.value)
                ));
    }

    public static void main(String[] args) {
        // Test Case 1: Normal case with mixed values
        List<Order> testCase1 = Arrays.asList(
                new Order("North", 600.0, "Laptop"),
                new Order("North", 400.0, "Phone"),
                new Order("South", 700.0, "TV"),
                new Order("East", 800.0, "Console")
        );

        // Test Case 2: Empty list
        List<Order> testCase2 = new ArrayList<>();

        // Test Case 3: All values below 500
        List<Order> testCase3 = Arrays.asList(
                new Order("North", 100.0, "Book"),
                new Order("South", 200.0, "Pen")
        );

        // Test Case 4: Large dataset
        List<Order> testCase4 = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            testCase4.add(new Order(
                    "Region" + (i % 5),
                    500.0 + (i % 1000),
                    "Product" + i
            ));
        }

        // Run tests
        runTest("Test 1: Normal case", testCase1);
        runTest("Test 2: Empty list", testCase2);
        runTest("Test 3: All values below 500", testCase3);
        runTest("Test 4: Large dataset", testCase4);
    }

    private static void runTest(String testName, List<Order> orders) {
        System.out.println("\n" + testName);
        try {
            long startTime = System.currentTimeMillis();
            Map<String, Double> result = analyzeOrders(orders);
            long endTime = System.currentTimeMillis();

            System.out.println("Results: " + result);
            System.out.println("Execution time: " + (endTime - startTime) + "ms");
            System.out.println("Status: PASS");
        } catch (Exception e) {
            System.out.println("Status: FAIL");
            System.out.println("Error: " + e.getMessage());
        }
    }

    static class Order {
        String region;
        double value;
        String productName;

        Order(String region, double value, String productName) {
            this.region = region;
            this.value = value;
            this.productName = productName;
        }
    }
}
