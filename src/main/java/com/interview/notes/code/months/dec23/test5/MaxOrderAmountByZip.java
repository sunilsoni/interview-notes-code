package com.interview.notes.code.months.dec23.test5;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MaxOrderAmountByZip {
    public static void main(String[] args) {
        // Sample list of orders (replace with your actual data)
        List<Order> orders = Arrays.asList(
                new Order("John", "ZIP1", 100),
                new Order("Alice", "ZIP1", 200),
                new Order("Bob", "ZIP2", 300),
                new Order("Eve", "ZIP2", 400),
                new Order("Charlie", "ZIP3", 500)
        );

        // Group orders by zip code and find the maximum order amount for each zip code
        Map<String, Integer> maxOrderAmountByZip = orders.stream()
                .collect(Collectors.groupingBy(Order::getZipCode,
                        Collectors.collectingAndThen(
                                Collectors.maxBy(Comparator.comparingInt(Order::getOrderAmount)),
                                maxOrder -> maxOrder.map(Order::getOrderAmount).orElse(0)
                        )
                ));

        // Find customers with the maximum order amount for each zip code
        /** Map<String, List<String>> customersWithMaxOrder = orders.stream()
         .collect(Collectors.groupingBy(OrderObj::getZipCode,
         Collectors.collectingAndThen(
         Collectors.toList(),
         orderList -> {
         int maxOrderAmount = maxOrderAmountByZip.get(orderList.get(0).getZipCode());
         return orderList.stream()
         .filter(order -> order.getOrderAmount() == maxOrderAmount)
         .map(Order::getCustomerName)
         .collect(Collectors.toList());
         }
         )
         ));

         // Print the results
         customersWithMaxOrder.forEach((zip, customers) -> {
         int maxAmount = maxOrderAmountByZip.get(zip);
         System.out.println("ZIP Code: " + zip);
         System.out.println("Maximum Order Amount: $" + maxAmount);
         System.out.println("Customers with Maximum Orders: " + customers);
         System.out.println();
         });**/
    }
}

class Order {
    private String customerName;
    private String zipCode;
    private int orderAmount;

    public Order(String customerName, String zipCode, int orderAmount) {
        this.customerName = customerName;
        this.zipCode = zipCode;
        this.orderAmount = orderAmount;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getZipCode() {
        return zipCode;
    }

    public int getOrderAmount() {
        return orderAmount;
    }
}
