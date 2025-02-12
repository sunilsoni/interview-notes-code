package com.interview.notes.code.year.y2025.feb25.common.test1;

import java.util.*;
import java.util.stream.Collectors;

public class OrderProcessor {

    /**
     * Filters orders with amount greater than 500 dollars, groups them by region,
     * and returns a map with the region as key and the total amount as value.
     *
     * @param orders List of orders.
     * @return Map from region to total order value.
     */
    public static Map<String, Double> getTotalValueByRegion(List<Order> orders) {
        // Using Java 8 streams to filter, group and sum order amounts.
        return orders.stream()
                .filter(order -> order.getAmount() > 500) // Only orders > $500
                .collect(Collectors.groupingBy(Order::getRegion,
                        Collectors.summingDouble(Order::getAmount)));
    }

    /**
     * A helper method to compare two maps of String to Double.
     */
    public static boolean mapsAreEqual(Map<String, Double> m1, Map<String, Double> m2) {
        if (m1.size() != m2.size()) {
            return false;
        }
        for (String key : m1.keySet()) {
            // Use a tolerance for double comparisons
            if (!m2.containsKey(key) || Math.abs(m1.get(key) - m2.get(key)) > 1e-9) {
                return false;
            }
        }
        return true;
    }

    /**
     * Main method that runs test cases and prints PASS/FAIL for each.
     */
    public static void main(String[] args) {
        boolean allTestsPassed = true;

        // Test Case 1: Normal case with orders above and below $500.
        List<Order> orders1 = Arrays.asList(
                new Order(600, "North"),
                new Order(750, "South"),
                new Order(400, "North"),
                new Order(800, "North"),
                new Order(550, "South"),
                new Order(499, "East")  // Should be filtered out.
        );
        Map<String, Double> expected1 = new HashMap<>();
        expected1.put("North", (double) (600 + 800)); // 1400
        expected1.put("South", (double) (750 + 550)); // 1300

        Map<String, Double> result1 = getTotalValueByRegion(orders1);
        if (mapsAreEqual(expected1, result1)) {
            System.out.println("Test Case 1: PASS");
        } else {
            System.out.println("Test Case 1: FAIL");
            allTestsPassed = false;
        }

        // Test Case 2: Edge case with orders exactly equal to $500.
        List<Order> orders2 = Arrays.asList(
                new Order(500, "East"),
                new Order(500, "West"),
                new Order(501, "East")
        );
        // Only order with 501 should be included.
        Map<String, Double> expected2 = new HashMap<>();
        expected2.put("East", 501.0);

        Map<String, Double> result2 = getTotalValueByRegion(orders2);
        if (mapsAreEqual(expected2, result2)) {
            System.out.println("Test Case 2: PASS");
        } else {
            System.out.println("Test Case 2: FAIL");
            allTestsPassed = false;
        }

        // Test Case 3: Edge case with an empty list of orders.
        List<Order> orders3 = Collections.emptyList();
        Map<String, Double> expected3 = new HashMap<>();
        Map<String, Double> result3 = getTotalValueByRegion(orders3);
        if (mapsAreEqual(expected3, result3)) {
            System.out.println("Test Case 3: PASS");
        } else {
            System.out.println("Test Case 3: FAIL");
            allTestsPassed = false;
        }

        // Test Case 4: Large Data Set
        List<Order> orders4 = new ArrayList<>();
        Map<String, Double> expected4 = new HashMap<>();
        // Generate 1,000,000 orders across 5 regions.
        String[] regions = {"North", "South", "East", "West", "Central"};
        Random random = new Random();
        int largeCount = 1_000_000;
        for (int i = 0; i < largeCount; i++) {
            // Generate amount between 100 and 1000.
            double amount = 100 + random.nextDouble() * 900;
            // Pick a region randomly.
            String region = regions[random.nextInt(regions.length)];
            orders4.add(new Order(amount, region));
            // Only add to expected if order > 500.
            if (amount > 500) {
                expected4.put(region, expected4.getOrDefault(region, 0.0) + amount);
            }
        }

        Map<String, Double> result4 = getTotalValueByRegion(orders4);
        if (mapsAreEqual(expected4, result4)) {
            System.out.println("Test Case 4 (Large Data): PASS");
        } else {
            System.out.println("Test Case 4 (Large Data): FAIL");
            allTestsPassed = false;
        }

        if (allTestsPassed) {
            System.out.println("All tests passed!");
        } else {
            System.out.println("Some tests failed. Please review your code.");
        }
    }

    // Order class representing each order with an amount and region.
    static class Order {
        private double amount;
        private String region;

        public Order(double amount, String region) {
            this.amount = amount;
            this.region = region;
        }

        public double getAmount() {
            return amount;
        }

        public String getRegion() {
            return region;
        }

        @Override
        public String toString() {
            return "Order{" +
                    "amount=" + amount +
                    ", region='" + region + '\'' +
                    '}';
        }
    }
}