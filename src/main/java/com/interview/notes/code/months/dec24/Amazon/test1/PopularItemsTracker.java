package com.interview.notes.code.months.dec24.Amazon.test1;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/*

Amazon sells millions of products on its website and for better customer experience we like to show a
widget with the "most popular items bought" on the home page.
I'd like you to tell me how you'd go about calculating the top-k popular items sold on Amazon.
You can assume that you have a service and your service gets notified
in the form of a {customerId, itemId, timestamp} message whenever a customer purchases an item


 */
public class PopularItemsTracker {
    private final Map<String, Integer> itemCounts;
    private final Queue<Purchase> recentPurchases;
    private final int windowHours;
    private final int topK;

    public PopularItemsTracker(int windowHours, int topK) {
        this.itemCounts = new ConcurrentHashMap<>();
        this.recentPurchases = new LinkedList<>();
        this.windowHours = windowHours;
        this.topK = topK;
    }

    public static void main(String[] args) {
        // Test cases
        PopularItemsTracker tracker = new PopularItemsTracker(24, 3);
        Instant now = Instant.now();

        // Test 1: Basic functionality
        System.out.println("Test 1: Basic functionality");
        tracker.recordPurchase("user1", "item1", now);
        tracker.recordPurchase("user2", "item1", now);
        tracker.recordPurchase("user3", "item2", now);
        System.out.println("Top items: " + tracker.getTopItems());
        System.out.println("Expected: [item1, item2]");

        // Test 2: Time window
        System.out.println("\nTest 2: Time window");
        tracker = new PopularItemsTracker(24, 3);
        tracker.recordPurchase("user1", "item1", now.minus(25, ChronoUnit.HOURS));
        tracker.recordPurchase("user2", "item2", now);
        System.out.println("Top items: " + tracker.getTopItems());
        System.out.println("Expected: [item2]");

        // Test 3: Large data
        System.out.println("\nTest 3: Large data");
        tracker = new PopularItemsTracker(24, 3);
        Random random = new Random();
        for (int i = 0; i < 10000; i++) {
            String itemId = "item" + (random.nextInt(100) + 1);
            tracker.recordPurchase("user" + i, itemId, now);
        }
        System.out.println("Top 3 items: " + tracker.getTopItems());
    }

    public void recordPurchase(String customerId, String itemId, Instant timestamp) {
        Purchase purchase = new Purchase(customerId, itemId, timestamp);
        synchronized (this) {
            recentPurchases.add(purchase);
            itemCounts.merge(itemId, 1, Integer::sum);
            removeOldPurchases(timestamp);
        }
    }

    private void removeOldPurchases(Instant currentTime) {
        while (!recentPurchases.isEmpty() &&
                ChronoUnit.HOURS.between(recentPurchases.peek().timestamp, currentTime) >= windowHours) {
            Purchase oldPurchase = recentPurchases.poll();
            itemCounts.compute(oldPurchase.itemId, (k, v) -> v == 1 ? null : v - 1);
        }
    }

    public List<String> getTopItems() {
        return itemCounts.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(topK)
                .map(Map.Entry::getKey)
                .toList();
    }

    static class Purchase {
        String customerId;
        String itemId;
        Instant timestamp;

        Purchase(String customerId, String itemId, Instant timestamp) {
            this.customerId = customerId;
            this.itemId = itemId;
            this.timestamp = timestamp;
        }
    }
}
