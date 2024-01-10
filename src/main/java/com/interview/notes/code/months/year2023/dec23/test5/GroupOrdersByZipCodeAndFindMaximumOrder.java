package com.interview.notes.code.months.year2023.dec23.test5;

import java.util.*;
import java.util.stream.Collectors;

public class GroupOrdersByZipCodeAndFindMaximumOrder {
    public static void main(String[] args) {
        // Sample list of orders (replace with your actual data)
        List<OrderObj> orders = Arrays.asList(
                new OrderObj("ZIP1", 100),
                new OrderObj("ZIP1", 200),
                new OrderObj("ZIP2", 300),
                new OrderObj("ZIP2", 400),
                new OrderObj("ZIP3", 500)
        );

        // Group orders by zip code and find the maximum order amount for each zip code
        Map<String, Optional<OrderObj>> maxOrderAmountByZip = orders.stream()
                .collect(Collectors.groupingBy(OrderObj::getZipCode,
                        Collectors.maxBy(Comparator.comparingInt(OrderObj::getOrderAmount))));

        // Iterate over the map and print the results
        maxOrderAmountByZip.forEach((zip, maxOrder) -> {
            int maxAmount = maxOrder.map(OrderObj::getOrderAmount).orElse(0);
            System.out.println("ZIP Code: " + zip);
            System.out.println("Maximum Order Amount: $" + maxAmount);
            System.out.println();
        });
    }
}

