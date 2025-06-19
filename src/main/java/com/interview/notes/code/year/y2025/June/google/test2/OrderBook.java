package com.interview.notes.code.year.y2025.June.google.test2;

import java.util.*;
/*





### **Problem Statement:**

Imagine a marketplace where individuals sell product "ABC."

Sellers enter the market at any time and input their desired selling price for ABC into the market’s computer system. The system then sorts this information (known as the order book) in ascending order of price.
If multiple sellers offer the same price, their entries are further ordered by the time they entered the system (timestamp).
This ensures that the seller with the lowest asking price is always the first to sell when a buyer arrives.

All prices entered are in the range of \$0.01 to \$100.00. Each seller can only submit one order at a time, and each order can only offer one unit of product ABC.

---

### **Input:**

* `seller_id`: A unique identifier for each seller
* `price`: The price the seller is willing to sell ABC for
* `timestamp`: The time the order was submitted

---

### **Design Two Functions:**

#### 1. `insert_order(seller_id, price, timestamp)`

This function inserts the seller’s order into the order book, ensuring proper sorting based on price and timestamp.

#### 2. `get_lowest_seller_id()`

This function returns the unique identifier of the seller currently offering ABC at the lowest price and removes the seller from the order book (their product is sold!).

---
 */
public class OrderBook {
    // TreeMap to store orders sorted by price (primary key)
    // For each price, we have a TreeMap of timestamp to seller_id (secondary sorting)
    private TreeMap<Double, TreeMap<Long, String>> orderBook;

    public OrderBook() {
        // Initialize empty order book
        orderBook = new TreeMap<>();
    }

    public void insert_order(String seller_id, double price, long timestamp) {
        // Validate price range ($0.01 to $100.00)
        if (price < 0.01 || price > 100.00) {
            throw new IllegalArgumentException("Price must be between $0.01 and $100.00");
        }

        // Get or create the TreeMap for this price level
        TreeMap<Long, String> sellersAtPrice = orderBook.computeIfAbsent(price, k -> new TreeMap<>());
        
        // Add the order with timestamp and seller_id
        sellersAtPrice.put(timestamp, seller_id);
    }

    public String get_lowest_seller_id() {
        // Check if order book is empty
        if (orderBook.isEmpty()) {
            return null;
        }

        // Get lowest price entry
        Map.Entry<Double, TreeMap<Long, String>> lowestPriceEntry = orderBook.firstEntry();
        
        // Get earliest timestamp for this price
        TreeMap<Long, String> sellersAtLowestPrice = lowestPriceEntry.getValue();
        Map.Entry<Long, String> earliestSeller = sellersAtLowestPrice.firstEntry();
        
        // Remove the seller
        sellersAtLowestPrice.remove(earliestSeller.getKey());
        
        // If no more sellers at this price, remove the price level
        if (sellersAtLowestPrice.isEmpty()) {
            orderBook.remove(lowestPriceEntry.getKey());
        }

        return earliestSeller.getValue();
    }

    // Main method for testing
    public static void main(String[] args) {
        OrderBook book = new OrderBook();
        
        // Test Case 1: Basic ordering
        book.insert_order("seller1", 10.00, 1000L);
        book.insert_order("seller2", 5.00, 1001L);
        book.insert_order("seller3", 5.00, 1002L);
        
        // Should print: seller2 (lowest price, earliest timestamp)
        System.out.println("Test 1: " + book.get_lowest_seller_id());
        
        // Should print: seller3 (same price, later timestamp)
        System.out.println("Test 2: " + book.get_lowest_seller_id());
        
        // Should print: seller1 (highest price)
        System.out.println("Test 3: " + book.get_lowest_seller_id());
        
        // Test Case 2: Empty order book
        System.out.println("Test 4 (Empty book): " + book.get_lowest_seller_id());
        
        // Test Case 3: Edge cases
        try {
            book.insert_order("seller4", 0.00, 1003L); // Should throw exception
        } catch (IllegalArgumentException e) {
            System.out.println("Test 5: Successfully caught invalid price");
        }
        
        // Test Case 4: Large number of orders
        for (int i = 0; i < 1000; i++) {
            book.insert_order("seller" + i, 50.00 + (i % 10), System.currentTimeMillis() + i);
        }
        System.out.println("Test 6: Successfully inserted 1000 orders");
    }
}
