package com.interview.notes.code.year.y2025.jan.amazon.test3;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*

"Let's imagine you're tasked with designing a system to determine the most popular items on Amazon.
I'd like you to tell me how you'd go about calculating what the most popular items on a website are.

You can assume that there's a service you can subscribe to that
sends you a {customerID, itemID, timestamp} message whenever a customer purchases an item."


 */
public class PopularItemTracker1 {
    private static final int TIME_WINDOW_MINUTES = 60; // 1 hour window
    private Map<String, Integer> itemCounts = new HashMap<>();
    private Map<String, List<Purchase>> timeWindowPurchases = new HashMap<>();

    // Simple test method
    public static void main(String[] args) {
        PopularItemTracker1 tracker = new PopularItemTracker1();
        LocalDateTime now = LocalDateTime.now();

        // Test Case 1: Basic functionality
        tracker.recordPurchase("user1", "item1", now);
        tracker.recordPurchase("user2", "item1", now);
        tracker.recordPurchase("user3", "item2", now);

        List<String> top1 = tracker.getTopNItems(1);
        System.out.println("Test 1 - Most popular item should be item1: " +
                ("item1".equals(top1.get(0)) ? "PASS" : "FAIL"));

        // Test Case 2: Time window
        tracker = new PopularItemTracker1();
        tracker.recordPurchase("user1", "item1", now.minusHours(2));
        tracker.recordPurchase("user2", "item2", now);

        top1 = tracker.getTopNItems(1);
        System.out.println("Test 2 - Old purchase should be excluded: " +
                ("item2".equals(top1.get(0)) ? "PASS" : "FAIL"));

        // Test Case 3: Large data input
        tracker = new PopularItemTracker1();
        for (int i = 0; i < 100000; i++) {
            tracker.recordPurchase("user" + i, "item" + (i % 10), now);
        }

        long startTime = System.currentTimeMillis();
        List<String> top10 = tracker.getTopNItems(10);
        long endTime = System.currentTimeMillis();

        System.out.println("Test 3 - Large data processing time: " +
                (endTime - startTime) + "ms" +
                (endTime - startTime < 1000 ? " PASS" : " FAIL"));
    }

    public void recordPurchase(String customerId, String itemId, LocalDateTime timestamp) {
        // Record the purchase
        Purchase purchase = new Purchase(customerId, itemId, timestamp);

        timeWindowPurchases.computeIfAbsent(itemId, k -> new ArrayList<>()).add(purchase);
        updatePopularItems(timestamp);
    }

    private void updatePopularItems(LocalDateTime currentTime) {
        itemCounts.clear();

        // Count items within the time window
        for (Map.Entry<String, List<Purchase>> entry : timeWindowPurchases.entrySet()) {
            String itemId = entry.getKey();
            List<Purchase> purchases = entry.getValue();

            int count = (int) purchases.stream()
                    .filter(p -> p.timestamp.isAfter(currentTime.minusMinutes(TIME_WINDOW_MINUTES)))
                    .count();

            if (count > 0) {
                itemCounts.put(itemId, count);
            }
        }
    }

    public List<String> getTopNItems(int n) {
        return itemCounts.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(n)
                .map(Map.Entry::getKey)
                .toList();
    }

    static class Purchase {
        String customerId;
        String itemId;
        LocalDateTime timestamp;

        Purchase(String customerId, String itemId, LocalDateTime timestamp) {
            this.customerId = customerId;
            this.itemId = itemId;
            this.timestamp = timestamp;
        }
    }
}
